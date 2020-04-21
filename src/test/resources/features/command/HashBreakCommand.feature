Feature: Test hash breaker command

  Scenario: Hash breaker basic functions
    Given a hash breaker command with mocked services
    When Line is executed: "hashbreak 123"
    Then mock hash breaker service should have been executed

  Scenario: Hash breaker basic functions alias
    Given a hash breaker command with mocked services
    When Line is executed: "hb 123"
    Then mock hash breaker service should have been executed

  Scenario: Hash breaker no execution 1
    Given a hash breaker command with mocked services
    When Line is executed: "abc 123"
    Then mock hash breaker service should not have been executed

  Scenario: Hash breaker no execution
    Given a hash breaker command with mocked services
    When Line is executed: "hb 123 456"
    Then mock hash breaker service should not have been executed