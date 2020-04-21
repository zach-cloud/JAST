Feature: Test hash command

  Scenario: Hash basic functions
    Given a hash command with mocked services
    When Line is executed: "hash 123"
    Then mock hash service should have been executed

  Scenario: Hash basic functions
    Given a hash command with mocked services
    When Line is executed: "h 123"
    Then mock hash service should have been executed

  Scenario: Hash basic functions
    Given a hash command with mocked services
    When Line is executed: "abc 123"
    Then mock hash service should not have been executed

  Scenario: Hash basic functions
    Given a hash command with mocked services
    When Line is executed: "hash 123 456"
    Then mock hash service should not have been executed