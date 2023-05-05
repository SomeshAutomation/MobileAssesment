package com.thescore.mobile.pageobject;

import com.thescore.mobile.utils.CommonFunction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WelcomePageObject extends CommonFunction {

    @AndroidFindBy(id = "com.fivemobile.thescore:id/btn_primary")
    private WebElement primaryButton;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/txt_welcome")
    private WebElement welcomeText;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/title_onboarding")
    private WebElement onboardingTitleText;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/subtitle_onboarding")
    private WebElement onboardingSubTitleText;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"NHL Hockey\")")
    private WebElement nhlHockeyText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"NHL Hockey\"]/following-sibling::android.widget.ImageView")
    private WebElement nhlFollowIcon;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/search_bar_placeholder")
    private WebElement searchBar;

    public static String FAV_TEAM_NAME_UI_AUTOMATOR = "new UiSelector().text(\"%s\")";

    public static String FAV_TEAM_LEAGUE_FOLLOW_ICON_XPATH = "//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.ImageView";

    public WelcomePageObject() {
    }

    public void clickPrimaryButton() {
        clickElement(primaryButton);
    }

    public void clickNHLFollowIcon() {
        clickElement(nhlFollowIcon);
    }

    public String getOnboardingPageTitleText() {
        return getText(onboardingTitleText);
    }

    public boolean isOnboardingPageTileTextDisplayed() {
        return isElementDisplayed(onboardingTitleText);
    }

    public boolean isWelcomeTextDisplayed() {
        return isElementDisplayed(welcomeText);
    }

    public void selectFavTeamOrLeague(String name) {
        clickElement(waitForVisibilityBy(By.xpath(String.format(FAV_TEAM_LEAGUE_FOLLOW_ICON_XPATH, name))));
    }
}
