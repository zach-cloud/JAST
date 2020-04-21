Feature: Test the Statements node

  @Statements
  Scenario: Test reading Statements basic
    Given input data:
    """
    call ConditionalTriggerExecute(gg_trg_trig2)
    call DisplayTextToForce(GetPlayersAll(),udg_myVar)
    set udg_myVar = 3
    """
    When Statements are read
    Then Statements should be:
    """
    call ConditionalTriggerExecute(gg_trg_trig2)
    call DisplayTextToForce(GetPlayersAll(),udg_myVar)
    set udg_myVar = 3
    """
    Then Statements should have 3 statements

  @Statements
  Scenario: Test reading Statements with returns
    Given input data:
    """
    set bf=WA
    set gf=AA
    set vf=YA
    call TriggerEvaluate(hf[1])
    return TriggerRegisterVariableEvent(WA,AA,qA,YA)
    """
    When Statements are read
    Then Statements should be:
    """
    set bf = WA
    set gf = AA
    set vf = YA
    call TriggerEvaluate(hf[1])
    return TriggerRegisterVariableEvent(WA,AA,qA,YA)
    """
    Then Statements should have 5 statements

  @Statements
  Scenario: Test reading Statements with nested if
    Given input data:
    """
    if a == b then
      if c == d then
        if e == f then
          call helloWorld(1)
        endif
      endif
    endif
    """
    When Statements are read
    Then Statements should be:
    """
    if a == b then
    if c == d then
    if e == f then
    call helloWorld(1)
    endif
    endif
    endif
    """
    Then Statements should have 1 statements