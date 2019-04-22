package GroupControllerTest;

import domain.post.Post;
import domain.post.PostDTO;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostControllerTest {
    private static Post createdPost = null;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost/war/api";
        RestAssured.port = 8080;
    }

    @Test
    public void basicPingTest() {
        given()
                .when().get("/post/ping").then().statusCode(200).body(equalTo("welkom bij posts"));
    }

    @Test
    public void createPosts() {
        PostDTO postDTO = new PostDTO();
        postDTO.setMessage("message");
        createdPost = given()
                .contentType("application/json")
                .body(postDTO)
                .when().post("/post").then()
                .statusCode(200)
                .extract().response().as(Post.class);
        System.out.println("Created domain.post: " + createdPost.toString());
    }

    @Test
    public void getPosts() {
        Post[] posts = given()
                .when().get("/post").then()
                .statusCode(200).extract().response().as(Post[].class);
        System.out.println("Number of post: " + posts.length);
        System.out.println(posts[0].toString());
    }

    @Test
    public void getCreatedPost() {
        Post post = given()
                .when().get("/post/" + createdPost.getId()).then()
                .body("message",equalTo("message"))
                .statusCode(200).extract().response().as(Post.class);
        System.out.println("Created post: " + post.toString());
    }

    @AfterClass
    public static void deletePost() {
        if (createdPost != null) {
            System.out.println("//post tests: clean-up");
            System.out.println("post.id to delete: " + createdPost.getId());
            given()
                    .when().delete("/post/" + createdPost.getId()).then()
                    .statusCode(200);
        }
    }
}
