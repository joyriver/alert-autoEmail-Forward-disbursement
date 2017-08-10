package com.kbers.alert;

import com.amzass.database.DBManager;
import com.amzass.service.common.ApplicationContext;
import com.google.inject.Inject;
import com.kbers.alert.Indicators.InfringementRecord;
import com.kbers.alert.Indicators.InfringementRecordVia;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.util.cri.SqlExpressionGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/5 17:29
 */
public class Alert {
    @Inject
    DBManager dbManager;

    void getTodayData(){
        Exceeding exceeding = ApplicationContext.getBean(Exceeding.class);
        Condition cnd =  Cnd.where("date", "like", Constants.today());
        List<Indicators.AlertRecord> alertRecordList = dbManager.query(Indicators.AlertRecord.class, cnd);
        System.out.println("alertRecordList.size():" + alertRecordList.size());
        if (alertRecordList.size() >= 1) {
            for (int size = 0; size < alertRecordList.size(); size++) {
                System.out.println("size: " + size);
                Indicators.AlertRecord atRecord = alertRecordList.get(size);
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
                String preFulfillCancelRate30days =atRecord.preFulfillCancelRate30days;

                if (account.matches(Constants.ACCOUNT_REGX)) {
                    String email =  getEmail(account);
                    if(!email.contentEquals("")) {
                        System.out.println("\n" + "email:" + email + "\n");
                        SES ses = ApplicationContext.getBean(SES.class);

                        odr(exceeding, account, odrShort, status, odrLong, email, ses);

                        vtr(exceeding, account, status, validTrackingRate7days, validTrackingRate30days, email, ses);

                        feedback(exceeding, account, status, feedback30days, orders30days, feedback7days,
                                orders7days, email, ses);

                        lateShipmentRate(exceeding, account, status, lateShipmentRate7days, lateShipmentRate30days,
                                email, ses);

//                        refundRate(exceeding, account, status, refundRate7days, refundRate30days, email, ses);

                        preFulfillCancelRate(exceeding, account, status, preFulfillCancelRate7days,
                                preFulfillCancelRate30days, email, ses);

                    }

                }
            }
        }
    }

    void getTodayInfringement(){
        Condition cnd =  Cnd.where("date", "like", Constants.today());
        List<Indicators.AlertRecord> alertRecordList = dbManager.query(Indicators.AlertRecord.class, cnd);
        System.out.println("alertRecordList.size():" + alertRecordList.size());
        if (alertRecordList.size() >= 1) {   //  alertRecordList.size()
            for (int size = 0; size < alertRecordList.size(); size++) {
                System.out.println("size: " + size);
                Indicators.AlertRecord atRecord = alertRecordList.get(size);
                String account = atRecord.ACC;
                String status = atRecord.status;
                if (account.matches(Constants.ACCOUNT_REGX)) {
                    String email = getEmail(account);
                    if (!email.contentEquals("")) {
                        System.out.println("\n" + "email:" + email + "\n");
                        SES ses = ApplicationContext.getBean(SES.class);
                        infringementStates(account, status, email, ses);
                    }
                }
            }
        }
    }

    private void infringementStates(String account, String status, String email, SES ses) {
        account = account.toUpperCase();

        Condition cndInfringementX = Cnd.where("ACC", "like", account).and("count","like",0).limit(10).asc("Number");
        List<InfringementRecordVia> InfringementRecordListX = dbManager.query(InfringementRecordVia.class, cndInfringementX);
        System.out.println("InfringementRecordListX :"+InfringementRecordListX);

        Condition cndInfringementZ = Cnd.where("ACC", "like", account).and("count","like",1);
        List<InfringementRecordVia> InfringementRecordListZ = dbManager.query(InfringementRecordVia.class, cndInfringementZ);
        System.out.println("InfringementRecordListZ :"+InfringementRecordListZ);

        int [] number = new int[10];
        Condition cndInfringement = null;

        if(InfringementRecordListX.size() == 0 && InfringementRecordListZ.size()!=0 ){
            for(int i=0; i< InfringementRecordListZ.size(); i++){
                int numberX = InfringementRecordListZ.get(i).Number;
                System.out.println("numberX: "+ numberX);
                unmarkInfringeVia(numberX, account);
            }
        }

        if(InfringementRecordListX.size() == 10 ) {
            number[0] = InfringementRecordListX.get(0).Number;
            number[1] = InfringementRecordListX.get(1).Number;
            number[2] = InfringementRecordListX.get(2).Number;
            number[3] = InfringementRecordListX.get(3).Number;
            number[4] = InfringementRecordListX.get(4).Number;
            number[5] = InfringementRecordListX.get(5).Number;
            number[6] = InfringementRecordListX.get(6).Number;
            number[7] = InfringementRecordListX.get(7).Number;
            number[8] = InfringementRecordListX.get(8).Number;
            number[9] = InfringementRecordListX.get(9).Number;

            SqlExpressionGroup group1 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[0]);
            SqlExpressionGroup group2 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[1]);
            SqlExpressionGroup group3 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[2]);
            SqlExpressionGroup group4 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[3]);
            SqlExpressionGroup group5 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[4]);
            SqlExpressionGroup group6 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[5]);
            SqlExpressionGroup group7 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[6]);
            SqlExpressionGroup group8 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[7]);
            SqlExpressionGroup group9 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[8]);
            SqlExpressionGroup group10 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[9]);

            cndInfringement = Cnd.where(group1).or(group2).or(group3).or(group4).or(group5).or(group6).or(group7).or(group8).or(group9).or(group10).limit(10).asc("IDI");
                    //("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and(or("IDI","like",number[0]).(or("IDI","like",number[0]))).and("IDI","like",number[0]).or("IDI","like",number[1]).or("IDI","like",number[2]).or("IDI","like",number[3]).or("IDI","like",number[4]).or("IDI","like",number[5]).or("IDI","like",number[6]).or("IDI","like",number[7]).or("IDI","like",number[8]).or("IDI","like",number[9]).limit(10).asc("IDI");
            centerProcess(account, status, email, ses, number, cndInfringement);
        } else if(InfringementRecordListX.size() == 9) {
            number[0] = InfringementRecordListX.get(0).Number;
            number[1] = InfringementRecordListX.get(1).Number;
            number[2] = InfringementRecordListX.get(2).Number;
            number[3] = InfringementRecordListX.get(3).Number;
            number[4] = InfringementRecordListX.get(4).Number;
            number[5] = InfringementRecordListX.get(5).Number;
            number[6] = InfringementRecordListX.get(6).Number;
            number[7] = InfringementRecordListX.get(7).Number;
            number[8] = InfringementRecordListX.get(8).Number;
            number[9] = 0;

            SqlExpressionGroup group1 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[0]);
            SqlExpressionGroup group2 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[1]);
            SqlExpressionGroup group3 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[2]);
            SqlExpressionGroup group4 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[3]);
            SqlExpressionGroup group5 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[4]);
            SqlExpressionGroup group6 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[5]);
            SqlExpressionGroup group7 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[6]);
            SqlExpressionGroup group8 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[7]);
            SqlExpressionGroup group9 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[8]);

//            cndInfringement = Cnd.where("IDI","like",number[0]).or("IDI","like",number[1]).or("IDI","like",number[2]).or("IDI","like",number[3]).or("IDI","like",number[4]).or("IDI","like",number[5]).or("IDI","like",number[6]).or("IDI","like",number[7]).or("IDI","like",number[8]).and("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").limit(9).asc("IDI");
            cndInfringement = Cnd.where(group1).or(group2).or(group3).or(group4).or(group5).or(group6).or(group7).or(group8).or(group9).limit(9).asc("IDI");
            centerProcess(account, status, email, ses, number, cndInfringement);
        } else if(InfringementRecordListX.size() == 8) {
            number[0] = InfringementRecordListX.get(0).Number;
            number[1] = InfringementRecordListX.get(1).Number;
            number[2] = InfringementRecordListX.get(2).Number;
            number[3] = InfringementRecordListX.get(3).Number;
            number[4] = InfringementRecordListX.get(4).Number;
            number[5] = InfringementRecordListX.get(5).Number;
            number[6] = InfringementRecordListX.get(6).Number;
            number[7] = InfringementRecordListX.get(7).Number;

            number[8] = 0;
            number[9] = 0;

            SqlExpressionGroup group1 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[0]);
            SqlExpressionGroup group2 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[1]);
            SqlExpressionGroup group3 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[2]);
            SqlExpressionGroup group4 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[3]);
            SqlExpressionGroup group5 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[4]);
            SqlExpressionGroup group6 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[5]);
            SqlExpressionGroup group7 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[6]);
            SqlExpressionGroup group8 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[7]);

//            cndInfringement = Cnd.where("IDI","like",number[0]).or("IDI","like",number[1]).or("IDI","like",number[2]).or("IDI","like",number[3]).or("IDI","like",number[4]).or("IDI","like",number[5]).or("IDI","like",number[6]).or("IDI","like",number[7]).and("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").limit(8).asc("IDI");
            cndInfringement = Cnd.where(group1).or(group2).or(group3).or(group4).or(group5).or(group6).or(group7).or(group8).limit(8).asc("IDI");
            centerProcess(account, status, email, ses, number, cndInfringement);
        } else if(InfringementRecordListX.size() == 7) {
            number[0] = InfringementRecordListX.get(0).Number;
            number[1] = InfringementRecordListX.get(1).Number;
            number[2] = InfringementRecordListX.get(2).Number;
            number[3] = InfringementRecordListX.get(3).Number;
            number[4] = InfringementRecordListX.get(4).Number;
            number[5] = InfringementRecordListX.get(5).Number;
            number[6] = InfringementRecordListX.get(6).Number;

            number[7] = 0;
            number[8] = 0;
            number[9] = 0;

            SqlExpressionGroup group1 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[0]);
            SqlExpressionGroup group2 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[1]);
            SqlExpressionGroup group3 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[2]);
            SqlExpressionGroup group4 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[3]);
            SqlExpressionGroup group5 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[4]);
            SqlExpressionGroup group6 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[5]);
            SqlExpressionGroup group7 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[6]);

//            cndInfringement = Cnd.where("IDI","like",number[0]).or("IDI","like",number[1]).or("IDI","like",number[3]).or("IDI","like",number[4]).or("IDI","like",number[5]).or("IDI","like",number[6]).or("IDI","like",number[2]).and("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").limit(7).asc("IDI");
            cndInfringement = Cnd.where(group1).or(group2).or(group3).or(group4).or(group5).or(group6).or(group7).limit(7).asc("IDI");
            centerProcess(account, status, email, ses, number, cndInfringement);
        } else if(InfringementRecordListX.size() == 6) {
            number[0] = InfringementRecordListX.get(0).Number;
            number[1] = InfringementRecordListX.get(1).Number;
            number[2] = InfringementRecordListX.get(2).Number;
            number[3] = InfringementRecordListX.get(3).Number;
            number[4] = InfringementRecordListX.get(4).Number;
            number[5] = InfringementRecordListX.get(5).Number;

            number[6] = 0;
            number[7] = 0;
            number[8] = 0;
            number[9] = 0;

            SqlExpressionGroup group1 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[0]);
            SqlExpressionGroup group2 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[1]);
            SqlExpressionGroup group3 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[2]);
            SqlExpressionGroup group4 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[3]);
            SqlExpressionGroup group5 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[4]);
            SqlExpressionGroup group6 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[5]);

//            cndInfringement = Cnd.where("IDI","like",number[0]).or("IDI","like",number[1]).or("IDI","like",number[2]).or("IDI","like",number[3]).or("IDI","like",number[4]).or("IDI","like",number[5]).and("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").limit(6).asc("IDI");
            cndInfringement = Cnd.where(group1).or(group2).or(group3).or(group4).or(group5).or(group6).limit(6).asc("IDI");
            centerProcess(account, status, email, ses, number, cndInfringement);
        } else if(InfringementRecordListX.size() == 5) {
            number[0] = InfringementRecordListX.get(0).Number;
            number[1] = InfringementRecordListX.get(1).Number;
            number[2] = InfringementRecordListX.get(2).Number;
            number[3] = InfringementRecordListX.get(3).Number;
            number[4] = InfringementRecordListX.get(4).Number;

            number[5] = 0;
            number[6] = 0;
            number[7] = 0;
            number[8] = 0;
            number[9] = 0;

            SqlExpressionGroup group1 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[0]);
            SqlExpressionGroup group2 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[1]);
            SqlExpressionGroup group3 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[2]);
            SqlExpressionGroup group4 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[3]);
            SqlExpressionGroup group5 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[4]);

//            cndInfringement = Cnd.where("IDI","like",number[0]).or("IDI","like",number[1]).or("IDI","like",number[2]).or("IDI","like",number[3]).or("IDI","like",number[4]).and("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").limit(5).asc("IDI");
            cndInfringement = Cnd.where(group1).or(group2).or(group3).or(group4).or(group5).limit(5).asc("IDI");
            centerProcess(account, status, email, ses, number, cndInfringement);
        } else if(InfringementRecordListX.size() == 4) {
            number[0] = InfringementRecordListX.get(0).Number;
            number[1] = InfringementRecordListX.get(1).Number;
            number[2] = InfringementRecordListX.get(2).Number;
            number[3] = InfringementRecordListX.get(3).Number;

            number[4] = 0;
            number[5] = 0;
            number[6] = 0;
            number[7] = 0;
            number[8] = 0;
            number[9] = 0;

            SqlExpressionGroup group1 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[0]);
            SqlExpressionGroup group2 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[1]);
            SqlExpressionGroup group3 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[2]);
            SqlExpressionGroup group4 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[3]);

//            cndInfringement = Cnd.where("IDI","like",number[0]).or("IDI","like",number[1]).or("IDI","like",number[2]).or("IDI","like",number[3]).and("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").limit(4).asc("IDI");
            cndInfringement = Cnd.where(group1).or(group2).or(group3).or(group4).limit(4).asc("IDI");
            centerProcess(account, status, email, ses, number, cndInfringement);
        } else if(InfringementRecordListX.size() == 3) {
            number[0] = InfringementRecordListX.get(0).Number;
            number[1] = InfringementRecordListX.get(1).Number;
            number[2] = InfringementRecordListX.get(2).Number;

            number[3] = 0;
            number[4] = 0;
            number[5] = 0;
            number[6] = 0;
            number[7] = 0;
            number[8] = 0;
            number[9] = 0;

            SqlExpressionGroup group1 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[0]);
            SqlExpressionGroup group2 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[1]);
            SqlExpressionGroup group3 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[2]);

//            cndInfringement = Cnd.where("IDI","like",number[0]).or("IDI","like",number[1]).or("IDI","like",number[2]).and("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").limit(3).asc("IDI");
            cndInfringement = Cnd.where(group1).or(group2).or(group3).limit(3).asc("IDI");
            centerProcess(account, status, email, ses, number, cndInfringement);
        } else if(InfringementRecordListX.size() == 2) {
            number[0] = InfringementRecordListX.get(0).Number;
            number[1] = InfringementRecordListX.get(1).Number;

            number[2] = 0;
            number[3] = 0;
            number[4] = 0;
            number[5] = 0;
            number[6] = 0;
            number[7] = 0;
            number[8] = 0;
            number[9] = 0;

            SqlExpressionGroup group1 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[0]);
            SqlExpressionGroup group2 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[1]);

//            cndInfringement = Cnd.where("IDI","like",number[0]).or("IDI","like",number[1]).and("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").limit(2).asc("IDI");
            cndInfringement = Cnd.where(group1).or(group2).limit(2).asc("IDI");
            centerProcess(account, status, email, ses, number, cndInfringement);
        } else if(InfringementRecordListX.size() == 1) {
            number[0] = InfringementRecordListX.get(0).Number;

            number[1] = 0;
            number[2] = 0;
            number[3] = 0;
            number[4] = 0;
            number[5] = 0;
            number[6] = 0;
            number[7] = 0;
            number[8] = 0;
            number[9] = 0;

            SqlExpressionGroup group1 = Cnd.exps("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").and("IDI","like",number[0]);

//            cndInfringement = Cnd.where("IDI","like",number[0]).and("ACC", "like", account).and("ComplaintWithdrawn", "like", "N").limit(1).asc("IDI");
            cndInfringement = Cnd.where(group1).limit(1).asc("IDI");
            centerProcess(account, status, email, ses, number, cndInfringement);
        }

            System.out.println("number[0]" + number[0]);
            System.out.println("number[1]" + number[1]);
            System.out.println("number[2]" + number[2]);
            System.out.println("number[3]" + number[3]);
            System.out.println("number[4]" + number[4]);


    }

    private void centerProcess(String account, String status, String email, SES ses, int[] number, Condition cndInfringement) {
        List<InfringementRecord> InfringementRecordList = dbManager.query(InfringementRecord.class, cndInfringement );
        System.out.println("InfringementRecordList.size():" + InfringementRecordList.size());

//                        List<String> list = new ArrayList<>(InfringementRecordList.size());
//                        list.add(InfriRecord.IDI + " " + InfriRecord.ACC + " " + InfriRecord.ReceiveDate + " " + InfriRecord.OwnerEmail);
//                        result += InfriRecord.IDI + "&nbsp;&nbsp;&nbsp;&nbsp;" + InfriRecord.ACC + "&nbsp;&nbsp;&nbsp;&nbsp;" + InfriRecord.ReceiveDate + "&nbsp;&nbsp;&nbsp;&nbsp;<font color='#FFD700'>" + InfriRecord.OwnerEmail+"</font></em><br>\n";
//                        String result = StringUtils.join(list, StringUtils.LF);

        String result = "";
        String color = "";
        String backup = null;
        String firstEmail = null;

        for (InfringementRecord InfriRecord : InfringementRecordList) {
            backup = InfriRecord.Backup;
            firstEmail = InfriRecord.FirstEmail;
            System.out.println("account:" + account + ",InfriRecord.OwnerEmail:" + InfriRecord.OwnerEmail);
            String OwnerEmailY = InfriRecord.OwnerEmail;
            OwnerEmailY = OwnerEmailY.replace("-", "");
            if (!OwnerEmailY.isEmpty()) {
                Condition cndInfringementY = Cnd.where("ComplaintWithdrawn", "like", "Y").and("OwnerEmail", "like", OwnerEmailY);
                List<InfringementRecord> InfringementRecordListY = dbManager.query(InfringementRecord.class, cndInfringementY);
                if (InfringementRecordListY.size() > 0)
                    color = "&nbsp;&nbsp;&nbsp;&nbsp;<font style=\"background:yellow\">";
                else
                    color = "&nbsp;&nbsp;&nbsp;&nbsp;";
            }
            result += InfriRecord.IDI + "&nbsp;&nbsp;&nbsp;&nbsp;" + InfriRecord.ACC + "&nbsp;&nbsp;&nbsp;&nbsp;" + InfriRecord.ReceiveDate + color + InfriRecord.OwnerEmail + "</font></em><br>\n";
        }
        System.out.println("result:" + result);
        int infringe = InfringementRecordList.size();
        String infringeString = String.valueOf(infringe);
        System.out.println("infringe:" + infringe);
        System.out.println("status:" + status);

        if(!(backup.contains("要钱") || backup.contains("不撤诉") || firstEmail.contentEquals("wait other account‘s reply")))
        {
            if (infringe >= 1) {//&& (status.contentEquals("Active")))  || status.contentEquals("Under Review") || status.contentEquals("Restricted"))) {

                System.out.println("result 1 is ok");
                ses.sendBySES(account, infringeString, email, "Infringement", result, true, false);
                markInfringeVia(number, account);
            }
        }
    }

    public void test(){
        Condition cndInfringement = Cnd.where("IDI", "like", 5171);
        List<InfringementRecord> InfringementRecordList = dbManager.query(InfringementRecord.class, cndInfringement);
        String backup = InfringementRecordList.get(0).Backup;
        String firstEmail = InfringementRecordList.get(0).FirstEmail;
        if(!(backup.contains("要钱") || backup.contains("不撤诉") || firstEmail.contentEquals("wait other account‘s reply")))
        System.out.println("backup:"+backup+"\n"+"firstEmail:"+firstEmail+"\n");
    }

    private void unmarkInfringeVia(int number, String account){
        Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:db/orderman.db");
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                statement.executeUpdate("UPDATE InfringementVia SET count = '0' WHERE Number='" + number+"' AND ACC='"+ account+"'");
                System.out.println("mark Infringe"+ number +","+ account +" successful!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    private void markInfringeVia(int number [], String account) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:db/orderman.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            System.out.println("number[0]:" + number[0]);
            System.out.println("number[1]:" + number[1]);
            System.out.println("number[2]:" + number[2]);
            System.out.println("number[3]:" + number[3]);
            System.out.println("number[4]:" + number[4]);

            statement.executeUpdate("UPDATE InfringementVia SET count = '1' WHERE Number='" + number[0]+"' AND ACC='"+ account+"'");
            statement.executeUpdate("UPDATE InfringementVia SET count = '1' WHERE Number='" + number[1]+"' AND ACC='"+ account+"'");
            statement.executeUpdate("UPDATE InfringementVia SET count = '1' WHERE Number='" + number[2]+"' AND ACC='"+ account+"'");
            statement.executeUpdate("UPDATE InfringementVia SET count = '1' WHERE Number='" + number[3]+"' AND ACC='"+ account+"'");
            statement.executeUpdate("UPDATE InfringementVia SET count = '1' WHERE Number='" + number[4]+"' AND ACC='"+ account+"'");
            statement.executeUpdate("UPDATE InfringementVia SET count = '1' WHERE Number='" + number[5]+"' AND ACC='"+ account+"'");
            statement.executeUpdate("UPDATE InfringementVia SET count = '1' WHERE Number='" + number[6]+"' AND ACC='"+ account+"'");
            statement.executeUpdate("UPDATE InfringementVia SET count = '1' WHERE Number='" + number[7]+"' AND ACC='"+ account+"'");
            statement.executeUpdate("UPDATE InfringementVia SET count = '1' WHERE Number='" + number[8]+"' AND ACC='"+ account+"'");
            statement.executeUpdate("UPDATE InfringementVia SET count = '1' WHERE Number='" + number[9]+"' AND ACC='"+ account+"'");
            System.out.println("mark Infringe successful!");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    private void preFulfillCancelRate(Exceeding exceeding, String account, String status, String
            preFulfillCancelRate7days, String preFulfillCancelRate30days, String email, SES ses) {
        String SevenDaysData;
        if( preFulfillCancelRate30days.matches(Constants.DOUBLE_REGEX) &&
                preFulfillCancelRate7days.matches(Constants.DOUBLE_REGEX) &&
                Double.parseDouble(preFulfillCancelRate30days) >= Double.parseDouble(preFulfillCancelRate7days)){
            if (exceeding.PreFulfillCancelRateNotice(preFulfillCancelRate30days)) {
                SevenDaysData = getSevenDaysData( account, "preFulfillCancelRate30days");
                System.out.println("\n account:" + account + "\n preFulfillCancelRate30days:" + preFulfillCancelRate30days);
                ses.sendBySES(account, preFulfillCancelRate30days, email, "preFulfillCancelRate(30 days)", SevenDaysData, false, false);
            }
            else if (exceeding.SummaryPreFulfillCancelRateExceeding(preFulfillCancelRate30days)) {
                SevenDaysData = getSevenDaysData( account, "preFulfillCancelRate30days");
                System.out.println("\n account:" + account + "\n preFulfillCancelRate30days:" + preFulfillCancelRate30days);
                ses.sendBySES(account, preFulfillCancelRate30days, email, "preFulfillCancelRate(30 days)", SevenDaysData, true, false);
            }
//           else if (exceeding.AccountClosedPreFulfillCancelRateExceeding(preFulfillCancelRate30days,
//                    status)) {
//                SevenDaysData = getSevenDaysData( account, "preFulfillCancelRate30days");
//                System.out.println("\n account:" + account + "\n preFulfillCancelRate30days:" + preFulfillCancelRate30days);
//                ses.sendBySES(account, preFulfillCancelRate30days, email, "preFulfillCancelRate(30 days)", SevenDaysData, true, true);
//            }
        } else {
            if(exceeding.PreFulfillCancelRateNotice(preFulfillCancelRate7days)){
                SevenDaysData = getSevenDaysData( account, "preFulfillCancelRate7days");
                System.out.println("\n account:" + account + "\n preFulfillCancelRate7days:" + preFulfillCancelRate7days);
                ses.sendBySES(account, preFulfillCancelRate7days, email, "preFulfillCancelRate(7 days)", SevenDaysData, false, false);
            }
            else if(exceeding.SummaryPreFulfillCancelRateExceeding(preFulfillCancelRate7days)){
                SevenDaysData = getSevenDaysData( account, "preFulfillCancelRate7days");
                System.out.println("\n account:" + account + "\n preFulfillCancelRate7days:" + preFulfillCancelRate7days);
                ses.sendBySES(account, preFulfillCancelRate7days, email, "preFulfillCancelRate(7 days)", SevenDaysData, true, false);
            }
//            else if(exceeding.AccountClosedPreFulfillCancelRateExceeding(preFulfillCancelRate7days,
//                    status)){
//                SevenDaysData = getSevenDaysData( account, "preFulfillCancelRate7days");
//                System.out.println("\n account:" + account + "\n preFulfillCancelRate7days:" + preFulfillCancelRate7days);
//                ses.sendBySES(account, preFulfillCancelRate7days, email, "preFulfillCancelRate(7 days)", SevenDaysData, true, true);
//            }
        }
    }

    private void refundRate(Exceeding exceeding, String account, String status, String refundRate7days, String
            refundRate30days, String email, SES ses) {
        String SevenDaysData;
        if( refundRate30days.matches(Constants.DOUBLE_REGEX) &&
                refundRate7days.matches(Constants.DOUBLE_REGEX) &&
                Double.parseDouble(refundRate30days) >= Double.parseDouble(refundRate7days)){
            if (exceeding.RefundRateNotice(refundRate30days)) {
                SevenDaysData = getSevenDaysData( account, "refundRate30days");
                System.out.println("\n account:" + account + "\n refundRate30days:" + refundRate30days);
                ses.sendBySES(account, refundRate30days, email, "RefundRate(30 days)", SevenDaysData, false, false);
            } else if (exceeding.SummaryRefundRateExceeding(refundRate30days)) {
                SevenDaysData = getSevenDaysData( account, "refundRate30days");
                System.out.println("\n account:" + account + "\n refundRate30days:" + refundRate30days);
                ses.sendBySES(account, refundRate30days, email, "RefundRate(30 days)", SevenDaysData, true, false);
            }
//            else if (exceeding.AccountClosedRefundRateExceeding(refundRate30days, status)) {
//                SevenDaysData = getSevenDaysData( account, "refundRate30days");
//                System.out.println("\n account:" + account + "\n refundRate30days:" + refundRate30days);
//                ses.sendBySES(account, refundRate30days, email, "RefundRate(30 days)", SevenDaysData, true, true);
//            }
        } else {
            if(exceeding.RefundRateNotice(refundRate7days)){
                SevenDaysData = getSevenDaysData( account, "refundRate7days");
                System.out.println("\n account:" + account + "\n refundRate7days:" + refundRate7days);
                ses.sendBySES(account, refundRate7days, email, "RefundRate(7 days)", SevenDaysData, false, false);
            }  else if(exceeding.SummaryRefundRateExceeding(refundRate7days)){
                SevenDaysData = getSevenDaysData( account, "refundRate7days");
                System.out.println("\n account:" + account + "\n refundRate7days:" + refundRate7days);
                ses.sendBySES(account, refundRate7days, email, "RefundRate(7 days)", SevenDaysData, true, false);
            }
//            else if(exceeding.AccountClosedRefundRateExceeding(refundRate7days, status)){
//                SevenDaysData = getSevenDaysData( account, "refundRate7days");
//                System.out.println("\n account:" + account + "\n refundRate7days:" + refundRate7days);
//                ses.sendBySES(account, refundRate7days, email, "RefundRate(7 days)", SevenDaysData, true, true);
//            }
        }
    }

    private void lateShipmentRate(Exceeding exceeding, String account, String status, String lateShipmentRate7days,
                                  String lateShipmentRate30days, String email, SES ses) {
        String SevenDaysData;
        if( lateShipmentRate30days.matches(Constants.DOUBLE_REGEX) &&
                lateShipmentRate7days.matches(Constants.DOUBLE_REGEX) &&
        Double.parseDouble(lateShipmentRate30days) >= Double.parseDouble(lateShipmentRate7days)){
            if (exceeding.LateShipmentRateNotice(lateShipmentRate30days)) {
                SevenDaysData = getSevenDaysData( account, "lateShipmentRate30days");
                System.out.println("\n account:" + account + "\n lateShipmentRate30days:" + lateShipmentRate30days);
                ses.sendBySES(account, lateShipmentRate30days, email, "LateShipmentRate(30 days)", SevenDaysData, false, false);
            } else if (exceeding.SummaryLateShipmentRateExceeding(lateShipmentRate30days)) {
                SevenDaysData = getSevenDaysData( account, "lateShipmentRate30days");
                System.out.println("\n account:" + account + "\n lateShipmentRate30days:" +
                        lateShipmentRate30days);
                ses.sendBySES(account, lateShipmentRate30days, email, "LateShipmentRate(30 days)", SevenDaysData, true, false);
            }
//            else if (exceeding.AccountClosedLateShipmentRateExceeding(lateShipmentRate30days, status)) {
//                SevenDaysData = getSevenDaysData( account, "lateShipmentRate30days");
//                System.out.println("\n account:" + account + "\n lateShipmentRate30days:" + lateShipmentRate30days);
//                ses.sendBySES(account, lateShipmentRate30days, email, "LateShipmentRate(30 days)", SevenDaysData, true, true);
//            }
        } else {
            if(exceeding.LateShipmentRateNotice(lateShipmentRate7days)){
                SevenDaysData = getSevenDaysData( account, "lateShipmentRate7days");
                System.out.println("\n account:" + account + "\n lateShipmentRate7days:" + lateShipmentRate7days);
                ses.sendBySES(account, lateShipmentRate7days, email, "LateShipmentRate(7 days)", SevenDaysData, false, false);
            } else if(exceeding.SummaryLateShipmentRateExceeding(lateShipmentRate7days)){
                SevenDaysData = getSevenDaysData( account, "lateShipmentRate7days");
                System.out.println("\n account:" + account + "\n lateShipmentRate7days:" +
                        lateShipmentRate7days);
                ses.sendBySES(account, lateShipmentRate7days, email, "LateShipmentRate(7 days)", SevenDaysData, true, false);
            }
//            else if(exceeding.AccountClosedLateShipmentRateExceeding(lateShipmentRate7days, status)){
//                SevenDaysData = getSevenDaysData( account, "lateShipmentRate7days");
//                System.out.println("\n account:" + account + "\n lateShipmentRate7days:" + lateShipmentRate7days);
//                ses.sendBySES(account, lateShipmentRate7days, email, "LateShipmentRate(7 days)", SevenDaysData, true, true);
//            }
        }
    }

    private void feedback(Exceeding exceeding, String account, String status, String feedback30days, String
            orders30days, String feedback7days, String orders7days, String email, SES ses) {
        String SevenDaysData;
        if (feedback30days.matches(Constants.INTEGER_REGX) && orders30days.matches(Constants.INTEGER_REGX) &&
                feedback7days.matches(Constants.INTEGER_REGX) && orders7days.matches(Constants.INTEGER_REGX)) {
            double feedback = Integer.parseInt(feedback30days);
            double orders = Integer.parseInt(orders30days);
            double orders7 = Integer.parseInt(orders7days);
            if (orders != 0 && orders7 != 0) {
                double feedbackRate = feedback / orders;
                String feedbackRate30days = String.valueOf(feedbackRate);

                double doubleFeedbackRate7days = Double.parseDouble(feedback7days) / Double.parseDouble(orders7days);
                String feedbackRate7days = String.valueOf(doubleFeedbackRate7days);

                if (feedbackRate <= doubleFeedbackRate7days) {
                    if (exceeding.FeedbackNotice(feedback30days, orders30days)) {
                        SevenDaysData = getSevenDaysFeedbackData(account, "feedbackRate30days");
                        System.out.println("\n account:" + account + "\n feedback30days:" + feedback30days);
                        ses.sendBySES(account, feedbackRate30days, email, "Feedback(30 days)", SevenDaysData, false, false);
                    } else if (exceeding.SummaryFeedbackExceeding(feedback30days, orders30days)) {
                        SevenDaysData = getSevenDaysFeedbackData(account, "feedbackRate30days");
                        System.out.println("\n account:" + account + "\n feedback30days:" + feedback30days);
                        System.out.println("alert1");
                        ses.sendBySES(account, feedbackRate30days, email, "Feedback(30 days)", SevenDaysData, true, false);
                    }
//                    else if (exceeding.AccountClosedFeedbackExceeding(feedback30days, orders30days, status)) {
//                        SevenDaysData = getSevenDaysFeedbackData(account, "feedbackRate30days");
//                        System.out.println("\n account:" + account + "\n feedback30days:" + feedback30days);
//                        ses.sendBySES(account, feedbackRate30days, email, "Feedback(30 days)", SevenDaysData, true, true);
//                    }
                } else {
                    if (orders7 != 0) {
                        if (exceeding.FeedbackNotice(feedback7days, orders7days)) {
                            SevenDaysData = getSevenDaysFeedbackData(account, "feedbackRate7days");
                            System.out.println("\n account:" + account + "\n feedback7days:" + feedback7days);
                            ses.sendBySES(account, feedbackRate7days, email, "Feedback(7 days)", SevenDaysData, false, false);
                        }
                        else if (exceeding.SummaryFeedbackExceeding(feedback7days, orders7days)) {
                            SevenDaysData = getSevenDaysFeedbackData(account, "feedbackRate7days");
                            System.out.println("\n account:" + account + "\n feedback7days:" + feedback7days);
                            ses.sendBySES(account, feedbackRate7days, email, "Feedback(7 days)", SevenDaysData, true, false);
                        }
//                        else if (exceeding.AccountClosedFeedbackExceeding(feedback7days, orders7days, status)) {
//                            SevenDaysData = getSevenDaysFeedbackData(account, "feedbackRate7days");
//                            System.out.println("\n account:" + account + "\n feedback7days:" +
//                                    feedback7days);
//                            ses.sendBySES(account, feedbackRate7days, email, "Feedback(7 days)", SevenDaysData, true, true);
//                        }
                    }
                }
            }
        }
    }

    private void vtr(Exceeding exceeding, String account, String status, String validTrackingRate7days, String
            validTrackingRate30days, String email, SES ses) {
        String SevenDaysData;
        if( validTrackingRate30days.matches(Constants.DOUBLE_REGEX)
                && validTrackingRate7days.matches(Constants.DOUBLE_REGEX) &&
                Double.parseDouble(validTrackingRate30days) <= Double.parseDouble(validTrackingRate7days)){
            if (exceeding.VTRNotice(validTrackingRate30days)) {
                SevenDaysData = getSevenDaysData( account, "validTrackingRate30days");
                System.out.println("\n account:" + account + "\n validTrackingRate30days:" + validTrackingRate30days);
                ses.sendBySES(account, validTrackingRate30days, email, "VTR(30 days)", SevenDaysData, false, false);
            } else if (exceeding.SummaryVTRExceeding(validTrackingRate30days)) {
                SevenDaysData = getSevenDaysData( account, "validTrackingRate30days");
                System.out.println("\n account:" + account + "\n validTrackingRate30days:" + validTrackingRate30days);
                ses.sendBySES(account, validTrackingRate30days, email, "VTR(30 days)", SevenDaysData, true, false);
            }
//            else if(exceeding.AccountClosedVTRExceeding(validTrackingRate30days, status)) {
//                SevenDaysData = getSevenDaysData( account, "validTrackingRate30days");
//                System.out.println("\n account:" + account + "\n validTrackingRate30days:" + validTrackingRate30days);
//                ses.sendBySES(account, validTrackingRate30days, email, "VTR(30 days)", SevenDaysData, true, true);
//            }
        } else {
            if(exceeding.VTRNotice(validTrackingRate7days) ){
                SevenDaysData = getSevenDaysData( account, "validTrackingRate7days");
                System.out.println("\n account:" + account + "\n validTrackingRate7days:" + validTrackingRate7days);
                ses.sendBySES(account, validTrackingRate7days, email, "VTR(7 days)", SevenDaysData, false, false);
            } else if(exceeding.SummaryVTRExceeding(validTrackingRate7days)){
                SevenDaysData = getSevenDaysData( account, "validTrackingRate7days");
                System.out.println("\n account:" + account + "\n validTrackingRate7days:" +
                        validTrackingRate7days);
                ses.sendBySES(account, validTrackingRate7days, email, "VTR(7 days)", SevenDaysData, true, false);
            }
//            else if(exceeding.AccountClosedVTRExceeding(validTrackingRate7days, status)){
//                SevenDaysData = getSevenDaysData( account, "validTrackingRate7days");
//                System.out.println("\n account:" + account + "\n validTrackingRate7days:" + validTrackingRate7days);
//                ses.sendBySES(account, validTrackingRate7days, email, "VTR(7 days)", SevenDaysData, true, true);
//            }
        }
    }

    private void odr(Exceeding exceeding, String account, String odrShort, String status, String odrLong, String email, SES ses) {
        String SevenDaysData;
        if(odrShort.matches(Constants.DOUBLE_REGEX)
                && odrLong.matches(Constants.DOUBLE_REGEX)) {
            if (exceeding.OdrNotice(odrShort)) {
                SevenDaysData = getSevenDaysData(account, "odrShort");
                System.out.println("\n account:" + account + "\n odrShort:" + odrShort);
                ses.sendBySES(account, odrShort, email, "ODR(Short term)", SevenDaysData, false, false);
            } else if (!exceeding.OdrNotice(odrShort) && exceeding.OdrNotice(odrLong)) {
                SevenDaysData = getSevenDaysData(account, "odrLong");
                System.out.println("\n account:" + account + "\n odrLong:" + odrLong);
                ses.sendBySES(account, odrLong, email, "ODR(Long term)", SevenDaysData, false, false);
            } else if (exceeding.SummaryOdrExceeding(odrShort)) {
                SevenDaysData = getSevenDaysData(account, "odrShort");
                System.out.println("\n account:" + account + "\n odrShort:" + odrShort);
                ses.sendBySES(account, odrShort, email, "ODR(Short term)", SevenDaysData, true, false);
            }
            /*else if (!exceeding.AccountClosedODRExceeding(odrShort, status) && exceeding.AccountClosedODRExceeding(odrLong, status)) {

                SevenDaysData = getSevenDaysData(account, "odrLong");
                System.out.println("\n account:" + account + "\n odrLong:" + odrLong);
                ses.sendBySES(account, odrLong, email, "ODR(Long term)", SevenDaysData, true, true);
            }
            else if (exceeding.OdrExceeding(odrShort, status)) {
                SevenDaysData = getSevenDaysData(account, "odrShort");
                System.out.println("\n account:" + account + "\n odrShort:" + odrShort);
                ses.sendBySES(account, odrShort, email, "ODR(Short term)", SevenDaysData, true, false);
            }*/
            else if (!exceeding.SummaryOdrExceeding(odrShort) && exceeding.SummaryOdrExceeding(odrLong)) {
                SevenDaysData = getSevenDaysData(account, "odrLong");
                System.out.println("\n account:" + account + "\n odrLong:" + odrLong);
                ses.sendBySES(account, odrLong, email, "ODR(Long term)", SevenDaysData, true, false);
            }
        }
    }

    public String getEmail(String account){
        String email = "";
        if(account.matches(Constants.ACCOUNT_REGX)) {
            Condition cnd = Cnd.where("ACC", "like", account);
            List<Indicators.EmailRecord> emailRecordList = dbManager.query(Indicators.EmailRecord.class, cnd);
            System.out.println("emailRecordList.size():" + emailRecordList.size());
            if (emailRecordList.size() != 0) {
                Indicators.EmailRecord emlRecord = emailRecordList.get(0);
                email = emlRecord.email;
            }
        }
        return email;
    }

    public String getTeam(String account){
        String team = "";
        if(account.matches(Constants.ACCOUNT_REGX)) {
            Condition cnd = Cnd.where("ACC", "like", account);
            List<Indicators.EmailRecord> emailRecordList = dbManager.query(Indicators.EmailRecord.class, cnd);
            System.out.println("emailRecordList.size():" + emailRecordList.size());
            if (emailRecordList.size() != 0) {
                Indicators.EmailRecord emlRecord = emailRecordList.get(0);
                team = emlRecord.team;
            }
        }
        return team;
    }

    public String getCountry(String account){
        String country = "";
        if(account.matches(Constants.ACCOUNT_REGX)) {
            Condition cnd = Cnd.where("ACC", "like", account);
            List<Indicators.EmailRecord> emailRecordList = dbManager.query(Indicators.EmailRecord.class, cnd);
            System.out.println("emailRecordList.size():" + emailRecordList.size());
            if (emailRecordList.size() != 0) {
                Indicators.EmailRecord emlRecord = emailRecordList.get(0);
                country = emlRecord.country;
            }
        }
        return country;
    }

    public String getSevenDaysFeedbackData(String account,String indicator){
        String dataLoop=" ";
        for(int i=0;i< 14;i++) {
            String specialData = getSpecialDayFeedbackData(account, indicator, i);
            if(account.isEmpty() || specialData.isEmpty())
                specialData = "N/A@-";
            System.out.println("倒数第"+i+"天，Feedback指标"+indicator +":"+ specialData);
            dataLoop = dataLoop + specialData +"|";
        }
        return  dataLoop;
    }

    public String getSevenDaysData(String account,String indicator){
        String dataLoop="";
        for(int i=0;i< 14;i++) {
            String specialData = getSpecialDayData(account, indicator, i);
            if(specialData.isEmpty())
                specialData = "N/A@N/A";
            System.out.println("倒数第"+i+"天，指标"+indicator+":"+ specialData);
            dataLoop = dataLoop + specialData +"|";
        }
       return  dataLoop;
    }

    public String getSpecialDayFeedbackData(String account,String indicator,int i){
        String date = Constants.day(i);
        Condition cnd =  Cnd.where("date", "like", date).and("ACC","like", account);
        List<Indicators.AlertRecord> alertRecordList = dbManager.query(Indicators.AlertRecord.class, cnd);
        String feedbackRate30days = "";
        String feedbackRate7days = "";
        if(alertRecordList.size()!=0) {
            Indicators.AlertRecord atRecord = alertRecordList.get(0);

            String feedback7days = atRecord.feedback;
            String orders7day = atRecord.orders7day;

            feedbackRate7days = "N/A";

            feedbackRate7days = getFeedbackRate(feedback7days, orders7day, feedbackRate7days);

            String feedback30days = atRecord.feedback30days;
            String orders30day = atRecord.orders30days;

            feedbackRate30days = "N/A";

            feedbackRate30days = getFeedbackRate(feedback30days, orders30day, feedbackRate30days);
        }
        if(feedbackRate30days.isEmpty())
            feedbackRate30days = "-";

        if(feedbackRate7days.isEmpty())
            feedbackRate7days = "-";

            if (indicator.contentEquals("feedbackRate30days"))
                return date + "@" + feedbackRate30days;
            else
                return date + "@" + feedbackRate7days;


    }

    private String getFeedbackRate(String feedback30days, String orders30day, String feedbackRate30days) {
        if(!feedback30days.isEmpty() && !orders30day.isEmpty()) {
            double feedbackThirtyDays = Integer.parseInt(feedback30days);
            double ordersThirtyDays = Integer.parseInt(orders30day);

            if (ordersThirtyDays != 0) {
                double feedbackRateThirtyDays = feedbackThirtyDays / ordersThirtyDays;
                feedbackRate30days = String.valueOf(feedbackRateThirtyDays);
            }
            if(feedbackRate30days.isEmpty())
                feedbackRate30days = "N/A";
            feedbackRate30days = feedbackRate30days.contentEquals("N/A")? feedbackRate30days:Constants.ToPercent(feedbackRate30days);
        }
        return feedbackRate30days;
    }

    public String getSpecialDayData(String account,String indicator,int i){
        String date = Constants.day(i);
        Condition cnd =  Cnd.where("date", "like", date).and("ACC","like", account);
        List<Indicators.AlertRecord> alertRecordList = dbManager.query(Indicators.AlertRecord.class, cnd);
        System.out.println("account is :"+account+",date is:"+ date);
        System.out.println("size:"+alertRecordList.size());
        if(alertRecordList.size() !=0) {
            Indicators.AlertRecord atRecord = alertRecordList.get(0);

            String odrLong = atRecord.odrLong;
            String odrShort = atRecord.odrShort;

            String validTrackingRate30days = atRecord.validTrackingRate30days;
            String validTrackingRate7days = atRecord.validTrackingRate7days;

            String lateShipmentRate30days = atRecord.lateShipmentRate30days;
            String lateShipmentRate7days = atRecord.lateShipmentRate7days;

            String refundRate30days = atRecord.refundRate30days;
            String refundRate7days = atRecord.refundRate7days;

            String preFulfillCancelRate30days = atRecord.preFulfillCancelRate30days;
            String preFulfillCancelRate7days = atRecord.preFulfillCancelRate7days;

            if(odrLong.isEmpty())
                odrLong = "N/A";
            if(odrShort.isEmpty())
                odrShort = "N/A";
            if(validTrackingRate30days.isEmpty())
                validTrackingRate30days = "N/A";
            if(validTrackingRate7days.isEmpty())
                validTrackingRate7days = "N/A";
            if(lateShipmentRate30days.isEmpty())
                lateShipmentRate30days = "N/A";
            if(lateShipmentRate7days.isEmpty())
                lateShipmentRate7days = "N/A";
            if(refundRate30days.isEmpty())
                refundRate30days = "N/A";
            if(refundRate7days.isEmpty())
                refundRate7days = "N/A";
            if(preFulfillCancelRate30days.isEmpty())
                preFulfillCancelRate30days = "N/A";
            if(preFulfillCancelRate7days.isEmpty())
                preFulfillCancelRate7days = "N/A";

            odrLong = odrLong.contentEquals("N/A") || !odrLong.matches(Constants.DOUBLE_REGEX) ? odrLong : Constants.ToPercent(odrLong);

            odrShort = odrShort.contentEquals("N/A") || !odrShort.matches(Constants.DOUBLE_REGEX) ? odrShort : Constants.ToPercent(odrShort);

            validTrackingRate30days = validTrackingRate30days.contentEquals("N/A") || !validTrackingRate30days.matches(Constants.DOUBLE_REGEX) ? validTrackingRate30days : Constants.ToPercent(validTrackingRate30days);
            validTrackingRate7days = validTrackingRate7days.contentEquals("N/A") || !validTrackingRate7days.matches(Constants.DOUBLE_REGEX) ? validTrackingRate7days : Constants.ToPercent(validTrackingRate7days);

            lateShipmentRate30days = lateShipmentRate30days.contentEquals("N/A") || !lateShipmentRate30days.matches(Constants.DOUBLE_REGEX) ? lateShipmentRate30days : Constants.ToPercent(lateShipmentRate30days);
            lateShipmentRate7days = lateShipmentRate7days.contentEquals("N/A") || !lateShipmentRate7days.matches(Constants.DOUBLE_REGEX) ? lateShipmentRate7days : Constants.ToPercent(lateShipmentRate7days);

            refundRate30days = refundRate30days.contentEquals("N/A") || !refundRate30days.matches(Constants.DOUBLE_REGEX) ? refundRate30days : Constants.ToPercent(refundRate30days);
            refundRate7days = refundRate7days.contentEquals("N/A") || !refundRate7days.matches(Constants.DOUBLE_REGEX) ? refundRate7days : Constants.ToPercent(refundRate7days);

            preFulfillCancelRate30days = preFulfillCancelRate30days.contentEquals("N/A") || !preFulfillCancelRate30days.matches(Constants.DOUBLE_REGEX) ? preFulfillCancelRate30days : Constants.ToPercent(preFulfillCancelRate30days);
            preFulfillCancelRate7days = preFulfillCancelRate7days.contentEquals("N/A") || !preFulfillCancelRate7days.matches(Constants.DOUBLE_REGEX) ? preFulfillCancelRate7days : Constants.ToPercent(preFulfillCancelRate7days);

            if (indicator.contentEquals("odrLong"))
                return date+"@"+odrLong;
            else if (indicator.contentEquals("odrShort"))
                return date+"@"+odrShort;
            else if (indicator.contentEquals("validTrackingRate30days"))
                return date+"@"+validTrackingRate30days;
            else if (indicator.contentEquals("validTrackingRate7days"))
                return date+"@"+validTrackingRate7days;
            else if (indicator.contentEquals("lateShipmentRate30days"))
                return date+"@"+lateShipmentRate30days;
            else if (indicator.contentEquals("lateShipmentRate7days"))
                return date+"@"+lateShipmentRate7days;
            else if (indicator.contentEquals("refundRate30days"))
                return date+"@"+refundRate30days;
            else if (indicator.contentEquals("refundRate7days"))
                return date+"@"+refundRate7days;
            else if (indicator.contentEquals("preFulfillCancelRate30days"))
                return date+"@"+preFulfillCancelRate30days;
            else if (indicator.contentEquals("preFulfillCancelRate7days"))
                return date+"@"+preFulfillCancelRate7days;
        }

                return "";
    }
   //  0.0395|0.0395|0.0395|0.0405|0.0405|0.0435|0.0435|
   public static void main(String[] args) {
       Alert alt = ApplicationContext.getBean(Alert.class);
//        alt.getTodayData();
//       Condition cndInfringement = Cnd.where("ACC", "like", "32CA").and("ComplaintWithdrawn", "like", "N");
//       List<InfringementRecord> InfringementRecordList = alt.dbManager.query(InfringementRecord.class, cndInfringement);
//       System.out.println("All:"+InfringementRecordList.size());
//       alt.markInfringeVia("5254","22CA");

//       alt.getTodayInfringement();
       alt.test();

//       alt.getTodayData();

//        System.out.println(alt.getSevenDaysData("157US","odrLong"));
   }

}
