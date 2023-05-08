package com.thescore.mobile;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    plugin = {
      "pretty",
      "html:target/results/cucumber-reports.html",
      "json:target/results/cucumber.json",
      "junit:target/results/cucumber.xml",
      "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
      "timeline:target/results/timeline"
    },
    glue = "com/thescore/mobile",
    monochrome = true,
    tags = "@FeatureName")
public class TestRunner {}
