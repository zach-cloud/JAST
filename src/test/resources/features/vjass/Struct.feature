Feature: Test the Struct node

  @Struct
  Scenario: Test struct basic
    Given input data:
    """
    struct MyStruct
    method myTest takes nothing returns nothing
    call BJDebugMsg("hello world")
    endmethod
    endstruct
    """
    When Struct is read
    Then Struct should be:
    """
    struct MyStruct
    method myTest takes nothing returns nothing
    call BJDebugMsg("hello world")
    endmethod
    endstruct
    """