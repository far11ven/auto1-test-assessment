package com.auto1.qa.stepdefs.api;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auto1.qa.context.TestContext;
import com.auto1.qa.enums.ContextEnums;
import com.auto1.qa.global.utils.ConfigReader;
import com.auto1.qa.global.utils.LogUtils;
import com.auto1.qa.helpers.Assertions;
import com.auto1.qa.utils.RestUtils;
import com.auto1.qa.utils.TestUtils;
import com.auto1.qa.global.utils.FileOperations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/***
 * This is class contains all the API Testing tasks
 */
public class ApiCommonSteps {

    public Response res = null; // Response
    public JsonPath jp = null; // JsonPath

    public RestUtils restUtils;
    public LogUtils LOGGER;
    public ConfigReader configReader;
    public static Assertions assertions;

    TestContext testContext;

    public ApiCommonSteps(TestContext context) {
        testContext = context;
        LOGGER = testContext.getLogUtils();
        restUtils = RestUtils.getInstance();
        configReader = ConfigReader.getInstance();
        assertions = Assertions.getInstance();

    }


    @Given("^As as a user I want to execute '([^\"]+)' endpoint$")
    public void user_wants_to_execute_api_endpoint(String endpoint) {
        restUtils.setBaseURI(configReader.getProperty("BASE_URL"));
        LOGGER.info("Setting BASEURL as :" + configReader.getProperty("BASE_URL"));
        // Setup Base Path
        restUtils.setBasePath(configReader.getProperty("API_BASE_PATH"));
        LOGGER.info("Setting BASEPATH as :" + configReader.getProperty("API_BASE_PATH"));
        // used for ignoring ssl
        restUtils.relaxedHTTPSValidation();

        // save API_ENDPOINT in context
        testContext.scenarioContext.setContext(ContextEnums.API_ENDPOINT, endpoint);
        LOGGER.info("Setting API_ENDPOINT as :" + endpoint);

        LOGGER.info("endpoint" + endpoint);

        LOGGER.info("Setting QualysSession cookies in header param.." + configReader.getProperty("QUALYS_SESSION_COOKIE"));
        restUtils.setRequestHeader("cookie", "QualysSession=" + configReader.getProperty("QUALYS_SESSION_COOKIE"));


    }

    @When("^I set query params as$")
    public void user_sets_query_params(Map<String, String> queryParams) {
        LOGGER.info("Setting query params DATA as..");
        Map<String, String> newParams = queryParams;
        for (Map.Entry<String, String> entry : newParams.entrySet()) {

            if (entry.getValue().contains(",")) {
                String currentParam = entry.getValue();
                currentParam = currentParam.replace(" ", "");
                String keyValues[] = currentParam.split(",");
                String queryString = "";
                for (int i = 0; i < keyValues.length; i++) {

                    if (i == 0) {
                        queryString = keyValues[i];
                    } else {
                        queryString = queryString + "&" + entry.getKey() + "=" + keyValues[i];
                    }
                }
                try {
                    newParams.put(entry.getKey(), queryString);
                } catch (UnsupportedOperationException e) {
                    e.printStackTrace();
                }

            }
            LOGGER.info(entry.getKey() + ":" + entry.getValue());
        }

        restUtils.setRequestQueryParams(newParams);

    }

    @When("^I set query param '([^\"]+)' as '([^\"]+)'$")
    public void user_sets_query_param_as(String queryParamName, String queryParamValue) {
        LOGGER.info("Setting query params DATA as..");
        Map<String, String> newRuleIDParams = new HashMap<>();
        newRuleIDParams.put(queryParamName, queryParamValue);
        restUtils.setRequestQueryParams(newRuleIDParams);

    }

    @When("^I set path params as$")
    public void user_sets_path_params(DataTable pathParams) {
        List<List<String>> pathData = pathParams.asLists();

        LOGGER.info("Setting path params DATA.." + pathData.get(0).get(0));
        restUtils.setRequestPathParams(pathData.get(0).get(0), pathData.get(0).get(1));

        testContext.scenarioContext.setContext(ContextEnums.CURRENT_PATH_PARAM, pathData.get(0).get(1));

    }


    @When("^I set path params '([^\"]+)' as \"([^\"]*)\"$")
    public void user_sets_path_params_as_key_and_value(String pathParam, String pathParamValue) {

        LOGGER.info("Setting path params DATA.." + pathParam);
        restUtils.setRequestPathParams(pathParam, pathParamValue);


    }

    @When("^I set headers as$")
    public void user_sets_headers(Map<String, String> headers) {
        LOGGER.info("Setting headers..");
        restUtils.setRequestHeader(headers);
        restUtils.setContentType(ContentType.JSON);

        int i = 1;

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            LOGGER.info("Setting headers.." + i++ + entry.getKey());
        }
    }

    @When("^I set request body as '([^\"]+)'$")
    public void user_sets_request_body(String fileName) {
        LOGGER.info("Setting request body DATA..");
        String requestBody = null;

        try {
            requestBody = FileOperations.readFromFile("src/test/resources/request-payloads/" + fileName);

        } catch (FileNotFoundException e) {
            LOGGER.error("Error reading Request body json file - " + fileName);
            e.printStackTrace();
        }

        restUtils.setRequestBody(requestBody);


    }


    @When("^User submits the 'GET' request$")
    public void user_submits_the_GET_request() {
        LOGGER.info("GETTING DATA.. FROM :" + testContext.scenarioContext.getContext(ContextEnums.API_ENDPOINT));

        res = restUtils.getResponsebyPath(testContext.scenarioContext.getContext(ContextEnums.API_ENDPOINT).toString());

        LOGGER.code("GET Response :" + res.asString());

        // save response
        testContext.scenarioContext.setResponse(ContextEnums.RESPONSE, res);

    }

    @When("^User submits the 'POST' request$")
    public void user_submits_the_POST_request() {
        LOGGER.info("POSTING DATA..");
        res = restUtils
                .getPOSTResponsebyPath(testContext.scenarioContext.getContext(ContextEnums.API_ENDPOINT).toString());


        LOGGER.code("POST Response : " + res.asString());

        // save response
        testContext.scenarioContext.setResponse(ContextEnums.RESPONSE, res);

    }

    @When("^User submits the 'DELETE' request$")
    public void user_submits_the_DELETE_request() {
        LOGGER.info("DELETING DATA..");
        res = restUtils
                .getDELETEResponsebyPath(testContext.scenarioContext.getContext(ContextEnums.API_ENDPOINT).toString());


        LOGGER.code("Delete Response : " + res.asString());

        // save response
        testContext.scenarioContext.setResponse(ContextEnums.RESPONSE, res);

    }

    @When("^User submits the 'PUT' request$")
    public void user_submits_the_PUT_request() {
        LOGGER.info("UPDATING DATA..");
        res = restUtils
                .getPUTResponsebyPath(testContext.scenarioContext.getContext(ContextEnums.API_ENDPOINT).toString());

        LOGGER.code("PUT Response " + res.asString());

        // save response
        testContext.scenarioContext.setResponse(ContextEnums.RESPONSE, res);

    }

    @Then("^Verify response status code is '([^\"]+)'$")
    public void verify_user_validates_status_code(int statusCode) {
        jp = restUtils.getJsonPath(res);

        // verify if the HTTP Status received in response was [statusCode]
        TestUtils.checkStatusIs(res, statusCode);
    }

}
