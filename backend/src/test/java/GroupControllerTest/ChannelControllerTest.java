package GroupControllerTest;

import domain.channel.Channel;
import domain.channel.ChannelDTO;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChannelControllerTest {
    private static Channel createdChannel = null;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost/war/api";
        RestAssured.port = 8080;
    }

    @Test
    public void basicPingTest() {
        given()
                .when().get("/account/ping").then().statusCode(200).body(equalTo("welkom bij accounts"));
    }

    @Test
    public void createChannel() {
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setName("name");
        createdChannel = given()
                .contentType("application/json")
                .body(channelDTO)
                .when().post("/channel").then()
                .statusCode(200)
                .extract().response().as(Channel.class);
        System.out.println("Created domain.channel: " + createdChannel.toString());
    }

    @Test
    public void getChannels() {
        Channel[] account = given()
                .when().get("/channel").then()
                .statusCode(200).extract().response().as(Channel[].class);
        System.out.println("Number of channel: " + account.length);
        System.out.println(account[0].toString());
    }

    @Test
    public void getCreatedChannel() {
        Channel channel = given()
                .when().get("/channel/" + createdChannel.getId()).then()
                .body("name",equalTo("name"))
                .statusCode(200).extract().response().as(Channel.class);
        System.out.println("Created channel: " + channel.toString());
    }

    @AfterClass
    public static void deleteAccount() {
        if (createdChannel != null) {
            System.out.println("//channel tests: clean-up");
            System.out.println("channel.id to delete: " + createdChannel.getId());
            given()
                    .when().delete("/channel/" + createdChannel.getId()).then()
                    .statusCode(200);
        }
    }
}
