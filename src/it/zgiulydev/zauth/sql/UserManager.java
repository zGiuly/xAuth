package it.zgiulydev.zauth.sql;

import it.zgiulydev.zauth.utils.SecUtils;
import it.zgiulydev.zauth.zAuth;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserManager {
    private final String user;
    private final String password;
    private Database database;
    private Connection connection;

    public UserManager(String user, String password) {
        this.user = user;
        this.password = SecUtils.calcuateSha256(password);
        database = zAuth.getInstance().getDb();
        connection = database.getConnection();
    }

    public String getPassword() {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? LIMIT 1")) {
            statement.setString(1, user);
            ResultSet rs = statement.executeQuery();
            String res = rs.getString("password");
            rs.close();
            return res;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean login() {
        if (!userExists()) return false;
        if (SecUtils.compareHexToString(password, getPassword())) {
            zAuth.getInstance().getLogged().add(user);
            return true;
        }
        return false;
    }

    public boolean logout() {
        if(!userExists()) return false;
        if(zAuth.getInstance().getLogged().contains(user)) {
            zAuth.getInstance().getLogged().remove(user);
            return true;
        }
        return false;
    }

    public boolean userExists() {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE username = ? LIMIT 1")) {
            statement.setString(1, user);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                rs.close();
                return true;
            }
            return false;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerUser() {
        if(userExists()) return false;
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO players (username, password) VALUES (?, ?)")) {
            statement.setString(1, user);
            statement.setString(2, SecUtils.calcuateSha256(password));
            statement.execute();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registerUsers(Map<String, String> users) {
        users.forEach((user, password) -> {
            try(PreparedStatement statement = connection.prepareStatement("INSERT INTO players (username, password) VALUES (?, ?)")) {
                if(!userExists()) {
                    statement.setString(1, user);
                    statement.setString(2, SecUtils.calcuateSha256(password));
                    statement.execute();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
