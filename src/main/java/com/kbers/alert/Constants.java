package com.kbers.alert;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/5 9:21
 */
public class Constants
{
    static final String methodMonitor = "AccountMonitor";
    static final String methodSortMonitor = "SortMonitor";
    static final String ORDER_UPDATE_SHEET_NAME_REGEX = "^[0-9]{1,2}/[0-9]{1,2}$";
    static final String spreadId = "1HeKQ47E1U4jUaHfjcQYZU-jDxyhOBLxAeiA8-UTypHE";
    static final String spreadIdInfringement = "1_NTV-c10kJXJ2m5FtZwRBcUWn6DIZtdS5rshdNQ8VzE";
    static final String spreadIdAllCountList = "1Pt8M2o6EAvh0IAtnlPZPx58-BtFwsBXP-MYgR_z0XsI";
    final static String ascript1ParameterInfringement ="https://script.google.com/macros/s/AKfycbytnD_M7EG7R-eYyAycFM5WEvtIdaC5WZcQid62yyATfDeqsjM/exec?m=%s";
    final static String ascript2ParameterAlert = "https://script.google.com/macros/s/AKfycbytnD_M7EG7R-eYyAycFM5WEvtIdaC5WZcQid62yyATfDeqsjM/exec?m=%s&h=%s";
    final static String ascript2ParameterSummary = "https://script.google.com/macros/s/AKfycbxU6kYaH7My6afsQBZYi8Wl8Lux5Qxd_vqkvfNBKgxrL6vW_Bo/exec?m=%s&h=%s";
    final static String methodAccount = "LOADACCOUNTDATA";
    final static String methodInfringement ="LOADINFRINGEMENT";
    final static String methodAllAccountList = "LOADALLACCOUNTLIST";
    static final String DOUBLE_REGEX = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";
    static final String TINTEGER_REGX = "^T\\d+$";
    static final String INTEGER_REGX = "^\\d+$";
    static final String ACCOUNT_REGX ="^[0-9]{2,3}[A-Z]{2}$";

    static final String emailAscript2Parameter = "https://script.google.com/macros/s/AKfycbytnD_M7EG7R-eYyAycFM5WEvtIdaC5WZcQid62yyATfDeqsjM/exec?m=%s&h=%s";
    static final String methodEmail = "LOADEMAILLIST";

    static final String Junyan_email = "kbintlcss@gmail.com";
    static final String Dongyong_email ="ezekieldong@gmail.com";
    static final List<String> Dover_email  = Arrays.asList("aaronkber@gmail.com", "joexu777@gmail.com");
    static final String RS_email ="josephchenkb@gmail.com";
    static final String security_email ="ebsafetywatcher@gmail.com";

    static final String sesSenderInfringe=" ebsafetyipro12@gmail.com";
    static final String sesSenderAlert = "ebsafetywatcher@gmail.com";

    static final String odr_vtr_feedback_email ="kbaccmetrics2017@gmail.com";
    static final String lateshipment_email ="willliforever153@gmail.com";
    static final List<String> refundRate_email =Arrays.asList("bujuninchrist@gmail.com","ebzhiqi@gmail.com");
    static final String developer_email = "joyriver7@gmail.com";

    static  final String APPLICATION_NAME = "Appeal";
    static final String alertAscript2Parameter = "https://script.google.com/macros/s/AKfycbytnD_M7EG7R-eYyAycFM5WEvtIdaC5WZcQid62yyATfDeqsjM/exec?m=%s&h=%s";
    static final String methodAccount1 = "LOADEMAILLIST";
    static final String accountSafetyAscript2Parameter = "https://script.google.com/macros/s/AKfycbxU6kYaH7My6afsQBZYi8Wl8Lux5Qxd_vqkvfNBKgxrL6vW_Bo/exec?m=%s&h=%s";
    static final String methodAppeal = "AppealMonitor";

    public static String today() {
        Calendar c1 = Calendar.getInstance();
        int month = c1.get(Calendar.MONTH) + 1;
        int date = c1.get(Calendar.DATE) ;
        String formatMonth = StringUtils.leftPad(String.valueOf(month), 2, '0');
        String formatDate  = StringUtils.leftPad(String.valueOf(date), 2, '0');
        return formatMonth + "/" + formatDate;
    }

    public static  String day(int i){
        Calendar c1 = Calendar.getInstance();
        int month = c1.get(Calendar.MONTH) + 1;
        int date = c1.get(Calendar.DATE) -i ;
        String formatMonth = null;
        String formatDate = null;

        if(date >= 1) {
             formatMonth = StringUtils.leftPad(String.valueOf(month), 2, '0');
             formatDate = StringUtils.leftPad(String.valueOf(date), 2, '0');
        } else {
            month = month -1;
            date = date +gap(month);
            formatMonth = StringUtils.leftPad(String.valueOf(month), 2, '0');
            formatDate = StringUtils.leftPad(String.valueOf(date), 2, '0');
        }
        return formatMonth + "/" + formatDate;
    }

    public static int gap(int month) {
        int gap= 0;
        if (month == 2)
            gap = 28;
        else if (month == 4 || month == 6 || month == 9 || month == 11)
            gap = 30;
        else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
            gap = 31;

        return gap;
    }


    public static int date(){
        Calendar c1 = Calendar.getInstance();
        int date = c1.get(Calendar.DATE)  ;
        return date;
    }

    public static String thisMonth(){
        Calendar c1 = Calendar.getInstance();
        int month = c1.get(Calendar.MONTH) + 1;
        String formatMonth = StringUtils.leftPad(String.valueOf(month), 2, '0');
        return formatMonth;
    }

    public static String thisDay(int i){
        Calendar c1 = Calendar.getInstance();
        int date = c1.get(Calendar.DATE) -i ;
        String formatDate  = StringUtils.leftPad(String.valueOf(date), 2, '0');
        return formatDate;
    }

    public static String ToPercent(String indicator){
//        double indicatorPretransfer = Float.parseFloat(indicator);
//        BigDecimal id = new BigDecimal(indicatorPretransfer).setScale(4, RoundingMode.UP);
//        String indicatorTransfer = String.valueOf(id.doubleValue()) ;
//        System.out.println(indicatorTransfer);
        double shortdouble = Float.parseFloat(indicator) * 100;
        BigDecimal bg = new BigDecimal(shortdouble).setScale(2, RoundingMode.UP);
        indicator = String.valueOf(bg.doubleValue()) + "%";
        return indicator;
    }

    public static int getWeekDay(){
        Calendar c1 = Calendar.getInstance();
        Date myDate = new Date();
        c1.setTime(myDate);
        int weekDay = c1.get(Calendar.DAY_OF_WEEK) -1;
        return weekDay;
    }

    public static int getWeekOfYear(){
        Calendar c1 = Calendar.getInstance();
        return c1.get(Calendar.WEEK_OF_YEAR);
    }

    public static void main(String[] args) {
        System.out.println(getWeekOfYear());
    }




}
