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