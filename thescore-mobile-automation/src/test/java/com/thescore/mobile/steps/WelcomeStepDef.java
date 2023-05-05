package com.thescore.mobile.steps;


import com.thescore.mobile.pageobject.WelcomePageObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WelcomeStepDef {

  private WelcomePageObject welcomePageObject;

  public WelcomeStepDef() {
    welcomePageObject = new WelcomePageObject();
  }

  @When("Navigate to Home Screen")
  public void userIsOnHomeScreen() {
    log.info("I am Home Screen");
  }

  @Given("User completes onboarding screen by following {string} League and {string} Team")
  public void userCompletesOnboardingScreenByFollowingLeagueAndTeam(
      String leagueName, String teamName) {
    if (welcomePageObject.isWelcomeTextDisplayed()) {
      welcomePageObject.clickPrimaryButton();
    }
    if (welcomePageObject.isOnboardingPageTileTextDisplayed()) {
      if (!leagueName.equals(null) || !leagueName.isEmpty()) {
        welcomePageObject.selectFavTeamOrLeague(leagueName);
        welcomePageObject.clickPrimaryButton();
      }
      if (!teamName.equals(null) || !teamName.isEmpty()) {
        welcomePageObject.selectFavTeamOrLeague(teamName);
        welcomePageObject.clickPrimaryButton();
      }
      welcomePageObject.clickPrimaryButton();
    }
  }
}
