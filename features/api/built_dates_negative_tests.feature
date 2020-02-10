@api @built-dates @negative1
Feature: API Tests related to "car-types/built-dates"

  @authentication-check
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'POST'
    Given As a user I want to execute 'built-dates' endpoint without 'wa_key'
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'POST' request
    Then Verify response status code is '401'

  @missing-params
  Scenario: Verify list of all Car built-dates is received, when user doesn't provide any locale
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    And I as a user submit the 'GET' request
    Then Verify response status code is '400'
    And Verify error is received
      | Bad Request |

  @missing-params
  Scenario: Verify list of all Car built-dates is received, when user doesn't provide any locale
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'GET' request
    Then Verify response status code is '400'
    And Verify error is received
      | Bad Request |

  @missing-params
  Scenario: Verify list of all Car built-dates is received, when user doesn't provide any main-type
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale       | FR  |
      | manufacturer | 130 |
    And I as a user submit the 'GET' request
    Then Verify response status code is '400'
    And Verify error is received
      | Bad Request |

  @missing-params
  Scenario: Verify list of all Car built-dates is received, when user doesn't provide any manufacturer code
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale    | FR |
      | main-type | X1 |
    And I as a user submit the 'GET' request
    Then Verify response status code is '400'
    And Verify error is received
      | Bad Request |

  @request-type
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'POST'
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'POST' request
    Then Verify response status code is '405'
    And Verify error is received
      | Method Not Allowed |

  @request-type
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'PUT'
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'PUT' request
    Then Verify response status code is '403'

  @request-type
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'PATCH'
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'PATCH' request
    Then Verify response status code is '403'

  @request-type
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'DELETE'
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'DELETE' request
    Then Verify response status code is '403'

