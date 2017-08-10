package com.kbers.alert;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/10 16:54
 */
public class OdrEmailTemplate {
    public static String OdrNoticeTitle(String account,String indicator,String indicatorName){
        String title="Warning!"+account+" "+indicatorName+" score "+indicator+" almost exceeds Amazon Seller 1% limitation, Please take action to reduce it and protect your account!";

        return title;
    }

    public static String OdrWarningTitle(String account,String indicator,String indicatorName){
        String title="Important Warning!!! "+account+" "+indicatorName+" score "+indicator+" exceeded Amazon Seller 1% limitation, Please resolve it immediately to avoid account be SUSPENDED!";
        return title;
    }

    public static String OdrClosedTitle(String account,String indicator,String indicatorName){
        String title= "Dear  "+account+"  CS, to reinstate your account, please help to reduce  "+account+" "+indicatorName+
                "score from "+indicator+" to the safe level: less than 0.8%!!!";
        return title;
    }

    //  0.0395|0.0395|0.0395|0.0405|0.0405|0.0435|0.0435|

    public static String ODRNoticeTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
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
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+" score  "+indicator+" now is almost over " +
                "seller performance requirement: Amazon seller"+indicatorName+" must be less than  1%, so Amazon may review or " +
                "suspend "+account+" at any time from now. \n" +
                "Order Defect Rate (ODR): The percentage of orders that have received a negative feedback, an A-to-z " +
                "Guarantee claim or a service credit card chargeback. \n" +
                "ODR = (negative feedback orders amount + A-to-z Orders amount + chargeback orders amount) / total " +
                "order amount. "+") .</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> Your account "+indicatorName+" <strong>"+indicator +"</strong> is over the Amazon seller requirement.  To avoid Amazon suspend or review your account, please take the following action immediately.   </font><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Remove the "+account+" negative feedback </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) Contact customer to withdraw the "+account+" A-Z complaints</font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 3) Reduce the amount of chargeback orders which may happen from now on.</font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 4) Pray for your account "+indicatorName+" could  keep at safe level: far less than 1%. </font></em><br><br>\n"+
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
                "<font>Thank you very much. </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html?nodeId=200205140 <br>\n"+
                "The instructions about A-Z claim and charge-back claim: <br>\n"+
                "A-Z Claim:https://www.dropbox.com/s/k05iy4ebd1jlzxr/A-Z%20Claim.pdf?dl=0 <br>\n"+
                "A-Z Claim Case Study:https://www.dropbox.com/s/871epj85njuq0gk/Case%20study%20for%20A-Z%20Claim.pptx?dl=0 <br>\n"+
                "The steps for customer to withdraw A-Z claim:https://www.amazon.com/gp/help/customer/display.html/ref=help_search_1-1?ie=UTF8&nodeId=201889420&qid=1484590787&sr=1-1 <br>\n"+
                "Chargeback Claim:https://www.dropbox.com/s/wwa37a44z2wqu7b/Chargeback.pdf?dl=0 <br>\n"+
                "</body>\n" +
                "</html>\n";
        return html;

    }

    public static String ODRWarningTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
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
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+" score  "+indicator+" now is over the Amazon " +
                "seller performance requirement: Amazon seller"+indicatorName+"must be less than  1%, so Amazon may review or " +
                "suspend "+account+" at any time from now. \n" +
                "Order Defect Rate (ODR): The percentage of orders that have received a negative feedback, an A-to-z " +
                "Guarantee claim or a service credit card chargeback. \n" +
                "ODR = (negative feedback orders amount + A-to-z Orders amount + chargeback orders amount) / total " +
                "order amount. "+") .</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> Your account "+indicatorName+" <strong>"+indicator +"</strong> is over the Amazon seller requirement.  To avoid Amazon suspend or review your account, please take the following action immediately.   </font><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Remove the "+account+" negative feedback </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) Contact customer to withdraw the "+account+" A-Z complaints</font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 3) Reduce the amount of chargeback orders which may happen from now on.</font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 4) Pray for your account "+indicatorName+" could return to the safe level :  less then 1%.</font></em><br><br>\n"+
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
                "<font>Thank you very much. </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html?nodeId=200205140 <br>\n"+
                "The instructions about A-Z claim and charge-back claim: <br>\n"+
                "A-Z Claim:https://www.dropbox.com/s/k05iy4ebd1jlzxr/A-Z%20Claim.pdf?dl=0 <br>\n"+
                "A-Z Claim Case Study:https://www.dropbox.com/s/871epj85njuq0gk/Case%20study%20for%20A-Z%20Claim.pptx?dl=0 <br>\n"+
                "The steps for customer to withdraw A-Z claim:https://www.amazon.com/gp/help/customer/display.html/ref=help_search_1-1?ie=UTF8&nodeId=201889420&qid=1484590787&sr=1-1 <br>\n"+
                "Chargeback Claim:https://www.dropbox.com/s/wwa37a44z2wqu7b/Chargeback.pdf?dl=0 <br>\n"+
                "</body>\n" +
                "</html>\n";
        return html;
    }

    public static String ODRClosedTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
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
                "<em><font style=\"font-style:normal;\">Your account was suspended because your account"+indicatorName+"score "+
                indicator+" was over the Amazon seller policy: Seller "+indicatorName+" must be less than  1%, so Amazon suspended " +
                "your account and couldn't reinstate it. \n" +
                "Order Defect Rate (ODR): The percentage of orders that have received a negative feedback, an A-to-z " +
                "Guarantee claim or a service credit card chargeback. \n" +
                "ODR = (negative feedback orders amount + A-to-z Orders amount + chargeback orders amount) / total " +
                "order amount. \n" +
                "To reinstate your account, please take the following action immediately to help your our account ODR" +
                " score  return to the safe level:  less than 1%. </font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Remove the "+account+" negative feedback. If you don't know how to remove as your account is suspended, please contact your supervisor. </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) Contact customer to withdraw the "+account+" A-Z complaints</font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 3) Reduce the amount of chargeback orders which may happen from now on.</font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 4) Please notice us immediately when your "+indicatorName+" is reduced so that we can appeal to Amazon to reinstate your account. </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 5) Pray for your account "+indicatorName+" could return to the safe level: less than 1%. </font></em><br>\n"+
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
                "<font>Thank you very much. </font> <br>\n"+
                "<font>  We will continue to inspect your account and follow up your "+indicatorName+" from now on. \n" +
                "Please feel free to let us or your supervisor know if you have any questions.  </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html?nodeId=200205140 <br>\n"+
                "The instructions about A-Z claim and charge-back claim: <br>\n"+
                "A-Z Claim:https://www.dropbox.com/s/k05iy4ebd1jlzxr/A-Z%20Claim.pdf?dl=0 <br>\n"+
                "A-Z Claim Case Study:https://www.dropbox.com/s/871epj85njuq0gk/Case%20study%20for%20A-Z%20Claim.pptx?dl=0 <br>\n"+
                "The steps for customer to withdraw A-Z claim:https://www.amazon.com/gp/help/customer/display.html/ref=help_search_1-1?ie=UTF8&nodeId=201889420&qid=1484590787&sr=1-1 <br>\n"+
                "Chargeback Claim:https://www.dropbox.com/s/wwa37a44z2wqu7b/Chargeback.pdf?dl=0 <br>\n"+
                "</body>\n" +
                "</html>\n";
        return html;
    }
}
