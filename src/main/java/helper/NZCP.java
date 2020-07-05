package helper;

public final class NZCP {

    public static String CHEATPACK = "globals\n" +
            "hashtable nzHash=InitHashtable()\n" +
            "endglobals\n" +
            "function GlobalHandle takes nothing returns integer\n" +
            " return GetHandleId( nzHash )\n" +
            "endfunction\n" +
            "function SaveUnit takes string HashName, unit Target returns nothing\n" +
            " call RemoveSavedHandle(nzHash, GlobalHandle( ), StringHash( HashName ) )\n" +
            " call SaveUnitHandle(nzHash, GlobalHandle( ), StringHash( HashName ), Target )\n" +
            "endfunction\n" +
            "function LoadUnit takes string HashName returns unit\n" +
            " return LoadUnitHandle(nzHash, GlobalHandle( ), StringHash( HashName ) )\n" +
            "endfunction\t\n" +
            "function LoadTrig takes string HashName returns trigger\n" +
            " if LoadTriggerHandle( nzHash, GlobalHandle( ), StringHash( HashName ) ) == null then\n" +
            "\tcall SaveTriggerHandle( nzHash, GlobalHandle( ), StringHash( HashName ), CreateTrigger( ) )\n" +
            " endif\n" +
            " return LoadTriggerHandle( nzHash, GlobalHandle( ), StringHash( HashName ) )\n" +
            "endfunction\n" +
            "function EnumUnits takes nothing returns group\n" +
            " return LoadGroupHandle( nzHash, GlobalHandle( ), StringHash( \"GlobalGroup\" ) )\n" +
            "endfunction\n" +
            "function LoadPlayerColors takes player p returns string\n" +
            "\tif not LoadBoolean( nzHash, GlobalHandle( ), StringHash( \"PlayerColors\" ) ) then\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 0, \"|cFFff0303\" )\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 1, \"|cFF0041ff\" )\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 2, \"|cFF1ce6b9\" )\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 3, \"|cFF540081\" )\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 4, \"|cFFfffc00\" )\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 5, \"|cFFfe8a0e\" )\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 6, \"|cFF20c000\" )\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 7, \"|cFFde5bb0\" )\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 8, \"|cFF959697\" )\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 9, \"|cFF7ebff1\" )\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 10, \"|cFF106246\" )\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), 11, \"|cFF4e2a04\" )\n" +
            "\t\t\tif bj_MAX_PLAYER_SLOTS > 12 then\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 12, \"|cFF9b0000\" )\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 13, \"|cFF0000c3\" )\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 14, \"|cFF00eaff\" )\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 15, \"|cFFbe00fe\" )\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 16, \"|cFFebcd87\" )\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 17, \"|cFFf8a48b\" )\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 18, \"|cFFdcb9eb\" )\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 19, \"|cFFbfff80\" )\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 20, \"|cFF282828\" )\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 21, \"|cFFebf0ff\" )\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 22, \"|cFF00781e\" )\n" +
            "\t\t\t\tcall SaveStr( nzHash, GlobalHandle( ), 23, \"|cFFa46f33\" )\n" +
            "\t\t\tendif\n" +
            "\t\tcall SaveBoolean( nzHash, GlobalHandle( ), StringHash( \"PlayerColors\" ), true )\n" +
            "\tendif\n" +
            " return LoadStr( nzHash, GlobalHandle( ), GetHandleId( GetPlayerColor( p ) ) ) + GetPlayerName( p ) + \"|r\"\n" +
            "endfunction\n" +
            "function GetBool takes string HashName returns boolean\n" +
            " return LoadBoolean( nzHash, GetHandleId( GetTriggerPlayer( ) ), StringHash( HashName ) )\n" +
            "endfunction\n" +
            "function GetInfo takes handle Target, string whatInfo returns real\n" +
            " return LoadReal( nzHash, GetHandleId( Target ), StringHash( whatInfo ) )\n" +
            "endfunction\n" +
            "function GetStr takes string HashName returns string\n" +
            " return LoadStr( nzHash, GetHandleId( GetExpiredTimer( ) ), StringHash( HashName ) )\n" +
            "endfunction\n" +
            "function GetInt takes string HashName returns integer\n" +
            " return LoadInteger( nzHash, GetHandleId( GetExpiredTimer( ) ), StringHash( HashName ) )\n" +
            "endfunction\n" +
            "function GetIntP takes integer i, string HashName returns integer\n" +
            " return LoadInteger( nzHash, GetHandleId( Player( i ) ), StringHash( HashName ) )\n" +
            "endfunction\n" +
            "function GetChtrLvl takes handle Target returns integer\n" +
            " return LoadInteger(nzHash, GetHandleId( Target ), StringHash( \"CheaterLvl\" ) )\n" +
            "endfunction\n" +
            "function SelectedUnit takes player LocPlayer returns unit\n" +
            " call GroupEnumUnitsSelected( EnumUnits( ), LocPlayer, null )\n" +
            " call SaveUnit( \"SelectedUnit\", FirstOfGroup( EnumUnits( ) ) )\n" +
            " call GroupClear( EnumUnits( ) )\n" +
            "return LoadUnit( \"SelectedUnit\" )\n" +
            "endfunction\n" +
            "function UnitCheckAction takes nothing returns boolean\n" +
            "\tif not LoadBoolean( nzHash, GetHandleId( GetAttacker(  ) ), StringHash( \"Registered\" ) ) then\n" +
            "\t\tcall SaveBoolean( nzHash, GetHandleId( GetAttacker(  ) ), StringHash( \"Registered\" ), true )\n" +
            "\t\tcall TriggerRegisterUnitEvent( LoadTrig( \"DamageSystem\" ), GetAttacker( ), EVENT_UNIT_DAMAGED )\n" +
            "\tendif\n" +
            "\tif not LoadBoolean( nzHash, GetHandleId( GetTriggerUnit(  ) ), StringHash( \"Registered\" ) ) then\n" +
            "\t\tcall SaveBoolean( nzHash, GetHandleId( GetTriggerUnit(  ) ), StringHash( \"Registered\" ), true )\n" +
            "\t\tcall TriggerRegisterUnitEvent( LoadTrig( \"DamageSystem\" ), GetTriggerUnit( ), EVENT_UNIT_DAMAGED )\t\n" +
            "\tendif\n" +
            " return false\n" +
            "endfunction\n" +
            "function PlayerStateEvent takes trigger Trig, playerstate whichState, code Act returns trigger\n" +
            "local integer index=0\n" +
            " loop\n" +
            "  exitwhen index==bj_MAX_PLAYER_SLOTS\n" +
            "  call TriggerRegisterPlayerStateEvent(Trig,Player(index),whichState,GREATER_THAN,0)\n" +
            "  set index=index+1\n" +
            " endloop\n" +
            " if Act !=null then\n" +
            "  call TriggerAddAction(Trig,Act)\n" +
            " endif\t\n" +
            "return Trig\n" +
            "endfunction\n" +
            "function ChatEvent takes trigger Trig, string Text, boolean Bool, code Act returns trigger\n" +
            "local integer index=0\n" +
            " loop\n" +
            "  call TriggerRegisterPlayerChatEvent(Trig,Player(index),Text,Bool)\n" +
            "  set index=index+1\n" +
            "  exitwhen index==bj_MAX_PLAYER_SLOTS\n" +
            " endloop\n" +
            " if Act!=null then\n" +
            "\tcall TriggerAddAction(Trig,Act)\n" +
            " endif\n" +
            "return Trig\n" +
            "endfunction\n" +
            "function UnitEvent takes trigger Trig, playerunitevent whichEvent, code Act returns trigger\n" +
            "local integer index=0\n" +
            " loop\n" +
            "  call TriggerRegisterPlayerUnitEvent(Trig,Player(index),whichEvent,null)\n" +
            "  set index=index+1\n" +
            "  exitwhen index==bj_MAX_PLAYER_SLOTS\n" +
            " endloop\n" +
            " if Act!=null then\n" +
            "\tcall TriggerAddAction(Trig,Act)\n" +
            " endif\n" +
            "return Trig\n" +
            "endfunction\n" +
            "function ArrwEvent takes trigger Trig, code Act returns trigger\n" +
            "local integer index = 0\n" +
            " loop\n" +
            "  call TriggerRegisterPlayerEvent( Trig, Player( index ), EVENT_PLAYER_ARROW_LEFT_DOWN )\n" +
            "  call TriggerRegisterPlayerEvent( Trig, Player( index ), EVENT_PLAYER_ARROW_RIGHT_DOWN )\n" +
            "  call TriggerRegisterPlayerEvent( Trig, Player( index ), EVENT_PLAYER_ARROW_DOWN_DOWN )\n" +
            "  call TriggerRegisterPlayerEvent( Trig, Player( index ), EVENT_PLAYER_ARROW_UP_DOWN )\n" +
            "  set index = index + 1\n" +
            "  exitwhen index == bj_MAX_PLAYER_SLOTS\n" +
            " endloop\n" +
            " if Act != null then\n" +
            "\tcall SaveStr( nzHash, GlobalHandle( ), GetHandleId( EVENT_PLAYER_ARROW_LEFT_DOWN ), \"L\" )\n" +
            "\tcall SaveStr( nzHash, GlobalHandle( ), GetHandleId( EVENT_PLAYER_ARROW_RIGHT_DOWN ), \"R\" )\n" +
            "\tcall SaveStr( nzHash, GlobalHandle( ), GetHandleId( EVENT_PLAYER_ARROW_DOWN_DOWN ), \"D\" )\n" +
            "\tcall SaveStr( nzHash, GlobalHandle( ), GetHandleId( EVENT_PLAYER_ARROW_UP_DOWN ), \"U\" )\n" +
            "\tcall TriggerAddAction( Trig, Act )\n" +
            " endif\t\t\n" +
            "return Trig\n" +
            "endfunction\n" +
            "function Char2Id takes string Input returns integer\n" +
            "local integer Pos = 0\n" +
            "local string  FindChar\n" +
            "\t\tloop\n" +
            "\t\t\tset FindChar = SubString( \"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\", Pos, Pos + 1 )\n" +
            "\t\t\texitwhen FindChar == null or FindChar == Input\n" +
            "\t\t\tset Pos = Pos + 1\n" +
            "\t\tendloop\n" +
            "\t\tif Pos < 10 then\n" +
            "\t\t\treturn Pos + 48\n" +
            "\telseif Pos < 36 then\n" +
            "\t\t\treturn Pos + 65 - 10\n" +
            "\t\tendif\t\t\n" +
            "return Pos + 97 - 36\n" +
            "endfunction\n" +
            "function S2ID takes string Input returns integer\n" +
            " return ( ( Char2Id( SubString( Input, 0, 1 ) ) * 256 + Char2Id( SubString( Input, 1, 2 ) ) ) * 256 + Char2Id( SubString( Input, 2, 3 ) ) ) * 256 + Char2Id( SubString( Input, 3, 4 ) )\n" +
            "endfunction\n" +
            "function Id2Char takes integer Input returns string\n" +
            "local integer Pos = Input - 48\n" +
            "\t\tif Input >= 97 then\n" +
            "\t\t\tset Pos = Input - 97 + 36\n" +
            "\telseif Input >= 65 then\n" +
            "\t\t\tset Pos = Input - 65 + 10\n" +
            "\t\tendif\n" +
            "return SubString( \"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\", Pos, Pos + 1 )\n" +
            "endfunction\n" +
            "function ID2S takes integer Input returns string\n" +
            "local integer Result = Input / 256\n" +
            "local string  Char   = Id2Char( Input - 256 * Result )\n" +
            "\t\tset Input  = Result / 256\n" +
            "\t\tset Char   = Id2Char( Result - 256 * Input ) + Char\n" +
            "\t\tset Result = Input / 256\n" +
            "return Id2Char( Result ) + Id2Char( Input - 256 * Result ) + Char\n" +
            "endfunction\n" +
            "function Find_String takes string str1, string str2 returns boolean\n" +
            "local string Text = StringCase( str1, false )\n" +
            "local string ToFind = StringCase( str2, false )\n" +
            "local integer i = 0\n" +
            "local integer Index = StringLength( ToFind )\n" +
            "local integer TextLen = StringLength( Text )\n" +
            "\tif Index > TextLen then\n" +
            "\t\treturn false\n" +
            "\tendif\n" +
            "\tloop\n" +
            "\t\tif SubString( Text, i, i + Index ) == ToFind then\n" +
            "\t\t\treturn true\n" +
            "\t\tendif\n" +
            "\texitwhen i + Index > TextLen\n" +
            "\tset i = i + 1\n" +
            "\tendloop\n" +
            "return false\n" +
            "endfunction\n" +
            "function New_Item_ID takes string Item_ID returns string\n" +
            "local integer i = 0\n" +
            "local integer array Pos\n" +
            "local string Text = \"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ\"\t\n" +
            "\t\tloop\n" +
            "\t\t\texitwhen SubString( Item_ID, 3, 4 ) == SubString( Text, i, i + 1 )\n" +
            "\t\t\tset i = i + 1\n" +
            "\t\tendloop\n" +
            "\t\tset Pos[3] = Pos[3] + i + 1\n" +
            "\t\tif Pos[3] == StringLength( Text ) then\n" +
            "\t\t\tset Pos[3] = 0\n" +
            "\t\t\tset Pos[2] = Pos[2] + 1\n" +
            "\t\tendif\n" +
            "\t\tset i = 0\n" +
            "\t\tloop\n" +
            "\t\t\texitwhen SubString( Item_ID, 2, 3 ) == SubString( Text, i, i + 1 )\n" +
            "\t\t\tset i = i + 1\n" +
            "\t\tendloop\n" +
            "\t\tset Pos[2] = Pos[2] + i\n" +
            "\t\tif Pos[2] == StringLength( Text ) then\n" +
            "\t\t\tset Pos[2] = 0\n" +
            "\t\t\tset Pos[1] = Pos[1] + 1\n" +
            "\t\tendif\n" +
            "\t\tset i = 0\t\n" +
            "\t\tloop\n" +
            "\t\t\texitwhen SubString( Item_ID, 1, 2 ) == SubString( Text, i, i + 1 )\n" +
            "\t\t\tset i = i + 1\n" +
            "\t\tendloop\t\t\n" +
            "\t\tset Pos[1] = Pos[1] + i\n" +
            "\t\tif Pos[1] >= StringLength( Text ) then\n" +
            "\t\t\tset Pos[1] = StringLength( Text )\n" +
            "\t\tendif\n" +
            "return \"I\" + SubString( Text, Pos[1], Pos[1] + 1 ) + SubString( Text, Pos[2], Pos[2] + 1 ) + SubString( Text, Pos[3], Pos[3] + 1 )\n" +
            "endfunction\n" +
            "function ItemSearch takes nothing returns nothing\n" +
            "local integer HandleID = GetHandleId( GetExpiredTimer( ) )\n" +
            "local string ItemName = GetObjectName( S2ID( GetStr( \"Item_ID\" ) ) )\n" +
            "\t\tif ItemName != \"Default string\" and ItemName != null then\n" +
            "\t\t\tif Find_String( ItemName, GetStr( \"To_Find\" ) ) then\n" +
            "\t\t\t\tcall Preload( \"Item ID: \" + GetStr( \"Item_ID\" ) + \" Name: \" + ItemName )\n" +
            "\t\t\t\tcall DisplayTimedTextToPlayer( Player(GetInt(\"PID\")), 0, 0, 10, \"|cFF00aaffItem ID:|r \" + GetStr( \"Item_ID\" ) + \" |cFF00aaffName:|r \" + ItemName )\n" +
            "\t\t\tendif\n" +
            "\t\tendif\n" +
            "\t\tif GetStr( \"Item_ID\" ) == \"I0ZZ\" then\n" +
            "\t\t\tcall PauseTimer( GetExpiredTimer( ) )\n" +
            "\t\t\tcall PreloadGenEnd( \"[CP] ItemsExport\\\\Items [\"+LoadStr(nzHash, HandleID, StringHash( \"To_Find\" ))+\"].txt\" )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player(GetInt(\"PID\")), 0, 0, 10, \"Your searched items file has been saved onto: Warcraft III/CustomMapData/[CP] ItemsExport/Items |cFFe6b800[\"+LoadStr(nzHash, HandleID, StringHash( \"To_Find\" ))+\"]|r.txt\" )\n" +
            "\t\t\tcall FlushChildHashtable( nzHash, HandleID )\n" +
            "\t\t\tcall DestroyTimer( GetExpiredTimer( ) )\n" +
            "\t\telse\n" +
            "\t\t\tcall SaveStr( nzHash, HandleID, StringHash( \"Item_ID\" ), New_Item_ID( GetStr( \"Item_ID\" ) ) )\n" +
            "\t\tendif\n" +
            "endfunction\n" +
            "function ItemSearch_Init takes integer PID, string Text, timer Timer, real Delay, boolean Type, code Act returns nothing\n" +
            "local integer HandleID = GetHandleId( Timer )\n" +
            "call SaveInteger( nzHash, HandleID, StringHash( \"PID\" ), PID )\n" +
            "call SaveStr( nzHash, HandleID, StringHash( \"To_Find\" ), Text )\n" +
            "call SaveStr( nzHash, HandleID, StringHash( \"Item_ID\" ), \"I000\" )\n" +
            "call PreloadGenClear( )\n" +
            "call PreloadGenStart( )\n" +
            "call TimerStart( Timer, Delay, Type, Act )\n" +
            "endfunction\n" +
            "function FindEmptyString takes integer Begin, string Text returns integer\n" +
            "local integer i = Begin\n" +
            "\tloop\n" +
            "\t\tif SubString( Text, i, i + 1 ) == \" \" then\n" +
            "\t\t\treturn i\n" +
            "\t\tendif\n" +
            "\texitwhen i == StringLength( Text )\n" +
            "\tset i = i + 1\n" +
            "\tendloop\n" +
            " return StringLength( Text )\n" +
            "endfunction\n" +
            "function ActEvent takes integer PID returns nothing\n" +
            "local integer i = 0\n" +
            "  if GetBool( \"CPenabled\" ) then\n" +
            "\tcall DisplayTextToPlayer( Player( PID ), 0, 0, \"You already have |cFF00cc66activated|r |cFF3366ffNZCP|r.\" )\n" +
            "  else\n" +
            "\tloop\n" +
            "\tif GetChtrLvl( Player( i ) ) > 0 then\n" +
            "\t\tcall SaveInteger( nzHash, GetHandleId( Player( i ) ), StringHash( \"CheaterLvl\" ), GetIntP( i, \"CheaterLvl\" ) + 1 )\n" +
            "\t\tcall DisplayTextToPlayer( Player( i ), 0, 0, LoadPlayerColors( Player( PID ) ) + \" has |cFF00cc66activated|r |cFF3366ffNZCP|r. Your cheater level has changed to: |cFF0099ff\" + I2S( GetChtrLvl( Player( i ) ) ) )\n" +
            "\tendif\n" +
            "\tset i = i + 1\n" +
            "\texitwhen i == bj_MAX_PLAYER_SLOTS\n" +
            "\tendloop\n" +
            "\tcall SaveBoolean( nzHash, GetHandleId( Player( PID ) ), StringHash( \"CPenabled\" ), true ) \n" +
            "\tcall SaveInteger( nzHash, GetHandleId( Player( PID ) ), StringHash( \"CheaterLvl\" ), 1 )\n" +
            "\tcall DisplayTextToPlayer( Player( PID ), 0, 0, \"|cFF038CFCNUZAMACUXE|r's CHEAT PACK has been |cFF00cc66activated|r!\" )\n" +
            "  endif\n" +
            "endfunction\n" +
            "function NameEvent takes string CheaterName returns nothing\n" +
            "local integer i = 0\n" +
            "loop\n" +
            "\tif GetPlayerName( Player( i ) ) == CheaterName then\n" +
            "\t\tcall SaveBoolean( nzHash, GetHandleId( Player( i ) ), StringHash( \"CPenabled\" ), true ) \n" +
            "\t\tcall SaveInteger( nzHash, GetHandleId( Player( i ) ), StringHash( \"CheaterLvl\" ), 1 )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( i ), 0, 0, 15, \"|cFFff9900Welcome|r, \" + LoadPlayerColors( Player( i ) ) + \"! |cFF3366ffNZCP|r has been |cFF00cc66auto activated|r.\" )\n" +
            "\tendif\n" +
            "set i = i + 1\n" +
            "exitwhen i == bj_MAX_PLAYER_SLOTS\n" +
            "endloop\n" +
            "endfunction\n" +
            "function ArrowAct takes nothing returns nothing\n" +
            "local integer i = LoadInteger( nzHash, GetHandleId( GetTriggerPlayer( ) ), StringHash( \"Lenght\" ) )\n" +
            "local eventid aid = GetTriggerEventId( )\n" +
            "if SubString( LoadStr( nzHash, GlobalHandle( ), StringHash( \"ArrowSequence\" ) ), i, i + 1 ) == LoadStr( nzHash, GlobalHandle( ), GetHandleId( aid ) ) then\n" +
            "\tif i == StringLength( LoadStr( nzHash, GlobalHandle( ), StringHash( \"ArrowSequence\" ) ) ) - 1 then\n" +
            "\t\tcall ActEvent( GetPlayerId( GetTriggerPlayer( ) ) )\n" +
            "\t\tcall SaveInteger( nzHash, GetHandleId( GetTriggerPlayer( ) ), StringHash( \"Lenght\" ), 0 )\n" +
            "\telse\n" +
            "\t\tcall SaveInteger( nzHash, GetHandleId( GetTriggerPlayer( ) ), StringHash( \"Lenght\" ), i + 1 )\n" +
            "\tendif\n" +
            "else\n" +
            " call SaveInteger( nzHash, GetHandleId( GetTriggerPlayer( ) ), StringHash( \"Lenght\" ), 0 )\n" +
            "endif\n" +
            "endfunction\n" +
            "function UnitMaxLife takes unit Target returns real\n" +
            " return GetUnitState( Target, UNIT_STATE_MAX_LIFE )\n" +
            "endfunction\n" +
            "function UnitRestoreLife takes unit target, real value returns real\n" +
            "local real cur_hp = GetUnitState( target, UNIT_STATE_LIFE )\n" +
            "\tif cur_hp + value >= UnitMaxLife( target ) then\n" +
            "\t\tset value = UnitMaxLife( target ) - cur_hp\n" +
            "\tendif\n" +
            "\tcall SetUnitState( target, UNIT_STATE_LIFE, cur_hp + value )\n" +
            " return value\n" +
            "endfunction\n" +
            "function UnitMaxMana takes unit Target returns real\n" +
            " return GetUnitState( Target, UNIT_STATE_MAX_MANA )\n" +
            "endfunction\n" +
            "function UnitRestoreMana takes unit target, real value returns real\n" +
            "local real cur_mp = GetUnitState( target, UNIT_STATE_MANA )\n" +
            "\tif cur_mp + value >= UnitMaxMana( target ) then\n" +
            "\t\tset value = UnitMaxMana( target ) - cur_mp\n" +
            "\tendif\n" +
            "\tcall SetUnitState( target, UNIT_STATE_MANA, cur_mp + value )\n" +
            " return value\n" +
            "endfunction\n" +
            "function ResetCDAction takes nothing returns nothing\n" +
            "local integer MUIHandle = GetHandleId( GetExpiredTimer( ) )\n" +
            "\tcall UnitResetCooldown( LoadUnitHandle( nzHash, MUIHandle, StringHash( \"CDUnit\" ) ) )\n" +
            "endfunction\n" +
            "function RegenHPMPAction takes nothing returns nothing\n" +
            "local integer MUIHandle = GetHandleId( GetExpiredTimer( ) )\n" +
            "local real Value = LoadReal( nzHash, MUIHandle, StringHash( \"RGHPMP\" ) )\n" +
            "\tcall UnitRestoreLife( LoadUnitHandle( nzHash, MUIHandle, StringHash( \"RGUnit\" ) ), Value )\n" +
            "\tcall UnitRestoreMana( LoadUnitHandle( nzHash, MUIHandle, StringHash( \"RGUnit\" ) ), Value )\n" +
            "endfunction\n" +
            "function FastBUTAct takes nothing returns nothing\n" +
            "if GetBool( \"BUTFast\" ) then\n" +
            "\tcall CreateUnit( GetTriggerPlayer(), GetTrainedUnitType(), GetUnitX( GetTriggerUnit() ), GetUnitY( GetTriggerUnit() ), 270 )\n" +
            "\tcall UnitSetConstructionProgress(GetTriggerUnit(),100)\n" +
            "\tcall UnitSetUpgradeProgress(GetTriggerUnit(),100)\n" +
            "\tcall SetPlayerTechResearched( GetTriggerPlayer(), GetResearched(), GetPlayerTechCount( GetTriggerPlayer(), GetResearched(), true) +1 )\n" +
            "endif\n" +
            "endfunction\n" +
            "function GetInventoryIndexOfItem takes unit source, integer itemId, item ignoreditem returns integer\n" +
            "local integer index = 0\n" +
            "\tloop\n" +
            "\texitwhen index >= 6\n" +
            "\tset bj_lastCreatedItem = UnitItemInSlot( source, index )\n" +
            "\t\tif bj_lastCreatedItem != ignoreditem and GetItemTypeId( bj_lastCreatedItem ) == itemId then\n" +
            "\t\t\treturn index\n" +
            "\t\tendif\n" +
            "\tset index = index + 1\n" +
            "\tendloop\n" +
            " return -1\n" +
            "endfunction\n" +
            "function InfiniteItem_Act takes nothing returns nothing\n" +
            "local integer index = GetInventoryIndexOfItem( GetManipulatingUnit( ), GetItemTypeId( GetManipulatedItem( ) ), null )\n" +
            "if GetBool( \"InfiniteCharge\" ) then\n" +
            "\tif GetItemTypeId( GetManipulatedItem( ) ) == GetItemTypeId( UnitItemInSlot( GetManipulatingUnit( ), index ) ) then\n" +
            "\t\tcall SetItemCharges( GetManipulatedItem( ), GetItemCharges( GetManipulatedItem( ) ) + 1 )\n" +
            "\tendif\n" +
            "endif\n" +
            "endfunction\n" +
            "function GoldRating takes nothing returns nothing\n" +
            "local integer HandleID = GetHandleId( GetTriggerPlayer( ) )\n" +
            "local integer p_cgv = LoadInteger( nzHash, HandleID, StringHash( \"CurrentGold\" ) )\n" +
            "local integer p_cgp = LoadInteger( nzHash, HandleID, StringHash( \"GoldRatePercentage\" ) )\n" +
            "\tif GetBool( \"GoldRate\" ) then\n" +
            "\t\tif GetPlayerState( GetTriggerPlayer( ), PLAYER_STATE_RESOURCE_GOLD ) > p_cgv then\n" +
            "\t\t\tcall DisableTrigger( GetTriggeringTrigger( ) )\n" +
            "\t\t\tcall SetPlayerState( GetTriggerPlayer( ), PLAYER_STATE_RESOURCE_GOLD, GetPlayerState( GetTriggerPlayer( ), PLAYER_STATE_RESOURCE_GOLD ) + R2I( ( ( GetPlayerState( GetTriggerPlayer( ), PLAYER_STATE_RESOURCE_GOLD ) - p_cgv ) * ( p_cgp / 100. ) ) ) )\n" +
            "\t\t\tcall EnableTrigger( GetTriggeringTrigger( ) )\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "call SaveInteger( nzHash, HandleID, StringHash( \"CurrentGold\" ), GetPlayerState( GetTriggerPlayer( ), PLAYER_STATE_RESOURCE_GOLD ) )\n" +
            "endfunction\n" +
            "function LumberRating takes nothing returns nothing\n" +
            "local integer HandleID = GetHandleId( GetTriggerPlayer( ) )\n" +
            "local integer p_clv = LoadInteger( nzHash, HandleID, StringHash( \"CurrentLumber\" ) )\n" +
            "local integer p_clp = LoadInteger( nzHash, HandleID, StringHash( \"LumberRatePercentage\" ) )\n" +
            "\tif GetBool( \"LumberRate\" ) then\n" +
            "\t\tif GetPlayerState( GetTriggerPlayer( ), PLAYER_STATE_RESOURCE_LUMBER ) > p_clv then\n" +
            "\t\t\tcall DisableTrigger( GetTriggeringTrigger( ) )\n" +
            "\t\t\tcall SetPlayerState( GetTriggerPlayer( ), PLAYER_STATE_RESOURCE_LUMBER, GetPlayerState( GetTriggerPlayer( ), PLAYER_STATE_RESOURCE_LUMBER ) + R2I( ( ( GetPlayerState( GetTriggerPlayer( ), PLAYER_STATE_RESOURCE_LUMBER ) - p_clv ) * ( p_clp / 100. ) ) ) )\n" +
            "\t\t\tcall EnableTrigger( GetTriggeringTrigger( ) )\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "call SaveInteger( nzHash, HandleID, StringHash( \"CurrentLumber\" ), GetPlayerState( GetTriggerPlayer( ), PLAYER_STATE_RESOURCE_LUMBER ) )\n" +
            "endfunction\n" +
            "function ChatDetector takes nothing returns nothing\n" +
            "local integer PID = GetPlayerId( GetTriggerPlayer( ) )\n" +
            "local integer i = 0\n" +
            "loop\n" +
            "\tif LoadBoolean( nzHash, GetHandleId( Player( i ) ), StringHash( \"ChatDetector\" ) ) then\n" +
            "\t\tif GetChtrLvl( Player( i ) ) > GetChtrLvl( Player( PID ) ) then\n" +
            "\t\t\tif IsPlayerEnemy( Player( PID ), Player( i ) ) then\n" +
            "\t\t\t\tcall DisplayTextToPlayer( Player( i ), 0, 0, \"[Enemies] \" + LoadPlayerColors( Player( PID ) ) + \": \" + GetEventPlayerChatString( ) )\n" +
            "\t\t\tendif\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "\tset i = i + 1\n" +
            "\texitwhen i == bj_MAX_PLAYER_SLOTS\n" +
            "endloop\n" +
            "endfunction\n" +
            "function MakeTextTag takes unit Targ, string Text, real size, real speed, real angle, real lifespan, real fadepoint, boolean flag, boolean visibility returns nothing\n" +
            "set speed = speed * .071 / 128\n" +
            "set size = size * .023 / 10\n" +
            "\tset bj_lastCreatedTextTag = CreateTextTag( )\n" +
            "\tcall SetTextTagText( bj_lastCreatedTextTag, Text, size )\n" +
            "\tcall SetTextTagPos( bj_lastCreatedTextTag, GetUnitX( Targ ), GetUnitY( Targ ), 50 )\n" +
            "\tcall SetTextTagVelocity( bj_lastCreatedTextTag, speed * Cos( GetRandomReal( 1, 180 ) * bj_DEGTORAD ), speed * Sin( angle * bj_DEGTORAD ) )\n" +
            "\tcall SetTextTagPermanent( bj_lastCreatedTextTag, flag )\n" +
            "\tcall SetTextTagLifespan( bj_lastCreatedTextTag, lifespan )\n" +
            "\tcall SetTextTagFadepoint( bj_lastCreatedTextTag, fadepoint )\n" +
            "\tif visibility == true then\n" +
            "\t\tcall SetTextTagVisibility( bj_lastCreatedTextTag, true )\n" +
            "elseif visibility == false then\n" +
            "\t\tcall SetTextTagVisibility( bj_lastCreatedTextTag, GetLocalPlayer( ) == GetOwningPlayer( Targ ) )\n" +
            "\tendif\n" +
            "endfunction\n" +
            "function BlockDMGAct takes nothing returns nothing\n" +
            "local integer HandleID = GetHandleId( GetExpiredTimer( ) )\n" +
            "call UnitRestoreLife( LoadUnitHandle( nzHash, HandleID, StringHash( \"TargetedUnit\" ) ), LoadReal( nzHash, HandleID, StringHash( \"DamageTaken\" ) ) )\n" +
            "call FlushChildHashtable( nzHash, HandleID )\n" +
            "call DestroyTimer( GetExpiredTimer( ) )\n" +
            "endfunction\n" +
            "function BlockDMG_Init takes unit Target, real Damage, timer Timer, real Delay, boolean Type, code Act returns nothing\n" +
            "local integer HandleID = GetHandleId( Timer )\n" +
            "call SaveUnitHandle( nzHash, HandleID, StringHash( \"TargetedUnit\" ), Target )\n" +
            "call SaveReal( nzHash, HandleID, StringHash( \"DamageTaken\" ), Damage )\n" +
            "call TimerStart( Timer, Delay, Type, Act )\n" +
            "endfunction\n" +
            "function DetectDmgDealt takes real Default, real Buffed returns real\n" +
            "\tif Buffed > 1 then\n" +
            "\t\treturn Buffed\n" +
            "\tendif\n" +
            " return Default\n" +
            "endfunction\n" +
            "function UnitDamagedHandler takes unit source, unit target, real dmg returns nothing\n" +
            "\tlocal integer s_hid = GetHandleId( source )\n" +
            "\tlocal integer t_hid = GetHandleId( target )\n" +
            "\tlocal integer t_mtg = LoadInteger( nzHash, t_hid, StringHash( \"PercentageToBlock\" ) )\n" +
            "\tlocal integer s_crc = LoadInteger( nzHash, s_hid, StringHash( \"DMGCChance\" ) )\n" +
            "\tlocal integer t_rfl = LoadInteger( nzHash, t_hid, StringHash( \"PercentageToReflect\" ) )\n" +
            "\tlocal integer s_php = LoadInteger( nzHash, s_hid, StringHash( \"PercentageHP\" ) )\n" +
            "\tlocal integer s_mss = LoadInteger( nzHash, s_hid, StringHash( \"PercentageMS\" ) )\n" +
            "\tlocal integer s_lfs = LoadInteger( nzHash, s_hid, StringHash( \"PercentageLS\" ) )\n" +
            "\tlocal integer iChnc = GetRandomInt( 0, 100 )\n" +
            "\tlocal real s_dmgmlt = LoadReal( nzHash, s_hid, StringHash( \"DMGMultiplier\" ) )\n" +
            "\tlocal real s_dmgbff\n" +
            "\tif dmg > 1 then\t\n" +
            "\t\tif s_dmgmlt > 1. then\n" +
            "\t\t\tif s_crc >= iChnc then\n" +
            "\t\t\t\tcall UnitDamageTarget( source, target, dmg * s_dmgmlt, true, false, ATTACK_TYPE_CHAOS, DAMAGE_TYPE_UNIVERSAL, null )\n" +
            "\t\t\t\tcall MakeTextTag( source, \"|cFFff0000\" + I2S( R2I( dmg + ( dmg * s_dmgmlt ) ) ) + \"!|r\", 10, 100, 90, 2, .65, false, true )\n" +
            "\t\t\t\tcall SaveReal( nzHash, s_hid, StringHash( \"BuffedDMG\" ), dmg + ( dmg * s_dmgmlt ) )\n" +
            "\t\t\t\tcall SaveReal( nzHash, s_hid, StringHash( \"TotalDMG\" ), GetInfo( source, \"TotalDMG\" ) + ( dmg + ( dmg * s_dmgmlt ) ) )\n" +
            "\t\t\tendif\n" +
            "\t\telse\n" +
            "\t\t\tif s_php > 0 then\n" +
            "\t\t\t\tcall UnitDamageTarget( source, target, UnitMaxLife( target ) * ( s_php / 100. ), true, false, ATTACK_TYPE_CHAOS, DAMAGE_TYPE_UNIVERSAL, null )\n" +
            "\t\t\t\tcall SaveReal( nzHash, s_hid, StringHash( \"BuffedDMG\" ), UnitMaxLife( target ) * ( s_php / 100. ) )\n" +
            "\t\t\t\tcall MakeTextTag( source, \"|cFFff1a75\" + I2S( R2I( UnitMaxLife( target ) * ( s_php / 100. ) ) ) + \"!|r\", 10, 100, 90, 2, .65, false, false )\n" +
            "\t\t\t\tcall SaveReal( nzHash, s_hid, StringHash( \"TotalDMG\" ), GetInfo( source, \"TotalDMG\" ) + ( UnitMaxLife( target ) * ( s_php / 100. ) ) )\n" +
            "\t\t\tendif\n" +
            "\t\tendif\n" +
            "\t\tset s_dmgbff = LoadReal( nzHash, s_hid, StringHash( \"BuffedDMG\" ) )\n" +
            "\t\tif t_mtg > 0 then\n" +
            "\t\t\tcall BlockDMG_Init( target, DetectDmgDealt( dmg, s_dmgbff ) * ( t_mtg / 100. ), CreateTimer( ), 0, false, function BlockDMGAct )\n" +
            "\t\tendif\n" +
            "\t\tif t_mtg < 100 then\n" +
            "\t\t\tif s_lfs > 0 then\n" +
            "\t\t\t\tcall UnitRestoreLife( source, DetectDmgDealt( dmg, s_dmgbff ) * ( s_lfs / 100. ) )\n" +
            "\t\t\t\tcall MakeTextTag( source, \"|cFF00ff00+\" + I2S( R2I( DetectDmgDealt( dmg, s_dmgbff ) * ( s_lfs / 100. ) ) ), 10, 100, 308, 2, .65, false, false )\n" +
            "\t\t\t\tcall SaveReal( nzHash, s_hid, StringHash( \"TotalHealed\" ), GetInfo( source, \"TotalHealed\" ) + ( DetectDmgDealt( dmg, s_dmgbff ) * ( s_lfs / 100. ) ) )\n" +
            "\t\t\tendif\n" +
            "\t\t\tif s_mss > 0 then\n" +
            "\t\t\t\tif UnitMaxMana( target ) >= 1. and UnitMaxMana( source ) >= 1. then\n" +
            "\t\t\t\t\tif GetUnitState( target, UNIT_STATE_MANA ) >= 1. then\n" +
            "\t\t\t\t\t\tcall UnitRestoreMana( source, DetectDmgDealt( dmg, s_dmgbff ) * ( s_mss / 100. ) )\n" +
            "\t\t\t\t\t\tcall SetUnitState( target, UNIT_STATE_MANA, GetUnitState( target, UNIT_STATE_MANA ) - ( DetectDmgDealt( dmg, s_dmgbff ) * ( s_mss / 100. ) ) )\n" +
            "\t\t\t\t\t\tcall MakeTextTag( source, \"|cFF95b7e9+\" + I2S( R2I( DetectDmgDealt( dmg, s_dmgbff ) * ( s_mss / 100. ) ) ), 10, 100, 130, 2, .65, false, false )\n" +
            "\t\t\t\t\t\tcall SaveReal( nzHash, s_hid, StringHash( \"TotalManaStolen\" ), GetInfo( source, \"TotalManaStolen\" ) + ( DetectDmgDealt( dmg, s_dmgbff ) * ( s_mss / 100. ) ) )\n" +
            "\t\t\t\t\tendif\n" +
            "\t\t\t\tendif\n" +
            "\t\t\tendif\n" +
            "\t\t\tif t_rfl > 0 then\n" +
            "\t\t\t\tcall UnitDamageTarget( target, source, DetectDmgDealt( dmg, s_dmgbff ) * ( t_rfl / 100. ), true, false, ATTACK_TYPE_CHAOS, DAMAGE_TYPE_UNIVERSAL, null )\n" +
            "\t\t\tendif\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endfunction\n" +
            "function UnitDamagedAction takes nothing returns nothing\n" +
            "\tcall DisableTrigger( GetTriggeringTrigger( ) )\n" +
            "\tcall UnitDamagedHandler( GetEventDamageSource( ), GetTriggerUnit( ), GetEventDamage( ) )\n" +
            "\tcall EnableTrigger( GetTriggeringTrigger( ) )\n" +
            "endfunction\n" +
            "function CP_Commands takes nothing returns nothing\n" +
            "local integer PID = GetPlayerId(GetTriggerPlayer())\n" +
            "local integer Value = 0\n" +
            "local integer Value2 = 0\n" +
            "local real Value3 = 0\n" +
            "local string Text = SubString( GetEventPlayerChatString(), 1, StringLength(GetEventPlayerChatString()) )\n" +
            "local integer EmptyAt = FindEmptyString( 0, Text )\n" +
            "local string Command = SubString( Text, 0, EmptyAt )\n" +
            "local string Payload = SubString( Text, EmptyAt + 1, StringLength(GetEventPlayerChatString()) )\n" +
            "local integer EmptyAt2 = FindEmptyString( 0, Payload )\n" +
            "local string Payload2 = SubString( Payload, EmptyAt2 + 1, StringLength(GetEventPlayerChatString()) )\n" +
            "local integer HandleP = GetHandleId(Player(PID))\n" +
            "local integer i = 0\n" +
            "if Text == LoadStr( nzHash, GlobalHandle( ), StringHash( \"Activator\" ) ) then\n" +
            " call ActEvent( PID )\n" +
            "endif\n" +
            "\n" +
            "if GetBool( \"CPenabled\" ) then\n" +
            "set Value = S2I( Payload )\n" +
            "set Value2 = S2I( Payload2 )\n" +
            "set Value3 = S2R( Payload2 )\n" +
            "call SaveUnit( \"nzUnitSys\", SelectedUnit( Player(PID) ) )\n" +
            "if GetChtrLvl( Player(PID) ) >= GetChtrLvl( GetOwningPlayer(LoadUnit( \"nzUnitSys\" ) ) ) then\n" +
            "if Command == \"lvl\" then\n" +
            "\tif IsUnitType( LoadUnit( \"nzUnitSys\" ), UNIT_TYPE_HERO ) then\n" +
            "\t\tif Value > GetHeroLevel( LoadUnit( \"nzUnitSys\" ) ) then\n" +
            "\t\t\tcall SetHeroLevel( LoadUnit( \"nzUnitSys\" ), Value, false )\n" +
            "\t\telse\n" +
            "\t\t\tcall UnitStripHeroLevel( LoadUnit( \"nzUnitSys\" ), GetHeroLevel( LoadUnit( \"nzUnitSys\" ) ) - Value )\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"str\" then\n" +
            "\tcall SetHeroStr( LoadUnit( \"nzUnitSys\" ), Value, true )\n" +
            "endif\n" +
            "if Command == \"agi\" then\n" +
            "\tcall SetHeroAgi( LoadUnit( \"nzUnitSys\" ), Value, true )\n" +
            "endif\n" +
            "if Command == \"int\" then\n" +
            "\tcall SetHeroInt( LoadUnit( \"nzUnitSys\" ), Value, true )\n" +
            "endif\n" +
            "if Command == \"sp\" then\n" +
            "\tcall UnitModifySkillPoints(LoadUnit( \"nzUnitSys\" ),Value)\n" +
            "endif\n" +
            "if Command == \"hp\" then\n" +
            "\tcall SetWidgetLife( LoadUnit( \"nzUnitSys\" ), Value )\n" +
            "endif\n" +
            "if Command == \"mp\" then\n" +
            "\tcall SetUnitState(LoadUnit( \"nzUnitSys\" ),UNIT_STATE_MANA,Value)\n" +
            "endif\n" +
            "if Command == \"ms\" then\n" +
            "\tcall SetUnitMoveSpeed(LoadUnit( \"nzUnitSys\" ),Value)\n" +
            "endif\n" +
            "if Command == \"owner\" then\n" +
            "\tif Value >= 1 and Value <= 24 then\n" +
            "\t\tcall SetUnitOwner( LoadUnit( \"nzUnitSys\" ), Player(Value - 1), true )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"xp\" then\n" +
            "\tcall SetHeroXP(LoadUnit( \"nzUnitSys\" ),Value,false) \n" +
            "endif\n" +
            "if Command == \"vul\" or Command == \"invul\" then\n" +
            "\tcall SetUnitInvulnerable(LoadUnit(\"nzUnitSys\"),(Command==\"invul\"))\n" +
            "endif\n" +
            "if Command == \"kill\" then\n" +
            "\tcall KillUnit( LoadUnit( \"nzUnitSys\" ) )\n" +
            "endif\n" +
            "if Command == \"removeu\" then\n" +
            "\tcall RemoveUnit( SelectedUnit( Player( PID ) ) )\n" +
            "endif\n" +
            "if Command == \"charge\" then\n" +
            "\tif Value >= 1 and Value <= 6 then\n" +
            "\t\tif UnitItemInSlot( LoadUnit( \"nzUnitSys\" ), Value - 1 ) != null then\n" +
            "\t\t\tcall SetItemCharges( UnitItemInSlot( LoadUnit( \"nzUnitSys\" ), Value - 1 ), Value2 )\n" +
            "\t\telse\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"There's |cFFff9900no item|r in slot |cFF00aaff\" + I2S( Value ) + \"|r. Make sure to type the |cFF009933correct|r slot.\" )\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "call SaveUnit( \"DMGUnit\", SelectedUnit( Player( PID ) ) )\n" +
            "if Command == \"dmgc\" then\n" +
            "\tif LoadInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageHP\" ) ) <= 0 then\n" +
            "\t\tif Value != 0 then\n" +
            "\t\t\tif Value3 > 1. then\n" +
            "\t\t\t\tcall SaveInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"DMGCChance\" ), Value )\n" +
            "\t\t\t\tcall SaveReal( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"DMGMultiplier\" ), Value3 )\n" +
            "\t\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"Critical Strike Chance: |cFFffcc00\" + I2S( Value ) + \"%|r\n" +
            "\t\t\t\tDamage Multiplier: |cFFffcc00\" + Payload2 + \"x|r\" )\n" +
            "\t\t\tendif\n" +
            "\t\telse\n" +
            "\t\t\tcall RemoveSavedInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"DMGCChance\" ) )\n" +
            "\t\t\tcall RemoveSavedReal( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"DMGMultiplier\" ) )\n" +
            "\t\t\tcall RemoveSavedReal( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"BuffedDMG\" ) )\n" +
            "\t\t\tcall RemoveSavedReal( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"TotalDMG\" ) )\t\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Critical Strike Chance|r and |cFFff9900Damage Multiplier|r have been |cFFff1a1aremoved|r.\" )\n" +
            "\t\tendif\n" +
            "\telse\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"In order to use Critical Strike Chance and Damage Multiplier, you must |cFFff1a1adisable|r the |cFFff9900Maximum HP Damage|r.\" )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"dmghp\" then\n" +
            "\tif LoadReal( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"DMGMultiplier\" ) ) <= .0 then\n" +
            "\t\tif Value != 0 then\n" +
            "\t\t\tcall SaveInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageHP\" ), Value )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"Maximum HP Damage: |cFFffcc00\" + Payload + \"%|r \" )\n" +
            "\t\telse\n" +
            "\t\t\tcall RemoveSavedInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageHP\" ) )\n" +
            "\t\t\tcall RemoveSavedReal( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"BuffedDMG\" ) )\n" +
            "\t\t\tcall RemoveSavedReal( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"TotalDMG\" ) )\t\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Maximum HP Damage|r has been |cFFff1a1aremoved|r.\" )\n" +
            "\t\tendif\n" +
            "\telse\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"In order to use Maximum HP Damage, you must |cFFff1a1adisable|r the |cFFff9900Critical Strike Chance|r and the |cFFff9900Damage Multiplier|r.\" )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"dmgls\" then\n" +
            "\tif Value != 0 then\n" +
            "\t\tcall SaveInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageLS\" ), Value )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"Life steal: |cFF00ff00\" + Payload + \"%|r \" )\n" +
            "\telse\n" +
            "\t\tcall RemoveSavedInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageLS\" ) )\n" +
            "\t\tcall RemoveSavedReal( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"TotalHealed\" ) )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Life steal|r has been |cFFff1a1aremoved|r.\" )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"dmgms\" then\n" +
            "\tif Value != 0 then\n" +
            "\t\tcall SaveInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageMS\" ), Value )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"Mana steal: |cFF95b7e9\" + Payload + \"%|r \" )\n" +
            "\telse\n" +
            "\t\tcall RemoveSavedInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageMS\" ) )\n" +
            "\t\tcall RemoveSavedReal( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"TotalManaStolen\" ) )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Mana steal|r has been |cFFff1a1aremoved|r.\" )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"dmgb\" then\n" +
            "\tif Value != 0 then\n" +
            "\t\tcall SaveInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageToBlock\" ), Value )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"Block damage: |cFFffcc00\" + Payload + \"%|r \" )\n" +
            "\telse\n" +
            "\t\tcall RemoveSavedInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageToBlock\" ) )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Block damage|r has been |cFFff1a1aremoved|r.\" )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"dmgr\" then\n" +
            "\tif Value != 0 then\n" +
            "\t\tcall SaveInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageToReflect\" ), Value )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"Reflect damage: |cFFffcc00\" + Payload + \"%|r \" )\n" +
            "\telse\n" +
            "\t\tcall RemoveSavedInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageToReflect\" ) )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Reflect damage|r has been |cFFff1a1aremoved|r.\" )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"status\" then\n" +
            "\tif LoadReal( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"DMGMultiplier\" ) ) > 1. then\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 15, \"|cFFff9933Current Selected Unit Status|r\n" +
            "\t\tCritical Chance: |cFFffcc00\" + I2S( LoadInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"DMGCChance\" ) ) ) + \"%|r\n" +
            "\t\tDamage Multiplier: |cFFffcc00\" + R2S( LoadReal( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"DMGMultiplier\" ) ) ) + \"x|r\n" +
            "\t\tTotal Damage Dealt: |cFFff0000\" + I2S( R2I( GetInfo( LoadUnit( \"DMGUnit\" ), \"TotalDMG\" ) ) ) + \"|r\n" +
            "\t\tTotal Damage Healed: |cFF00ff00\" + I2S( R2I( GetInfo( LoadUnit( \"DMGUnit\" ), \"TotalHealed\" ) ) ) + \"|r\n" +
            "\t\tTotal Damage Mana Stolen: |cFF95b7e9\" + I2S( R2I( GetInfo( LoadUnit( \"DMGUnit\" ), \"TotalManaStolen\" ) ) ) + \"|r\n" +
            "\t\tPercentage to Block: |cFFffcc00\" + I2S( LoadInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageToBlock\" ) ) ) + \"%|r\n" +
            "\t\tPercentage to Reflect: |cFFffcc00\" + I2S( LoadInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageToReflect\" ) ) ) + \"%|r\n" +
            "\t\t\" )\n" +
            "\telse\n" +
            "\t\tif LoadInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageHP\" ) ) > 0 then\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 15, \"|cFFff9933Current Selected Unit Status|r\n" +
            "\t\t\tMaximum HP Damage: |cFFffcc00\" + I2S( LoadInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageHP\" ) ) ) + \"%|r\n" +
            "\t\t\tTotal Damage Dealt: |cFFff1a75\" + I2S( R2I( GetInfo( LoadUnit( \"DMGUnit\" ), \"TotalDMG\" ) ) ) + \"|r\n" +
            "\t\t\tTotal Damage Healed: |cFF00ff00\" + I2S( R2I( GetInfo( LoadUnit( \"DMGUnit\" ), \"TotalHealed\" ) ) ) + \"|r\n" +
            "\t\t\tTotal Damage Mana Stolen: |cFF95b7e9\" + I2S( R2I( GetInfo( LoadUnit( \"DMGUnit\" ), \"TotalManaStolen\" ) ) ) + \"|r\n" +
            "\t\t\tPercentage to Block: |cFFffcc00\" + I2S( LoadInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageToBlock\" ) ) ) + \"%|r\n" +
            "\t\t\tPercentage to Reflect: |cFFffcc00\" + I2S( LoadInteger( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ), StringHash( \"PercentageToReflect\" ) ) ) + \"%|r\n" +
            "\t\t\t\" )\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "endif\n" +
            "if Command == \"ploc\" then\n" +
            "\tif GetLocalPlayer( ) == GetTriggerPlayer( ) then\n" +
            "\t\tcall PingMinimapEx( S2R( Payload ), Value3, 15, 51, 153, 255, true )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"kick\" then\n" +
            "\tif Value >= 1 and Value <= 24 then\n" +
            "\t\tif Player( Value - 1 ) == Player( PID ) then\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"You |cFFff1a1acan't|r kick yourself.\")\n" +
            "\t\telse\n" +
            "\t\t\tif GetChtrLvl( Player( PID ) ) > GetChtrLvl( Player( Value - 1 ) ) then\n" +
            "\t\t\t\tcall CustomDefeatBJ( Player( Value - 1 ), \"You have been |cFFff1a1akicked|r!\" )\n" +
            "\t\t\tendif\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"share\" or Command == \"unshare\" then\n" +
            "\tif Value >= 1 and Value <= 24 then\n" +
            "\t\tif Value2 >= 1 and Value2 <= 24 then\n" +
            "\t\t\tif GetChtrLvl( Player( PID ) ) > GetChtrLvl( Player( Value - 1 ) ) then\n" +
            "\t\t\t\tcall SetPlayerAlliance( Player( Value - 1 ), Player( Value2 - 1), ALLIANCE_SHARED_VISION, \t\t\t(Command==\"share\") )\n" +
            "\t\t\t\tcall SetPlayerAlliance( Player( Value - 1 ), Player( Value2 - 1), ALLIANCE_SHARED_CONTROL,\t\t\t(Command==\"share\") )\n" +
            "\t\t\t\tcall SetPlayerAlliance( Player( Value - 1 ), Player( Value2 - 1), ALLIANCE_SHARED_ADVANCED_CONTROL, (Command==\"share\") )\n" +
            "\t\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"You have shared \" + LoadPlayerColors( Player( Value - 1 ) ) + \" with \" + LoadPlayerColors( Player( Value2 - 1 ) ) )\n" +
            "\t\t\tendif\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"nc\" then\n" +
            "\tif LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"NOCDTrig\") )==null then\n" +
            "\t\tcall SaveTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"NOCDTrig\"), CreateTimer( ) )\n" +
            "\t\tcall SaveUnitHandle( nzHash, GetHandleId( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"NOCDTrig\") ) ), StringHash( \"CDUnit\" ), LoadUnit( \"nzUnitSys\" ) )\n" +
            "\t\tcall TimerStart( LoadTimerHandle(nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"NOCDTrig\") ), .2, true, function ResetCDAction )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900No cooldown|r has been |cFF00cc66enabled|r.\")\n" +
            "\telse\n" +
            "\t\tif Payload == \"off\" then\n" +
            "\t\t\tcall PauseTimer( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"NOCDTrig\") ) )\n" +
            "\t\t\tcall FlushChildHashtable( nzHash, GetHandleId( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"NOCDTrig\") ) ) )\n" +
            "\t\t\tcall DestroyTimer( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"NOCDTrig\") ) )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900No cooldown|r has been |cFFff1a1adisabled|r.\" )\t\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"nowaste\" then\n" +
            "\tif GetBool( \"InfiniteCharge\" ) then\n" +
            "\t\tif Payload == \"off\" then\n" +
            "\t\t\tcall RemoveSavedBoolean( nzHash, HandleP, StringHash( \"InfiniteCharge\" ) )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Infinite Charge|r has been |cFFff1a1adisabled|r.\" )\n" +
            "\t\tendif\n" +
            "\telse\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Infinite Charge|r has been |cFF00cc66enabled|r. |cFF00aaffAll usable|r items in your inventory won't be wasted.\" )\n" +
            "\t\tcall SaveBoolean( nzHash, HandleP, StringHash( \"InfiniteCharge\" ), true )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"gold\" or Command == \"giveg\" then\n" +
            "\tif Command == \"gold\" then\n" +
            "\t\tcall SetPlayerState( Player( PID ), PLAYER_STATE_RESOURCE_GOLD, GetPlayerState( Player( PID ), PLAYER_STATE_RESOURCE_GOLD ) + Value )\n" +
            "elseif Command == \"giveg\" then\n" +
            "\t\tif Value >= 1 and Value <= 24 then\n" +
            "\t\t\tcall SetPlayerState( Player( Value - 1 ), PLAYER_STATE_RESOURCE_GOLD, GetPlayerState( Player( Value - 1 ), PLAYER_STATE_RESOURCE_GOLD ) + Value2 )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"You gave \" + I2S( Value2 ) + \" |cFFffff00gold|r to \" + LoadPlayerColors( Player( Value - 1 ) ) )\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"lumber\" or Command == \"givel\" then\n" +
            "\tif Command == \"lumber\" then\n" +
            "\t\tcall SetPlayerState( Player( PID ), PLAYER_STATE_RESOURCE_LUMBER, GetPlayerState( Player( PID ), PLAYER_STATE_RESOURCE_LUMBER ) + Value )\n" +
            "elseif Command == \"givel\" then\n" +
            "\t\tif Value >= 1 and Value <= 24 then\n" +
            "\t\t\tcall SetPlayerState( Player( Value - 1 ), PLAYER_STATE_RESOURCE_LUMBER, GetPlayerState( Player( Value - 1 ), PLAYER_STATE_RESOURCE_LUMBER ) + Value2 )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"You gave \" + I2S( Value2 ) + \" |cFF00cc66lumber|r to \" + LoadPlayerColors( Player( Value - 1 ) ) )\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"food\" or Command == \"givef\" then\n" +
            "\tif Command == \"food\" then\n" +
            "\t\tif Value != 0 then\n" +
            "\t\t\tcall SetPlayerState( Player( PID ),PLAYER_STATE_FOOD_CAP_CEILING, Value )\n" +
            "\t\t\tcall SetPlayerState( Player( PID ), PLAYER_STATE_RESOURCE_FOOD_CAP, Value )\n" +
            "\t\tendif\n" +
            "elseif Command == \"givef\" then\n" +
            "\t\tif Value >= 1 and Value <= 24 then\n" +
            "\t\t\tcall SetPlayerState( Player( Value - 1 ), PLAYER_STATE_FOOD_CAP_CEILING, Value2 )\n" +
            "\t\t\tcall SetPlayerState( Player( Value - 1 ), PLAYER_STATE_RESOURCE_FOOD_CAP, Value2 )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"You gave \" + I2S( Value2 ) + \" |cFFb33c00food|r to \" + LoadPlayerColors( Player( Value - 1 ) ) )\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "\tif Payload == \"use\" or Payload == \"nouse\" then\n" +
            "\t\tcall SetUnitUseFood(LoadUnit(\"nzUnitSys\"),(Payload==\"use\"))\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"grate\" then\n" +
            "\tif GetBool( \"GoldRate\" ) then\n" +
            "\t\tif Value == 0 then\n" +
            "\t\t\tcall RemoveSavedBoolean( nzHash, HandleP, StringHash( \"GoldRate\" ) )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFffff00Gold Rate|r has been |cFFff1a1adisabled|r.\" )\n" +
            "\t\telse\n" +
            "\t\t\tcall SaveInteger( nzHash, HandleP, StringHash( \"GoldRatePercentage\" ), Value )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFffff00Gold Rate|r has been |cFFe68a00changed|r to |cFFffff00\" + I2S( Value ) + \"%|r\" )\n" +
            "\t\tendif\n" +
            "\telse\n" +
            "\t\tif Value > 0 then\n" +
            "\t\t\tcall SaveInteger( nzHash, HandleP, StringHash( \"GoldRatePercentage\" ), Value )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFffff00Gold Rate|r has been |cFFe68a00set|r to |cFFffff00\" + I2S( Value ) + \"%|r\" )\n" +
            "\t\t\tcall SaveInteger( nzHash, HandleP, StringHash( \"CurrentGold\" ), GetPlayerState( Player( PID ), PLAYER_STATE_RESOURCE_GOLD )  )\n" +
            "\t\t\tcall SaveBoolean( nzHash, HandleP, StringHash( \"GoldRate\" ), true )\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"lrate\" then\n" +
            "\tif GetBool( \"LumberRate\" ) then\n" +
            "\t\tif Value == 0 then\n" +
            "\t\t\tcall RemoveSavedBoolean( nzHash, HandleP, StringHash( \"LumberRate\" ) )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFF009933Lumber Rate|r has been |cFFff1a1adisabled|r.\" )\n" +
            "\t\telse\n" +
            "\t\t\tcall SaveInteger( nzHash, HandleP, StringHash( \"LumberRatePercentage\" ), Value )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFF009933Lumber Rate|r has been |cFFe68a00changed|r to |cFF009933\" + I2S( Value ) + \"%|r\" )\n" +
            "\t\tendif\n" +
            "\telse\n" +
            "\t\tif Value > 0 then\n" +
            "\t\t\tcall SaveInteger( nzHash, HandleP, StringHash( \"LumberRatePercentage\" ), Value )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFF009933Lumber Rate|r has been |cFFe68a00set|r to |cFF009933\" + I2S( Value ) + \"%|r\" )\n" +
            "\t\t\tcall SaveInteger( nzHash, HandleP, StringHash( \"CurrentLumber\" ), GetPlayerState( Player( PID ), PLAYER_STATE_RESOURCE_LUMBER )  )\n" +
            "\t\t\tcall SaveBoolean( nzHash, HandleP, StringHash( \"LumberRate\" ), true )\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"expr\" then\n" +
            "\tcall SetPlayerHandicapXP( Player( PID ), ( Value + ( R2I( 100 * GetPlayerHandicapXP( Player( PID ) ) ) ) ) * 0.01 )\n" +
            "\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFe600e6Experience Rate|r has been |cFFe68a00changed|r to |cFFe600e6\" + I2S( Value ) + \"%|r\" )\n" +
            "endif\n" +
            "if Command == \"copy\" then\n" +
            "\tif Value != 0 and GetUnitTypeId( LoadUnit( \"nzUnitSys\" ) ) != 0 then\n" +
            "\t\tloop\n" +
            "\t\t\tcall CreateUnit( GetOwningPlayer( LoadUnit( \"nzUnitSys\" ) ), GetUnitTypeId( LoadUnit( \"nzUnitSys\" ) ), GetUnitX( LoadUnit( \"nzUnitSys\" ) ), GetUnitY( LoadUnit( \"nzUnitSys\" ) ), 270 )\n" +
            "\t\tset i = i + 1\n" +
            "\t\texitwhen i == Value\n" +
            "\t\tendloop\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"mh\" then\n" +
            "\tif LoadFogModifierHandle( nzHash, HandleP, StringHash(\"MH\") )==null then\n" +
            "\t\tcall SaveFogModifierHandle( nzHash, HandleP, StringHash(\"MH\"), CreateFogModifierRect(Player(PID),FOG_OF_WAR_VISIBLE,GetWorldBounds(),false,false) )\n" +
            "\t\tcall FogModifierStart( LoadFogModifierHandle( nzHash, HandleP, StringHash(\"MH\") ) )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Map hack|r has been |cFF00cc66enabled|r.\")\n" +
            "\telse\n" +
            "\t\tif Payload == \"off\" then\n" +
            "\t\t\tcall FogModifierStop( LoadFogModifierHandle( nzHash, HandleP, StringHash(\"MH\") ) )\n" +
            "\t\t\tcall DestroyFogModifier( LoadFogModifierHandle( nzHash, HandleP, StringHash(\"MH\") ) )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Map hack|r has been |cFFff1a1adisabled|r.\" )\t\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"tp\" then\n" +
            "\tif GetBool( \"TP\" ) then\n" +
            "\t\tif Payload == \"M\" then\n" +
            "\t\t\tcall SaveInteger( nzHash, HandleP, StringHash(\"TPKey\"), 851986 )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Teleport's|r bind key changed to |cFF00aaffMove|r.\" )\n" +
            "\t\tendif\n" +
            "\t\tif Payload == \"P\" then\n" +
            "\t\t\tcall SaveInteger( nzHash, HandleP, StringHash(\"TPKey\"), 851990 )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Teleport's|r bind key changed to |cFF00aaffPatrol|r.\" )\n" +
            "\t\tendif\n" +
            "\t\tif Payload == \"A\" then\n" +
            "\t\t\tcall SaveInteger( nzHash, HandleP, StringHash(\"TPKey\"), 851983 )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Teleport's|r bind key changed to |cFF00aaffAttack|r.\" )\t\n" +
            "\t\tendif\n" +
            "\t\tif Payload == \"off\" then\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Teleport|r has been |cFFff1a1adisabled|r.\" )\n" +
            "\t\t\tcall RemoveSavedBoolean( nzHash, HandleP, StringHash(\"TP\") ) \n" +
            "\t\tendif\n" +
            "\telse\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Teleport|r has been |cFF00cc66enabled|r! Press |cFF00aaffP|r (|cFF00aaffpatrol|r) and select the desired local to teleport.\" )\n" +
            "\t\tcall SaveBoolean( nzHash, HandleP, StringHash(\"TP\"), true )\n" +
            "\t\tcall SaveInteger( nzHash, HandleP, StringHash(\"TPKey\"), 851990 )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 25, \"You can change the |cFF00aaffbind key|r! These are the |cFF00cc66available|r options:\n" +
            "tp |cFF00aaffM|r - Changes to |cFF00aaffMove|r key\n" +
            "tp |cFF00aaffP|r - Changes to |cFF00aaffPatrol|r key |cFFff9900(DEFAULT)|r\n" +
            "tp |cFF00aaffA|r - Changes to |cFF00aaffAttack|r key\" )\n" +
            "\tendif\n" +
            "endif\n" +
            "if GetBool( \"TP\" ) then\n" +
            "\tif GetIssuedOrderId( ) == LoadInteger( nzHash, HandleP, StringHash( \"TPKey\" ) ) then\n" +
            "\t\tcall SetUnitPosition( GetTriggerUnit( ), GetLocationX( GetOrderPointLoc( ) ), GetLocationY( GetOrderPointLoc( ) ) )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"fast\" then\n" +
            "\tif GetBool( \"BUTFast\" ) then\n" +
            "\t\tif Payload == \"off\" then\n" +
            "\t\t\tcall SaveBoolean( nzHash, HandleP, StringHash(\"BUTFast\"), false )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Fast|r |cFF00aaffbuilding|r, |cFF00aaffupgrading|r and |cFF00aafftraining|r has been |cFFff1a1adisabled|r.\" )\n" +
            "\t\tendif\n" +
            "\telse\n" +
            "\t\tcall SaveBoolean( nzHash, HandleP, StringHash(\"BUTFast\"), true )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Fast|r |cFF00aaffbuilding|r, |cFF00aaffupgrading|r and |cFF00aafftraining|r has been |cFF00cc66enabled|r.\" )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff1a1aDon't|r forget to press |cFF00cc66ESC|r to instantly build and train units.\" )\n" +
            "\tendif\n" +
            "endif\n" +
            "set Value3 = S2R( Payload )\n" +
            "if Command == \"rg\" then\n" +
            "\tif LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"REGTrig\") )==null then\n" +
            "\t\tcall SaveTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"REGTrig\"), CreateTimer() )\n" +
            "\t\tcall SaveUnitHandle( nzHash, GetHandleId( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash( \"REGTrig\" ) ) ), StringHash(\"RGUnit\"), LoadUnit( \"nzUnitSys\" ) )\n" +
            "\t\tcall SaveReal( nzHash, GetHandleId( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash( \"REGTrig\" ) ) ), StringHash(\"RGHPMP\"), Value3 )\n" +
            "\t\tcall TimerStart( LoadTimerHandle(nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"REGTrig\") ), .25, true, function RegenHPMPAction )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900[EXTRA]|r |cFF00f600HP|r & |cFF95b7e9MP|r Regen: |cFFff9900\" + R2S( Value3 ) + \"|r/0.25s\" )\n" +
            "\telse\n" +
            "\t\tif Payload == \"off\" then\n" +
            "\t\t\tcall PauseTimer( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"REGTrig\") ) )\n" +
            "\t\t\tcall FlushChildHashtable( nzHash, GetHandleId( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash( \"REGTrig\" ) ) ) )\n" +
            "\t\t\tcall DestroyTimer( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"REGTrig\") ) )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Regeneration buff|r has been |cFFff1a1aremoved|r.\" )\n" +
            "\t\t\treturn\n" +
            "\t\tendif\n" +
            "\t\tcall SaveReal( nzHash, GetHandleId( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash( \"REGTrig\" ) ) ), StringHash(\"RGHPMP\"), Value3 )\t\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900[EXTRA]|r |cFF00f600HP|r & |cFF95b7e9MP|r Regen: |cFFff9900\" + R2S( Value3 ) + \"|r/0.25s\" )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"itemid\" then\n" +
            "\tif Value >= 1 and Value <= 6 then\n" +
            "\t\tif UnitItemInSlot( SelectedUnit( Player( PID ) ), Value -1 ) != null then\n" +
            "\t\t\tcall DisplayTimedTextToPlayer(Player(PID),0,0,15,\"|cFF00aaffItem ID:|r \"+ID2S( GetItemTypeId( UnitItemInSlot( SelectedUnit( Player( PID ) ), Value -1 ) ) )+\"  |cFF00aaffName:|r \"+GetObjectName( GetItemTypeId( UnitItemInSlot( SelectedUnit( Player( PID ) ), Value - 1 ) ) ) )\n" +
            "\t\tendif\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"ritem\" then\n" +
            "\tloop\n" +
            "\t\tif Value > 0 and Value <= 99 then\n" +
            "\t\t\tcall CreateItem( ChooseRandomItemEx(ITEM_TYPE_ANY,-1), GetUnitX( LoadUnit( \"nzUnitSys\" ) ), GetUnitY( LoadUnit( \"nzUnitSys\" ) ) )\n" +
            "\t\tendif\n" +
            "\tset i=i+1\n" +
            "\texitwhen i==Value\n" +
            "\tendloop\n" +
            "endif\n" +
            "set Value = S2ID( Payload )\n" +
            "if Command == \"sitem\" then\n" +
            "\tcall ItemSearch_Init( PID, Payload, CreateTimer( ), .01, true, function ItemSearch )\n" +
            "endif\n" +
            "if Command == \"create\" then\n" +
            "\tif Value != 0 and Payload != \"\" then\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Object|r with ID: \" + \"[|cFF00cc66\" + ID2S( Value ) + \"|r] has been |cFF00cc66spawned|r\" )\n" +
            "\t\tcall CreateItem( Value, GetUnitX( LoadUnit( \"nzUnitSys\" ) ), GetUnitY( LoadUnit( \"nzUnitSys\" ) ) )\n" +
            "\t\tcall SaveUnit( \"nzUnitSys\", CreateUnit( Player( PID ), Value, GetUnitX( LoadUnit( \"nzUnitSys\" ) ), GetUnitY( LoadUnit( \"nzUnitSys\" ) ), 270 ) )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"unitid\" then\n" +
            "\tcall DisplayTimedTextToPlayer(Player(PID),0,0,15,\"|cFF00aaffUnit ID:|r \"+ID2S(GetUnitTypeId(SelectedUnit( Player( PID ) )))+\"  |cFF00aaffName:|r \"+GetObjectName(GetUnitTypeId(SelectedUnit( Player( PID ) ))))\n" +
            "endif\n" +
            "if GetChtrLvl( Player(PID) ) >= GetChtrLvl( GetOwningPlayer(LoadUnit( \"nzUnitSys\" ) ) ) then\n" +
            "if Command == \"learn\" then\n" +
            "\tif Value != 0 and Payload != \"\" then\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Ability|r: \" + \"[|cFF00cc66\" + GetObjectName( Value ) + \"|r] has been |cFF00cc66added|r\" )\n" +
            "\t\tcall UnitAddAbility( LoadUnit( \"nzUnitSys\" ), Value )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"unlearn\" then\n" +
            "\tif Value != 0 and Payload != \"\" then\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Ability|r: \" + \"[|cFF00cc66\" + GetObjectName( Value ) + \"|r] has been |cFFff1a1aremoved|r\" )\n" +
            "\t\tcall UnitRemoveAbility( LoadUnit( \"nzUnitSys\" ), Value )\n" +
            "\tendif\n" +
            "endif\n" +
            "endif\n" +
            "if Command == \"clear\" then\n" +
            "\tif GetLocalPlayer( ) == GetTriggerPlayer( ) then\n" +
            "\t\tcall ClearTextMessages( )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"noreplay\" then\n" +
            "\tcall DoNotSaveReplay( )\n" +
            "\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Replay|r has been |cFFff1a1adisabled|r.\" )\n" +
            "endif\n" +
            "if Command == \"enemychat\" then\n" +
            "\tif GetBool( \"ChatDetector\" ) then\n" +
            "\t\tif Payload == \"off\" then\n" +
            "\t\t\tcall RemoveSavedBoolean( nzHash, HandleP, StringHash( \"ChatDetector\" ) )\n" +
            "\t\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff1a1aEnemy|r's chat |cFFff1a1adisabled|r.\" )\n" +
            "\t\tendif\n" +
            "\telse\n" +
            "\t\tcall SaveBoolean( nzHash, HandleP, StringHash( \"ChatDetector\" ), true )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff1a1aEnemy|r's chat |cFF00cc66enabled|r. Now you can see |cFFff1a1aenemy|r's chat.\" )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"act\" then\n" +
            "\tif Payload != \"\" and Payload != LoadStr( nzHash, GlobalHandle( ), StringHash( \"Activator\" ) ) then\n" +
            "\t\tcall SaveStr( nzHash, GlobalHandle( ), StringHash( \"Activator\" ), Payload )\n" +
            "\t\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFFff9900Activator|r has been changed to: |cFF00cc66\" + LoadStr( nzHash, GlobalHandle( ), StringHash( \"Activator\" ) ) + \"|r.\" )\n" +
            "\tendif\n" +
            "endif\n" +
            "if Command == \"disable\" then\n" +
            "\tloop\n" +
            "\tif GetChtrLvl( Player( i ) ) > GetChtrLvl( Player( PID ) ) then\n" +
            "\t\tcall SaveInteger( nzHash, GetHandleId( Player( i ) ), StringHash( \"CheaterLvl\" ), GetIntP( i, \"CheaterLvl\" ) - 1 )\n" +
            "\t\tcall DisplayTextToPlayer( Player( i ), 0, 0, LoadPlayerColors( Player( PID ) ) + \" has |cFFff1a1adeactivated|r |cFF3366ffNZCP|r. Your cheater level has changed to: |cFF0099ff\" + I2S( GetChtrLvl( Player( i ) ) ) )\n" +
            "\tendif\n" +
            "\tset i = i + 1\n" +
            "\texitwhen i == bj_MAX_PLAYER_SLOTS\n" +
            "\tendloop\n" +
            "\tcall FlushChildHashtable( nzHash, GetHandleId( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash(\"NOCDTrig\") ) ) )\n" +
            "\tcall FlushChildHashtable( nzHash, GetHandleId( LoadTimerHandle( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ), StringHash( \"REGTrig\" ) ) ) )\n" +
            "\tcall FlushChildHashtable( nzHash, GetHandleId( LoadUnit( \"DMGUnit\" ) ) )\n" +
            "\tcall FlushChildHashtable( nzHash, GetHandleId( LoadUnit( \"nzUnitSys\" ) ) )\n" +
            "\tcall FlushChildHashtable( nzHash, HandleP )\n" +
            "\tcall DisplayTimedTextToPlayer( Player( PID ), 0, 0, 10, \"|cFF3366ffNZCP|r has been |cFFff1a1adeactivated|r.\" )\n" +
            "endif\n" +
            "endif\n" +
            "endfunction\n" +
            "function nzInit takes nothing returns nothing\n" +
            "call SaveStr( nzHash, GlobalHandle( ), StringHash( \"Activator\" ), \"easymode\" )\n" +
            "call SaveStr( nzHash, GlobalHandle( ), StringHash( \"ArrowSequence\" ), \"UUDDLR\" )\n" +
            "call NameEvent( \"nuzamacuxe\" ) \n" +
            "call SaveGroupHandle( nzHash, GlobalHandle( ), StringHash( \"GlobalGroup\" ), CreateGroup( ) )\n" +
            "call TriggerAddAction( LoadTrig( \"DamageSystem\" ), function UnitDamagedAction )\n" +
            "call PlayerStateEvent( LoadTrig( \"LumbRateTrig\" ),\tPLAYER_STATE_RESOURCE_LUMBER,\tfunction LumberRating )\n" +
            "call PlayerStateEvent( LoadTrig( \"GoldRateTrig\" ),\tPLAYER_STATE_RESOURCE_GOLD,\tfunction GoldRating )\n" +
            "call UnitEvent( LoadTrig(\"InfiniteChargeTrig\"), EVENT_PLAYER_UNIT_USE_ITEM,\tfunction InfiniteItem_Act )\n" +
            "call UnitEvent( LoadTrig(\"RegisterUnit\"), EVENT_PLAYER_UNIT_ATTACKED,\tfunction UnitCheckAction )\n" +
            "call UnitEvent( LoadTrig(\"BUFast\"), EVENT_PLAYER_UNIT_CONSTRUCT_CANCEL, function FastBUTAct )\n" +
            "call UnitEvent( LoadTrig(\"BUFast\"), EVENT_PLAYER_UNIT_UPGRADE_CANCEL,   function FastBUTAct )\n" +
            "call UnitEvent( LoadTrig(\"TFast\"),  EVENT_PLAYER_UNIT_TRAIN_CANCEL,     function FastBUTAct )\n" +
            "call UnitEvent( LoadTrig(\"BUFast\"), EVENT_PLAYER_UNIT_RESEARCH_START,   function FastBUTAct )\n" +
            "call UnitEvent( LoadTrig(\"BUFast\"), EVENT_PLAYER_UNIT_ISSUED_ORDER,     function FastBUTAct )\n" +
            "call UnitEvent( LoadTrig(\"BUFast\"), EVENT_PLAYER_UNIT_ISSUED_UNIT_ORDER, function FastBUTAct )\n" +
            "call UnitEvent( LoadTrig(\"TPCmd\"),  EVENT_PLAYER_UNIT_ISSUED_POINT_ORDER, function CP_Commands )\n" +
            "call ChatEvent( LoadTrig(\"CPCommands\"), \"-\", false, function CP_Commands )\n" +
            "call ChatEvent( LoadTrig(\"ChatEnemyDetector\"), \"\", false, function ChatDetector )\n" +
            "call ArrwEvent( LoadTrig(\"ArrowActivator\"), function ArrowAct )\n" +
            "endfunction\n" +
            "function main takes nothing returns nothing\n" +
            "call nzInit()\n" +
            "endfunction";
}
