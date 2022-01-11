Feature: Validate pet store functionaility

Scenario: Add selected product to cart

Given Access Product selection url is accessed 
When select first product "Sony vaio i5" from displayed product
Then Add first product to cart
And  Validate that correct first product "Sony vaio i5" is selected
Then Navigate to product selection home page
And select second product "Dell i7 8gb" from displayed product
Then Add second product to cart
And  Validate that correct second product "Dell i7 8gb" is selected
Then Navigate to cart page
Then Delete second product "Dell i7 8gb" from cart selection and Validate deleted product
Then Place the order
And Fill form details with name "name" and country "country" and city "city" and card "3435" and month "12" and year "2022" 
And Purcase the Order
And Validate purchase id and amount in purchase order confirmation message
Then Close the Browser
And Update test data file
And Generate HTML report


