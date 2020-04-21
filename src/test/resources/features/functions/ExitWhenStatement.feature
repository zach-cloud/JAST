Feature: Test exitwhen statement

  @ExitwhenStatement
  Scenario: Test exitwhen basic
    Given input data:
    """
    exitwhen bc>=gc
    """
    When Exitwhen statement is read
    Then Exitwhen statement should be:
    """
    exitwhen bc >= gc
    """
    Then Exitwhen condition should be "bc >= gc"