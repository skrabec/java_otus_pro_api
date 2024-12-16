package services;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class HttpHelper {
    WireMockServer wireMockServer = new WireMockServer(8080);

    @BeforeEach
    public void startMocks() {

        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo("/user/get/1"))
            .willReturn(aResponse()
                .withHeader("Content-type", "application/json")
                .withBodyFile("getScoreResponse.json")
                .withStatus(200)));

        wireMockServer.stubFor(get(urlEqualTo("/user/get/all"))
            .willReturn(aResponse()
                .withHeader("Content-type", "application/json")
                .withBodyFile("getUserResponse.json")
                .withStatus(200)));

        wireMockServer.stubFor(get(urlEqualTo("/courses/get/all"))
            .willReturn(aResponse()
                .withHeader("Content-type", "application/json")
                .withBodyFile("getCoursesResponse.json")
                .withStatus(200)));

    }

    @AfterEach
    public void stopMocks() {
        wireMockServer.stop();
    }
}
