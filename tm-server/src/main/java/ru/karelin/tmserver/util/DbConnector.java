package ru.karelin.tmserver.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnector {
    public static Connection init() throws ClassNotFoundException, SQLException, IOException {
        Properties properties = new Properties();
        properties.load(DbConnector.class.getResourceAsStream("/db.properties"));
        String url = properties.getProperty("url");
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, properties);
    }

}
