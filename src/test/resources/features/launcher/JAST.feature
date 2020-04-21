Feature: Test JAST class

  Scenario: Test JAST can execute command
    Given scanner data:
    """
    h abc
    q
    """
    When JAST is executed
    Then exit state should be true