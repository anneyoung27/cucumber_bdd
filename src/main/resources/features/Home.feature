@AddProduct
Feature: Add Product functionality feature
  Background:
    Given User navigates to the sauce demo website
    When User verify that the login page is visible

  @AddProduct
  Scenario: Add Product
    Given User navigates to the sauce demo website
    When User verify that the login page is visible
    And User enters username "standard_user"
    And User enters password "secret_sauce"
    Then User click login button
    And User should see "Products" page
    Then User verify that the product page is visible
    And User click add to cart button on the first product
    And User click on the shopping cart icon in the top right corner
    And User verify that added product is exists in the cart
    Then User click continue shopping button to add more product
    And User click second product to cart
    And User click shopping cart
    And User click remove button that added product in the cart
