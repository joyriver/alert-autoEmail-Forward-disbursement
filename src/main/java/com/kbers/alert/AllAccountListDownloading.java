package com.kbers.alert;

import com.alibaba.fastjson.JSON;
import com.amzass.aop.Repeat;
import com.amzass.service.common.ApplicationContext;
import com.amzass.service.sheet.AppScript;
import com.amzass.utils.common.Exceptions.BusinessException;
import com.amzass.utils.common.HttpUtils;
import com.amzass.utils.common.PageUtils;
import com.amzass.utils.common.Tools;
import com.google.inject.Inject;
import com.mailman.model.common.Sheets;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/4/22 16:57
 */
public class AllAccountListDownloading {

    @Inject
    private AppScript appScript;
//    private static final Logger LOGGER = LoggerFactory.getLogger(WatchdogMonitorDownloading.class);

    void loadAllCountList(){
        Sheets sheets = appScript.getSheets(Constants.spreadIdAllCountList);
        for (String sheetName : sheets.getSheetNames()) {
            if(sheetName.contentEquals("AccountInfo") || sheetName.contentEquals("riverside")
                    || sheetName.contentEquals("dover") ||sheetName.contentEquals("intel")){
                downloadAllAccountList(sheetName);
            }
        }
    }

    AllAccountLists infringementR = ApplicationContext.getBean(AllAccountLists.class);

    @Repeat(times = 8, sleepTime = 10)
    String[] downloadAllAccountList(String sheetName) {
        String url = String.format(Constants.ascript2ParameterAlert, Constants.methodAllAccountList, sheetName);
        String sidLinkSpreadId[] = new String[0];
        try {
            String range = PageUtils.processResult(HttpUtils.getTextThriceIfFail(url));
            String[][] sidAndIndicator = JSON.parseObject(range, String[][].class);
            System.out.println(sidAndIndicator);
            System.out.println("length:"+sidAndIndicator.length);
            sidLinkSpreadId = new String[sidAndIndicator.length];
            for (int i = 0; i  < sidAndIndicator.length; i++) {
                sidLinkSpreadId[i] = sidAndIndicator[i][0] + "|" + sidAndIndicator[i][1] + "|" + sidAndIndicator[i][2] + "|" + sidAndIndicator[i][3] + "|" + sidAndIndicator[i][4]
                        + "|" + sidAndIndicator[i][5] + "|" + sidAndIndicator[i][6] + "|" + sidAndIndicator[i][7] + "|" + sidAndIndicator[i][8] + "|" + sidAndIndicator[i][9]
                        + "|" + sidAndIndicator[i][10] + "|" + sidAndIndicator[i][11] + "|" + sidAndIndicator[i][12] + "|" + sidAndIndicator[i][13] + "|" + sidAndIndicator[i][14]
                        + "|" + sidAndIndicator[i][15] + "|" + sidAndIndicator[i][16];
                System.out.println(sidLinkSpreadId[i]+"\n");
                String a1 = sidAndIndicator[i][0];
                String a2 = sidAndIndicator[i][1];
                String a3 = sidAndIndicator[i][2];
                String a4 = sidAndIndicator[i][3];
                String a5 = sidAndIndicator[i][4];
                String a6 = sidAndIndicator[i][5];
                String a7 = sidAndIndicator[i][6];
                String a8 = sidAndIndicator[i][7];
                String a9 = sidAndIndicator[i][8];
                String a10 = sidAndIndicator[i][9];
                String a11 = sidAndIndicator[i][10];
                String a12 = sidAndIndicator[i][11];
                String a13 = sidAndIndicator[i][12];
                String a14 = sidAndIndicator[i][13];
                String a15 = sidAndIndicator[i][14];
                String a16 = sidAndIndicator[i][15];
                String a17 = sidAndIndicator[i][16];
//                if(!a1.isEmpty()) {
//                    if (a1.matches(Constants.INTEGER_REGX)) {
//                        int a1X = Integer.parseInt(a1);
                        infringementR.dbManager.save(new AllAccountLists.allCountList(Tools.generateUUID(), a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17), AllAccountLists.allCountList.class);
//                    }
//                }
            }
        } catch (BusinessException e) {
//            LOGGER.error("AppScript URL {}:", url, e);
        }
        return sidLinkSpreadId;
    }


    void clearAllAccountList() {
        infringementR.dbManager.clearAll(AllAccountLists.allCountList.class);
        System.out.print("Clear allCountList dataBase successfully!");
    }


    public static void main(String[] args) {
        AllAccountListDownloading security = ApplicationContext.getBean(AllAccountListDownloading.class);
//        security.clearInfringement();
//        security.clearInfringementVia();
        security.loadAllCountList();
    }

}
