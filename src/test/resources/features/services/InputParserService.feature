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

  Scenario: Test optimizer handler input
    Given Input to be parsed "optimize src/test/resources/war3map1 output.j"
    When Optimize input is parsed
    Then Tree1 should exist
    Then Optimize Output should be "output.j"

  Scenario: Test rawcode handler input
    Given Input to be parsed "rawcodes war3map.w3t out/rawcodes.txt"
    When Rawcode input is parsed
    Then Rawcode input should be "war3map.w3t"
    Then Rawcode output should be "out/rawcodes.txt"

  Scenario: Test rawcode handler input with wts
    Given Input to be parsed "rawcodes war3map.w3t out/rawcodes.txt war3map.wts"
    When Rawcode input is parsed
    Then Rawcode input should be "war3map.w3t"
    Then Rawcode output should be "out/rawcodes.txt"
    Then Rawcode wtsfile should be "war3map.wts"