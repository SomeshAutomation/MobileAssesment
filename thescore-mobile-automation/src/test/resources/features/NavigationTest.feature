@Navigation
Feature: League, Team , Player and sub tab Navigation test

  Background:
    Given User completes onboarding screen by following "NHL Hockey" League and "Toronto Maple Leafs" Team
    And User is on Home Screen

  @TestID-1234 @FeatureName
  Scenario Outline: Verify "<type>" navigation to "<sub-tab>" sub tab and navigate back
    When user selects "<entity>" "<type>" from Favorites List on Home screen
    Then user is on "<page title>" screen of "<type>"
    When user selects "<sub-tab>" sub tab on "<type>" screen
    Then user "<sub-tab>" sub-tab for "<type>" type is selected
    When user navigate back
    Then Home screen is displayed
    Examples:
      | entity | type   | page title          | sub-tab    |
      | TOR    | TEAM   | Toronto Maple Leafs | Team Stats |
      | NHL    | LEAGUE | NHL                 | Standings  |
