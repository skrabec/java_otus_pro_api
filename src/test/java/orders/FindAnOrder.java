package orders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.StoreApi;

public class FindAnOrder {

    private StoreApi storeApi = new StoreApi();

    @Test
    @DisplayName("Happy path. Search an order by valid id")
    public void searchAnOrderById() {

        storeApi.findAnOrder(1)
            .statusCode(200)
            .body("id", equalTo(1))
            .body("petId", notNullValue())
            .body("quantity", notNullValue())
            .body("shipDate", notNullValue())
            .body("status", notNullValue());
    }

    @Test
    @DisplayName("Negative scenario. Id equals -1")
    public void searchAnOrderByNegativeId() {

        storeApi.findAnOrder(-1)
            .statusCode(404)
            .body("code", equalTo(1))
            .body("type", equalTo("error"))
            .body("message", equalTo("Order not found"));
    }
}
