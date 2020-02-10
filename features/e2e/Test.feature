@api @e2e
Feature: Some test

  @positive
  Scenario Outline: Verify list of all Car manufacturer, main-types & their built-dates, when user provides locale = FR (France)
    #get all available manufacturer codes
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response
    And I store all the available car manufacturer
    #get all available manufacturer codes for locale = FR
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | <Locale> |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response
    And Verify all manufacturer codes are part of list of all available manufacturer codes

    Examples:
      | Locale |
      | FR     |
      | UK     |
      | DE     |

