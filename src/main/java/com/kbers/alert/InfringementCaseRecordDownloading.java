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
import com.kbers.alert.Indicators.InfringementRecord;
import com.kbers.alert.Indicators.InfringementRecordVia;
import com.mailman.model.common.Sheets;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/30 14:02
 */
public class InfringementCaseRecordDownloading {
    @Inject
    private AppScript appScript;
    private static final Logger LOGGER = LoggerFactory.getLogger(WatchdogMonitorDownloading.class);

    void loadInfringement(){
        Sheets sheets = appScript.getSheets(Constants.spreadIdInfringement);
        for (String sheetName : sheets.getSheetNames()) {
        if(sheetName.contentEquals("Infringement Case Record")){
            downloadInfringement();
        }
        }
    }

    Indicators infringementR = ApplicationContext.getBean(Indicators.class);

    @Repeat(times = 8, sleepTime = 10)
    String[] downloadInfringement() {
        System.out.println("hello");
//        String sheetName ="01/04";
        String url = String.format(Constants.ascript1ParameterInfringement, Constants.methodInfringement);
        String sidLinkSpreadId[] = new String[0];
        try {
            String range = PageUtils.processResult(HttpUtils.getTextThriceIfFail(url));
            String[][] sidAndIndicator = JSON.parseObject(range, String[][].class);
            System.out.println(sidAndIndicator);
            System.out.println("length:"+sidAndIndicator.length);
            sidLinkSpreadId = new String[sidAndIndicator.length];
            for (int i = 0; i  < sidAndIndicator.length; i++) {
                sidLinkSpreadId[i] = sidAndIndicator[i][0] + "|" + sidAndIndicator[i][1]+ "|" + sidAndIndicator[i][2]+ "|" + sidAndIndicator[i][3]+ "|" + sidAndIndicator[i][4]
                        + "|" + sidAndIndicator[i][5]+ "|" + sidAndIndicator[i][6]+ "|" + sidAndIndicator[i][7]+ "|" + sidAndIndicator[i][8]+ "|" + sidAndIndicator[i][9]
                        + "|" + sidAndIndicator[i][10]+ "|" + sidAndIndicator[i][11]+ "|" + sidAndIndicator[i][12]+ "|" + sidAndIndicator[i][13]+ "|" + sidAndIndicator[i][14]
                        + "|" + sidAndIndicator[i][15]+ "|" + sidAndIndicator[i][16]+ "|" + sidAndIndicator[i][17]+ "|" + sidAndIndicator[i][18]+ "|" + sidAndIndicator[i][19]
                        + "|" + sidAndIndicator[i][20]+ "|" + sidAndIndicator[i][21];
//                System.out.println(sidLinkSpreadId[i]+"\n");
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
                    String a18 = sidAndIndicator[i][17];
                    String a19 = sidAndIndicator[i][18];
                    String a20 = sidAndIndicator[i][19];
                    String a21 = sidAndIndicator[i][20];
                    String a22 = sidAndIndicator[i][21];
//                    String a23 = sidAndIndicator[22 * i + 22];
//                    String a24 = sidAndIndicator[22 * i + 23];

                if(!a1.isEmpty()) {

                    a2 = a2.replace("EB", "00");


                    if(a1.matches(Constants.INTEGER_REGX)) {
                        int a1X = Integer.parseInt(a1);

                        a2 = a2.toUpperCase();

                        infringementR.dbManager.save(new Indicators.InfringementRecord(Tools.generateUUID(), a1X, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22
                        ), Indicators.InfringementRecord.class);

                        System.out.println("a1:" + a1);
                        System.out.println("a2:" + a2);
                        System.out.println("a1X:" + a1);
                        if (!a2.isEmpty()) {
                            Condition cndInfringementX = Cnd.where("Number", "like", a1X).and("ACC", "like", a2);
                            List<InfringementRecordVia> InfringementRecordListX = infringementR.dbManager.query(InfringementRecordVia.class, cndInfringementX);

                            System.out.println("InfringementRecordListX :" + InfringementRecordListX);
                            if (InfringementRecordListX.size() == 0) {
                                InfringementRecordViaDownloading security = ApplicationContext.getBean(InfringementRecordViaDownloading.class);
                                security.UpdateInfringementRecordVia(Tools.generateUUID(), a1X, a2, 0);
                            }
                        }
                    }
                }
            }
        } catch (BusinessException e) {
            LOGGER.error("AppScript URL {}:", url, e);
        }
        return sidLinkSpreadId;
    }


    void clearInfringement() {
        infringementR.dbManager.clearAll(Indicators.InfringementRecord.class);
        System.out.print("Clear InfringementRecord dataBase successfully!");

    }

    void clearInfringementVia(){
        infringementR.dbManager.clearAll(Indicators.InfringementRecordVia.class);
        System.out.println("Clear InfringementRecordVia dataBase successfully!");
    }

    public static void main(String[] args) {
        InfringementCaseRecordDownloading security = ApplicationContext.getBean(InfringementCaseRecordDownloading.class);
 //       security.clearInfringement();
//        security.clearInfringementVia();
        security. downloadInfringement();
    }
}
