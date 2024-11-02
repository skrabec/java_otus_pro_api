package orders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;

import dto.OrderDto;
import org.junit.jupiter.api.Test;
import services.StoreApi;

public class CreateAnOrderNegariveScenariosTest {
    private StoreApi storeApi = new StoreApi();

    /*
     * this test checks response to empty body (Negative scenario)
     * */

    @Test
    public void createOrderWithEmptyBody() {

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
