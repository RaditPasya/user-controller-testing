package CREATE;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Create_Test {

    // Define base URL
    private static final String BASE_URL = "https://dummyapi.io/data/v1/";
    // Define endpoint paths
    private static final String USER_ENDPOINT = "user/";
    private static final String APP_ID = "663740a560f331ce55a47ca8";

    @Test
    public void TC301() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"Azis\",\n" +
                "  \"lastName\" : \"Suro\",\n" +
                "  \"email\": \"azis@examples.com\"\n" +
                "}";

        given()
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_MISSING"));
    }

    @Test
    public void TC302() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"Azis\",\n" +
                "  \"lastName\" : \"Suro\",\n" +
                "  \"email\": \"azis@examples.com\"\n" +
                "}";

        given()
            .header("app-id", "663679b53fb5f584f811")
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_NOT_EXIST"));
    }

    // Test case 1-3
    @Test
    public void TC303() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"Azis\",\n" +
                "  \"lastName\" : \"Suro\",\n" +
                "  \"email\": \"zis@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(200)
            .body("firstName", equalTo("Azis"))
            .body("lastName", equalTo("Suro"))
            .body("email", equalTo("zis@examples.com"));
    }

    // Test case 1-4
    @Test
    public void TC304() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"ya\",\n" +
                "  \"lastName\" : \"biya\",\n" +
                "  \"email\": \"biya@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(200)
            .body("firstName", equalTo("ya"))
            .body("lastName", equalTo("biya"))
            .body("email", equalTo("biya@examples.com"));
    }

    // Test case 1-5
    @Test
    public void TC305() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"Pneumonoultramicroscopicsilicovolcanoconiosis\",\n" +
                "  \"lastName\" : \"bokir\",\n" +
                "  \"email\": \"bokir@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(200)
            .body("firstName", equalTo("Pneumonoultramicroscopicsilicovolcanoconiosis"))
            .body("lastName", equalTo("bokir"))
            .body("email", equalTo("bokir@examples.com"));
    }

    // Test case 1-6
    @Test
    public void TC306() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"apoy\",\n" +
                "  \"lastName\" : \"jb\",\n" +
                "  \"email\": \"poypoy@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(200)
            .body("firstName", equalTo("apoy"))
            .body("lastName", equalTo("jb"))
            .body("email", equalTo("poypoy@examples.com"));
    }

    @Test
    public void TC307() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"jek\",\n" +
                "  \"lastName\" : \"Pneumonoultramicroscopicsilicovolcanoconiosis\",\n" +
                "  \"email\": \"poypoy@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(200);
    }

    // Test case 1-8
    @Test
    public void TC308() {
        String requestBody = "{\n" +
                "  \"title\" : \"Mr\",\n" +
                "  \"firstName\": \"Ahmad\",\n" +
                "  \"lastName\": \"Surya\",\n" +
                "  \"gender\": \"male\",\n" +
                "  \"email\": \"ahmad.surya@example.com\",\n" +
                "  \"dateOfBirth\": \"1988-10-15\",\n" +
                "  \"phone\": \"+6281234567890\",\n" +
                "  \"picture\": \"https://contoh.com/gambar_profil.jpg\",\n" +
                "  \"location\": {\n" +
                "    \"street\": \"Jl. Merdeka No. 10\",\n" +
                "    \"city\": \"Bandung\",\n" +
                "    \"state\": \"Jawa Barat\",\n" +
                "    \"country\": \"Indonesia\",\n" +
                "    \"timezone\": \"WIB\"\n" +
                "  }\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(200);
    }

    // Test case 1-9
    @Test
    public void TC309() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"Azis\",\n" +
                "  \"lastName\" : \"Suro\",\n" +
                "  \"email\": \"biya@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-10
    @Test
    public void TC310() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"\",\n" +
                "  \"lastName\" : \"Suro\",\n" +
                "  \"email\": \"suro@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-11
    @Test
    public void TC311() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"Azis\",\n" +
                "  \"lastName\" : \"\",\n" +
                "  \"email\": \"suro@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-12
    @Test
    public void TC312() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"Azis\",\n" +
                "  \"lastName\" : \"suro\",\n" +
                "  \"email\": \"\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-13
    @Test
    public void TC313() {
        given()
            .header("app-id", APP_ID)
            .body("")
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-14
    @Test
    public void TC314() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"Azis\",\n" +
                "  \"lastName\" : \"sr\",\n" +
                "  \"email\": \"suroexamples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-15
    @Test
    public void TC315() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"Azis\",\n" +
                "  \"lastName\" : \"sr\",\n" +
                "  \"email\": \"suro@\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    @Test
    public void TC316() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"a\",\n" +
                "  \"lastName\" : \"suro\",\n" +
                "  \"email\": \"suro@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-17
    @Test
    public void TC317() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"aqwerqwerqwerqwerqwerqqqtetqwrqw\",\n" +
                "  \"lastName\" : \"suro\",\n" +
                "  \"email\": \"suro@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-18
    @Test
    public void TC318() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"azis\",\n" +
                "  \"lastName\" : \"s\",\n" +
                "  \"email\": \"suro@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-19
    @Test
    public void TC319() {
        String requestBody = "{\n" +
                "  \"firstName\" : \"azis\",\n" +
                "  \"lastName\" : \"sasddfgghjjklzxcdgherawdfghjwetdfgasd\",\n" +
                "  \"email\": \"suro@examples.com\"\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-20
    @Test
    public void TC320() {
        String requestBody = "{\n" +
                "  \"title\" : \"21\",\n" +
                "  \"firstName\": \"Ahmad\",\n" +
                "  \"lastName\": \"Surya\",\n" +
                "  \"gender\": \"male\",\n" +
                "  \"email\": \"ahmad.surya@example.com\",\n" +
                "  \"dateOfBirth\": \"1988-10-15\",\n" +
                "  \"phone\": \"+6281234567890\",\n" +
                "  \"picture\": \"https://contoh.com/gambar_profil.jpg\",\n" +
                "  \"location\": {\n" +
                "    \"street\": \"Jl. Merdeka No. 10\",\n" +
                "    \"city\": \"Bandung\",\n" +
                "    \"state\": \"Jawa Barat\",\n" +
                "    \"country\": \"Indonesia\",\n" +
                "    \"timezone\": \"WIB\"\n" +
                "  }\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-21
    @Test
    public void TC321() {
        String requestBody = "{\n" +
                "  \"title\" : \"bro\",\n" +
                "  \"firstName\": \"Ahmad\",\n" +
                "  \"lastName\": \"Surya\",\n" +
                "  \"gender\": \"male\",\n" +
                "  \"email\": \"ahmad.surya@example.com\",\n" +
                "  \"dateOfBirth\": \"1988-10-15\",\n" +
                "  \"phone\": \"+6281234567890\",\n" +
                "  \"picture\": \"https://contoh.com/gambar_profil.jpg\",\n" +
                "  \"location\": {\n" +
                "    \"street\": \"Jl. Merdeka No. 10\",\n" +
                "    \"city\": \"Bandung\",\n" +
                "    \"state\": \"Jawa Barat\",\n" +
                "    \"country\": \"Indonesia\",\n" +
                "    \"timezone\": \"WIB\"\n" +
                "  }\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }

    // Test case 1-22
    @Test
    public void TC322() {
        String requestBody = "{\n" +
                "  \"title\" : \"mr\",\n" +
                "  \"firstName\": \"4215\",\n" +
                "  \"lastName\": \"Surya\",\n" +
                "  \"gender\": \"male\",\n" +
                "  \"email\": \"ahmad.surya@example.com\",\n" +
                "  \"dateOfBirth\": \"1988-10-15\",\n" +
                "  \"phone\": \"+6281234567890\",\n" +
                "  \"picture\": \"https://contoh.com/gambar_profil.jpg\",\n" +
                "  \"location\": {\n" +
                "    \"street\": \"Jl. Merdeka No. 10\",\n" +
                "    \"city\": \"Bandung\",\n" +
                "    \"state\": \"Jawa Barat\",\n" +
                "    \"country\": \"Indonesia\",\n" +
                "    \"timezone\": \"WIB\"\n" +
                "  }\n" +
                "}";

        given()
            .header("app-id", APP_ID)
            .body(requestBody)
            .contentType("application/json")
        .when()
            .post(BASE_URL + "user/create")
        .then()
            .statusCode(400);
    }
    
}
