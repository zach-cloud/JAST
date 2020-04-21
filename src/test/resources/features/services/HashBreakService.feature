Feature: Test Hash Break service

  Scenario: Test hash break service calls correct class
    Given a hash breaker service
    When hash breaker service is executed
    Then the result should have been displayed