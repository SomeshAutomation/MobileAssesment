# Mobile Automation Assessment

* This framework is based on Cucumber - Page Object module using Appium library.
* For reporting Allure Library is used. Lombak library is used for logging and other Data annotation.
* Currently, it is implemented for Android application and can easily update for iOS platform as well.

The framework follows three layer structure:
* feature files: for Test Case writing
* StepDef files: for Step definition
* PageObject files: for storing locators and their action methods

------------------------------------------------------------
**Prerequisites**

* Java 11 and Maven Installed, path set in environmental variables
* Android sdk configured and Path set in environmental variables
* Appium server 1.22
* Allure configured
(Please refer https://docs.qameta.io/allure/#_installing_a_commandline)

------------------------------------------------------------
**Running Project**

Once the Appium server is up and running
And the test device details, appium servers details are update in config.properties file

Run below command

mvn clean test -Dcucumber.filter.tags="@Navigation"

------------------------------------------------------------
**Reports**

**Cucumber Report**
One can find cucumber Reports by opening target/results/cucumber-reports.html

[image](https://github.com/SomeshAutomation/MobileAssesment/blob/master/CucumberReport.png)


**Allure Report**
in project path, please navigate to target folder 
cd target/
then Run
allure serve

[image](https://github.com/SomeshAutomation/MobileAssesment/blob/master/AllureReport1.png)
[image](https://github.com/SomeshAutomation/MobileAssesment/blob/master/AllureReport2.png)
------------------------------------------------------------







