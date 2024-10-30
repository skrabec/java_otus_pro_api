package orders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.DisplayName;
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

    /*
     * this test checks an order search by negative id (Negative scenario)
     * */

    @Test
    public void searchAnOrderByNegativeId() {

        storeApi.findAnOrder(-1)
            .statusCode(404)
            .body("code", equalTo(1))
            .body("type", equalTo("error"))
            .body("message", equalTo("Order not found"));
    }
}
