Feature: Optimize GUI Conditions

  Scenario: Optimize simple GUI condition
    Given GUI tree data:
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
    When GUI code is optimized
    Then Converted tree data should be:
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

  Scenario: Optimize multiple function
    Given GUI tree data:
    """
    globals
    endglobals
    function Func0002 takes nothing returns boolean
    return(GetPlayerName(GetEnumPlayer())==(((strings001[1]+strings001[2])+("|CFF20C000"+strings001[3]))+strings001[4]))
    endfunction
    function Func0003 takes nothing returns boolean
    return(GetPlayerName(GetEnumPlayer())==((strings001[1]+strings001[2])+(strings001[3]+strings001[4])))
    endfunction
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
    When GUI code is optimized
    Then Converted tree data should be:
    """
    globals
    endglobals
    function Func0005 takes nothing returns nothing
    if GetBooleanOr((GetPlayerName(GetEnumPlayer()) == (((strings001[1] + strings001[2]) + ("|CFF20C000" + strings001[3])) + strings001[4])),(GetPlayerName(GetEnumPlayer()) == ((strings001[1] + strings001[2]) + (strings001[3] + strings001[4])))) then
    set player004 = GetEnumPlayer()
    call ForceAddPlayerSimple(GetEnumPlayer(),force001)
    call QuestMessageBJ(force001,bj_QUESTMESSAGE_UPDATED,"4.2 - 24")
    endif
    endfunction
    """

  Scenario: Optimize multiple functions with triggers
    Given GUI tree data:
    """
    globals
    endglobals
    function Func0008 takes nothing returns boolean
    return(IsUnitType(GetFilterUnit(),UNIT_TYPE_SUMMONED)==true)
    endfunction
    function Func0009 takes nothing returns nothing
    call KillUnit(GetEnumUnit())
    endfunction
    function Func0010 takes nothing returns nothing
    call ForGroupBJ(GetUnitsInRectMatching(GetPlayableMapRect(),Condition(function Func0008)),function Func0009)
    endfunction
    """
    When GUI code is optimized
    Then Converted tree data should be:
    """
    globals
    endglobals
    function Func0008 takes nothing returns boolean
    return (IsUnitType(GetFilterUnit(),UNIT_TYPE_SUMMONED) == true)
    endfunction
    function Func0009 takes nothing returns nothing
    call KillUnit(GetEnumUnit())
    endfunction
    function Func0010 takes nothing returns nothing
    call ForGroupBJ(GetUnitsInRectMatching(GetPlayableMapRect(),Condition(function Func0008)),function Func0009)
    endfunction
    """

  Scenario: Optimize another with function usage
    Given GUI tree data:
    """
    globals
    endglobals
    function Func0029 takes nothing returns boolean
    return(IsUnitEnemy(GetFilterUnit(),GetTriggerPlayer())==true)
    endfunction
    function Func0068 takes nothing returns nothing
    if(Func0031())then
    call ForGroupBJ(GetUnitsInRectMatching(GetPlayableMapRect(),Condition(function Func0029)),function Func0030)
    else
    endif
    endfunction
    """
    When GUI code is optimized
    Then Converted tree data should be:
    """
    globals
    endglobals
    function Func0029 takes nothing returns boolean
    return (IsUnitEnemy(GetFilterUnit(),GetTriggerPlayer()) == true)
    endfunction
    function Func0068 takes nothing returns nothing
    if Func0031() then
    call ForGroupBJ(GetUnitsInRectMatching(GetPlayableMapRect(),Condition(function Func0029)),function Func0030)
    endif
    endfunction
    """

  Scenario: Test failing optimization
    Given GUI tree data:
    """
    globals
    endglobals
    function Func0708 takes nothing returns boolean
    return ( GetPlayerName(player004) == ( ( ( strings001[1] + strings001[2] ) + ( "|CFF20C000" + strings001[3] ) ) + strings001[4] ) )
    endfunction
    function Func0709 takes nothing returns boolean
    return ( GetPlayerName(player004) == ( ( strings001[1] + strings001[2] ) + ( strings001[3] + strings001[4] ) ) )
    endfunction
    function Func0710 takes nothing returns boolean
    if ( not GetBooleanOr( Func0708(), Func0709() ) ) then
    return false
    endif
    if ( not ( IsPlayerInForce(player004, force001) == true ) ) then
    return false
    endif
    return true
    endfunction
    """
    When GUI code is optimized
    Then Converted tree data should be:
    """
    globals
    endglobals
    function Func0710 takes nothing returns boolean
    if not (GetBooleanOr((GetPlayerName(player004) == (((strings001[1] + strings001[2]) + ("|CFF20C000" + strings001[3])) + strings001[4])),(GetPlayerName(player004) == ((strings001[1] + strings001[2]) + (strings001[3] + strings001[4]))))) then
    return false
    endif
    if not ((IsPlayerInForce(player004,force001) == true)) then
    return false
    endif
    return true
    endfunction
    """