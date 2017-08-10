package com.kbers.alert;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/2/9 15:28
 */
public class InfringementEmailTemplate {
    public static String ActiveInfringementTitle(String account, String indicatorAmount){
        String title ="Dear  "+ account + "  Customer Service, Your account has  "+indicatorAmount+" infringement cases are not withdrawn, please help to withdraw them immediately to avoid Amazon suspend or review your account!!!";
        return title;
    }

    public static String CloseInfringementTitle(String account, String indicatorAmount){
        String title = "Dear "+ account +" CS, Your account has  "+indicatorAmount+" infringement cases are not withdrawn, please help to withdraw them immediately and then Amazon can reinstate your account!";
        return title;
    }

    public static String ActiveInfringementTemplate(String account, String result, String indicatorAmount){

        String html= ""+
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head> \n" +
                "<meta charset=\"utf-8\"> \n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\">Dear "+account+" Customer Service,\n</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\">Your account has  "+indicatorAmount+" infringement cases are " +
                "not withdrawn which is very risky for your account. Amazon may review or suspend "+ account +" any time " +
                "from now due to the "+ indicatorAmount +" infringement cases.  Please help to contact the following " +
                "right owners to withdraw their complaints.The cases with yellow color means the RO has withdrawn the complaints on other seller accounts. So you can first follow \n" +
                "the right owner marked by the yellow color.<br>\n"+result+"</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> Due to more and more accounts were suspended by Amazon because of the infringement cases and also the very " +
                "limited human power of HQ Infringement withdrawing Team,  you are required to study the following materials about how to follow up the right owners to " +
                "withdraw the complaints for your accounts. Please see the teaching material here: \n  </font><br><br>\n" +
                "<em><font style=\"font-style:normal;\"> English version: https://www.dropbox.com/s/s9btj3s0wkve36m/English.zip?dl=0 <br>\n" +
                "<em><font style=\"font-style:normal;\"> Chinese version: https://www.dropbox.com/s/jlcaz56nsb2np6d/CN.zip?dl=0 <br>\n" +
                "<em><font style=\"font-style:normal;\"> Video text: https://www.dropbox.com/s/0f13pwi6shozyrh/InfringementCaseEmailfollowUpExplanation.docx?dl=0 <br>\n"+
                "<em><font style=\"font-style:normal;\"> https://www.dropbox.com/s/h7bhdxc16yc5w0q/InfringementPhoneCallWithdrawExplanation.docx?dl=0     </font></em><br><br>\n"+
                "<em><font style=\"font-style:normal;\"> If you have any qustions, please feel free to send email to the Infringement Withdrawing Team (ebsafetyipro12@gmail.com ).   </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> Or you may contact the Infringement withdrawing team senior members to get the exact support.    </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> Helen: +1 202 725 9368(Telegram);   yuqininthelord@gmail.com   </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> Meng:   +1 951 294 1274(Telegram);  mengzsc16@gmail.com   </font></em><br><br>\n"+
                "<font>Thank you very much. We will continue inspecting your account until your not withdrawing cases amount is less than seven.    </font> <br><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br><br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html/ref=help_search_1-1?ie=UTF8&nodeId=201361050&qid=1485847612&sr=1-1\n "+
                "</body>\n" +
                "</html>\n";
        return html;

    }

    public static String CloseInfringementTemplate(String account, String result, String indicatorAmount){
        String html= ""+
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head> \n" +
                "<meta charset=\"utf-8\"> \n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\">Dear "+account+" Customer Service,\n</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\">\n" +
                "Your account was suspended due to the following "+indicatorAmount+" infringement cases are not " +
                "withdrawn. But Amazon could reinstate it  if  the "+indicatorAmount+" infringement cases are " +
                "withdrawn by the right owners.  So please help to contact the following right owners to withdraw " +
                "their complaints. The cases with yellow color means the RO has withdrawn the complaints on other seller accounts. So you can first follow \n" +
                "the right owner marked by the yellow color.<br>\n"+result+"</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> Due to more and more accounts were suspended by Amazon because of the infringement cases and also the very " +
                "limited human power of HQ Infringement withdrawing Team,  you are required to study the following materials about how to follow up the right owners to " +
                "withdraw the complaints for your accounts. Please see the teaching material here: \n  </font><br><br>\n" +
                "<em><font style=\"font-style:normal;\"> English version: https://www.dropbox.com/s/s9btj3s0wkve36m/English.zip?dl=0 <br>\n" +
                "<em><font style=\"font-style:normal;\"> Chinese version: https://www.dropbox.com/s/jlcaz56nsb2np6d/CN.zip?dl=0 <br>\n" +
                "<em><font style=\"font-style:normal;\"> Video text: https://www.dropbox.com/s/0f13pwi6shozyrh/InfringementCaseEmailfollowUpExplanation.docx?dl=0 <br>\n"+
                "<em><font style=\"font-style:normal;\"> https://www.dropbox.com/s/h7bhdxc16yc5w0q/InfringementPhoneCallWithdrawExplanation.docx?dl=0     </font></em><br><br>\n"+
                "<em><font style=\"font-style:normal;\"> If you have any qustions, please feel free to send email to the Infringement Withdrawing Team (ebsafetyipro12@gmail.com ).   </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> Or you may contact the Infringement withdrawing team senior members to get the exact support.    </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> Helen: +1 202 725 9368(Telegram);   yuqininthelord@gmail.com   </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> Meng:   +1 951 294 1274(Telegram);  mengzsc16@gmail.com   </font></em><br><br>\n"+
                "<font>Thank you very much. We will continue inspecting your account and try our best to reinsate it.     </font> <br><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br><br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html/ref=help_search_1-1?ie=UTF8&nodeId=201361050&qid=1485847612&sr=1-1\n "+
                "</body>\n" +
                "</html>\n";
        return html;
    }
}
