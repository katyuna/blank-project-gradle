package config;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Класс для работы с конфигурацией проекта.
 * <p>
 * Загружает переменные окружения из файла `.env`.
 */
public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static final String BASE_URL = dotenv.get("BASE_URL");
    public static final String LOGIN = dotenv.get("LOGIN");
    public static final String PASSWORD = dotenv.get("PASSWORD");
}

