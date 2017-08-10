package com.kbers.alert;

import com.amzass.aop.Repeat;
import com.amzass.service.common.ApplicationContext;
import com.amzass.service.sheet.AppScript;
import com.amzass.utils.common.Exceptions.BusinessException;
import com.amzass.utils.common.HttpUtils;
import com.amzass.utils.common.PageUtils;
import com.amzass.utils.common.Tools;
import com.google.inject.Inject;
import com.mailman.model.common.Sheets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/5 9:31
 */
public class WatchdogMonitorDownloading {
    @Inject
    private AppScript appScript;

    private static final Logger LOGGER = LoggerFactory.getLogger(WatchdogMonitorDownloading.class);

    void loadAccountIndicators(){
        Sheets sheets = appScript.getSheets(Constants.spreadId);
        List<String> results = this.filter(sheets);
        for (String sheetName : results) {
            downloadAccountIndicators(sheetName);
        }
    }

    List<String> filter(Sheets sheets) {
        List<String> results = new ArrayList<>();
        for (String sheetName : sheets.getSheetNames()) {
            if (sheetName.matches(Constants.ORDER_UPDATE_SHEET_NAME_REGEX))
                results.add(sheetName);
        }
       return results;
    }

    Indicators alert = ApplicationContext.getBean(Indicators.class);

    @Repeat(times = 8, sleepTime = 10)
    String[] downloadAccountIndicators(String sheetName) {
//        String sheetName ="01/04";
        String url = String.format(Constants.ascript2ParameterAlert, Constants.methodAccount, sheetName);
        String sidLinkSpreadId[] = new String[0];
        try {
            String range = PageUtils.processResult(HttpUtils.getTextThriceIfFail(url));
            System.out.println(range);
            String sidAndIndicator[] = range.split(",");
            sidLinkSpreadId = new String[sidAndIndicator.length / 24];
            for (int i = 0; i  < sidAndIndicator.length/24; i++) {
                sidLinkSpreadId[i] = sidAndIndicator[24 * i] + "|" + sidAndIndicator[24 * i + 1]+ "|" + sidAndIndicator[24 * i + 2]+ "|" + sidAndIndicator[24 * i + 3]+ "|" + sidAndIndicator[24 * i + 4]
                        + "|" + sidAndIndicator[24 * i + 5]+ "|" + sidAndIndicator[24 * i + 6]+ "|" + sidAndIndicator[24 * i + 7]+ "|" + sidAndIndicator[24 * i + 8]+ "|" + sidAndIndicator[24 * i + 9]
                        + "|" + sidAndIndicator[24 * i + 10]+ "|" + sidAndIndicator[24 * i + 11]+ "|" + sidAndIndicator[24 * i + 12]+ "|" + sidAndIndicator[24 * i + 13]+ "|" + sidAndIndicator[24 * i + 14]
                        + "|" + sidAndIndicator[24 * i + 15]+ "|" + sidAndIndicator[24 * i + 16]+ "|" + sidAndIndicator[24 * i + 17]+ "|" + sidAndIndicator[24 * i + 18]+ "|" + sidAndIndicator[24 * i + 19]
                        + "|" + sidAndIndicator[24 * i + 20]+ "|" + sidAndIndicator[24 * i + 21]+ "|" + sidAndIndicator[24 * i + 22]+ "|" + sidAndIndicator[24 * i + 23];
                if (sheetName.matches(Constants.ORDER_UPDATE_SHEET_NAME_REGEX)) {
                    String a1 = sidAndIndicator[24 * i];
                    String a2 = sidAndIndicator[24 * i + 1];
                    String a3 = sidAndIndicator[24 * i + 2];
                    String a4 = sidAndIndicator[24 * i + 3];
                    String a5 = sidAndIndicator[24 * i + 4];
                    String a6 = sidAndIndicator[24 * i + 5];
                    String a7 = sidAndIndicator[24 * i + 6];
                    String a8 = sidAndIndicator[24 * i + 7];
                    String a9 = sidAndIndicator[24 * i + 8];
                    String a10 = sidAndIndicator[24 * i + 9];
                    String a11 = sidAndIndicator[24 * i + 10];
                    String a12 = sidAndIndicator[24 * i + 11];
                    String a13 = sidAndIndicator[24 * i + 12];
                    String a14 = sidAndIndicator[24 * i + 13];
                    String a15 = sidAndIndicator[24 * i + 14];
                    String a16 = sidAndIndicator[24 * i + 15];
                    String a17 = sidAndIndicator[24 * i + 16];
                    String a18 = sidAndIndicator[24 * i + 17];
                    String a19 = sidAndIndicator[24 * i + 18];
                    String a20 = sidAndIndicator[24 * i + 19];
                    String a21 = sidAndIndicator[24 * i + 20];
                    String a22 = sidAndIndicator[24 * i + 21];
                    String a23 = sidAndIndicator[24 * i + 22];
                    String a24 = sidAndIndicator[24 * i + 23];

                    alert.dbManager.save(new Indicators.AlertRecord(Tools.generateUUID(),sheetName,a1,a2, a3, a4, a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24
                           ), Indicators.AlertRecord.class);
                }
            }
        } catch (BusinessException e) {
            LOGGER.error("AppScript URL {}:", url, e);
        }
        return sidLinkSpreadId;
    }

    void clearIndicators() {
        alert.dbManager.clearAll(Indicators.AlertRecord.class);
        System.out.print("Clear AlertRecord dataBase successfully!");

    }

    public static void main(String[] args) {
        WatchdogMonitorDownloading security = ApplicationContext.getBean(WatchdogMonitorDownloading.class);
        security.clearIndicators();
        security.loadAccountIndicators();
    }
}
