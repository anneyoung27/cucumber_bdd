Feature: Product Feature

  Background:
    Given SauceDemo login page is open
    And User successfully logs in and redirected to the "Products" page

  Scenario: User can see products are loaded after login successful
    Then User should see a list of available products

  Scenario: User can add the product and the added product is visible in the cart
    When User clicks add to cart for the Sauce Labs Backpack product
    And User clicks the shopping cart icon in the top right corner
    Then The product was successfully added to the cart is visible in cart list

  Scenario: User want to add more product to the cart
    When User clicks CONTINUE SHOPPING button
    And User clicks add to cart for the Sauce Labs Bike Light product
    And User clicks the shopping cart icon in the top right corner
    Then The product was successfully added to the cart is visible in cart list

  Scenario: User want to remove product from the cart
    When User clicks the shopping cart icon in the top right corner
    And User clicks REMOVE button
    Then The product was successfully removed from the cart