package api.endpoints;

import api.payload.UserPayload;
import api.urls.CRUDurls;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class UserEndpoints {

    public static void post(UserPayload payload) {

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when().relaxedHTTPSValidation()
                .post(CRUDurls.post_url);
    }

    public static void get(){

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().relaxedHTTPSValidation()
                .get(CRUDurls.get_url);
    }

    public static void put(UserPayload payload){

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when().relaxedHTTPSValidation()
                .post(CRUDurls.put_url);
    }

    public static void patch(UserPayload payload){

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when().relaxedHTTPSValidation()
                .get(CRUDurls.patch_url);
    }

    public static void delete(){

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().relaxedHTTPSValidation()
                .get(CRUDurls.delete_url);
    }

}
