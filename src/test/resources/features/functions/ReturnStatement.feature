Feature: Test return statement

  @ReturnStatement
  Scenario: Test return statement basic
    Given input data:
    """
    return TriggerRegisterVariableEvent(WA,AA,qA,YA)
    """
    When Return statement is read
    Then Return statement should be:
    """
    return TriggerRegisterVariableEvent(WA,AA,qA,YA)
    """
    Then Return statement body should be: "TriggerRegisterVariableEvent(WA,AA,qA,YA)"

  @ReturnStatement
  Scenario: Test return statement comparison operator
    Given input data:
    """
    return ( dx * dx + dy * dy <= bj_enumDestructableRadius )
    """
    When Return statement is read
    Then Return statement should be:
    """
    return (dx * dx + dy * dy <= bj_enumDestructableRadius)
    """