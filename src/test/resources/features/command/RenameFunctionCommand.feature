Feature: Test rename function command

  Scenario: Rename function basic functions
    Given a rename function command with mocked services
    When Line is executed: "renamefunction src/test/resources/war3map1 oldName newName out.j"
    Then mock replace service should have been executed for function

  Scenario: Rename function basic functions alias
    Given a rename function command with mocked services
    When Line is executed: "rf src/test/resources/war3map1 oldName newName out.j"
    Then mock replace service should have been executed for function

  Scenario: Rename function basic functions no execution
    Given a rename function command with mocked services
    When Line is executed: "renamefunction src/test/resources/war3map1 asdf oldName newName out.j"
    Then mock replace service should not have been executed for function

  Scenario: Rename function basic functions no execution 2
    Given a rename function command with mocked services
    When Line is executed: "renamefunction2 src/test/resources/war3map1 oldName newName out.j"
    Then mock replace service should not have been executed for function