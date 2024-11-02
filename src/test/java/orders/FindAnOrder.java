package orders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import services.StoreApi;

public class FindAnOrder {

    private StoreApi storeApi = new StoreApi();

    /*
     * this test checks an order search by valid id (Happy path)
     * */

    @Test
    public void searchAnOrderById() {

        storeApi.findAnOrder(1)
            .statusCode(200)
            .body("id", equalTo(1))
            .body("petId", notNullValue())
            .body("quantity", notNullValue())
            .body("shipDate", notNullValue())
            .body("status", notNullValue());
    }


}
