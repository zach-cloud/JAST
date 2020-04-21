Feature: Test native statement

  @NativeStatement
  Scenario: Test native statement simple
    Given input data:
    """
    native UnitAlive takes unit u returns boolean
    """
    When Native Statement is read
    Then Native Statement should be:
    """
    native UnitAlive takes unit u returns boolean
    """