Feature: Test the Functions Section node

  @FunctionsSection
  Scenario: Test functions section basic
    Given input data:
    """
    function InitGlobals takes nothing returns nothing
    local integer i=0
    set udg_myVar="asdf"
    endfunction
    function Trig_trig1_Actions takes nothing returns nothing
    call DisplayTextToForce(GetPlayersAll(),"hello world")
    endfunction
    function InitTrig_trig1 takes nothing returns nothing
    set gg_trg_trig1=CreateTrigger()
    call TriggerRegisterTimerEventSingle(gg_trg_trig1,5)
    call TriggerRegisterTimerEventSingle(gg_trg_trig1,3.00)
    call TriggerAddAction(gg_trg_trig1,function Trig_trig1_Actions)
    endfunction
    function Trig_trig2_Actions takes nothing returns nothing
    call CreateNUnitsAtLoc(1,'hfoo',Player(0),GetRectCenter(GetPlayableMapRect()),bj_UNIT_FACING)
    endfunction
    function InitTrig_trig2 takes nothing returns nothing
    set gg_trg_trig2=CreateTrigger()
    call TriggerAddAction(gg_trg_trig2,function Trig_trig2_Actions)
    endfunction
    function Trig_trig3_Actions takes nothing returns nothing
    call ConditionalTriggerExecute(gg_trg_trig2)
    call DisplayTextToForce(GetPlayersAll(),udg_myVar)
    endfunction
    function InitTrig_trig3 takes nothing returns nothing
    set gg_trg_trig3=CreateTrigger()
    call TriggerRegisterPlayerChatEvent(gg_trg_trig3,Player(0),"asdf",true)
    call TriggerAddAction(gg_trg_trig3,function Trig_trig3_Actions)
    endfunction
    function InitCustomTriggers takes nothing returns nothing
    call InitTrig_trig1()
    call InitTrig_trig2()
    call InitTrig_trig3()
    endfunction
    function InitCustomPlayerSlots takes nothing returns nothing
    call SetPlayerStartLocation(Player(0),0)
    call SetPlayerColor(Player(0),ConvertPlayerColor(0))
    call SetPlayerRacePreference(Player(0),RACE_PREF_HUMAN)
    call SetPlayerRaceSelectable(Player(0),true)
    call SetPlayerController(Player(0),MAP_CONTROL_USER)
    endfunction
    function InitCustomTeams takes nothing returns nothing
    call SetPlayerTeam(Player(0),0)
    endfunction
    function main takes nothing returns nothing
    call SetCameraBounds(-3328.0+GetCameraMargin(CAMERA_MARGIN_LEFT),-3584.0+GetCameraMargin(CAMERA_MARGIN_BOTTOM),3328.0-GetCameraMargin(CAMERA_MARGIN_RIGHT),3072.0-GetCameraMargin(CAMERA_MARGIN_TOP),-3328.0+GetCameraMargin(CAMERA_MARGIN_LEFT),3072.0-GetCameraMargin(CAMERA_MARGIN_TOP),3328.0-GetCameraMargin(CAMERA_MARGIN_RIGHT),-3584.0+GetCameraMargin(CAMERA_MARGIN_BOTTOM))
    call SetDayNightModels("Environment\\DNC\\DNCLordaeron\\DNCLordaeronTerrain\\DNCLordaeronTerrain.mdl","Environment\\DNC\\DNCLordaeron\\DNCLordaeronUnit\\DNCLordaeronUnit.mdl")
    call NewSoundEnvironment("Default")
    call SetAmbientDaySound("LordaeronSummerDay")
    call SetAmbientNightSound("LordaeronSummerNight")
    call SetMapMusic("Music",true,0)
    call InitBlizzard()
    call InitGlobals()
    call InitCustomTriggers()
    endfunction
    function config takes nothing returns nothing
    call SetMapName("Just another Warcraft III map")
    call SetMapDescription("Nondescript")
    call SetPlayers(1)
    call SetTeams(1)
    call SetGamePlacement(MAP_PLACEMENT_USE_MAP_SETTINGS)
    call DefineStartLocation(0,729.9,10.5)
    call InitCustomPlayerSlots()
    call SetPlayerSlotAvailable(Player(0),MAP_CONTROL_USER)
    call InitGenericPlayerSlots()
    endfunction
    """
    When Functions Section is read
    Then Functions Section should contain 12 functions

  @FunctionsSection
  Scenario: Test reading constant function
    Given input data:
    """
    constant function Q4x takes integer Q5x returns integer
    if Q5x=='H01Z' then
    return 3
    elseif Q5x=='H02N' then
    return 3
    elseif Q5x=='H02U' then
    return 5
    elseif Q5x=='H03Z' then
    return 5
    elseif Q5x=='H04M' then
    return 6
    endif
    return 6
    endfunction
    """
    When Functions Section is read
    Then Functions Section should contain 1 functions