package orders;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import services.StoreApi;

public class FindAnOrderNegativeScenariosTest {
    private StoreApi storeApi = new StoreApi();

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
