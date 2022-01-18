Feature: Validate pet store functionaility


Scenario: Get  pet details based on status

Given Access get pet store method 

When Get pet information based on status "available" method  executed 

Then Validate response status code for get pet status method execution

Then Validate the reponse body for get pet status method execution

Then Validate the response header for get pet status method execution

Scenario: Create new pet

Given Access create pet store method 
When Create new pet method  executed with id "121" and name "name_pet_121" and status "available"

Then Validate response status code for Create new pet execution

Then Validate the reponse body for Create new pet execution
Then Validate the response header for Create new pet method execution




Scenario: Update new pet

Given Access Update pet store method 
When Update new pet method  executed with id "121" and name "name_pet_121" and field "status" with "sold"

Then Validate response status code "200" for update new pet execution

Then Validate the reponse body with id "121" for update new pet execution

Then Validate the response header for update new pet method execution

Scenario: Delete pet information

Given Access delete pet store method 
When Delete pet method  executed with id "121" 
Then Validate response status code "200" for delete pet execution
Then Validate the reponse body with message "deleted" for delete pet execution
Then Validate the response header for delete pet method execution


