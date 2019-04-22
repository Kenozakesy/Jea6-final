package GroupControllerTest;

import domain.server.Server;
import domain.server.ServerDTO;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServerControllerTest {
    private static Server createdServer = null;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost/war/api";
        RestAssured.port = 8080;
    }

    @Test
    public void basicPingTest() {
        given()
                .when().get("/server/ping").then().statusCode(200).body(equalTo("welkom bij servers"));
    }

    @Test
    public void createServer() {
        ServerDTO serverDTO = new ServerDTO();
        serverDTO.setName("name");
        createdServer = given()
                .contentType("application/json")
                .body(serverDTO)
                .when().post("/server").then()
                .statusCode(200)
                .extract().response().as(Server.class);
        System.out.println("Created domain.server: " + createdServer.toString());
    }

    @Test
    public void getServers() {
        Server[] server = given()
                .when().get("/server").then()
                .statusCode(200).extract().response().as(Server[].class);
        System.out.println("Number of Server: " + server.length);
        System.out.println(server[0].toString());
    }

    @Test
    public void getCreatedServer() {
        Server servers = given()
                .when().get("/server/" + createdServer.getId()).then()
                .body("name",equalTo("name"))
                .statusCode(200).extract().response().as(Server.class);
        System.out.println("Created server: " + servers.toString());
    }

    @AfterClass
    public static void deleteServer() {
        if (createdServer != null) {
            System.out.println("//Server tests: clean-up");
            System.out.println("server.id to delete: " + createdServer.getId());
            given()
                    .when().delete("/server/" + createdServer.getId()).then()
                    .statusCode(200);
        }
    }

}
