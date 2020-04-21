Feature: Test quit command

  Scenario: Quit basic functions
    Given a quit command with mocked services
    When Line is executed: "quit"
    Then mock quit service should have been executed

  Scenario: Quit basic functions no execution
    Given a quit command with mocked services
    When Line is executed: "quit2"
    Then mock quit service should not have been executed