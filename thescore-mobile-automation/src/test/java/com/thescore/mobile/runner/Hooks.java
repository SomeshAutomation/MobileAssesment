package com.thescore.mobile.runner;

import com.thescore.mobile.steps.CommonStepDef;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Hooks {
    public static final int IMPLICIT_WAIT = 5;
    public static Properties properties = new Properties();
    public static final String CONFIG_FILE_NAME = "config.properties";
    static AppiumDriver appiumDriver;

    @BeforeAll
    public static void setUp() throws IOException {
        properties.load(Hooks.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        log.info("in the Before All Hook");
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.UDID, properties.getProperty("deviceUDID"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("androidDeviceName"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("androidAutomationName"));
        capabilities.setCapability(MobileCapabilityType.NO_RESET, properties.getProperty("noResetFlag"));
        capabilities.setCapability("autoGrantPermissions", properties.getProperty("autoGrantPermissionsFlag"));
        capabilities.setCapability(MobileCapabilityType.APP, properties.getProperty("appPath"));
        capabilities.setCapability("appium:appPackage", properties.getProperty("androidAppPackage"));
        capabilities.setCapability("appium:appActivity", properties.getProperty("androidAppMainActivity"));
        appiumDriver = new AndroidDriver(new URL(properties.getProperty("appiumServerURL")), capabilities);
        appiumDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public static AppiumDriver getAppiumDriver() {
        return appiumDriver;
    }

    @After
    public void tearDown() {
        takeScreenshot();
        // To make sure every new test case start from Home Screen
        new CommonStepDef().navigatesToHomeScreen();
    }

    @AfterAll
    public static void teardown() {
        //close the app
        appiumDriver.quit();
    }

    public static void takeScreenshot() {
        log.info("taking screen shot.");
        byte[] screenShot = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(
                "Screenshot for scenario ",
                new ByteArrayInputStream(screenShot));
    }


}
