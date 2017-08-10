package com.kbers.replay;

import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amzass.aop.Repeat;
import com.amzass.database.DBManager;
import com.amzass.enums.common.ConfigEnums.AccountType;
import com.amzass.enums.common.DateFormat;
import com.amzass.model.common.Account;
import com.amzass.model.submit.OrderEnums;
import com.amzass.service.common.ApplicationContext;
import com.amzass.service.common.EmailSender;
import com.amzass.service.google.GmailHelper;
import com.amzass.utils.common.HttpUtils;
import com.amzass.utils.common.PageUtils;
import com.amzass.utils.common.Tools;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.*;
import com.google.inject.Inject;

import com.kbers.alert.*;
import com.sun.corba.se.impl.util.Utility;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.lang.Thread;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


import static com.amzass.utils.common.Constants.UTF8;
import static com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance;

/**
 * <a href="mailto:nathanael4ever@gmail.com">Nathanael Yang</a> 6/26/2017 8:44 AM
 */
public class ReplyEmail {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppealEmail.class);
    @Inject
    public GmailHelper gmailHelper;

    @Inject DBManager dbManager;

    private GoogleClientSecrets clientSecrets;
    private HttpTransport httpTransport;
    private  List<String> SCOPES = Collections.singletonList(GmailScopes.MAIL_GOOGLE_COM);
  //  private  List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_SETTINGS_BASIC,GmailScopes.GMAIL_SETTINGS_SHARING);


    private Map<String, Gmail> gmailServices = new ConcurrentHashMap<>();
    @Inject
    public void initialize() throws IOException, GeneralSecurityException {
        InputStream in = this.getClass().getResourceAsStream("/" + "client_secret.json");
        clientSecrets = GoogleClientSecrets.load(getDefaultInstance(), new InputStreamReader(in));
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    }

    AuthorizationCodeFlow initializeFlow(String userId) throws IOException {
        DataStoreFactory dataStoreFactory = new FileDataStoreFactory(new File("token/", userId));
        return new GoogleAuthorizationCodeFlow.Builder(httpTransport, getDefaultInstance(), clientSecrets, SCOPES)
                .setDataStoreFactory(dataStoreFactory)
                .setAccessType("offline").setApprovalPrompt("force").build();

    }

    @Repeat(times = 8, sleepTime = 10)
    private String validate(String buyergmail, String account) throws IOException, GeneralSecurityException,
            InterruptedException {
        String userId = buyergmail.toLowerCase().trim();
        AuthorizationCodeFlow codeFlow = this.initializeFlow(userId);
        Credential credential = codeFlow.loadCredential(userId);
        int i = 0;
        String month = Constants.thisMonth();
        String day = Constants.thisDay(0);

        Gmail gmail = new Gmail.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("Application").build();
        try {
            com.google.api.services.gmail.model.Profile profile = gmail.users().getProfile(userId.toLowerCase()).execute();
            System.out.println("profile:"+ profile);
            System.out.println(profile.getEmailAddress());
            List<Message> messages = gmail.users().messages().list(userId).setQ(" newer_than:4d").execute().getMessages();
            if (messages != null) {
                for (Message message : messages) {
                    String messageid = message.getId();  //  Refund on order
                    Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                    Map<String, String> header = gmailHelper.getHeader(msg);
                    if(!header.isEmpty()) {
                        Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                        String subject = header.get("Subject");
                        Session session = Session.getDefaultInstance(new Properties());
                        MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                        String content = gmailHelper.readContent(mimeMessage);
                        String keyWords1 = "Re: Your Amazon.";
                        String keyWords2 = "";

//                        if(header.get("To").contentEquals("1sqd0ck882tykmr@marketplace.amazon.ca")) {
                        if(header.containsKey("Subject")) {
                            if (!subject.isEmpty()) {
//                                System.out.println("subject:"+ subject);
//                                System.out.println("header:"+ header);   //  3+4+4+4+5+2 = 22
                                String keyemail[] = {"Important information about your listings on Amazon.com", "Policy Warning", "Product customization request from Amazon customer Evan Ottaviani",
                                        "Warning: Notice of Intellectual Property Rights Infringement", "Your Amazon Enquiry","Notice: Policy Warning","Warning: Notice of Intellectual Property Rights Infringement",
                                        "Your Amazon Listings", "Product details inquiry from Amazon customer Viva Naturals Inc", "Your Amazon.com Seller Account", "Avertissement concernant les politiques de vente",
                                        "Votre Compte Vendeur Amazon.fr", "Avertissement concernant les politiques de vente","Mitteilung: Verstoß gegen die Richtlinien","Infracción de Política",
                                        "Aviso: Notificación de Infracción de Derechos de Propiedad Intelectual", "Infracción de Política", "Avviso violazione norma", "Your Amazon.com Seller Account", "Notice: Policy Warning",
                                        "Warning: Notice of Intellectual Property Rights Infringement", "Infracción de Política","Policy&nbsp;Warning"};
//     content.contains(keyWords1)
                                if(header.containsKey("To")) {
                                    if (!header.get("To").isEmpty()) {
                                        String sendEmail = header.get("Subject");
                                        String sendFrom = header.get("From");
//                                        System.out.println("header.get(\"Subject\"): " + sendEmail + "\n");

//                                        if (sendEmail.contentEquals(keyemail[0]) || sendEmail.contentEquals(keyemail[1])
//                                                || sendEmail.contentEquals(keyemail[2])
//                                                || sendEmail.contentEquals(keyemail[3]) || sendEmail.contentEquals(keyemail[4])
//                                                || sendEmail.contentEquals(keyemail[5]) || sendEmail.contentEquals(keyemail[6])
//                                                || sendEmail.contentEquals(keyemail[7]) || sendEmail.contentEquals(keyemail[8])
//                                                || sendEmail.contentEquals(keyemail[9]) || sendEmail.contentEquals(keyemail[10])
//                                                || sendEmail.contentEquals(keyemail[11]) || sendEmail.contentEquals(keyemail[12])
//                                                || sendEmail.contentEquals(keyemail[13]) || sendEmail.contentEquals(keyemail[14])
//                                                || sendEmail.contentEquals(keyemail[15]) || sendEmail.contentEquals(keyemail[16])
//                                                || sendEmail.contentEquals(keyemail[17]) || sendEmail.contentEquals(keyemail[18])
//                                                || sendEmail.contentEquals(keyemail[19]) || sendEmail.contentEquals(keyemail[20])
//                                                || sendEmail.contentEquals(keyemail[21]) || sendEmail.contentEquals(keyemail[22])
//                                                )  //  备份关键字  notice-dispute@amazon
                                        GmailList MailDatabase = ApplicationContext.getBean(GmailList.class);

                                        String a1= account;
                                        String country = null;
                                        String FromMail = header.get("From");
                                        String ToMail = header.get("To");
                                        String DateMark = header.get("Date");
//                                        System.out.println("Account: " + account + "\n");
//                                        System.out.println("Account.length(): " + account.length()+ "\n");
                                        if(account.length() == 4)
                                            country = account.substring(2,4);
                                        else if(account.length() == 5)
                                            country = account.substring(3,5);



                                        String a2= country;
                                        String a3= account;
                                        String a4= sendFrom;
                                        String a5= ToMail;
                                        String a6= DateMark;
                                        String a7= sendEmail;
                                        String a8= html2Text(content);

//                                        if(!queryKbDatabase(account, sendEmail,DateMark))
//                                        MailDatabase.dbManager.save(new GmailList.gmailList(Tools.generateUUID(), a1, a2, a3, a4, a5, a6, a7,a8), GmailList.gmailList.class);

                                         if(sendFrom.contains("@amazon") && (content.contains("notice-dispute@amazon") || content.contains("Complaint ID"))&& ! sendEmail.contains("RE:")) {

//                                            System.out.println("Subject: " + subject + "\n");
                                            System.out.println("From:" + FromMail + "\n" + "To:" + ToMail);

                                            i++;
                                            ReplySES rpses = ApplicationContext.getBean(ReplySES.class);
                                            content = html2Text(content);
                                            content = "From:"+FromMail +"To:"+ ToMail +"\n\n"+ content;
                                            Content textBody = new Content().withData(content).withCharset(UTF8);
                                            Body bodys = new Body().withText(textBody);
                                            subject = subject+" ["+ account +","+DateMark+"]";

                                            rpses.sendBySES(bodys,subject);
//                                            Label label = new Label().setName("Infringement Forward");
//                                            label = gmail.users().labels().create(userId,label).execute();

                                            gmail.users().messages().trash(userId, messageid).execute();
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        } catch(GoogleJsonResponseException e){
            System.err.println(userId + " is invalid:" + e);
            return "Forward|"+month+"|"+account+"|-|"+day;
        }catch(TokenResponseException e1){
            System.err.println(userId + " is invalid:" + e1);
            return "Forward|"+month+"|"+account+"|-|"+day;
        } catch(MessagingException e2){
//                e.printStackTrace();
            System.err.println(userId + " is invalid:" + e2);
            Thread.sleep(2000);
        }
        catch(SocketTimeoutException e3){
            System.err.println(userId + " connect time out :" + e3);
            Thread.sleep(2000);
        }

        return "Forward|"+month+"|"+account+"|"+i+"|"+day;

    }

    @Repeat(times = 8, sleepTime = 10)
    private String SaveEmails(String buyergmail, String account) throws IOException, GeneralSecurityException,
            InterruptedException {
        String userId = buyergmail.toLowerCase().trim();
        AuthorizationCodeFlow codeFlow = this.initializeFlow(userId);
        Credential credential = codeFlow.loadCredential(userId);
        int i = 0;
        String month = Constants.thisMonth();
        String day = Constants.thisDay(0);

        Gmail gmail = new Gmail.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("Application").build();
        try {
            com.google.api.services.gmail.model.Profile profile = gmail.users().getProfile(userId.toLowerCase()).execute();
            System.out.println("profile:"+ profile);
            System.out.println(profile.getEmailAddress());
            List<Message> messages = gmail.users().messages().list(userId).setQ(" newer_than:14d").execute().getMessages();
            if (messages != null) {
                for (Message message : messages) {
                    String messageid = message.getId();  //  Refund on order
                    Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                    Map<String, String> header = gmailHelper.getHeader(msg);
                    if(!header.isEmpty()) {
                        Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                        String subject = header.get("Subject");
                        Session session = Session.getDefaultInstance(new Properties());
                        MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                        String content = gmailHelper.readContent(mimeMessage);
                        String keyWords1 = "Re: Your Amazon.";
                        String keyWords2 = "";

//                        if(header.get("To").contentEquals("1sqd0ck882tykmr@marketplace.amazon.ca")) {
                        if(header.containsKey("Subject")) {
                            if (!subject.isEmpty()) {

                                if(header.containsKey("To")) {
                                    if (!header.get("To").isEmpty()) {
                                        String sendEmail = header.get("Subject");
                                        String sendFrom = header.get("From");
//
                                        GmailList MailDatabase = ApplicationContext.getBean(GmailList.class);

                                        String a1 = account;
                                        String country = null;
                                        String FromMail = header.get("From");
                                        String ToMail = header.get("To");
                                        String DateMark = header.get("Date");
//                                        System.out.println("Account: " + account + "\n");
//                                        System.out.println("Account.length(): " + account.length()+ "\n");
                                        if (account.length() == 4)
                                            country = account.substring(2, 4);
                                        else if (account.length() == 5)
                                            country = account.substring(3, 5);


                                        String a2 = country;
                                        String a3 = account;
                                        String a4 = sendFrom;
                                        String a5 = ToMail;
                                        String a6 = DateMark;
                                        String a7 = sendEmail;
                                        String a8 = html2Text(content);

                                        if(!queryKbDatabase(account, sendEmail,DateMark))
                                        MailDatabase.dbManager.save(new GmailList.gmailList(Tools.generateUUID(), a1, a2, a3, a4, a5, a6, a7,a8), GmailList.gmailList.class);


                                    }
                                }
                            }
                        }
                    }

                }
            }
        } catch(GoogleJsonResponseException e){
            System.err.println(userId + " is invalid:" + e);
            return "Save|"+month+"|"+account+"|-|"+day;
        }catch(TokenResponseException e1){
            System.err.println(userId + " is invalid:" + e1);
            return "Save|"+month+"|"+account+"|-|"+day;
        } catch(MessagingException e2){
//                e.printStackTrace();
            System.err.println(userId + " is invalid:" + e2);
            Thread.sleep(2000);
        }
        catch(SocketTimeoutException e3){
            System.err.println(userId + " connect time out :" + e3);
            Thread.sleep(2000);
        }

        return "Save|"+month+"|"+account+"|"+i+"|"+day;

    }

    public void scanAccount(boolean forwordOrSaving) throws IOException, GeneralSecurityException, InterruptedException {
        AccountListDownloading security = ApplicationContext.getBean(AccountListDownloading.class);
        security.downloadAccountList();

        AllAccounts alcount = ApplicationContext.getBean(AllAccounts.class);

        Condition cnd = null;
        List<AllAccounts.allCount> emailRecordList = alcount.dbManager.query(AllAccounts.allCount.class, cnd);
        System.out.println("emailRecordList.size():" + emailRecordList.size());

        for(int i= 0; i< emailRecordList.size(); i++) {
            String account = emailRecordList.get(i).childAccount;
            String gmail = emailRecordList.get(i).sellerEmail;
            String output;
            if(forwordOrSaving) {
                output = validate(gmail, account);
                OutputSummary(output);
                System.out.println("扫面卖家邮箱后输出："+output);
            }
            else {
                output = SaveEmails(gmail, account);
                System.out.println("扫面卖家邮箱后输出："+output);
            }


//            String userId = gmail.toLowerCase().trim();
//            ForwardEmail(userId,output);
//            OutputSummary(output);

        }
    }


    public Gmail getGmailService(String userId) throws IOException, GeneralSecurityException {
        String key = this.validateUserId(userId);
        Gmail gmailService = this.gmailServices.get(key);
        if (gmailService != null) {
            return gmailService;
        }
        AuthorizationCodeFlow codeFlow = this.initializeFlow(userId);
        Credential credential = codeFlow.loadCredential(userId);
        gmailService = new Gmail.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("Application").build();
//        gmailService = new Gmail.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();

        this.gmailServices.put(key, gmailService);
        return gmailService;
    }

    public String validateUserId(String userId) {
        return userId.toLowerCase();
    }


    private void ForwardEmail(String userId, String output) throws IOException, GeneralSecurityException {
        Gmail gmailService = this.getGmailService(userId);
        AuthorizationCodeFlow codeFlow = this.initializeFlow(userId);
        Credential credential = codeFlow.loadCredential(userId);

        createLabel(gmailService, userId, "KB Infringement");

//        ForwardingAddress address = new ForwardingAddress().setForwardingEmail("keywordsfilter@gmail.com");
//        address.
////        System.out.print("address:"+address);

//        if (createAddressResult.getVerificationStatus().equals("accepted")) {
//            AutoForwarding autoForwarding = new AutoForwarding().setEnabled(true).setEmailAddress(address.getForwardingEmail()).setDisposition("trash");

//            autoForwarding = gmailService.users().settings().updateAutoForwarding(userId, autoForwarding).execute();
//        }
//          gmailService.users().settings().forwardingAddresses("jeromeinjesus@gmail.com","accepted");
//        AutoForwarding afd = null;
//        afd.setEnabled(true);
//        afd.setEmailAddress("jeromeinjesus@gmail.com");
//
//        gmailService.users().settings().updateAutoForwarding(userId,afd).execute();
//          ForwardingAddress createAddressResult = gmailService.users().settings().forwardingAddresses().create(userId, address).execute();


////        System.out.print(new ModifyThreadRequest().setRemoveLabelIds());
//        GenericUrl url = new GenericUrl(GmailScopes.GMAIL_SETTINGS_BASIC);
//        final HttpRequest request = httpTransport.createRequestFactory().buildGetRequest(url) ;
//        final HttpContext httpContext = new BasicHttpContext();
//        AuthState authState = (AuthState) httpContext.getAttribute(ClientContext.TARGET_AUTH_STATE);
//
////        final AuthState authState = null;
//        authState.setAuthScope(AuthScope.ANY);
//        authState.setAuthScheme(new BasicScheme());
//        System.out.print("authState:"+authState);
//        final String password = "Ibport777!$$)))";
//        Credentials credentials = new UsernamePasswordCredentials("ordermanibport@gmail.com", password);
//        authState.setCredentials(credentials);
////        Filter sd = null;
//        System.out.print(address.setVerificationStatus("accepted"));
//        System.out.print(gmailService.users().settings().getAutoForwarding(userId).buildHttpRequest().execute());


    }

    public static Label createLabel(Gmail service, String userId, String newLabelName)
            throws IOException {
        Label label = new Label().setName(newLabelName);
        label = service.users().labels().create(userId, label).execute();

        System.out.println("Label id: " + label.getId());
        System.out.println(label.toPrettyString());

        return label;
    }

    private Map<String, Long> notifyTimestamps = new HashMap<>();

    @Repeat(times = 8, sleepTime = 10)
    boolean OutputSummary(String data) {
        String url = String.format(ConstantsReplay.accountSafetyAscript2Parameter, ConstantsReplay.methodForward, PageUtils.encodeParamValue(data));

        long start = System.currentTimeMillis();
        String result;

        result = PageUtils.processResult(HttpUtils.getTextThriceIfFail(url));
        if (OrderEnums.ReturnCode.Success.name().equals(result)) {
//                notifyTimestamps.remove("WarehouseMonitor");
            LOGGER.info("成功将搜索重复指标数据{}返回{}, 耗时{}", data, "ForwardMonitor", Tools.formatCostTime(start));
            return true;
        }
//        System.out.println(today());
        LOGGER.warn("将搜索重复指标数据{}返回{}失败: {}. 尝试重复操作.", data, "ForwardMonitor", result);

        if (Tools.contains(result, "above the limit of 2000000 cells")) {
            // 不重复发送
            Long timestamp = notifyTimestamps.get("ForwardMonitor");
            System.out.print(System.currentTimeMillis() + "\n");
            System.out.print("timestamp:" + timestamp + "\n");
            System.out.print("toMillis:" + TimeUnit.HOURS.toMillis(3) + "\n");
            if (timestamp != null && (System.currentTimeMillis() - timestamp < TimeUnit.HOURS.toMillis(3))) {
                LOGGER.info("{}对应表格清理通知邮件已在{}发送过了, 短期内无需重复发送", "ForwardMonitor", DateFormat.DATE_TIME.format
                        (timestamp));
                return true;
            }

            // 数据总量超过上限, 需及时清理对应表格, 发送通知邮件  ServiceConstants.MAIL_MAN_RND_EMAIL
            try {
                String subject = String.format("Spreadsheet of %s needs cleanse and backup ASAP!!", "ForwardMonitor");
                String content = subject + StringUtils.LF + StringUtils.defaultString(result);
                ApplicationContext.getBean(EmailSender.class).sendGmail(subject, content, EmailSender
                                .EmailContentType.PlainText,
                        new Account("mailmanibport@gmail.com", "ibport7777", AccountType.EmailSender), "Cleanse Notifier", new Destination().withToAddresses
                                ("jeromeinjesus@gmail.com"));

                notifyTimestamps.put("ForwardMonitor", System.currentTimeMillis());
            } catch (Exception e) {
                // -> Ignore
            }
            return true;
        }
        return false;
    }

    private void singleValidate() throws IOException, GeneralSecurityException, InterruptedException {

        String gmail = "havvindisseawind@gmail.com";
        String account ="00US";
        validate(gmail,account);
    }

    public static String sqliteEscape(String keyWord){
        keyWord = keyWord.replace("/", "//");
        keyWord = keyWord.replace("'", "''");
        keyWord = keyWord.replace("[", "/[");
        keyWord = keyWord.replace("]", "/]");
        keyWord = keyWord.replace("%", "/%");
        keyWord = keyWord.replace("&","/&");
        keyWord = keyWord.replace("_", "/_");
        keyWord = keyWord.replace("(", "/(");
        keyWord = keyWord.replace(")", "/)");
        keyWord = keyWord.replace("@", "/@");
        return keyWord;
    }

    public  boolean queryKbDatabase(String acc, String subject,String date){

            Condition cnd = Cnd.where("ACC", "like", acc).and("SubjectS", "like", subject).and("DateS",
                    "like", date);
            List<GmailList.gmailList> maillist = dbManager.query(GmailList.gmailList.class, cnd);
            if(maillist.size() == 0)
                return false;
            else
                return true;

    }


    /**
     * html转化为text
     * @param inputString
     * @return
     */
    public static String html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException, InterruptedException {
        ReplyEmail security = ApplicationContext.getBean(ReplyEmail.class);
//        security.singleValidate();
        security.scanAccount(false);
//        security.OutputSummary("Forward|07|100CA|0|03");
    }
}
