package au.gov.nla.amber.rescue.session;

import java.io.File;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import amberdb.AmberDb;
import amberdb.AmberSession;

public class ProcessSessionRecords {

    public static void main(String[] args) throws SQLException {

        if (args.length != 3) {
            System.err.println("Usage:");
            System.err.println("java -jar resolve-session-records-1.jar <url> <user> <password> <session-key> <store-path> <info> <moreinfo>");
            System.exit(1);
        }

        MysqlDataSource amberdb = new MysqlDataSource();
        String url = args[0];
        String user = args[1];
        String password = args[2];
        Long session = Long.parseLong(args[3]);
        String path = args[4];
        String info = args[5];
        String moreinfo = args[6];

        amberdb.setURL(url);
        amberdb.setUser(user);
        amberdb.setPassword(password);
        AmberDb amberDb = new AmberDb((DataSource) amberdb, new File(path).toPath());
 
        try (AmberSession ambersession = amberDb.resume(session)) {
            ambersession.commit(info, moreinfo);
        }
    }
}
