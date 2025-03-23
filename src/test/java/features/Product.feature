Feature: Product Feature

  Background:
    Given SauceDemo login page is open
    When User type "standard_user" in username field
    And User type "secret_sauce" in password field
    And User click login button

  Scenario: User can see products are loaded after login successful
    Then User successfully logs in and redirected to the "Products" page
    And User should see a list of available products

  Scenario: User can add the product and the added product is visible in the cart
    Then User successfully logs in and redirected to the "Products" page
    When User clicks add to cart for the Sauce Labs Backpack product
    And User clicks the shopping cart icon in the top right corner
    Then The product was successfully added to the cart is visible in cart list

  Scenario: User want to remove product from the cart
    Then User successfully logs in and redirected to the "Products" page
    When User clicks add to cart for the Sauce Labs Backpack product
    And User clicks the shopping cart icon in the top right corner
    And User clicks REMOVE button
    Then The product was successfully removed from the cart