package CodigoBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

public class ApisSteps {
	
	public Response res = null;
    private String contenType = "";
    private String accept = "";
    private String endPointOrWSDL = "";
    public RequestSpecification requestSpec;
    private String headerKey1 = "";
    private String headerValue1 = "";
    private String headerKey2 = "";
    private String headerValue2 = "";
    private String headerKey3 = "";
    private String headerValue3 = "";
    private String headerKey4 = "";
    private String headerValue4 = "";
    private String headerKey5 = "";
    private String headerValue5 = "";
    private String headerKey6 = "";
    private String headerValue6 = "";
    private String headerKey7 = "";
    private String headerValue7 = "";
    private String requestConsume = "{}";
    
    @Step
    public void stepsRequestHTTP (){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath(endPointOrWSDL);
        builder.setContentType(contenType);
        builder.setAccept(accept);
        builder.addHeader(headerKey1,headerValue1);
        builder.addHeader(headerKey2,headerValue2);
        builder.addHeader(headerKey3,headerValue3);
        builder.addHeader(headerKey4,headerValue4);
        builder.addHeader(headerKey5,headerValue5);
        builder.addHeader(headerKey6,headerValue6);
        builder.setBody(requestConsume);
        requestSpec = builder.build();
        requestSpec = RestAssured.given().spec(requestSpec);
        requestSpec.log().all();
    }

    @Step
    public void stepsRequestHTTPWithoutHeaders (){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath(endPointOrWSDL);
        builder.setContentType(contenType);
        builder.setAccept(accept);
        builder.setBody(requestConsume);
        requestSpec = builder.build();
        requestSpec = RestAssured.given().spec(requestSpec);
        requestSpec.log().all();
    }

    @Step
    public void withEndPoint(String endPoint)
    {
        this.endPointOrWSDL = endPoint;
    }

    @Step
    public void withMessageInJson() {
        this.contenType = "application/json";
        this.accept = "*/*";
    }

    @Step
    public void withMessageInXML() {
        this.contenType = "text/xml; charset=UTF-8";
        this.accept = "application/xml";
    }

    @Step
    public void withHeader1(String headerKey1,String headerValue1)
    {
        this.headerKey1 = headerKey1;
        this.headerValue1 = headerValue1;
    }

    @Step
    public void withHeader2(String headerKey2,String headerValue2)
    {
        this.headerKey2 = headerKey2;
        this.headerValue2 = headerValue2;
    }

    @Step
    public void withHeader3(String headerKey3,String headerValue3)
    {
        this.headerKey3 = headerKey3;
        this.headerValue3 = headerValue3;
    }

    @Step
    public void withHeader4(String headerKey4,String headerValue4)
    {
        this.headerKey4 = headerKey4;
        this.headerValue4 = headerValue4;
    }

    @Step
    public void withHeader5(String headerKey5,String headerValue5)
    {
        this.headerKey5 = headerKey5;
        this.headerValue5 = headerValue5;
    }

    @Step
    public void withHeader6(String headerKey6,String headerValue6)
    {
        this.headerKey6 = headerKey6;
        this.headerValue6 = headerValue6;
    }

    @Step
    public void withHeader7(String headerKey7,String headerValue7)
    {
        this.headerKey7 = headerKey7;
        this.headerValue7 = headerValue7;
    }

    @Step
    public void withBodyRequest(String requestConsume)
    {
        this.requestConsume = requestConsume;
    }

    @Step
    public void postStepsRequest()  {
        res = requestSpec.when().post();
    }

    @Step
    public void getStepsRequest()  {
        res = requestSpec.when().get();
    }

    @Step
    public void deleteStepsRequest()
    {
        res = requestSpec.when().delete();
    }

    @Step
    public void putStepsRequest()
    {
        res = requestSpec.when().put();
    }

    @Step
    public void verifyStatusSuccess(int status) {
        Assert.assertEquals("The Status Responses Is Not Equal!",res.getStatusCode(),status);
    }

    @Step
    public void verifyResponseSuccess(String response) {
        Assert.assertEquals("The Responses Is Not Equal!",res.getBody().prettyPrint(),response);
    }

    @Step
    public void verifyResponseDataSucces(String dataResponse)
    {
        Assert.assertEquals("The Responses Is Not Equal!",res.getBody().prettyPrint().contains(dataResponse),true);
    }

    @Step
    public void teachResponseJson() {
    	System.out.println(res.getBody().prettyPrint());
    }
    
    @Step    
    public String getResponseJson() {
    	return res.getBody().prettyPrint();
    }
}
