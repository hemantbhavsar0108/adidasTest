$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/features/SelectProductsAndAddToCart.feature");
formatter.feature({
  "name": "Validate pet store functionaility",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Add selected product to cart",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "Access Product selection url is accessed",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.access_Product_selection_url_is_accessed()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "select first product \"Sony vaio i5\" from displayed product",
  "keyword": "When "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.select_first_product_from_displayed_product(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Add first product to cart",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.add_first_product_to_cart()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validate that correct first product \"Sony vaio i5\" is selected",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.validate_that_correct_first_product_is_selected(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Navigate to product selection home page",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.navigate_to_product_selection_home_page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "select second product \"Dell i7 8gb\" from displayed product",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.select_second_product_from_displayed_product(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Add second product to cart",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.add_second_product_to_cart()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validate that correct second product \"Dell i7 8gb\" is selected",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.validate_that_correct_second_product_is_selected(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Navigate to cart page",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.navigate_to_cart_page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Delete second product \"Dell i7 8gb\" from cart selection and Validate deleted product",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.delete_second_product_from_cart_selection_and_Validat_deleted_product(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Place the order",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.place_the_order()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Fill form details with name \"name\" and country \"country\" and city \"city\" and card \"3435\" and month \"12\" and year \"2022\"",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.fill_form_details_with_name_and_country_and_city_and_card_and_month_and_year(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Purcase the Order",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.purcase_the_Order()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validate purchase id and amount in purchase order confirmation message",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.validate_purchase_id_and_amount_in_purchase_order_confirmation_message()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Close the Browser",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.close_the_Browser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Update test data file",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.update_test_data_file()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Generate HTML report",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinition.StepDefinition.generate_HTML_report()"
});
formatter.result({
  "status": "passed"
});
});