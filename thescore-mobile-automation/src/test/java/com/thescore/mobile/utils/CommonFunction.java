package com.thescore.mobile.utils;

import com.thescore.mobile.runner.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonFunction {

    @AndroidFindBy(accessibility = "Navigate up")
    private WebElement backNavigationButton;

    @AndroidFindBy(accessibility = "Favorites")
    private WebElement favoritesBottomNavigation;

    @AndroidFindBy(accessibility = "Leagues")
    private WebElement leaguesBottomNavigation;
    public AppiumDriver driver;
    private WebDriverWait explicitShortWait;
    private WebDriverWait explicitLongWait;

    public static final long SHORT_WAIT_TIME = 3;
    public static final long LONG_WAIT_TIME = 7;

    public CommonFunction() {
        this.driver = Hooks.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        explicitShortWait = new WebDriverWait(driver, Duration.ofSeconds(SHORT_WAIT_TIME));
        explicitLongWait = new WebDriverWait(driver, Duration.ofSeconds(LONG_WAIT_TIME));
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public void waitForVisibility(WebElement e) {
        explicitLongWait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForVisibilityShortWait(WebElement e) {
        explicitShortWait.until(ExpectedConditions.visibilityOf(e));
    }

    public WebElement waitForVisibilityBy(By e) {
        return explicitShortWait.until(ExpectedConditions.visibilityOfElementLocated(e));
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    public void navigateBack() {
        clickElement(backNavigationButton);
    }

    public boolean isNavigateBackDisplayed() {
        return isElementDisplayed(backNavigationButton);
    }

    public boolean isFavoritesBottomNavigationDisplayed() {
        return isElementDisplayed(favoritesBottomNavigation);
    }

    public boolean isFavoritesBottomNavigationSelected() {
        return isElementSelected(favoritesBottomNavigation);
    }

    public void selectFavorites() {
        clickElement(favoritesBottomNavigation);
    }

    public void selectLeagues() {
        clickElement(leaguesBottomNavigation);
    }
}
