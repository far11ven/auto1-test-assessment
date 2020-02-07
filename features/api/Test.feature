@api @e2e
Feature: Some test

  @positive
  Scenario: sometest
    Given As as a user I want to execute 'car-types/manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR                           |
      | wa_key | coding-puzzle-client-449cc9d |
    And User submits the 'GET' request
    Then Verify response status code is '200'

