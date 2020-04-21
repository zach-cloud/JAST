Feature: Test optimize gui command

  Scenario: Optimize gui basic functions
    Given an optimize gui command with mocked services
    When Line is executed: "optimize src/test/resources/war3map1 out/war3map.j"
    Then mock optimizer service should have been executed

  Scenario: Optimize gui basic functions
    Given an optimize gui command with mocked services
    When Line is executed: "opt src/test/resources/war3map1 out/war3map.j"
    Then mock optimizer service should have been executed

  Scenario: Optimize gui should not trigger
    Given an optimize gui command with mocked services
    When Line is executed: "abc 123"
    Then mock optimizer service should not have been executed

  Scenario: Optimize gui should not trigger 2
    Given an optimize gui command with mocked services
    When Line is executed: "hash 123 456"
    Then mock optimizer service should not have been executed