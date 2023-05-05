package com.thescore.mobile.steps;


import com.thescore.mobile.utils.CommonFunction;
import io.cucumber.java.en.When;

public class CommonStepDef {
  private CommonFunction commonFunction;

  public CommonStepDef() {
    commonFunction = new CommonFunction();
  }

  @When("user navigate back")
  public void userNavigateBack() {
    commonFunction.navigateBack();
  }

  public void navigatesToHomeScreen() {
    if (commonFunction.isNavigateBackDisplayed()) {
      commonFunction.navigateBack();
    }
    if (commonFunction.isFavoritesBottomNavigationDisplayed()) {
      commonFunction.selectFavorites();
    }
  }
}
