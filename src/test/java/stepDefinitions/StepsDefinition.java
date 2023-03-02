package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepsDefinition extends Utils{

    RequestSpecification res;

    ResponseSpecification resspec;

    Response response;

    static String place_id;

    static TestDataBuild testDataBuild = new TestDataBuild();

    JsonPath js;
    @When("user calls {string} with {string} http request")
    public void user_calls_Post_http_req(String apiResource, String requestType){
        APIResources resourceAPI = APIResources.valueOf(apiResource);
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(requestType.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getResource());
        } else if (requestType.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResource());
        }
    }

    @Then("the API call is success with status code {int}")
    public void the_API_call_got_success_with_status_code(int integer){
        assertEquals(response.getStatusCode(), integer);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue){
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {
        res=given().spec(requestSpecification())
                .body(testDataBuild.addPlacePayload(name, language, address));
    }

    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(response, "place_id");
        res=given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_Post_http_req(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);
    }

    @Given("DeletePlaceAPI Payload")
    public void deleteplaceapiPayload() throws IOException {
        res = given().spec(requestSpecification()).body(testDataBuild.deletePlacePayload(place_id));
    }
}
