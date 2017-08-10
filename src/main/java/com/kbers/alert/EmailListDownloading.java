package com.kbers.alert;

import com.amzass.aop.Repeat;
import com.amzass.service.common.ApplicationContext;
import com.amzass.service.sheet.AppScript;
import com.amzass.utils.common.Exceptions.BusinessException;
import com.amzass.utils.common.HttpUtils;
import com.amzass.utils.common.PageUtils;
import com.amzass.utils.common.Tools;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/7 10:28
 */
public class EmailListDownloading
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailListDownloading.class);

    Indicators alert = ApplicationContext.getBean(Indicators.class);

    @Repeat(times = 8, sleepTime = 10)
    String[] downloadEmailList() {
        String sheetNames[]={"US","EU_CA_JP_MX_IN","Dover","RS"};
        String sidLinkSpreadId[] = new String[0];
        for(String sheetName:sheetNames) {
            String url = String.format(Constants.emailAscript2Parameter, Constants.methodEmail, sheetName);
            try {
                String range = PageUtils.processResult(HttpUtils.getTextThriceIfFail(url));
                System.out.println(range);
                String sidAndIndicator[] = range.split(",");
                sidLinkSpreadId = new String[sidAndIndicator.length / 3];
                for (int i = 0; i < sidAndIndicator.length / 3; i++) {
                    String a1 = sidAndIndicator[3 * i];
                    String a2 = sidAndIndicator[3 * i + 1];
                    String a3 = sidAndIndicator[3 * i + 2];
                    String a4 = sheetName;
                    alert.dbManager.save(new Indicators.EmailRecord(Tools.generateUUID(), a1, a2, a3,a4
                    ), Indicators.EmailRecord.class);
                }
            } catch (BusinessException e) {
                LOGGER.error("AppScript URL {}:", url, e);
            }
        }
        return sidLinkSpreadId;
    }

    void clearEmail() {
        alert.dbManager.clearAll(Indicators.EmailRecord.class);
        System.out.print("Clear Email dataBase successfully!");

    }

    public static void main(String[] args) {
        EmailListDownloading EmailList = ApplicationContext.getBean(EmailListDownloading.class);
        EmailList.clearEmail();
        EmailList.downloadEmailList();
    }
}
