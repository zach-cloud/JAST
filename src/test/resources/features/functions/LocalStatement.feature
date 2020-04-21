Feature: Test the Local Statement node

  @LocalStatement
  Scenario: Test local statements basic
    Given input data:
    """
    local integer bc=0
    """
    When Local statement is read
    Then Local statement should be:
    """
    local integer bc=0
    """
    Then Local statement name should be "bc"
    Then Local statement type should be "integer"
    Then Local statement array should be "false"
    Then Local statement initial value should be "0"

  @LocalStatement
  Scenario: Test local statements basic
    Given input data:
    """
    local integer array Fc
    """
    When Local statement is read
    Then Local statement should be:
    """
    local integer array Fc
    """
    Then Local statement name should be "Fc"
    Then Local statement type should be "integer"
    Then Local statement array should be "true"
    Then Local statement initial value should be null

  @LocalStatement
  Scenario: Test local with tab separator
    Given input data:
    """
    local integer	index=1
    """
    When Local statement is read
    Then Local statement should be:
    """
    local integer index=1
    """

  @LocalStatement
  Scenario: Test edge case local statement
    Given input data:
    """
    local boolean Vrv=(Vev==ITEM_TYPE_POWERUP and GetUnitAbilityLevel(u,'A0HP')>0)
    """
    When Local statement is read
    Then Local statement should be:
    """
    local boolean Vrv=(Vev == ITEM_TYPE_POWERUP and GetUnitAbilityLevel(u,'A0HP')>0)
    """