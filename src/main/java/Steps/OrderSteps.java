package Steps;

import Order.Order;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static Constant.Constant.ORDER_CANCEL_PATH;
import static Constant.Constant.CREATE_ORDER_PATH;
import static io.restassured.RestAssured.given;

public class OrderSteps {
    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(CREATE_ORDER_PATH)
                .then().log().all();
    }

    @Step("Получение списка всех заказов")
    public ValidatableResponse getOrders() {
        return given()
                .log().all()
                .when()
                .get(CREATE_ORDER_PATH)
                .then().log().all();
    }

    @Step("Отмена заказа по трек-номеру '{trackNumber}'")
    public ValidatableResponse cancelOrder(int trackNumber) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .queryParam("track", trackNumber).
                when().
                put(ORDER_CANCEL_PATH).
                then().log().all();
    }
}
