Feature: Test Hash service

  Scenario: Test hash service calls correct class
    Given a hash service
    When hash service is executed
    Then the hash result should have been displayed