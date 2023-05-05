package com.thescore.mobile.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class DriverManager {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public AppiumDriver getDriver(){
        return driver.get();
    }


    public void setDriver(AppiumDriver appiumDriver){
        driver.set(appiumDriver);
    }

    public void initializeDriver() throws Exception {
        AppiumDriver driver = null;
        }

}

