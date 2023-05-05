package com.thescore.mobile.pageobject;


import com.thescore.mobile.utils.CommonFunction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LeaguePageObject extends CommonFunction {

  @AndroidFindBy(id = "com.fivemobile.thescore:id/titleTextView")
  private WebElement pageTitleText;

  @AndroidFindBy(accessibility = "Standings")
  private WebElement standingsSubTab;

  @AndroidFindBy(accessibility = "Chat")
  private WebElement chatSubTab;

  @AndroidFindBy(accessibility = "Leaders")
  private WebElement leadersSubTab;

  public static String SUB_TAB_LAYOUT_XPATH = "//android.widget.LinearLayout[@content-desc=\"%s\"]";

  public String getPageTitleText() {
    return getText(pageTitleText);
  }

  public void selectStandingsSubTab() {
    clickElement(standingsSubTab);
  }

  public void selectChatSubTab() {
    clickElement(chatSubTab);
  }

  public void selectLeadersSubTab() {
    clickElement(leadersSubTab);
  }

  public boolean isStandingSubTabDisplayed() {
    return isElementDisplayed(standingsSubTab);
  }

  public boolean isLeadersSubTabDisplayed() {
    return isElementDisplayed(leadersSubTab);
  }

  public boolean isStandingSubTabSelected() {
    return isElementSelected(standingsSubTab);
  }

  public void selectSubTab(String subTab) {
    clickElement(waitForVisibilityBy(By.xpath(String.format(SUB_TAB_LAYOUT_XPATH, subTab))));
  }

  public boolean isSubTabSelected(String subTab) {
    return isElementSelected(
        waitForVisibilityBy(By.xpath(String.format(SUB_TAB_LAYOUT_XPATH, subTab))));
  }
}
