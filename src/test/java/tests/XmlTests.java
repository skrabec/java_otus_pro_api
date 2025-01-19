package tests;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import services.SoapHelper;

@Feature("XML tests")
public class XmlTests extends SoapHelper {

    @Description("Get users test")
    @Test
    public void getUsersTest() {
        Response response = given()
            .header("Accept", "application/xml") // Specify that we expect XML
            .when()
            .get("http://localhost:8282/soap/user/get/1") // Replace with your API's endpoint
            .then()
            .statusCode(200) // Assert that the status code is 200
            .extract()
            .response();

        response.then()
            .body("Envelope.Body.GetUserResponse.User.Name", equalTo("Test user"))
            .body("Envelope.Body.GetUserResponse.User.Course", equalTo("QA"))
            .body("Envelope.Body.GetUserResponse.User.Email", equalTo("test@test.test"))
            .body("Envelope.Body.GetUserResponse.User.Age", equalTo("23"));
    }
}
