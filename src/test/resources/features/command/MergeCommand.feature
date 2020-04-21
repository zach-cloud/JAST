Feature: Test merge command

  Scenario: merge basic functions
    Given a merge command with mocked services
    When Line is executed: "merge src/test/resources/war3map1 src/test/resources/war3map2 out.j"
    Then mock merge service should have been executed for merge

  Scenario: merge basic functions alias
    Given a merge command with mocked services
    When Line is executed: "m src/test/resources/war3map1 src/test/resources/war3map2 out.j"
    Then mock merge service should have been executed for merge

  Scenario: merge basic functions no execution
    Given a merge command with mocked services
    When Line is executed: "m src/test/resources/war3map1 src/test/resources/war3map2 out.j other.j"
    Then mock merge service should not have been executed for merge

  Scenario: merge basic functions no execution 2
    Given a merge command with mocked services
    When Line is executed: "m2 src/test/resources/war3map1 src/test/resources/war3map2 out.j"
    Then mock merge service should not have been executed for merge