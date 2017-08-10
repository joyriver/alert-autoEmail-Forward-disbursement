package com.kbers.alert;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/11 15:56
 */
public class FeedbackEmailTemplate {

    public static String FeedbackNoticeTitle(String account,String indicator,String indicatorName){
        String title= "Warning!"+account+" "+indicatorName+" score "+indicator+" is almost below Amazon normal Seller standard at " +
                "least 10% feedback of their sales ,  Please take action to increase it and protect your account!";
        return title;

    }

    public static String FeedbackWarningTitle(String account,String indicator,String indicatorName){
        String title=  "Important Warning!!! "+account+" "+indicatorName+" score "+indicator+" is  below Amazon normal " +
                "Seller standard at least 10% feedback of their sales, Please resolve it immediately to avoid account " +
                "be SUSPENDED!";
        return title;
    }

    public static String FeedbackClosedTitle(String account,String indicator,String indicatorName){
        String title= "Dear  "+ account +"  CS, to reinstate your account, please help to increase "+account+" "+indicatorName+" " +
                "score from "+indicator+" to more than 10% feedback of your sales.";
        return title;
    }

    public static String FeedbackNoticeTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
        String SevenDatas [] =SevenDaysData.split("[|]");
        String last_13th = SevenDatas[13];
        String last_12th = SevenDatas[12];
        String last_11th = SevenDatas[11];
        String last_10th = SevenDatas[10];
        String last_9th = SevenDatas[9];
        String last_8th = SevenDatas[8];
        String last_7th = SevenDatas[7];
        String last_6th = SevenDatas[6];
        String last_5th = SevenDatas[5];
        String last_4th = SevenDatas[4];
        String last_3rd = SevenDatas[3];
        String last_2nd = SevenDatas[2];
        String last_1st = SevenDatas[1];
        String today = SevenDatas[0];

        System.out.println("Email历史指标："+today+","+last_1st+","+last_2nd+","+last_3rd+","+last_4th+","+last_5th+","+last_6th);

        String html="" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head> \n" +
                "<meta charset=\"utf-8\"> \n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\">Dear "+account+" Customer Service,\n</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+" is "+indicator+" which is almost below the Amazon seller standard: " +
                "Most Amazon sellers receive feedback on 10% - 20% of their sales. Amazon may review or suspend "+account+" any time from now. </font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> "+indicatorName+" =  feedback amount/ order amount.   </font><br><br>\n" +
                "<em><font style=\"font-style:normal;\"> Please take the following action immediately to increase your "+indicatorName+":  </font><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Please check your mailman reports to make sure it send feedback request emails to customers everyday;   </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) When you reply customers' email, please ask him to leave positive feedback for us after we have helped to resolve his quesiton and he is satisfied.   </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 3)  Please pray for your account 30 days feedback rate could keep the safe level: more than 10%.      </font></em><br><br>\n"+
                "<table border='1'>"+
                "<tr>"+
                "<td>"+last_13th.split("@")[0]+"</td>"+
                "<td>"+last_12th.split("@")[0]+"</td>"+
                "<td>"+last_11th.split("@")[0]+"</td>"+
                "<td>"+last_10th.split("@")[0]+"</td>"+
                "<td>"+last_9th.split("@")[0]+"</td>"+
                "<td>"+last_8th.split("@")[0]+"</td>"+
                "<td>"+last_7th.split("@")[0]+"</td>"+
                "<td>"+last_6th.split("@")[0]+"</td>"+
                "<td>"+last_5th.split("@")[0]+"</td>"+
                "<td>"+last_4th.split("@")[0]+"</td>"+
                "<td>"+last_3rd.split("@")[0]+"</td>"+
                "<td>"+last_2nd.split("@")[0]+"</td>"+
                "<td>"+last_1st.split("@")[0]+"</td>"+
                "<td>"+today.split("@")[0]+"</td>"+
                "</tr>"+
                "<tr>"+
                "<td>"+last_13th.split("@")[1]+"</td>"+
                "<td>"+last_12th.split("@")[1]+"</td>"+
                "<td>"+last_11th.split("@")[1]+"</td>"+
                "<td>"+last_10th.split("@")[1]+"</td>"+
                "<td>"+last_9th.split("@")[1]+"</td>"+
                "<td>"+last_8th.split("@")[1]+"</td>"+
                "<td>"+last_7th.split("@")[1]+"</td>"+
                "<td>"+last_6th.split("@")[1]+"</td>"+
                "<td>"+last_5th.split("@")[1]+"</td>"+
                "<td>"+last_4th.split("@")[1]+"</td>"+
                "<td>"+last_3rd.split("@")[1]+"</td>"+
                "<td>"+last_2nd.split("@")[1]+"</td>"+
                "<td>"+last_1st.split("@")[1]+"</td>"+
                "<td>"+today.split("@")[1]+"</td>"+
                "</tr>"+
                "</table><br><br>\n" +
                "<font>Thank you very much.  We will continue to inspect your account and follow up your feedback amount from now on.  </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html?nodeId=1161284&#much <br>\n"+
                "Leave feedback: https://www.amazon.com/gp/help/customer/display.html/ref=help_search_1-1?ie=UTF8&nodeId=201889700&qid=1484593592&sr=1-1 <br>\n"+
                "</body>\n" +
                "</html>\n";
        return html;

    }

    public static String FeedbackWarningTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
        String SevenDatas [] =SevenDaysData.split("[|]");
        String last_13th = SevenDatas[13];
        String last_12th = SevenDatas[12];
        String last_11th = SevenDatas[11];
        String last_10th = SevenDatas[10];
        String last_9th = SevenDatas[9];
        String last_8th = SevenDatas[8];
        String last_7th = SevenDatas[7];
        String last_6th = SevenDatas[6];
        String last_5th = SevenDatas[5];
        String last_4th = SevenDatas[4];
        String last_3rd = SevenDatas[3];
        String last_2nd = SevenDatas[2];
        String last_1st = SevenDatas[1];
        String today = SevenDatas[0];

        System.out.println("Email历史指标："+today+","+last_1st+","+last_2nd+","+last_3rd+","+last_4th+","+last_5th+","+last_6th+","+last_7th+","+last_8th+","+last_9th+","+last_10th+","+last_12th);
        System.out.println("alert3");
        String html="" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head> \n" +
                "<meta charset=\"utf-8\"> \n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\">Dear "+account+" Customer Service,\n</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+" is "+indicator+" which is far below the Amazon seller standard:  " +
                "Most Amazon sellers receive feedback on 10% - 20% of their sales. Amazon may review or suspend "+account+" any time from now. </font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> "+indicatorName+" = feedback amount/ order amount.   </font><br><br>\n" +
                "<em><font style=\"font-style:normal;\"> Please take the following action immediately to increase your "+indicatorName+":  </font><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Please check your mailman reports to make sure it send feedback request emails to customers everyday;   </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) When you reply customers' email, please ask him to leave positive feedback for us after we have helped to resolve his quesiton and he is satisfied.   </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 3) Please pray for your account 30 days feedback rate could increase to the safe level: more than 10%.     </font></em><br><br>\n"+
                "<table border='1'>"+
                "<tr>"+
                "<td>"+last_13th.split("@")[0]+"</td>"+
                "<td>"+last_12th.split("@")[0]+"</td>"+
                "<td>"+last_11th.split("@")[0]+"</td>"+
                "<td>"+last_10th.split("@")[0]+"</td>"+
                "<td>"+last_9th.split("@")[0]+"</td>"+
                "<td>"+last_8th.split("@")[0]+"</td>"+
                "<td>"+last_7th.split("@")[0]+"</td>"+
                "<td>"+last_6th.split("@")[0]+"</td>"+
                "<td>"+last_5th.split("@")[0]+"</td>"+
                "<td>"+last_4th.split("@")[0]+"</td>"+
                "<td>"+last_3rd.split("@")[0]+"</td>"+
                "<td>"+last_2nd.split("@")[0]+"</td>"+
                "<td>"+last_1st.split("@")[0]+"</td>"+
                "<td>"+today.split("@")[0]+"</td>"+
                "</tr>"+
                "<tr>"+
                "<td>"+last_13th.split("@")[1]+"</td>"+
                "<td>"+last_12th.split("@")[1]+"</td>"+
                "<td>"+last_11th.split("@")[1]+"</td>"+
                "<td>"+last_10th.split("@")[1]+"</td>"+
                "<td>"+last_9th.split("@")[1]+"</td>"+
                "<td>"+last_8th.split("@")[1]+"</td>"+
                "<td>"+last_7th.split("@")[1]+"</td>"+
                "<td>"+last_6th.split("@")[1]+"</td>"+
                "<td>"+last_5th.split("@")[1]+"</td>"+
                "<td>"+last_4th.split("@")[1]+"</td>"+
                "<td>"+last_3rd.split("@")[1]+"</td>"+
                "<td>"+last_2nd.split("@")[1]+"</td>"+
                "<td>"+last_1st.split("@")[1]+"</td>"+
                "<td>"+today.split("@")[1]+"</td>"+
                "</tr>"+
                "</table><br><br>\n" +
                "<font>Thank you very much.  We will continue to inspect your account and follow up your feedback amount from now on.  </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html?nodeId=1161284&#much <br>\n"+
                "Leave feedback: https://www.amazon.com/gp/help/customer/display.html/ref=help_search_1-1?ie=UTF8&nodeId=201889700&qid=1484593592&sr=1-1 <br>\n"+
                "</body>\n" +
                "</html>\n";
        return html;
    }

    public static String FeedbackClosedTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
        String SevenDatas [] =SevenDaysData.split("[|]");
        String last_13th = SevenDatas[13];
        String last_12th = SevenDatas[12];
        String last_11th = SevenDatas[11];
        String last_10th = SevenDatas[10];
        String last_9th = SevenDatas[9];
        String last_8th = SevenDatas[8];
        String last_7th = SevenDatas[7];
        String last_6th = SevenDatas[6];
        String last_5th = SevenDatas[5];
        String last_4th = SevenDatas[4];
        String last_3rd = SevenDatas[3];
        String last_2nd = SevenDatas[2];
        String last_1st = SevenDatas[1];
        String today = SevenDatas[0];

        System.out.println("Email历史指标："+today+","+last_1st+","+last_2nd+","+last_3rd+","+last_4th+","+last_5th+","+last_6th);

        String html="" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head> \n" +
                "<meta charset=\"utf-8\"> \n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\">Dear "+account+" Customer Service,\n</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+" is "+indicator+" which has been far below the Amazon seller standard:  " +
                "Most Amazon sellers receive feedback on 10% - 20% of their sales. So Amazon reviewed or suspended "+account+". </font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> "+indicatorName+" =  feedback amount/ order amount.   </font><br><br>\n" +
                "<em><font style=\"font-style:normal;\"> To reinstate your accound, please take the following action immediately to increase your "+indicatorName+":   </font><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Please email to customers who haven't left feedback for us and his order has no problem to leave positive feedback for us;   </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) When you reply customers' email, please ask him to leave positive feedback for us after we have helped to resolve his quesiton and he is satisfied. </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 3) Please notice us immediately when your 30 days feedback rate increased so that we can appeal to Amazon to reinstate your account as soon as possible.    </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 4) Please pray for your account that it could be reinstated as soon as possible.  </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 5) Please learn and make sure you know how to check mailman " +
                "reports to ensure your account feedback rate so that it won't be suspended again. \n" +
                "\n</em><br><br>\n"+
                "<table border='1'>"+
                "<tr>"+
                "<td>"+last_13th.split("@")[0]+"</td>"+
                "<td>"+last_12th.split("@")[0]+"</td>"+
                "<td>"+last_11th.split("@")[0]+"</td>"+
                "<td>"+last_10th.split("@")[0]+"</td>"+
                "<td>"+last_9th.split("@")[0]+"</td>"+
                "<td>"+last_8th.split("@")[0]+"</td>"+
                "<td>"+last_7th.split("@")[0]+"</td>"+
                "<td>"+last_6th.split("@")[0]+"</td>"+
                "<td>"+last_5th.split("@")[0]+"</td>"+
                "<td>"+last_4th.split("@")[0]+"</td>"+
                "<td>"+last_3rd.split("@")[0]+"</td>"+
                "<td>"+last_2nd.split("@")[0]+"</td>"+
                "<td>"+last_1st.split("@")[0]+"</td>"+
                "<td>"+today.split("@")[0]+"</td>"+
                "</tr>"+
                "<tr>"+
                "<td>"+last_13th.split("@")[1]+"</td>"+
                "<td>"+last_12th.split("@")[1]+"</td>"+
                "<td>"+last_11th.split("@")[1]+"</td>"+
                "<td>"+last_10th.split("@")[1]+"</td>"+
                "<td>"+last_9th.split("@")[1]+"</td>"+
                "<td>"+last_8th.split("@")[1]+"</td>"+
                "<td>"+last_7th.split("@")[1]+"</td>"+
                "<td>"+last_6th.split("@")[1]+"</td>"+
                "<td>"+last_5th.split("@")[1]+"</td>"+
                "<td>"+last_4th.split("@")[1]+"</td>"+
                "<td>"+last_3rd.split("@")[1]+"</td>"+
                "<td>"+last_2nd.split("@")[1]+"</td>"+
                "<td>"+last_1st.split("@")[1]+"</td>"+
                "<td>"+today.split("@")[1]+"</td>"+
                "</tr>"+
                "</table><br><br>\n" +
                "<font>Thank you very much.  We will continue to inspect your account and follow up your feedback amount from now on.  </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html?nodeId=1161284&#much <br>\n"+
                "Leave feedback: https://www.amazon.com/gp/help/customer/display.html/ref=help_search_1-1?ie=UTF8&nodeId=201889700&qid=1484593592&sr=1-1 <br>\n"+
                "</body>\n" +
                "</html>\n";
        return html;
    }
}
