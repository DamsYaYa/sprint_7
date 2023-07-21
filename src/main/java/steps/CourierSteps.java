package steps;

import io.qameta.allure.Step;
import courier.Courier;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

import static constant.Constant.COURIER_PATH;
import static constant.Constant.COURIER_LOGIN_PATH;


public class CourierSteps {
    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Удаление созданного курьера")
    public static void deleteCourier() {
        Courier login = new Courier(Courier.getRandomCourier().getLogin(), Courier.getRandomCourier().getPassword());
        Response response =
                given()
                        .log().all()
                        .header("Content-type", "application/json")
                        .and()
                        .body(login)
                        .when()
                        .post(COURIER_LOGIN_PATH);
        String id = response.jsonPath().getString("id");
        given()
                .log().all()
                .when()
                .delete(COURIER_PATH+"/" + id)
                .then().log().all();
    }

    @Step("Авторизация курьером")
    public ValidatableResponse loginWithCourier(Courier courier) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(courier).
                when().
                post(COURIER_LOGIN_PATH).
                then().log().all();
    }
}
