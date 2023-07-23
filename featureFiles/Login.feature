Feature: As a admin user should be able to login

  Scenario: Admin can login with valid credentials
    Given User is able to load the application
    When User enters username as "admin@email.com" and password as "admin@123" and clicks on submit button
    Then User should be able to login
  
