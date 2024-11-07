package orders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;

import dto.OrderDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import services.StoreApi;

public class CreateAnOrderNegariveScenariosTest {
    private StoreApi storeApi = new StoreApi();
    private int createdOrderId;


    @AfterEach
    public void cleanUp(){
        if (createdOrderId != 0) {
            storeApi.deleteAnOrder(createdOrderId);
        }
    }

    /*
     * this test checks response to empty body (Negative scenario)
     * */

    @Test
    public void createOrderWithEmptyBody() {

        OrderDto order = OrderDto
            .builder()
            .build();

        createdOrderId = storeApi.createOrder(order)
            .statusCode(200)
            .body("id", notNullValue())
            .body("petId", equalTo(0))
            .body("quantity", equalTo(0))
            .body("shipDate", isEmptyOrNullString())
            .body("status", isEmptyOrNullString())
            .extract().path("id");
    }
}
