Feature: Test the Loop Statement node

  @LoopStatement
  Scenario: Test loop statements basic
    Given input data:
    """
    loop
    exitwhen bc>=gc
    set vc=Fc[bc]*Fc[bc]*bc+Fc[bc]*vc+vc+$c7
    set bc=bc+1
    endloop
    """
    When Loop statement is read
    Then Loop Statement should be:
    """
    loop
    exitwhen bc >= gc
    set vc = Fc[bc] * Fc[bc] * bc + Fc[bc] * vc + vc + $c7
    set bc = bc + 1
    endloop
    """
    Then Loop exit condition block should be "bc >= gc"
    Then Loop should have 3 statements

  @LoopStatement
  Scenario: Test nested loops
    Given input data:
    """
    loop
    exitwhen gY>Gw
    loop
    exitwhen RY>12
    call SaveStr(cw,vY,RY,null)
    set RY=RY+1
    endloop
    endloop
    """
    When Loop statement is read
    Then Loop Statement should be:
    """
    loop
    exitwhen gY>Gw
    loop
    exitwhen RY>12
    call SaveStr(cw,vY,RY,null)
    set RY = RY + 1
    endloop
    endloop
    """
    Then Loop exit condition block should be "gY>Gw"
    Then Loop should have 2 statements

  @LoopStatement
  Scenario: Test loop statement with complex condition
    Given input data:
    """
    loop
    exitwhen MP >= PD or dP
    if GetSpellAbilityId()==nD[MP]then
    set dP=true
    else
    set MP=MP+1
    endif
    endloop
    """
    When Loop statement is read
    Then Loop exit condition block should be "MP >= PD or dP"