Feature: Test renaming syntax tree variables

  Scenario: Deduplicate variables
    Given Non-deduplicated script:
    """
    globals
    hashtable nzHash=InitHashtable()
    endglobals
    """
    Given mock random name generator set up for "myName"
    When Tree is deduplicated
    Then Deduplicated script should be:
    """
    globals
    hashtable myName=InitHashtable()
    endglobals
    """

  Scenario: Deduplicate function
    Given Non-deduplicated script:
    """
    globals
    endglobals
    function stuff takes nothing returns nothing
    local real x
    call myTest()
    endfunction
    """
    Given mock random name generator set up for "myName2"
    When Tree is deduplicated
    Then Deduplicated script should be:
    """
    globals
    endglobals
    function myName2 takes nothing returns nothing
    local real x
    call myTest()
    endfunction
    """

  Scenario: Deduplicate function does not affect main
    Given Non-deduplicated script:
    """
    globals
    endglobals
    function main takes nothing returns nothing
    local real x
    call myTest()
    endfunction
    """
    Given mock random name generator set up for "myName2"
    When Tree is deduplicated
    Then Deduplicated script should be:
    """
    globals
    endglobals
    function main takes nothing returns nothing
    local real x
    call myTest()
    endfunction
    """