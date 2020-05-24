Feature: Test syntax tree

  @Script
  Scenario: Test reading real j file (Sample Map)
    Given tree file "war3map1"
    Then there should be no syntax error

  @Script
  Scenario: Test reading real j file (SWAT Aftermath)
    Given tree file "war3map2"
    Then there should be no syntax error