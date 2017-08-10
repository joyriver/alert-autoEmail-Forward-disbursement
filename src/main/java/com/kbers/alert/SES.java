package com.kbers.alert;

import com.amazonaws.regions.Region;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import com.amzass.service.common.ApplicationContext;
import com.amzass.utils.common.Tools;
import com.google.inject.Inject;
import com.mailman.utils.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.amazonaws.regions.Regions.US_WEST_2;
import static com.amzass.utils.common.Constants.UTF8;


/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/7 11:41
 */
public class SES {
    @Inject
    private AmazonSimpleEmailServiceClient client;
    private static final Logger LOGGER = LoggerFactory.getLogger(SES.class);

    public void sendBySES(String account, String indicator, String email,String indicatorName, String SevenDaysData, boolean exceeding,boolean accountClosed){

        String content="";
        System.setProperty("aws.accessKeyId", "AKIAIT3GTVIIPDBSJ3GA");
        System.setProperty("aws.secretKey", "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");
        client = new AmazonSimpleEmailServiceClient();
        client.setRegion(Region.getRegion(US_WEST_2));

        String title="",html="";
        if(!indicatorName.contentEquals("Infringement"))
        indicator = Constants.ToPercent(indicator);

        String indicatorEmail = null;
        if(indicatorName.contentEquals("ODR(Short term)") || indicatorName.contentEquals("ODR(Long term)")) {
            indicatorEmail= Constants.odr_vtr_feedback_email;
            if (exceeding == true && accountClosed == false) {
                title = OdrEmailTemplate.OdrWarningTitle(account, indicator, indicatorName);
                html = OdrEmailTemplate.ODRWarningTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == false && accountClosed == false) {
                title = OdrEmailTemplate.OdrNoticeTitle(account, indicator, indicatorName);
                html = OdrEmailTemplate.ODRNoticeTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == true && accountClosed == true) {
                title = OdrEmailTemplate.OdrClosedTitle(account, indicator, indicatorName);
                html = OdrEmailTemplate.ODRClosedTemplate(account, indicator, indicatorName,SevenDaysData);
            }
        } else if(indicatorName.contentEquals("VTR(7 days)") || indicatorName.contentEquals("VTR(30 days)")){
            indicatorEmail= Constants.odr_vtr_feedback_email;
            if (exceeding == true && accountClosed == false) {
                title = VtrEmailTemplate.VtrWarningTitle(account, indicator, indicatorName);
                html = VtrEmailTemplate.VtrWarningTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == false && accountClosed == false) {
                title = VtrEmailTemplate.VtrNoticeTitle(account, indicator, indicatorName);
                html = VtrEmailTemplate.VtrNoticeTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == true && accountClosed == true) {
                title = VtrEmailTemplate.VtrClosedTitle(account, indicator, indicatorName);
                html = VtrEmailTemplate.VtrClosedTemplate(account, indicator, indicatorName,SevenDaysData);
            }
        } else if(indicatorName.contentEquals("Feedback(7 days)") || indicatorName.contentEquals("Feedback(30 days)")){
             indicatorEmail= Constants.odr_vtr_feedback_email;
            if (exceeding == true && accountClosed == false) {
                title = FeedbackEmailTemplate.FeedbackWarningTitle(account, indicator, indicatorName);
                System.out.println("alert2");
                html = FeedbackEmailTemplate.FeedbackWarningTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == false && accountClosed == false) {
                title = FeedbackEmailTemplate.FeedbackNoticeTitle(account, indicator, indicatorName);
                html = FeedbackEmailTemplate.FeedbackNoticeTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == true && accountClosed == true) {
                title = FeedbackEmailTemplate.FeedbackClosedTitle(account, indicator, indicatorName);
                html = FeedbackEmailTemplate.FeedbackClosedTemplate(account, indicator, indicatorName,SevenDaysData);
            }
        } else if(indicatorName.contentEquals("LateShipmentRate(7 days)") || indicatorName.contentEquals("LateShipmentRate(30 days)")){
//             indicatorEmail= Constants.lateshipment_email;
            if (exceeding == true && accountClosed == false) {
                title = LateShipmentEmailTemplate.LateShipmentWarningTitle(account, indicator, indicatorName);
                html = LateShipmentEmailTemplate.LateShipmentWarningTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == false && accountClosed == false) {
                title = LateShipmentEmailTemplate.LateShipmentNoticeTitle(account, indicator, indicatorName);
                html = LateShipmentEmailTemplate.LateShipmentNoticeTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == true && accountClosed == true) {
                title = LateShipmentEmailTemplate.LateShipmentClosedTitle(account, indicator, indicatorName);
                html = LateShipmentEmailTemplate.LateShipmentClosedTemplate(account, indicator, indicatorName,SevenDaysData);
            }
        } else if(indicatorName.contentEquals("RefundRate(7 days)") || indicatorName.contentEquals("RefundRate(30 days)")){
//             indicatorEmail = Constants.refundRate_email;
            if (exceeding == true && accountClosed == false) {
                title = RefundRateEmailTemplate.RefundRateWarningTitle(account, indicator, indicatorName);
                html = RefundRateEmailTemplate.RefundRateWarningTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == false && accountClosed == false) {
                title = RefundRateEmailTemplate.RefundRateNoticeTitle(account, indicator, indicatorName);
                html = RefundRateEmailTemplate.RefundRateNoticeTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == true && accountClosed == true) {
                title = RefundRateEmailTemplate.RefundRateClosedTitle(account, indicator, indicatorName);
                html = RefundRateEmailTemplate.RefundRateClosedTemplate(account, indicator, indicatorName,SevenDaysData);
            }
        } else if(indicatorName.contentEquals("preFulfillCancelRate(7 days)") || indicatorName.contentEquals("preFulfillCancelRate(30 days)")){
            if (exceeding == true && accountClosed == false) {
                title = PreFulfillCancelRateEmailTemplate.PreFulfillCancelRateWarningTitle(account, indicator, indicatorName);
                html = PreFulfillCancelRateEmailTemplate.PreFulfillCancelRateWarningTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == false && accountClosed == false) {
                title = PreFulfillCancelRateEmailTemplate.PreFulfillCancelRateNoticeTitle(account, indicator, indicatorName);
                html = PreFulfillCancelRateEmailTemplate.PreFulfillCancelRateNoticeTemplate(account, indicator, indicatorName,SevenDaysData);
            } else if (exceeding == true && accountClosed == true) {
                title = PreFulfillCancelRateEmailTemplate.PreFulfillCancelRateClosedTitle(account, indicator, indicatorName);
                html = PreFulfillCancelRateEmailTemplate.PreFulfillCancelRateClosedTemplate(account, indicator, indicatorName,SevenDaysData);
            }
        } else if(indicatorName.contentEquals("Infringement")){
            if(exceeding == true && accountClosed ==false){
                title = InfringementEmailTemplate.ActiveInfringementTitle(account,indicator);
                html = InfringementEmailTemplate.ActiveInfringementTemplate(account,SevenDaysData,indicator);
            } else if(exceeding == false && accountClosed == true){
                title = InfringementEmailTemplate.CloseInfringementTitle(account,indicator);
                html = InfringementEmailTemplate.CloseInfringementTemplate(account, SevenDaysData, indicator);
            }
        }


        long start = System.currentTimeMillis();
        Content subject = new Content().withData(title).withCharset(UTF8);
        Content textBody = new Content().withData(content).withCharset(UTF8);

        textBody.setData(html);

        //   Body body = contentType == EmailSender.EmailContentType.Html ? new Body().withHtml(textBody) : new Body().withText(textBody);
        Body body = new Body().withHtml(textBody);
        com.amazonaws.services.simpleemail.model.Message message = new com.amazonaws.services.simpleemail.model.Message().withSubject(subject).withBody(body);

        Alert alet = ApplicationContext.getBean(Alert.class);

//        String countryEmail = null;
//
//        if (alet.getCountry(account).contentEquals("US"))
//            countryEmail = Constants.Junyan_email ;
//        else if (alet.getCountry(account).contains("EU") || alet.getCountry(account).contains("UK") ||
//                alet.getCountry(account).contains("CA") || alet.getCountry(account).contains("JP") ||
//                alet.getCountry(account).contains("MX") || alet.getCountry(account).contains("IN"))
//            countryEmail = Constants.Dongyong_email ;

        List<String> teamEmail = null;

        if (alet.getTeam(account).contentEquals( "Dover")) {
            teamEmail = Constants.Dover_email;
        }
        else if (alet.getTeam(account).contentEquals("RS")) {
            teamEmail = Arrays.asList(Constants.RS_email);
        }

        String developerEmail = Constants.developer_email;

        System.out.println("email:"+email);
//        System.out.println("countryEmail:"+countryEmail);
        System.out.println("teamEmail:"+teamEmail);
        System.out.println("indicatorEmail:"+indicatorEmail);
        System.out.println("developerEmail:"+developerEmail);  //

        if(indicatorName.contentEquals("Infringement")) {
            Destination destination = new Destination().withToAddresses(email);  //.withBccAddresses(developerEmail);
            SendEmailRequest request = new SendEmailRequest().withSource(Constants.sesSenderInfringe).withDestination(destination).withMessage(message);
            SendEmailResult response = client.sendEmail(request);
            LOGGER.info("使用SES发送邮件给{}完成:{}, 耗时{}", ServiceUtils.abbrev(destination), response, Tools.formatCostTime(start));
        } else {
            Destination destination = new Destination().withToAddresses(email, Constants.security_email).withCcAddresses(teamEmail).withCcAddresses(indicatorEmail);  //.withBccAddresses(developerEmail);
            SendEmailRequest request = new SendEmailRequest().withSource(Constants.sesSenderAlert).withDestination(destination).withMessage(message);
            SendEmailResult response = client.sendEmail(request);
            LOGGER.info("使用SES发送邮件给{}完成:{}, 耗时{}", ServiceUtils.abbrev(destination), response, Tools.formatCostTime(start));
        }


    }


    public static void main(String[] args) {
        SES security = ApplicationContext.getBean(SES.class);
//        security.sendBySES();
    }
}
