package com.thescore.mobile.pageobject;


import com.thescore.mobile.utils.CommonFunction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePageObject extends CommonFunction {

  @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Download Now\")")
  private WebElement downloadNowButton;

  @AndroidFindBy(id = "com.fivemobile.thescore:id/dismiss_modal")
  private WebElement dismissButton;

  @AndroidFindBy(id = "com.fivemobile.thescore:id/search_bar_text_view")
  private WebElement searchBar;

  public static String FAV_LABEL_IN_HORIZONTAL_VIEW_XPATH =
      "//*[@resource-id=\"com.fivemobile.thescore:id/horizontal_recycler_view\"]//android.widget.TextView[@text=\"%s\"]";

  public boolean isDownloadNowButtonDisplayed() {
    return isElementDisplayed(downloadNowButton);
  }

  public void dismissDownloadBetPopUp() {
    clickElement(dismissButton);
  }

  public void selectFavFromHorizontalView(String favName) {
    clickElement(
        waitForVisibilityBy(By.xpath(String.format(FAV_LABEL_IN_HORIZONTAL_VIEW_XPATH, favName))));
  }

  public boolean isSearchBarDisplayed() {
    return isElementDisplayed(searchBar);
  }
}
