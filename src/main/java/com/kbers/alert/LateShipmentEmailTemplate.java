package com.kbers.alert;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/11 16:55
 */
public class LateShipmentEmailTemplate {

    public static String LateShipmentNoticeTitle(String account,String indicator,String indicatorName){
        String title= "Warning!"+account+" "+indicatorName+" score "+indicator+" almost exceeds Amazon Seller 4% limitation, " +
                "Please take action to reduce it and protect your account!";
        return title;

    }

    public static String LateShipmentWarningTitle(String account,String indicator,String indicatorName){
        String title=  "Important Warning!!! "+account+" "+indicatorName+" score "+indicator+" exceeded Amazon Seller 4% " +
                "limitation, Please resolve it immediately to avoid account be SUSPENDED!";
        return title;
    }

    public static String LateShipmentClosedTitle(String account,String indicator,String indicatorName){
        String title="Dear  "+ account +"  CS, to reinstate your account, please help to reduce  "+account+" "+indicatorName+" " +
                "score from "+indicator+" to the safe level: less than 3%!!!";
        return title;
    }

    public static String LateShipmentNoticeTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
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
                "exceeded the Amazon seller policy: seller late shipment rate must be less than 4%.   </font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> An order will be considered late if shipment is not " +
                "confirmed by the expected ship date, an order was previously considered late when the ship " +
                "confirmation was overdue by three or more days. \n  </font><br><br>\n" +
                "<em><font style=\"font-style:normal;\"> Please take the following action to reduce your late shipment rate.  </font><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Check and confirm orders within 2 business days.  </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) Please notice us immediately when your late shipment rate has some reduced.   </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 3) Pray for your account late shipment could keep at safe level: less than 4%.     </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 4)  Please  let us know if you have read this email and will" +
                " take action to resolve this.\n  </font></em><br>\n"+
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
                "<font>Thank you very much.  We will continue to inspect your account and follow up your "+indicatorName+" from now on.   </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html/?ie=UTF8&nodeId=200205140#late\n "+
                "</body>\n" +
                "</html>\n";
        return html;

    }

    public static String LateShipmentWarningTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
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
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+" "+indicator+" has exceeded the Amazon seller policy: seller late shipment rate must be less than 4%. Amazon may review or suspend "+account+" any time from now due to late shipment rate.    </font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> An order will be considered late if shipment is not " +
                "confirmed by the expected ship date, an order was previously considered late when the ship " +
                "confirmation was overdue by three or more days. \n  </font><br><br>\n" +
                "<em><font style=\"font-style:normal;\"> Please take the following action to reduce your late shipment rate.  </font><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Check and confirm orders within 2 business days.  </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) Please notice us immediately when your late shipment rate has some reduced.   </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 3) Pray for your account late shipment could return to the safe level: less than 4%.    </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 4)  Please  let us know if you have read this email and will" +
                " take action to resolve this.\n  </font></em><br>\n"+
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
                "<font>Thank you very much.  We will continue to inspect your account and follow up your "+indicatorName+" from now on.   </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html/?ie=UTF8&nodeId=200205140#late\n "+
                "</body>\n" +
                "</html>\n";
        return html;

    }

    public static String LateShipmentClosedTemplate(String account, String indicator,String indicatorName,String SevenDaysData){
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
                "<em><font style=\"font-style:normal;\">Your account "+indicatorName+" "+indicator+"  has " +
                "exceeded the Amazon seller policy: seller late shipment rate must be less than 4%. So Amazon suspended your account and couldn't reinstate it.  </font></em><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> An order will be considered late if shipment is not " +
                "confirmed by the expected ship date, an order was previously considered late when the ship " +
                "confirmation was overdue by three or more days. \n  </font><br><br>\n" +
                "<em><font style=\"font-style:normal;\"> Please take the following action to reduce your late shipment rate.  </font><br><br>\n" +
                "\n" +
                "<em><font style=\"font-style:normal;\"> 1) Check and confirm orders within 2 business days.  </font></em><br>\n" +
                "<em><font style=\"font-style:normal;\"> 2) Please notice us immediately when your late shipment rate has some reduced.   </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 3) Pray for your account late shipment could keep at safe level: less than 4%.     </font></em><br>\n"+
                "<em><font style=\"font-style:normal;\"> 4)  Please  let us know if you have read this email and will" +
                " take action to resolve this.\n  </font></em><br>\n"+
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
                "<font>Thank you very much.  We will continue to inspect your account and follow up your "+indicatorName+" from now on.   </font> <br>\n"+
                "<font> Please feel free to let us or your supervisor know if you have any questions. </font><br>\n"+
                " Kind Regards,<br>\n" +
                " Account Safety Team<br>\n" +
                "\n More information: https://www.amazon.com/gp/help/customer/display.html/?ie=UTF8&nodeId=200205140#late\n "+
                "</body>\n" +
                "</html>\n";
        return html;

    }
}
