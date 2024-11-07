package orders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import dto.OrderDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.StoreApi;
import java.time.LocalDate;

public class CreateOrderTest {

    private String shipDateNow = LocalDate.now().toString();
    private StoreApi storeApi = new StoreApi();
    private int createdOrderId;

    @AfterEach
    public void cleanUp(){
            if (createdOrderId != 0) {
                storeApi.deleteAnOrder(createdOrderId);
            }
        }


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

        createdOrderId = order.getId();

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
    }
}
