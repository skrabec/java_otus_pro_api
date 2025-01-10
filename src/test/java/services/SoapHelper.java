package services;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;

public class SoapHelper {
    WireMockServer wireMockServer = new WireMockServer(8080);

    @BeforeEach
    public void startMocks() throws IOException {
        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo("/soap/user/get/1"))
            .willReturn(aResponse()
                .withHeader("Content-type", "application/soap+xml")
                .withBodyFile("user_response.xml")
                .withStatus(200)));
    }

    @AfterEach
    public void stopMocks() {
        wireMockServer.stop();
    }
}
