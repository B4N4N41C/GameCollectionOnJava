package ru.mochalin.laba6.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private static String DB_URL = "jdbc:mysql://localhost:3306/games";
    private static String LOGIN = "root";
    private static String PASS = "Kolia977";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, LOGIN, PASS);
    }

    public static String getProperty(String key) {
        URL url = DBHelper
                .class
                .getResource("/ru/mochalin/laba6/sql/statements.properties");
        Properties property = new Properties();
        FileInputStream fis = null;
        //TODO: add alert for catch
        try {
            fis = new FileInputStream(url.getFile());
            property.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return key != null ? property.getProperty(key) : null;
    }
}
