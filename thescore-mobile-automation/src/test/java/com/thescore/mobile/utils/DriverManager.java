package com.thescore.mobile.utils;


import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DriverManager {
  private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

  public AppiumDriver getDriver() {
    return driver.get();
  }

  public void setDriver(AppiumDriver appiumDriver) {
    driver.set(appiumDriver);
  }

  public void initializeDriver() throws Exception {
    AppiumDriver driver = null;
  }
}
