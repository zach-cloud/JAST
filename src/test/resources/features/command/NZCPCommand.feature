Feature: Test NZCP command

  Scenario: NZCP basic functions
    Given a NZCP command with mocked services
    When Line is executed: "nzcp src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should have been executed for nzcp

  Scenario: NZCP basic functions alias
    Given a NZCP command with mocked services
    When Line is executed: "nz src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should have been executed for nzcp

  Scenario: NZCP basic functions no execution
    Given a NZCP command with mocked services
    When Line is executed: "nz src/test/resources/war3map1 -wc3edit out.j other.j"
    Then mock merge service should not have been executed for nzcp

  Scenario: NZCP basic functions no execution 2
    Given a NZCP command with mocked services
    When Line is executed: "nz2 src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should not have been executed for nzcp