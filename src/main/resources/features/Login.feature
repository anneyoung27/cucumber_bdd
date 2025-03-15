Feature: User Login with Valid Credentials
  In order to access an account successfully
  As a User must enter correctly the username and password

  Scenario Outline: Successful User Login
    Given User navigates to the saucedemo website
    When User verify that the login page is visible
    And User enters with "<username>" valid username
    And User enters with "<password>" valid password
    Then User click login button
    And User close the browser

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |