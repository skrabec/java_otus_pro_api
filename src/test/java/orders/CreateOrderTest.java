package orders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

import dto.OrderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.StoreApi;
import java.time.LocalDate;

public class CreateOrderTest {

    String shipDateNow = LocalDate.now().toString();
    private StoreApi storeApi = new StoreApi();

    /*
    * this test checks valid order creation (happy path)
    * */

    @Test
    public void createValidOrder(){

        OrderDto order = OrderDto
            .builder()
            .id(1)
            .petId(1)
            .quantity(1)
            .shipDate(shipDateNow)
            .status("completed")
            .completeStatus(true)
            .build();

        storeApi.createOrder(order)
            .statusCode(200)
            .body("id", equalTo(1))
            .body("petId", equalTo(1))
            .body("quantity", equalTo(1))
            .body("shipDate", startsWith(shipDateNow))
            .body("status", equalTo("completed"));
    }

    /*
     * this test checks response to empty body (Negative scenario)
     * */

    @Test
    public void createOrderWithEmptyBody(){

        OrderDto order = OrderDto
            .builder()
            .build();

        storeApi.createOrder(order)
            .statusCode(200)
            .body("id", notNullValue())
            .body("petId", equalTo(0))
            .body("quantity", equalTo(0))
            .body("shipDate", isEmptyOrNullString())
            .body("status", isEmptyOrNullString());
    }
}
