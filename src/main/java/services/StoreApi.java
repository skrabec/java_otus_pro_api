package services;

import static io.restassured.RestAssured.given;

import dto.OrderDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

public class StoreApi {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/";
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public StoreApi(){
        requestSpecification = given()
            .baseUri(BASE_URI)
            .contentType(ContentType.JSON)
            .log().all();

        responseSpecification = RestAssured.expect();
        responseSpecification.contentType(ContentType.JSON);
        responseSpecification.time(Matchers.lessThan(5000L));
    }

    public ValidatableResponse createOrder(OrderDto storeOrderDto) {

        return given(requestSpecification)
            .basePath("store/order")
            .body(storeOrderDto)
        .when()
            .post()
        .then()
            .spec(responseSpecification);
    }

    public ValidatableResponse findAnOrder(int orderId) {
        return given(requestSpecification)
            .basePath("store/order/" + orderId)
        .when()
            .get()
        .then()
            .spec(responseSpecification)
            .log().all();
    }
}
