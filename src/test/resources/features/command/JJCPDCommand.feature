Feature: Test JJCPD command

  Scenario: JJCPD basic functions
    Given a JJCPD command with mocked services
    When Line is executed: "jjcp-d src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should have been executed for jjcpd

  Scenario: JJCPD basic functions alias
    Given a JJCPD command with mocked services
    When Line is executed: "jjd src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should have been executed for jjcpd

  Scenario: JJCPD basic functions no execution
    Given a JJCPD command with mocked services
    When Line is executed: "jjd src/test/resources/war3map1 -wc3edit out.j other.j"
    Then mock merge service should not have been executed for jjcpd

  Scenario: JJCPD basic functions no execution 2
    Given a JJCPD command with mocked services
    When Line is executed: "jjd2 src/test/resources/war3map1 -wc3edit out.j"
    Then mock merge service should not have been executed for jjcpd