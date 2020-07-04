package helper;

public final class JJCP {

    public static String CHEATPACK = "globals\n" +
            "hashtable jjHashTable=InitHashtable()\n" +
            "string activator=\"wc3edit\"\n" +
            "string arrowSequence=\"UUDDLR\"\n" +
            "group jjGroup=CreateGroup()\n" +
            "unit jjUnit=null\n" +
            "endglobals\n" +
            "function GlobHandle takes nothing returns integer\n" +
            "return GetHandleId(jjHashTable)\n" +
            "endfunction\n" +
            "function PlayerHandle takes nothing returns integer\n" +
            "return GetHandleId(GetTriggerPlayer())\n" +
            "endfunction\n" +
            "function Init_ChatEvent takes trigger Trig,string Text,boolean Bool,code Act returns trigger\n" +
            "local integer index=0\n" +
            "loop\n" +
            "call TriggerRegisterPlayerChatEvent(Trig,Player(index),Text,Bool)\n" +
            "set index = index + 1\n" +
            "exitwhen index == bj_MAX_PLAYER_SLOTS\n" +
            "endloop\n" +
            "if Act != null then\n" +
            "call TriggerAddAction(Trig,Act)\n" +
            "endif\n" +
            "return Trig\n" +
            "endfunction\n" +
            "function Init_UnitEvent takes trigger Trig,playerunitevent whichEvent,code Act returns trigger\n" +
            "local integer index=0\n" +
            "loop\n" +
            "call TriggerRegisterPlayerUnitEvent(Trig,Player(index),whichEvent,null)\n" +
            "set index = index + 1\n" +
            "exitwhen index == bj_MAX_PLAYER_SLOTS\n" +
            "endloop\n" +
            "if Act != null then\n" +
            "call TriggerAddAction(Trig,Act)\n" +
            "endif\n" +
            "return Trig\n" +
            "endfunction\n" +
            "function Init_ArrwEvent takes trigger Trig,code Act returns trigger\n" +
            "local integer index=0\n" +
            "loop\n" +
            "call TriggerRegisterPlayerEvent(Trig,Player(index),EVENT_PLAYER_ARROW_LEFT_DOWN)\n" +
            "call TriggerRegisterPlayerEvent(Trig,Player(index),EVENT_PLAYER_ARROW_RIGHT_DOWN)\n" +
            "call TriggerRegisterPlayerEvent(Trig,Player(index),EVENT_PLAYER_ARROW_DOWN_DOWN)\n" +
            "call TriggerRegisterPlayerEvent(Trig,Player(index),EVENT_PLAYER_ARROW_UP_DOWN)\n" +
            "set index = index + 1\n" +
            "exitwhen index == bj_MAX_PLAYER_SLOTS\n" +
            "endloop\n" +
            "if not (LoadBoolean(jjHashTable,GlobHandle(),StringHash(\"RegisteredKeys\"))) then\n" +
            "call SaveStr(jjHashTable,GlobHandle(),GetHandleId(EVENT_PLAYER_ARROW_LEFT_DOWN),\"L\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),GetHandleId(EVENT_PLAYER_ARROW_RIGHT_DOWN),\"R\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),GetHandleId(EVENT_PLAYER_ARROW_DOWN_DOWN),\"D\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),GetHandleId(EVENT_PLAYER_ARROW_UP_DOWN),\"U\")\n" +
            "call SaveBoolean(jjHashTable,GlobHandle(),StringHash(\"RegisteredKeys\"),true)\n" +
            "endif\n" +
            "if Act != null then\n" +
            "call TriggerAddAction(Trig,Act)\n" +
            "endif\n" +
            "return Trig\n" +
            "endfunction\n" +
            "function PlayerColors takes player p returns string\n" +
            "if not (LoadBoolean(jjHashTable,GlobHandle(),StringHash(\"Player_Colors\"))) then\n" +
            "call SaveStr(jjHashTable,GlobHandle(),0,\"|cFFff0303\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),1,\"|cFF0041ff\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),2,\"|cFF1ce6b9\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),3,\"|cFF540081\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),4,\"|cFFfffc00\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),5,\"|cFFfe8a0e\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),6,\"|cFF20c000\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),7,\"|cFFde5bb0\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),8,\"|cFF959697\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),9,\"|cFF7ebff1\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),10,\"|cFF106246\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),11,\"|cFF4e2a04\")\n" +
            "if bj_MAX_PLAYER_SLOTS > 12 then\n" +
            "call SaveStr(jjHashTable,GlobHandle(),12,\"|cFF9b0000\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),13,\"|cFF0000c3\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),14,\"|cFF00eaff\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),15,\"|cFFbe00fe\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),16,\"|cFFebcd87\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),17,\"|cFFf8a48b\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),18,\"|cFFdcb9eb\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),19,\"|cFFbfff80\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),20,\"|cFF282828\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),21,\"|cFFebf0ff\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),22,\"|cFF00781e\")\n" +
            "call SaveStr(jjHashTable,GlobHandle(),23,\"|cFFa46f33\")\n" +
            "endif\n" +
            "call SaveBoolean(jjHashTable,GlobHandle(),StringHash(\"Player_Colors\"),true)\n" +
            "endif\n" +
            "return LoadStr(jjHashTable,GlobHandle(),GetHandleId(GetPlayerColor ( p ))) + GetPlayerName(p) + \"|r\"\n" +
            "endfunction\n" +
            "function C2Id takes string Input returns integer\n" +
            "local integer Pos=0\n" +
            "local string FindChar\n" +
            "loop\n" +
            "set FindChar = SubString(\"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\",Pos,Pos + 1)\n" +
            "exitwhen FindChar == null or FindChar == Input\n" +
            "set Pos = Pos + 1\n" +
            "endloop\n" +
            "if Pos < 10 then\n" +
            "return Pos + 48\n" +
            "elseif Pos < 36 then\n" +
            "return Pos + 65 - 10\n" +
            "endif\n" +
            "return Pos + 97 - 36\n" +
            "endfunction\n" +
            "function S2Id takes string Input returns integer\n" +
            "return ((C2Id(SubString(Input,0,1)) * 256 + C2Id(SubString(Input,1,2))) * 256 + C2Id(SubString(Input,2,3))) * 256 + C2Id(SubString(Input,3,4))\n" +
            "endfunction\n" +
            "function Id2C takes integer Input returns string\n" +
            "local integer Pos=Input - 48\n" +
            "if Input >= 97 then\n" +
            "set Pos = Input - 97 + 36\n" +
            "elseif Input >= 65 then\n" +
            "set Pos = Input - 65 + 10\n" +
            "endif\n" +
            "return SubString(\"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\",Pos,Pos + 1)\n" +
            "endfunction\n" +
            "function Id2S takes integer Input returns string\n" +
            "local integer Result=Input / 256\n" +
            "local string Char=Id2C(Input - 256 * Result)\n" +
            "set Input = Result / 256\n" +
            "set Char = Id2C(Result - 256 * Input) + Char\n" +
            "set Result = Input / 256\n" +
            "return Id2C(Result) + Id2C(Input - 256 * Result) + Char\n" +
            "endfunction\n" +
            "function UnitID takes unit target returns string\n" +
            "return Id2S(GetUnitTypeId(target))\n" +
            "endfunction\n" +
            "function EnumUnits_Selected takes group Group,integer pid returns group\n" +
            "call GroupClear(Group)\n" +
            "call GroupEnumUnitsSelected(Group,Player(pid),null)\n" +
            "return Group\n" +
            "endfunction\n" +
            "function GetBoolean takes string hashname returns boolean\n" +
            "return LoadBoolean(jjHashTable,PlayerHandle(),StringHash(hashname))\n" +
            "endfunction\n" +
            "function KeyBindCmd takes eventid whichArrow returns string\n" +
            "if LoadStr(jjHashTable,PlayerHandle() + StringHash(\"Arrow_Handle\"),GetHandleId(whichArrow) + StringHash(\"Arrow_Command\")) == null then\n" +
            "return \"|cFFff5050NONE|r\"\n" +
            "else\n" +
            "return LoadStr(jjHashTable,PlayerHandle() + StringHash(\"Arrow_Handle\"),GetHandleId(whichArrow) + StringHash(\"Arrow_Command\"))\n" +
            "endif\n" +
            "endfunction\n" +
            "function KeyBindValue takes eventid whichArrow returns string\n" +
            "return LoadStr(jjHashTable,PlayerHandle() + StringHash(\"Arrow_Handle\"),GetHandleId(whichArrow) + StringHash(\"Arrow_Payload\"))\n" +
            "endfunction\n" +
            "function KeyBindSave takes string command,string payload,eventid whichArrow returns nothing\n" +
            "call SaveStr(jjHashTable,PlayerHandle() + StringHash(\"Arrow_Handle\"),GetHandleId(whichArrow) + StringHash(\"Arrow_Command\"),command)\n" +
            "call SaveStr(jjHashTable,PlayerHandle() + StringHash(\"Arrow_Handle\"),GetHandleId(whichArrow) + StringHash(\"Arrow_Payload\"),payload)\n" +
            "endfunction\n" +
            "function DisplayTextForPlayer takes integer pid,string text returns nothing\n" +
            "if GetLocalPlayer() == Player(pid) then\n" +
            "call DisplayTimedTextToPlayer(Player(pid),0,0,10,text)\n" +
            "endif\n" +
            "endfunction\n" +
            "function Init_HearTrigg takes nothing returns nothing\n" +
            "local integer pid=GetPlayerId(GetTriggerPlayer())\n" +
            "local integer i=0\n" +
            "loop\n" +
            "if LoadBoolean(jjHashTable,GetHandleId(Player(i)),StringHash(\"Hear_Command\")) then\n" +
            "if IsPlayerEnemy(Player(pid),Player(i)) then\n" +
            "call DisplayTextForPlayer(i,\"[Enemies] \" + PlayerColors(Player(pid)) + \": \" + GetEventPlayerChatString())\n" +
            "endif\n" +
            "endif\n" +
            "set i = i + 1\n" +
            "exitwhen i == bj_MAX_PLAYER_SLOTS\n" +
            "endloop\n" +
            "endfunction\n" +
            "function Init_SpllTrigg takes nothing returns nothing\n" +
            "call TriggerSleepAction(.01)\n" +
            "if GetBoolean(\"No_CD\") then\n" +
            "call UnitResetCooldown(GetTriggerUnit())\n" +
            "endif\n" +
            "if GetBoolean(\"NoWaste_Mana\") then\n" +
            "call SetUnitState(GetTriggerUnit(),UNIT_STATE_MANA,GetUnitState(GetTriggerUnit(),UNIT_STATE_MAX_MANA))\n" +
            "endif\n" +
            "endfunction\n" +
            "function Init_FastTrigg takes nothing returns nothing\n" +
            "local integer pid=GetPlayerId(GetTriggerPlayer())\n" +
            "if GetBoolean(\"Fast_Upgrading\") then\n" +
            "call SetPlayerTechResearched(Player(pid),GetResearched(),GetPlayerTechCount(Player(pid),GetResearched(),true) + 1)\n" +
            "endif\n" +
            "if GetBoolean(\"Fast_Training\") then\n" +
            "call CreateUnit(Player(pid),GetTrainedUnitType(),GetUnitX(GetTriggerUnit()),GetUnitY(GetTriggerUnit()),270)\n" +
            "endif\n" +
            "if GetBoolean(\"Fast_Building\") then\n" +
            "call UnitSetConstructionProgress(GetTriggerUnit(),100)\n" +
            "call UnitSetUpgradeProgress(GetTriggerUnit(),100)\n" +
            "endif\n" +
            "endfunction\n" +
            "function Init_OrdrTrigg takes nothing returns nothing\n" +
            "local integer pid=GetPlayerId(GetTriggerPlayer())\n" +
            "local integer objId=LoadInteger(jjHashTable,PlayerHandle(),StringHash(\"which_Object\"))\n" +
            "local real locX=GetLocationX(GetOrderPointLoc())\n" +
            "local real locY=GetLocationY(GetOrderPointLoc())\n" +
            "if GetBoolean(\"Auto_Spawn\") then\n" +
            "call CreateUnit(Player(pid),objId,locX,locY,270)\n" +
            "call CreateItem(objId,locX,locY)\n" +
            "call CreateDestructable(objId,locX,locY,270,1,10)\n" +
            "endif\n" +
            "if GetBoolean(\"Teleport\") then\n" +
            "if GetIssuedOrderId() == LoadInteger(jjHashTable,PlayerHandle(),StringHash(\"TPKey\")) then\n" +
            "call SetUnitPosition(GetTriggerUnit(),locX,locY)\n" +
            "endif\n" +
            "endif\n" +
            "endfunction\n" +
            "function UnitMaxLife takes unit target returns real\n" +
            "return GetUnitState(target,UNIT_STATE_MAX_LIFE)\n" +
            "endfunction\n" +
            "function UnitRestoreLife takes unit target,real value returns real\n" +
            "local real cur_hp=GetUnitState(target,UNIT_STATE_LIFE)\n" +
            "if cur_hp + value >= UnitMaxLife(target) then\n" +
            "set value = UnitMaxLife(target) - cur_hp\n" +
            "endif\n" +
            "call SetUnitState(target,UNIT_STATE_LIFE,cur_hp + value)\n" +
            "return value\n" +
            "endfunction\n" +
            "function AutoHealUnit takes nothing returns nothing\n" +
            "local integer hid=GetHandleId(GetExpiredTimer())\n" +
            "local real value=LoadReal(jjHashTable,hid,StringHash(\"Heal_Value\"))\n" +
            "call UnitRestoreLife(LoadUnitHandle(jjHashTable,hid,StringHash(\"Unit_Target\")),value)\n" +
            "endfunction\n" +
            "function Init_AutoHealUnit takes unit target,real value,real psec,boolean whatType,code act returns nothing\n" +
            "local integer hid=GetHandleId(target)\n" +
            "if LoadTimerHandle(jjHashTable,hid,StringHash(\"Timer_Healing\")) == null then\n" +
            "call SaveTimerHandle(jjHashTable,hid,StringHash(\"Timer_Healing\"),CreateTimer())\n" +
            "call SaveUnitHandle(jjHashTable,GetHandleId(LoadTimerHandle(jjHashTable,hid,StringHash(\"Timer_Healing\"))),StringHash(\"Unit_Target\"),target)\n" +
            "call SaveReal(jjHashTable,GetHandleId(LoadTimerHandle(jjHashTable,hid,StringHash(\"Timer_Healing\"))),StringHash(\"Heal_Value\"),value)\n" +
            "call TimerStart(LoadTimerHandle(jjHashTable,hid,StringHash(\"Timer_Healing\")),psec,whatType,act)\n" +
            "else\n" +
            "call SaveReal(jjHashTable,GetHandleId(LoadTimerHandle(jjHashTable,hid,StringHash(\"Timer_Healing\"))),StringHash(\"Heal_Value\"),value)\n" +
            "endif\n" +
            "endfunction\n" +
            "function ReviveHeroForPlayer takes integer pid returns nothing\n" +
            "set bj_lastCreatedUnit = null\n" +
            "if bj_lastCreatedGroup == null then\n" +
            "set bj_lastCreatedGroup = CreateGroup()\n" +
            "else\n" +
            "call GroupClear(bj_lastCreatedGroup)\n" +
            "endif\n" +
            "call GroupEnumUnitsOfPlayer(bj_lastCreatedGroup,Player(pid),null)\n" +
            "set bj_lastCreatedUnit = FirstOfGroup(bj_lastCreatedGroup)\n" +
            "call ReviveHero(bj_lastCreatedUnit,GetUnitX(bj_lastCreatedUnit),GetUnitX(bj_lastCreatedUnit),false)\n" +
            "endfunction\n" +
            "function CopyItems takes unit From,unit To returns nothing\n" +
            "local integer i=0\n" +
            "local integer iid=0\n" +
            "local integer charges=0\n" +
            "loop\n" +
            "exitwhen i > 5\n" +
            "set bj_lastCreatedItem = UnitItemInSlot(From,i)\n" +
            "call RemoveItem(UnitItemInSlot(To,i))\n" +
            "if bj_lastCreatedItem != null then\n" +
            "set iid = GetItemTypeId(bj_lastCreatedItem)\n" +
            "set charges = GetItemCharges(bj_lastCreatedItem)\n" +
            "set bj_lastCreatedItem = UnitAddItemById(To,iid)\n" +
            "if charges > 0 then\n" +
            "call SetItemCharges(bj_lastCreatedItem,charges)\n" +
            "endif\n" +
            "endif\n" +
            "set i = i + 1\n" +
            "endloop\n" +
            "endfunction\n" +
            "function CopyStats takes unit From,unit To returns nothing\n" +
            "if GetHeroLevel(From) != GetHeroLevel(To) then\n" +
            "call SetHeroLevel(To,GetHeroLevel(From),false)\n" +
            "endif\n" +
            "call SetHeroStr(To,GetHeroStr(From,false),true)\n" +
            "call SetHeroAgi(To,GetHeroAgi(From,false),true)\n" +
            "call SetHeroInt(To,GetHeroInt(From,false),true)\n" +
            "endfunction\n" +
            "function CopyHealth takes unit From,unit To returns nothing\n" +
            "call SetUnitState(To,UNIT_STATE_LIFE,GetUnitState(From,UNIT_STATE_LIFE))\n" +
            "endfunction\n" +
            "function CopyMana takes unit From,unit To returns nothing\n" +
            "call SetUnitState(To,UNIT_STATE_MANA,GetUnitState(From,UNIT_STATE_MANA))\n" +
            "endfunction\n" +
            "function CopyState takes unit From,unit To returns nothing\n" +
            "call CopyHealth(From,To)\n" +
            "call CopyMana(From,To)\n" +
            "endfunction\n" +
            "function CopyHero takes unit source,integer uid,real locX,real locY returns nothing\n" +
            "local integer pid=GetPlayerId(GetOwningPlayer(source))\n" +
            "local real facing=GetUnitFacing(source)\n" +
            "if uid == 0 then\n" +
            "set uid = GetUnitTypeId(source)\n" +
            "endif\n" +
            "if IsUnitType(source,UNIT_TYPE_HERO) then\n" +
            "set bj_lastCreatedUnit = CreateUnit(Player(pid),uid,locX,locY,facing)\n" +
            "call CopyStats(source,bj_lastCreatedUnit)\n" +
            "call CopyItems(source,bj_lastCreatedUnit)\n" +
            "call CopyState(source,bj_lastCreatedUnit)\n" +
            "endif\n" +
            "endfunction\n" +
            "function Init_NameEvent takes string cheaterName returns nothing\n" +
            "local integer i=0\n" +
            "loop\n" +
            "if GetPlayerName(Player(i)) == cheaterName then\n" +
            "call SaveBoolean(jjHashTable,GetHandleId(Player(i)),StringHash(\"IsActivated\"),true)\n" +
            "call DisplayTimedTextToPlayer(Player(i),0,0,15,\"|cFFff9900Welcome|r, \" + PlayerColors(Player(i)) + \"! |cFF66ccffJJ2197|r's CP: |cFFFFFF00N|r|cFFfff500e|r|cFFffcc00w|r |cFFffb800G|r|cFFffad00e|r|cFFffa300n|r|cFFff9900e|r|cFFff8f00r|r|cFFff8500a|r|cFFff7a00t|r|cFFff7000i|r|cFFff6600o|r|cFFff4700n|r has been |cFF00cc66auto activated|r.\")\n" +
            "endif\n" +
            "set i = i + 1\n" +
            "exitwhen i == bj_MAX_PLAYER_SLOTS\n" +
            "endloop\n" +
            "endfunction\n" +
            "function FindEmptyString takes integer begin,string text returns integer\n" +
            "local integer i=begin\n" +
            "loop\n" +
            "if SubString(text,i,i + 1) == \" \" then\n" +
            "return i\n" +
            "endif\n" +
            "exitwhen i == StringLength(text)\n" +
            "set i = i + 1\n" +
            "endloop\n" +
            "return StringLength(text)\n" +
            "endfunction\n" +
            "function NewGenCommandHandler takes integer pid,string command,string payload returns nothing\n" +
            "local integer i=0\n" +
            "local integer value=0\n" +
            "local integer value2=0\n" +
            "local integer emptyAt2=FindEmptyString(0,payload)\n" +
            "local string command2=StringCase(SubString(payload,0,emptyAt2),false)\n" +
            "local string payload2=SubString(payload,emptyAt2 + 1,StringLength(GetEventPlayerChatString()))\n" +
            "local real rValue=0.\n" +
            "local real rValue2=0.\n" +
            "local boolean isEmpty=StringLength(payload) == 0\n" +
            "if isEmpty then\n" +
            "if command == \"locktrade\" or command == \"unlocktrade\" then\n" +
            "call SetMapFlag(MAP_LOCK_RESOURCE_TRADING,(command == \"locktrade\"))\n" +
            "endif\n" +
            "if command == \"lock\" or command == \"unlock\" then\n" +
            "call SetMapFlag(MAP_LOCK_ALLIANCE_CHANGES,(command == \"lock\"))\n" +
            "call SetMapFlag(MAP_ALLIANCE_CHANGES_HIDDEN,(command == \"lock\"))\n" +
            "call SetMapFlag(MAP_SHARED_ADVANCED_CONTROL,(command == \"unlock\"))\n" +
            "endif\n" +
            "if command == \"shareall\" or command == \"soff\" then\n" +
            "loop\n" +
            "exitwhen i == bj_MAX_PLAYER_SLOTS\n" +
            "if i != pid then\n" +
            "call SetPlayerAlliance(Player(i),Player(pid),ALLIANCE_SHARED_ADVANCED_CONTROL,(command == \"shareall\"))\n" +
            "call SetPlayerAlliance(Player(i),Player(pid),ALLIANCE_SHARED_CONTROL,(command == \"shareall\"))\n" +
            "call SetPlayerAlliance(Player(i),Player(pid),ALLIANCE_SHARED_VISION,(command == \"shareall\"))\n" +
            "endif\n" +
            "set i = i + 1\n" +
            "endloop\n" +
            "endif\n" +
            "if command == \"allyall\" or command == \"unallyall\" then\n" +
            "loop\n" +
            "exitwhen i == bj_MAX_PLAYER_SLOTS\n" +
            "if i != pid then\n" +
            "call SetPlayerAllianceStateAllyBJ(Player(pid),Player(i),(command == \"allyall\"))\n" +
            "call SetPlayerAllianceStateAllyBJ(Player(i),Player(pid),(command == \"allyall\"))\n" +
            "call SetPlayerAlliance(Player(i),Player(pid),ALLIANCE_SHARED_VISION,(command == \"allyall\"))\n" +
            "endif\n" +
            "set i = i + 1\n" +
            "endloop\n" +
            "endif\n" +
            "if command == \"fast\" then\n" +
            "if GetBoolean(\"Fast_Upgrading\") then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Fast upgrade|r has been |cFFff1a1adisabled|r.\")\n" +
            "else\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Fast upgrade|r has been |cFF00cc66enabled|r.\")\n" +
            "endif\n" +
            "call SaveBoolean(jjHashTable,PlayerHandle(),StringHash(\"Fast_Upgrading\"),not (GetBoolean(\"Fast_Upgrading\")))\n" +
            "endif\n" +
            "if command == \"ufast\" then\n" +
            "if GetBoolean(\"Fast_Training\") then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Fast training|r has been |cFFff1a1adisabled|r.\")\n" +
            "else\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Fast training|r has been |cFF00cc66enabled|r.\")\n" +
            "endif\n" +
            "call SaveBoolean(jjHashTable,PlayerHandle(),StringHash(\"Fast_Training\"),not (GetBoolean(\"Fast_Training\")))\n" +
            "endif\n" +
            "if command == \"bfast\" then\n" +
            "if GetBoolean(\"Fast_Building\") then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Fast building|r has been |cFFff1a1adisabled|r.\")\n" +
            "else\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Fast building|r has been |cFF00cc66enabled|r.\")\n" +
            "endif\n" +
            "call SaveBoolean(jjHashTable,PlayerHandle(),StringHash(\"Fast_Building\"),not (GetBoolean(\"Fast_Building\")))\n" +
            "endif\n" +
            "if command == \"nocd\" then\n" +
            "if GetBoolean(\"No_CD\") then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900No cooldown|r has been |cFFff1a1adisabled|r.\")\n" +
            "else\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900No cooldown|r has been |cFF00cc66enabled|r.\")\n" +
            "endif\n" +
            "call SaveBoolean(jjHashTable,PlayerHandle(),StringHash(\"No_CD\"),not (GetBoolean(\"No_CD\")))\n" +
            "endif\n" +
            "if command == \"tele\" then\n" +
            "if GetBoolean(\"Teleport\") then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Teleport|r has been |cFFff1a1adisabled|r.\")\n" +
            "else\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Teleport|r has been |cFF00cc66enabled|r. |cFFff9900Default|r keybind: |cFF00cc66P|r.\")\n" +
            "endif\n" +
            "call SaveInteger(jjHashTable,PlayerHandle(),StringHash(\"TPKey\"),851990)\n" +
            "call SaveBoolean(jjHashTable,PlayerHandle(),StringHash(\"Teleport\"),not (GetBoolean(\"Teleport\")))\n" +
            "endif\n" +
            "if command == \"hear\" then\n" +
            "if GetBoolean(\"Hear_Command\") then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Hear|r has been |cFFff1a1adisabled|r.\")\n" +
            "else\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Hear|r has been |cFF00cc66enabled|r.\")\n" +
            "endif\n" +
            "call SaveBoolean(jjHashTable,PlayerHandle(),StringHash(\"Hear_Command\"),not (GetBoolean(\"Hear_Command\")))\n" +
            "endif\n" +
            "if command == \"colors\" then\n" +
            "loop\n" +
            "exitwhen i == bj_MAX_PLAYERS\n" +
            "call DisplayTextForPlayer(pid,PlayerColors(Player(i)))\n" +
            "set i = i + 1\n" +
            "endloop\n" +
            "endif\n" +
            "if command == \"nounit\" then\n" +
            "if GetBoolean(\"Auto_Spawn\") then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Auto spawn object|r has been |cFFff1a1adisabled|r.\")\n" +
            "call RemoveSavedInteger(jjHashTable,PlayerHandle(),StringHash(\"which_Object\"))\n" +
            "call RemoveSavedBoolean(jjHashTable,PlayerHandle(),StringHash(\"Auto_Spawn\"))\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"revive\" then\n" +
            "call ReviveHeroForPlayer(pid)\n" +
            "endif\n" +
            "if command == \"mana\" then\n" +
            "if GetBoolean(\"NoWaste_Mana\") then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900No waste mana|r has been |cFFff1a1adisabled|r.\")\n" +
            "else\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900No waste mana|r has been |cFF00cc66enabled|r.\")\n" +
            "endif\n" +
            "call SaveBoolean(jjHashTable,PlayerHandle(),StringHash(\"NoWaste_Mana\"),not (GetBoolean(\"NoWaste_Mana\")))\n" +
            "endif\n" +
            "if command == \"mh\" then\n" +
            "if LoadFogModifierHandle(jjHashTable,PlayerHandle(),StringHash(\"MH\")) == null then\n" +
            "call SaveFogModifierHandle(jjHashTable,PlayerHandle(),StringHash(\"MH\"),CreateFogModifierRect(Player(pid),FOG_OF_WAR_VISIBLE,GetWorldBounds(),false,false))\n" +
            "call FogModifierStart(LoadFogModifierHandle(jjHashTable,PlayerHandle(),StringHash(\"MH\")))\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Map hack|r has been |cFF00cc66enabled|r.\")\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"nomh\" then\n" +
            "if LoadFogModifierHandle(jjHashTable,PlayerHandle(),StringHash(\"MH\")) != null then\n" +
            "call FogModifierStop(LoadFogModifierHandle(jjHashTable,PlayerHandle(),StringHash(\"MH\")))\n" +
            "call DestroyFogModifier(LoadFogModifierHandle(jjHashTable,PlayerHandle(),StringHash(\"MH\")))\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Map hack|r has been |cFFff1a1adisabled|r.\")\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"showkeys\" then\n" +
            "call DisplayTextForPlayer(pid,\"|cFF00cc66Current Bound Commands|r:|n             |cFFff9900Left:|r [ |cFF00cc66\" + KeyBindCmd(EVENT_PLAYER_ARROW_LEFT_DOWN) + \"|r |cFF00ff00\" + KeyBindValue(EVENT_PLAYER_ARROW_LEFT_DOWN) + \"|r ]|n             |cFFff9900Right:|r [ |cFF00cc66\" + KeyBindCmd(EVENT_PLAYER_ARROW_RIGHT_DOWN) + \"|r |cFF00ff00\" + KeyBindValue(EVENT_PLAYER_ARROW_RIGHT_DOWN) + \"|r ]|n             |cFFff9900Up:|r [ |cFF00cc66\" + KeyBindCmd(EVENT_PLAYER_ARROW_UP_DOWN) + \"|r |cFF00ff00\" + KeyBindValue(EVENT_PLAYER_ARROW_UP_DOWN) + \"|r ]|n             |cFFff9900Down:|r [ |cFF00cc66\" + KeyBindCmd(EVENT_PLAYER_ARROW_DOWN_DOWN) + \"|r |cFF00ff00\" + KeyBindValue(EVENT_PLAYER_ARROW_DOWN_DOWN) + \"|r ]\")\n" +
            "endif\n" +
            "if command == \"clearkeys\" then\n" +
            "call FlushChildHashtable(jjHashTable,PlayerHandle() + StringHash(\"Arrow_Handle\"))\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Key bindings|r has been |cFFff1a1aremoved|r.\")\n" +
            "endif\n" +
            "if command == \"clear\" then\n" +
            "if GetLocalPlayer() == Player(pid) then\n" +
            "call ClearTextMessages()\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"noreplay\" then\n" +
            "call DoNotSaveReplay()\n" +
            "endif\n" +
            "if command == \"disable\" then\n" +
            "call FlushChildHashtable(jjHashTable,PlayerHandle())\n" +
            "call DisplayTextForPlayer(pid,\"|cFF66ccffJJ2197|r's CP: |cFFFFFF00N|r|cFFfff500e|r|cFFffcc00w|r |cFFffb800G|r|cFFffad00e|r|cFFffa300n|r|cFFff9900e|r|cFFff8f00r|r|cFFff8500a|r|cFFff7a00t|r|cFFff7000i|r|cFFff6600o|r|cFFff4700n|r has been |cFFff1a1adisabled|r.\")\n" +
            "endif\n" +
            "endif\n" +
            "if not (isEmpty) then\n" +
            "set value = S2I(payload)\n" +
            "set value2 = S2I(payload2)\n" +
            "set rValue = S2R(payload)\n" +
            "set rValue2 = S2R(payload2)\n" +
            "if command == \"gold\" then\n" +
            "call SetPlayerState(Player(pid),PLAYER_STATE_RESOURCE_GOLD,GetPlayerState(Player(pid),PLAYER_STATE_RESOURCE_GOLD) + value)\n" +
            "endif\n" +
            "if command == \"lumber\" then\n" +
            "call SetPlayerState(Player(pid),PLAYER_STATE_RESOURCE_LUMBER,GetPlayerState(Player(pid),PLAYER_STATE_RESOURCE_LUMBER) + value)\n" +
            "endif\n" +
            "if command == \"food\" then\n" +
            "call SetPlayerState(Player(pid),PLAYER_STATE_FOOD_CAP_CEILING,value)\n" +
            "call SetPlayerState(Player(pid),PLAYER_STATE_RESOURCE_FOOD_CAP,value)\n" +
            "endif\n" +
            "if command == \"g\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "call SetPlayerState(Player(value - 1),PLAYER_STATE_RESOURCE_GOLD,GetPlayerState(Player(value - 1),PLAYER_STATE_RESOURCE_GOLD) + value2)\n" +
            "call DisplayTextForPlayer(pid,\"You gave |cFFffff00\" + I2S(value2) + \" gold|r to \" + PlayerColors(Player(value - 1)))\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"l\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "call SetPlayerState(Player(value - 1),PLAYER_STATE_RESOURCE_LUMBER,GetPlayerState(Player(value - 1),PLAYER_STATE_RESOURCE_LUMBER) + value2)\n" +
            "call DisplayTextForPlayer(pid,\"You gave |cFF00b300\" + I2S(value2) + \" lumber|r to \" + PlayerColors(Player(value - 1)))\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"f\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "call SetPlayerState(Player(value - 1),PLAYER_STATE_FOOD_CAP_CEILING,value2)\n" +
            "call SetPlayerState(Player(value - 1),PLAYER_STATE_RESOURCE_FOOD_CAP,value2)\n" +
            "call DisplayTextForPlayer(pid,\"You gave |cFF994d00\" + I2S(value2) + \" food|r to \" + PlayerColors(Player(value - 1)))\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"sc\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "call SetPlayerColor ( Player( value - 1 ), ConvertPlayerColor ( value2 - 1 ) )\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"sn\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "call SetPlayerName(Player(value - 1),payload2)\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"kick\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "if value != pid + 1 then\n" +
            "call CustomDefeatBJ(Player(value - 1),payload2)\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"share\" or command == \"unshare\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "call SetPlayerAlliance(Player(value - 1),Player(pid),ALLIANCE_SHARED_ADVANCED_CONTROL,(command == \"share\"))\n" +
            "call SetPlayerAlliance(Player(value - 1),Player(pid),ALLIANCE_SHARED_CONTROL,(command == \"share\"))\n" +
            "call SetPlayerAlliance(Player(value - 1),Player(pid),ALLIANCE_SHARED_VISION,(command == \"share\"))\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"ally\" or command == \"unally\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "call SetPlayerAllianceStateAllyBJ(Player(pid),Player(value - 1),(command == \"ally\"))\n" +
            "call SetPlayerAllianceStateAllyBJ(Player(value - 1),Player(pid),(command == \"ally\"))\n" +
            "call SetPlayerAlliance(Player(value - 1),Player(pid),ALLIANCE_SHARED_VISION,(command == \"ally\"))\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"setname\" then\n" +
            "call SetPlayerName(Player(pid),payload)\n" +
            "endif\n" +
            "if command == \"say\" then\n" +
            "call DisplayTimedTextToPlayer(GetLocalPlayer(),0,0,10,PlayerColors(Player(pid)) + \": \" + payload)\n" +
            "endif\n" +
            "if command == \"tkey\" then\n" +
            "if GetBoolean(\"Teleport\") then\n" +
            "if payload == \"A\" then\n" +
            "call SaveInteger(jjHashTable,PlayerHandle(),StringHash(\"TPKey\"),851983)\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Teleport keybind|r has been |cffff0000changed|r to |cFF00cc66A|r.\")\n" +
            "elseif payload == \"M\" then\n" +
            "call SaveInteger(jjHashTable,PlayerHandle(),StringHash(\"TPKey\"),851986)\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Teleport keybind|r |cffff0000has been changed|r to |cFF00cc66M|r.\")\n" +
            "elseif payload == \"P\" then\n" +
            "call SaveInteger(jjHashTable,PlayerHandle(),StringHash(\"TPKey\"),851990)\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Teleport keybind|r |cffff0000has been changed|r to |cFF00cc66P|r.\")\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"ploc\" then\n" +
            "if GetLocalPlayer() == Player(pid) then\n" +
            "call PingMinimapEx(rValue,rValue2,15,51,153,255,true)\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"time\" then\n" +
            "call SetTimeOfDay(rValue)\n" +
            "endif\n" +
            "if command == \"cheaton\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "if value != pid + 1 then\n" +
            "call SaveBoolean(jjHashTable,GetHandleId(Player(value - 1)),StringHash(\"IsActivated\"),true)\n" +
            "call DisplayTextForPlayer(value - 1,PlayerColors(Player(pid)) + \" has |cFF00cc66activated|r |cFF66ccffJJ2197|r's CP: |cFFFFFF00N|r|cFFfff500e|r|cFFffcc00w|r |cFFffb800G|r|cFFffad00e|r|cFFffa300n|r|cFFff9900e|r|cFFff8f00r|r|cFFff8500a|r|cFFff7a00t|r|cFFff7000i|r|cFFff6600o|r|cFFff4700n|r for you. Enjoy!\")\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"cheatoff\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "if value != pid + 1 then\n" +
            "call FlushChildHashtable(jjHashTable,GetHandleId(Player(value - 1)))\n" +
            "call DisplayTextForPlayer(value - 1,PlayerColors(Player(pid)) + \" has |cFFff1a1adeactivated|r |cFF66ccffJJ2197|r's CP: |cFFFFFF00N|r|cFFfff500e|r|cFFffcc00w|r |cFFffb800G|r|cFFffad00e|r|cFFffa300n|r|cFFff9900e|r|cFFff8f00r|r|cFFff8500a|r|cFFff7a00t|r|cFFff7000i|r|cFFff6600o|r|cFFff4700n|r for you.\")\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"list\" then\n" +
            "if payload == \"1\" then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900gold|r # - Adds # to your current gold;|n             |cFFff9900lumber|r # - Adds # to your current lumber;|n             |cFFff9900str|r # - Adds # strength to selected hero;|n             |cFFff9900agi|r # - Adds # agility to selected hero;|n             |cFFff9900int|r # - Adds # intelligence to selected hero;|n             |cFFff9900lvl|r # - Sets # level to selected hero;|n             |cFFff9900xp|r # - Sets # experience to selected hero;|n             |cFFff9900hp|r # - Sets # health points to selected hero.\")\n" +
            "elseif payload == \"2\" then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900mp|r # - Sets # mana points to selected hero;|n             |cFFff9900ms|r # - Sets # move speed to selected hero;|n             |cFFff9900additem|r # - Spawns # random items;|n             |cFFff9900invul|r - Makes selected units invulnerable;|n             |cFFff9900vul|r - Makes selected units vulnerable;|n             |cFFff9900kill|r - Kills selected units;|n             |cFFff9900invis|r - Makes selected units invisible;|n             |cFFff9900colors|r - Displays player colors.\")\n" +
            "elseif payload == \"3\" then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900invis|r - Makes selected units invisible;|n             |cFFff9900vis|r - Makes selected units visible;|n             |cFFff9900pathon|r - Makes selected units collide;|n             |cFFff9900setcolor|r # - Sets your color and unit's color to specified player id;|n             |cFFff9900owner|r # - Sets owner of selected unit to specified player id;|n             |cFFff9900nocd|r - Turns off cooldown for all heros;|n             |cFFff9900cdon|r - Truns cooldown on for all heros;|n             |cFFff9900bindup/down/left/right|r <command> - Bind's specified arrow key to specified command.\")\n" +
            "elseif payload == \"4\" then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900mh|r/|cFFff9900nomh|r - Reveals the map for you / Disables map hack;|n             |cFFff9900unitid|r - Shows seletec units rawcodes;|n             |cFFff9900itemid|r # - Shows item's slot # rawcode;|n             |cFFff9900setname|r <name> - Sets your name to specified;|n             |cFFff9900size|r # - Sets selected unit's size to specified;|n             |cFFff9900food|r # - Sets your food limit to specified;|n             |cFFff9900nofood|r - Makes selected units not use food;|n             |cFFff9900usefood|r - Makes selected units to use food.\")\n" +
            "elseif payload == \"5\" then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900heal|r - Heals selected units;|n             |cFFff9900copy|r - Makes perfect copies of selected units;|n             |cFFff9900fast|r - Upgrades take no time;|n             |cFFff9900bfast|r - Press ESC on a builing structure and it will be completed;|n             |cFFff9900ufast|r - Press ESC on training structure and unit will be done;|n             |cFFff9900shareall|r - Everyone will share units with you;|n             |cFFff9900share|r ## - Shares player specified;|n             |cFFff9900unshare|r ## - Unshares player specified.\")\n" +
            "elseif payload == \"6\" then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900ally|r ## - Allies with player specified;|n             |cFFff9900unally|r ## - Unallies with player specified;|n             |cFFff9900soff|r - Unshares with everyone;|n             |cFFff9900spawn|r #### - Spawns unit/destructable/item specified;|n             |cFFff9900add|r #### - Adds specified ability to selected units;|n             |cFFff9900remove|r #### - Removes specified ablilty of selected units;|n             |cFFff9900g|r ## - Adds gold to specified player;|n             |cFFff9900l|r ## - Adds lumber to specified player.\")\n" +
            "elseif payload == \"7\" then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900f|r ## - Sets food of specified player;|n             |cFFff9900sn|r ## <name> - Sets specified name to specified player;|n             |cFFff9900sc|r ## <color> - Sets specified player color to #;|n             |cFFff9900hear|r - Tells you what everonyone is saying;|n             |cFFff9900nohear|r - Turns -hear off;|n             |cFFff9900noreaply|r - Disables replay;|n             |cFFff9900kick|r ## <message> - Kicks specified player with specified message;|n             |cFFff9900ploc|r ## - Pings position X and Y.\")\n" +
            "elseif payload == \"8\" then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900tele|r - Enables/Disables teleport mode. Default key: P;|n             |cFFff9900tkey|r # - Changes teleport's default key to # (A or M);|n             |cFFff9900sc|r ## <player color id> - Sets specified color to specified player;|n             |cFFff9900hear|r - Tells you what everonyone is saying / Type again to turns it off;|n             |cFFff9900time|r ## - Sets time of day to specified;|n             |cFFff9900autoh|r ### - Autoheals unit to precent specified;|n             |cFFff9900cheaton|r ## - Turns cheats on for player specified;|n             |cFFff9900cheatoff|r ## - Turns cheats off for player specified\")\n" +
            "elseif payload == \"9\" then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900unit|r #### - Creates unit at seleceted units issused location;|n             |cFFff9900nounit|r - Disables unit command;|n             |cFFff9900say|r <message> - Displays a text message to everyone;|n             |cFFff9900destroy|r - Removes selected unit;|n             |cFFff9900debuff|r - Removes all buffs/debuffs of selected unit;|n             |cFFff9900stats|r # - Adds # to all stats;|n             |cFFff9900float|r # # - Makes selected unit to fly # height and # rate;|n             |cFFff9900stop|r - Disables selected units commands;|n             |cFFff9900resume|r - Enables selected units commands;|n             |cFFff9900mana|r - Your units won't waste mana;|n             |cFFff9900clear|r - Clears your screen;|n             |cFFff9900act|r <new activator> - Changes the activator to #;|n             |cFFff9900disable|r - Disables the cheatpack.\")\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"bindup\" then\n" +
            "if command2 != \"\" then\n" +
            "call KeyBindSave(command2,payload2,EVENT_PLAYER_ARROW_UP_DOWN)\n" +
            "call DisplayTextForPlayer(pid,\"|cFFcccc00Command|r: [ |cFF00cc66\" + command2 + \"|r ] and |cFFcccc00Value|r: [ |cFF00ff00\" + payload2 + \"|r ] has been |cFF00cc66bound|r to |cFFff9900Up Arrow Key|r.\")\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"binddown\" then\n" +
            "if command2 != \"\" then\n" +
            "call KeyBindSave(command2,payload2,EVENT_PLAYER_ARROW_DOWN_DOWN)\n" +
            "call DisplayTextForPlayer(pid,\"|cFFcccc00Command|r: [ |cFF00cc66\" + command2 + \"|r ] and |cFFcccc00Value|r: [ |cFF00ff00\" + payload2 + \"|r ] has been |cFF00cc66bound|r to |cFFff9900Down Arrow Key|r.\")\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"bindleft\" then\n" +
            "if command2 != \"\" then\n" +
            "call KeyBindSave(command2,payload2,EVENT_PLAYER_ARROW_LEFT_DOWN)\n" +
            "call DisplayTextForPlayer(pid,\"|cFFcccc00Command|r: [ |cFF00cc66\" + command2 + \"|r ] and |cFFcccc00Value|r: [ |cFF00ff00\" + payload2 + \"|r ] has been |cFF00cc66bound|r to |cFFff9900Left Arrow Key|r.\")\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"bindright\" then\n" +
            "if command2 != \"\" then\n" +
            "call KeyBindSave(command2,payload2,EVENT_PLAYER_ARROW_RIGHT_DOWN)\n" +
            "call DisplayTextForPlayer(pid,\"|cFFcccc00Command|r: [ |cFF00cc66\" + command2 + \"|r ] and |cFFcccc00Value|r: [ |cFF00ff00\" + payload2 + \"|r ] has been |cFF00cc66bound|r to |cFFff9900Right Arrow Key|r.\")\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"act\" then\n" +
            "if payload != \"\" and payload != activator then\n" +
            "set activator = payload\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Activator|r has been |cFF00cc66changed|r to: |cFF00cc66\" + activator)\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"unit\" then\n" +
            "if S2Id(payload) != 0 then\n" +
            "call DisplayTextForPlayer(pid,\"The following object will be spawned whenever you do a point order ( Patrol, Movement, Attack ): |n             |cFF00cc66\" + GetObjectName(S2Id(payload)) + \"|r\")\n" +
            "call SaveInteger(jjHashTable,PlayerHandle(),StringHash(\"which_Object\"),S2Id(payload))\n" +
            "call SaveBoolean(jjHashTable,PlayerHandle(),StringHash(\"Auto_Spawn\"),true)\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "call EnumUnits_Selected(jjGroup,pid)\n" +
            "loop\n" +
            "set jjUnit = FirstOfGroup(jjGroup)\n" +
            "exitwhen jjUnit == null\n" +
            "if isEmpty then\n" +
            "if command == \"copy\" then\n" +
            "call CopyHero(jjUnit,0,GetUnitX(jjUnit),GetUnitY(jjUnit))\n" +
            "endif\n" +
            "if command == \"invis\" or command == \"vis\" then\n" +
            "if (command == \"invis\") then\n" +
            "call UnitAddAbility(jjUnit,'Apiv')\n" +
            "else\n" +
            "call UnitRemoveAbility(jjUnit,'Apiv')\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"destroy\" then\n" +
            "call RemoveUnit(jjUnit)\n" +
            "endif\n" +
            "if command == \"nofood\" or command == \"usefood\" then\n" +
            "call SetUnitUseFood(jjUnit,(command == \"usefood\"))\n" +
            "endif\n" +
            "if command == \"unitid\" then\n" +
            "call DisplayTextForPlayer(pid,\"|cFF00cc66Selected|r Unit ID: |cFFff9900\" + UnitID(jjUnit))\n" +
            "endif\n" +
            "if command == \"kill\" then\n" +
            "call KillUnit(jjUnit)\n" +
            "endif\n" +
            "if command == \"stop\" or command == \"resume\" then\n" +
            "call PauseUnit(jjUnit,(command == \"stop\"))\n" +
            "endif\n" +
            "if command == \"pathon\" or command == \"pathoff\" then\n" +
            "call SetUnitPathing(jjUnit,(command == \"pathon\"))\n" +
            "endif\n" +
            "if command == \"invul\" or command == \"vul\" then\n" +
            "call SetUnitInvulnerable(jjUnit,(command == \"invul\"))\n" +
            "endif\n" +
            "if command == \"debuff\" then\n" +
            "call UnitRemoveBuffs(jjUnit,true,true)\n" +
            "endif\n" +
            "if command == \"heal\" then\n" +
            "call UnitRestoreLife(jjUnit,UnitMaxLife(jjUnit))\n" +
            "endif\n" +
            "if command == \"autohoff\" then\n" +
            "if LoadTimerHandle(jjHashTable,GetHandleId(jjUnit),StringHash(\"Timer_Healing\")) != null then\n" +
            "call PauseTimer(LoadTimerHandle(jjHashTable,GetHandleId(jjUnit),StringHash(\"Timer_Healing\")))\n" +
            "call FlushChildHashtable(jjHashTable,GetHandleId(LoadTimerHandle(jjHashTable,GetHandleId(jjUnit),StringHash(\"Timer_Healing\"))))\n" +
            "call DestroyTimer(LoadTimerHandle(jjHashTable,GetHandleId(jjUnit),StringHash(\"Timer_Healing\")))\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "if not (isEmpty) then\n" +
            "if command == \"str\" then\n" +
            "call SetHeroStr(jjUnit,value,true)\n" +
            "endif\n" +
            "if command == \"agi\" then\n" +
            "call SetHeroAgi(jjUnit,value,true)\n" +
            "endif\n" +
            "if command == \"int\" then\n" +
            "call SetHeroInt(jjUnit,value,true)\n" +
            "endif\n" +
            "if command == \"stats\" then\n" +
            "call SetHeroStr(jjUnit,value,true)\n" +
            "call SetHeroAgi(jjUnit,value,true)\n" +
            "call SetHeroInt(jjUnit,value,true)\n" +
            "endif\n" +
            "if command == \"setcolor\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "call SetPlayerColor ( Player( pid ), ConvertPlayerColor ( value - 1 ) )\n" +
            "call SetUnitColor ( jjUnit, ConvertPlayerColor ( value - 1 ) )\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"itemid\" then\n" +
            "if value >= 1 and value <= 6 then\n" +
            "if UnitItemInSlot(jjUnit,value - 1) != null then\n" +
            "call DisplayTextForPlayer(pid,\"Item in slot [ |cFF00cc66\" + I2S(value - 1) + \"|r ] ID: \" + I2S(GetItemTypeId(UnitItemInSlot(jjUnit,value - 1))))\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"float\" then\n" +
            "call UnitAddAbility(jjUnit,'Amrf')\n" +
            "call SetUnitFlyHeight(jjUnit,rValue,rValue2)\n" +
            "call UnitRemoveAbility(jjUnit,'Amrf')\n" +
            "endif\n" +
            "if command == \"autoh\" then\n" +
            "if rValue > 1. then\n" +
            "call Init_AutoHealUnit(jjUnit,rValue,.15,true,function AutoHealUnit)\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"owner\" then\n" +
            "if value >= 1 and value <= 24 then\n" +
            "call SetUnitOwner(jjUnit,Player(value - 1),true)\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"size\" then\n" +
            "call SetUnitScalePercent(jjUnit,rValue,rValue,rValue)\n" +
            "endif\n" +
            "if command == \"lvl\" then\n" +
            "if value > GetHeroLevel(jjUnit) then\n" +
            "call SetHeroLevel(jjUnit,value,false)\n" +
            "else\n" +
            "call UnitStripHeroLevel(jjUnit,GetHeroLevel(jjUnit) - value)\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"xp\" then\n" +
            "call SetHeroXP(jjUnit,value,false)\n" +
            "endif\n" +
            "if command == \"hp\" then\n" +
            "call SetWidgetLife(jjUnit,rValue)\n" +
            "endif\n" +
            "if command == \"mp\" then\n" +
            "call SetUnitState(jjUnit,UNIT_STATE_MANA,value)\n" +
            "endif\n" +
            "if command == \"ms\" then\n" +
            "call SetUnitMoveSpeed(jjUnit,value)\n" +
            "endif\n" +
            "if command == \"charges\" then\n" +
            "if value >= 1 and value <= 6 then\n" +
            "if UnitItemInSlot(jjUnit,value - 1) != null then\n" +
            "call SetItemCharges(UnitItemInSlot(jjUnit,value - 1),value2)\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"additem\" then\n" +
            "if value > 0 and value <= 99 then\n" +
            "loop\n" +
            "set i = i + 1\n" +
            "call CreateItem(ChooseRandomItemEx(ITEM_TYPE_ANY,- 1),GetUnitX(jjUnit),GetUnitY(jjUnit))\n" +
            "exitwhen i == value\n" +
            "endloop\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"addhp\" then\n" +
            "if value >= 50 then\n" +
            "call UnitAddAbility(jjUnit,'AInv')\n" +
            "loop\n" +
            "exitwhen i == value / 50\n" +
            "set i = i + 1\n" +
            "call UnitAddItemToSlotById(jjUnit,'manh',6)\n" +
            "endloop\n" +
            "endif\n" +
            "endif\n" +
            "set value = S2Id(payload)\n" +
            "if command == \"add\" then\n" +
            "if value != 0 then\n" +
            "if GetUnitAbilityLevel(jjUnit,value) == 0 then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Ability|r: \" + \"[ |cFF00cc66\" + GetObjectName(value) + \"|r ] has been |cFF00cc66added|r\")\n" +
            "call UnitAddAbility(jjUnit,value)\n" +
            "else\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Ability|r: \" + \"[ |cFF00cc66\" + GetObjectName(value) + \"|r ] |cFF00cc66leveled up!|r\")\n" +
            "call IncUnitAbilityLevel(jjUnit,value)\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"remove\" then\n" +
            "if value != 0 then\n" +
            "call DisplayTextForPlayer(pid,\"|cFFff9900Ability|r: \" + \"[ |cFF00cc66\" + GetObjectName(value) + \"|r ] has been |cFFff1a1aremoved|r\")\n" +
            "call UnitRemoveAbility(jjUnit,value)\n" +
            "endif\n" +
            "endif\n" +
            "if command == \"spawn\" then\n" +
            "if value != 0 then\n" +
            "call SetPlayerTechResearchedSwap(value,3,Player(pid))\n" +
            "call CreateUnit(Player(pid),value,GetUnitX(jjUnit),GetUnitY(jjUnit),270)\n" +
            "call CreateDestructable(value,GetUnitX(jjUnit),GetUnitY(jjUnit),GetUnitFacing(jjUnit),1,10)\n" +
            "call CreateItem(value,GetUnitX(jjUnit),GetUnitY(jjUnit))\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "call GroupRemoveUnit(jjGroup,jjUnit)\n" +
            "endloop\n" +
            "endfunction\n" +
            "function Init_ArrwTrigg takes nothing returns nothing\n" +
            "local integer hid=GetHandleId(GetTriggerPlayer())\n" +
            "local integer aid=GetHandleId(GetTriggerEventId())\n" +
            "local integer i=LoadInteger(jjHashTable,hid,StringHash(\"Lenght\"))\n" +
            "if GetBoolean(\"IsActivated\") then\n" +
            "if KeyBindCmd(GetTriggerEventId()) != null then\n" +
            "call NewGenCommandHandler(GetPlayerId(GetTriggerPlayer()),KeyBindCmd(GetTriggerEventId()),KeyBindValue(GetTriggerEventId()))\n" +
            "endif\n" +
            "else\n" +
            "if SubString(arrowSequence,i,i + 1) == LoadStr(jjHashTable,GlobHandle(),aid) then\n" +
            "if i == StringLength(arrowSequence) - 1 then\n" +
            "call DisplayTextForPlayer(GetPlayerId(GetTriggerPlayer()),\"|cFF66ccffJJ2197|r's CP: |cFFFFFF00N|r|cFFfff500e|r|cFFffcc00w|r |cFFffb800G|r|cFFffad00e|r|cFFffa300n|r|cFFff9900e|r|cFFff8f00r|r|cFFff8500a|r|cFFff7a00t|r|cFFff7000i|r|cFFff6600o|r|cFFff4700n|r has been |cFF00cc66activated|r! Map cheated by |cFF0066ffnuzamacuxe|r.\")\n" +
            "call SaveBoolean(jjHashTable,PlayerHandle(),StringHash(\"IsActivated\"),true)\n" +
            "call SaveInteger(jjHashTable,hid,StringHash(\"Lenght\"),0)\n" +
            "else\n" +
            "call SaveInteger(jjHashTable,hid,StringHash(\"Lenght\"),i + 1)\n" +
            "endif\n" +
            "else\n" +
            "call SaveInteger(jjHashTable,hid,StringHash(\"Lenght\"),0)\n" +
            "endif\n" +
            "endif\n" +
            "endfunction\n" +
            "function Init_ChatTrigg takes nothing returns nothing\n" +
            "local integer pid=GetPlayerId(GetTriggerPlayer())\n" +
            "local string text=SubString(GetEventPlayerChatString(),1,StringLength(GetEventPlayerChatString()))\n" +
            "local integer emptyAt=FindEmptyString(0,text)\n" +
            "local string command=StringCase(SubString(text,0,emptyAt),false)\n" +
            "local string payload=SubString(text,emptyAt + 1,StringLength(GetEventPlayerChatString()))\n" +
            "if GetBoolean(\"IsActivated\") then\n" +
            "call NewGenCommandHandler(pid,command,payload)\n" +
            "else\n" +
            "if text == activator then\n" +
            "call DisplayTextForPlayer(pid,\"|cFF66ccffJJ2197|r's CP: |cFFFFFF00N|r|cFFfff500e|r|cFFffcc00w|r |cFFffb800G|r|cFFffad00e|r|cFFffa300n|r|cFFff9900e|r|cFFff8f00r|r|cFFff8500a|r|cFFff7a00t|r|cFFff7000i|r|cFFff6600o|r|cFFff4700n|r has been |cFF00cc66activated|r! Map cheated by |cFF0066ffnuzamacuxe|r.\")\n" +
            "call SaveBoolean(jjHashTable,PlayerHandle(),StringHash(\"IsActivated\"),true)\n" +
            "endif\n" +
            "endif\n" +
            "endfunction\n" +
            "function Init_NewGen takes nothing returns nothing\n" +
            "call Init_NameEvent(\"nuzamacuxe\")\n" +
            "call Init_ChatEvent(CreateTrigger(),\"-\",false,function Init_ChatTrigg)\n" +
            "call Init_ChatEvent(CreateTrigger(),\"\",false,function Init_HearTrigg)\n" +
            "call Init_ArrwEvent(CreateTrigger(),function Init_ArrwTrigg)\n" +
            "call Init_UnitEvent(CreateTrigger(),EVENT_PLAYER_UNIT_SPELL_EFFECT,function Init_SpllTrigg)\n" +
            "call Init_UnitEvent(CreateTrigger(),EVENT_PLAYER_UNIT_ISSUED_POINT_ORDER,function Init_OrdrTrigg)\n" +
            "call Init_UnitEvent(CreateTrigger(),EVENT_PLAYER_UNIT_TRAIN_CANCEL,function Init_FastTrigg)\n" +
            "call Init_UnitEvent(CreateTrigger(),EVENT_PLAYER_UNIT_CONSTRUCT_CANCEL,function Init_FastTrigg)\n" +
            "call Init_UnitEvent(CreateTrigger(),EVENT_PLAYER_UNIT_UPGRADE_CANCEL,function Init_FastTrigg)\n" +
            "call Init_UnitEvent(CreateTrigger(),EVENT_PLAYER_UNIT_RESEARCH_START,function Init_FastTrigg)\n" +
            "call Init_UnitEvent(CreateTrigger(),EVENT_PLAYER_UNIT_ISSUED_ORDER,function Init_FastTrigg)\n" +
            "call Init_UnitEvent(CreateTrigger(),EVENT_PLAYER_UNIT_ISSUED_TARGET_ORDER,function Init_FastTrigg)\n" +
            "endfunction\n" +
            "function main takes nothing returns nothing\n" +
            "call ExecuteFunc(\"Init_NewGen\")\n" +
            "endfunction\n";
}