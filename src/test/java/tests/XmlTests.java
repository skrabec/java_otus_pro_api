package tests;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import dto.UserDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import services.CoursesApi;
import services.SoapHelper;
import java.util.List;

public class XmlTests extends SoapHelper {
    private CoursesApi coursesApi = new CoursesApi();

    @Test
    public void getUsersTest() {
        Response response = given()
            .header("Accept", "application/xml") // Specify that we expect XML
            .when()
            .get("http://localhost:8080/soap/user/get/1") // Replace with your API's endpoint
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
