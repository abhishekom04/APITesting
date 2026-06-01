package api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RESTApiAuthentication {


    @Test
    public void basicAuthentication(){

        given()
                .auth().basic("","")
                .when()
                .relaxedHTTPSValidation()
                .get("")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();

        SoftAssert sa = new SoftAssert();
        sa.assertEquals("https://api.example.com", "https://api.example.com");
        sa.assertAll();

        ChromeOptions  options=new ChromeOptions();
//if file is .zip never display popup download it directly
        String key="browser.helperApps.neverAsk.saveToDisk";
        String value="application/zip";
        options.setCapability(key,value);
//open firefox with above setting
        WebDriver driver=new ChromeDriver(options);
    }

    @Test
    public void digestAuthentication(){

        given()
                .auth().digest("","")
                .when()
                .relaxedHTTPSValidation()
                .get("")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();

    }

    @Test
    public void preemptiveAuthentication(){

        given()
                .auth().preemptive().basic("","")
                .when()
                .relaxedHTTPSValidation()
                .get("")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();

    }

    @Test
    public void oauth1Authentication(){

        given()
                .auth().oauth("","","","")
                .when()
                .relaxedHTTPSValidation()
                .get("")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    public void oauth2Authentication(){

        given()
                .auth().oauth2("")
                .when()
                .relaxedHTTPSValidation()
                .get("")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    public void bearerAuthentication(){

        String bearertoken="";

        given()
                .header("Authorization","Bearer"+bearertoken)
                .when()
                .relaxedHTTPSValidation()
                .get("")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    public void apiAuthentication(){

        given()
                .queryParam("appid","")
                .when()
                .relaxedHTTPSValidation()
                .get("")
                .then()
                .statusCode(200)
                .log().all();

        given()
                .queryParam("appid","")
                .when()
                .relaxedHTTPSValidation()
                .get("")
                .then()
                .statusCode(200)
                .log().all();
    }

}
