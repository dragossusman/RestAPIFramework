package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepsDefinition stepsDefinition = new StepsDefinition();
        if(StepsDefinition.place_id == null) {
            stepsDefinition.addPlacePayloadWith("Dragos", "French", "Asia");
            stepsDefinition.user_calls_Post_http_req("AddPlaceAPI", "POST");
            stepsDefinition.verifyPlace_IdCreatedMapsToUsing("Dragos", "getPlaceAPI");
        }
    }
}
