Feature: Test merge command

  Scenario: merge dedupe basic functions
    Given a merge dedupe command with mocked services
    When Line is executed: "merge-d src/test/resources/war3map1 src/test/resources/war3map2 out.j"
    Then mock merge service should have been executed for merge dedupe

  Scenario: merge dedupe basic functions alias
    Given a merge dedupe command with mocked services
    When Line is executed: "md src/test/resources/war3map1 src/test/resources/war3map2 out.j"
    Then mock merge service should have been executed for merge dedupe

  Scenario: merge dedupe basic functions no execution
    Given a merge dedupe command with mocked services
    When Line is executed: "md src/test/resources/war3map1 src/test/resources/war3map2 out.j other.j"
    Then mock merge service should not have been executed for merge dedupe

  Scenario: merge dedupe basic functions no execution 2
    Given a merge dedupe command with mocked services
    When Line is executed: "md2 src/test/resources/war3map1 src/test/resources/war3map2 out.j"
    Then mock merge service should not have been executed for merge dedupe