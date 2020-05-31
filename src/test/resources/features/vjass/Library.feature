Feature: Test the Variable node

  @Variable
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