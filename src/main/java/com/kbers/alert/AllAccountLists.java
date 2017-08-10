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
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/4/24 14:09
 */
public class AllAccountLists {
    @Inject
    public DBManager dbManager;

    @Inject
    public void init() {
        dbManager.getDao().create(AllAccountLists.allCountList.class, false);
    }

    @Table(value = "allcountlist")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class allCountList extends PrimaryKey {
        @Name public String id;
        @Column public String Number;
        @Column public String ACC;
        @Column public String Country;
        @Column public String Location;
        @Column public String StoreName;
        @Column public String ORK;
        @Column public String SellerGmail;
        @Column public String Warrior;
        @Column public String ManagerName;
        @Column public String appealMan;
        @Column public String CustomerService;

        @Column public String note;
        @Column public String WorkingPlace;
        @Column public String Status;

        @Column public String ManagerWoman;
        @Column public String StoreID;
        @Column public String BusinessType;

        @Override
        public String getPK(){
            return id;
        }
    }

}
