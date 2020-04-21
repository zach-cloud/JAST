Feature: Test the input parser

  Scenario: Test merge handler input
    Given Input to be parsed "merge src/test/resources/war3map1 src/test/resources/war3map2 output.j"
    When Merge input is parsed
    Then Tree1 should exist
    Then Tree2 should exist
    Then Output path should be "output.j"

  Scenario: Test cheatpack handler input
    Given Input to be parsed "jjcp src/test/resources/war3map1 -debug output.j"
    When Cheatpack input is parsed
    Then Tree1 should exist
    Then Activator should be "-debug"
    Then Output path should be "output.j"

  Scenario: Test rename handler input
    Given Input to be parsed "jjcp src/test/resources/war3map1 var1 var2 output.j"
    When Rename input is parsed
    Then Tree1 should exist
    Then Old Name should be "var1"
    Then New Name should be "var2"
    Then Output path should be "output.j"

  Scenario: Test hash handler input
    Given Input to be parsed "hash test"
    When Hash input is parsed
    Then Plaintext should be "test"

  Scenario: Test hashbreak handler input
    Given Input to be parsed "hashbreak 49839573"
    When Hashbreak input is parsed
    Then Hash should be "49839573"
