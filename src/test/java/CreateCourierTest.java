import Steps.CourierSteps;
import Courier.Courier;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.core.IsEqual.equalTo;

@DisplayName("Тесты на создание курьера")
public class CreateCourierTest {

    CourierSteps courierSteps = new CourierSteps();

    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI= "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Создание курьера со всеми валидными полями")
    public void checkItIsAbleToCreateNewCourierWithFullValidData(){

        ValidatableResponse response = courierSteps.createCourier(Courier.getRandomCourier());

        response.assertThat().body("ok",is(true)).and().statusCode(201);
    }

    @Test
    @DisplayName("Создание курьера без поля firstName")
    public void checkItIsNotAbleToCreateNewCourierWithoutFirstName(){

        courier = Courier.getRandomCourier();

        courier.setFirstName(null);

        ValidatableResponse response = courierSteps.createCourier(courier);

        response.assertThat().body("ok",is(true)).and().statusCode(201);
    }

    @Test
    @DisplayName("Создание курьера без поля password")
    public void checkItIsNotAbleToCreateNewCourierWithoutPassword() {
        courier = Courier.getRandomCourier();

        courier.setPassword(null);

        ValidatableResponse response = courierSteps.createCourier(courier);

        response.assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")).and().statusCode(400);
    }

    @Test
    @DisplayName("Создание курьера без поля login")
    public void checkItIsNotAbleToCreateNewCourierWithoutLogin() {
        courier = Courier.getRandomCourier();

        courier.setLogin(null);

        ValidatableResponse response = courierSteps.createCourier(courier);

        response.assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")).and().statusCode(400);
    }

    @Test
    @DisplayName("Создание курьера с полем login который уже есть в системе")
    public void courierSameLoginFailTest() {

        courierSteps.createCourier(Courier.getRandomCourier());

        ValidatableResponse response = courierSteps.createCourier(Courier.getRandomCourier());

        response.assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой.")).and().statusCode(409);

    }

    @After
    public void deleteCourier() {
        CourierSteps.deleteCourier();
    }
}