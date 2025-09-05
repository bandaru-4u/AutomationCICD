@tag
Feature: Error Validation

@ErroValidation
Scenario Outline: Validating login functionality for incorrect credentials
Given Logged with username <name> and password <password>
Then "Incorrect email or password." message should be displayed

Examples:
	| name				 |password|
	| mypbu2023@gmail.com|ClopayA1!|