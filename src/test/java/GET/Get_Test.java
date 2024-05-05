package GET;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get_Test {
    
    // Define base URL
    private static final String BASE_URL = "https://dummyapi.io/data/v1/";
    // Define endpoint paths
    private static final String USER_ENDPOINT = "user/";
    private static final String POST_ENDPOINT = "post/";

    // Global APP_ID variable
    private static final String APP_ID = "663740a560f331ce55a47ca8"; // Change this to your valid app ID

    @Test
    public void TC101() {
        String userId = "60d0fe4f5311236168a109fb"; // Valid user ID

        given()
            .header("app-id", APP_ID)
        .when()
            .get(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_MISSING"));
    }

    @Test
    public void TC102() {
        String userId = "60d0fe4f5311236168a109fbi"; // Invalid user ID

        given()
            .header("app-id", APP_ID)
        .when()
            .get(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_MISSING"));
    }

    @Test
    public void TC103() {
        String userId = "60d0fe4f5311236168a109fb"; // Valid user ID

        given()
            .header("app-id", "NonexistentAppID")
        .when()
            .get(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_NOT_EXIST"));
    }

    @Test
    public void TC104() {
        String userId = "60d0fe4f5311236168a109fbi"; // Invalid user ID

        given()
            .header("app-id", "NonexistentAppID")
        .when()
            .get(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_NOT_EXIST"));
    }

    @Test
    public void TC105() {
        String userId = "60d0fe4f5311236168a109fb"; // Valid user ID

        given()
            .header("app-id", APP_ID)
        .when()
            .get(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("title", equalTo("mr"))
            .body("firstName", equalTo("Sohan"));
            // Add more assertions as needed
    }

    @Test
    public void TC106() {
        String userId = "60d0fe4f5311236168a109f1"; // Nonexistent user ID

        given()
            .header("app-id", APP_ID)
        .when()
            .get(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(404)
            .body("error", equalTo("RESOURCE_NOT_FOUND"));
    }

    @Test
    public void TC107() {
        String userId = "0"; // Invalid user ID

        given()
            .header("app-id", APP_ID)
        .when()
            .get(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("PARAMS_NOT_VALID"));
    }
}
