package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.Location;
import pojo.MapLocation;
import utilities.APIResources;
import utilities.TestDataBuild;
import utilities.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class LocationStepDefinition extends Utils {


    ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    RequestSpecification res;
    Response response;
    static String place_id;

    TestDataBuild data =new TestDataBuild();

    @Given("Add Place Payload")
    public void add_place_payload() throws IOException {

        // res = given()
               //.queryParam("key","qaclick123") // already added at BeforeAll setUp method
                // .spec(requestSpecification())
                // .body(data.addPlacePayload());
    }

    @Given("Add Place  with {string} {string} {string}")
    public void addPlaceWith(String name, String language, String address) throws IOException {
        res = given()
                .spec(requestSpecification())
                .body(data.addPlacePayload(name,language,address));
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResources apiResources = APIResources.valueOf(resource);
        System.out.println(apiResources.getResource());
        if (method.equalsIgnoreCase("post")){
            response = res.when()
                    .post(apiResources.getResource()).prettyPeek();
        }else if(method.equalsIgnoreCase("get")) {
            response = res.when()
                    .get(apiResources.getResource()).prettyPeek();
        }
    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
         response.then().spec(resSpec).statusCode(int1)
                .extract().response().asString();


    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {

        assertEquals(getJsonPath(response,keyValue), expectedValue);

    }


    @And("verify {string} created maps to {string} using {string}")
    public void verifyCreatedMapsToUsing(String place_id1, String expectedName, String resource) throws IOException {
        // request spec
         place_id = getJsonPath(response,"place_id");
         res = given()
                .spec(requestSpecification())
                .queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "get");

        String actualName = getJsonPath(response,"name");
        assertEquals(actualName, expectedName );
    }

    @Given("DeletePlace Payload")
    public void deletePlacePayload() throws IOException {

       res =  given()
                .spec(requestSpecification())
                 .body(data.deletePlacePayload(place_id));
    }
}
