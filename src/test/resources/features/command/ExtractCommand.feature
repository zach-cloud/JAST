Feature: Test Extract command

  Scenario: Extract basic functions
    Given An extract command with mocked services
    When Line is executed: "extract war3map.w3x outFiles"
    Then Mock mpq extract service should have been executed

  Scenario: Extract basic functions alias
    Given An extract command with mocked services
    When Line is executed: "ext war3map.w3x outFiles"
    Then Mock mpq extract service should have been executed

  Scenario: Extract basic no execution 1
    Given An extract command with mocked services
    When Line is executed: "abc war3map.w3x outFiles"
    Then Mock mpq extract service should not have been executed

  Scenario: Extract basic no execution 2
    Given An extract command with mocked services
    When Line is executed: "ext war3map.w3x"
    Then Mock mpq extract service should not have been executed