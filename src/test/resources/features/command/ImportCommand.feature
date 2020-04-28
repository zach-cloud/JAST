Feature: Test Import command

  Scenario: Import basic functions
    Given An import command with mocked services
    When Line is executed: "import war3map.w3x inFiles"
    Then Mock mpq import service should have been executed

  Scenario: Import basic functions alias
    Given An import command with mocked services
    When Line is executed: "imp war3map.w3x inFiles"
    Then Mock mpq import service should have been executed

  Scenario: Import basic no execution 1
    Given An import command with mocked services
    When Line is executed: "abc war3map.w3x inFiles"
    Then Mock mpq import service should not have been executed

  Scenario: Import basic no execution 2
    Given An import command with mocked services
    When Line is executed: "imp war3map.w3x"
    Then Mock mpq import service should not have been executed