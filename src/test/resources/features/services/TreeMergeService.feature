Feature: Test tree merger

  Scenario: Test tree merge basic
    Given a tree merger with dedupe "false", tree 1 "src/test/resources/war3map1", tree 2 "src/test/resources/FAI", and output "out.j"
    When tree merge is executed
    Then tree file should have been written

  Scenario: Test tree merge basic with dedupe
    Given a tree merger with dedupe "true", tree 1 "src/test/resources/war3map1", tree 2 "src/test/resources/FAI", and output "out.j"
    When tree merge is executed
    Then tree file should have been written

  Scenario: Test tree jjcp basic
    Given a cheatpack merger with dedupe "false", tree 1 "src/test/resources/war3map1", activator "-wc3edit", and output "out.j"
    When jjcp merge is executed
    Then tree file should have been written

  Scenario: Test tree jjcp basic with dedupe
    Given a cheatpack merger with dedupe "true", tree 1 "src/test/resources/war3map1", activator "-wc3edit", and output "out.j"
    When jjcp merge is executed
    Then tree file should have been written

  Scenario: Test tree nzcp basic
    Given a cheatpack merger with dedupe "false", tree 1 "src/test/resources/war3map1", activator "wc3edit", and output "out.j"
    When nzcp merge is executed
    Then tree file should have been written

  Scenario: Test tree nzcp basic with dedupe
    Given a cheatpack merger with dedupe "true", tree 1 "src/test/resources/war3map1", activator "wc3edit", and output "out.j"
    When nzcp merge is executed
    Then tree file should have been written