@api @manufacturer
Feature: Some test

  @positive
  Scenario: sometest
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR                           |
      | wa_key | coding-puzzle-client-449cc9d |
    And User submits the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response

  @negative
  Scenario: Verify User provides request Type as 'POST'
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR                           |
      | wa_key | coding-puzzle-client-449cc9d |
    And User submits the 'GET' request
    Then Verify response status code is '400'
    And Verify error is received
    |Bad Request|

