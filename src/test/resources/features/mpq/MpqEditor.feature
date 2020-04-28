Feature: Test MPQ Editor

  Scenario: Test MPQ Editor extraction
    Given A mock MPQ editor with file "war3map.w3a"
    When MPQ is extracted
    Then File "war3map.w3a" should have been exported

  Scenario: Test MPQ Editor import
    Given A mock MPQ editor with file ""
    When MPQ is imported from directory "src/test/resources/extractedFiles"
    Then File "war3map.w3t" should have been imported