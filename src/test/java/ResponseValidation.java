import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ResponseValidation {


    @Test
    public void jsonValidation(){

        given()
                .when()
                .get("")
                .then()
                .statusCode(200);

        Response res = given().when().get("");

    }
}
