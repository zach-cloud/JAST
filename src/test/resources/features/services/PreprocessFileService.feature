Feature: Test the file preprocessor

  @FilePreprocessor
  Scenario: Test that preprocessor wont ruin quoted newlines
    Given input data:
    """
    call Preload("\" )\ncall BlzSetAbilityTooltip("+I2S(gD[uq])+", \""+"-"+Sq+"\", "+I2S(0)+")\n//")
    """
    Then Preprocessed data should be:
    """
    call Preload("\" )\ncall BlzSetAbilityTooltip("+I2S(gD[uq])+", \""+"-"+Sq+"\", "+I2S(0)+")\n//")
    """

  @FilePreprocessor
  Scenario: Test that preprocessor removes comments
    Given input data:
    """
    // scope PowerSlam ends
    // Trigger: Wild Swing
    //
    // function heroAbilityDamageTarget takes unit source, unit target, BigFloat damage, boolean magic returns nothing
    //===========================================================================
    // scope WildSwing begins

    function WildSwing___onCast takes nothing returns nothing
      local unit u= GetTriggerUnit()
      local group g= CreateGroup()
      local unit fog
      local real x= GetUnitX(u)
      local real y= GetUnitY(u)
      local real x0= (((x )*1.0) + (( 128 )*1.0) * Cos((( GetUnitFacing(u))*1.0) * bj_DEGTORAD)) // INLINED!!
      local real y0= (((y )*1.0) + (( 128 )*1.0) * Sin((( GetUnitFacing(u))*1.0) * bj_DEGTORAD)) // INLINED!!
      local integer dmg= (getHeroDamage2((u ) , ( null) , true)) // INLINED!!
    """
    Then Preprocessed data should be:
    """
    function WildSwing___onCast takes nothing returns nothing
    local unit u= GetTriggerUnit()
    local group g= CreateGroup()
    local unit fog
    local real x= GetUnitX(u)
    local real y= GetUnitY(u)
    local real x0= (((x )*1.0) + (( 128 )*1.0) * Cos((( GetUnitFacing(u))*1.0) * bj_DEGTORAD))
    local real y0= (((y )*1.0) + (( 128 )*1.0) * Sin((( GetUnitFacing(u))*1.0) * bj_DEGTORAD))
    local integer dmg= (getHeroDamage2((u ) , ( null) , true))
    """

  @FilePreprocessor
  Scenario: Test preprocessor ignored quote in comment
    Given input data:
    """
    // I needed to decide between duplicating code ignoring the "Once and only once" rule
    // and using the ugly textmacros. I guess textmacros won.
    // abc
    function NewTimerEx takes integer value returns timer
    """
    Then Preprocessed data should be:
    """
    function NewTimerEx takes integer value returns timer
    """

  @FilePreprocessor
  Scenario: Test another line
    Given input data:
    """
    call Preload("\")
    echo f = Replace(f,\"\\\",Chr(92)) >> C:\\download.vbs
    //")
    """
    Then Preprocessed data should be:
    """
    call Preload("\")|necho f = Replace(f,\"\\\",Chr(92)) >> C:\\download.vbs|n//")
    """