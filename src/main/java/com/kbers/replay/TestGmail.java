package com.kbers.replay;

import com.amzass.service.common.ApplicationContext;
import com.amzass.utils.common.Tools;


/**
 * <a href="mailto:jeromeinjesus@gmail.com">Jerome Hong</a> 7/14/2017 3:11 PM
 */
public class TestGmail {



    public static void main(String[] args) {
        String a1 = "sd";
        String a2= "sd@";
        String a3= "sd@";
        String a4= "s@";
        String a5= "sf@";
        String a6= "sf@";
        String a7= "sf@";
        String a8 ="";
        GmailList MailDatabase = ApplicationContext.getBean(GmailList.class);
        MailDatabase.dbManager.save(new GmailList.gmailList(Tools.generateUUID(), a1, a2, a3, a4, a5, a6, a7,a8), GmailList.gmailList.class);
    }
}
