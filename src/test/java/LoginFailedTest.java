import io.qameta.allure.Description;
import org.junit.After;
import steps.CourierSteps;
import courier.Courier;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.apache.http.HttpStatus.*;

@DisplayName("Негативные тесты на логин")

public class LoginFailedTest {
    private Courier courier;
    private CourierSteps courierSteps;
    private int courierId;

    @Before
    public void setUp() {
        RestAssured.baseURI= "https://qa-scooter.praktikum-services.ru";
        courier = Courier.getRandomCourier();
        courierSteps = new CourierSteps();
        courierSteps.createCourier(courier);
    }

    @Test
    @DisplayName("Авторизация с  несуществующим логином")
    @Description("Базовый тест с запросом к /api/v1/courier/login")
    public void testErrorMessageForIncorrectLogin() {
        ValidatableResponse loginResponse = courierSteps.loginWithCourier(new Courier("labla", courier.getPassword()));
        courierId = courierSteps.loginWithCourier(Courier.from(courier)).extract().path("id");

        int statusCode = loginResponse.extract().statusCode();
        assertEquals(SC_NOT_FOUND, statusCode);

        String bodyAnswer = loginResponse.extract().path("message");
        assertEquals("Учетная запись не найдена", bodyAnswer);
    }

    @Test
    @DisplayName("Авторизация с пустым логином")
    @Description("Базовый тест с запросом к /api/v1/courier/login")
    public void courierEmptyLoginTest() {
        ValidatableResponse loginResponse = courierSteps.loginWithCourier(new Courier(null, courier.getPassword()));
        courierId = courierSteps.loginWithCourier(Courier.from(courier)).extract().path("id");

        int statusCode = loginResponse.extract().statusCode();
        assertEquals(SC_BAD_REQUEST, statusCode);

        String bodyAnswer = loginResponse.extract().path("message");
        assertEquals("Недостаточно данных для входа", bodyAnswer);
    }


    @Test
    @DisplayName("Авторизация с  несуществующим паролем")
    @Description("Базовый тест с запросом к /api/v1/courier/password")
    public void testErrorMessageForIncorrectPassword() {
        ValidatableResponse loginResponse = courierSteps.loginWithCourier(new Courier(courier.getLogin(), "qwerty"));
        courierId = courierSteps.loginWithCourier(Courier.from(courier)).extract().path("id");

        int statusCode = loginResponse.extract().statusCode();
        assertEquals(SC_NOT_FOUND, statusCode);

        String bodyAnswer = loginResponse.extract().path("message");
        assertEquals("Учетная запись не найдена", bodyAnswer);
    }


    @Test
    @DisplayName("Авторизация несуществующего курьера")
    @Description("Базовый тест с запросом к /api/v1/courier/login")
    public void testErrorMessageForEmptyPassword() {
        ValidatableResponse loginResponse = courierSteps.loginWithCourier(new Courier("Creature", "Tupik"));
        courierId = courierSteps.loginWithCourier(Courier.from(courier)).extract().path("id");

        int statusCode = loginResponse.extract().statusCode();
        assertEquals(SC_NOT_FOUND, statusCode);

        String bodyAnswer = loginResponse.extract().path("message");
        assertEquals("Учетная запись не найдена", bodyAnswer);
    }

    @After
    public void deleteCourier() {
        CourierSteps.deleteCourier();
    }
}