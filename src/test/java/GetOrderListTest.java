import steps.OrderSteps;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.allOf;

@DisplayName("Тесты на получение списка заказов")
public class GetOrderListTest {

    private final OrderSteps order = new OrderSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrderListTest() {
        ValidatableResponse response = order.getOrders();
        response.assertThat().body("orders", (allOf())).and().statusCode(SC_OK);
    }
}