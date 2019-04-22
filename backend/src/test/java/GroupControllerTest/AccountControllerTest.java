package GroupControllerTest;

import domain.accoun.Account;
import domain.accoun.AccountDTO;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountControllerTest {
    private static Account createdAccount = null;

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
    public void createAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("name");
        accountDTO.setPassword("password");
        createdAccount = given()
                .contentType("application/json")
                .body(accountDTO)
                .when().post("/account").then()
                .statusCode(200)
                .extract().response().as(Account.class);
        System.out.println("Created domain.account: " + createdAccount.toString());
    }

    @Test
    public void getAccounts() {
        Account[] account = given()
                .when().get("/account").then()
                .statusCode(200).extract().response().as(Account[].class);
        System.out.println("Number of account: " + account.length);
        System.out.println(account[0].toString());
    }

    @Test
    public void getCreatedAccount() {
        Account account = given()
                .when().get("/account/" + createdAccount.getId()).then()
                .body("name",equalTo("name"))
                .body("password",equalTo("password"))
                .statusCode(200).extract().response().as(Account.class);
        System.out.println("Created account: " + account.toString());
    }

    @AfterClass
    public static void deleteAccount() {
        if (createdAccount != null) {
            System.out.println("//account tests: clean-up");
            System.out.println("account.id to delete: " + createdAccount.getId());
            given()
                    .when().delete("/account/" + createdAccount.getId()).then()
                    .statusCode(200);
        }
    }
}
