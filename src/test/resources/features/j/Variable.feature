Feature: Test the Variable node

  @Variable
  Scenario: Test variable basic
    Given input data:
    """
    trigger gg_trg_trig1=null
    """
    When Variable is read
    Then Variable should be "trigger gg_trg_trig1=null"
    Then Variable type should be "trigger"
    Then Variable constant should be "false"
    Then Variable array should be "false"
    Then Variable name should be "gg_trg_trig1"
    Then Variable value should be "null"

  @Variable
  Scenario: Test variable with initial
    Given input data:
    """
    real udg_LethalDamageEvent=0.
    """
    When Variable is read
    Then Variable should be "real udg_LethalDamageEvent=0."
    Then Variable type should be "real"
    Then Variable constant should be "false"
    Then Variable array should be "false"
    Then Variable name should be "udg_LethalDamageEvent"
    Then Variable value should be "0."

  @Variable
  Scenario: Test variable with array
    Given input data:
    """
    integer array Kp
    """
    When Variable is read
    Then Variable should be "integer array Kp"
    Then Variable type should be "integer"
    Then Variable constant should be "false"
    Then Variable array should be "true"
    Then Variable name should be "Kp"
    Then Variable value should be null

  @Variable
  Scenario: Test variable with constant
    Given input data:
    """
    constant string udg_LethalDamageEvent="hello"
    """
    When Variable is read
    Then Variable should be "constant string udg_LethalDamageEvent=''hello''"
    Then Variable type should be "string"
    Then Variable constant should be "true"
    Then Variable array should be "false"
    Then Variable name should be "udg_LethalDamageEvent"
    Then Variable value should be "''hello''"