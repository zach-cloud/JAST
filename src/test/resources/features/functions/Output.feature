Feature: Test the Output node

  @Output
  Scenario: Test output basic
    Given input data:
    """
    returns integer
    """
    When Output is read
    Then Output should be:
    """
    returns integer
    """
    Then Output type should be "integer"

  @Output
  Scenario: Test output (nothing)
    Given input data:
    """
    returns nothing
    """
    When Output is read
    Then Output should be:
    """
    returns nothing
    """
    Then Output type should be "nothing"