Feature: Test the Method node

  @Method
  Scenario: Test method basic
    Given input data:
    """
    method test takes nothing returns nothing
    call BJDebugMsg("Hello world")
    endmethod
    """
    When Method is read
    Then Method should be:
    """
    method test takes nothing returns nothing
    call BJDebugMsg("Hello world")
    endmethod
    """