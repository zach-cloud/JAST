Feature: Test rename string command

  Scenario: Rename string basic functions
    Given a rename string command with mocked services
    When Line is executed: "replacestring src/test/resources/war3map1 oldName newName out.j"
    Then mock replace service should have been executed for string

  Scenario: Rename string basic functions alias
    Given a rename string command with mocked services
    When Line is executed: "rs src/test/resources/war3map1 oldName newName out.j"
    Then mock replace service should have been executed for string

  Scenario: Rename string basic functions no execution
    Given a rename string command with mocked services
    When Line is executed: "renamestring src/test/resources/war3map1 asdf oldName newName out.j"
    Then mock replace service should not have been executed for string

  Scenario: Rename string basic functions no execution 2
    Given a rename string command with mocked services
    When Line is executed: "renamestring2 src/test/resources/war3map1 oldName newName out.j"
    Then mock replace service should not have been executed for string