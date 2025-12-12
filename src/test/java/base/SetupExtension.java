package base;

/*
 * Это шаблон для расширения, которое делает всё один раз на весь прогон.
 * Реализует BeforeAllCallback — значит, JUnit вызовет его до всех тестов.
 * Реализует CloseableResource — значит, JUnit вызовет close() после всех тестов.
 */

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public abstract class SetupExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    /**
     * JUnit при запуске тестов создаёт ExtensionContext - объект,
     * который хранит всё, что нужно знать о текущем запуске:
     * - какой тестовый класс сейчас выполняется,
     * - какой метод тестируется,
     * - доступ к специальным Store — "ящикам для хранения данных".
     * @param context
     * @throws Exception
     */

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        // Создаем уникальный ключ расширения.
        String uniqueKey = this.getClass().getName();

        Object value = context.getRoot().getStore(GLOBAL).get(uniqueKey);
        if (value == null) {
            context.getRoot().getStore(GLOBAL).put(uniqueKey, this);
            setup();
        }
    }

    // Обратный вызов, который вызывается ровно один раз
    // перед началом всех тестовых контейнеров.
    abstract void setup() throws SQLException;

    // Обратный вызов, который вызывается ровно один раз
    // после окончания всех тестовых контейнеров.
    // Унаследовано от {@code CloseableResource}
    public abstract void close() throws Throwable;
}
