package au.gov.nla.amber.rescue.session;

import amberdb.AmberDb;
import amberdb.AmberSession;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProcessSessionRecords {

    public static void main(String[] args) throws SQLException {

        if (args.length < 7) {
            System.err.println("Usage:");
            System.err.println("java -jar resolve-session-records-1.jar <url> <user> <password> <session-key> <store-path> <commit-user> <commit-info>");
            System.exit(1);
        }

        MysqlDataSource amberdb = new MysqlDataSource();
        String url = args[0];
        String user = args[1];
        String password = args[2];
        Long session = Long.parseLong(args[3]);
        String path = args[4];
        String commitUser = args[5];
        String commitInfo = args[6];

        amberdb.setURL(url);
        amberdb.setUser(user);
        amberdb.setPassword(password);
        AmberDb amberDb = new AmberDb(amberdb, new File(path).toPath());
        DriverManager.registerDriver(org.h2.Driver.load());
        try (AmberSession ambersession = amberDb.begin()) {
            ambersession.commitPersistedSession(session, commitUser, commitInfo);
        }
    }
}
