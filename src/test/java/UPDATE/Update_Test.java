package UPDATE;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Update_Test {

    // Define base URL
    private static final String BASE_URL = "https://dummyapi.io/data/v1/";
    // Define endpoint paths
    private static final String USER_ENDPOINT = "user/";
    private static final String APP_ID = "663740a560f331ce55a47ca8";

    @Test
    public void TC401() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID

        given()
            .body("{\n" +
                "    \"title\": \"mrs\",\n" +
                "    \"firstName\": \"Josefina\",\n" +
                "    \"lastName\": \"Calvo\",\n" +
                "    \"picture\": \"https://randomuser.me/api/portraits/med/women/3.jpg\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"email\": \"josefina.calvo@example.com\",\n" +
                "    \"dateOfBirth\": \"1982-01-27T16:01:51.717Z\",\n" +
                "    \"phone\": \"976-538-478\",\n" +
                "    \"location\": {\n" +
                "        \"street\": \"8941, Calle de Argumosa\",\n" +
                "        \"city\": \"Orihuela\",\n" +
                "        \"state\": \"Ceuta\",\n" +
                "        \"country\": \"Spain\",\n" +
                "        \"timezone\": \"0:00\"\n" +
                "    }\n" +
                "}")
            .header("app-id", "")
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_MISSING"));
    }

    @Test
    public void TC402() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = "6635747e0de1363eba89ff1g"; // Nonexistent app ID

        given()
            .body("{\n" +
                "    \"title\": \"mrs\",\n" +
                "    \"firstName\": \"Josefina\",\n" +
                "    \"lastName\": \"Calvo\",\n" +
                "    \"picture\": \"https://randomuser.me/api/portraits/med/women/3.jpg\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"email\": \"josefina.calvo@example.com\",\n" +
                "    \"dateOfBirth\": \"1982-01-27T16:01:51.717Z\",\n" +
                "    \"phone\": \"976-538-478\",\n" +
                "    \"location\": {\n" +
                "        \"street\": \"8941, Calle de Argumosa\",\n" +
                "        \"city\": \"Orihuela\",\n" +
                "        \"state\": \"Ceuta\",\n" +
                "        \"country\": \"Spain\",\n" +
                "        \"timezone\": \"0:00\"\n" +
                "    }\n" +
                "}")
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_NOT_EXIST"));
    }

    @Test
    public void TC403() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        given()
            .body("{\n" +
                "    \"firstName\": \"Josefina\",\n" +
                "    \"lastName\": \"Calvi\"\n" +
                "}")
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("title", equalTo("mrs"))
            .body("firstName", equalTo("Josefina"))
            .body("lastName", equalTo("Calvi"));
    }

    @Test
    public void TC404() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body
        String requestBody = "{\n" +
                "    \"title\": \"mr\",\n" +
                "    \"firstName\": \"Josafina\",\n" +
                "    \"lastName\": \"Calvi\",\n" +
                "    \"picture\": \"https://randomuser.me/api/portraits/med/women/3.jpg\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"dateOfBirth\": \"1995-01-27T16:01:51.717Z\",\n" +
                "    \"phone\": \"977-538-478\",\n" +
                "    \"location\": {\n" +
                "        \"street\": \"8951, Calle de Argumosa\",\n" +
                "        \"city\": \"Orichuela\",\n" +
                "        \"state\": \"Cheuta\",\n" +
                "        \"country\": \"USA\",\n" +
                "        \"timezone\": \"1:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("title", equalTo("mr"))
            .body("firstName", equalTo("Josafina"))
            .body("lastName", equalTo("Calvi"));
    }

    @Test
    public void TC405() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body
        String requestBody = "{\n" +
                "    \"firstName\": \"Jo\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("firstName", equalTo("Jo"));
    }

    @Test
    public void TC406() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with a very long first name
        String requestBody = "{\n" +
                "    \"firstName\": \"JosefinaAbraKadabraAllaihimuSalamWassalamualaikumm\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("firstName", equalTo("JosefinaAbraKadabraAllaihimuSalamWassalamualaikumm"));
    }

    @Test
    public void TC407() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with last name only
        String requestBody = "{\n" +
                "    \"lastName\": \"Br\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("lastName", equalTo("Br"));
    }

    @Test
    public void TC408() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with a very long last name
        String requestBody = "{\n" +
                "    \"lastName\": \"BroMasBroAduhBroIcikiwirAseloleJossAhayMasAmpunMas\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("lastName", equalTo("BroMasBroAduhBroIcikiwirAseloleJossAhayMasAmpunMas"));
    }

    @Test
    public void TC409() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with title only
        String requestBody = "{\n" +
                "  \"title\" : \"mr\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("title", equalTo("mr"));
    }

    @Test
    public void TC410() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with title ms
        String requestBody = "{\n" +
                "  \"title\" : \"ms\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("title", equalTo("ms"));
    }

    @Test
    public void TC411() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with title mrs
        String requestBody = "{\n" +
                "  \"title\" : \"mrs\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("title", equalTo("mrs"));
    }

    @Test
    public void TC412() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with title Miss
        String requestBody = "{\n" +
                "  \"title\" : \"miss\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("title", equalTo("miss"))
            .body("gender", equalTo("male")) // Assuming the gender is not updated
            .body("email", equalTo("josefina.calvo@example.com")) // Assuming the email remains the same
            .body("dateOfBirth", equalTo("1995-01-27T16:01:51.717Z")); // Assuming the date of birth remains the same
    }

    @Test
    public void TC413() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with title Dr
        String requestBody = "{\n" +
                "  \"title\" : \"dr\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("title", equalTo("dr"));
    }

    @Test
    public void TC414() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with empty title
        String requestBody = "{\n" +
                "  \"title\" : \"\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("title", equalTo(""));
    }

    @Test
    public void TC415() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with gender male
        String requestBody = "{\n" +
                "  \"gender\" : \"male\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("gender", equalTo("male"));
    }

    @Test
    public void TC416() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with gender female
        String requestBody = "{\n" +
                "  \"gender\" : \"female\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("gender", equalTo("female"));
    }

    @Test
    public void TC417() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with gender "other"
        String requestBody = "{\n" +
                "  \"gender\" : \"other\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("gender", equalTo("other"));
    }

    @Test
    public void TC418() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with empty gender
        String requestBody = "{\n" +
                "  \"gender\" : \"\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("gender", equalTo(""));
    }



    
}
