package com.kbers.alert;

import com.amzass.service.common.ApplicationContext;
import com.kbers.disbursement.Disbursement;
import com.kbers.replay.ReplyEmail;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainEntry implements Job {
    public static void main(String[] args) throws JobExecutionException {
        new MainEntry().execute(null);
    }

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Logger logger = Logger.getLogger(MainEntry.class);
        SimpleLayout layout = new SimpleLayout();
        FileAppender appender = null;


        // Set the logger level to Level.INFO
        logger.setLevel(Level.INFO);
        // This request will be disabled since Level.DEBUG < Level.INFO.
        logger.debug("This is debug.");
        // These requests will be enabled.
        try {
            appender = new FileAppender(layout, "Alert.log", false);
        } catch (Exception e) {
            logger.error("生成log失败：", e);
        }
        logger.addAppender(appender);
        try {
            exe();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void exe() throws Exception {

        Disbursement dbr = ApplicationContext.getBean(Disbursement.class);
        dbr.singleValidate();

        ReplyEmail security = ApplicationContext.getBean(ReplyEmail.class);
        security.scanAccount(true);
//
//        WatchdogMonitorDownloading watchdog = ApplicationContext.getBean(WatchdogMonitorDownloading.class);
//        watchdog.clearIndicators();
//        watchdog.loadAccountIndicators();
//
//        EmailListDownloading EmailList = ApplicationContext.getBean(EmailListDownloading.class);
//        EmailList.clearEmail();
//        EmailList.downloadEmailList();
//
//        //  未每次清空InfringementVia库
//        InfringementCaseRecordDownloading Infringement = ApplicationContext.getBean(InfringementCaseRecordDownloading.class);
//        Infringement.clearInfringement();
//        Infringement.loadInfringement();
//
//        Alert alt = ApplicationContext.getBean(Alert.class);
//
//        if(Constants.getWeekDay() == 1 || Constants.getWeekDay() == 4){
//            alt.getTodayInfringement();
//        }
//
//        if(Constants.getWeekDay() == 7 || Constants.getWeekDay() == 1 || Constants.getWeekDay() == 3|| Constants.getWeekDay() == 5) {
//            alt.getTodayData();
//        }
//
//        Summary sum = ApplicationContext.getBean(Summary.class);
////        sum.getEverydayData(exceeding,0);
//        sum.getMonthData();
//
//        AppealEmail appeal = ApplicationContext.getBean(AppealEmail.class);
//        appeal.scanAccount();



    }


}  