Feature: Test help command

  Scenario: Help basic functions
    Given a help command with mocked services
    When Line is executed: "help"
    Then mock output help service should have been executed

  Scenario: Help basic functions
    Given a help command with mocked services
    When Line is executed: "help2"
    Then mock output help service should not have been executed