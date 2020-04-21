Feature: Test the Inputs node

  @Inputs
  Scenario: Test inputs basic
    Given input data:
    """
    takes integer Gq,string iq
    """
    When inputs are read
    Then Inputs should be:
    """
    takes integer Gq,string iq
    """
    Then Inputs should contain 2 input nodes

  @Inputs
  Scenario: Test inputs with nothing
    Given input data:
    """
    takes nothing
    """
    When inputs are read
    Then Inputs should be:
    """
    takes nothing
    """
    Then Inputs should contain 0 input nodes