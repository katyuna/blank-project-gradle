package utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.bidi.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseClient {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private org.openqa.selenium.bidi.Connection connection;
    private String dbKey;

    public void establishConnection(String dbKey) {
        this.dbKey = dbKey;

        String url = mustGet("DB_" + dbKey + "_URL");
        String user = mustGet("DB_" + dbKey + "_USER");
        String password = mustGet("DB_" + dbKey + "_PASSWORD");

        try {
            connection = (org.openqa.selenium.bidi.Connection) DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Не удалось подключиться к БД: " + dbKey + " (" + url + ")", e
            );
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            connection.close();
        }
    }

    private static String mustGet(String key) {
        String value = dotenv.get(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("В .env не найдено или пусто: " + key);
        }
        return value;
    }
}