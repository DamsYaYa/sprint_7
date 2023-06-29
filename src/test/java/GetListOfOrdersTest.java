import Steps.OrderSteps;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static java.util.function.Predicate.not;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.emptyArray;

@DisplayName("Тесты на получение списка заказов")
public class GetListOfOrdersTest {

    private final OrderSteps order = new OrderSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void GetOrdersTest() {
        ValidatableResponse response = order.getOrders();
        response.assertThat().body("orders", (emptyArray())).and().statusCode(SC_CREATED);
    }
}