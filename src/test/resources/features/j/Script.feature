Feature: Test the Script node

  @Script
  Scenario: Test reading real j file (Sample map)
    Given input file: "war3map1"
    When J File is read
    Then J File should contain 5 variables
    Then J File should contain 12 functions

  @Script
  Scenario: Test reading real j file (SWAT Aftermath)
    Given input file: "war3map2"
    When J File is read
    Then J File should contain 507 variables
    Then J File should contain 254 functions

  @Script
  Scenario: Test reading real j file 3 (Chaos Dimension 2)
    Given input file: "war3map3"
    When J File is read
    Then J File should contain 1040 variables
    Then J File should contain 685 functions

  @Script
  Scenario: Test reading real j file 3 (NZCP)
    Given input file: "NZCP"
    When J File is read

  @Script
  Scenario: Test reading real j file 3 (JJCP)
    Given input file: "JJCP"
    When J File is read

  @Script
  Scenario: Test reading real j file 3 (FAI)
    Given input file: "FAI"
    When J File is read