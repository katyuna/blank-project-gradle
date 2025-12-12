package smoke;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;

@ExtendWith(AllureJunit5.class)
@Tag("SMOKE")
class GradleSmokeTest {

    @Test
    @Description("Smoke: Gradle + JUnit5 + RestAssured + Allure работают")
    void gradleInfrastructureShouldWork() {
        configureRestAssured();
        sendRequest();
    }

    @Step("Настройка RestAssured")
    void configureRestAssured() {
        RestAssured.baseURI = "https://httpbin.org";
    }

    @Step("Отправка GET запроса и проверка ответа 200")
    void sendRequest() {
        given()
                .when()
                .get("/status/200")
                .then()
                .statusCode(200);
    }
}
