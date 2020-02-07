package com.auto1.qa.stepdefs.hooks;

import cucumber.api.PendingException;
import org.apache.commons.lang3.RandomStringUtils;
import com.auto1.qa.utils.RestUtils;
import com.auto1.qa.context.TestContext;
import com.auto1.qa.enums.ContextEnums;
import com.auto1.qa.global.utils.ConfigReader;
import com.auto1.qa.global.utils.LogUtils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/***
 * This custom class provides scenario hooks i.e @before ans @after
 */
public class Hooks {

	private static ConfigReader configReader = ConfigReader.getInstance();
	private static RestUtils restUtils = RestUtils.getInstance();
	private static LogUtils LOGGER;

	private TestContext testContext;
	public Hooks(TestContext context) {

		testContext = context;
		LOGGER = context.getLogUtils();

		if (null != System.getProperty("mode")) {
			// no need to initiate for api test
			if (!System.getProperty("mode").equalsIgnoreCase("api")) {
				// Setup API Pre-requisites here
			}
		} else if (null != configReader.getRunnerConfigProperty("MODE")) {
			// no need to initiate for api test
			if (!configReader.getRunnerConfigProperty("MODE").equalsIgnoreCase("api")) {

				// Setup API Pre-requisites here
			}
		}
		testContext.scenarioContext.setContext(ContextEnums.TEST_RUN_TIMESTAMP,
				System.getProperty("TEST_RUN_TIMESTAMP"));
	}

	@Before(order = 0)
	public void beforeScenario(Scenario scenario) {
		String scenarioID = RandomStringUtils.random(5, true, true);
		LOGGER.start("Starting Test : " + scenario.getName() + " with id : " + scenarioID);

		testContext.scenarioContext.setContext(ContextEnums.CURRENT_SCENARIO_ID, scenarioID);
		testContext.scenarioContext.setContext(ContextEnums.CURRENT_SCENARIO_NAME, scenario.getName()); // save in
		// scenario
		// context
	}

	@Before("@ignore")
	public void beforeIgnoreTest() throws Throwable {
		LOGGER.warn("Test was marked as ignored" );

		throw new PendingException();
	}

	/*
	 * This method runs at the last of a test case run and closes current driver
	 * instance
	 *
	 */
	@After(order = 0)
	public void tearDown() throws InterruptedException {

		// no need to initiate for api test
		if (null != System.getProperty("mode")) {

			if (System.getProperty("mode").equalsIgnoreCase("api")) {
				// Reset ApiUtils Values
				LOGGER.info("Resetting  RestUtils instance..");
				restUtils.resetRestAssured();
			}
		} else if (null != configReader.getRunnerConfigProperty("MODE")) {

			if (configReader.getRunnerConfigProperty("MODE").equalsIgnoreCase("api")) {
				// Reset ApiUtils Values
				LOGGER.info("Resetting  RestUtils instance..");
				restUtils.resetRestAssured();
			}
		}

		LOGGER.info("Test Run Complete \n");
	}
}
