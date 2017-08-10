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

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/5 11:26
 */
public class Indicators {
    @Inject
    public DBManager dbManager;

    @Inject
    public void initialize() throws IOException, GeneralSecurityException {
        InputStream in = this.getClass().getResourceAsStream("/" + "client_secret.json");
    }
    @Inject
    public void init() {
        dbManager.getDao().create(Indicators.AlertRecord.class, false);
        dbManager.getDao().create(Indicators.EmailRecord.class, false);
        dbManager.getDao().create(Indicators.InfringementRecord.class, false);
        dbManager.getDao().create(Indicators.InfringementRecordVia.class, false);
    }

    @Table(value = "alert")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AlertRecord  extends PrimaryKey {
        @Name public String id;
        @Column public String date;
        @Column public String ACC;
        @Column public String status;
        @Column public String feedback;
        @Column public String feedback1Week;
        @Column public String orders7day;
        @Column public String UnshippedOrders;
        @Column public String Ratings30days;
        @Column public String Ratings365days;
        @Column public String odrShort;
        @Column public String odrLong;
        @Column public String preFulfillCancelRate7days;
        @Column public String preFulfillCancelRate30days;
        @Column public String lateShipmentRate7days;
        @Column public String lateShipmentRate30days;
        @Column public String refundRate7days;
        @Column public String refundRate30days;
        @Column public String feedback30days;
        @Column public String orders30days;
        @Column public String suppressedListing;
        @Column public String validTrackingRate7days;
        @Column public String validTrackingRate30days;
        @Column public String feedbackPercentage30days;
        @Column public String region;
        @Column public String cityCountry;

        @Override
        public String getPK() {
            return id;
        }
    }

    @Table(value = "email")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmailRecord  extends PrimaryKey {
        @Name public String id;
        @Column public String ACC;
        @Column public String country;
        @Column public String email;
        @Column public String team;

        @Override
        public String getPK() {
            return id;
        }
    }

    @Table(value = "Infringement")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InfringementRecord extends PrimaryKey {
        @Name public String id;
        @Column public int IDI;
        @Column public String ACC;
        @Column public String ReceiveDate;
        @Column public String OwnerEmail;
        @Column public String OwnerName;
        @Column public String ASIN;
        @Column public String Type;
        @Column public String BrandName;
        @Column public String FirstEmail;
        @Column public String Backup;
        @Column public String WorkerName;

        @Column public String ComplaintWithdrawn;
        @Column public String WithdrawingDate;
        @Column public String WithdrawnBy;

        @Column public String Followup2;
        @Column public String Followup3;
        @Column public String PhoneArea;

        @Column public String Reply1stDate;
        @Column public String Summary;
        @Column public String Reply3rdDate;
        @Column public String InfringementProducts;
        @Column public String Remark2;

        @Override
        public String getPK(){
            return id;
        }
    }

    @Table(value = "InfringementVia")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InfringementRecordVia extends PrimaryKey{
        @Name public String id;
        @Column public int Number;
        @Column public String ACC;
        @Column public int count;
        @Override
        public String getPK(){
            return id;
        }
    }



}
