Feature: Test Cheatpack Loader

  Scenario: NZCP Loads Successfully
    When "NZCP.j" is loaded
    Then Cheatpack is loaded successfully

  Scenario: JJCP Loads Successfully
    When "JJCP.j" is loaded
    Then Cheatpack is loaded successfully

  Scenario: FAI Loads Successfully
    When "FAI.j" is loaded
    Then Cheatpack is loaded successfully

  Scenario: Invalid cheatpack does not load
    When "noCheatpack.j" is loaded
    Then Cheatpack is not loaded successfully