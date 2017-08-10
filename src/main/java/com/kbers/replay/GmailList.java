package com.kbers.replay;

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
 * <a href="mailto:jeromeinjesus@gmail.com">Jerome Hong</a> 7/14/2017 11:33 AM
 */
public class GmailList {
    @Inject
    public DBManager dbManager;

    @Inject
    public void init() {
        dbManager.getDao().create(GmailList.gmailList.class, false);
    }

    @Table(value = "MailLists")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class gmailList extends PrimaryKey {
        @Name public String id;
        @Column public String ACC;
        @Column public String CountryS;
        @Column public String SellerGmail;
        @Column public String FromS;
        @Column public String ToS;
        @Column public String DateS;
        @Column public String SubjectS;
        @Column public String Content;

        @Override
        public String getPK(){
            return id;
        }
    }

}
