package com.kbers.replay;

import com.amzass.service.common.ApplicationContext;
import com.kbers.alert.*;
import com.kbers.disbursement.Disbursement;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainEntryReply implements Job {
    public static void main(String[] args) throws JobExecutionException {
        new MainEntryReply().execute(null);
    }

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Logger logger = Logger.getLogger(MainEntryReply.class);
        SimpleLayout layout = new SimpleLayout();
        FileAppender appender = null;


        // Set the logger level to Level.INFO
        logger.setLevel(Level.INFO);
        // This request will be disabled since Level.DEBUG < Level.INFO.
        logger.debug("This is debug.");
        // These requests will be enabled.
//        try {
//            appender = new FileAppender(layout, "Alert.log", false);
//        } catch (Exception e) {
//            logger.error("生成log失败：", e);
//        }
//        logger.addAppender(appender);
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

//        Disbursement dbr = ApplicationContext.getBean(Disbursement.class);
//        dbr.singleValidate();

        ReplyEmail rpe = ApplicationContext.getBean(ReplyEmail.class);
        rpe.scanAccount(true);
    }


}  