Feature: Test rename variable command

  Scenario: Rename variable basic functions
    Given a rename variable command with mocked services
    When Line is executed: "renamevariable src/test/resources/war3map1 oldName newName out.j"
    Then mock replace service should have been executed for variable

  Scenario: Rename variable basic functions alias
    Given a rename variable command with mocked services
    When Line is executed: "rv src/test/resources/war3map1 oldName newName out.j"
    Then mock replace service should have been executed for variable

  Scenario: Rename variable basic functions no execution
    Given a rename variable command with mocked services
    When Line is executed: "renamevariable src/test/resources/war3map1 asdf oldName newName out.j"
    Then mock replace service should not have been executed for variable

  Scenario: Rename variable basic functions no execution 2
    Given a rename variable command with mocked services
    When Line is executed: "renamevariable2 src/test/resources/war3map1 oldName newName out.j"
    Then mock replace service should not have been executed for variable