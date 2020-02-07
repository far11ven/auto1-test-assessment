# Project Summary

This framework uses Java-Cucumber-TestNG with RestAssured for testing

- For Reporting Extent Reports are used and for Log4J is used for logging 
- ```"features/"``` contains all the Test Scenario features written in Gherkin
- ```"output/<env>/"``` contains all the Test Reports related resources
- ```"src/main/java > com.auto1.qa.global.utils"``` contains ALL the utility functions used in project
- ```"src/test/java > com.auto1.qa.runner"``` contains all test runners for running this project
- ```"src/test/java > com.auto1.qa.context"``` contains required test context classes and ```ContextEnums.java``` contains list of stored values for a Test Run
- ```"src/test/java > com.auto1.qa.stepdefs"``` contains all the step definitions
- ```"src/test/resources > runner.config"``` contains all the TestRunner configurations required when running from IDE
- ```"src/test/resources > <env>/env.config"``` contains all the environment specific configuration settings related to an env
- ```"src/test/resources > schemas"``` contains response schemas


## How To Run :

 **Method#1** - Running Via IDE
 
 **Pre-requisite:**
 ```runner.config``` file should have following properties:
 ```ENV	= [test, dev]```
 ```MODE = [api]```
 
 - Goto ```src/test/java > com.auto1.qa.runner```
 - Right click on ```"CucumberTestRunnerReporter.java"``` > ```Run As``` > ```TestNG Test```
 
 
 **Method#2** - Running Via Command Line
 - Open ```"Command Prompt"```
 - Goto ```Project directory```
 - type following command : 
 > 
 		- mvn install
 		- mvn test -Denv="qa" -Dmode="api" -Dcucumber.options="features/*" -Dcucumber.options="--tags @api" 
 		
**Maven Attributes :**  
 > 
 		**-Denv** values "qa" or "dev"  
 		**-Dmode** can take value "api"  
 		**-Dcucumber.options="features/*"** specifies path to features folder where all ```.feature``` files are stored  
 		**-Dcucumber.options="--tags @api"** specify "@api" to run all test features or a particular tag like "@e2e" to run single feature  

 		
## Run Report: 
Test Reports can be found at path ```\output\<env>\Test_Report.html``` (Automatically created after first run) 

![alt text](https://raw.githubusercontent.com/far11ven/auto1-test-assessment/develop/src/test/resources/images/Screenshot_TestReport.png)

## Logs: 

Logs are stored under respective date folder under "output/<env>/logs" directory path ```\output\<env>\logs\TestLog_<Timestamp>``` (Automatically created after first run)
