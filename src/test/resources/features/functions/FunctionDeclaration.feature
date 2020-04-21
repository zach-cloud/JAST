Feature: Test the Function Declaration node

  @FunctionDeclaration
  Scenario: Test reading Function Declaration basic
    Given input data:
    """
    function Trig_trig3_Actions takes nothing returns nothing
    """
    When Function Declaration is read
    Then Function Declaration should be:
    """
    function Trig_trig3_Actions takes nothing returns nothing
    """
    Then Function Declaration should have name "Trig_trig3_Actions"
    Then Function Declaration should have 0 inputs
    Then Function Declaration output should have type "nothing"