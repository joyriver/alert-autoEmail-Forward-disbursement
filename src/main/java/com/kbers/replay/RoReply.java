package com.kbers.replay;

import com.amzass.service.common.ApplicationContext;
import com.amzass.service.google.GmailHelper;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.google.inject.Inject;
import com.kbers.disbursement.Constants;
import com.kbers.disbursement.Disbursement;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance;

/**
 * <a href="mailto:nathanael4ever@gmail.com">Nathanael Yang</a> 6/26/2017 7:49 AM
 */
public class RoReply {
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

    public void getDisbursement(int i) throws IOException, GeneralSecurityException, Exception {
        String value = ConstantsReplay.getAccountEmailList(i);
        String [] valuepart=value.split("/");
        String account_list=valuepart[0].trim();
        String email_list=valuepart[1].trim();
        String buyergmail = email_list;

        WebToken(account_list,buyergmail);

    }

    private void WebToken(String account_list,String buyergmail) throws IOException, GeneralSecurityException{

        String userId = buyergmail.toLowerCase().trim();
        AuthorizationCodeFlow codeFlow = this.initializeFlow(userId);
        Credential credential = codeFlow.loadCredential(userId);

        Gmail gmail = new Gmail.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("Application").build();

//        com.google.api.services.gmail.model.Profile profile = gmail.users().getProfile(userId.toLowerCase())
//                .execute();
//        System.out.println("profile:" + profile);
//        System.out.println(profile.getEmailAddress());

        String title = Constants.getTitle();
        String [] title0=title.split("/");
        String sb1="",sb2="";

        if(account_list.contains("UK")) {
            try {
                WebTokenUK(gmail, userId, account_list);
            } catch (Exception e) {
                e.printStackTrace();
                EuropeException(account_list, userId, title, title0, sb1, sb2, e);
            }
        } else if(account_list.contains("US")){
            try {
                WebTokenUS(gmail, userId, account_list);
            } catch (Exception e) {
                e.printStackTrace();
                AmericaException(account_list,userId, title, title0, sb1, sb2, e);
            }
        }  else if(account_list.contains("ES")) {
            try {
                WebTokenES(gmail, userId, account_list);
            } catch (Exception e) {
                e.printStackTrace();
                EuropeException(account_list, userId, title, title0, sb1, sb2, e);
            }
        }  else if(account_list.contains("FR")) {
            try {
                WebTokenFR(gmail, userId, account_list);
            } catch (Exception e) {
                e.printStackTrace();
                EuropeException(account_list, userId, title, title0, sb1, sb2, e);
            }
        }  else if(account_list.contains("IT")) {
            try {
                WebTokenIT(gmail, userId, account_list);
            } catch (Exception e) {
                e.printStackTrace();
                EuropeException(account_list, userId, title, title0, sb1, sb2, e);
            }
        }  else if(account_list.contains("DE")) {
            try {
                WebTokenDE(gmail, userId, account_list);
            } catch (Exception e) {
                e.printStackTrace();
                EuropeException(account_list, userId, title, title0, sb1, sb2, e);
            }
        } else if(account_list.contains("CA")) {
            try {
                WebTokenCA(gmail, userId, account_list);
            } catch (Exception e) {
                AmericaException(account_list,userId, title, title0, sb1, sb2, e);
            }
        }  else if(account_list.contains("MX")) {
            try {
                WebTokenMX(gmail, userId, account_list);
            } catch (Exception e) {
                e.printStackTrace();
                EuropeException(account_list, userId, title, title0, sb1, sb2, e);
            }
        }  else if(account_list.contains("JP")) {
            try {
                WebTokenJP(gmail, userId, account_list);
            } catch (Exception e) {
                e.printStackTrace();
                AsiaException(account_list, userId, title, title0, sb1, sb2, e);
            }
        }
    }

    private void AsiaException(String account_list, String userId, String title, String[] title0, String sb1, String
            sb2, Exception e) throws IOException {
        String sb3;
        System.out.println("USER_ID: "+userId+", Exception:"+e);
        sb3 = title0[1]+"/"+title0[2]+"/"+title0[0];
        double sb4 = 0;
        System.out.println("title:"+title);
        String ch3="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=Total&pa1="+sb2+"&pa2="+sb3+"&pa3="+sb1+"&pa4="+sb4+"&pa5="+"-"+"&ACC="+account_list;
        System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get().text());
    }

    private void AmericaException(String account_list, String userId,String title, String[] title0, String sb1, String sb2, Exception e) throws IOException {
        String sb3;
        e.printStackTrace();
        System.out.println("USER_ID: "+userId+", Exception:"+e);

        sb3 = title0[0]+"/"+title0[1]+"/"+title0[2];
        ;
        double sb4 = 0;
        System.out.println("title:"+title);
        String ch3="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=Total&pa1="+sb2+"&pa2="+sb3+"&pa3="+sb1+"&pa4="+sb4+"&pa5="+"-"+"&ACC="+account_list;
        System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get().text());
    }

    private void EuropeException(String account_list, String userId, String title, String[] title0, String sb1, String sb2, Exception e) throws IOException {
        String sb3;
        System.out.println("USER_ID: "+userId+", Exception:"+e);

        sb3 = title0[1]+"/"+title0[0]+"/"+title0[2];
        ;
        double sb4 = 0;
        System.out.println("title:"+title);
        String ch3="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=Total&pa1="+sb2+"&pa2="+sb3+"&pa3="+sb1+"&pa4="+sb4+"&pa5="+"-"+"&ACC="+account_list;
        System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get().text());
    }

    public void WebTokenUK(Gmail gmail, String userId, String account_list) throws IOException, Exception {
        List<Message> messages = gmail.users().messages().list(userId).setQ("From:seller-notification@amazon.co.uk subject:(\"Your Amazon.co.uk disbursement is on its way\") newer_than:6d").execute().getMessages();
        if (messages != null) {
            System.out.println("messages:" + messages);
            for (Message message : messages) {
                String messageid = message.getId();  //  Refund on order
                Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                Map<String, String> header = gmailHelper.getHeader(msg);
                if (!header.isEmpty()) {
                    String sb1, sb2, sb3, sb = null, ss = null;
                    int hang = 0;

                    Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                    ss = header.get("Subject");

                    Session session = Session.getDefaultInstance(new Properties());
                    MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                    sb = gmailHelper.readContent(mimeMessage);

                    System.out.println("sb:" + sb);

                    sb1 = StringUtils.substringBetween(sb, "(", ")");
                    sb2 = StringUtils.substringBetween(sb, " of ", " on ");
                    sb3 = StringUtils.substringBetween(sb, " on ", ".");
                    sb3 = sb3.trim();

                    if (sb3.contains("/")) {
                        String[] sb33 = sb3.split("/");
                        System.out.println(sb33[0]);
                        System.out.println(sb33[1]);
                        System.out.println(sb33[2]);

                        String sb20 = sb2.replace("£", "");
                        double sb22 = Double.valueOf(sb20).doubleValue();
                        double sb4 = sb22 * Constants.getGBPrateDouble();
                        double sb5 = 0.1 * sb4;

                        BigDecimal b1 = new BigDecimal(sb4);
                        BigDecimal b2 = new BigDecimal(sb5);
                        sb4 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        sb5 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        sb2 = URLEncoder.encode(sb2, "UTF8");

                        String ch3 = "https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=Total&pa1=" + sb2 + "&pa2=" + sb3 + "&pa3=" + sb1 + "&pa4=" + sb4 + "&pa5=" + sb5 + "&ACC=" + account_list;

                        System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get()
                                .text());
                        String ch2 = "https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=CopyOrder_ACC&pa1=" + sb20 + "&pa2=" + sb3 + "&pa3=" + sb1 + "&ACC=" + account_list;
                    }
                }
            }
        }
    }

    public void WebTokenUS(Gmail gmail, String userId, String account_list) throws IOException, Exception {
        List<Message> messages = gmail.users().messages().list(userId).setQ("From:seller-notification@amazon.com " +
                "subject:(\"Your payment is on its way\") newer_than:6d").execute().getMessages();
        if (messages != null) {
            System.out.println("messages:" + messages);
            for (Message message : messages) {
                String messageid = message.getId();  //  Refund on order
                Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                Map<String, String> header = gmailHelper.getHeader(msg);
                if (!header.isEmpty()) {
                    String sb1, sb2, sb3, sb = null, ss = null;
                    int hang = 0;

                    Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                    ss = header.get("Subject");

                    Session session = Session.getDefaultInstance(new Properties());
                    MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                    sb = gmailHelper.readContent(mimeMessage);

                    System.out.println("sb:" + sb);

                    sb1 = StringUtils.substringBetween(sb, "(", ")");
                    sb2 = StringUtils.substringBetween(sb, "of ", " on ");
                    sb3 = StringUtils.substringBetween(sb, " on ", ".");
                    sb3 = sb3.trim();
                    System.out.println(ss);
                    System.out.println(account_list);
                    System.out.println(sb1);
                    System.out.println(sb2);
                    System.out.println(sb3);

                    String[] sb33 = sb3.split("/");
                    System.out.println(sb33[0]);
                    System.out.println(sb33[1]);
                    System.out.println(sb33[2]);

                    String sb20 = sb2.replace("$", "");
                    sb20 = sb20.replace(" ", "");
                    sb20 = sb20.trim();
                    float sb22 = Float.valueOf(sb20).floatValue();
                    double sb4 = sb22;
                    double sb5 = 0.1 * sb4;

                    BigDecimal b1 = new BigDecimal(sb4);
                    BigDecimal b2 = new BigDecimal(sb5);
                    sb4 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    sb5 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                    sb2 = URLEncoder.encode(sb2, "UTF8");

                    String ch3 = "https://script.google" +
                            ".com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method" +
                            "=Total&pa1=" + sb2 + "&pa2=" + sb3 + "&pa3=" + sb1 + "&pa4=" + sb4 + "&pa5=" + sb5 +
                            "&ACC=" + account_list;
                    System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get().text());
                    String ch2 = "https://script.google" +
                            ".com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method" +
                            "=CopyOrder_ACC&pa1=" + sb20 + "&pa2=" + sb3 + "&pa3=" + sb1 + "&ACC=" + account_list;

                }
            }
        }
    }

    public void WebTokenES(Gmail gmail, String userId, String account_list) throws IOException, Exception {
        List<Message> messages = gmail.users().messages().list(userId).setQ("From:notificacion-vendedor@amazon.es " +
                "subject:(\"La transferencia de fondos de Amazon.es está en camino\") newer_than:6d").execute().getMessages();
        if (messages != null) {
            System.out.println("messages:" + messages);
            for (Message message : messages) {
                String messageid = message.getId();  //  Refund on order
                Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                Map<String, String> header = gmailHelper.getHeader(msg);
                if (!header.isEmpty()) {
                    String sb1, sb2, sb3, sb = null, ss = null;
                    int hang = 0;

                    Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                    ss = header.get("Subject");

                    Session session = Session.getDefaultInstance(new Properties());
                    MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                    sb = gmailHelper.readContent(mimeMessage);

                    System.out.println("sb:" + sb);

                    sb1 = StringUtils.substringBetween(sb, " terminada ", ".");
                    sb2 = StringUtils.substringBetween(sb, " de ", " a ");
                    sb3 = StringUtils.substringBetween(sb, "El", "se");
                    sb3 = sb3.trim();
                    sb3 = sb3.replace("-", "/");
                    System.out.println(ss);
                    System.out.println(account_list);
                    System.out.println(sb1);
                    System.out.println(sb2);
                    System.out.println(sb3);

                    String[] sb33 = sb3.split("/");
                    System.out.println(sb33[0]);
                    System.out.println(sb33[1]);
                    System.out.println(sb33[2]);

                    String sb20 = sb2.replace("EUR", "");
                    sb20 = sb20.replace(".", "");
                    sb20 = sb20.replace(",", ".");

                    double sb22 = Double.valueOf(sb20).doubleValue();
                    double sb4 = sb22 * Constants.getEUrateDouble();
                    double sb5 = 0.1 * sb4;

                    BigDecimal b1 = new BigDecimal(sb4);
                    BigDecimal b2 = new BigDecimal(sb5);
                    sb4 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    sb5 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                    sb2 = URLEncoder.encode(sb2, "UTF8");
                    String ch3 = "https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=Total&pa1=" + sb2 + "&pa2=" + sb3 + "&pa3=" + sb1 + "&pa4=" + sb4 + "&pa5=" + sb5 + "&ACC=" + account_list;

                    System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get()
                            .text());

                    String ch2 = "https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=CopyOrder_ACC&pa1=" + sb20 + "&pa2=" + sb3 + "&pa3=" + sb1 + "&ACC=" + account_list;
                }
            }
        }
    }

    public void WebTokenFR(Gmail gmail, String userId, String account_list) throws IOException, Exception {
        List<Message> messages = gmail.users().messages().list(userId).setQ("From:notification-vendeur@amazon.fr " +
                "subject:(\"Versement Amazon.fr en cours\") newer_than:6d").execute().getMessages();
        if (messages != null) {
            System.out.println("messages:" + messages);
            for (Message message : messages) {
                String messageid = message.getId();  //  Refund on order
                Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                Map<String, String> header = gmailHelper.getHeader(msg);
                if (!header.isEmpty()) {
                    String sb1, sb2, sb3, sb = null, ss = null;
                    int hang = 0;

                    Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                    ss = header.get("Subject");

                    Session session = Session.getDefaultInstance(new Properties());
                    MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                    sb = gmailHelper.readContent(mimeMessage);

                    System.out.println("sb:" + sb);


                    sb1 = StringUtils.substringBetween(sb, "termine ", ") ");
                    sb2 = StringUtils.substringBetween(sb, " de ", " le ");
                    sb3 = StringUtils.substringBetween(sb, "€ le ", ".");
                    sb3 = sb3.trim();
                    System.out.println(ss);
                    System.out.println(account_list);
                    System.out.println(sb1);
                    System.out.println(sb2);
                    System.out.println(sb3);
                    String[] sb33 = sb3.split("/");
                    System.out.println(sb33[0]);
                    System.out.println(sb33[1]);
                    System.out.println(sb33[2]);

                    String sb20 = sb2.replace("€", "");
                    sb20 = sb20.replace(".", "");
                    sb20 = sb20.replace(",", ".");

                    double sb22 = Double.valueOf(sb20).doubleValue();
                    double sb4 = sb22 * Constants.getEUrateDouble();
                    double sb5 = 0.1 * sb4;

                    BigDecimal b1 = new BigDecimal(sb4);
                    BigDecimal b2 = new BigDecimal(sb5);
                    sb4 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    sb5 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                    sb2 = URLEncoder.encode(sb2, "UTF8");

                    String ch3 = "https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=Total&pa1=" + sb2 + "&pa2=" + sb3 + "&pa3=" + sb1 + "&pa4=" + sb4 + "&pa5=" + sb5 + "&ACC=" + account_list;

                    System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get()
                            .text());
                    String ch2 = "https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=CopyOrder_ACC&pa1=" + sb20 + "&pa2=" + sb3 + "&pa3=" + sb1 + "&ACC=" + account_list;
                }
            }
        }
    }

    public void WebTokenIT(Gmail gmail, String userId, String account_list) throws IOException, Exception {
        List<Message> messages = gmail.users().messages().list(userId).setQ("From:notifica-venditore@amazon.it " +
                "subject:(\"Il pagamento da parte di Amazon.it è in arrivo\") newer_than:6d").execute().getMessages();
        if (messages != null) {
            System.out.println("messages:" + messages);
            for (Message message : messages) {
                String messageid = message.getId();  //  Refund on order
                Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                Map<String, String> header = gmailHelper.getHeader(msg);
                if (!header.isEmpty()) {
                    String sb1, sb2, sb3, sb = null, ss = null;
                    int hang = 0;

                    Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                    ss = header.get("Subject");

                    Session session = Session.getDefaultInstance(new Properties());
                    MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                    sb = gmailHelper.readContent(mimeMessage);

                    System.out.println("sb:" + sb);

                    sb1=StringUtils.substringBetween(sb," termina ",")");
                    sb2=StringUtils.substringBetween(sb," di "," su ");
                    sb3=StringUtils.substringBetween(sb," su ",".");
                    sb3=sb3.trim();
                    System.out.println(ss);
                    System.out.println(account_list);
                    System.out.println(sb1);
                    System.out.println(sb2);
                    System.out.println(sb3);

                    String[] sb33 = sb3.split("/");
                    System.out.println(sb33[0]);
                    System.out.println(sb33[1]);
                    System.out.println(sb33[2]);

                    String sb20 = sb2.replace("€", "");
                    sb20 = sb20.replace(".", "");
                    sb20 = sb20.replace(",", ".");

                    double sb22 = Double.valueOf(sb20).doubleValue();
                    double sb4 = sb22 * Constants.getEUrateDouble();
                    double sb5 = 0.1 * sb4;

                    BigDecimal b1 = new BigDecimal(sb4);
                    BigDecimal b2 = new BigDecimal(sb5);
                    sb4 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    sb5 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                    sb2 = URLEncoder.encode(sb2, "UTF8");

                    String ch3 = "https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=Total&pa1=" + sb2 + "&pa2=" + sb3 + "&pa3=" + sb1 + "&pa4=" + sb4 + "&pa5=" + sb5 + "&ACC=" + account_list;

                    System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get()
                            .text());
                    String ch2 = "https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=CopyOrder_ACC&pa1=" + sb20 + "&pa2=" + sb3 + "&pa3=" + sb1 + "&ACC=" + account_list;
                }
            }
        }
    }

    public void WebTokenDE(Gmail gmail, String userId, String account_list) throws IOException, Exception {
        List<Message> messages = gmail.users().messages().list(userId).setQ("From:haendler-benachrichtigungen@amazon" +
                ".de subject:(\"Auszahlung Ihrer Amazon Verkaufserlöse\") newer_than:6d").execute().getMessages();
        if (messages != null) {
            System.out.println("messages:" + messages);
            for (Message message : messages) {
                String messageid = message.getId();  //  Refund on order
                Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                Map<String, String> header = gmailHelper.getHeader(msg);
                if (!header.isEmpty()) {
                    String sb1, sb2, sb3, sb = null, ss = null;
                    int hang = 0;

                    Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                    ss = header.get("Subject");

                    Session session = Session.getDefaultInstance(new Properties());
                    MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                    sb = gmailHelper.readContent(mimeMessage);

                    System.out.println("sb:" + sb);

                    sb1=StringUtils.substringBetween(sb,"den ",")");
                    sb2=StringUtils.substringBetween(sb," von "," veranlasst");
                    sb3=StringUtils.substringBetween(sb," am"," eine ");
                    sb3=sb3.trim();
                    sb3=sb3.replace(".", "/");
                    System.out.println(ss);
                    System.out.println(account_list);
                    System.out.println(sb1);
                    System.out.println(sb2);
                    System.out.println(sb3);

                    String []sb33=sb3.split("/");
                    System.out.println(sb33[0]);
                    System.out.println(sb33[1]);
                    System.out.println(sb33[2]);

                    String sb20=sb2.replace("€","");
                    sb20=sb20.replace(".","");
                    sb20=sb20.replace(",",".");

                    double sb22=Double.valueOf(sb20).doubleValue();
                    double sb4=sb22* Constants.getEUrateDouble();
                    double sb5=0.1*sb4;

                    BigDecimal b1=new BigDecimal(sb4);
                    BigDecimal b2=new BigDecimal(sb5);
                    sb4=b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    sb5=b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

                    sb2= URLEncoder.encode(sb2, "UTF8");

                    String ch3="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=Total&pa1="+sb2+"&pa2="+sb3+"&pa3="+sb1+"&pa4="+sb4+"&pa5="+sb5+"&ACC="+account_list;
                    System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get().text());
                    String ch2="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=CopyOrder_ACC&pa1="+sb20+"&pa2="+sb3+"&pa3="+sb1+"&ACC="+account_list;
                }
            }
        }
    }

    public void WebTokenCA(Gmail gmail, String userId, String account_list) throws IOException, Exception {
        List<Message> messages = gmail.users().messages().list(userId).setQ("From:seller-notification@amazon.ca " +
                "subject:(\"Your payment is on its way\") newer_than:6d").execute().getMessages();
        if (messages != null) {
            System.out.println("messages:" + messages);
            for (Message message : messages) {
                String messageid = message.getId();  //  Refund on order
                Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                Map<String, String> header = gmailHelper.getHeader(msg);
                if (!header.isEmpty()) {
                    String sb1, sb2, sb3, sb = null, ss = null;
                    int hang = 0;

                    Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                    ss = header.get("Subject");

                    Session session = Session.getDefaultInstance(new Properties());
                    MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                    sb = gmailHelper.readContent(mimeMessage);

                    System.out.println("sb:" + sb);

                    sb1=StringUtils.substringBetween(sb,"(",")");
                    sb2=StringUtils.substringBetween(sb," of "," on ");
                    sb3=StringUtils.substringBetween(sb," on ",".");
                    System.out.println(sb3);
                    sb3=sb3.trim();
                    System.out.println(ss);
                    System.out.println(account_list);
                    System.out.println(sb1);
                    System.out.println(sb2);

                    String sb20=sb2.replace("CDN$","");
                    double sb22=Double.valueOf(sb20).doubleValue();
                    double sb4=sb22* Constants.getCADrateDouble();
                    double sb5=0.1*sb4;

                    BigDecimal b1=new BigDecimal(sb4);
                    BigDecimal b2=new BigDecimal(sb5);
                    sb4=b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    sb5=b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

                    sb2= URLEncoder.encode(sb2, "UTF8");

                    String ch3="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=Total&pa1="+sb2+"&pa2="+sb3+"&pa3="+sb1+"&pa4="+sb4+"&pa5="+sb5+"&ACC="+account_list;
                    System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get().text());
                    String ch2="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=CopyOrder_ACC&pa1="+sb20+"&pa2="+sb3+"&pa3="+sb1+"&ACC="+account_list;
                }
            }
        }
    }

    public void WebTokenMX(Gmail gmail, String userId, String account_list) throws IOException, Exception {
        List<Message> messages = gmail.users().messages().list(userId).setQ("From:informacion-vendedor@amazon.com.mx " +
                "subject:(\"Tu pago de Amazon.com.mx está en camino\") newer_than:6d").execute().getMessages();
        if (messages != null) {
            System.out.println("messages:" + messages);
            for (Message message : messages) {
                String messageid = message.getId();  //  Refund on order
                Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                Map<String, String> header = gmailHelper.getHeader(msg);
                if (!header.isEmpty()) {
                    String sb1, sb2, sb3, sb = null, ss = null;
                    int hang = 0;

                    Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                    ss = header.get("Subject");

                    Session session = Session.getDefaultInstance(new Properties());
                    MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                    sb = gmailHelper.readContent(mimeMessage);

                    System.out.println("sb:" + sb);

                    sb1=StringUtils.substringBetween(sb,"terminada ",")");
                    sb2=StringUtils.substringBetween(sb,"de "," el");
                    sb3=StringUtils.substringBetween(sb,"día ",".");
                    sb3=sb3.trim();
                    System.out.println(ss);
                    System.out.println(account_list);
                    System.out.println(sb1);
                    System.out.println(sb2);
                    System.out.println(sb3);

                    String []sb33=sb3.split("/");
                    System.out.println(sb33[0]);
                    System.out.println(sb33[1]);
                    System.out.println(sb33[2]);

                    String sb20=sb2.replace("MXN$","");
                    double sb22=Double.valueOf(sb20).doubleValue();
                    double sb4=sb22* Constants.getMXNrateDouble();
                    double sb5=0.1*sb4;

                    BigDecimal b1=new BigDecimal(sb4);
                    BigDecimal b2=new BigDecimal(sb5);
                    sb4=b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    sb5=b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

                    sb2= URLEncoder.encode(sb2, "UTF8");

                    String ch3="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=Total&pa1="+sb2+"&pa2="+sb3+"&pa3="+sb1+"&pa4="+sb4+"&pa5="+sb5+"&ACC="+account_list;
                    System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get().text());
                    String ch2="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=CopyOrder_ACC&pa1="+sb20+"&pa2="+sb3+"&pa3="+sb1+"&ACC="+account_list;
                }
            }
        }
    }

    public void WebTokenJP(Gmail gmail, String userId, String account_list) throws IOException, Exception {
        List<Message> messages = gmail.users().messages().list(userId).setQ("From:seller-notification@amazon.co.jp  " +
                "subject:(\"振り込み手続き開始のお知らせ\") newer_than:6d").execute().getMessages();
        if (messages != null) {
            System.out.println("messages:" + messages);
            for (Message message : messages) {
                String messageid = message.getId();  //  Refund on order
                Message msg = gmail.users().messages().get(userId, messageid).setFormat("metadata").execute();
                Map<String, String> header = gmailHelper.getHeader(msg);
                if (!header.isEmpty()) {
                    String sb1, sb2, sb3, sb = null, ss = null;
                    int hang = 0;

                    Message body = gmail.users().messages().get(userId, messageid).setFormat("raw").execute();
                    ss = header.get("Subject");

                    Session session = Session.getDefaultInstance(new Properties());
                    MimeMessage mimeMessage = new MimeMessage(session, new ByteArrayInputStream(body.decodeRaw()));
                    sb = gmailHelper.readContent(mimeMessage);

                    System.out.println("sb:" + sb);

                    sb1=StringUtils.substringBetween(sb,"座（","）");
                    sb2=StringUtils.substringBetween(sb,"たる","円の");
                    sb3=StringUtils.substringBetween(sb,"様","付け");
                    sb3=sb3.trim();
                    sb3=sb3.replace("<br><br>", "");
                    System.out.println(sb);
                    System.out.println(account_list);

                    String []sb33=sb3.split("/");
                    System.out.println(sb33[0]);
                    System.out.println(sb33[1]);
                    System.out.println(sb33[2]);
                    String sb20=sb2;

                    sb20=sb20.replace(",","");
                    double sb22=Double.valueOf(sb20).doubleValue();
                    double sb4=sb22* Constants.getJPrateDouble();
                    double sb5=0.1*sb4;
                    System.out.println(sb20);

                    BigDecimal b1=new BigDecimal(sb4);
                    BigDecimal b2=new BigDecimal(sb5);
                    sb4=b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    sb5=b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

                    System.out.println("sb1="+sb1);
                    System.out.println("sb2="+sb2);
                    System.out.println("sb3="+sb3);
                    System.out.println("sb4="+sb4);
                    System.out.println("sb5="+sb5);

                    String ch3="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=Total&pa1="+sb2+"&pa2="+sb3+"&pa3="+sb1+"&pa4="+sb4+"&pa5="+sb5+"&ACC="+account_list;
                    System.out.println(Jsoup.connect(ch3).timeout(30000000).ignoreContentType(true).get().text());
                    String ch2="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=CopyOrder_ACC&pa1="+sb20+"&pa2="+sb3+"&pa3="+sb1+"&ACC="+account_list;
                }
            }
        }
    }



    public void singleValidate() throws IOException, GeneralSecurityException, Exception {

//        String gmail = "havvindisseawind@gmail.com";
//        String account ="00US";
        for(int i=2;i<=950;i++)
            getDisbursement(i);
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException, Exception {
        Disbursement security = ApplicationContext.getBean(Disbursement.class);
        security.singleValidate();
//        security.scanAccount();
    }

}
