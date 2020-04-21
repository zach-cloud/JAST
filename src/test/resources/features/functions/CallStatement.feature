Feature: Test the Call Statement node

  @CallStatement
  Scenario: Test reading Call Statement basic
    Given input data:
    """
    call KillUnit(u)
    """
    When Call Statement is read
    Then Call Statement should be:
    """
    call KillUnit(u)
    """

  @CallStatement
  Scenario: Test reading call statement with inner newlines
    Given input data:
    """
    call DisplayTimedTextToPlayer(bLv,bGv,bhv,180,"|c00ffff80Your code has been saved onto Documents/Warcraft III/CustomMapData/"+uv+"/"+m6x[vZi]+".txt.
    This file path must not include letters other than alphabets and numbers.")
    """
    When Call Statement is read
    Then Call Statement should be:
    """
    call DisplayTimedTextToPlayer(bLv,bGv,bhv,180,"|c00ffff80Your code has been saved onto Documents/Warcraft III/CustomMapData/" + uv + "/" + m6x[vZi] + ".txt.|nThis file path must not include letters other than alphabets and numbers.")
    """

  @CallStatement
  Scenario: Test crashing call statement
    Given input data:
    """
    call Xq(yc(Lc),dc+"\n"+ec)
    """
    When Call Statement is read
    Then Call Statement should be:
    """
    call Xq(yc(Lc),dc + "\n" + ec)
    """

  @CallStatement
  Scenario: Test crashing call statement 2
    Given input data:
    """
    call ico(mRv[PSx],"units\\nightelf\\Wisp\\Wisp.mdl")
    """
    When Call Statement is read
    Then Call Statement should be:
    """
    call ico(mRv[PSx],"units\\nightelf\\Wisp\\Wisp.mdl")
    """

  @CallStatement
  Scenario: Test crashing call statement 3
    Given input data:
    """
    call Preload("\" )\ncall BlzSetAbilityTooltip("+I2S(gD[uq])+", \""+"-"+Sq+"\", "+I2S(0)+")\n//")
    """
    When Call Statement is read
    Then Call Statement should be:
    """
    call Preload("\" )\ncall BlzSetAbilityTooltip(" + I2S(gD[uq]) + ", \"" + "-" + Sq + "\", " + I2S(0) + ")\n//")
    """

  @CallStatement
  Scenario: Test call statement with function
    Given input data:
    """
    call TriggerAddAction(BeE,function imbA)
    """
    When Call Statement is read
    Then Call Statement should be:
    """
    call TriggerAddAction(BeE,function imbA)
    """