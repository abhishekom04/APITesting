package api.test;

import api.utilities.ExtentReport;
import com.aventstack.extentreports.Status;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResponseValidation {


    @Test
    public void getRequestJsonFormat1(){

        ExtentReport.getTest().log(Status.INFO, "Starting API validation");

        given()
                .contentType(ContentType.JSON)
                .when().relaxedHTTPSValidation()
                .get("https://dummyjson.com/users")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("users[0].email",equalTo("emily.johnson@x.dummyjson.com"),
                        "users[0].gender",equalTo("female"),
                        "users.size()",equalTo(30))
                .log().all();

        //Note:
        //if you want to log all the details then use .log().all();
        //if you want to log only the body then use .log().body();

        Response response =  given().contentType(ContentType.JSON).when().relaxedHTTPSValidation()
                                    .get("https://dummyjson.com/users");

        String body = response.getBody().asString();
        System.out.println("Body: "+body);

        String contentType = response.getHeader("Content-Type");
        System.out.println("Content-Type: "+contentType);
        ExtentReport.getTest().log(Status.INFO, "Content-Type: "+contentType);

        String encoding = response.getHeader("Content-Encoding");
        System.out.println("Content-Encoding: "+encoding);
        ExtentReport.getTest().log(Status.INFO, "Content-Encoding: "+encoding);

        String statusLine = response.getStatusLine().toString();
        System.out.println("Status Line: "+statusLine);
        ExtentReport.getTest().log(Status.INFO, "Status Line: "+statusLine);

        JsonPath jsonPath = new JsonPath(response.asString());
        int numberOfUsers = jsonPath.getInt("users.size()");
        System.out.println("Number of users: "+numberOfUsers);
        ExtentReport.getTest().log(Status.INFO, "Number of users: "+numberOfUsers);

        //Method-1 of getting only one element
        String firstName = response.jsonPath().getString("users[0].firstName");
        System.out.println("First Name: "+firstName);
        ExtentReport.getTest().log(Status.INFO, "First Name: "+firstName);

        //Method-2 of getting only one element
        String email = response.jsonPath().get("users[0].email").toString();
        System.out.println("Email: "+email);
        ExtentReport.getTest().log(Status.INFO, "Email: "+email);

        //Method of getting list of JsonObjects of JsonArray
        //when more than one element is present, URL=https://dummyjson.com/users
        List<String> firstNames = response.jsonPath().getList("users.firstName");
        System.out.println("First Names: "+firstNames);
        ExtentReport.getTest().log(Status.INFO, "First Names: "+firstNames);

        //Method of getting list of JsonObjects of JsonArray
        //when more than one element is present, URL=https://dummyjson.com/users
        //For nested JSON elements
        List<String> hairColors = response.jsonPath().getList("users.hair.color");
        System.out.println("Hair Colors: "+hairColors);
        ExtentReport.getTest().log(Status.INFO, "Hair Colors: "+hairColors);

    }

    @Test
    public void getRequestJsonFormat2(){

        ExtentReport.getTest().log(Status.INFO, "Starting API validation");

        given()
                .contentType(ContentType.JSON)
                .when().relaxedHTTPSValidation()
                .get("https://dummyjson.com/users/1")
                .then()
                .assertThat()
                .header("Content-Type","application/json; charset=utf-8")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("email",equalTo("emily.johnson@x.dummyjson.com"),
                        "gender",equalTo("female"),
                        "hair.color",equalTo("Brown"),
                        "size()",equalTo(28))
                .log().all();


        //Note:
        //if you want to log all the details then use .log().all();
        //if you want to log only the body then use .log().body();

        Response response =  given().contentType(ContentType.JSON).when().relaxedHTTPSValidation()
                .get("https://dummyjson.com/users/1");

        String body = response.getBody().asString();
        System.out.println("Body: "+body);

        String contentType = response.getHeader("Content-Type");
        System.out.println("Content-Type: "+contentType);
        ExtentReport.getTest().log(Status.INFO, "Content-Type: "+contentType);

        String encoding = response.getHeader("Content-Encoding");
        System.out.println("Content-Encoding: "+encoding);
        ExtentReport.getTest().log(Status.INFO, "Content-Encoding: "+encoding);

        String statusLine = response.getStatusLine().toString();
        System.out.println("Status Line: "+statusLine);
        ExtentReport.getTest().log(Status.INFO, "Status Line: "+statusLine);

        JsonPath jsonPath = new JsonPath(response.asString());
        int numberOfUserDetails = jsonPath.getInt("size()");
        System.out.println("Number of user details: "+numberOfUserDetails);
        ExtentReport.getTest().log(Status.INFO, "Number of user details: "+numberOfUserDetails);

        String firstName = response.jsonPath().getString("firstName");
        System.out.println("First Name: "+firstName);
        ExtentReport.getTest().log(Status.INFO, "First Name: "+firstName);

        String email = response.jsonPath().get("email").toString();
        System.out.println("Email: "+email);
        ExtentReport.getTest().log(Status.INFO, "Email: "+email);


        Map<String,String> hair = response.jsonPath().getMap("hair");
        System.out.println("Hair : "+hair);
        System.out.println("Hair Color : "+hair.get("color").contains("Brown"));
        ExtentReport.getTest().log(Status.INFO, "Hair : "+hair);

        //use .getList() method if the element are present in an array

//        List<String> instructions =  response.jsonPath().getList("instructions");
//        System.out.println("Instructions: "+instructions);
//        System.out.println("Instructions : "+instructions.get(0));

        //Note: if there is element inside the third loop
        //ex: company.address.coordinate then if you want to get elements of coordinate then
//        "company": {
//            "department": "Engineering",
//                    "name": "Dooley, Kozey and Cronin",
//                    "title": "Sales Manager",
//                    "address": {
//                "address": "263 Tenth Street",
//                        "city": "San Francisco",
//                        "state": "Wisconsin",
//                        "stateCode": "WI",
//                        "postalCode": "37657",
//                        "coordinates": {
//                    "lat": 71.814525,
//                            "lng": -161.150263
//                },
//                "country": "United States"
//            }
//        }

//        Map<String,String> coordinate = response.jsonPath().getMap("address.coordinate");
//        System.out.println("Coordinate : "+coordinate);
    }

    @Test(enabled = false)
    public void getRequestXmlFormat2(){

        given()
                .contentType(ContentType.XML)
                .when().relaxedHTTPSValidation()
                .get("https://dummyjson.com/users/1")
                .then()
                .assertThat()
                .header("Content-Type","application/xml")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("email",equalTo("emily.johnson@x.dummyjson.com"),
                        "gender",equalTo("female"),
                        "hair.color",equalTo("Brown"),
                        "users.size()",equalTo(28))
                .log().all();


        //Note:
        //if you want to log all the details then use .log().all();
        //if you want to log only the body then use .log().body();

        Response response =  given().contentType(ContentType.XML).when().relaxedHTTPSValidation()
                .get("https://dummyjson.com/users/1");

        String body = response.getBody().asString();
        System.out.println("Body: "+body);

        String contentType = response.getHeader("Content-Type");
        System.out.println("Header: "+contentType);

        String encoding = response.getHeader("Content-Encoding");
        System.out.println("Encoding: "+encoding);

        String statusLine = response.getStatusLine().toString();
        System.out.println("Status Line: "+statusLine);

        JsonPath jsonPath = new JsonPath(response.asString());
        int numberOfUserDetails = jsonPath.getInt("size()");
        System.out.println("Number of user details: "+numberOfUserDetails);

        String firstName = response.jsonPath().getString("firstName");
        System.out.println("First Name: "+firstName);

        String email = response.jsonPath().get("email").toString();
        System.out.println("Email: "+email);


        Map<String,String> hair = response.jsonPath().getMap("hair");
        System.out.println("Hair : "+hair);
        System.out.println("Hair Color : "+hair.get("color").contains("Brown"));

        //use .getList() method if the element are present in an array

        List<String> instructions =  response.jsonPath().getList("instructions");
        System.out.println("Instructions: "+instructions);
        System.out.println("Instructions : "+instructions.get(0));

        //Note: if there is element inside the third loop
        //ex: company.address.coordinate then if you want to get elements of coordinate then
//        "company": {
//            "department": "Engineering",
//                    "name": "Dooley, Kozey and Cronin",
//                    "title": "Sales Manager",
//                    "address": {
//                "address": "263 Tenth Street",
//                        "city": "San Francisco",
//                        "state": "Wisconsin",
//                        "stateCode": "WI",
//                        "postalCode": "37657",
//                        "coordinates": {
//                    "lat": 71.814525,
//                            "lng": -161.150263
//                },
//                "country": "United States"
//            }
//        }

//        Map<String,String> coordinate = response.jsonPath().getMap("address.coordinate");
//        System.out.println("Coordinate : "+coordinate);
    }

    @Test(enabled = false)
    public void uploadSingleFile() {

        given()
                .multiPart("file", new File("src/test/resources/report1.pdf"))
                .contentType("multipart/form-data")
                .when().relaxedHTTPSValidation()
                .post("")
                .then()
                .assertThat()
                .statusCode(200)
                .body("filename",equalTo("report1.pdf"))
                .log().all();

    }

    @Test(enabled = false)
    public void uploadMultipleFiles() {

        given()
                .multiPart("file", new File("src/test/resources/report1.pdf"))
                .multiPart("file", new File("src/test/resources/report2.pdf"))
                .contentType("multipart/form-data")
                .when().relaxedHTTPSValidation()
                .post("")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].filename",equalTo("report1.pdf"),
                        "[1].filename",equalTo("report2.pdf"))
                .log().all();

        //OR using array of files

        //File[] files =  new File("src/test/resources/report1.pdf").listFiles();
        File file1 = new File("src/test/resources/report1.pdf");
        File file2 = new File("src/test/resources/report2.pdf");
        File[] files ={file1,file2};

        given()
                .multiPart("file",files)
                .contentType("multipart/form-data")
                .when().relaxedHTTPSValidation()
                .post("")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].filename",equalTo("report1.pdf"),
                        "[1].filename",equalTo("report2.pdf"))
                .log().all();
    }

    @Test(enabled = false)
    public void downloadFile() {

        Response response=
        given()
                .when().relaxedHTTPSValidation()
                .get("/download/reports.pdf")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type","application/pdf")
                .header("Content-Disposition","attachment; filename=\"reports.pdf\"")
                .extract().response();

        byte[] fileContents = response.asByteArray();
        Assert.assertTrue(fileContents.length>0);
    }
}
