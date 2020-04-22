Feature: Test rawcode generation

  Scenario: Rawcode generate basic
    Given a rawcode generate command with mocked services
    When Line is executed: "rawcodes src/test/resources/war3map.w3t out/rawcodes.txt"
    Then mock rawcode generator service should have been executed

  Scenario: Rawcode generate basic with wts
    Given a rawcode generate command with mocked services
    When Line is executed: "rawcodes src/test/resources/war3map.w3t out/rawcodes.txt war3map.wts"
    Then mock rawcode generator service should have been executed

  Scenario: Rawcode generate basic alias
    Given a rawcode generate command with mocked services
    When Line is executed: "rc src/test/resources/war3map.w3t out/rawcodes.txt"
    Then mock rawcode generator service should have been executed

  Scenario: Rawcode generate should not trigger 1
    Given a rawcode generate command with mocked services
    When Line is executed: "abc 123"
    Then mock rawcode generator service should not have been executed

  Scenario: Rawcode generate should not trigger 1
    Given a rawcode generate command with mocked services
    When Line is executed: "hash 123 456"
    Then mock rawcode generator service should not have been executed