package com.thescore.mobile.steps;


import com.thescore.mobile.pageobject.HomePageObject;
import com.thescore.mobile.pageobject.LeaguePageObject;
import com.thescore.mobile.pageobject.TeamPageObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Slf4j
public class HomeStepDef {

  private HomePageObject homePageObject;
  private LeaguePageObject leaguePageObject;
  private TeamPageObject teamPageObject;
  public static final String TEAM = "TEAM";
  public static final String LEAGUE = "LEAGUE";

  public HomeStepDef() {

    homePageObject = new HomePageObject();
    leaguePageObject = new LeaguePageObject();
    teamPageObject = new TeamPageObject();
  }

  @When("User is on Home Screen")
  public void userIsOnHomeScreen() {
    if (homePageObject.isDownloadNowButtonDisplayed()) {
      homePageObject.dismissDownloadBetPopUp();
    }
    if (!homePageObject.isFavoritesBottomNavigationSelected()) {
      homePageObject.selectFavorites();
    }
    log.info("I am on Home Screen");
  }

  @Given("User select {string} and {string}")
  public void userSelectAnd(String arg0, String arg1) {
    log.info("I am User select");
  }

  @And("User is already logged in")
  public void userIsAlreadyLoggedIn() {
    log.info("I am ser is already logged");
  }

  @When("user selects {string} {string} from Favorites List on Home screen")
  public void userSelectsFromFavoritesListOnHomeScreen(String entity, String type) {
    homePageObject.selectFavFromHorizontalView(entity);
  }

  @Then("user is on {string} screen of {string}")
  public void userIsOnScreenOf(String title, String type) {
    if (type.equals(TEAM)) {
      Assert.assertEquals(
          teamPageObject.getTeamPageTitle(),
          title,
          "User navigate to correct Team screen with title:");
    } else if (type.equals(LEAGUE)) {
      Assert.assertEquals(
          leaguePageObject.getPageTitleText(),
          title,
          " User navigates to correct League screen with title:");
    } else {
      Assert.fail("Incorrect Type");
    }
  }

  @When("user selects {string} sub tab on {string} screen")
  public void userSelectsSubTabOnScreen(String subTab, String type) {
    if (type.equals(TEAM)) {
      teamPageObject.selectSubTab(subTab);
    } else if (type.equals(LEAGUE)) {
      leaguePageObject.selectSubTab(subTab);
    } else {
      Assert.fail("Incorrect Type");
    }
  }

  @Then("user {string} sub-tab for {string} type is selected")
  public void userSubTabForTypeIsSelected(String subTab, String type) {
    if (type.equals(TEAM)) {
      Assert.assertTrue(teamPageObject.isSubTabSelected(subTab), "user is on correct sub tab");
    } else if (type.equals(LEAGUE)) {
      Assert.assertTrue(leaguePageObject.isSubTabSelected(subTab), "user is on correct sub tab");
    } else {
      Assert.fail("Incorrect Type");
    }
  }

  @Then("Home screen is displayed")
  public void homeScreenIsDisplayed() {
    Assert.assertTrue(homePageObject.isSearchBarDisplayed(), " user is on Home Screen");
  }
}
