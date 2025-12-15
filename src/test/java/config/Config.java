package config;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Класс для работы с конфигурацией проекта.
 * <p>
 * Загружает переменные окружения из файла `.env`.
 */
public class Config {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static String mustGet(String key) {
        String value = dotenv.get(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("В .env не найдено или пусто: " + key);
        }
        return value;
    }

    public static String get(String key, String defaultValue) {
        String value = dotenv.get(key);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }
}


