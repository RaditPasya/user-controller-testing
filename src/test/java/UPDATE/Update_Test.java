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

    @Test
    public void TC419() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID
    
        // Request body with a long street name
        String requestBody = "{\n" +
                "  \"location\": {\n" +
                "        \"street\": \"JalanSultanArabKidulBelahWetanKatapangCileunyiDiskotikAhayAseloleJossSenggolDonkDekLingSadboiNihBoss\",\n" +
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
            .body("location.street", equalTo("JalanSultanArabKidulBelahWetanKatapangCileunyiDiskotikAhayAseloleJossSenggolDonkDekLingSadboiNihBoss"));
    }

    @Test
    public void TC420() {
        String userId = "60d0fe4f5311236168a109ff"; // Valid user ID
        String appId = APP_ID; // Valid app ID

        // Request body with a long street name
        String requestBody = "{\n" +
                "  \"location\": {\n" +
                "        \"street\": \"JalanSultanArabKidulBelahWetanKatapangCileunyiDiskotikAhayAseloleJossSenggolDonkDekLingSadboiNihBoss\",\n" +
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
            .body("location.street", equalTo("JalanSultanArabKidulBelahWetanKatapangCileunyiDiskotikAhayAseloleJossSenggolDonkDekLingSadboiNihBoss"));
    }
    @Test
    public void TC421() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"location\": {\n" +
                "        \"street\": \"JalanSultanArabKidulBelahWetanKatapangCileunyiDiskotikAhayAseloleJossSenggolDonkDekLingSadboiNihBoss\",\n" +
                "        \"city\": \"Or\",\n" +
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
            .body("location.city", equalTo("Or"));
    }
    @Test
    public void TC422() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"location\": {\n" +
                "        \"street\": \"JalanSultanArabKidulBelahWetanKatapangCileunyiDiskotikAhayAseloleJossSenggolDonkDekLingSadboiNihBoss\",\n" +
                "        \"city\": \"TchallaMaungHideungTiWakandaaa\",\n" +
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
            .body("location.city", equalTo("TchallaMaungHideungTiWakandaaa"));
    }

    @Test
    public void TC423() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"location\": {\n" +
                "        \"street\": \"JalanSultanArabKidulBelahWetanKatapangCileunyiDiskotikAhayAseloleJossSenggolDonkDekLingSadboiNihBoss\",\n" +
                "        \"city\": \"TchallaMaungHideungTiWakandaaa\",\n" +
                "        \"state\": \"Ch\",\n" +
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
            .body("location.state", equalTo("Ch"));
    }

    @Test
    public void TC424() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"location\": {\n" +
                "        \"street\": \"JalanSultanArabKidulBelahWetanKatapangCileunyiDiskotikAhayAseloleJossSenggolDonkDekLingSadboiNihBoss\",\n" +
                "        \"city\": \"TchallaMaungHideungTiWakandaaa\",\n" +
                "        \"state\": \"SultanAbdulMusallihAlaMuhammad\",\n" +
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
            .body("location.state", equalTo("SultanAbdulMusallihAlaMuhammad"));
    }

    @Test
    public void TC425() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"location\": {\n" +
                "        \"street\": \"JalanSultanArabKidulBelahWetanKatapangCileunyiDiskotikAhayAseloleJossSenggolDonkDekLingSadboiNihBoss\",\n" +
                "        \"city\": \"TchallaMaungHideungTiWakandaaa\",\n" +
                "        \"state\": \"SultanAbdulMusallihAlaMuhammad\",\n" +
                "        \"country\": \"UK\",\n" +
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
            .body("location.country", equalTo("UK"));
    }

    @Test
    public void TC426() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"location\": {\n" +
                "        \"street\": \"JalanSultanArabKidulBelahWetanKatapangCileunyiDiskotikAhayAseloleJossSenggolDonkDekLingSadboiNihBoss\",\n" +
                "        \"city\": \"TchallaMaungHideungTiWakandaaa\",\n" +
                "        \"state\": \"SultanAbdulMusallihAlaMuhammad\",\n" +
                "        \"country\": \"NegaraIsraelAncurinAjahLaTuman\",\n" +
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
            .body("location.country", equalTo("NegaraIsraelAncurinAjahLaTuman"));
    }

    @Test
    public void TC427() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"email\" : \"emails@example.com\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC428() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"dateOfBirth\" : \"29 November 2002\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC429() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"picture\" : \"KepoAjahSihElu\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC430() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"firstName\" : \"B\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC431() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"firstName\" : \"AhmadAbrorAbraKadabraAllaihimuSalamWassalamualaikum\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC432() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"lastName\" : \"C\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC433() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"lastName\" : \"AhBuAsriLieurIeuNahaBisaUpdateLimaPuluhSatuKarakter\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC434() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"firstName\" : 69\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC435() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"lastName\" : 25\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC436() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"title\" : 24\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC437() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"title\" : \"tua\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC438() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"gender\" : 69\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC439() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "  \"gender\" : \"banci\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC440() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"dateOfBirth\" : \"29-11-1899\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC441() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"dateOfBirth\" : \"29-11-2027\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC442() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"Kepo\",\n" +
                "        \"city\": \"Plymouth\",\n" +
                "        \"state\": \"Berkshire\",\n" +
                "        \"country\": \"United Kingdom\",\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    // Test case 43
    @Test
    public void TC443() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String longStreetName = "AhBuPusingKokBisaMasukinSeratusSatuKarakterKeNamaJalanBukannyaHarusnyaGakBisaYaBuGimanaYaBuWebsitenya";

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"" + longStreetName + "\",\n" +
                "        \"city\": \"Plymouth\",\n" +
                "        \"state\": \"Berkshire\",\n" +
                "        \"country\": \"United Kingdom\",\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC444() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": 1900,\n" +
                "        \"city\": \"Plymouth\",\n" +
                "        \"state\": \"Berkshire\",\n" +
                "        \"country\": \"United Kingdom\",\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    // Test case 45
    @Test
    public void TC445() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"AiyoWTF\",\n" +
                "        \"city\": \"B\",\n" +
                "        \"state\": \"Berkshire\",\n" +
                "        \"country\": \"United Kingdom\",\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC446() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"AiyoWTF\",\n" +
                "        \"city\": \"IbuIniKokBisaTigaPuluhSatuHuruf\",\n" +
                "        \"state\": \"Berkshire\",\n" +
                "        \"country\": \"United Kingdom\",\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    // Test case 47
    @Test
    public void TC447() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"AiyoWTF\",\n" +
                "        \"city\": 71,\n" +
                "        \"state\": \"Berkshire\",\n" +
                "        \"country\": \"United Kingdom\",\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    // Test case 48
    @Test
    public void TC448() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"5508, New Street\",\n" +
                "        \"city\": \"Plymouth\",\n" +
                "        \"state\": \"H\",\n" +
                "        \"country\": \"United Kingdom\",\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    // Test case 49
    @Test
    public void TC449() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"5508, New Street\",\n" +
                "        \"city\": \"Plymouth\",\n" +
                "        \"state\": \"IbuIniKokBisaTigaPuluhSatuHuruf\",\n" +
                "        \"country\": \"United Kingdom\",\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    // Test case 50
    @Test
    public void TC450() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"5508, New Street\",\n" +
                "        \"city\": \"Plymouth\",\n" +
                "        \"state\": 96,\n" +
                "        \"country\": \"United Kingdom\",\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }
    @Test
    public void TC451() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"5508, New Street\",\n" +
                "        \"city\": \"Plymouth\",\n" +
                "        \"state\": \"Berkshire\",\n" +
                "        \"country\": \"C\",\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    // Test case 52
    @Test
    public void TC452() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"5508, New Street\",\n" +
                "        \"city\": \"Plymouth\",\n" +
                "        \"state\": \"Berkshire\",\n" +
                "        \"country\": \"IbuIniKokBisaTigaPuluhSatuHuruf\",\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    // Test case 53
    @Test
    public void TC453() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"5508, New Street\",\n" +
                "        \"city\": \"Plymouth\",\n" +
                "        \"state\": \"Berkshire\",\n" +
                "        \"country\": 71,\n" +
                "        \"timezone\": \"-10:00\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    // Test case 54
    @Test
    public void TC454() {
        String userId = "60d0fe4f5311236168a109ff";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "   \"location\": {\n" +
                "        \"street\": \"5508, New Street\",\n" +
                "        \"city\": \"Plymouth\"\n" +
                "    }\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }

    // Test case 55
    @Test
    public void TC455() {
        String userId = "60d0fe4f5311236168a109fg";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "    \"title\": \"mr\",\n" +
                "    \"firstName\": \"Tatang\",\n" +
                "    \"lastName\": \"BaksoAbrag\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(404)
            .body("error", equalTo("RESOURCE_NOT_FOUND"));
    }

    @Test
    public void TC456() {
        String userId = "213";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "    \"title\": \"mr\",\n" +
                "    \"firstName\": \"Tatang\",\n" +
                "    \"lastName\": \"BaksoAbrag\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(400)
            .body("error", equalTo("PARAMS_NOT_VALID"));
    }

    // Test case 57
    @Test
    public void TC457() {
        String userId = "";
        String appId = APP_ID;

        String requestBody = "{\n" +
                "    \"title\": \"mr\",\n" +
                "    \"firstName\": \"Tatang\",\n" +
                "    \"lastName\": \"BaksoAbrag\"\n" +
                "}";

        given()
            .body(requestBody)
            .header("app-id", appId)
            .contentType("application/json")
        .when()
            .put(BASE_URL + USER_ENDPOINT + userId)
        .then()
            .statusCode(404)
            .body("error", equalTo("PATH_NOT_FOUND"));
    }


    
}
