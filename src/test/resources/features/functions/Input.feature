Feature: Test the Input node

  @Input
  Scenario: Test input basic
    Given input data:
    """
    integer Gq
    """
    When input is read
    Then Input should be:
    """
    integer Gq
    """
    Then Input type should be "integer"
    Then Input name should be "Gq"