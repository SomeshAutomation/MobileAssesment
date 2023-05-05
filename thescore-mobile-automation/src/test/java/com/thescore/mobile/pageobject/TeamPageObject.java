package com.thescore.mobile.pageobject;

import com.thescore.mobile.utils.CommonFunction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TeamPageObject extends CommonFunction {

    @AndroidFindBy(id = "com.fivemobile.thescore:id/team_name")
    private WebElement teamPageTitle;

    @AndroidFindBy(accessibility = "Schedule")
    private WebElement scheduleSubTab;

    @AndroidFindBy(accessibility = "Team Stats")
    private WebElement teamStatsSubTab;

    public static String SUB_TAB_LAYOUT_XPATH = "//android.widget.LinearLayout[@content-desc=\"%s\"]";

    public String getTeamPageTitle() {
        return getText(teamPageTitle);
    }

    public void selectTeamStatsSubTab() {
        clickElement(teamStatsSubTab);
    }

    public boolean isTeamStatsSubTabDisplayed() {
        return isElementDisplayed(teamStatsSubTab);
    }

    public boolean isTeamStatsSubTabSelected() {
        return isElementSelected(teamStatsSubTab);
    }

    public boolean isScheduleSubTabDisplayed() {
        return isElementDisplayed(scheduleSubTab);
    }

    public void selectSubTab(String subTab) {
        clickElement(waitForVisibilityBy(By.xpath(String.format(SUB_TAB_LAYOUT_XPATH, subTab))));
    }

    public boolean isSubTabSelected(String subTab) {
        return isElementSelected(waitForVisibilityBy(By.xpath(String.format(SUB_TAB_LAYOUT_XPATH, subTab))));
    }
}
