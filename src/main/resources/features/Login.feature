@Login
Feature: Login valid feature
  Background:
    Given User navigates to the sauce demo website
    When User verify that the login page is visible

  @Login
  Scenario: User login
    Given User navigates to the sauce demo website
    When User verify that the login page is visible
    And User enters username "standard_user"
    And User enters password "secret_sauce"
    Then User click login button
    And User should see "Products" page