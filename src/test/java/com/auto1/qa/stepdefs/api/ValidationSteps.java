package com.auto1.qa.stepdefs.api;

import com.auto1.qa.context.TestContext;
import com.auto1.qa.context.enums.ContextEnums;
import com.auto1.qa.global.utils.ConfigReader;
import com.auto1.qa.global.utils.LogUtils;
import com.auto1.qa.helpers.Assertions;

import com.auto1.qa.helpers.TestUtils;
import com.auto1.qa.models.ApiResponse;
import com.auto1.qa.models.ErrorResponse;
import com.auto1.qa.utils.RestUtils;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;


/***
 * This is class contains all the validation for cucumber steps
 */
public class ValidationSteps {

    private Response res = null; // Response
    private JsonPath jp = null; // JsonPath

    private RestUtils restUtils;
    private LogUtils LOGGER;
    private ConfigReader configReader;
    private static Assertions assertions;

    private TestContext testContext;

    public ValidationSteps(TestContext context) {
        testContext = context;
        LOGGER = testContext.getLogUtils();
        restUtils = RestUtils.getInstance();
        configReader = ConfigReader.getInstance();
        assertions = Assertions.getInstance();
    }

    @Then("^Verify actual schema of response$")
    public void verify_schema_of_response() {
        res = (Response) testContext.scenarioContext.getResponse(ContextEnums.RESPONSE);

        ApiResponse schemaObj = res.as(ApiResponse.class);

        LOGGER.code("schemaObj:" + schemaObj.getTotalPageCount());
        TestUtils.validateResponseSchema(res,"ApiResult.json");
    }

    @Then("^Verify error is received$")
    public void verify_error_response(DataTable pathParams) {
        List<List<String>> errorCode = pathParams.asLists();

        res = (Response) testContext.scenarioContext.getResponse(ContextEnums.RESPONSE);

        ErrorResponse errorObj = res.as(ErrorResponse.class);
        assertions.assertEquals(errorObj.getStatus(), 400, "Error Status code doesn't match");
        assertions.assertIsNotNull(errorObj.getError(), "Error Code is null");
        assertions.assertEquals(errorObj.getError(), errorCode.get(0).get(0), "Error code doesn't match");
        assertions.assertIsNotNull(errorObj.getMessage(), "Error Message is null");
    }



}
