Feature: Unhex service tests

  Scenario: Unhex simple
    Given Unhex data:
    """
    globals
    endglobals
    function XY takes nothing returns nothing
    set Yw=BlzGetAbilityExtendedTooltip($41303342,0)
    endfunction
    """
    When Code is unhexed
    Then Unhexed code should be:
    """
    globals
    endglobals
    function XY takes nothing returns nothing
    set Yw = BlzGetAbilityExtendedTooltip(1093677890,0)
    endfunction
    """

  Scenario: Unhex works in if statement
    Given Unhex data:
    """
    globals
    endglobals
    function Wy takes integer fy,integer sy returns real
    if sy >= 100 and sy<$1f4 then
    return 2.
    endif
    endfunction
    """
    When Code is unhexed
    Then Unhexed code should be:
    """
    globals
    endglobals
    function Wy takes integer fy,integer sy returns real
    if sy >= 100 and sy < 500 then
    return 2.
    endif
    endfunction
    """

  Scenario: Unhex works in if statement 2
    Given Unhex data:
    """
    globals
    endglobals
    function Wy takes integer fy,integer sy returns real
    if GetUnitAbilityLevel(np,$42303034)>0 then
    return true
    endif
    endfunction
    """
    When Code is unhexed
    Then Unhexed code should be:
    """
    globals
    endglobals
    function Wy takes integer fy,integer sy returns real
    if GetUnitAbilityLevel(np,1110454324) > 0 then
    return true
    endif
    endfunction
    """

  Scenario: Unhex works negative
    Given Unhex data:
    """
    globals
    endglobals
    function Wy takes integer fy,integer sy returns real
    set tw[Ew] = -$7d0
    endfunction
    """
    When Code is unhexed
    Then Unhexed code should be:
    """
    globals
    endglobals
    function Wy takes integer fy,integer sy returns real
    set tw[Ew] = -2000
    endfunction
    """


