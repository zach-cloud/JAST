Feature: Test random name generator

  Scenario: Generate a random name
    Given 10000 randomly generated names
    Then there should be no duplicates