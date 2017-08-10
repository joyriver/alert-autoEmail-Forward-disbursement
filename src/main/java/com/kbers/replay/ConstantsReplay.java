package com.kbers.replay;


import com.amzass.aop.Repeat;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * <a href="mailto:nathanael4ever@gmail.com">Nathanael Yang</a> 6/26/2017 8:33 AM
 */
public class ConstantsReplay {
    @Repeat(times = 8, sleepTime = 10)
    public static String  getAccountEmailList(int i) throws IOException {
        String valueurl="https://script.google.com/macros/s/AKfycbwT_lgvjI5iApGszmRkJ_ZEt3xmj4byni38dqV9GImrRGsYxag/exec?method=GETVALUE&p="+i;
        String value = Jsoup.connect(valueurl).timeout(3000000).ignoreContentType(true).get().text();
        return value;
    }

    static final String accountSafetyAscript2Parameter = "https://script.google.com/macros/s/AKfycbx2fsEoY-Gj55fWlb77i9NlzfAEuOQmELzOFLKux4HL_VpoEO4/exec?m=%s&h=%s";
    static final String methodForward = "ForwardMonitor";




}
