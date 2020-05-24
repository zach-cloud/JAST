Feature: Test syntax checker

  Scenario: Test good syntax returns no errors
    Given a new syntax checker
    When file "war3map1" is checked for errors
    Then there should be no syntax errors

  Scenario: Test bad syntax returns errors
    Given a new syntax checker
    When file "badSyntax1" is checked for errors
    Then there should be syntax errors