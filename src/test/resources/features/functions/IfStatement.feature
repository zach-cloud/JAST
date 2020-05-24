Feature: Test the If statement node

  @IfStatement
  Scenario: Test reading If Statement basic
    Given input data:
    """
    if kD==ND then
    set kD=yY
    set TD[yY]=ND
    set ZD[yY]=ND
    else
    set ZD[yY]=XD[VD[lD-1]]
    set TD[VD[lD-1]]=yY
    set TD[yY]=ND
    endif
    """
    When If Statement is read
    Then If Statements should be:
    """
    if kD == ND then
    set kD = yY
    set TD[yY] = ND
    set ZD[yY] = ND
    else
    set ZD[yY] = XD[VD[lD - 1]]
    set TD[VD[lD-1]] = yY
    set TD[yY] = ND
    endif
    """
    Then If Statement should have condition block "kD == ND"
    Then If Statement then block should have 3 statements
    Then If Statement else block should have 3 statements
    Then If Statement should have 0 elseif statements
    Then If Statement should have 0 elseif blocks

  @IfStatement
  Scenario: Test crashing if statement
    Given input data:
    """
    if nd>0 then
    if Jd==PLAYER_STATE_RESOURCE_GOLD then
    set md=PLAYER_STATE_GOLD_GATHERED
    call SetPlayerState(td,md,GetPlayerState(td,md)+nd)
    elseif Jd==PLAYER_STATE_RESOURCE_LUMBER then
    set md=PLAYER_STATE_LUMBER_GATHERED
    call SetPlayerState(td,md,GetPlayerState(td,md)+nd)
    endif
    endif
    """
    When If Statement is read
    Then If Statements should be:
    """
    if nd > 0 then
    if Jd == PLAYER_STATE_RESOURCE_GOLD then
    set md = PLAYER_STATE_GOLD_GATHERED
    call SetPlayerState(td,md,GetPlayerState(td,md) + nd)
    elseif Jd == PLAYER_STATE_RESOURCE_LUMBER then
    set md = PLAYER_STATE_LUMBER_GATHERED
    call SetPlayerState(td,md,GetPlayerState(td,md) + nd)
    endif
    endif
    """

  @IfStatement
  Scenario: Test crashing if statement 2
    Given input data:
    """
    if xe() then
    set dp=dp*1.1
    elseif ue() then
    set dp=dp*1.16
    else
    set dp=dp*1.2
    endif
    """
    When If Statement is read
    Then If Statements should be:
    """
    if xe() then
    set dp = dp * 1.1
    elseif ue() then
    set dp = dp * 1.16
    else
    set dp = dp * 1.2
    endif
    """

  @IfStatement
  Scenario: Test crashing if statement 3
    Given input data:
    """
    if ( (LoadInteger(TimerUtils___ht, 0, GetHandleId((t)))) == TimerUtils___HELD ) then // INLINED!!
    return
    endif
    """
    When If Statement is read
    Then If Statements should be:
    """
    if (LoadInteger(TimerUtils___ht,0,GetHandleId(t)) == TimerUtils___HELD) then
    return
    endif
    """

  @IfStatement
  Scenario: Test edge case if statement
    Given input data:
    """
    function iFv takes integer ifv returns nothing
    if G!=ifv then
    set G=ifv
    call iDv(ifv)
    endif
    endfunction
    """
    When If Statement is read
    Then If Statements should be:
    """
    if G != ifv then
    set G = ifv
    call iDv(ifv)
    endif
    """