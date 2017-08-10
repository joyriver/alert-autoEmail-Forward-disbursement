package com.kbers.disbursement;

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
import com.kbers.alert.AllAccounts;
import com.kbers.alert.Constants;
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
            String day = Constants.thisDay(40);

            Gmail gmail = new Gmail.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("Application").build();

            try {
                com.google.api.services.gmail.model.Profile profile = gmail.users().getProfile(userId.toLowerCase())
                        .execute();
                System.out.println("profile:" + profile);
                System.out.println(profile.getEmailAddress());

                List<Message> messages = gmail.users().messages().list(userId).setQ(" newer_than:40d").execute().getMessages();
                System.out.println("messages:" + messages);
                if (messages != null) {
                    for (Message message : messages) {
                        String messageid = message.getId();  //  Refund on order
                        Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                        Map<String, String> header = gmailHelper.getHeader(msg);
                        if (!header.isEmpty()) {
                            Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                            String subject = header.get("Subject");

                            Session session = Session.getDefaultInstance(new Properties());
                            MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                            String content = gmailHelper.readContent(mimeMessage);

                            System.out.println("content:" + content);
//
                        }
                    }
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }


            return "appeal|" + month + "|" + account + "|" + i + "|" + day;

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



    private void singleValidate() throws IOException, GeneralSecurityException {

        String gmail = "linglingamazon@gmail.com";
        String account ="34US";
        validate(gmail,account);
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        AppealEmail security = ApplicationContext.getBean(AppealEmail.class);
        security.singleValidate();
//        security.scanAccount();
    }
}
