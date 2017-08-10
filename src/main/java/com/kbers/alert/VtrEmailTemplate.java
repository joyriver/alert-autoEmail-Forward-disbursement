package com.kbers.alert;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/10 17:02
 */
public class VtrEmailTemplate {
    public static String VtrNoticeTitle(String account,String indicator,String indicatorName){
        String title= "Warning!"+account+" "+indicatorName+" score "+indicator+" is below Amazon Seller standard"+indicatorName+"> 95% , Please take action to increase it and protect your account!";
        return title;

    }

    public static String VtrWarningTitle(String account,String indicator,String indicatorName){
        String title=  "Important Warning!!! "+account+" "+indicatorName+" score "+indicator+" is far below Amazon Seller " +
                "standard"+indicatorName+"> 95% , Please resolve it immediately to avoid account be SUSPENDED!";
        return title;
    }

    public static String VtrClosedTitle(String account,String indicator,String indicatorName){
        String title= "Dear  "+ account +"  CS, to reinstate your account, please help to increase "+account+" "+indicatorName+" " +
                "score from "+indicator+" to make it's close to Amazon standard"+indicatorName+"> 95%";
        return title;
    }

    public static String VtrNoticeTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
        System.out.println(SevenDaysData);
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
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+"score  "+indicator+"  is below the Amazon seller standard:" +
                " seller"+indicatorName+"must be more than 95%. Please tack action to increase it. Amazon may review or suspend "+account+". if your account"+indicatorName+"keep reducing. \n" +
                "VTR(Valid Tracking Rate) is a performance metric that reflects Amazon customers’ expectations that they should be able to find out where their orders " +
                "are and when they can expect to receive them. All major carriers, including USPS, FedEx, UPS, and DHL now offer free tracking.</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> Please take the following action immediately to increase your VTR:   </font><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Check mailman reports to see if it fetches and uploads " +
                "tracking number everyday. If not, please fix it immediately!     \n" +
                "Please note: The tracking number which is uploaded after the order was delivered, means it is not " +
                "useful to the buyer and thus it does not count toward your Valid Tracking Rate metric.  \n" +
                "So the recommended \"Tracking No Crawl Order Date Range\" in your mailman configuration is 1-12 Days" +
                " Before. </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) Please pray for your account "+indicatorName+" could rise to the safe level: more than 90%.  </font></em><br><br>\n"+
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
                "</tr>"+
                "</table><br><br>\n" +
                "<font>Thank you very much.  We will continue to inspect your account and follow up your "+indicatorName+" from now on. </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html?ie=UTF8&nodeId=201830480 "+
                "</body>\n" +
                "</html>\n";
        return html;

    }

    public static String VtrWarningTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
        System.out.println(SevenDaysData);
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
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+" score  "+indicator+"  is below the Amazon seller standard:" +
                " seller"+indicatorName+"must be more than 95%.  Amazon may review or suspend "+account+". any time from now. \n" +
                "VTR(Valid Tracking Rate) is a performance metric that reflects Amazon customers’ expectations that they should be able to find out where their orders " +
                "are and when they can expect to receive them. All major carriers, including USPS, FedEx, UPS, and DHL now offer free tracking.</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> Please take the following action immediately to increase your VTR:   </font><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Check mailman reports to see if it fetches and uploads " +
                "tracking number everyday. If not, please fix it immediately!     \n" +
                "Please note: The tracking number which is uploaded after the order was delivered, means it is not " +
                "useful to the buyer and thus it does not count toward your Valid Tracking Rate metric.  \n" +
                "So the recommended \"Tracking No Crawl Order Date Range\" in your mailman configuration is 1-12 Days" +
                " Before. </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) Please pray for your account "+indicatorName+" could rise to the safe level: more than 90%.  </font></em><br><br>\n"+
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
                "</tr>"+
                "</table><br><br>\n" +
                "<font>Thank you very much.  We will continue to inspect your account and follow up your "+indicatorName+" from now on. </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html?ie=UTF8&nodeId=201830480 "+
                "</body>\n" +
                "</html>\n";
        return html;
    }

    public static String VtrClosedTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
        System.out.println(SevenDaysData);
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
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+"score  "+indicator+"   was far below the Amazon seller standard: seller"+indicatorName+"must be more than 95%, so Amazon suspended your account and couldn't reinstate it.\n" +
                "VTR(Valid Tracking Rate) is a performance metric that reflects Amazon customers’ expectations that they should be able to find out where their orders " +
                "are and when they can expect to receive them. All major carriers, including USPS, FedEx, UPS, and DHL now offer free tracking.</font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> Please take the following action immediately to increase your VTR:   </font><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Please pray for your account that it could be reinstated as soon as possible. </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) Please learn and make sure you know how to check mailman " +
                "reports to ensure your account VTR so that it won't be suspended again.\n  </font></em><br><br>\n"+
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
                "</tr>"+
                "</table><br><br>\n" +
                "<font>Thank you very much.  We will continue to inspect your account and follow up your "+indicatorName+" from now on. </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html?ie=UTF8&nodeId=201830480 "+
                "</body>\n" +
                "</html>\n";
        return html;
    }
}
