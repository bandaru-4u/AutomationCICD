@tag
Feature: Purchase order from Ecommerce Website 

@tag
Scenario Outline: Positive Test of submitting the order
Given Logged with username <name> and password <password>
When I add product <productName> to Cart
And checkout <productName> and submit the order 
Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page

Examples:
	| name				 |password|productName|
	| mypbu2023@gmail.com|Clopay1!|ZARA COAT 3|