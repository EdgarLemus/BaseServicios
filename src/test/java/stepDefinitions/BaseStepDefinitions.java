package stepDefinitions;

import CodigoBase.ApisSteps;
import cucumber.api.java.en.*;
import io.restassured.RestAssured;
import net.thucydides.core.annotations.Steps;

public class BaseStepDefinitions {
	
	@Steps
	ApisSteps stepApi;
	
	@Given("^I want to write a step with precondition$")
	public void iWantToWriteAStepWithPrecondition() {
		String jsonBodyRequest = "{\r\n" + 
				"    \"type\": \"/LastGameResult\", \r\n" + 
				"    \"parameters\": {\r\n" + 
				"        \"order\": \"sorteo DESC\", \r\n" + 
				"        \"limit\": 1}\r\n" + 
				"}";
		RestAssured.baseURI = "https://api-baloto-prod.baloto.com/";
		stepApi.withEndPoint("petition");
		stepApi.withMessageInJson();
		stepApi.withBodyRequest(jsonBodyRequest);
		stepApi.stepsRequestHTTPWithoutHeaders();
	}


	@When("^I complete action$")
	public void iCompleteAction() {
		stepApi.postStepsRequest();
	}

	@Then("^I validate the outcomes$")
	public void iValidateTheOutcomes() {
		stepApi.verifyStatusSuccess(200);
		stepApi.teachResponseJson();
	}
}
