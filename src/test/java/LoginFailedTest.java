import Steps.CourierSteps;
import Courier.Courier;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;

@DisplayName("Негативные тесты на логин")
public class LoginFailedTest {
    Courier courier;
    static CourierSteps courierSteps = new CourierSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI= "https://qa-scooter.praktikum-services.ru";
    }
    @Test
    @DisplayName("Авторизация с  несуществующим логином")
    public void testErrorMessageForIncorrectLogin() {
        courier.setLogin("vader");
        ValidatableResponse response = courierSteps.loginWithCourier(courier);
        response.assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Авторизация с пустым логином")
    public void testErrorMessageForNotFoundLogin() {
        courier.setLogin(null);
        ValidatableResponse response = courierSteps.loginWithCourier(courier);
        response.assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    public void testErrorMessageForIncorrectPassword() {
        courier.setPassword("hghf");
        ValidatableResponse response = courierSteps.loginWithCourier(courier);
        response.assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Авторизация с пустым паролем")
    public void testErrorMessageForNotFoundPassword() {
        courier.setPassword("");
        ValidatableResponse response = courierSteps.loginWithCourier(courier);
        response.assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(SC_NOT_FOUND);
    }
}