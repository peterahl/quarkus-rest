package org.acme.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GamesResourceTest {

    // @Test
    // public void testHelloEndpoint() {
    //     given()
    //       .when().get("/games")
    //       .then()
    //          .statusCode(200)
    //          .body(is("[{\"likes\":0,\"name\":\"Fortnite\"}]"));
    // }

}
