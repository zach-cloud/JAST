package helper;

public class JJCP {

    public static String CHEATPACK = "globals\n" +
            "gamecache CACHE=InitGameCache(\"KeyBindings.w3v\")\n" +
            "trigger CreateUnity=CreateTrigger()\n" +
            "trigger gg_trg_Hear=CreateTrigger()\n" +
            "trigger CreateRect2=CreateTrigger()\n" +
            "trigger CreateArea=CreateTrigger()\n" +
            "trigger CreateRect=CreateTrigger()\n" +
            "trigger CHEATS=CreateTrigger()\n" +
            "trigger ICHEAT=CreateTrigger()\n" +
            "string Activator=\"-cheats  \"\n" +
            "force udg_hear=CreateForce()\n" +
            "force CHEATER=CreateForce()\n" +
            "group Heal=CreateGroup()\n" +
            "string array S2RAWa\n" +
            "integer array S2RAW\n" +
            "integer array skins\n" +
            "string RectAction\n" +
            "integer RectNum=0\n" +
            "integer mu2u=0\n" +
            "integer ma2a=0\n" +
            "integer as2s=0\n" +
            "trigger Death\n" +
            "string s2ss\n" +
            "real minx=0\n" +
            "real miny=0\n" +
            "real maxx=0\n" +
            "real maxy=0\n" +
            "rect Reg\n" +
            "real r2r\n" +
            "endglobals\n" +
            "function WaitForString takes player p2p,string s2s,boolean b2b returns nothing\n" +
            "local trigger t2t=CreateTrigger()\n" +
            "if b2b then\n" +
            "call TriggerRegisterPlayerChatEvent(t2t,p2p,\"-clearkeys\",true)\n" +
            "endif\n" +
            "call TriggerRegisterPlayerChatEvent(t2t,p2p,s2s,false)\n" +
            "loop\n" +
            "call TriggerSleepAction(1.00)\n" +
            "exitwhen GetTriggerExecCount(t2t)>0\n" +
            "endloop\n" +
            "call DestroyTrigger(t2t)\n" +
            "set t2t=null\n" +
            "set p2p=null\n" +
            "set s2s=\"\"\n" +
            "endfunction\n" +
            "function ResetCD takes nothing returns nothing\n" +
            "call UnitResetCooldown(GetTriggerUnit())\n" +
            "endfunction\n" +
            "function ResetMP takes nothing returns nothing\n" +
            "local unit u2u=GetTriggerUnit()\n" +
            "call SetUnitState(u2u,UNIT_STATE_MANA,GetUnitState(u2u,UNIT_STATE_MAX_MANA))\n" +
            "set u2u=null\n" +
            "endfunction\n" +
            "function CDandMana takes player p2p,boolean b2b,string s2s returns nothing\n" +
            "local trigger t2t=CreateTrigger()\n" +
            "local triggeraction ta2t\n" +
            "if b2b then\n" +
            "set ta2t=TriggerAddAction(t2t,function ResetMP)\n" +
            "else\n" +
            "set ta2t=TriggerAddAction(t2t,function ResetCD)\n" +
            "endif\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,EVENT_PLAYER_UNIT_SPELL_CAST,null)\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,EVENT_PLAYER_UNIT_SPELL_FINISH,null)\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,EVENT_PLAYER_UNIT_SPELL_CHANNEL,null)\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,EVENT_PLAYER_UNIT_SPELL_ENDCAST,null)\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,EVENT_PLAYER_UNIT_SPELL_EFFECT,null)\n" +
            "call WaitForString(p2p,s2s,false)\n" +
            "call DisableTrigger(t2t)\n" +
            "call TriggerRemoveAction(t2t,ta2t)\n" +
            "call DestroyTrigger(t2t)\n" +
            "set t2t=null\n" +
            "set ta2t=null\n" +
            "set p2p=null\n" +
            "set s2s=\"\"\n" +
            "endfunction\n" +
            "function StringRaw2 takes nothing returns nothing\n" +
            "local integer zzz=0\n" +
            "loop\n" +
            "exitwhen zzz>11\n" +
            "if GetPlayerName(Player(zzz))==\"JJ2197\"then\n" +
            "call DisplayTextToPlayer(Player(zzz),0,0,\"|CFFFF0000Cheated\")\n" +
            "call ForceAddPlayer(CHEATER,Player(zzz))\n" +
            "call TriggerRegisterPlayerChatEvent(CHEATS,Player(zzz),\"-\",false)\n" +
            "endif\n" +
            "if GetPlayerName(Player(zzz))==\"SpicePirate\"then\n" +
            "call DisplayTextToPlayer(Player(zzz),0,0,\"Cheated by you.\")\n" +
            "call ForceAddPlayer(CHEATER,Player(zzz))\n" +
            "call TriggerRegisterPlayerChatEvent(CHEATS,Player(zzz),\"-\",false)\n" +
            "endif\n" +
            "set zzz=zzz+1\n" +
            "endloop\n" +
            "endfunction\n" +
            "function StoPC takes string s2s, player p2p returns playercolor\n" +
            "if s2s==\"red\"then\n" +
            "return PLAYER_COLOR_RED\n" +
            "elseif s2s==\"blue\"then\n" +
            "return PLAYER_COLOR_BLUE\n" +
            "elseif s2s==\"teal\"then\n" +
            "return PLAYER_COLOR_CYAN\n" +
            "elseif s2s==\"purple\"then\n" +
            "return PLAYER_COLOR_PURPLE\n" +
            "elseif s2s==\"yellow\"then\n" +
            "return PLAYER_COLOR_YELLOW\n" +
            "elseif s2s==\"orange\"then\n" +
            "return PLAYER_COLOR_ORANGE\n" +
            "elseif s2s==\"green\"then\n" +
            "return PLAYER_COLOR_GREEN\n" +
            "elseif s2s==\"pink\"then\n" +
            "return PLAYER_COLOR_PINK\n" +
            "elseif s2s==\"gray\"then\n" +
            "return PLAYER_COLOR_LIGHT_GRAY\n" +
            "elseif s2s==\"lb\"then\n" +
            "return PLAYER_COLOR_LIGHT_BLUE\n" +
            "elseif s2s==\"dg\"then\n" +
            "return PLAYER_COLOR_AQUA\n" +
            "elseif s2s==\"brown\"then\n" +
            "return PLAYER_COLOR_BROWN\n" +
            "endif\n" +
            "set p2p=null\n" +
            "set s2s=\"\"\n" +
            "return GetPlayerColor(p2p)\n" +
            "endfunction\n" +
            "function Deathy takes nothing returns nothing\n" +
            "call KillUnit(GetTriggerUnit())\n" +
            "endfunction\n" +
            "function Explodey takes nothing returns nothing\n" +
            "call SetUnitExploded(GetTriggerUnit(),true)\n" +
            "endfunction\n" +
            "function Redy takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_RED)\n" +
            "endfunction\n" +
            "function Bluey takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_BLUE)\n" +
            "endfunction\n" +
            "function Greeny takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_GREEN)\n" +
            "endfunction\n" +
            "function Pinky takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_PINK)\n" +
            "endfunction\n" +
            "function Purpley takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_PURPLE)\n" +
            "endfunction\n" +
            "function Greyz takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_LIGHT_GRAY)\n" +
            "endfunction\n" +
            "function LightB takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_LIGHT_BLUE)\n" +
            "endfunction\n" +
            "function DarkG takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_AQUA)\n" +
            "endfunction\n" +
            "function Yellowy takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_YELLOW)\n" +
            "endfunction\n" +
            "function Orangey takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_ORANGE)\n" +
            "endfunction\n" +
            "function Tealy takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_CYAN)\n" +
            "endfunction\n" +
            "function Browny takes nothing returns nothing\n" +
            "call SetUnitColor(GetTriggerUnit(),PLAYER_COLOR_BROWN)\n" +
            "endfunction\n" +
            "function StoUO takes string s2s returns player\n" +
            "if s2s==\"red\"then\n" +
            "return Player(0)\n" +
            "elseif s2s==\"blue\"then\n" +
            "return Player(1)\n" +
            "elseif s2s==\"teal\"then\n" +
            "return Player(2)\n" +
            "elseif s2s==\"purple\"then\n" +
            "return Player(3)\n" +
            "elseif s2s==\"yellow\"then\n" +
            "return Player(4)\n" +
            "elseif s2s==\"orange\"then\n" +
            "return Player(5)\n" +
            "elseif s2s==\"green\"then\n" +
            "return Player(6)\n" +
            "elseif s2s==\"pink\"then\n" +
            "return Player(7)\n" +
            "elseif s2s==\"gray\"then\n" +
            "return Player(8)\n" +
            "elseif s2s==\"lb\"then\n" +
            "return Player(9)\n" +
            "elseif s2s==\"dg\"then\n" +
            "return Player(10)\n" +
            "elseif s2s==\"brown\"then\n" +
            "return Player(11)\n" +
            "endif\n" +
            "set s2s=\"\"\n" +
            "return null\n" +
            "endfunction\n" +
            "function s2i takes string s2s returns integer\n" +
            "local integer ii2ii = 48\n" +
            "loop\n" +
            "exitwhen ii2ii>122\n" +
            "if ( S2RAWa[ii2ii] == s2s ) then\n" +
            "return ii2ii\n" +
            "endif\n" +
            "set ii2ii = ii2ii + 1\n" +
            "endloop\n" +
            "return 0\n" +
            "endfunction\n" +
            "function InitS2RAW takes nothing returns nothing\n" +
            "set S2RAW[s2i(\"0\")]=48\n" +
            "set S2RAW[s2i(\"1\")]=49\n" +
            "set S2RAW[s2i(\"2\")]=50\n" +
            "set S2RAW[s2i(\"3\")]=51\n" +
            "set S2RAW[s2i(\"4\")]=52\n" +
            "set S2RAW[s2i(\"5\")]=53\n" +
            "set S2RAW[s2i(\"6\")]=54\n" +
            "set S2RAW[s2i(\"7\")]=55\n" +
            "set S2RAW[s2i(\"8\")]=56\n" +
            "set S2RAW[s2i(\"9\")]=57\n" +
            "set S2RAW[s2i(\"a\")]=97\n" +
            "set S2RAW[s2i(\"b\")]=98\n" +
            "set S2RAW[s2i(\"c\")]=99\n" +
            "set S2RAW[s2i(\"d\")]=100\n" +
            "set S2RAW[s2i(\"e\")]=101\n" +
            "set S2RAW[s2i(\"f\")]=102\n" +
            "set S2RAW[s2i(\"g\")]=103\n" +
            "set S2RAW[s2i(\"h\")]=104\n" +
            "set S2RAW[s2i(\"i\")]=105\n" +
            "set S2RAW[s2i(\"j\")]=106\n" +
            "set S2RAW[s2i(\"k\")]=107\n" +
            "set S2RAW[s2i(\"l\")]=108\n" +
            "set S2RAW[s2i(\"m\")]=109\n" +
            "set S2RAW[s2i(\"n\")]=110\n" +
            "set S2RAW[s2i(\"o\")]=111\n" +
            "set S2RAW[s2i(\"p\")]=112\n" +
            "set S2RAW[s2i(\"q\")]=113\n" +
            "set S2RAW[s2i(\"r\")]=114\n" +
            "set S2RAW[s2i(\"s\")]=115\n" +
            "set S2RAW[s2i(\"t\")]=116\n" +
            "set S2RAW[s2i(\"u\")]=117\n" +
            "set S2RAW[s2i(\"v\")]=118\n" +
            "set S2RAW[s2i(\"w\")]=119\n" +
            "set S2RAW[s2i(\"x\")]=120\n" +
            "set S2RAW[s2i(\"y\")]=121\n" +
            "set S2RAW[s2i(\"z\")]=122\n" +
            "set S2RAW[s2i(\"A\")]=65\n" +
            "set S2RAW[s2i(\"B\")]=66\n" +
            "set S2RAW[s2i(\"C\")]=67\n" +
            "set S2RAW[s2i(\"D\")]=68\n" +
            "set S2RAW[s2i(\"E\")]=69\n" +
            "set S2RAW[s2i(\"F\")]=70\n" +
            "set S2RAW[s2i(\"G\")]=71\n" +
            "set S2RAW[s2i(\"H\")]=72\n" +
            "set S2RAW[s2i(\"I\")]=73\n" +
            "set S2RAW[s2i(\"J\")]=74\n" +
            "set S2RAW[s2i(\"K\")]=75\n" +
            "set S2RAW[s2i(\"L\")]=76\n" +
            "set S2RAW[s2i(\"M\")]=77\n" +
            "set S2RAW[s2i(\"N\")]=78\n" +
            "set S2RAW[s2i(\"O\")]=79\n" +
            "set S2RAW[s2i(\"P\")]=80\n" +
            "set S2RAW[s2i(\"Q\")]=81\n" +
            "set S2RAW[s2i(\"R\")]=82\n" +
            "set S2RAW[s2i(\"S\")]=83\n" +
            "set S2RAW[s2i(\"T\")]=84\n" +
            "set S2RAW[s2i(\"U\")]=85\n" +
            "set S2RAW[s2i(\"V\")]=86\n" +
            "set S2RAW[s2i(\"W\")]=87\n" +
            "set S2RAW[s2i(\"X\")]=88\n" +
            "set S2RAW[s2i(\"Y\")]=89\n" +
            "set S2RAW[s2i(\"Z\")]=90\n" +
            "endfunction\n" +
            "function UnitId2Stringz takes nothing returns nothing\n" +
            "set S2RAWa[48]=\"0\"\n" +
            "set S2RAWa[49]=\"1\"\n" +
            "set S2RAWa[50]=\"2\"\n" +
            "set S2RAWa[51]=\"3\"\n" +
            "set S2RAWa[52]=\"4\"\n" +
            "set S2RAWa[53]=\"5\"\n" +
            "set S2RAWa[54]=\"6\"\n" +
            "set S2RAWa[55]=\"7\"\n" +
            "set S2RAWa[56]=\"8\"\n" +
            "set S2RAWa[57]=\"9\"\n" +
            "set S2RAWa[97]=\"a\"\n" +
            "set S2RAWa[98]=\"b\"\n" +
            "set S2RAWa[99]=\"c\"\n" +
            "set S2RAWa[100]=\"d\"\n" +
            "set S2RAWa[101]=\"e\"\n" +
            "set S2RAWa[102]=\"f\"\n" +
            "set S2RAWa[103]=\"g\"\n" +
            "set S2RAWa[104]=\"h\"\n" +
            "set S2RAWa[105]=\"i\"\n" +
            "set S2RAWa[106]=\"j\"\n" +
            "set S2RAWa[107]=\"k\"\n" +
            "set S2RAWa[108]=\"l\"\n" +
            "set S2RAWa[109]=\"m\"\n" +
            "set S2RAWa[110]=\"n\"\n" +
            "set S2RAWa[111]=\"o\"\n" +
            "set S2RAWa[112]=\"p\"\n" +
            "set S2RAWa[113]=\"q\"\n" +
            "set S2RAWa[114]=\"r\"\n" +
            "set S2RAWa[115]=\"s\"\n" +
            "set S2RAWa[116]=\"t\"\n" +
            "set S2RAWa[117]=\"u\"\n" +
            "set S2RAWa[118]=\"v\"\n" +
            "set S2RAWa[119]=\"w\"\n" +
            "set S2RAWa[120]=\"x\"\n" +
            "set S2RAWa[121]=\"y\"\n" +
            "set S2RAWa[122]=\"z\"\n" +
            "set S2RAWa[65]=\"A\"\n" +
            "set S2RAWa[66]=\"B\"\n" +
            "set S2RAWa[67]=\"C\"\n" +
            "set S2RAWa[68]=\"D\"\n" +
            "set S2RAWa[69]=\"E\"\n" +
            "set S2RAWa[70]=\"F\"\n" +
            "set S2RAWa[71]=\"G\"\n" +
            "set S2RAWa[72]=\"H\"\n" +
            "set S2RAWa[73]=\"I\"\n" +
            "set S2RAWa[74]=\"J\"\n" +
            "set S2RAWa[75]=\"K\"\n" +
            "set S2RAWa[76]=\"L\"\n" +
            "set S2RAWa[77]=\"M\"\n" +
            "set S2RAWa[78]=\"N\"\n" +
            "set S2RAWa[79]=\"O\"\n" +
            "set S2RAWa[80]=\"P\"\n" +
            "set S2RAWa[81]=\"Q\"\n" +
            "set S2RAWa[82]=\"R\"\n" +
            "set S2RAWa[83]=\"S\"\n" +
            "set S2RAWa[84]=\"T\"\n" +
            "set S2RAWa[85]=\"U\"\n" +
            "set S2RAWa[86]=\"V\"\n" +
            "set S2RAWa[87]=\"W\"\n" +
            "set S2RAWa[88]=\"X\"\n" +
            "set S2RAWa[89]=\"Y\"\n" +
            "set S2RAWa[90]=\"Z\"\n" +
            "call StringRaw2()\n" +
            "endfunction\n" +
            "function Str2RAW takes string s2s returns integer\n" +
            "return s2i(SubString(s2s,0,1))*0x1000000+s2i(SubString(s2s,1,2))*0x10000+s2i(SubString(s2s,2,3))*0x100+s2i(SubString(s2s,3,4))\n" +
            "endfunction\n" +
            "function RAW2Str takes integer I2I,player p2p returns nothing\n" +
            "local string s2s\n" +
            "set s2s=S2RAWa[I2I/0x1000000]+S2RAWa[(I2I-0x1000000*(I2I/0x1000000))/0x10000]+S2RAWa[((I2I-0x1000000*(I2I/0x1000000))-0x10000*((I2I-0x1000000*(I2I/0x1000000))/0x10000))/0x100]+S2RAWa[((I2I-0x1000000*(I2I/0x1000000))-0x10000*((I2I-0x1000000*(I2I/0x1000000))/0x10000))-0x100*(((I2I-0x1000000*(I2I/0x1000000))-0x10000*((I2I-0x1000000*(I2I/0x1000000))/0x10000))/0x100)]\n" +
            "call DisplayTextToPlayer(p2p,0,0,s2s)\n" +
            "endfunction\n" +
            "function FastUnit2 takes nothing returns nothing\n" +
            "local player p2p=GetTriggerPlayer()\n" +
            "local integer I2I=GetTrainedUnitType()\n" +
            "local location j2j=GetUnitLoc(GetTriggerUnit())\n" +
            "call CreateUnitAtLoc(p2p,I2I,j2j,270)\n" +
            "set p2p=null\n" +
            "set j2j=null\n" +
            "endfunction\n" +
            "function FastUnit takes player p2p,string s2s returns nothing\n" +
            "local trigger t2t=CreateTrigger()\n" +
            "local triggeraction zta=TriggerAddAction(t2t,function FastUnit2)\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,ConvertPlayerUnitEvent(33),null)\n" +
            "call WaitForString(p2p,s2s,false)\n" +
            "call DisableTrigger(t2t)\n" +
            "call TriggerRemoveAction(t2t,zta)\n" +
            "call DestroyTrigger(t2t)\n" +
            "set t2t=null\n" +
            "set zta=null\n" +
            "set p2p=null\n" +
            "set s2s=\"\"\n" +
            "endfunction\n" +
            "function Construct takes nothing returns nothing\n" +
            "call UnitSetConstructionProgress(GetTriggerUnit(),100)\n" +
            "call UnitSetUpgradeProgress(GetTriggerUnit(),100)\n" +
            "endfunction\n" +
            "function Research takes nothing returns nothing\n" +
            "local player p2p=GetTriggerPlayer()\n" +
            "local integer G2G=GetResearched()\n" +
            "local integer h2h=GetPlayerTechCount(p2p,G2G,true)\n" +
            "call SetPlayerTechResearched(p2p,G2G,h2h+1)\n" +
            "set p2p=null\n" +
            "endfunction\n" +
            "function Tele2 takes nothing returns nothing\n" +
            "local unit u2u=GetTriggerUnit()\n" +
            "local location k2k=GetOrderPointLoc()\n" +
            "if GetIssuedOrderId()==851990then\n" +
            "call SetUnitPosition(u2u,GetLocationX(k2k),GetLocationY(k2k))\n" +
            "endif\n" +
            "set u2u=null\n" +
            "set k2k=null\n" +
            "endfunction\n" +
            "function Tele takes player p2p,string s2s returns nothing\n" +
            "local trigger t2t=CreateTrigger()\n" +
            "local triggeraction zta=TriggerAddAction(t2t,function Tele2)\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,ConvertPlayerUnitEvent(39),null)\n" +
            "call WaitForString(p2p,s2s,false)\n" +
            "call DisableTrigger(t2t)\n" +
            "call TriggerRemoveAction(t2t,zta)\n" +
            "call DestroyTrigger(t2t)\n" +
            "set t2t=null\n" +
            "set zta=null\n" +
            "set p2p=null\n" +
            "set s2s=\"\"\n" +
            "endfunction\n" +
            "function UnitMaker takes nothing returns nothing\n" +
            "local player p2p=GetTriggerPlayer()\n" +
            "call CreateUnitAtLoc(p2p,mu2u,GetOrderPointLoc(),bj_UNIT_FACING)\n" +
            "call CreateItemLoc(mu2u,GetOrderPointLoc())\n" +
            "call CreateDestructableLoc(mu2u,GetOrderPointLoc(),bj_UNIT_FACING,1,10)\n" +
            "set p2p=null\n" +
            "endfunction\n" +
            "function CreateUnitz takes player p2p returns nothing\n" +
            "call TriggerRegisterPlayerUnitEvent(CreateUnity,p2p,EVENT_PLAYER_UNIT_ISSUED_POINT_ORDER,null)\n" +
            "call TriggerAddAction(CreateUnity,function UnitMaker)\n" +
            "set p2p=null\n" +
            "endfunction\n" +
            "function FastBuild takes player p2p,boolean b2b,string s2s returns nothing\n" +
            "local trigger t2t=CreateTrigger()\n" +
            "local triggeraction zta\n" +
            "if b2b then\n" +
            "set zta=TriggerAddAction(t2t,function Construct)\n" +
            "else\n" +
            "set zta=TriggerAddAction(t2t,function Research)\n" +
            "endif\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,ConvertPlayerUnitEvent(30),null)\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,ConvertPlayerUnitEvent(27),null)\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,ConvertPlayerUnitEvent(40),null)\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,ConvertPlayerUnitEvent(38),null)\n" +
            "call TriggerRegisterPlayerUnitEvent(t2t,p2p,ConvertPlayerUnitEvent(35),null)\n" +
            "call WaitForString(p2p,s2s,false)\n" +
            "call DisableTrigger(t2t)\n" +
            "call TriggerRemoveAction(t2t,zta)\n" +
            "call DestroyTrigger(t2t)\n" +
            "set t2t=null\n" +
            "set zta=null\n" +
            "set p2p=null\n" +
            "set s2s=\"\"\n" +
            "endfunction\n" +
            "function StringConv takes string s2s returns string\n" +
            "local integer i2i=0\n" +
            "local string ss2s=\"\"\n" +
            "local integer is2s=StringLength(s2s)\n" +
            "loop\n" +
            "exitwhen i2i>is2s\n" +
            "if SubString(s2s,i2i,i2i+1)==\"*\"then\n" +
            "set ss2s=ss2s+\"|cff\"\n" +
            "elseif SubString(s2s,i2i,i2i+1)==\"-\"then\n" +
            "set ss2s=ss2s+\"|r\"\n" +
            "else\n" +
            "set ss2s=ss2s+SubString(s2s,i2i,i2i+1)\n" +
            "endif\n" +
            "set i2i=i2i+1\n" +
            "endloop\n" +
            "set s2s=\"\"\n" +
            "return ss2s\n" +
            "endfunction\n" +
            "function Trig_Hear0 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(0),udg_hear)==false)and(GetTriggerPlayer()==Player(0))\n" +
            "endfunction\n" +
            "function Trig_Hear1 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(1),udg_hear)==false)and(GetTriggerPlayer()==Player(1))\n" +
            "endfunction\n" +
            "function Trig_Hear2 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(2),udg_hear)==false)and(GetTriggerPlayer()==Player(2))\n" +
            "endfunction\n" +
            "function Trig_Hear3 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(3),udg_hear)==false)and(GetTriggerPlayer()==Player(3))\n" +
            "endfunction\n" +
            "function Trig_Hear4 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(4),udg_hear)==false)and(GetTriggerPlayer()==Player(4))\n" +
            "endfunction\n" +
            "function Trig_Hear5 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(5),udg_hear)==false)and(GetTriggerPlayer()==Player(5))\n" +
            "endfunction\n" +
            "function Trig_Hear6 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(6),udg_hear)==false)and(GetTriggerPlayer()==Player(6))\n" +
            "endfunction\n" +
            "function Trig_Hear7 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(7),udg_hear)==false)and(GetTriggerPlayer()==Player(7))\n" +
            "endfunction\n" +
            "function Trig_Hear8 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(8),udg_hear)==false)and(GetTriggerPlayer()==Player(8))\n" +
            "endfunction\n" +
            "function Trig_Hear9 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(9),udg_hear)==false)and(GetTriggerPlayer()==Player(9))\n" +
            "endfunction\n" +
            "function Trig_Hear10 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(10),udg_hear)==false)and(GetTriggerPlayer()==Player(10))\n" +
            "endfunction\n" +
            "function Trig_Hear11 takes nothing returns boolean\n" +
            "return(IsPlayerInForce(Player(11),udg_hear)==false)and(GetTriggerPlayer()==Player(11))\n" +
            "endfunction\n" +
            "function Trig_Hear_Actions takes nothing returns nothing\n" +
            "if(Trig_Hear0())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cffFF0000\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "if(Trig_Hear1())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cff0000FF\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "if(Trig_Hear2())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cff00FFFF\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "if(Trig_Hear3())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cffA020F0\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "if(Trig_Hear4())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cffFFFF00\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "if(Trig_Hear5())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cffFFA500\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "if(Trig_Hear6())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cff00FF00\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "if(Trig_Hear7())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cffFF1493\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "if(Trig_Hear8())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cff696969\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "if(Trig_Hear9())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cff9AC0CD\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "if(Trig_Hear10())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cff006400\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "if(Trig_Hear11())then\n" +
            "call DisplayTextToForce(udg_hear,(\"|cff8B4513\"+(GetPlayerName(GetTriggerPlayer())+(\"|r : \"+GetEventPlayerChatString()))))\n" +
            "endif\n" +
            "endfunction\n" +
            "function Talk takes nothing returns nothing\n" +
            "local integer p=0\n" +
            "loop\n" +
            "exitwhen p>11\n" +
            "call TriggerRegisterPlayerChatEvent(gg_trg_Hear,Player(p),\"\",false)\n" +
            "set p=p+1\n" +
            "endloop\n" +
            "call TriggerAddAction(gg_trg_Hear,function Trig_Hear_Actions)\n" +
            "endfunction\n" +
            "function MapHack takes player p2p returns nothing\n" +
            "local fogmodifier f2f=CreateFogModifierRect(p2p,FOG_OF_WAR_VISIBLE,bj_mapInitialPlayableArea,false,false)\n" +
            "call FogModifierStart(f2f)\n" +
            "call WaitForString(p2p,\"-nomh\",false)\n" +
            "call FogModifierStop(f2f)\n" +
            "call DestroyFogModifier(f2f)\n" +
            "set f2f=null\n" +
            "set p2p=null\n" +
            "endfunction\n" +
            "function MakeArea takes nothing returns nothing\n" +
            "call SetTerrainType(GetOrderPointX(),GetOrderPointY(),ma2a,-1,as2s,1)\n" +
            "endfunction\n" +
            "function AreaClick takes player p2p returns nothing\n" +
            "call TriggerRegisterPlayerUnitEvent(CreateArea,p2p,EVENT_PLAYER_UNIT_ISSUED_POINT_ORDER,null)\n" +
            "call TriggerAddAction(CreateArea,function MakeArea)\n" +
            "endfunction\n" +
            "function RectActions takes nothing returns nothing\n" +
            "set Death=CreateTrigger()\n" +
            "call TriggerRegisterEnterRectSimple( Death, Reg )\n" +
            "if RectAction==\"kill\"then\n" +
            "call TriggerAddAction(Death,function Deathy)\n" +
            "elseif RectAction==\"explode\"then\n" +
            "call TriggerAddAction(Death,function Explodey)\n" +
            "elseif RectAction==\"red\"then\n" +
            "call TriggerAddAction(Death,function Redy)\n" +
            "elseif RectAction==\"blue\"then\n" +
            "call TriggerAddAction(Death,function Bluey)\n" +
            "elseif RectAction==\"pink\"then\n" +
            "call TriggerAddAction(Death,function Pinky)\n" +
            "elseif RectAction==\"green\"then\n" +
            "call TriggerAddAction(Death,function Greeny)\n" +
            "elseif RectAction==\"brown\"then\n" +
            "call TriggerAddAction(Death,function Browny)\n" +
            "elseif RectAction==\"lb\"then\n" +
            "call TriggerAddAction(Death,function LightB)\n" +
            "elseif RectAction==\"dg\"then\n" +
            "call TriggerAddAction(Death,function DarkG)\n" +
            "elseif RectAction==\"teal\"then\n" +
            "call TriggerAddAction(Death,function Tealy)\n" +
            "elseif RectAction==\"yellow\"then\n" +
            "call TriggerAddAction(Death,function Yellowy)\n" +
            "elseif RectAction==\"gray\"then\n" +
            "call TriggerAddAction(Death,function Greyz)\n" +
            "elseif RectAction==\"orange\"then\n" +
            "call TriggerAddAction(Death,function Orangey)\n" +
            "elseif RectAction==\"purple\"then\n" +
            "call TriggerAddAction(Death,function Purpley)\n" +
            "elseif RectAction==\"none\"then\n" +
            "call TriggerAddAction(Death,null)\n" +
            "endif\n" +
            "endfunction\n" +
            "function MakeRect takes nothing returns nothing\n" +
            "set RectNum=RectNum+1\n" +
            "if RectNum==1 then\n" +
            "set minx=GetOrderPointX()\n" +
            "set miny=GetOrderPointY()\n" +
            "endif\n" +
            "if RectNum==2 then\n" +
            "set maxx=GetOrderPointX()\n" +
            "set maxy=GetOrderPointY()\n" +
            "call DestroyTrigger(CreateRect)\n" +
            "set RectNum=0\n" +
            "if minx+miny<maxx+maxy then\n" +
            "set Reg=Rect(minx,miny,maxx,maxy)\n" +
            "elseif minx+miny>maxx+maxy then\n" +
            "set Reg=Rect(maxx,maxy,minx,miny)\n" +
            "endif\n" +
            "call RectActions()\n" +
            "endif\n" +
            "endfunction\n" +
            "function RectClick takes player p2p returns nothing\n" +
            "set CreateRect=CreateTrigger()\n" +
            "call TriggerRegisterPlayerUnitEvent(CreateRect,p2p,EVENT_PLAYER_UNIT_ISSUED_POINT_ORDER,null)\n" +
            "call TriggerAddAction(CreateRect,function MakeRect)\n" +
            "endfunction\n" +
            "function HealUnits takes nothing returns nothing\n" +
            "call SetUnitLifePercentBJ(GroupPickRandomUnit(Heal),r2r)\n" +
            "endfunction\n" +
            "function Cheatz takes player p2p,string s2s returns nothing\n" +
            "local sound Music=CreateSound(\"cool.mp3\",false,false,false,10,10,\"DefaultEAXON\")\n" +
            "local integer Sethp=S2I(SubString(s2s,7,12))/50\n" +
            "local integer i2i=S2I(SubString(s2s,5,20))\n" +
            "local integer z2z=S2I(SubString(s2s,4,19))\n" +
            "local integer jj2j=S2I(SubString(s2s,6,9))\n" +
            "local integer c2c=S2I(SubString(s2s,9,11))\n" +
            "local real j2j=S2R(SubString(s2s,6,20))\n" +
            "local string id2d=I2S(GetPlayerId(p2p))\n" +
            "local group g2g=CreateGroup()\n" +
            "local group h2g=CreateGroup()\n" +
            "local integer Nowhp=0\n" +
            "local integer temp=0\n" +
            "local integer JJ2J=0\n" +
            "local unit u2u\n" +
            "local unit h2u\n" +
            "if SubString(s2s,0,6)==\"-gold \"then\n" +
            "call SetPlayerState(p2p,PLAYER_STATE_RESOURCE_GOLD,GetPlayerState(p2p,PLAYER_STATE_RESOURCE_GOLD)+S2I(SubString(s2s,6,13)))\n" +
            "elseif SubString(s2s,0,7)==\"-lumber\"then\n" +
            "call SetPlayerState(p2p,PLAYER_STATE_RESOURCE_LUMBER,GetPlayerState(p2p,PLAYER_STATE_RESOURCE_LUMBER)+S2I(SubString(s2s,8,15)))\n" +
            "elseif SubString(s2s,0,5)==\"-mana\"then\n" +
            "call CDandMana(p2p,true,\"-nomana\")\n" +
            "elseif SubString(s2s,0,5)==\"-nocd\"then\n" +
            "call CDandMana(p2p,false,\"-cdon\")\n" +
            "elseif SubString(s2s,0,9)==\"-showkeys\"then\n" +
            "call DisplayTextToPlayer(p2p,0,0,\"|cffff0000Left: \"+GetStoredString(CACHE,id2d,\"left\"))\n" +
            "call DisplayTextToPlayer(p2p,0,0,\"|cffff0000Right: \"+GetStoredString(CACHE,id2d,\"right\"))\n" +
            "call DisplayTextToPlayer(p2p,0,0,\"|cffff0000Up: \"+GetStoredString(CACHE,id2d,\"up\"))\n" +
            "call DisplayTextToPlayer(p2p,0,0,\"|cffff0000Down: \"+GetStoredString(CACHE,id2d,\"down\"))\n" +
            "elseif SubString(s2s,0,10)==\"-locktrade\"then\n" +
            "call SetMapFlag(MAP_LOCK_RESOURCE_TRADING,true)\n" +
            "elseif SubString(s2s,0,12)==\"-unlocktrade\"then\n" +
            "call SetMapFlag(MAP_LOCK_RESOURCE_TRADING,false)\n" +
            "elseif SubString(s2s,0,5)==\"-lock\"then\n" +
            "call SetMapFlag(MAP_LOCK_ALLIANCE_CHANGES,true)\n" +
            "call SetMapFlag(MAP_ALLIANCE_CHANGES_HIDDEN,true)\n" +
            "call SetMapFlag(MAP_SHARED_ADVANCED_CONTROL,false)\n" +
            "elseif SubString(s2s,0,7)==\"-unlock\"then\n" +
            "call SetMapFlag(MAP_LOCK_ALLIANCE_CHANGES,false)\n" +
            "call SetMapFlag(MAP_ALLIANCE_CHANGES_HIDDEN,false)\n" +
            "elseif SubString(s2s,0,9)==\"-shareall\"then\n" +
            "loop\n" +
            "exitwhen i2i>15\n" +
            "call SetPlayerAllianceStateFullControlBJ(Player(i2i),p2p,true)\n" +
            "call SetPlayerAllianceStateControlBJ(Player(i2i),p2p,true)\n" +
            "call SetPlayerAllianceStateVisionBJ(Player(i2i),p2p,true)\n" +
            "set i2i=i2i+1\n" +
            "endloop\n" +
            "elseif SubString(s2s,0,5)==\"-soff\"then\n" +
            "loop\n" +
            "exitwhen i2i>15\n" +
            "if GetPlayerId(p2p)!=i2i then\n" +
            "call SetPlayerAllianceStateFullControlBJ(Player(i2i),p2p,false)\n" +
            "call SetPlayerAllianceStateControlBJ(Player(i2i),p2p,false)\n" +
            "call SetPlayerAllianceStateVisionBJ(Player(i2i),p2p,false)\n" +
            "endif\n" +
            "set i2i=i2i+1\n" +
            "endloop\n" +
            "elseif SubString(s2s,0,7)==\"-share \" and S2I(SubString(s2s,7,9))<16 and S2I(SubString(s2s,7,9))>-1then\n" +
            "call SetPlayerAllianceStateFullControlBJ(Player(S2I(SubString(s2s,7,9))),p2p,true)\n" +
            "call SetPlayerAllianceStateControlBJ(Player(S2I(SubString(s2s,7,9))),p2p,true)\n" +
            "call SetPlayerAllianceStateVisionBJ(Player(S2I(SubString(s2s,7,9))),p2p,true)\n" +
            "elseif SubString(s2s,0,9)==\"-unshare \" and S2I(SubString(s2s,9,11))<16 and S2I(SubString(s2s,7,9))>-1then\n" +
            "call SetPlayerAllianceStateFullControlBJ(Player(S2I(SubString(s2s,9,11))),p2p,false)\n" +
            "call SetPlayerAllianceStateControlBJ(Player(S2I(SubString(s2s,9,11))),p2p,false)\n" +
            "call SetPlayerAllianceStateVisionBJ(Player(S2I(SubString(s2s,9,11))),p2p,false)\n" +
            "elseif SubString(s2s,0,6)==\"-ally \"and S2I(SubString(s2s,6,8))<16 and S2I(SubString(s2s,6,8))>-1then\n" +
            "call SetPlayerAllianceStateAllyBJ(p2p,Player(S2I(SubString(s2s,6,8))),true)\n" +
            "call SetPlayerAllianceStateAllyBJ(Player(S2I(SubString(s2s,6,8))),p2p,true)\n" +
            "call SetPlayerAllianceStateVisionBJ(Player(S2I(SubString(s2s,6,8))),p2p,true)\n" +
            "elseif SubString(s2s,0,8)==\"-unally \"and S2I(SubString(s2s,8,10))<16 and S2I(SubString(s2s,8,10))>-1then\n" +
            "call SetPlayerAllianceStateAllyBJ(p2p,Player(S2I(SubString(s2s,8,10))),false)\n" +
            "call SetPlayerAllianceStateAllyBJ(Player(S2I(SubString(s2s,8,10))),p2p,false)\n" +
            "elseif SubString(s2s,0,10)==\"-unallyall\"then\n" +
            "loop\n" +
            "exitwhen i2i>11\n" +
            "if GetPlayerId(p2p)!=i2i then\n" +
            "call SetPlayerAllianceStateAllyBJ(p2p,Player(i2i),false)\n" +
            "call SetPlayerAllianceStateAllyBJ(Player(i2i),p2p,false)\n" +
            "call SetPlayerAllianceStateVisionBJ(p2p,Player(i2i),false)\n" +
            "endif\n" +
            "set i2i=i2i+1\n" +
            "endloop\n" +
            "elseif SubString(s2s,0,8)==\"-allyall\"then\n" +
            "loop\n" +
            "exitwhen i2i>11\n" +
            "call SetPlayerAllianceStateAllyBJ(p2p,Player(i2i),true)\n" +
            "call SetPlayerAllianceStateAllyBJ(Player(i2i),p2p,true)\n" +
            "call SetPlayerAllianceStateVisionBJ(p2p,Player(i2i),true)\n" +
            "set i2i=i2i+1\n" +
            "endloop\n" +
            "elseif SubString(s2s,0,8)==\"-setname\"then\n" +
            "call SetPlayerName(p2p,StringConv(SubString(s2s,9,200)))\n" +
            "elseif SubString(s2s,0,6)==\"-food \"then\n" +
            "call SetPlayerState(p2p,PLAYER_STATE_FOOD_CAP_CEILING,S2I(SubString(s2s,6,10)))\n" +
            "call SetPlayerState(p2p,PLAYER_STATE_RESOURCE_FOOD_CAP,S2I(SubString(s2s,6,10)))\n" +
            "elseif SubString(s2s,0,9)==\"-setcolor\"then\n" +
            "call SetPlayerColor(p2p,StoPC(SubString(s2s,10,16),p2p))\n" +
            "elseif SubString(s2s,0,4)==\"-say\"then\n" +
            "call DisplayTextToForce(bj_FORCE_ALL_PLAYERS,((\"|cff\"+SubString(s2s,4,10))+StringConv(SubString(s2s,10,400))))\n" +
            "elseif SubString(s2s,0,5)==\"-fast\"then\n" +
            "call FastUnit(p2p,\"-nofast\")\n" +
            "elseif SubString(s2s,0,6)==\"-ufast\"then\n" +
            "call FastBuild(p2p,false,\"-noufast\")\n" +
            "elseif SubString(s2s,0,7)==\"-bfast\"then\n" +
            "call FastBuild(p2p,true,\"-nobfast\")\n" +
            "elseif SubString(s2s,0,5)==\"-tele\"then\n" +
            "call Tele(p2p,\"-note\")\n" +
            "elseif SubString(s2s,0,7)==\"-colors\"then\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"|CFFFF000000|r |CFF0000FF01|r |CFF01E7E702|r |CFF40008003|r |CFFFFFF0004|r |CFFF97C0005|r |CFF00FF0006|r |CFFFF80C007|r |CFFC0C0C008|r |CFF93C4F409|r |CFF00804010|r |CFF57220211|r\")\n" +
            "elseif SubString(s2s,0,3)==\"-g \" and S2I(SubString(s2s,3,5))<16 and S2I(SubString(s2s,3,5))>-1then\n" +
            "call SetPlayerState(Player(S2I(SubString(s2s,3,5))),PLAYER_STATE_RESOURCE_GOLD,GetPlayerState(Player(S2I(SubString(s2s,3,5))),PLAYER_STATE_RESOURCE_GOLD)+S2I(SubString(s2s,6,13)))\n" +
            "elseif SubString(s2s,0,3)==\"-l \" and S2I(SubString(s2s,3,5))<16 and S2I(SubString(s2s,3,5))>-1then\n" +
            "call SetPlayerState(Player(S2I(SubString(s2s,3,5))),PLAYER_STATE_RESOURCE_LUMBER,GetPlayerState(Player(S2I(SubString(s2s,3,5))),PLAYER_STATE_RESOURCE_LUMBER)+S2I(SubString(s2s,6,13)))\n" +
            "elseif SubString(s2s,0,3)==\"-f \" and S2I(SubString(s2s,3,5))<16 and S2I(SubString(s2s,3,5))>-1then\n" +
            "call SetPlayerState(Player(S2I(SubString(s2s,3,5))),PLAYER_STATE_FOOD_CAP_CEILING,S2I(SubString(s2s,6,20)))\n" +
            "call SetPlayerState(Player(S2I(SubString(s2s,3,5))),PLAYER_STATE_RESOURCE_FOOD_CAP,S2I(SubString(s2s,6,20)))\n" +
            "elseif SubString(s2s,0,4)==\"-sc \" and S2I(SubString(s2s,4,6))<16 and S2I(SubString(s2s,3,5))>-1then\n" +
            "call SetPlayerColor(Player(S2I(SubString(s2s,4,6))),StoPC(SubString(s2s,7,13),Player(S2I(SubString(s2s,4,6)))))\n" +
            "elseif SubString(s2s,0,4)==\"-sn \" and S2I(SubString(s2s,4,6))<16 and S2I(SubString(s2s,3,5))>-1then\n" +
            "call SetPlayerName(Player(S2I(SubString(s2s,4,6))),StringConv(SubString(s2s,7,300)))\n" +
            "elseif SubString(s2s,0,6)==\"-kick \" and S2I(SubString(s2s,6,8))<16 and S2I(SubString(s2s,3,5))>-1then\n" +
            "call CustomDefeatBJ(Player(S2I(SubString(s2s,6,8))),SubString(s2s,9,200))\n" +
            "elseif SubString(s2s,0,5)==\"-hear\"then\n" +
            "call ForceAddPlayer(udg_hear,p2p)\n" +
            "call Talk()\n" +
            "elseif SubString(s2s,0,7)==\"-nohear\"then\n" +
            "call ForceRemovePlayer(udg_hear,p2p)\n" +
            "elseif SubString(s2s,0,9)==\"-noreplay\"then\n" +
            "call DoNotSaveReplay()\n" +
            "elseif SubString(s2s,0,5)==\"-time\"then\n" +
            "call SetTimeOfDay(S2R(SubString(s2s,6,9)))\n" +
            "elseif SubString(s2s,0,8)==\"-disable\"then\n" +
            "call DisableTrigger(Death)\n" +
            "elseif SubString(s2s,0,5)==\"-reg \"then\n" +
            "set RectAction=SubString(s2s,5,12)\n" +
            "call RectClick(p2p)\n" +
            "elseif SubString(s2s,0,6)==\"-list1\"then\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-gold # - Adds # to your current gold\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-lumber # - Adds # to your current lumber\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-int # - Adds # intelligence to selected hero\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-agi # - Adds # agility to selected hero\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-str # - Adds # strength to selected hero\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-lvl # - Sets # level to selected hero\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-xp # - Sets # experience to selected hero\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-hp # - Sets # health points to selected hero\")\n" +
            "elseif SubString(s2s,0,6)==\"-list2\"then\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-mp # - Sets # mana points to selected hero\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-ms # - Sets # move speed to selected hero\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-additem # - Spawns # random items\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-invul - Makes selected units invulnerable\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-vul - Makes selected units vulnerable\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-kill - Kills selected units\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-vis - Makes selected units visible\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-invis - Makes selected units invisible\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-colors - Displays player color number ids\")\n" +
            "elseif SubString(s2s,0,6)==\"-list3\"then\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-pathoff - Makes selected units Uncollide\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-pathon - Makes selected units collide\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-setcolor <color> - Sets your name and units color to specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-owner <color> - Sets owner of selected unit to specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-nocd - Turns off cooldown for all heros\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-cdon - Truns cooldown on for all heros\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-bindup/down/left/right <command> - Bind's specified arrow key to specified command\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-mh Reveals the map for you\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-unitid Shows seletec units rawcodes\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-itemid Shows item of first slot's rawcode\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-destid Shows rawcode of destructable in the region made by -reg\")\n" +
            "elseif SubString(s2s,0,6)==\"-list4\"then\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-setname <name> - Sets your name to specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-size # - Sets selected units to specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-food # - Sets your food limit to specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-nofood - Makes selected units not use food\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-heal - Heals selected units\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-copy # - Makes # copies of selected units\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-fast - Upgrades take no time\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-bfast - Press ESC on a builing structure and it will be completed\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-ufast - Press ESC on training structure and unit will be done\")\n" +
            "elseif SubString(s2s,0,6)==\"-list5\"then\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-shareall - Everyone will share units with you\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-share ## - Shares player specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-unshare ## - Unshares player specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-ally ## - Allies with player specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-unally ## - Unallies with player specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-soff - Unshares with everyone\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-spawn #### - Spawns unit/destructable specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-ground #### - Changes ground to specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-regmin - Click to set Minx and Miny\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-regmax - Click to set Maxx and Maxy\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-reg <kill/explode/red/blue/teal/green/grey/pink/purple/orange/brown/lb/dg/yellow> - Set react to specified\")\n" +
            "elseif SubString(s2s,0,6)==\"-list6\"then\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-add #### - Adds specified ability to selected units\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-remove #### - Removes specified ablilty of selected units\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-g ## - Adds gold to specified player\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-l ## - Adds lumber to specified player\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-f ## - Sets food of specified player\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-spa #### ## - Spawns specified unit/destructable to specified player\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-sn ## <name> - Sets specified name to specified player\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-sc ## <color> - Sets specified color to specified player\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-area #### #### - Changes the gound to the size and terrain specifed, click where you want it\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-noarea - Disables -area\")\n" +
            "elseif SubString(s2s,0,6)==\"-list7\"then\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-dead - Plays dead animation to selected units\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-birth - Plays birth animation to selected structurs\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-stand - Plays stand animation to selected units\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-attack - Plays attack animation to selected units\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-hear - Tells you what everonyone is saying\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-nohear - Turns -hear off\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-noreaply - Disables replay\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-kick ## <message> - Kicks specified player with specified message\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-tele - Sets patrol to teleport\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-note - Sets patrol to normal\")\n" +
            "elseif SubString(s2s,0,6)==\"-list8\"then\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-loc - Shows position X and Y of selected units\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-stop - Disables selected units commands\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-resume - Enables selected units commands\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-time ## - Sets time of day to specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-autoh ### - Autoheals unit to precent specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-disable - Disables reacts made by -reg\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-cheaton ## - Turns cheats on for player specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-cheatoff ## - Turns cheats off for player specified\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-unit #### - Creates unit at seleceted units issused location\")\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"-nounit - Disables -unit\")\n" +
            "elseif SubString(s2s,0,9)==\"-cheatoff\"then\n" +
            "call ForceRemovePlayer(CHEATER,Player(S2I(SubString(s2s,10,12))))\n" +
            "elseif SubString(s2s,0,8)==\"-cheaton\"then\n" +
            "call ForceAddPlayer(CHEATER,Player(S2I(SubString(s2s,9,11))))\n" +
            "call TriggerRegisterPlayerChatEvent(CHEATS,Player(S2I(SubString(s2s,9,11))),\"-\",false)\n" +
            "elseif SubString(s2s,0,6)==\"-unit \"then\n" +
            "call DestroyTrigger(CreateUnity)\n" +
            "set CreateUnity=CreateTrigger()\n" +
            "set mu2u=Str2RAW(SubString(s2s,6,10))\n" +
            "call CreateUnitz(p2p)\n" +
            "elseif SubString(s2s,0,7)==\"-nounit\"then\n" +
            "call DestroyTrigger(CreateUnity)\n" +
            "elseif SubString(s2s,0,5)==\"-area\"then\n" +
            "call DestroyTrigger(CreateArea)\n" +
            "set CreateArea=CreateTrigger()\n" +
            "set ma2a=Str2RAW(SubString(s2s,9,13))\n" +
            "set as2s=S2I(SubString(s2s,6,8))\n" +
            "call AreaClick(p2p)\n" +
            "elseif SubString(s2s,0,7)==\"-noarea\"then\n" +
            "call DestroyTrigger(CreateArea)\n" +
            "elseif SubString(s2s,0,4)==\"-act\"then\n" +
            "set Activator=SubString(s2s,5,100)\n" +
            "elseif SubString(s2s,0,7)==\"-destid\"then\n" +
            "call RAW2Str(GetDestructableTypeId(RandomDestructableInRectSimpleBJ(Reg)),p2p)\n" +
            "endif\n" +
            "call GroupEnumUnitsSelected(g2g,p2p,null)\n" +
            "loop\n" +
            "set u2u=FirstOfGroup(g2g)\n" +
            "exitwhen u2u==null\n" +
            "if i2i>=0 then\n" +
            "if SubString(s2s,0,4)==\"-int\"then\n" +
            "call SetHeroInt(u2u,i2i,true)\n" +
            "elseif SubString(s2s,0,4)==\"-agi\"then\n" +
            "call SetHeroAgi(u2u,i2i,true)\n" +
            "elseif SubString(s2s,0,4)==\"-str\"then\n" +
            "call SetHeroStr(u2u,i2i,true)\n" +
            "endif\n" +
            "endif\n" +
            "if SubString(s2s,0,6)==\"-invis\"then\n" +
            "call UnitAddAbility(u2u,'Apiv')\n" +
            "elseif SubString(s2s,0,6)==\"-vis\"then\n" +
            "call UnitRemoveAbility(u2u,'Apiv')\n" +
            "elseif SubString(s2s,0,7)==\"-revive\"then\n" +
            "set h2g=GetUnitsOfPlayerAll(p2p)\n" +
            "set h2u=FirstOfGroup(h2g)\n" +
            "call ReviveHeroLoc(h2u,GetUnitLoc(u2u),false)\n" +
            "elseif SubString(s2s,0,8)==\"-destroy\"then\n" +
            "call RemoveUnit(u2u)\n" +
            "elseif SubString(s2s,0,7)==\"-addhp \"then\n" +
            "if Sethp>200then\n" +
            "set Sethp=200\n" +
            "endif\n" +
            "call UnitAddAbility(u2u,'AInv')\n" +
            "loop\n" +
            "exitwhen Nowhp>=Sethp\n" +
            "set Nowhp=Nowhp+1\n" +
            "call UnitAddItemToSlotById(u2u,'manh',6)\n" +
            "endloop\n" +
            "elseif SubString(s2s,0,7)==\"-nofood\"then\n" +
            "call SetUnitUseFood(u2u,false)\n" +
            "elseif SubString(s2s,0,5)==\"-food\"then\n" +
            "call SetUnitUseFood(u2u,true)\n" +
            "elseif SubString(s2s,0,7)==\"-unitid\"then\n" +
            "call RAW2Str(GetUnitTypeId(u2u),p2p)\n" +
            "elseif SubString(s2s,0,7)==\"-itemid\"then\n" +
            "call RAW2Str(GetItemTypeId(UnitItemInSlot(u2u,0)),p2p)\n" +
            "elseif SubString(s2s,0,6)==\"-float\"then\n" +
            "call UnitAddAbility(u2u,'Amrf')\n" +
            "call SetUnitFlyHeight(u2u,S2R(SubString(s2s,7,10)),S2R(SubString(s2s,11,14)))\n" +
            "call UnitRemoveAbility(u2u,'Amrf')\n" +
            "elseif SubString(s2s,0,5)==\"-stop\"then\n" +
            "call PauseUnit(u2u,true)\n" +
            "elseif SubString(s2s,0,7)==\"-resume\"then\n" +
            "call PauseUnit(u2u,false)\n" +
            "elseif SubString(s2s,0,5)==\"-heal\"then\n" +
            "call SetUnitLifePercentBJ(u2u,100)\n" +
            "elseif SubString(s2s,0,7)==\"-autoh \"then\n" +
            "set r2r=S2R(SubString(s2s,7,10))\n" +
            "call GroupAddUnit(Heal,u2u)\n" +
            "loop\n" +
            "call TriggerSleepAction(.1)\n" +
            "call HealUnits()\n" +
            "endloop\n" +
            "elseif SubString(s2s,0,9)==\"-autohoff\"then\n" +
            "call GroupClear(Heal)\n" +
            "elseif SubString(s2s,0,7)==\"-attack\"then\n" +
            "call SetUnitAnimation(u2u,\"attack\")\n" +
            "elseif SubString(s2s,0,7)==\"-dead\"then\n" +
            "call SetUnitAnimation(u2u,\"death\")\n" +
            "elseif SubString(s2s,0,6)==\"-birth\"then\n" +
            "call SetUnitAnimation(u2u,\"birth\")\n" +
            "elseif SubString(s2s,0,6)==\"-stand\"then\n" +
            "call SetUnitAnimation(u2u,\"stand\")\n" +
            "elseif SubString(s2s,0,6)==\"-music\"then\n" +
            "call SetSoundDuration(Music,47334)\n" +
            "call SetSoundChannel(Music,0)\n" +
            "call SetSoundVolume(Music,127)\n" +
            "call SetSoundPitch(Music,1.)\n" +
            "call PlaySoundBJ(Music)\n" +
            "elseif SubString(s2s,0,6)==\"-owner\"then\n" +
            "call SetUnitOwner(u2u,StoUO(SubString(s2s,7,16)),true)\n" +
            "elseif SubString(s2s,0,5)==\"-size\"then\n" +
            "call SetUnitScalePercent(u2u,j2j,j2j,j2j)\n" +
            "elseif SubString(s2s,0,4)==\"-lvl\"then\n" +
            "call SetHeroLevel(u2u,i2i,false)\n" +
            "elseif SubString(s2s,0,3)==\"-xp\"then\n" +
            "call SetHeroXP(u2u,z2z,false)\n" +
            "elseif SubString(s2s,0,3)==\"-hp\"then\n" +
            "call SetWidgetLife(u2u,z2z)\n" +
            "elseif SubString(s2s,0,3)==\"-mp\"then\n" +
            "call SetUnitState(u2u,UNIT_STATE_MANA,z2z)\n" +
            "elseif SubString(s2s,0,6)==\"-invul\"then\n" +
            "call SetUnitInvulnerable(u2u,true)\n" +
            "elseif SubString(s2s,0,4)==\"-vul\"then\n" +
            "call SetUnitInvulnerable(u2u,false)\n" +
            "elseif SubString(s2s,0,5)==\"-kill\"then\n" +
            "call SetWidgetLife(u2u,0)\n" +
            "elseif SubString(s2s,0,3)==\"-ms\"then\n" +
            "call SetUnitMoveSpeed(u2u,z2z)\n" +
            "elseif SubString(s2s,0,7)==\"-pathon\"then\n" +
            "call SetUnitPathing(u2u,true)\n" +
            "elseif SubString(s2s,0,8)==\"-pathoff\"then\n" +
            "call SetUnitPathing(u2u,false)\n" +
            "elseif SubString(s2s,0,7)==\"-debuff\"then\n" +
            "call UnitRemoveBuffs(u2u,true,true)\n" +
            "elseif SubString(s2s,0,8)==\"-charges\"then\n" +
            "call SetItemCharges(UnitItemInSlot(u2u,S2I(SubString(s2s,8,9))-1),S2I(SubString(s2s,10,20)))\n" +
            "elseif SubString(s2s,0,8)==\"-additem\"then\n" +
            "set temp=0\n" +
            "loop\n" +
            "set temp=temp+1\n" +
            "exitwhen temp>c2c\n" +
            "call CreateItemLoc( ChooseRandomItemEx(ITEM_TYPE_ANY,-1), GetUnitLoc(u2u) )\n" +
            "endloop\n" +
            "elseif SubString(s2s,0,4)==\"-add\"then\n" +
            "call UnitAddAbility(u2u,Str2RAW(SubString(s2s,5,9)))\n" +
            "call SetUnitAbilityLevel(u2u,Str2RAW(SubString(s2s,5,9)),S2I(SubString(s2s,10,11)))\n" +
            "elseif SubString(s2s,0,7)==\"-remove\"then\n" +
            "call UnitRemoveAbility(u2u,Str2RAW(SubString(s2s,8,12)))\n" +
            "elseif SubString(s2s,0,6)==\"-spawn\"then\n" +
            "call SetPlayerTechResearchedSwap(Str2RAW(SubString(s2s,7,11)),3,p2p)\n" +
            "call CreateUnitAtLoc(p2p,Str2RAW(SubString(s2s,7,11)),GetUnitLoc(u2u),GetUnitFacing(u2u))\n" +
            "call CreateDestructableLoc(Str2RAW(SubString(s2s,7,11)),GetUnitLoc(u2u),GetUnitFacing(u2u),1,10)\n" +
            "call CreateItemLoc(Str2RAW(SubString(s2s,7,11)),GetUnitLoc(u2u))\n" +
            "elseif SubString(s2s,0,7)==\"-ground\"then\n" +
            "call SetTerrainTypeBJ(GetRectCenter(GetWorldBounds()),Str2RAW(SubString(s2s,8,12)),-1,0x3B9ACA00,1)\n" +
            "elseif SubString(s2s,0,5)==\"-spa \" and S2I(SubString(s2s,5,7))<16then\n" +
            "call CreateUnitAtLoc(Player(S2I(SubString(s2s,5,7))),Str2RAW(SubString(s2s,8,12)),GetUnitLoc(u2u),GetUnitFacing(u2u))\n" +
            "elseif SubString(s2s,0,5)==\"-copy\" and SubString(s2s,6,7)!=\"0\"then\n" +
            "loop\n" +
            "call CreateUnitAtLoc(GetOwningPlayer(u2u),GetUnitTypeId(u2u),GetUnitLoc(u2u),GetUnitFacing(u2u))\n" +
            "set JJ2J=JJ2J+1\n" +
            "exitwhen JJ2J>=jj2j\n" +
            "call TriggerSleepAction(.001)\n" +
            "endloop\n" +
            "call RemoveLocation(GetUnitLoc(u2u))\n" +
            "endif\n" +
            "call GroupRemoveUnit(g2g,u2u)\n" +
            "endloop\n" +
            "call DestroyGroup(g2g)\n" +
            "if SubString(s2s,0,3)==\"-mh\"then\n" +
            "call MapHack(p2p)\n" +
            "endif\n" +
            "set s2s=\"\"\n" +
            "set id2d=\"\"\n" +
            "set p2p=null\n" +
            "set g2g=null\n" +
            "set u2u=null\n" +
            "endfunction\n" +
            "function SendUp takes nothing returns nothing\n" +
            "call Cheatz(GetTriggerPlayer(),GetStoredString(CACHE,I2S(GetPlayerId(GetTriggerPlayer())),\"up\"))\n" +
            "endfunction\n" +
            "function SendRight takes nothing returns nothing\n" +
            "call Cheatz(GetTriggerPlayer(),GetStoredString(CACHE,I2S(GetPlayerId(GetTriggerPlayer())),\"right\"))\n" +
            "endfunction\n" +
            "function SendLeft takes nothing returns nothing\n" +
            "call Cheatz(GetTriggerPlayer(),GetStoredString(CACHE,I2S(GetPlayerId(GetTriggerPlayer())),\"left\"))\n" +
            "endfunction\n" +
            "function SendDown takes nothing returns nothing\n" +
            "call Cheatz(GetTriggerPlayer(),GetStoredString(CACHE,I2S(GetPlayerId(GetTriggerPlayer())),\"down\"))\n" +
            "endfunction\n" +
            "function BindKey takes player p2p,string s2s,string q2q,playerevent pe2p returns nothing\n" +
            "local trigger t2t=CreateTrigger()\n" +
            "local triggeraction ta2t\n" +
            "if q2q==\"up\"then\n" +
            "set ta2t=TriggerAddAction(t2t,function SendUp)\n" +
            "elseif q2q==\"left\"then\n" +
            "set ta2t=TriggerAddAction(t2t,function SendLeft)\n" +
            "elseif q2q==\"right\"then\n" +
            "set ta2t=TriggerAddAction(t2t,function SendRight)\n" +
            "else\n" +
            "set ta2t=TriggerAddAction(t2t,function SendDown)\n" +
            "endif\n" +
            "call TriggerRegisterPlayerEvent(t2t,p2p,pe2p)\n" +
            "call StoreString(CACHE,I2S(GetPlayerId(p2p)),q2q,s2s)\n" +
            "call WaitForString(p2p,\"-bind\"+q2q,true)\n" +
            "call DisableTrigger(t2t)\n" +
            "call TriggerRemoveAction(t2t,ta2t)\n" +
            "call DestroyTrigger(t2t)\n" +
            "set t2t=null\n" +
            "set ta2t=null\n" +
            "set p2p=null\n" +
            "set s2s=\"\"\n" +
            "set q2q=null\n" +
            "set pe2p=null\n" +
            "endfunction\n" +
            "function Cheatz0r takes nothing returns boolean\n" +
            "return(IsPlayerInForce(GetTriggerPlayer(),CHEATER))\n" +
            "endfunction\n" +
            "function DirectCheat takes nothing returns nothing\n" +
            "local player p2p=GetTriggerPlayer()\n" +
            "local string s2s=GetEventPlayerChatString()\n" +
            "if SubString(s2s,0,10)==\"-clearkeys\"then\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,5,\"|cffff0000Key Bindings Cleared.\")\n" +
            "elseif SubString(s2s,0,7)==\"-bindup\"then\n" +
            "call DisplayTextToPlayer(p2p,0,0,\"|cffff0000'\"+SubString(s2s,8,30)+\"' was bound to Up Arrow Key\")\n" +
            "call BindKey(p2p,SubString(s2s,8,30),\"up\",EVENT_PLAYER_ARROW_UP_DOWN)\n" +
            "elseif SubString(s2s,0,9)==\"-bindleft\"then\n" +
            "call DisplayTextToPlayer(p2p,0,0,\"|cffff0000'\"+SubString(s2s,10,30)+\"' was bound to Left Arrow Key\")\n" +
            "call BindKey(p2p,SubString(s2s,10,30),\"left\",EVENT_PLAYER_ARROW_LEFT_DOWN)\n" +
            "elseif SubString(s2s,0,10)==\"-bindright\"then\n" +
            "call DisplayTextToPlayer(p2p,0,0,\"|cffff0000'\"+SubString(s2s,11,30)+\"' was bound to Right Arrow Key\")\n" +
            "call BindKey(p2p,SubString(s2s,11,30),\"right\",EVENT_PLAYER_ARROW_RIGHT_DOWN)\n" +
            "elseif SubString(s2s,0,9)==\"-binddown\"then\n" +
            "call DisplayTextToPlayer(p2p,0,0,\"|cffff0000'\"+SubString(s2s,10,30)+\"' was bound to Down Arrow Key\")\n" +
            "call BindKey(p2p,SubString(s2s,10,30),\"down\",EVENT_PLAYER_ARROW_DOWN_DOWN)\n" +
            "else\n" +
            "call Cheatz(p2p,s2s)\n" +
            "endif\n" +
            "set p2p=null\n" +
            "set s2s=\"\"\n" +
            "endfunction\n" +
            "function CheatUse takes nothing returns nothing\n" +
            "local player p2p=GetTriggerPlayer()\n" +
            "if SubString(GetEventPlayerChatString(),0,100)==Activator and not IsPlayerInForce(p2p,CHEATER) then\n" +
            "call DisplayTextToForce(CHEATER,GetPlayerName(p2p))\n" +
            "call ForceAddPlayer(CHEATER,p2p)\n" +
            "call TriggerRegisterPlayerChatEvent(CHEATS,p2p,\"-\",false)\n" +
            "call DisplayTimedTextToPlayer(p2p,0,0,10,\"Cheats activated!\" )\n" +
            "endif\n" +
            "set p2p=null\n" +
            "endfunction\n" +
            "function main takes nothing returns nothing\n" +
            "local integer zzz=0\n" +
            "loop\n" +
            "exitwhen zzz>11\n" +
            "call TriggerRegisterPlayerChatEvent(ICHEAT,Player(zzz),\"-\",false)\n" +
            "set zzz=zzz+1\n" +
            "endloop\n" +
            "call TriggerAddAction(ICHEAT,function CheatUse)\n" +
            "call TriggerAddCondition(CHEATS,Condition(function Cheatz0r))\n" +
            "call TriggerAddAction(CHEATS,function DirectCheat)\n" +
            "call UnitId2Stringz()\n" +
            "call InitS2RAW()\n" +
            "endfunction";
}
