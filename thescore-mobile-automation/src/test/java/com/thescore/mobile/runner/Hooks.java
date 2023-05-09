package com.thescore.mobile.runner;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

@Slf4j
public class Hooks {
  public static final int IMPLICIT_WAIT = 7;
  public static Properties properties = new Properties();
  public static final String CONFIG_FILE_NAME = "config.properties";
  // Command to generate Ready-to-Go Allure report
  private static final String ALLURE_CMD =
      "allure generate target/allure-results -o target/allure-reports --clean";
  private static ThreadLocal<AppiumDriver> appiumDriverThreadLocal = new ThreadLocal<>();

  @BeforeAll
  public static void beforeAllSetUp() throws IOException {
    properties.load(Hooks.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
    log.info("in the Before All : config properties loaded");
    // All Data Setup and DB call to get Test Data can be done here
  }

  @Before
  public void beforeSetUp() throws MalformedURLException {
    initializeDriver();
  }

  @After
  public void tearDown() {
    takeScreenshot();
    getAppiumDriver().quit();
    appiumDriverThreadLocal.remove();
  }

  @AfterAll
  public static void closeDriver() {
    log.info("in the After All ");
    Runtime runtime = Runtime.getRuntime();
    try {
      runtime.exec(ALLURE_CMD);
      log.info("Allure Reports generated");
    } catch (IOException e) {
      log.info(
          "Unable to generate ready-to-go Allure report. User now has to run 'allure serve' cmd to"
              + " generate report");
    }
    // All Data clean up and restoring of data can be done here.
  }

  public static AppiumDriver getAppiumDriver() {
    return appiumDriverThreadLocal.get();
  }

  public void takeScreenshot() {
    log.info("taking screen shot.");
    byte[] screenShot = ((TakesScreenshot) getAppiumDriver()).getScreenshotAs(OutputType.BYTES);
    Allure.addAttachment("Screenshot for scenario ", new ByteArrayInputStream(screenShot));
  }

  public void initializeDriver() throws MalformedURLException {
    if (appiumDriverThreadLocal != null
        && appiumDriverThreadLocal.get() != null
        && !isEmpty(appiumDriverThreadLocal.get())) {
      closeDriver();
    }
    if (appiumDriverThreadLocal.get() == null) {
      AppiumDriver appiumDriver = null;
      if (properties.getProperty("platformName").equalsIgnoreCase("Android")) {
        appiumDriver =
            new AndroidDriver(
                new URL(properties.getProperty("appiumServerURL")),
                getAndroidDesiredCapabilities());
        log.info("The Driver has been initialized for Android");
      }
      appiumDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
      appiumDriverThreadLocal.set(appiumDriver);
    }
  }

  public DesiredCapabilities getAndroidDesiredCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.UDID, properties.getProperty("deviceUDID"));
    capabilities.setCapability(
        MobileCapabilityType.PLATFORM_NAME, properties.getProperty("platformName"));
    capabilities.setCapability(
        MobileCapabilityType.DEVICE_NAME, properties.getProperty("androidDeviceName"));
    capabilities.setCapability(
        MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("androidAutomationName"));
    capabilities.setCapability(
        MobileCapabilityType.NO_RESET, properties.getProperty("noResetFlag"));
    capabilities.setCapability(
        "autoGrantPermissions", properties.getProperty("autoGrantPermissionsFlag"));
    capabilities.setCapability(MobileCapabilityType.APP, properties.getProperty("appPath"));
    capabilities.setCapability("appium:appPackage", properties.getProperty("androidAppPackage"));
    capabilities.setCapability(
        "appium:appActivity", properties.getProperty("androidAppMainActivity"));
    return capabilities;
  }
}
