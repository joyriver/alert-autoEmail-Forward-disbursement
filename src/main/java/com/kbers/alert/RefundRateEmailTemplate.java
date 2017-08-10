package com.kbers.alert;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/12 13:48
 */
public class RefundRateEmailTemplate {

    public static String RefundRateNoticeTitle(String account,String indicator,String indicatorName){
        String title= "Warning!"+account+" "+indicatorName+" score "+indicator+" almost exceeds Amazon Seller 10% limitation," +
                " Please take action to reduce it and protect your account!";
        return title;
    }

    public static String RefundRateWarningTitle(String account,String indicator,String indicatorName){
        String title=  "Important Warning!!! "+account+" "+indicatorName+" "+indicator+" exceeded Amazon Seller 10% limitation, Please resolve it immediately to avoid account be SUSPENDED!";
        return title;
    }

    public static String RefundRateClosedTitle(String account,String indicator,String indicatorName){
        String title="Dear  "+ account +"  CS, to reinstate your account, please help to reduce  "+account+" "+indicatorName+" score from "+indicator+" to the safe level: less than 8% !!!";
        return title;
    }

    public static String RefundRateNoticeTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
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
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+" "+indicator+"  almost " +
                "exceeded the Amazon seller policy: seller "+indicatorName+" must be less than 10%.   </font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> Please contact price revising department (ebzhiqi@gmail.com) to resolve this issue.  \n  </font><br><br>\n" +
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
                "<font>Thank you very much.  We will continue to inspect your account and follow up your "+indicatorName+" from now on.    </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "</body>\n" +
                "</html>\n";
        return html;

    }

    public static String RefundRateWarningTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
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
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+" "+indicator+" has exceeded the Amazon seller policy: seller "+indicatorName+" must be less than 10%. Amazon may review or suspend " + account + ". any time from now.  </font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> Please contact price revising department (ebzhiqi@gmail.com) to resolve this issue.  \n  </font><br><br>\n" +
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
                "<font>Thank you very much.  We will continue to inspect your account and follow up your "+indicatorName+" from now on.    </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "</body>\n" +
                "</html>\n";
        return html;

    }

    public static String RefundRateClosedTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
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
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+" "+indicator+" exceeded the Amazon seller policy: seller "+indicatorName+" must be less than 10%. Amazon reviewed or suspended " + account + ". </font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> Please contact price revising department (ebzhiqi@gmail.com) to resolve this issue.  \n  </font><br><br>\n" +
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
                "<font>Thank you very much.  We will continue to inspect your account and follow up your "+indicatorName+" from now on.    </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "</body>\n" +
                "</html>\n";
        return html;

    }
}
