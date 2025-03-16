@Checkout
Feature: Proceed to checkout functionality feature
  Background:
    Given User navigates to the sauce demo website
    When User verify that the login page is visible

  @Checkout
  Scenario Outline: Proceed to checkout the product
    And User enters username "standard_user"
    And User enters password "secret_sauce"
    Then User click login button
    And User should see "Products" page
    Then User verify that the product page is visible
    And User click add to cart button on the first product
    And User click on the shopping cart icon in the top right corner
    Then User click checkout button
    And User verify that the checkout page is visible
    Then User write his information <firstname>, <lastname>, <zipCode>
    And User click continue button to proceed the checkout
    Then User verify that the Checkout preview page is visible
    And User click finish button to checkout the product

    Examples:
      | firstname      | lastname | zipCode |
      | Injas Mahendra | Berutu   | 12399   |