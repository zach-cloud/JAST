Feature: Test NZCPD command

  Scenario: NZCPD basic functions
    Given a NZCPD command with mocked services
    When Line is executed: "nzcp-d src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should have been executed for nzcpd

  Scenario: NZCP basic functions alias
    Given a NZCPD command with mocked services
    When Line is executed: "nzd src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should have been executed for nzcpd

  Scenario: NZCP basic functions no execution
    Given a NZCPD command with mocked services
    When Line is executed: "nzd src/test/resources/war3map1 -wc3edit out.j other.j"
    Then mock merge service should not have been executed for nzcpd

  Scenario: NZCP basic functions no execution 2
    Given a NZCPD command with mocked services
    When Line is executed: "nzd2 src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should not have been executed for nzcpd