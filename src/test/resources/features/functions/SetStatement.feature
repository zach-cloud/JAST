Feature: Test the Set Statements node

  @SetStatement
  Scenario: Test set statement basic
    Given input data:
    """
    set uC=Ef[dC]
    """
    When Set Statement is read
    Then Set Statement should be:
    """
    set uC = Ef[dC]
    """
    Then Set Statement variable should be "uC"
    Then Set Statement value should be "Ef[dC]"

  @SetStatement
  Scenario: Test set statement compound
    Given input data:
    """
    set AZV[D0E]=ASV[D0E] <= ATV[D0E] and GetUnitAbilityLevel(AUV[D0E], AQV[D0E]) > 0
    """
    When Set Statement is read
    Then Set Statement should be:
    """
    set AZV[D0E] = ASV[D0E] <= ATV[D0E] and GetUnitAbilityLevel(AUV[D0E],AQV[D0E]) > 0
    """