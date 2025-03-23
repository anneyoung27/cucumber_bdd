Feature: Checkout Feature

  Background:
    Given SauceDemo login page is open
    When User type "standard_user" in username field
    And User type "secret_sauce" in password field
    And User click login button

  Scenario Outline: Verify that user can enter customer information for checkout
    Then User successfully logs in and redirected to the "Products" page
    When User clicks add to cart for the Sauce Labs Backpack product
    And User clicks the shopping cart icon in the top right corner
    And The product was successfully added to the cart is visible in cart list
    When User clicks checkout button
    And User fills in "<first_name>" , "<last_name>", "<zip_code>" data
    And User clicks the continue button to proceed to checkout
    Then User should see Checkout Overview page
    Examples:
      | first_name | last_name       | zip_code |
      | Injas      | Mahendra Berutu | 99999    |


  Scenario Outline: Verify that User can complete his checkout process
    Then User successfully logs in and redirected to the "Products" page
    When User clicks add to cart for the Sauce Labs Backpack product
    And User clicks the shopping cart icon in the top right corner
    And The product was successfully added to the cart is visible in cart list
    When User clicks checkout button
    And User fills in "<first_name>" , "<last_name>", "<zip_code>" data
    And User clicks the continue button to proceed to checkout
    And User should see Checkout Overview page
    And User verifies that the order total amount are correct
    And User clicks the finish button to complete the checkout
    Then User should see a confirmation that the checkout was successful

    Examples:
      | first_name | last_name       | zip_code |
      | Injas      | Mahendra Berutu | 99999    |