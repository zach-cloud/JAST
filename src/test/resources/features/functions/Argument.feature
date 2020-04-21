Feature: Test single argument

  @Argument
  Scenario: Test basic argument
    Given input data:
    """
    TimerUtils___ht
    """
    When Argument is read
    Then Argument should be:
    """
    TimerUtils___ht
    """

  @Argument
  Scenario: Test complex argument
    Given input data:
    """
    GetHandleId((s__TimerUtils___tT[i] ))
    """
    When Argument is read
    Then Argument should be:
    """
    GetHandleId(s__TimerUtils___tT[i])
    """

  @Argument
  Scenario: Test basic argument with parenthesis
    Given input data:
    """
    ( TimerUtils___HELD)
    """
    When Argument is read
    Then Argument should be:
    """
    TimerUtils___HELD
    """

  @Argument
  Scenario: Test malformed function parenthesis
    Given input data:
    """
    GetLocationX(CBE) + CCE * Cos(CDE * bj_DEGTORAD)
    """
    When Argument is read
    Then Argument should be:
    """
    GetLocationX(CBE) + CCE * Cos(CDE * bj_DEGTORAD)
    """

  @Argument
  Scenario: Test argument with or
    Given input data:
    """
    GetRandomInt(1,100)<=sOv+5*xMo(wdx((No[(PSx)]),'odef')or wdx((No[(PSx)]),'rej6'))
    """
    When Argument is read
    Then Argument should be:
    """
    GetRandomInt(1,100) <= sOv + 5 * xMo(wdx(No[(PSx)],'odef') or wdx(No[(PSx)],'rej6'))
    """

  @Argument
  Scenario: Test not statement
    Given input data:
    """
    not GetBooleanOr(Func0002(),Func0003())
    """
    When Argument is read
    Then Argument should be:
    """
    not (GetBooleanOr(Func0002(),Func0003()))
    """