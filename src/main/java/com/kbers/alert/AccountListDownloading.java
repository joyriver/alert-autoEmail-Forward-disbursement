package com.kbers.alert;

import com.amzass.aop.Repeat;
import com.amzass.service.common.ApplicationContext;
import com.amzass.utils.common.Exceptions.BusinessException;
import com.amzass.utils.common.HttpUtils;
import com.amzass.utils.common.PageUtils;
import com.amzass.utils.common.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/3/10 15:38
 */
public class AccountListDownloading {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountListDownloading.class);

    AllAccounts alcount = ApplicationContext.getBean(AllAccounts.class);

    @Repeat(times = 8, sleepTime = 10)
    public String[] downloadAccountList() {
        String sheetName= "Template";
        String sidLinkSpreadId[] = new String[0];
            String url = String.format(Constants.alertAscript2Parameter, Constants.methodAccount1, sheetName);
            try {
                String range = PageUtils.processResult(HttpUtils.getTextThriceIfFail(url));
                System.out.println(range);
                String sidAndIndicator[] = range.split(",");
                sidLinkSpreadId = new String[sidAndIndicator.length / 3];
                for (int i = 0; i < sidAndIndicator.length / 3; i++) {
                    String a1 = sidAndIndicator[3 * i];
                    String a2 = sidAndIndicator[3 * i + 1];
                    String a3 = sidAndIndicator[3 * i + 2];
                    alcount.dbManager.save(new AllAccounts.allCount(Tools.generateUUID(), a1, a2, a3
                    ), AllAccounts.allCount.class);
                }
            } catch (BusinessException e) {
                LOGGER.error("AppScript URL {}:", url, e);
            }

        return sidLinkSpreadId;
    }

    void clearAccountList() {
        alcount.dbManager.clearAll(AllAccounts.allCount.class);
        System.out.print("Clear allCount dataBase successfully!");

    }

    public static void main(String[] args) {
        AccountListDownloading security = ApplicationContext.getBean(AccountListDownloading.class);
        security.clearAccountList();
        security.downloadAccountList();
    }
}
