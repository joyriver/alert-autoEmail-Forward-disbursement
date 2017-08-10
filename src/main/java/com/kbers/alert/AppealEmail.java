package com.kbers.alert;

import com.amazonaws.services.simpleemail.model.Destination;
import com.amzass.aop.Repeat;
import com.amzass.enums.common.DateFormat;
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
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.google.inject.Inject;
import com.mailman.utils.ServiceConstants;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/2/20 11:53
 */
public class AppealEmail {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppealEmail.class);
    @Inject
    public GmailHelper gmailHelper;

    private GoogleClientSecrets clientSecrets;
    private HttpTransport httpTransport;
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.MAIL_GOOGLE_COM);
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

        private String validate(String buyergmail, String account) throws IOException, GeneralSecurityException {
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
            List<Message> messages = gmail.users().messages().list(userId).setQ(" newer_than:1d").execute().getMessages();
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
                                System.out.println("subject:"+ subject);
//                                System.out.println("header:"+ header);
                                String keyemail[] = {"alliance", "seller-performance", "product-compliance", "performance-", "-performance",
                                        "notice+", "payments-", "listings-", "notice", "aviso", "notifica",
                                        "andtyler", "aschiffe", "bhanug", "jeff", "fondo-pago", "investigacion", "revisione"};
//     content.contains(keyWords1)
                                if(header.containsKey("To")) {
                                    if (!header.get("To").isEmpty()) {
                                        String sendEmail = header.get("To");
                                        System.out.println("header.get(\"To\"): " + sendEmail + "\n");

                                        if (sendEmail.contains(keyemail[0]) || sendEmail.contains(keyemail[1])
                                                || sendEmail.contains(keyemail[2])
                                                || sendEmail.contains(keyemail[3]) || sendEmail.contains(keyemail[4])
                                                || sendEmail.contains(keyemail[5]) || sendEmail.contains(keyemail[6])
                                                || sendEmail.contains(keyemail[7]) || sendEmail.contains(keyemail[8])
                                                || sendEmail.contains(keyemail[9]) || sendEmail.contains(keyemail[10])
                                                || sendEmail.contains(keyemail[11]) || sendEmail.contains(keyemail[12])
                                                || sendEmail.contains(keyemail[13]) || sendEmail.contains(keyemail[14])
                                                || sendEmail.contains(keyemail[15]) || sendEmail.contains(keyemail[16])
                                                || sendEmail.contains(keyemail[17])
                                                ) {
                                            System.out.println("Subject: " + subject + "\n");
                                            System.out.println("From:" + header.get("From") + "\n" + "To:" + header.get
                                                    ("To"));
                                            i++;
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
               return "appeal|"+month+"|"+account+"|-|"+day;
           }catch(TokenResponseException e1){
                System.err.println(userId + " is invalid:" + e1);
                return "appeal|"+month+"|"+account+"|-|"+day;
            } catch(MessagingException e2){
//                e.printStackTrace();
            System.err.println(userId + " is invalid:" + e2);
            }

            return "appeal|"+month+"|"+account+"|"+i+"|"+day;

        }

    public void scanAccount() throws IOException, GeneralSecurityException {
        AllAccounts alcount = ApplicationContext.getBean(AllAccounts.class);

        Condition cnd = null;
        List<AllAccounts.allCount> emailRecordList = alcount.dbManager.query(AllAccounts.allCount.class, cnd);
        System.out.println("emailRecordList.size():" + emailRecordList.size());

        for(int i= 0; i< emailRecordList.size(); i++) {
            String account = emailRecordList.get(i).childAccount;
            String gmail = emailRecordList.get(i).sellerEmail;
            String output = validate(gmail,account);
            System.out.println("扫面卖家邮箱后输出："+output);
//            OutputSummary(output);
        }
    }

    private Map<String, Long> notifyTimestamps = new HashMap<>();

    @Repeat(times = 8, sleepTime = 10)
    boolean OutputSummary(String data) {
        String url = String.format(Constants.accountSafetyAscript2Parameter, Constants.methodAppeal, PageUtils.encodeParamValue(data));

        long start = System.currentTimeMillis();
        String result;

        result = PageUtils.processResult(HttpUtils.getTextThriceIfFail(url));
        if (OrderEnums.ReturnCode.Success.name().equals(result)) {
//                notifyTimestamps.remove("WarehouseMonitor");
            LOGGER.info("成功将搜索重复指标数据{}返回{}, 耗时{}", data, "AppealMonitor", Tools.formatCostTime(start));
            return true;
        }
//        System.out.println(today());
        LOGGER.warn("将搜索重复指标数据{}返回{}失败: {}. 尝试重复操作.", data, "AppealMonitor", result);

        if (Tools.contains(result, "above the limit of 2000000 cells")) {
            // 不重复发送
            Long timestamp = notifyTimestamps.get("AppealMonitor");
            System.out.print(System.currentTimeMillis() + "\n");
            System.out.print("timestamp:" + timestamp + "\n");
            System.out.print("toMillis:" + TimeUnit.HOURS.toMillis(3) + "\n");
            if (timestamp != null && (System.currentTimeMillis() - timestamp < TimeUnit.HOURS.toMillis(3))) {
                LOGGER.info("{}对应表格清理通知邮件已在{}发送过了, 短期内无需重复发送", "AppealMonitor", DateFormat.DATE_TIME.format
                        (timestamp));
                return true;
            }

            // 数据总量超过上限, 需及时清理对应表格, 发送通知邮件  ServiceConstants.MAIL_MAN_RND_EMAIL
            try {
                String subject = String.format("Spreadsheet of %s needs cleanse and backup ASAP!!", "AppealMonitor");
                String content = subject + StringUtils.LF + StringUtils.defaultString(result);
                ApplicationContext.getBean(EmailSender.class).sendGmail(subject, content, EmailSender
                                .EmailContentType.PlainText,
                        ServiceConstants.MAIL_MAN, "Cleanse Notifier", new Destination().withToAddresses
                                ("joyriver7@gmail.com"));

                notifyTimestamps.put("AppealMonitor", System.currentTimeMillis());
            } catch (Exception e) {
                // -> Ignore
            }
            return true;
        }
        return false;
    }

    private void singleValidate() throws IOException, GeneralSecurityException {

        String gmail = "havvindisseawind@gmail.com";
        String account ="00US";
        validate(gmail,account);
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        AppealEmail security = ApplicationContext.getBean(AppealEmail.class);
        security.singleValidate();
//        security.scanAccount();
    }
}
