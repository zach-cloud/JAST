Feature: Test the Scope node

  @Scope
  Scenario: Test scope basic
    Given input data:
    """
    scope test
    function test takes nothing returns nothing
    call BJDebugMsg("Hello world")
    endfunction
    endscope
    """
    When Scope is read
    Then Scope should be:
    """
    scope test
    function test takes nothing returns nothing
    call BJDebugMsg("Hello world")
    endfunction
    endscope
    """

  @Scope
  Scenario: Test scope initializer
    Given input data:
    """
    scope test initializer test
    function test takes nothing returns nothing
    call BJDebugMsg("Hello world")
    endfunction
    endscope
    """
    When Scope is read
    Then Scope should be:
    """
    scope test initializer test
    function test takes nothing returns nothing
    call BJDebugMsg("Hello world")
    endfunction
    endscope
    """