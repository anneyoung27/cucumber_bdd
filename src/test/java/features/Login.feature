Feature: Login feature

  Background:
    Given SauceDemo login page is open
    And login fields are empty

  Scenario: User can't login if don't fill username and password
    When User click login button
    Then User should see an error message "Epic sadface: Username is required"

  Scenario: User can't login if don't fill username
    When User type "secret_sauce" in password field
    When User click login button
    Then User should see an error message "Epic sadface: Username is required"

  Scenario: User can't login if don't fill password
    When User type "standard_user" in username field
    When User click login button
    Then User should see an error message "Epic sadface: Password is required"

  Scenario Outline: User can login with a valid credentials
    When User type "<username>" in username field
    And User type "<password>" in password field
    When User click login button
    Then User successfully logs in and redirected to the "Products" page

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |