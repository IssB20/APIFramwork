package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        // execute only when place_id is null
        // write a code that will give you place_id


      LocationStepDefinition m = new LocationStepDefinition();

      if(LocationStepDefinition.place_id == null) {

          m.addPlaceWith("Islem", "French", "Africa");
          m.user_calls_with_http_request("addPlaceAPI", "post");
          m.verifyCreatedMapsToUsing("", "Islem", "getPlaceAPI");
      }
    }
}
