import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @Test
    public void testUserEndpoint() {
        given()
            .header("app-id", "662e4409bb70a71d2f2594c7")
        .when()
            .get("https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109cb")
        .then()
            .statusCode(200)
            .body("id", equalTo("60d0fe4f5311236168a109cb"))
            .body("title", equalTo("miss"))
            .body("firstName", equalTo("Edita"))
            .body("lastName", equalTo("Vestering"))
            .body("picture", equalTo("https://randomuser.me/api/portraits/med/women/89.jpg"))
            .body("gender", equalTo("female"))
            .body("email", equalTo("edita.vestering@example.com"))
            .body("dateOfBirth", equalTo("1956-04-15T00:10:35.555Z"))
            .body("phone", equalTo("(019)-646-0430"))
            .body("location.street", equalTo("1371, Dilledonk-Zuid"))
            .body("location.city", equalTo("Den Bommel"))
            .body("location.state", equalTo("Gelderland"))
            .body("location.country", equalTo("Netherlands"))
            .body("location.timezone", equalTo("-5:00"))
            .body("registerDate", equalTo("2021-06-21T21:02:07.533Z"))
            .body("updatedDate", equalTo("2021-06-21T21:02:07.533Z"));
    }
}
