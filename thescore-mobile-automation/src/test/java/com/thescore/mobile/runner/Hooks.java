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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Hooks {
    public static final int IMPLICIT_WAIT=5;
    static AppiumDriver appiumDriver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        log.info("in the Before All Hook");
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.UDID, "4347445145353098");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "SM-G965W");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/somesh/Documents/Documents/Learning/TheScore/theScore_ Sports News & Scores_23.5.0_Apkpure.apk");
        capabilities.setCapability("appium:appPackage", "com.fivemobile.thescore");
        capabilities.setCapability("appium:appActivity", "com.fivemobile.thescore.ui.MainActivity");
        appiumDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
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
