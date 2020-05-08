Feature: Test inlining returns functions

  Scenario: Test inline function basic
    Given Inliner data:
    """
    globals
    endglobals
    function Func0004 takes nothing returns boolean
    return GetBooleanOr(Func0002(),Func0003())
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
    When All functions are inlined
    Then The inlined code should be:
    """
    globals
    endglobals
    function Func0005 takes nothing returns nothing
    if GetBooleanOr(Func0002(),Func0003()) then
    set player004 = GetEnumPlayer()
    call ForceAddPlayerSimple(GetEnumPlayer(),force001)
    call QuestMessageBJ(force001,bj_QUESTMESSAGE_UPDATED,"4.2 - 24")
    endif
    endfunction
    """