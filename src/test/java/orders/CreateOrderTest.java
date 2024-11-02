package orders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import dto.OrderDto;
import org.junit.jupiter.api.Test;
import services.StoreApi;
import java.time.LocalDate;

public class CreateOrderTest {

    private String shipDateNow = LocalDate.now().toString();
    private StoreApi storeApi = new StoreApi();

    /*
     * this test checks valid order creation (happy path)
     * */

    @Test
    public void createValidOrder() {

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

        storeApi.checkCreatedOrder(order.getId(), order)
            .statusCode(200)
            .body("id", equalTo(order.getId()))
            .body("petId", equalTo(order.getPetId()))
            .body("quantity", equalTo(order.getQuantity()))
            .body("shipDate", startsWith(order.getShipDate()))
            .body("status", equalTo(order.getStatus()));

        storeApi.deleteAnOrder(order.getId())
            .statusCode(200)
            .body("code", equalTo(200))
            .body("type", equalTo("unknown"))
            .body("message", equalTo("1"));

        storeApi.checkCreatedOrder(order.getId(), order)
            .statusCode(404);
    }


}
