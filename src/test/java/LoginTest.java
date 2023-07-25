import steps.CourierSteps;
import courier.Courier;
import io.restassured.response.ValidatableResponse;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;

import static org.hamcrest.Matchers.greaterThan;
import static org.apache.http.HttpStatus.*;

@DisplayName("Позитивные тест на логин")

public class LoginTest {

    static CourierSteps courierSteps = new CourierSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI= "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Авторизация с корректным логином и паролем")
    public void loginWithCorrectLoginAndPassword() {
        courierSteps.createCourier(Courier.getRandomCourier());

        ValidatableResponse response = courierSteps.loginWithCourier(Courier.getRandomCourier());

        response.assertThat().body("id", greaterThan(0)).and().statusCode(SC_OK);
    }

    @After
    public void deleteCourier() {
        CourierSteps.deleteCourier();
    }
}
