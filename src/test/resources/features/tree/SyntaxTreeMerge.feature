Feature: Test merging syntax trees

  Scenario: Test merging scripts
    Given script 1:
    """
    globals
    integer myVar1 = 1
    integer myVar2 = 2
    unit u
    endglobals
    function myTest takes nothing returns nothing
    call KillUnit(u)
    endfunction
    function main takes nothing returns nothing
    local real x
    call myTest()
    endfunction
    """
    Given script 2:
    """
    globals
    integer myVar3 = 1
    endglobals
    function mytest2 takes nothing returns nothing
    set myVar3 = myVar3 + 1
    endfunction
    function main takes nothing returns nothing
    local real y
    call mytest2()
    endfunction
    """
    When scripts are merged
    Then merged script should be:
    """
    globals
    integer myVar1=1
    integer myVar2=2
    unit u
    integer myVar3=1
    endglobals
    function myTest takes nothing returns nothing
    call KillUnit(u)
    endfunction
    function mytest2 takes nothing returns nothing
    set myVar3 = myVar3 + 1
    endfunction
    function main takes nothing returns nothing
    local real x
    local real y
    call myTest()
    call mytest2()
    endfunction
    """