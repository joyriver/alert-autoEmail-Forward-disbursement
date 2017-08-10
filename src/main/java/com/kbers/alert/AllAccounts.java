package com.kbers.alert;

import com.amzass.database.DBManager;
import com.amzass.model.common.PrimaryKey;
import com.google.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;


/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/3/10 15:30
 */
public class AllAccounts {
    @Inject
    public DBManager dbManager;

    @Inject
    public void init() {
        dbManager.getDao().create(AllAccounts.allCount.class, false);
    }

    @Table(value = "allCounts")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class allCount extends PrimaryKey {
        @Name public String id;
        @Column public String childAccount;
        @Column public String marketPlace;
        @Column public String sellerEmail;
        @Override
        public String getPK(){
            return id;
        }
    }

}
