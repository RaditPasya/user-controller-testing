package DELETE;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Delete_Test {

    // Define base URL
    private static final String BASE_URL = "https://dummyapi.io/data/v1/";
    // Define endpoint paths
    private static final String USER_ENDPOINT = "user/";
    private static final String APP_ID = "663799321319330c7fc3842e";

    // Test case 1-01
    @Test
    public void TC201() {
        given()
            .delete(BASE_URL + USER_ENDPOINT + "60d0fe4f5311236168a109fb")
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_MISSING"));
    }

    // Test case 1-02
    @Test
    public void TC202() {
        given()
            .delete(BASE_URL + USER_ENDPOINT + "60d0fe4f5311236168a109fbi")
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_MISSING"));
    }

    // Test case 1-03
    @Test
    public void TC203() {
        given()
            .header("app-id", "6635747e0de1363eba89ff1g")
            .delete(BASE_URL + USER_ENDPOINT + "60d0fe4f5311236168a109fb")
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_NOT_EXIST"));
    }

    // Test case 1-04
    @Test
    public void TC204() {
        given()
            .header("app-id", "6635747e0de1363eba89ff1g")
            .delete(BASE_URL + USER_ENDPOINT + "60d0fe4f5311236168a109fbi")
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_NOT_EXIST"));
    }

    // Test case 1-05
    @Test
    public void TC205() {
        given()
            .header("app-id", APP_ID)
            .delete(BASE_URL + USER_ENDPOINT + "60d0fe4f5311236168a109fb")
        .then()
            .statusCode(200)
            .body("id", equalTo("60d0fe4f5311236168a109fb"));
    }

    // Test case 1-06
    @Test
    public void TC206() {
        given()
            .header("app-id", APP_ID)
            .delete(BASE_URL + USER_ENDPOINT + "60d0fe4f5311236168a109f1")
        .then()
            .statusCode(404)
            .body("error", equalTo("RESOURCE_NOT_FOUND"));
    }

    // Test case 1-07
    @Test
    public void TC207() {
        given()
            .header("app-id", APP_ID)
            .delete(BASE_URL + USER_ENDPOINT + "0")
        .then()
            .statusCode(400)
            .body("error", equalTo("PARAMS_NOT_VALID"));
    }
}
    

