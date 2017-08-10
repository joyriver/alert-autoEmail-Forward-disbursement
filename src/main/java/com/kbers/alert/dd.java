package com.kbers.alert;

import com.amzass.database.DBManager;
import com.amzass.service.common.ApplicationContext;
import com.google.inject.Inject;
import com.kbers.alert.Indicators.InfringementRecord;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;

import java.util.List;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/4/13 15:24
 */
public class dd {
    @Inject
    DBManager dbManager;



    public static void main(String[] args) {
        JavaSwing exceeding = ApplicationContext.getBean(JavaSwing.class);
        System.out.println(exceeding.de(12));
    }
}
