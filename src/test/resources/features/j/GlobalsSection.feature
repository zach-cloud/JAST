Feature: Test the Globals Section node

  @GlobalsSection
  Scenario: Test globals section basic
    Given input data:
    """
    globals
    string udg_myVar
    unit array udg_myUnit
    trigger gg_trg_trig1=null
    trigger gg_trg_trig2=null
    trigger gg_trg_trig3=null
    endglobals
    """
    When Globals section is read
    Then Globals section should be:
    """
    globals
    string udg_myVar
    unit array udg_myUnit
    trigger gg_trg_trig1=null
    trigger gg_trg_trig2=null
    trigger gg_trg_trig3=null
    endglobals
    """
    Then Globals section should have 5 variables