package com.kbers.disbursement;

import com.amzass.aop.Repeat;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Calendar;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/5/19 10:03
 */
public class Constants {
    static final String EUrate="http://quote.yahoo.com/d/quotes.csv?s=EURUSD=X&f=l1&e=.csv";
    static final String JPrate="http://quote.yahoo.com/d/quotes.csv?s=JPYUSD=X&f=l1&e=.csv";
    static final String GBPrate="http://quote.yahoo.com/d/quotes.csv?s=GBPUSD=X&f=l1&e=.csv";
    static final String CADrate="http://quote.yahoo.com/d/quotes.csv?s=CADUSD=X&f=l1&e=.csv";
    static final String MXNrate="http://quote.yahoo.com/d/quotes.csv?s=MXNUSD=X&f=l1&e=.csv";

    @Repeat(times = 8, sleepTime = 10)
    public static String getEUrate() throws IOException {
        String EUrateValue= Jsoup.connect(EUrate).timeout(30000000).ignoreContentType(true).get().text();
        return EUrateValue;
    }

    public static double getEUrateDouble() throws IOException {
        double EUrateDouble=Double.valueOf(getEUrate()).doubleValue();
        return EUrateDouble;
    }

    @Repeat(times = 8, sleepTime = 10)
    public static  String getJPrate() throws IOException {
        String JPrateValue=Jsoup.connect(JPrate).timeout(30000000).ignoreContentType(true).get().text();
        return  JPrateValue;
    }

    public static double getJPrateDouble() throws IOException {
        double JPrateDouble=Double.valueOf(getJPrate()).doubleValue();
        return JPrateDouble;
    }

    @Repeat(times = 8, sleepTime = 10)
    public static String getGBPrate() throws IOException {
        String GBPrateValue=Jsoup.connect(GBPrate).timeout(30000000).ignoreContentType(true).get().text();
        return GBPrateValue;
    }

    public static double getGBPrateDouble() throws IOException {
        double GBPrateDouble=Double.valueOf(getGBPrate()).doubleValue();
        return GBPrateDouble;
    }

    @Repeat(times = 8, sleepTime = 10)
    public static String getCADrate() throws IOException {
        String CADrateValue=Jsoup.connect(CADrate).timeout(30000000).ignoreContentType(true).get().text();
        return CADrateValue;
    }

    public static double getCADrateDouble() throws IOException {
        double CADrateDouble=Double.valueOf(getCADrate()).doubleValue();
        return CADrateDouble;
    }

    @Repeat(times = 8, sleepTime = 10)
    public static String getMXNrate() throws IOException {
        String MXNrateValue=Jsoup.connect(MXNrate).timeout(30000000).ignoreContentType(true).get().text();
        return MXNrateValue;
    }

    public static double getMXNrateDouble() throws IOException {
        double MXNrateDouble=Double.valueOf(getMXNrate()).doubleValue();
        return MXNrateDouble;
    }

    @Repeat(times = 8, sleepTime = 10)
    public static String  getAccountEmailList(int i) throws IOException {
        String valueurl="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=GETVALUE&p="+i;
        String value = Jsoup.connect(valueurl).timeout(3000000).ignoreContentType(true).get().text();
        return value;
    }

    public static String getTitle(){
        String title = null;
        Calendar c1 = Calendar.getInstance();
        int year = c1.get(Calendar.YEAR);
        int month = c1.get(Calendar.MONTH);
        int date = c1.get(Calendar.DATE);
        int hour = c1.get(Calendar.HOUR_OF_DAY);
        int minute = c1.get(Calendar.MINUTE);
        int second = c1.get(Calendar.SECOND);

        int m1=month;
        int d1=date-1;

        int nd=0;

        if(m1==2)
            nd=28;
        else if(m1==4 || m1==6 || m1==9|| m1==11)
            nd=30;
        else if(m1==1 || m1==3 || m1==5|| m1==7 || m1==8 || m1==10|| m1==12)
            nd=31;

        if(m1==1 && d1<1)
        {
            m1=12;
            d1=d1+nd;
        }else if(m1==1 && d1>=1)
        {
            m1=1;
            d1=d1;
        }else if(m1>1 && d1<1)
        {
            m1=m1;
            d1=d1+nd;
        }else if(m1>1 && d1>=1)
        {
            m1=m1+1;
            d1=d1;
        }



//          m1=12;
        if(m1<=9 && d1>=10)
            title= "0"+m1+"/"+d1+"/"+year;
        else if(m1<=9 && d1<10)
            title= "0"+m1+"/"+"0"+d1+"/"+year;
        else if(m1>9 && d1>=10)
            title= m1+"/"+d1+"/"+year;
        else if(m1>9 && d1<10)
            title= m1+"/"+"0"+d1+"/"+year;
        return title;
    }


}
