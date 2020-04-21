Feature: Test WTS File Node

  @WtsFile
  Scenario: Test reading real wts file
    Given input file: "war3map.wts"
    When WTS File is read
    Then WTS File should contain 1680 strings