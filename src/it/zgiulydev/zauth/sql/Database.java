package it.zgiulydev.zauth.sql;

import it.zgiulydev.zauth.utils.SecUtils;
import org.sqlite.JDBC;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.*;
import java.util.Map;

@SuppressWarnings("unused")
public class Database {
    private final File path;
    private Connection connection;

    public Database(File path) {
        this.path = path;
        try {
            DriverManager.registerDriver(new JDBC());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDb() {
        if(path.exists()) return;
        path.getParentFile().mkdirs();
        try {
            path.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+path.getAbsolutePath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
