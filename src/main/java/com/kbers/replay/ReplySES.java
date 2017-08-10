package com.kbers.replay;

import com.amazonaws.regions.Region;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import com.amzass.utils.common.Tools;
import com.google.inject.Inject;
import com.kbers.alert.Constants;
import com.kbers.alert.SES;
import com.mailman.utils.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.amazonaws.regions.Regions.US_WEST_2;
import static com.amzass.utils.common.Constants.UTF8;

/**
 * <a href="mailto:nathanael4ever@gmail.com">Nathanael Yang</a> 6/28/2017 4:25 PM
 */
public class ReplySES {
    @Inject
    private AmazonSimpleEmailServiceClient client;
    private static final Logger LOGGER = LoggerFactory.getLogger(SES.class);

    public void sendBySES(Body body, String title) {

        String content = "";
        System.setProperty("aws.accessKeyId", "AKIAIT3GTVIIPDBSJ3GA");
        System.setProperty("aws.secretKey", "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");
        client = new AmazonSimpleEmailServiceClient();
        client.setRegion(Region.getRegion(US_WEST_2));
        long start = System.currentTimeMillis();

        Content subject = new Content().withData(title).withCharset(UTF8);
//        Content textBody = new Content().withData(html).withCharset(UTF8);

//        textBody.setData(html);

        //   Body body = contentType == EmailSender.EmailContentType.Html ? new Body().withHtml(textBody) : new Body().withText(textBody);
//        Body body = new Body().withHtml(textBody);
        String sesSenderInfringe = "ebsafetyipro12@gmail.com";
        String email = "tongzhouyuni@gmail.com";
        String developerEmail = "jeromeinjesus@gmail.com";
        com.amazonaws.services.simpleemail.model.Message message = new com.amazonaws.services.simpleemail.model.Message().withSubject(subject).withBody(body);
        Destination destination = new Destination().withToAddresses(sesSenderInfringe).withBccAddresses(developerEmail);
        SendEmailRequest request = new SendEmailRequest().withSource(sesSenderInfringe).withDestination(destination).withMessage(message);
        SendEmailResult response = client.sendEmail(request);
        LOGGER.info("使用SES发送邮件给{}完成:{}, 耗时{}", ServiceUtils.abbrev(destination), response, Tools.formatCostTime(start));
    }
}
