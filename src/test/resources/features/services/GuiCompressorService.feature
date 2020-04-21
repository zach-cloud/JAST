Feature: Test finding GUI optimizable functions and compressing

  Scenario: Test find function basic
    Given optimization data:
    """
    globals
    endglobals
    function Func0004 takes nothing returns boolean
    if(not GetBooleanOr(Func0002(),Func0003()))then
    return false
    endif
    return true
    endfunction
    function Func0005 takes nothing returns nothing
    if(Func0004())then
    set player004=GetEnumPlayer()
    call ForceAddPlayerSimple(GetEnumPlayer(),force001)
    call QuestMessageBJ(force001,bj_QUESTMESSAGE_UPDATED,"4.2 - 24")
    else
    endif
    endfunction
    """
    When data is searched for optimizable functions
    Then there should be 1 function in the list of optimizable functions

  Scenario: Test optimize function into line
    Given optimization function:
    """
    function Func0004 takes nothing returns boolean
    if(not GetBooleanOr(Func0002(),Func0003()))then
    return false
    endif
    return true
    endfunction
    """
    When Funtion is optimized
    Then Optimized function should be:
    """
    function Func0004 takes nothing returns boolean
    return GetBooleanOr(Func0002(),Func0003())
    endfunction
    """