package com.kbers.alert;

import com.amazonaws.services.simpleemail.model.Destination;
import com.amzass.aop.Repeat;
import com.amzass.database.DBManager;
import com.amzass.enums.common.DateFormat;
import com.amzass.model.submit.OrderEnums;
import com.amzass.service.common.ApplicationContext;
import com.amzass.service.common.EmailSender;
import com.amzass.utils.common.HttpUtils;
import com.amzass.utils.common.PageUtils;
import com.amzass.utils.common.Tools;
import com.google.inject.Inject;
import com.kbers.alert.Indicators.AlertRecord;
import com.kbers.alert.Indicators.InfringementRecord;
import com.mailman.utils.ServiceConstants;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/17 17:19
 */
public class Summary {
    @Inject
    DBManager dbManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(Summary.class);

    void getMonthData() {
        Exceeding exceeding = ApplicationContext.getBean(Exceeding.class);
       //  Constants.date()
        for(int i= 0 ;i< 6;i++)
        {
            getEverydayData(exceeding, i);
        }
    }

    public void getEverydayData(Exceeding exceeding, int i) {
        String currentDay =  Constants.day(i);
        Condition cnd = Cnd.where("date", "like", currentDay);
        List<AlertRecord> alertRecordList = dbManager.query(AlertRecord.class, cnd);
        System.out.println("alertRecordList.size():" + alertRecordList.size());
        if (alertRecordList.size() >= 1) {  //  alertRecordList.size()
            for (int size = 0; size < alertRecordList.size(); size++) {
                System.out.println("size: " + size);
                AlertRecord atRecord = alertRecordList.get(size);
                String account = atRecord.ACC;
                String odrShort = atRecord.odrShort;
                String status = atRecord.status;

                String validTrackingRate7days = atRecord.validTrackingRate7days;
                String feedback30days = atRecord.feedback30days;
                String orders30days = atRecord.orders30days;
                String lateShipmentRate7days = atRecord.lateShipmentRate7days;
                String refundRate7days = atRecord.refundRate7days;
                String preFulfillCancelRate7days = atRecord.preFulfillCancelRate7days;

                String odrLong = atRecord.odrLong;
                String validTrackingRate30days = atRecord.validTrackingRate30days;
                String feedback7days = atRecord.feedback;
                String orders7days = atRecord.orders7day;
                String lateShipmentRate30days = atRecord.lateShipmentRate30days;
                String refundRate30days = atRecord.refundRate30days;
                String preFulfillCancelRate30days = atRecord.preFulfillCancelRate30days;
               //  ODR|01|18UK|13|86
                String[] date = currentDay.split("/");
                String month = date[0];
                String day = date[1];


                    if (account.matches(Constants.ACCOUNT_REGX)) {

                        odr(exceeding, account, odrShort, status, odrLong, month, day);

                        validTrackingRate(exceeding, account, status, validTrackingRate7days,
                                validTrackingRate30days, month, day);

                        feedback(exceeding, account, status, feedback30days, orders30days, feedback7days,
                                orders7days, month, day);

                        lateShipmentRate(exceeding, account, status, lateShipmentRate7days, lateShipmentRate30days,
                                month, day);

                        refundRate(exceeding, account, status, refundRate7days, refundRate30days, month, day);

                        preFulfillCancelRateExceeding(exceeding, account, status, preFulfillCancelRate7days,
                                preFulfillCancelRate30days, month, day);

                        infringement(account,status,month,day);
                    }
                }
            }
    }

    private void infringement(String account,String status,String month,String day){
        Condition cndInfringement = Cnd.where("ACC", "like", account).and("ComplaintWithdrawn", "like", "N");
        List<Indicators.InfringementRecord> InfringementRecordList = dbManager.query(Indicators.InfringementRecord.class, cndInfringement);
        System.out.println("InfringementRecordList.size():" + InfringementRecordList.size());
        int infringe = InfringementRecordList.size();
        String infringeString = String.valueOf(infringe);
        int InfringeIndicator = 4;
        if(infringe >7)
            InfringeIndicator = 0;
        else if(infringe <=7 && infringe >= 5)
            InfringeIndicator = 2;
        else if(infringe < 5 && infringe >= 0)
            InfringeIndicator =3;
        else if(status.contentEquals("Login Blocked")){
            infringeString = "";
            InfringeIndicator = 1;
        }
        String data = "Infringement|"+ month + "|" + account + "|" + day + "|" + infringeString + "|" + status + "|" + InfringeIndicator;
//       //  //  SummaryOutput((data,Constants.methodMonitor );
    }

    private void odr(Exceeding exceeding, String account, String odrShort, String status, String odrLong, String
            month, String day) {
        String data;
        if(!odrShort.contentEquals("") && exceeding.SummaryOdrNormal(odrShort)){
            data = "ODR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(odrShort) + "|" + status + "|" + 4;
            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
        } else if(!odrLong.contentEquals("") &&
                !exceeding.OdrNotice(odrLong) &&
                !exceeding.OdrNotice(odrShort) &&
                !exceeding.SummaryOdrExceeding(odrLong) &&
                !exceeding.SummaryOdrExceeding(odrShort) &&
                !odrShort.contentEquals("") && !exceeding.SummaryOdrNormal(odrShort) && exceeding.SummaryOdrNormal(odrLong)) {
            data = "ODR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(odrLong) + "|" + status + "|" + 5;
            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
        }

        if(!odrShort.contentEquals("") && exceeding.OdrNotice(odrShort)){
            data = "ODR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(odrShort) + "|" + status + "|" + 2;
            System.out.println("data:" + data);
           //  //  SummaryOutput((data,Constants.methodMonitor );
        } else if(!odrLong.contentEquals("") && !odrShort.contentEquals("") && !exceeding.OdrNotice(odrShort) && exceeding.OdrNotice(odrLong)){
            data = "ODR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(odrLong) + "|" + status + "|" + 2;
            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
        }

        if (!odrShort.contentEquals("") && exceeding.SummaryOdrExceeding(odrShort)) {
            data = "ODR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(odrShort) + "|" + status + "|" + 0;
            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
        } else if (!odrLong.contentEquals("") && !odrShort.contentEquals("") && !exceeding.SummaryOdrExceeding(odrShort) && exceeding.SummaryOdrExceeding(odrLong)) {
            data = "ODR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(odrLong) + "|" + status + "|" + 0;
            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
        }

//        if(status.contentEquals("")){
//            data = "ODR|" + month + "|" + account + "|" + day + "|" + "" + "|" + status + "|" + 1;
//            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
//        }

        if(odrShort.contentEquals("-") || odrLong.contentEquals("-") || odrShort.contentEquals("") || odrLong.contentEquals("")){
            data = "ODR|" + month + "|" + account + "|" + day + "|" + "-" + "|" + status + "|" + 3;
            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
        }
    }

    private void validTrackingRate(Exceeding exceeding, String account, String status, String validTrackingRate7days,
                                   String validTrackingRate30days, String month, String day) {
        String data;Alert alet = ApplicationContext.getBean(Alert.class);
        if (alet.getCountry(account).contentEquals("US") && !validTrackingRate30days.contentEquals("") &&
                !validTrackingRate7days.contentEquals("") &&
                validTrackingRate30days.matches(Constants.DOUBLE_REGEX)
                && validTrackingRate7days.matches(Constants.DOUBLE_REGEX)) {
            if (Double.parseDouble(validTrackingRate30days) <= Double.parseDouble(validTrackingRate7days)) {
                if (exceeding.VTRNotice(validTrackingRate30days)) {

                    data = "VTR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(validTrackingRate30days) + "|" + status + "|" + 2;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                } else if (exceeding.SummaryVTRExceeding(validTrackingRate30days)) {
                    data = "VTR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(validTrackingRate30days) + "|" + status + "|" + 0;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                } else if (exceeding.SummaryVTRNormal(validTrackingRate30days)) {
                    data = "VTR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(validTrackingRate30days) + "|" + status + "|" + 4;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                }
            } else {
                if (exceeding.VTRNotice(validTrackingRate7days)) {
                    data = "VTR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(validTrackingRate7days) + "|" + status + "|" + 2;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                } else if (exceeding.SummaryVTRExceeding(validTrackingRate7days)) {
                    data = "VTR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(validTrackingRate7days) + "|" + status + "|" + 0;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                } else if (exceeding.SummaryVTRNormal(validTrackingRate7days)) {
                    data = "VTR|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(validTrackingRate7days) + "|" + status + "|" + 5;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                }
            }
        }
        if(validTrackingRate30days.contentEquals("-") || validTrackingRate7days.contentEquals("-") || validTrackingRate30days.contentEquals("") || validTrackingRate7days.contentEquals("")){
            data = "VTR|" + month + "|" + account + "|" + day + "|" + "-" + "|" + status + "|" + 3;
            System.out.println("data:" + data);
           //  //  SummaryOutput((data,Constants.methodMonitor );
        }
//        if(status.contentEquals("")){
//            data = "VTR|" + month + "|" + account + "|" + day + "|" + "" + "|" + status + "|" + 1;
//            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
//        }
    }

    private void feedback(Exceeding exceeding, String account, String status, String feedback30days, String
            orders30days, String feedback7days, String orders7days, String month, String day) {
        String data;
        if(!feedback30days.contentEquals("") && !orders30days.contentEquals("") &&
                feedback30days.matches(Constants.INTEGER_REGX) &&
                orders30days.matches(Constants.INTEGER_REGX) &&
                feedback7days.matches(Constants.INTEGER_REGX) &&
                orders7days.matches(Constants.INTEGER_REGX) &&
                !feedback7days.contentEquals("") && !orders7days.contentEquals("")){
            double DoubleFeedback30days = Integer.parseInt(feedback30days);
            double DoubleOrders30days = Integer.parseInt(orders30days);
            double DoubleFeedbackRate30days = DoubleFeedback30days / DoubleOrders30days;

            double DoubleFeedback7days = Integer.parseInt(feedback7days);
            double DoubleOrders7days = Integer.parseInt(orders7days);
            double DoubleFeedbackRate7days = DoubleFeedback7days / DoubleOrders7days;

            if(DoubleFeedbackRate30days <= DoubleFeedbackRate7days){
                if( exceeding.SummaryFeedbackNormal(feedback30days, orders30days)) {
                    if (DoubleOrders30days != 0) {
                        data = "Feedback|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(String.valueOf(DoubleFeedbackRate30days)) + "|" + status + "|" + 4;
                        System.out.println("data:" + data);
//                       //  //  SummaryOutput((data,Constants.methodMonitor );
                    }
                } else if(exceeding.FeedbackNotice(feedback30days, orders30days)) {

                    if (DoubleOrders30days != 0) {
                        data = "Feedback|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(String.valueOf(DoubleFeedbackRate30days)) + "|" + status + "|" + 2;
                        System.out.println("data:" + data);
//                       //  //  SummaryOutput((data,Constants.methodMonitor );
                    }
                } else if (exceeding.SummaryFeedbackExceeding(feedback30days, orders30days)) {

                    if (DoubleOrders30days != 0) {
                        data = "Feedback|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(String.valueOf(DoubleFeedbackRate30days)) + "|" + status + "|" + 0;
                        System.out.println("data:" + data);
//                       //  //  SummaryOutput((data,Constants.methodMonitor );
                    }
                }

            } else {
                if (exceeding.SummaryFeedbackNormal(feedback7days, orders7days)) {
                    if (DoubleOrders7days != 0) {
                        data = "Feedback|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(String.valueOf(DoubleFeedbackRate7days)) + "|" + status + "|" + 5;
                        System.out.println("data:" + data);
//                       //  //  SummaryOutput((data,Constants.methodMonitor );
                    }
                } else if(exceeding.FeedbackNotice(feedback7days,orders7days)) {
                    if (DoubleOrders7days != 0) {
//                        data = "Feedback|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(String.valueOf(DoubleFeedbackRate7days)) + "|" + status + "|" + 2;
//                        System.out.println("data:" + data);
//                       //  //  SummaryOutput((data,Constants.methodMonitor );
                        //  SummaryOutput(3into2("Feedback",account, status, String.valueOf(DoubleFeedbackRate7days), month, day);

                    }
                } else if (exceeding.SummaryFeedbackExceeding(feedback7days, orders7days)) {
                    if (DoubleOrders7days != 0) {
//                        data = "Feedback|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(String.valueOf(DoubleFeedbackRate7days)) + "|" + status + "|" + 0;
//                        System.out.println("data:" + data);
//                       //  //  SummaryOutput((data,Constants.methodMonitor );
                        //  SummaryOutput(1into0("Feedback",account, status, String.valueOf(DoubleFeedbackRate7days), month, day);

                    }
                }
            }
        }
        if(orders7days.contentEquals("0") || orders30days.contentEquals("0")||orders7days.contentEquals("") || orders30days.contentEquals("")){
            data = "Feedback|" + month + "|" + account + "|" + day + "|" + "-" + "|" + status + "|" + 3;
            System.out.println("data:" + data);
           //  //  SummaryOutput((data,Constants.methodMonitor );
        }
//        if(status.contentEquals("")){
//            data = "Feedback|" + month + "|" + account + "|" + day + "|" + "" + "|" + status + "|" + 1;
//            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
//        }
    }

    private void lateShipmentRate(Exceeding exceeding, String account, String status, String lateShipmentRate7days,
                                  String lateShipmentRate30days, String month, String day) {
        String data;
        if(!lateShipmentRate30days.contentEquals("") &&
                !lateShipmentRate7days.contentEquals("") &&
                lateShipmentRate30days.matches(Constants.DOUBLE_REGEX) &&
                lateShipmentRate7days.matches(Constants.DOUBLE_REGEX)) {
            if (Double.parseDouble(lateShipmentRate30days) >= Double.parseDouble(lateShipmentRate7days)) {
                if (exceeding.SummaryLateShipmentRateNormal(lateShipmentRate30days)) {
                    data = "LateShip|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(lateShipmentRate30days) + "|" + status + "|" + 4;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );

                } else if (exceeding.LateShipmentRateNotice(lateShipmentRate30days)) {
                    data = "LateShip|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(lateShipmentRate30days) + "|" + status + "|" + 2;

                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                } else if (exceeding.SummaryLateShipmentRateExceeding(lateShipmentRate30days)) {
                    data = "LateShip|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(lateShipmentRate30days) + "|" + status + "|" + 0;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                }
            } else {
                if (exceeding.SummaryLateShipmentRateNormal(lateShipmentRate7days)) {
                    data = "LateShip|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(lateShipmentRate7days) + "|" + status + "|" + 5;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                } else if (exceeding.LateShipmentRateNotice(lateShipmentRate7days)) {
                    //  SummaryOutput(3into2("LateShip", account, status, lateShipmentRate7days, month, day);
                } else if (exceeding.SummaryLateShipmentRateExceeding(lateShipmentRate7days)) {
                    //  SummaryOutput(1into0("LateShip", account, status, lateShipmentRate7days, month, day);
                }
            }
        }

        if(lateShipmentRate30days.contentEquals("-") || lateShipmentRate7days.contentEquals("-") || lateShipmentRate30days.contentEquals("") || lateShipmentRate7days.contentEquals("")){
            data = "LateShip|" + month + "|" + account + "|" + day + "|" + "-" + "|" + status + "|" + 3;
            System.out.println("data:" + data);
           //  //  SummaryOutput((data,Constants.methodMonitor );
        }
//        if(status.contentEquals("")){
//            data = "LateShip|" + month + "|" + account + "|" + day + "|" + "" + "|" + status + "|" + 1;
//            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
//        }
    }

    private void preFulfillCancelRateExceeding(Exceeding exceeding, String account, String status, String
            preFulfillCancelRate7days, String preFulfillCancelRate30days, String month, String day) {
        String data;
        if(!preFulfillCancelRate30days.contentEquals("") && !preFulfillCancelRate7days.contentEquals("") &&
                preFulfillCancelRate30days.matches(Constants.DOUBLE_REGEX) &&
                preFulfillCancelRate7days.matches(Constants.DOUBLE_REGEX)
        ){
            if(Double.parseDouble(preFulfillCancelRate30days) >= Double.parseDouble(preFulfillCancelRate7days)){
                if (exceeding.SummaryPreFulfilledCancelRateNormal(preFulfillCancelRate30days)) {
                    data = "PreFulfillCancelRate|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(preFulfillCancelRate30days) + "|" + status + "|" + 4;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                } else  if(exceeding.PreFulfillCancelRateNotice(preFulfillCancelRate30days)){
                    data = "PreFulfillCancelRate|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(preFulfillCancelRate30days) + "|" + status + "|" + 2;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                } else if (exceeding.SummaryPreFulfillCancelRateExceeding(preFulfillCancelRate30days)) {
                    data = "PreFulfillCancelRate|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(preFulfillCancelRate30days) + "|" + status + "|" + 0;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                }
            } else {
                if (exceeding.SummaryPreFulfilledCancelRateNormal(preFulfillCancelRate7days)) {
                    data = "PreFulfillCancelRate|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(preFulfillCancelRate7days) + "|" + status + "|" + 5;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                } else  if(exceeding.PreFulfillCancelRateNotice(preFulfillCancelRate7days)){
                    //  SummaryOutput(3into2("PreFulfillCancelRate",account, status, preFulfillCancelRate7days, month, day);
                } else if (exceeding.SummaryPreFulfillCancelRateExceeding(preFulfillCancelRate7days)) {
                    //  SummaryOutput(1into0("PreFulfillCancelRate",account, status, preFulfillCancelRate7days, month, day);
                }
            }
        }
//        if(status.contentEquals("")){
//            data = "PreFulfillCancelRate|" + month + "|" + account + "|" + day + "|" + "" + "|" + status + "|" + 1;
//            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
//        }
        if(preFulfillCancelRate30days.contentEquals("-") || preFulfillCancelRate7days.contentEquals("-") || preFulfillCancelRate30days.contentEquals("") || preFulfillCancelRate7days.contentEquals("")){
            data = "PreFulfillCancelRate|" + month + "|" + account + "|" + day + "|" + "-" + "|" + status + "|" + 3;
            System.out.println("data:" + data);
           //  //  SummaryOutput((data,Constants.methodMonitor );
        }
    }

    public void refundRate(Exceeding exceeding, String account, String status, String refundRate7days, String
            refundRate30days, String month, String day) {
        String data;
        if(!refundRate30days.contentEquals("") && !refundRate7days.contentEquals("") &&
                refundRate30days.matches(Constants.DOUBLE_REGEX) &&
                refundRate7days.matches(Constants.DOUBLE_REGEX)) {
            System.out.println("Double.parseDouble(refundRate30days):" + Double.parseDouble(refundRate30days));
            System.out.println("Double.parseDouble(refundRate7days):" + Double.parseDouble(refundRate7days));
            if (Double.parseDouble(refundRate30days) >= Double.parseDouble(refundRate7days)) {
                if (exceeding.SummaryRefundRateNormal(refundRate30days)) {
                    data = "RefundRate|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(refundRate30days) + "|" + status + "|" + 4;
                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                } else if (exceeding.RefundRateNotice(refundRate30days)) {
                    //  SummaryOutput(3into2("RefundRate",account, status, refundRate30days, month, day);
                } else if (exceeding.SummaryRefundRateExceeding(refundRate30days)) {
                    //  SummaryOutput(1into0("RefundRate",account, status, refundRate30days, month, day);
                }

            } else {
                if (exceeding.SummaryRefundRateNormal(refundRate7days)) {
                    data = "RefundRate|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(refundRate7days) + "|" + status + "|" + 5;

                    System.out.println("data:" + data);
                   //  //  SummaryOutput((data,Constants.methodMonitor );
                } else if (exceeding.RefundRateNotice(refundRate7days)) {
                    //  SummaryOutput(3into2("RefundRate",account, status, refundRate7days, month, day);
                } else if (exceeding.SummaryRefundRateExceeding(refundRate7days)) {
                    //  SummaryOutput(1into0("RefundRate",account, status, refundRate7days, month, day);
                }
            }
        }
//        if(status.contentEquals("")){
//            data = "RefundRate|" + month + "|" + account + "|" + day + "|" + "" + "|" + status + "|" + 1;
//            System.out.println("data:" + data);
//           //  //  SummaryOutput((data,Constants.methodMonitor );
//        }
        if(refundRate30days.contentEquals("-") || refundRate7days.contentEquals("-") || refundRate30days.contentEquals("") || refundRate7days.contentEquals("")){
            data = "RefundRate|" + month + "|" + account + "|" + day + "|" + "-" + "|" + status + "|" + 3;
            System.out.println("data:" + data);
           //  //  SummaryOutput((data,Constants.methodMonitor );
        }
    }

//    private void   SummaryOutput(into0(String indicator, String account, String status, String refundRate7days, String month, String day) {
//        String data;
//        data = indicator+"|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(refundRate7days.trim()) + "|" +
//    status + "|" + 0;
//        System.out.println("data:" + data);
//       //  //  SummaryOutput((data,Constants.methodMonitor );
//    }
//
//    private void   SummaryOutput(3into2(String indicator, String account, String status, String refundRate7days, String month, String day) {
//        String data;
//        data = indicator+"|" + month + "|" + account + "|" + day + "|" + Constants.ToPercent(refundRate7days.trim()) + "|" +
//    status + "|" + 2;
//        System.out.println("data:" + data);
//       //  //  SummaryOutput((data,Constants.methodMonitor );
//    }

//    public void sort(){
//        String data;
//        String indicatorNameArray[] = { "ODR","VTR","Feedback","LateShip","PreFulfillCancelRate","RefundRate","Infringement"};
//        String month = Constants.thisMonth();
//        Boolean sortBy;
//
//        for(int i=0; i < indicatorNameArray.length; i++) {
//            String indicatorName = indicatorNameArray[i];
//            if(indicatorName.contentEquals("VTR") || indicatorName.contentEquals("Feedback"))
//                sortBy = true;
//            else
//                sortBy = false;
//            String formula = "=sort(" + indicatorName + "_Table_" + month + "!A:AI, 34, "+sortBy+")";
//            data = indicatorName + "|" + month + "|" + formula;
//            System.out.println("data:" + data);
//            SummaryOutput((data, Constants.methodSortMonitor);
//        }
//    }
//
//    private Map<String, Long> notifyTimestamps = new HashMap<>();
//
//    @Repeat(times = 8, sleepTime = 10)
//    boolean   SummaryOutput((String data,String method) {
//        String url = String.format(Constants.ascript2ParameterSummary, method, PageUtils.encodeParamValue(data));
//
//        long start = System.currentTimeMillis();
//        String result;
//
//        result = PageUtils.processResult(HttpUtils.getTextThriceIfFail(url));
//        if (OrderEnums.ReturnCode.Success.name().equals(result)) {
////                notifyTimestamps.remove("WarehouseMonitor");
//            LOGGER.info("成功将搜索重复指标数据{}返回{}, 耗时{}", data, "AccountMonitor", Tools.formatCostTime(start));
//            return true;
//        }
////        System.out.println(today());
//        LOGGER.warn("将搜索重复指标数据{}返回{}失败: {}. 尝试重复操作.", data, "AccountMonitor", result);
//
//        if (Tools.contains(result, "above the limit of 2000000 cells")) {
//            // 不重复发送
//            Long timestamp = notifyTimestamps.get("AccountMonitor");
//            System.out.print(System.currentTimeMillis() + "\n");
//            System.out.print("timestamp:" + timestamp + "\n");
//            System.out.print("toMillis:" + TimeUnit.HOURS.toMillis(3) + "\n");
//            if (timestamp != null && (System.currentTimeMillis() - timestamp < TimeUnit.HOURS.toMillis(3))) {
//                LOGGER.info("{}对应表格清理通知邮件已在{}发送过了, 短期内无需重复发送", "AccountMonitor", DateFormat.DATE_TIME.format
//                        (timestamp));
//                return true;
//            }
//
//            // 数据总量超过上限, 需及时清理对应表格, 发送通知邮件  ServiceConstants.MAIL_MAN_RND_EMAIL
//            try {
//                String subject = String.format("Spreadsheet of %s needs cleanse and backup ASAP!!", "AccountMonitor");
//                String content = subject + StringUtils.LF + StringUtils.defaultString(result);
//                ApplicationContext.getBean(EmailSender.class).sendGmail(subject, content, EmailSender
//                                .EmailContentType.PlainText,
//                        ServiceConstants.MAIL_MAN, "Cleanse Notifier", new Destination().withToAddresses
//                                ("joyriver7@gmail.com"));
//
//                notifyTimestamps.put("AccountMonitor", System.currentTimeMillis());
//            } catch (Exception e) {
//                // -> Ignore
//            }
//            return true;
//        }
//        return false;
//    }




//    public static void main(String[] args) {
//        Summary sum = ApplicationContext.getBean(Summary.class);
//        sum.getMonthData();
////        sum.sort();
////        String data ="Feedback|02|00DE|03|1.64%|Active|0";
////        sum.//  SummaryOutput((data,Constants.methodMonitor );
////        sum.getEverydayData();
//    }

}
