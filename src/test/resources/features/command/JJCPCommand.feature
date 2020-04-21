Feature: Test JJCP command

  Scenario: JJCP basic functions
    Given a JJCP command with mocked services
    When Line is executed: "jjcp src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should have been executed for jjcp

  Scenario: JJCP basic functions alias
    Given a JJCP command with mocked services
    When Line is executed: "jj src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should have been executed for jjcp

  Scenario: JJCP basic functions no execution
    Given a JJCP command with mocked services
    When Line is executed: "jj src/test/resources/war3map1 -wc3edit out.j other.j"
    Then mock merge service should not have been executed for jjcp

  Scenario: JJCP basic functions no execution 2
    Given a JJCP command with mocked services
    When Line is executed: "jj2 src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should not have been executed for jjcp