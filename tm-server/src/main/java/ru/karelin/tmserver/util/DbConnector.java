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
        String host = properties.getProperty("host");
        String port = properties.getProperty("port");
        String bd = properties.getProperty("database");
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+bd, properties);
    }

}
