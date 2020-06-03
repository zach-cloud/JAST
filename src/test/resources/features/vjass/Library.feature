Feature: Test the Library node

  @Library
  Scenario: Test library basic
    Given input data:
    """
    library test
    function test takes nothing returns nothing
    call BJDebugMsg("Hello world")
    endfunction
    endlibrary
    """
    When Library is read
    Then Library should be:
    """
    library test
    function test takes nothing returns nothing
    call BJDebugMsg("Hello world")
    endfunction
    endlibrary
    """

  @Library
  Scenario: Test library initializer
    Given input data:
    """
    library test initializer test
    function test takes nothing returns nothing
    call BJDebugMsg("Hello world")
    endfunction
    endlibrary
    """
    When Library is read
    Then Library should be:
    """
    library test initializer test
    function test takes nothing returns nothing
    call BJDebugMsg("Hello world")
    endfunction
    endlibrary
    """