package com.kbers.alert;

import com.amzass.aop.Repeat;
import com.amzass.database.DBManager;
import com.amzass.service.common.ApplicationContext;
import com.amzass.service.sheet.AppScript;
import com.google.inject.Inject;
import com.kbers.alert.Indicators.AlertRecord;
import com.mailman.model.common.Sheets;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/3/2 15:07
 */
public class InfringementRecordViaDownloading {
    @Inject
    DBManager dbManager;
    @Inject
    private AppScript appScript;
    private static final Logger LOGGER = LoggerFactory.getLogger(WatchdogMonitorDownloading.class);

   void UpdateInfringementRecordVia(String id, int Number, String account, int count){
       Connection connection = null;
       try
       {
           // create a database connection
           connection = DriverManager.getConnection("jdbc:sqlite:db/orderman.db");
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);  // set timeout to 30 sec.

//           statement.executeUpdate("drop table if exists person");

//           statement.executeUpdate("create table person (id integer, name string)");
           statement.executeUpdate("insert into InfringementVia values('"+id+"','"+Number+"','"+account+"','"+count+"')");
//           statement.executeUpdate("insert into InfringementVia values(9, 'yui',1)");
//           statement.executeUpdate("insert into InfringementVia values(7, 'yuli',0)");
//           ResultSet rs = statement.executeQuery("select * from person");

//           Condition cnd =  Cnd.where("date", "like", Constants.today());
//           List<AlertRecord> alertRecordList = dbManager.query(Indicators.AlertRecord.class, cnd);
//           System.out.println("alertRecordList.size():" + alertRecordList.size());

//           while(rs.next())
//           {
//               // read the result set
//               System.out.println("name = " + rs.getString("name"));
//               System.out.println("id = " + rs.getInt("id"));
//           }
       }
       catch(SQLException e)
       {
           // if the error message is "out of memory",
           // it probably means no database file is found
           System.err.println(e.getMessage());
       }
       finally
       {
           try
           {
               if(connection != null)
                   connection.close();
           }
           catch(SQLException e)
           {
               // connection close failed.
               System.err.println(e);
           }
       }
   }

    public static void main(String[] args) {
        InfringementRecordViaDownloading security = ApplicationContext.getBean(InfringementRecordViaDownloading.class);
//        security.UpdateInfringementRecordVia();
    }
}
