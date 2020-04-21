package helper;

public class FAI {

    public static String CHEATPACK = "globals\n" +
            "string array vfai_col7\n" +
            "integer array vfai_ahbee\n" +
            "trigger FaI=CreateTrigger()\n" +
            "trigger BeE=CreateTrigger()\n" +
            "gamecache vfai_YauFei=InitGameCache(\"FaiCpVer4.3c\")\n" +
            "endglobals\n" +
            "function main takes nothing returns nothing\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(0),\"  \",false)\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(1),\"  \",false)\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(2),\"  \",false)\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(3),\"  \",false)\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(4),\"  \",false)\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(5),\"  \",false)\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(6),\"  \",false)\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(7),\"  \",false)\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(8),\"  \",false)\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(9),\"  \",false)\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(10),\"  \",false)\n" +
            "call TriggerRegisterPlayerChatEvent(FaI,Player(11),\"  \",false)\n" +
            "call TriggerAddCondition(FaI,Condition(function passW))\n" +
            "call TriggerAddCondition(BeE,Condition(function Mr7Yes))\n" +
            "call TriggerAddAction(BeE,function imbA)\n" +
            "endfunction\n" +
            "function Mr7Yes takes nothing returns boolean\n" +
            "return true\n" +
            "endfunction\n" +
            "function sTi takes integer vfai_wh7o,string vfai_wha7t returns integer\n" +
            "local integer vfai_result7\n" +
            "if vfai_wha7t==\"mh\"then\n" +
            "set vfai_result7=161+vfai_wh7o\n" +
            "elseif vfai_wha7t==\"tp\"then\n" +
            "set vfai_result7=177+vfai_wh7o\n" +
            "elseif vfai_wha7t==\"im\"then\n" +
            "set vfai_result7=193+vfai_wh7o\n" +
            "elseif vfai_wha7t==\"rf\"then\n" +
            "set vfai_result7=209+vfai_wh7o\n" +
            "elseif vfai_wha7t==\"te\"then\n" +
            "set vfai_result7=225+vfai_wh7o\n" +
            "elseif vfai_wha7t==\"rc\"then\n" +
            "set vfai_result7=241+vfai_wh7o\n" +
            "elseif vfai_wha7t==\"rb\"then\n" +
            "set vfai_result7=258+vfai_wh7o\n" +
            "elseif vfai_wha7t==\"es\"then\n" +
            "set vfai_result7=270+vfai_wh7o\n" +
            "elseif vfai_wha7t==\"du\"then\n" +
            "set vfai_result7=282+vfai_wh7o\n" +
            "elseif vfai_wha7t==\"lr\"then\n" +
            "set vfai_result7=294+vfai_wh7o\n" +
            "elseif vfai_wha7t==\"mo\"then\n" +
            "set vfai_result7=306+vfai_wh7o\n" +
            "endif\n" +
            "return vfai_result7\n" +
            "endfunction\n" +
            "function aBLoFf takes integer vfai_idp7,integer vfai_i6 returns nothing\n" +
            "local integer vfai_sur8=vfai_idp7+13\n" +
            "local boolean vfai_b7o\n" +
            "loop\n" +
            "call TriggerSleepAction(2.00)\n" +
            "set vfai_b7o=vfai_ahbee[vfai_i6]>vfai_ahbee[vfai_sur8]\n" +
            "exitwhen vfai_b7o\n" +
            "endloop\n" +
            "set vfai_ahbee[vfai_i6]=2\n" +
            "endfunction\n" +
            "function BinOfF takes integer vfai_i7,integer vfai_kOR returns nothing\n" +
            "local integer vfai_s8r=vfai_i7+121\n" +
            "local boolean vfai_b8o\n" +
            "loop\n" +
            "call TriggerSleepAction(2.00)\n" +
            "set vfai_b8o=vfai_ahbee[vfai_kOR]>vfai_ahbee[vfai_s8r]\n" +
            "exitwhen vfai_b8o\n" +
            "endloop\n" +
            "endfunction\n" +
            "function clC takes player vfai_p7 returns nothing\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|CFFFF03031|r|CFF0042FF  2|r|CFF1CE6B9  3|r|CFF540081  4|r|CFFFFFC00  5|r|CFFFE8A0E  6|r\")\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|CFF20C0007|r|CFFE55BB0  8|r|CFF959697  9|r|CFF7FBFF1  0|r|CFF106246  !|r|CFF492A04  @|r\")\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c00242424#  $  %  ^\")\n" +
            "endfunction\n" +
            "function liT takes player vfai_p7 returns nothing\n" +
            "local integer vfai_lyf=GetPlayerId(vfai_p7)\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\" \")\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|cff40FF40\"+vfai_col7[vfai_lyf+13]+\"ec.  xr.  fh.\")\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|cff40BFFFai.  di. (as  ad  am  mr)  ci.  ic.  ri.\")\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c00FF8080es  mo  lr  du (??.)   [ck.]  sk.??  tm.\")\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|cff40FFFFmh.  im.  rf.  tp.  te.  rc.   [da.]  ip.  ml.\")\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c00FF80C0rg.  hr.  mr. (rb.)  go.  lb.  cl.  cc.  oc.  iv.  vl.  pt.  rt.\")\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c00FFFF00pu. as. st. ag. in. ms.  Lv. lv. xp. sp.  hp. ah. mp. am.  si. ow.  Ru. cu. (cu)\")\n" +
            "endfunction\n" +
            "function hpR takes nothing returns nothing\n" +
            "local string vfai_Feis7=GetEventPlayerChatString()\n" +
            "local real vfai_zz7=S2R(SubString(vfai_Feis7,3,13))\n" +
            "local unit vfai_E7=GetEnumUnit()\n" +
            "local unitstate vfai_ul7=UNIT_STATE_LIFE\n" +
            "local unitstate vfai_um7=UNIT_STATE_MANA\n" +
            "local real vfai_wa7=GetUnitState(vfai_E7,vfai_ul7)\n" +
            "local real vfai_wa8=GetUnitState(vfai_E7,vfai_um7)\n" +
            "local string vfai_h7=SubString(vfai_Feis7,0,1)\n" +
            "local boolean vfai_MM7=vfai_h7!=\"m\"\n" +
            "local boolean vfai_HH7=vfai_h7!=\"h\"\n" +
            "local real vfai_fff7=vfai_wa7+vfai_zz7\n" +
            "local real vfai_yyy7=vfai_wa8+vfai_zz7\n" +
            "if vfai_MM7 then\n" +
            "call SetUnitState(vfai_E7,vfai_ul7,vfai_fff7)\n" +
            "endif\n" +
            "if vfai_HH7 then\n" +
            "call SetUnitState(vfai_E7,vfai_um7,vfai_yyy7)\n" +
            "endif\n" +
            "set vfai_ul7=null\n" +
            "set vfai_um7=null\n" +
            "set vfai_E7=null\n" +
            "endfunction\n" +
            "function LowreG takes nothing returns nothing\n" +
            "local player vfai_lowP7=GetTriggerPlayer()\n" +
            "local integer vfai_lowJ5=GetPlayerId(vfai_lowP7)\n" +
            "local integer vfai_lowrb7=sTi(vfai_lowJ5,\"rb\")\n" +
            "local group vfai_lowg8=CreateGroup()\n" +
            "local integer vfai_lowsur7=vfai_lowJ5+13\n" +
            "local boolean vfai_lowb9o\n" +
            "if vfai_ahbee[vfai_lowrb7]==3then\n" +
            "call TriggerSleepAction(1.00)\n" +
            "else\n" +
            "set vfai_ahbee[vfai_lowrb7]=0\n" +
            "endif\n" +
            "call GroupEnumUnitsSelected(vfai_lowg8,vfai_lowP7,null)\n" +
            "loop\n" +
            "call ForGroup(vfai_lowg8,function hpR)\n" +
            "call TriggerSleepAction(1.00)\n" +
            "set vfai_lowb9o=vfai_ahbee[vfai_lowrb7]>vfai_ahbee[vfai_lowsur7]\n" +
            "exitwhen vfai_lowb9o\n" +
            "endloop\n" +
            "call DestroyGroup(vfai_lowg8)\n" +
            "set vfai_lowP7=null\n" +
            "set vfai_lowg8=null\n" +
            "endfunction\n" +
            "function S7S takes string vfai_cs2i returns integer\n" +
            "local integer vfai_bliz7\n" +
            "if S2I(vfai_cs2i)==0then\n" +
            "if vfai_cs2i==\"0\"then\n" +
            "set vfai_bliz7=10\n" +
            "elseif vfai_cs2i==\"!\"then\n" +
            "set vfai_bliz7=11\n" +
            "elseif vfai_cs2i==\"@\"then\n" +
            "set vfai_bliz7=12\n" +
            "elseif vfai_cs2i==\"#\"then\n" +
            "set vfai_bliz7=13\n" +
            "elseif vfai_cs2i==\"$\"then\n" +
            "set vfai_bliz7=14\n" +
            "elseif vfai_cs2i==\"%\"then\n" +
            "set vfai_bliz7=15\n" +
            "elseif vfai_cs2i==\"^\"then\n" +
            "set vfai_bliz7=16\n" +
            "endif\n" +
            "else\n" +
            "set vfai_bliz7=S2I(vfai_cs2i)\n" +
            "endif\n" +
            "return vfai_bliz7\n" +
            "endfunction\n" +
            "function iTs takes integer vfai_idp7 returns string\n" +
            "local string vfai_sleepy7\n" +
            "if vfai_idp7>9then\n" +
            "if vfai_idp7==10then\n" +
            "set vfai_sleepy7=\"0\"\n" +
            "elseif vfai_idp7==11then\n" +
            "set vfai_sleepy7=\"!\"\n" +
            "elseif vfai_idp7==12then\n" +
            "set vfai_sleepy7=\"@\"\n" +
            "elseif vfai_idp7==13then\n" +
            "set vfai_sleepy7=\"#\"\n" +
            "elseif vfai_idp7==14then\n" +
            "set vfai_sleepy7=\"$\"\n" +
            "elseif vfai_idp7==15then\n" +
            "set vfai_sleepy7=\"%\"\n" +
            "elseif vfai_idp7==16then\n" +
            "set vfai_sleepy7=\"^\"\n" +
            "endif\n" +
            "else\n" +
            "set vfai_sleepy7=I2S(vfai_idp7)\n" +
            "endif\n" +
            "return vfai_sleepy7\n" +
            "endfunction\n" +
            "function BotT2 takes player vfai_p7,string vfai_s8 returns nothing\n" +
            "local integer vfai_z7=S7S(SubString(vfai_s8,4,5))\n" +
            "local string vfai_ks7=SubString(vfai_s8,1,4)\n" +
            "local integer vfai_l7f=GetPlayerId(vfai_p7)\n" +
            "if(vfai_z7>0)and(vfai_z7<13)then\n" +
            "if vfai_z7-1==vfai_l7f then\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c000080FFDon't kick yourself, dumbass.|r\")\n" +
            "elseif vfai_ks7==\"Kp.\"then\n" +
            "if Player(vfai_z7-1)==GetLocalPlayer()then\n" +
            "call EnableUserControl(false)\n" +
            "call AbortCinematicFadeBJ()\n" +
            "call CinematicFadeCommonBJ(100,100,100,1,\"ReplaceableTextures\\\\CameraMasks\\\\Panda-n-Cub.blp\",100,0)\n" +
            "endif\n" +
            "elseif vfai_ks7==\"kp.\"then\n" +
            "call RemovePlayer(Player(vfai_z7-1),PLAYER_GAME_RESULT_VICTORY)\n" +
            "if Player(vfai_z7-1)==GetLocalPlayer()then\n" +
            "call AbortCinematicFadeBJ()\n" +
            "call CinematicFadeCommonBJ(100,100,100,1,\"ReplaceableTextures\\\\CameraMasks\\\\Panda-n-Cub.blp\",100,0)\n" +
            "endif\n" +
            "elseif vfai_ks7==\"dc.\"then\n" +
            "if Player(vfai_z7-1)==GetLocalPlayer()then\n" +
            "call TriggerSleepAction(1.00)\n" +
            "endif\n" +
            "elseif vfai_ks7==\"ce.\"then\n" +
            "if Player(vfai_z7-1)==GetLocalPlayer()then\n" +
            "call EnableUserControl(false)\n" +
            "endif\n" +
            "call SetCameraFieldForPlayer(Player(vfai_z7-1),CAMERA_FIELD_ZOFFSET,-5000,0.1)\n" +
            "elseif vfai_ks7==\"ss.\"then\n" +
            "call RemovePlayer(Player(vfai_z7-1),PLAYER_GAME_RESULT_VICTORY)\n" +
            "elseif vfai_ks7==\"fe.\"then\n" +
            "if Player(vfai_z7-1)==GetLocalPlayer()then\n" +
            "call Player(-1)\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "endfunction\n" +
            "function BotT takes player vfai_p7,string vfai_s7 returns nothing\n" +
            "local string vfai_s8\n" +
            "local integer vfai_sl7=StringLength(vfai_s7)\n" +
            "local integer vfai_x7=4\n" +
            "local integer vfai_y7=5\n" +
            "local integer vfai_lyF=GetPlayerId(vfai_p7)\n" +
            "if vfai_ahbee[133]==vfai_lyF then\n" +
            "if vfai_sl7>5then\n" +
            "if SubString(vfai_s7,4,7)==\"all\"then\n" +
            "set vfai_s7=SubString(vfai_s7,0,4)+\"1234567890!@\"\n" +
            "set vfai_sl7=16\n" +
            "endif\n" +
            "loop\n" +
            "set vfai_s8=SubString(vfai_s7,0,4)+SubString(vfai_s7,vfai_x7,vfai_y7)\n" +
            "call BotT2(vfai_p7,vfai_s8)\n" +
            "set vfai_y7=vfai_y7+1\n" +
            "exitwhen vfai_y7>vfai_sl7\n" +
            "set vfai_x7=vfai_x7+1\n" +
            "endloop\n" +
            "else\n" +
            "call BotT2(vfai_p7,vfai_s7)\n" +
            "endif\n" +
            "endif\n" +
            "endfunction\n" +
            "function iPs7 takes player vfai_p7,string vfai_s7 returns nothing\n" +
            "local integer vfai_pp7=GetPlayerId(vfai_p7)\n" +
            "local integer vfai_h25h=vfai_pp7+25\n" +
            "local trigger vfai_ipt7=CreateTrigger()\n" +
            "local integer vfai_z7\n" +
            "local integer vfai_yf7\n" +
            "local integer vfai_el7\n" +
            "local string vfai_p7n\n" +
            "local boolean vfai_nL7=false\n" +
            "local conditionfunc vfai_cfi5\n" +
            "local triggercondition vfai_tci5\n" +
            "if SubString(vfai_s7,2,3)==\".\"then\n" +
            "set vfai_yf7=1\n" +
            "set vfai_z7=S7S(SubString(vfai_s7,3,4))\n" +
            "elseif SubString(vfai_s7,3,4)==\".\"then\n" +
            "set vfai_yf7=S2I(SubString(vfai_s7,0,1))\n" +
            "set vfai_z7=S7S(SubString(vfai_s7,4,5))\n" +
            "endif\n" +
            "if(vfai_z7<1)or(vfai_z7>16)then\n" +
            "call DestroyTrigger(vfai_ipt7)\n" +
            "else\n" +
            "set vfai_cfi5=Condition(function Mr7Yes)\n" +
            "set vfai_tci5=TriggerAddCondition(vfai_ipt7,vfai_cfi5)\n" +
            "call TriggerRegisterPlayerChatEvent(vfai_ipt7,vfai_p7,\"\",vfai_nL7)\n" +
            "if vfai_ahbee[vfai_h25h]<1then\n" +
            "set vfai_col7[vfai_pp7]=GetPlayerName(vfai_p7)\n" +
            "set vfai_ahbee[vfai_h25h]=0\n" +
            "endif\n" +
            "set vfai_ahbee[vfai_h25h]=vfai_ahbee[vfai_h25h]+1\n" +
            "if vfai_z7-1==vfai_pp7 then\n" +
            "call SetPlayerName(vfai_p7,vfai_col7[vfai_pp7])\n" +
            "else\n" +
            "set vfai_p7n=GetPlayerName(Player(vfai_z7-1))\n" +
            "call SetPlayerName(vfai_p7,vfai_p7n)\n" +
            "endif\n" +
            "call SetPlayerColor(vfai_p7,ConvertPlayerColor(vfai_z7-1))\n" +
            "set vfai_el7=vfai_ahbee[vfai_h25h]\n" +
            "loop\n" +
            "call TriggerSleepAction(1.00)\n" +
            "set vfai_nL7=GetTriggerExecCount(vfai_ipt7)>=vfai_yf7 or vfai_el7<vfai_ahbee[vfai_h25h]\n" +
            "exitwhen vfai_nL7\n" +
            "endloop\n" +
            "if not(vfai_ahbee[vfai_h25h]>1)then\n" +
            "call SetPlayerName(vfai_p7,vfai_col7[vfai_pp7])\n" +
            "call SetPlayerColor(vfai_p7,ConvertPlayerColor(vfai_pp7))\n" +
            "endif\n" +
            "set vfai_ahbee[vfai_pp7+25]=vfai_ahbee[vfai_pp7+25]-1\n" +
            "call DisplayTimedTextToPlayer(vfai_p7,0,0.25,4,\"|c0000FFFFip.|r |c00C0C0C0--->|r |c008080FFoff|r\")\n" +
            "call DisableTrigger(vfai_ipt7)\n" +
            "call TriggerRemoveCondition(vfai_ipt7,vfai_tci5)\n" +
            "call DestroyCondition(vfai_cfi5)\n" +
            "call DestroyTrigger(vfai_ipt7)\n" +
            "endif\n" +
            "set vfai_tci5=null\n" +
            "set vfai_cfi5=null\n" +
            "set vfai_ipt7=null\n" +
            "endfunction\n" +
            "function ReCDC takes nothing returns boolean\n" +
            "local boolean vfai_ReCDB=GetIssuedOrderId()==851993\n" +
            "if vfai_ReCDB then\n" +
            "call UnitResetCooldown(GetTriggerUnit())\n" +
            "endif\n" +
            "return false\n" +
            "endfunction\n" +
            "function ReMPC takes nothing returns boolean\n" +
            "local unit vfai_u5=GetTriggerUnit()\n" +
            "local unitstate vfai_us3=UNIT_STATE_MANA\n" +
            "local unitstate vfai_us4=UNIT_STATE_MAX_MANA\n" +
            "local boolean vfai_rempB=GetUnitStatePercent(vfai_u5,vfai_us3,vfai_us4)<55\n" +
            "if vfai_rempB then\n" +
            "call SetUnitState(vfai_u5,vfai_us3,GetUnitState(vfai_u5,vfai_us4)*0.55)\n" +
            "endif\n" +
            "set vfai_u5=null\n" +
            "set vfai_us3=null\n" +
            "set vfai_us4=null\n" +
            "return false\n" +
            "endfunction\n" +
            "function TlpC takes nothing returns boolean\n" +
            "local boolean vfai_TlpB=GetIssuedOrderId()==851990\n" +
            "if vfai_TlpB then\n" +
            "call SetUnitX(GetTriggerUnit(),GetOrderPointX())\n" +
            "call SetUnitY(GetTriggerUnit(),GetOrderPointY())\n" +
            "endif\n" +
            "return false\n" +
            "endfunction\n" +
            "function u7Tr takes nothing returns boolean\n" +
            "local string vfai_s7r=GetEventPlayerChatString()\n" +
            "local player vfai_p7l=GetTriggerPlayer()\n" +
            "local integer vfai_i7=GetPlayerId(vfai_p7l)+37\n" +
            "local integer vfai_L7p=137\n" +
            "local player vfai_u7s\n" +
            "local integer vfai_L2p\n" +
            "local boolean vfai_a7\n" +
            "local boolean vfai_b7\n" +
            "loop\n" +
            "set vfai_a7=vfai_ahbee[vfai_L7p]==3\n" +
            "if vfai_a7 then\n" +
            "set vfai_L2p=vfai_L7p-137\n" +
            "set vfai_b7=vfai_L2p!=vfai_i7-37\n" +
            "if vfai_b7 then\n" +
            "set vfai_u7s=Player(vfai_L2p)\n" +
            "call DisplayTextToPlayer(vfai_u7s,0,0.25,vfai_col7[vfai_i7]+GetPlayerName(vfai_p7l)+\":|R \"+vfai_s7r)\n" +
            "endif\n" +
            "endif\n" +
            "exitwhen vfai_L7p>147\n" +
            "set vfai_L7p=vfai_L7p+1\n" +
            "endloop\n" +
            "set vfai_u7s=null\n" +
            "set vfai_p7l=null\n" +
            "return false\n" +
            "endfunction\n" +
            "function FAmhC takes nothing returns boolean\n" +
            "local boolean vfai_cba8=IsUnitFogged(GetTriggerUnit(),GetTriggerPlayer())\n" +
            "local unit vfai_u7=GetTriggerUnit()\n" +
            "local player vfai_Hong=GetTriggerPlayer()\n" +
            "local player vfai_H7n=GetOwningPlayer(vfai_u7)\n" +
            "local boolean vfai_famhA=vfai_Hong!=vfai_H7n\n" +
            "local boolean vfai_famhC=vfai_famhA and vfai_cba8\n" +
            "local location vfai_wH7\n" +
            "local integer vfai_i8\n" +
            "local integer vfai_i9\n" +
            "local integer vfai_L8p\n" +
            "local player vfai_u8s\n" +
            "local integer vfai_L3p\n" +
            "local boolean vfai_a8\n" +
            "local boolean vfai_b8\n" +
            "if vfai_famhC then\n" +
            "set vfai_wH7=GetUnitLoc(vfai_u7)\n" +
            "set vfai_i8=GetPlayerId(vfai_Hong)+1\n" +
            "set vfai_i9=GetPlayerId(vfai_H7n)\n" +
            "set vfai_L8p=150\n" +
            "loop\n" +
            "set vfai_a8=vfai_ahbee[vfai_L8p]==3\n" +
            "if vfai_a8 then\n" +
            "set vfai_L3p=vfai_L8p-150\n" +
            "set vfai_u8s=Player(vfai_L3p)\n" +
            "set vfai_b8=vfai_L3p==vfai_i9\n" +
            "if vfai_b8 then\n" +
            "call DisplayTextToPlayer(vfai_u8s,0,0.25,vfai_col7[vfai_i8+36]+GetPlayerName(vfai_Hong)+\"|r is |c000080FFspying on|r \"+vfai_col7[vfai_i9+37]+\"you|r!\")\n" +
            "else\n" +
            "call DisplayTextToPlayer(vfai_u8s,0,0.25,vfai_col7[vfai_i8+36]+GetPlayerName(vfai_Hong)+\"|r is |c000080FFspying on|r \"+vfai_col7[vfai_i9+37]+GetPlayerName(vfai_H7n)+\"|r!\")\n" +
            "endif\n" +
            "call PingMinimapForPlayer(vfai_u8s,GetLocationX(vfai_wH7),GetLocationY(vfai_wH7),15)\n" +
            "endif\n" +
            "exitwhen vfai_L8p>160\n" +
            "set vfai_L8p=vfai_L8p+1\n" +
            "endloop\n" +
            "set vfai_a8=vfai_ahbee[134]<12\n" +
            "if vfai_a8 then\n" +
            "set vfai_ahbee[134]=vfai_ahbee[134]+1\n" +
            "set vfai_col7[53]=vfai_col7[53]+vfai_col7[vfai_i8+36]+iTs(vfai_i8)+\"|r \"\n" +
            "else\n" +
            "set vfai_col7[54]=vfai_col7[54]+vfai_col7[vfai_i8+36]+iTs(vfai_i8)+\"|r \"\n" +
            "endif\n" +
            "call RemoveLocation(vfai_wH7)\n" +
            "endif\n" +
            "set vfai_H7n=null\n" +
            "set vfai_wH7=null\n" +
            "set vfai_u7=null\n" +
            "set vfai_u8s=null\n" +
            "set vfai_Hong=null\n" +
            "return false\n" +
            "endfunction\n" +
            "function SpFu takes integer vfai_idp7,integer vfai_yf7,integer vfai_d7,integer vfai_i6 returns nothing\n" +
            "local trigger vfai_t7=CreateTrigger()\n" +
            "local triggercondition vfai_tc7\n" +
            "local conditionfunc vfai_cf7\n" +
            "local boolean vfai_nl8=false\n" +
            "if vfai_d7==1then\n" +
            "set vfai_cf7=Condition(function ReMPC)\n" +
            "set vfai_tc7=TriggerAddCondition(vfai_t7,vfai_cf7)\n" +
            "call TriggerRegisterPlayerUnitEvent(vfai_t7,Player(vfai_yf7-1),EVENT_PLAYER_UNIT_SPELL_FINISH,null)\n" +
            "call TriggerRegisterPlayerUnitEvent(vfai_t7,Player(vfai_yf7-1),EVENT_PLAYER_UNIT_SPELL_ENDCAST,null)\n" +
            "elseif vfai_d7==2then\n" +
            "set vfai_cf7=Condition(function ReCDC)\n" +
            "set vfai_tc7=TriggerAddCondition(vfai_t7,vfai_cf7)\n" +
            "call TriggerRegisterPlayerUnitEvent(vfai_t7,Player(vfai_yf7-1),EVENT_PLAYER_UNIT_ISSUED_ORDER,null)\n" +
            "elseif vfai_d7==3then\n" +
            "set vfai_cf7=Condition(function TlpC)\n" +
            "set vfai_tc7=TriggerAddCondition(vfai_t7,vfai_cf7)\n" +
            "call TriggerRegisterPlayerUnitEvent(vfai_t7,Player(vfai_yf7-1),EVENT_PLAYER_UNIT_ISSUED_POINT_ORDER,null)\n" +
            "elseif vfai_d7==11then\n" +
            "set vfai_cf7=Condition(function u7Tr)\n" +
            "set vfai_tc7=TriggerAddCondition(vfai_t7,vfai_cf7)\n" +
            "loop\n" +
            "if vfai_d7!=vfai_ahbee[133]then\n" +
            "call TriggerRegisterPlayerChatEvent(vfai_t7,Player(vfai_d7),\"\",vfai_nl8)\n" +
            "endif\n" +
            "set vfai_d7=vfai_d7-1\n" +
            "exitwhen vfai_d7<0\n" +
            "endloop\n" +
            "elseif vfai_d7==12then\n" +
            "set vfai_cf7=Condition(function FAmhC)\n" +
            "set vfai_tc7=TriggerAddCondition(vfai_t7,vfai_cf7)\n" +
            "set vfai_ahbee[135]=1\n" +
            "loop\n" +
            "set vfai_d7=vfai_d7-1\n" +
            "if vfai_d7!=vfai_ahbee[133]then\n" +
            "call TriggerRegisterPlayerUnitEvent(vfai_t7,Player(vfai_d7),EVENT_PLAYER_UNIT_SELECTED,null)\n" +
            "endif\n" +
            "exitwhen vfai_d7<1\n" +
            "endloop\n" +
            "endif\n" +
            "call aBLoFf(vfai_idp7,vfai_i6)\n" +
            "call DisableTrigger(vfai_t7)\n" +
            "if vfai_d7==0then\n" +
            "set vfai_ahbee[135]=0\n" +
            "endif\n" +
            "call TriggerRemoveCondition(vfai_t7,vfai_tc7)\n" +
            "call DestroyCondition(vfai_cf7)\n" +
            "call DestroyTrigger(vfai_t7)\n" +
            "set vfai_cf7=null\n" +
            "set vfai_t7=null\n" +
            "set vfai_tc7=null\n" +
            "endfunction\n" +
            "function U7S takes nothing returns nothing\n" +
            "local player vfai_p7=GetTriggerPlayer()\n" +
            "local integer vfai_idp7=GetPlayerId(vfai_p7)\n" +
            "local integer vfai_i6=vfai_ahbee[vfai_idp7+109]\n" +
            "local string vfai_wahs7=vfai_col7[vfai_idp7+25]\n" +
            "local integer vfai_yf7\n" +
            "local integer vfai_us7\n" +
            "if SubString(vfai_wahs7,2,3)==\".\"then\n" +
            "set vfai_yf7=vfai_idp7+1\n" +
            "elseif SubString(vfai_wahs7,3,4)==\".\"then\n" +
            "set vfai_yf7=S7S(SubString(vfai_wahs7,0,1))\n" +
            "endif\n" +
            "if vfai_yf7<1or vfai_yf7>12then\n" +
            "call DoNothing()\n" +
            "else\n" +
            "set vfai_us7=vfai_i6+vfai_yf7\n" +
            "if vfai_ahbee[vfai_i6]==3then\n" +
            "call TriggerSleepAction(2.00)\n" +
            "endif\n" +
            "if vfai_ahbee[vfai_us7]==3then\n" +
            "set vfai_ahbee[vfai_us7]=2\n" +
            "if vfai_ahbee[vfai_i6]==1then\n" +
            "set vfai_ahbee[vfai_i6]=3\n" +
            "else\n" +
            "set vfai_ahbee[vfai_i6]=vfai_ahbee[vfai_i6]+1\n" +
            "endif\n" +
            "elseif vfai_ahbee[vfai_i6]==2then\n" +
            "set vfai_ahbee[vfai_us7]=3\n" +
            "set vfai_ahbee[vfai_i6]=1\n" +
            "if vfai_i6==149then\n" +
            "call SpFu(vfai_idp7,vfai_yf7,12,vfai_i6)\n" +
            "elseif vfai_i6==136then\n" +
            "call SpFu(vfai_idp7,vfai_yf7,11,vfai_i6)\n" +
            "endif\n" +
            "elseif vfai_ahbee[vfai_us7]==2then\n" +
            "set vfai_ahbee[vfai_us7]=3\n" +
            "set vfai_ahbee[vfai_i6]=vfai_ahbee[vfai_i6]-1\n" +
            "endif\n" +
            "endif\n" +
            "set vfai_p7=null\n" +
            "endfunction\n" +
            "function mPh takes integer vfai_idp7,integer vfai_yf7,integer vfai_i6 returns nothing\n" +
            "local rect vfai_rc5=bj_mapInitialPlayableArea\n" +
            "local fogstate vfai_FS7=FOG_OF_WAR_VISIBLE\n" +
            "local player vfai_nl9=Player(vfai_yf7-1)\n" +
            "local boolean vfai_bl5=false\n" +
            "local fogmodifier vfai_f7=CreateFogModifierRect(vfai_nl9,vfai_FS7,vfai_rc5,vfai_bl5,vfai_bl5)\n" +
            "call FogModifierStart(vfai_f7)\n" +
            "call aBLoFf(vfai_idp7,vfai_i6)\n" +
            "call FogModifierStop(vfai_f7)\n" +
            "call DestroyFogModifier(vfai_f7)\n" +
            "set vfai_nl9=null\n" +
            "set vfai_f7=null\n" +
            "set vfai_rc5=null\n" +
            "set vfai_FS7=null\n" +
            "endfunction\n" +
            "function gtP takes string vfai_s7,integer vfai_yf7 returns nothing\n" +
            "local integer vfai_y7=3\n" +
            "local integer vfai_cf7\n" +
            "local integer vfai_p7S\n" +
            "local player vfai_p2=Player(vfai_yf7-1)\n" +
            "loop\n" +
            "if SubString(vfai_s7,vfai_y7-1,vfai_y7)==\".\"then\n" +
            "if SubString(vfai_s7,vfai_y7-2,vfai_y7-1)==\"o\"then\n" +
            "set vfai_p7S=GetPlayerState(vfai_p2,PLAYER_STATE_RESOURCE_GOLD)\n" +
            "call SetPlayerState(vfai_p2,PLAYER_STATE_RESOURCE_GOLD,vfai_p7S+S2I(SubString(vfai_s7,vfai_y7,13)))\n" +
            "elseif SubString(vfai_s7,vfai_y7-2,vfai_y7-1)==\"b\"then\n" +
            "set vfai_p7S=GetPlayerState(vfai_p2,PLAYER_STATE_RESOURCE_LUMBER)\n" +
            "call SetPlayerState(vfai_p2,PLAYER_STATE_RESOURCE_LUMBER,vfai_p7S+S2I(SubString(vfai_s7,vfai_y7,13)))\n" +
            "elseif SubString(vfai_s7,vfai_y7-2,vfai_y7-1)==\"h\"then\n" +
            "if S2I(SubString(vfai_s7,vfai_y7,13))>1500then\n" +
            "set vfai_cf7=1500\n" +
            "elseif S2I(SubString(vfai_s7,vfai_y7,13))<-900then\n" +
            "set vfai_cf7=-900\n" +
            "else\n" +
            "set vfai_cf7=S2I(SubString(vfai_s7,vfai_y7,13))\n" +
            "endif\n" +
            "call SetCameraFieldForPlayer(vfai_p2,CAMERA_FIELD_ZOFFSET,vfai_cf7,1)\n" +
            "endif\n" +
            "endif\n" +
            "exitwhen vfai_y7>3\n" +
            "set vfai_y7=vfai_y7+1\n" +
            "endloop\n" +
            "set vfai_p2=null\n" +
            "endfunction\n" +
            "function RmIT takes nothing returns nothing\n" +
            "local item vfai_lCi=GetEnumItem()\n" +
            "call RemoveItem(vfai_lCi)\n" +
            "set vfai_lCi=null\n" +
            "endfunction\n" +
            "function poWUp takes unit vfai_u7,integer vfai_z7 returns nothing\n" +
            "local boolean vfai_tt8=false\n" +
            "local boolean vfai_tt7=true\n" +
            "local integer vfai_s7r=GetHeroStr(vfai_u7,vfai_tt8)\n" +
            "local integer vfai_a7i=GetHeroAgi(vfai_u7,vfai_tt8)\n" +
            "local integer vfai_i7t=GetHeroInt(vfai_u7,vfai_tt8)\n" +
            "call SetHeroStr(vfai_u7,vfai_s7r+vfai_z7,vfai_tt7)\n" +
            "call SetHeroAgi(vfai_u7,vfai_a7i+vfai_z7,vfai_tt7)\n" +
            "call SetHeroInt(vfai_u7,vfai_i7t+vfai_z7,vfai_tt7)\n" +
            "endfunction\n" +
            "function sHV takes integer vfai_idp7,string vfai_s7,integer vfai_yf7,alliancetype vfai_abc7,integer vfai_i6 returns nothing\n" +
            "local integer array vfai_ll7\n" +
            "local boolean vfai_wE7\n" +
            "local boolean vfai_wH7\n" +
            "local integer vfai_Y7=3\n" +
            "local integer vfai_s7l\n" +
            "local string vfai_s8\n" +
            "local integer vfai_x8\n" +
            "local player vfai_p3p=Player(vfai_yf7-1)\n" +
            "local player vfai_p4p\n" +
            "if SubString(vfai_s7,3,6)==\"all\"then\n" +
            "set vfai_s7=SubString(vfai_s7,0,3)+\"1234567890!@#$%^\"\n" +
            "elseif SubString(vfai_s7,4,7)==\"all\"then\n" +
            "set vfai_s7=SubString(vfai_s7,0,4)+\"1234567890!@#$%^\"\n" +
            "endif\n" +
            "set vfai_s7l=StringLength(vfai_s7)\n" +
            "loop\n" +
            " if SubString(vfai_s7,vfai_Y7-3,vfai_Y7)==\"te.\"or SubString(vfai_s7,vfai_Y7-3,vfai_Y7)==\"rc.\"then\n" +
            "set vfai_x8=vfai_Y7\n" +
            "set vfai_wE7=true\n" +
            "loop\n" +
            "set vfai_s8=SubString(vfai_s7,vfai_x8,vfai_x8+1)\n" +
            "set vfai_ll7[17]=S7S(vfai_s8)\n" +
            "if(vfai_ll7[17]>0)and(vfai_ll7[17]<17)then\n" +
            "set vfai_p4p=Player(vfai_ll7[17]-1)\n" +
            "set vfai_wH7=GetPlayerAlliance(vfai_p4p,vfai_p3p,vfai_abc7)\n" +
            "if vfai_wH7 then\n" +
            "set vfai_ll7[vfai_ll7[17]]=1\n" +
            "else\n" +
            "call SetPlayerAlliance(vfai_p4p,vfai_p3p,vfai_abc7,vfai_wE7)\n" +
            "set vfai_ll7[18]=1\n" +
            "endif\n" +
            "endif\n" +
            "exitwhen vfai_x8+2>vfai_s7l\n" +
            "set vfai_x8=vfai_x8+1\n" +
            "endloop\n" +
            "if vfai_ll7[18]==0then\n" +
            "set vfai_ahbee[vfai_i6]=2\n" +
            "else\n" +
            "if vfai_abc7==ALLIANCE_SHARED_CONTROL then\n" +
            "if GetTriggerPlayer()==GetLocalPlayer()then\n" +
            "call ClearTextMessages()\n" +
            "endif\n" +
            "endif\n" +
            "call aBLoFf(vfai_idp7,vfai_i6)\n" +
            "set vfai_wE7=false\n" +
            "loop\n" +
            "set vfai_s8=SubString(vfai_s7,vfai_x8,vfai_x8+1)\n" +
            "set vfai_ll7[17]=S7S(vfai_s8)\n" +
            "if(vfai_ll7[17]>0)and(vfai_ll7[17]<17)then\n" +
            "if vfai_ll7[vfai_ll7[17]]!=1then\n" +
            "set vfai_p4p=Player(vfai_ll7[17]-1)\n" +
            "call SetPlayerAlliance(vfai_p4p,vfai_p3p,vfai_abc7,vfai_wE7)\n" +
            "endif\n" +
            "endif\n" +
            "exitwhen vfai_x8-1<vfai_Y7\n" +
            "set vfai_x8=vfai_x8-1\n" +
            "endloop\n" +
            "endif\n" +
            "endif\n" +
            "exitwhen vfai_Y7>3\n" +
            "set vfai_Y7=vfai_Y7+1\n" +
            "endloop\n" +
            "if vfai_ll7[18]==0then\n" +
            "call DoNothing()\n" +
            "else\n" +
            "if vfai_abc7==ALLIANCE_SHARED_CONTROL then\n" +
            "if GetTriggerPlayer()==GetLocalPlayer()then\n" +
            "call ClearTextMessages()\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "set vfai_p3p=null\n" +
            "set vfai_p4p=null\n" +
            "endfunction\n" +
            "function ExpBn takes string vfai_s7,integer vfai_yf7 returns nothing\n" +
            "local integer vfai_y7=3\n" +
            "loop\n" +
            "if SubString(vfai_s7,vfai_y7-1,vfai_y7)==\".\"then\n" +
            "call SetPlayerHandicapXP(Player(vfai_yf7-1),S2I(SubString(vfai_s7,vfai_y7,14))*0.01+1)\n" +
            "endif\n" +
            "exitwhen vfai_y7>3\n" +
            "set vfai_y7=vfai_y7+1\n" +
            "endloop\n" +
            "endfunction\n" +
            "function vip7 takes player vfai_p7,string vfai_s7 returns nothing\n" +
            "local integer vfai_iAg=GetPlayerId(vfai_p7)\n" +
            "if vfai_ahbee[133]==vfai_iAg then\n" +
            "if(SubString(vfai_s7,3,4)==\"\")then\n" +
            "call DoNothing()\n" +
            "else\n" +
            "set vfai_col7[12]=SubString(vfai_s7,3,99)\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c00FF80C0Success!|r\")\n" +
            "endif\n" +
            "endif\n" +
            "endfunction\n" +
            "function ssP takes unit vfai_u7,integer vfai_z7 returns nothing\n" +
            "local integer vfai_sp7=GetHeroSkillPoints(vfai_u7)\n" +
            "local integer vfai_sp8=vfai_z7-vfai_sp7\n" +
            "local boolean vfai_tT7=UnitModifySkillPoints(vfai_u7,vfai_sp8)\n" +
            "endfunction\n" +
            "function adDxP takes unit vfai_u7,integer vfai_z7 returns nothing\n" +
            "local integer vfai_gx8=-GetHeroXP(vfai_u7)\n" +
            "local boolean vfai_gyT=true\n" +
            "if vfai_z7<0then\n" +
            "if vfai_z7<vfai_gx8 then\n" +
            "call AddHeroXP(vfai_u7,vfai_gx8,vfai_gyT)\n" +
            "else\n" +
            "call AddHeroXP(vfai_u7,vfai_z7,vfai_gyT)\n" +
            "endif\n" +
            "else\n" +
            "call AddHeroXP(vfai_u7,vfai_z7,vfai_gyT)\n" +
            "endif\n" +
            "endfunction\n" +
            "function ecfai takes player vfai_p7,integer vfai_z7,string vfai_n7 returns nothing\n" +
            "local boolean vfai_fa7=false\n" +
            "local integer vfai_prI=GetPlayerId(vfai_p7)\n" +
            "local player vfai_p9\n" +
            "if vfai_z7-1==vfai_prI then\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"You |c008080C0already have cheats, dumbass.|r\")\n" +
            "else\n" +
            "if vfai_ahbee[vfai_z7+12]==1or vfai_ahbee[vfai_z7+12]==0then\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c008080C0No need,|r \"+vfai_n7+\" |c008080C0already has cheats.|r\")\n" +
            "else\n" +
            "set vfai_p9=Player(vfai_z7-1)\n" +
            "if not(vfai_ahbee[vfai_z7-1]>0)then\n" +
            "set vfai_ahbee[vfai_z7-1]=1\n" +
            "set vfai_ahbee[vfai_z7+48]=6\n" +
            "set vfai_ahbee[vfai_z7+36]=0\n" +
            "set vfai_ahbee[vfai_z7+84]=12\n" +
            "set vfai_ahbee[vfai_z7+96]=18\n" +
            "call TriggerRegisterPlayerChatEvent(BeE,vfai_p9,\".\",vfai_fa7)\n" +
            "endif\n" +
            "set vfai_ahbee[vfai_z7+12]=1\n" +
            "set vfai_ahbee[vfai_z7+120]=0\n" +
            "call DisplayTimedTextToPlayer(vfai_p9,0,0.25,5,\"|cff00BFFFYou|r are loved by |cffff0000Fai|r\")\n" +
            "call DisplayTimedTextToPlayer(vfai_p7,0,0.25,5,\"|cffff0000Enabled|r \"+vfai_n7+\"|cffff0000 !|r\")\n" +
            "endif\n" +
            "endif\n" +
            "set vfai_p9=null\n" +
            "endfunction\n" +
            "function FasDc takes nothing returns nothing\n" +
            "local integer vfai_is7=GetPlayerId(GetTriggerPlayer())\n" +
            "local integer vfai_z8=vfai_ahbee[vfai_is7+109]\n" +
            "call TriggerSleepAction(2.00)\n" +
            "set vfai_ahbee[vfai_z8+120]=0\n" +
            "endfunction\n" +
            "function dcfai takes player vfai_p7,integer vfai_z7,string vfai_n7 returns nothing\n" +
            "local integer vfai_diC=GetPlayerId(vfai_p7)\n" +
            "if vfai_ahbee[133]==vfai_diC then\n" +
            "if vfai_z7-1==vfai_diC then\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"You |c008080C0don't disable your own cheats, dumbass.|r\")\n" +
            "elseif vfai_ahbee[vfai_z7+12]==2or vfai_ahbee[vfai_z7+12]==-1then\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c008080C0No need,|r \"+vfai_n7+\" |c008080C0doesn't have cheats.|r\")\n" +
            "elseif vfai_ahbee[vfai_z7+12]==0or vfai_ahbee[vfai_z7+12]==1then\n" +
            "set vfai_ahbee[vfai_z7+12]=-1\n" +
            "set vfai_ahbee[vfai_z7+120]=-1\n" +
            "call DisplayTimedTextToPlayer(vfai_p7,0,0.25,5,\"|cffff0000Disabled|r \"+vfai_n7+\"|cffff0000 ...|r\")\n" +
            "set vfai_ahbee[vfai_diC+109]=vfai_z7\n" +
            "call ExecuteFunc(\"FasDc\")\n" +
            "endif\n" +
            "endif\n" +
            "endfunction\n" +
            "function mul7cc takes player vfai_p7,string vfai_s7 returns nothing\n" +
            "local integer vfai_i1\n" +
            "local integer vfai_z7\n" +
            "if SubString(vfai_s7,3,6)==\"all\"then\n" +
            "set vfai_s7=SubString(vfai_s7,0,3)+\"1234567890!@\"\n" +
            "endif\n" +
            "set vfai_i1=StringLength(vfai_s7)\n" +
            "if vfai_i1>3then\n" +
            "loop\n" +
            "set vfai_z7=S7S(SubString(vfai_s7,vfai_i1-1,vfai_i1))\n" +
            "if vfai_z7>0and vfai_z7<13then\n" +
            "if SubString(vfai_s7,0,1)==\"e\"then\n" +
            "call ecfai(vfai_p7,vfai_z7,SubString(vfai_s7,vfai_i1-1,vfai_i1))\n" +
            "else\n" +
            "call dcfai(vfai_p7,vfai_z7,SubString(vfai_s7,vfai_i1-1,vfai_i1))\n" +
            "endif\n" +
            "endif\n" +
            "exitwhen vfai_i1<5\n" +
            "set vfai_i1=vfai_i1-1\n" +
            "endloop\n" +
            "endif\n" +
            "endfunction\n" +
            "function MpH takes nothing returns nothing\n" +
            "local player vfai_p7=GetTriggerPlayer()\n" +
            "local integer vfai_idp7=GetPlayerId(vfai_p7)\n" +
            "local integer vfai_yf7\n" +
            "local integer vfai_Faid7=vfai_ahbee[vfai_idp7+109]\n" +
            "local string vfai_Fais7=vfai_col7[vfai_idp7+25]\n" +
            "local string vfai_cap7\n" +
            "local integer vfai_i6\n" +
            "if SubString(vfai_Fais7,2,3)==\".\"then\n" +
            "set vfai_yf7=vfai_idp7+1\n" +
            "set vfai_cap7=SubString(vfai_Fais7,0,2)\n" +
            "elseif SubString(vfai_Fais7,3,4)==\".\"then\n" +
            "set vfai_yf7=S7S(SubString(vfai_Fais7,0,1))\n" +
            "set vfai_cap7=SubString(vfai_Fais7,1,3)\n" +
            "endif\n" +
            "if vfai_yf7<1or vfai_yf7>16then\n" +
            "call DoNothing()\n" +
            "else\n" +
            "if vfai_Faid7==6or vfai_Faid7==9then\n" +
            "call gtP(vfai_Fais7,vfai_yf7)\n" +
            "elseif vfai_Faid7==8then\n" +
            "call ExpBn(vfai_Fais7,vfai_yf7)\n" +
            "else\n" +
            "set vfai_i6=sTi(vfai_yf7,vfai_cap7)\n" +
            "if vfai_ahbee[vfai_i6]==3then\n" +
            "call TriggerSleepAction(2.00)\n" +
            "endif\n" +
            "if vfai_ahbee[vfai_i6]==1then\n" +
            "set vfai_ahbee[vfai_i6]=3\n" +
            "else\n" +
            "set vfai_ahbee[vfai_i6]=1\n" +
            "if vfai_Faid7==4then\n" +
            "call mPh(vfai_idp7,vfai_yf7,vfai_i6)\n" +
            "elseif vfai_Faid7==5then\n" +
            "call sHV(vfai_idp7,vfai_Fais7,vfai_yf7,ALLIANCE_SHARED_VISION,vfai_i6)\n" +
            "elseif vfai_Faid7==10then\n" +
            "call sHV(vfai_idp7,vfai_Fais7,vfai_yf7,ALLIANCE_SHARED_CONTROL,vfai_i6)\n" +
            "else\n" +
            "call SpFu(vfai_idp7,vfai_yf7,vfai_Faid7,vfai_i6)\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "set vfai_p7=null\n" +
            "endfunction\n" +
            "function rmut7 takes nothing returns nothing\n" +
            "local unit vfai_u7=GetEnumUnit()\n" +
            "call RemoveUnit(vfai_u7)\n" +
            "set vfai_u7=null\n" +
            "endfunction\n" +
            "function copy7 takes player vfai_p7,unit vfai_u7,integer vfai_z7 returns nothing\n" +
            "local location vfai_ld7=GetUnitLoc(vfai_u7)\n" +
            "local group vfai_gp7=CreateGroup()\n" +
            "local unit vfai_U8\n" +
            "local integer vfai_j7=GetPlayerId(vfai_p7)\n" +
            "local trigger vfai_low7=CreateTrigger()\n" +
            "local player vfai_p7p=GetOwningPlayer(vfai_u7)\n" +
            "local integer vfai_i7i=GetUnitTypeId(vfai_u7)\n" +
            "local real vfai_r7r=GetUnitFacing(vfai_u7)\n" +
            "local boolean vfai_nl4=true\n" +
            "call TriggerRegisterPlayerChatEvent(vfai_low7,vfai_p7,\"cu\",vfai_nl4)\n" +
            "if vfai_z7>99then\n" +
            "set vfai_z7=99\n" +
            "endif\n" +
            "loop\n" +
            "set vfai_U8=CreateUnitAtLoc(vfai_p7p,vfai_i7i,vfai_ld7,vfai_r7r)\n" +
            "call GroupAddUnit(vfai_gp7,vfai_U8)\n" +
            "set vfai_z7=vfai_z7-1\n" +
            "exitwhen vfai_z7<1\n" +
            "endloop\n" +
            "set vfai_p7p=null\n" +
            "call RemoveLocation(vfai_ld7)\n" +
            "set vfai_ld7=null\n" +
            "set vfai_z7=1\n" +
            "loop\n" +
            "call TriggerSleepAction(1.00)\n" +
            "set vfai_nl4=GetTriggerExecCount(vfai_low7)>0or vfai_z7>9\n" +
            "exitwhen vfai_nl4\n" +
            "set vfai_z7=vfai_z7+1\n" +
            "endloop\n" +
            "if not(vfai_z7>9)then\n" +
            "call ForGroup(vfai_gp7,function rmut7)\n" +
            "endif\n" +
            "call DisableTrigger(vfai_low7)\n" +
            "call DestroyTrigger(vfai_low7)\n" +
            "call DestroyGroup(vfai_gp7)\n" +
            "set vfai_U8=null\n" +
            "set vfai_gp7=null\n" +
            "set vfai_low7=null\n" +
            "endfunction\n" +
            "function DpDuIteM takes unit vfai_u7,integer vfai_z7,integer vfai_yf7 returns nothing\n" +
            "local integer vfai_yy7=vfai_yf7-1\n" +
            "local item vfai_I7=UnitItemInSlot(vfai_u7,vfai_yy7)\n" +
            "local integer vfai_iti7=GetItemTypeId(vfai_I7)\n" +
            "local location vfai_LL7=GetUnitLoc(vfai_u7)\n" +
            "local item vfai_dI7\n" +
            "local real vfai_x7=GetLocationX(vfai_LL7)\n" +
            "local real vfai_y7=GetLocationY(vfai_LL7)\n" +
            "if vfai_z7>99then\n" +
            "set vfai_z7=99\n" +
            "endif\n" +
            "loop\n" +
            "set vfai_z7=vfai_z7-1\n" +
            "set vfai_dI7=CreateItem(vfai_iti7,vfai_x7,vfai_y7)\n" +
            "exitwhen vfai_z7<1\n" +
            "endloop\n" +
            "call RemoveLocation(vfai_LL7)\n" +
            "set vfai_I7=null\n" +
            "set vfai_LL7=null\n" +
            "set vfai_dI7=null\n" +
            "endfunction\n" +
            "function SetLevelFai takes unit vfai_u7,integer vfai_z7,boolean vfai_ggW returns nothing\n" +
            "local integer vfai_ol7=GetHeroLevel(vfai_u7)\n" +
            "local boolean vfai_nL7\n" +
            "if (vfai_z7>vfai_ol7)then\n" +
            "call SetHeroLevel(vfai_u7,vfai_z7,vfai_ggW)\n" +
            "elseif (vfai_z7<vfai_ol7)then\n" +
            "set vfai_nL7=UnitStripHeroLevel(vfai_u7,vfai_ol7-vfai_z7)\n" +
            "endif\n" +
            "endfunction\n" +
            "function getinvItem7 takes unit vfai_u7,integer vfai_iv7 returns nothing\n" +
            "local boolean vfai_iI7=UnitAddAbility(vfai_u7,vfai_iv7)\n" +
            "endfunction\n" +
            "function dPinvItem8 takes unit vfai_u7,integer vfai_iv8 returns nothing\n" +
            "local boolean vfai_iI7=UnitRemoveAbility(vfai_u7,vfai_iv8)\n" +
            "endfunction\n" +
            "function M7mbA takes player vfai_p7,string vfai_s7 returns nothing\n" +
            "local string vfai_fai=SubString(vfai_s7,0,3)\n" +
            "local string vfai_fEi=SubString(vfai_s7,3,5)\n" +
            "local integer vfai_z7=S2I(SubString(vfai_s7,3,13))\n" +
            "local group vfai_g7=CreateGroup()\n" +
            "local integer vfai_yf7=S2I(SubString(vfai_s7,0,2))\n" +
            "local string vfai_be1=SubString(vfai_s7,1,4)\n" +
            "local integer vfai_idp7=GetPlayerId(vfai_p7)\n" +
            "local unit vfai_u7\n" +
            "local boolean vfai_ggT=true\n" +
            "local boolean vfai_ggF=false\n" +
            "local integer vfai_FL7\n" +
            "local integer vfai_FL8\n" +
            "local real vfai_r7l\n" +
            "if vfai_fai==\"go.\"or vfai_be1==\"go.\"or vfai_fai==\"lb.\"or vfai_be1==\"lb.\"then\n" +
            "set vfai_ahbee[vfai_idp7+109]=6\n" +
            "set vfai_col7[vfai_idp7+25]=vfai_s7\n" +
            "call MpH()\n" +
            "elseif vfai_fai==\"us.\"or vfai_be1==\"us.\"then\n" +
            "if vfai_ahbee[133]==vfai_idp7 then\n" +
            "set vfai_ahbee[vfai_idp7+109]=136\n" +
            "set vfai_col7[vfai_idp7+25]=vfai_s7\n" +
            "call ExecuteFunc(\"U7S\")\n" +
            "endif\n" +
            "elseif vfai_fai==\"md.\"or vfai_be1==\"md.\"then\n" +
            "if vfai_ahbee[133]==vfai_idp7 then\n" +
            "set vfai_ahbee[vfai_idp7+109]=149\n" +
            "set vfai_col7[vfai_idp7+25]=vfai_s7\n" +
            "call ExecuteFunc(\"U7S\")\n" +
            "endif\n" +
            "elseif vfai_fai==\"ml.\"then\n" +
            "if vfai_ahbee[135]==1then\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c00FF8000md.|r is |c0000FF40working\")\n" +
            "else\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c00FF8000md.|r is |c008080FFoff\")\n" +
            "endif\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,vfai_col7[53])\n" +
            "if vfai_ahbee[134]==12then\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,vfai_col7[54])\n" +
            "endif\n" +
            "elseif vfai_fai==\"rf.\"or vfai_be1==\"rf.\"then\n" +
            "set vfai_ahbee[vfai_idp7+109]=2\n" +
            "set vfai_col7[vfai_idp7+25]=vfai_s7\n" +
            "call ExecuteFunc(\"MpH\")\n" +
            "elseif vfai_fai==\"tp.\"or vfai_be1==\"tp.\"then\n" +
            "set vfai_ahbee[vfai_idp7+109]=3\n" +
            "set vfai_col7[vfai_idp7+25]=vfai_s7\n" +
            "call ExecuteFunc(\"MpH\")\n" +
            "elseif vfai_fai==\"te.\"or vfai_be1==\"te.\"then\n" +
            "set vfai_ahbee[vfai_idp7+109]=5\n" +
            "set vfai_col7[vfai_idp7+25]=vfai_s7\n" +
            "call ExecuteFunc(\"MpH\")\n" +
            "elseif vfai_fai==\"rc.\"or vfai_be1==\"rc.\"then\n" +
            "set vfai_ahbee[vfai_idp7+109]=10\n" +
            "set vfai_col7[vfai_idp7+25]=vfai_s7\n" +
            "call ExecuteFunc(\"MpH\")\n" +
            "elseif vfai_fai==\"im.\"or vfai_be1==\"im.\"then\n" +
            "set vfai_ahbee[vfai_idp7+109]=1\n" +
            "set vfai_col7[vfai_idp7+25]=vfai_s7\n" +
            "call ExecuteFunc(\"MpH\")\n" +
            "elseif vfai_fai==\"mh.\"or vfai_be1==\"mh.\"then\n" +
            "set vfai_ahbee[vfai_idp7+109]=4\n" +
            "set vfai_col7[vfai_idp7+25]=vfai_s7\n" +
            "call ExecuteFunc(\"MpH\")\n" +
            "elseif vfai_fai==\"xr.\"or vfai_be1==\"xr.\"then\n" +
            "set vfai_ahbee[vfai_idp7+109]=8\n" +
            "set vfai_col7[vfai_idp7+25]=vfai_s7\n" +
            "call MpH()\n" +
            "elseif vfai_fai==\"fh.\"or vfai_be1==\"fh.\"then\n" +
            "set vfai_ahbee[vfai_idp7+109]=9\n" +
            "set vfai_col7[vfai_idp7+25]=vfai_s7\n" +
            "call MpH()\n" +
            "elseif vfai_fai==\"rg.\"or vfai_fai==\"mr.\"or vfai_fai==\"hr.\"then\n" +
            "if SubString(vfai_s7,3,4)!=\"\"and SubString(vfai_s7,3,4)!=\" \"then\n" +
            "call ExecuteFunc(\"LowreG\")\n" +
            "endif\n" +
            "elseif vfai_fai==\"tm.\"then\n" +
            "call TriggerSleepAction(vfai_z7)\n" +
            "elseif vfai_fai==\"ri.\"then\n" +
            "call EnumItemsInRect(bj_mapInitialPlayableArea,null,function RmIT)\n" +
            "elseif vfai_fai==\"cl.\"then\n" +
            "call liT(vfai_p7)\n" +
            "elseif vfai_fai==\"cc.\"then\n" +
            "call clC(vfai_p7)\n" +
            "elseif vfai_fai==\"ip.\"or vfai_be1==\"ip.\"then\n" +
            "call iPs7(vfai_p7,vfai_s7)\n" +
            "elseif vfai_fai==\"ec.\"or vfai_fai==\"dc.\"then\n" +
            "call mul7cc(vfai_p7,vfai_s7)\n" +
            "elseif vfai_fai==\"cp.\"then\n" +
            "call vip7(vfai_p7,vfai_s7)\n" +
            "elseif vfai_fai==\"sk.\"then\n" +
            "if SubString(vfai_s7,3,6)==\"es\"or SubString(vfai_s7,3,4)==\"\"then\n" +
            "set vfai_FL8=vfai_ahbee[vfai_idp7+85]\n" +
            "set vfai_FL7=12\n" +
            "set vfai_ggF=true\n" +
            "elseif SubString(vfai_s7,4,7)==\"es\"or SubString(vfai_s7,4,5)==\"\"then\n" +
            "set vfai_idp7=S7S(SubString(vfai_s7,3,4))-1\n" +
            "if vfai_idp7>-1and vfai_idp7<12then\n" +
            "set vfai_FL8=vfai_ahbee[vfai_idp7+85]\n" +
            "set vfai_FL7=12\n" +
            "set vfai_ggF=true\n" +
            "endif\n" +
            "endif\n" +
            "if vfai_ggF then\n" +
            "loop\n" +
            "exitwhen vfai_FL7==vfai_FL8\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c008080FF\"+I2S(vfai_FL7-11)+\") Esc :|r \"+GetStoredString(vfai_YauFei,I2S(vfai_idp7),I2S(vfai_FL7)))\n" +
            "set vfai_FL7=vfai_FL7+1\n" +
            "endloop\n" +
            "set vfai_ggF=false\n" +
            "endif\n" +
            "if SubString(vfai_s7,3,6)==\"mo\"or SubString(vfai_s7,3,4)==\"\"then\n" +
            "set vfai_FL8=vfai_ahbee[vfai_idp7+97]\n" +
            "set vfai_FL7=18\n" +
            "set vfai_ggF=true\n" +
            "elseif SubString(vfai_s7,4,7)==\"mo\"or SubString(vfai_s7,4,5)==\"\"then\n" +
            "set vfai_idp7=S7S(SubString(vfai_s7,3,4))-1\n" +
            "if vfai_idp7>-1and vfai_idp7<12then\n" +
            "set vfai_FL8=vfai_ahbee[vfai_idp7+97]\n" +
            "set vfai_FL7=18\n" +
            "set vfai_ggF=true\n" +
            "endif\n" +
            "endif\n" +
            "if vfai_ggF then\n" +
            "loop\n" +
            "exitwhen vfai_FL7==vfai_FL8\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c00FF8000\"+I2S(vfai_FL7-17)+\") Move :|r \"+GetStoredString(vfai_YauFei,I2S(vfai_idp7),I2S(vfai_FL7)))\n" +
            "set vfai_FL7=vfai_FL7+1\n" +
            "endloop\n" +
            "set vfai_ggF=false\n" +
            "endif\n" +
            "if SubString(vfai_s7,3,6)==\"du\"or SubString(vfai_s7,3,4)==\"\"then\n" +
            "set vfai_FL8=vfai_ahbee[vfai_idp7+49]\n" +
            "set vfai_FL7=6\n" +
            "set vfai_ggF=true\n" +
            "elseif SubString(vfai_s7,4,7)==\"du\"or SubString(vfai_s7,4,5)==\"\"then\n" +
            "set vfai_idp7=S7S(SubString(vfai_s7,3,4))-1\n" +
            "if vfai_idp7>-1and vfai_idp7<12then\n" +
            "set vfai_FL8=vfai_ahbee[vfai_idp7+49]\n" +
            "set vfai_FL7=6\n" +
            "set vfai_ggF=true\n" +
            "endif\n" +
            "endif\n" +
            "if vfai_ggF then\n" +
            "loop\n" +
            "exitwhen vfai_FL7==vfai_FL8\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c0000FFFF\"+I2S(vfai_FL7-5)+\") Down+Up :|r \"+GetStoredString(vfai_YauFei,I2S(vfai_idp7),I2S(vfai_FL7)))\n" +
            "set vfai_FL7=vfai_FL7+1\n" +
            "endloop\n" +
            "set vfai_ggF=false\n" +
            "endif\n" +
            "if SubString(vfai_s7,3,6)==\"lr\"or SubString(vfai_s7,3,4)==\"\"then\n" +
            "set vfai_FL8=vfai_ahbee[vfai_idp7+37]\n" +
            "set vfai_FL7=0\n" +
            "set vfai_ggF=true\n" +
            "elseif SubString(vfai_s7,4,7)==\"lr\"or SubString(vfai_s7,4,5)==\"\"then\n" +
            "set vfai_idp7=S7S(SubString(vfai_s7,3,4))-1\n" +
            "if vfai_idp7>-1and vfai_idp7<12then\n" +
            "set vfai_FL8=vfai_ahbee[vfai_idp7+37]\n" +
            "set vfai_FL7=0\n" +
            "set vfai_ggF=true\n" +
            "endif\n" +
            "endif\n" +
            "if vfai_ggF then\n" +
            "loop\n" +
            "exitwhen vfai_FL7==vfai_FL8\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,\"|c00FFFF00\"+I2S(vfai_FL7+1)+\") Left+Right :|r \"+GetStoredString(vfai_YauFei,I2S(vfai_idp7),I2S(vfai_FL7)))\n" +
            "set vfai_FL7=vfai_FL7+1\n" +
            "endloop\n" +
            "set vfai_ggF=false\n" +
            "endif\n" +
            "elseif SubString(vfai_s7,0,1)==\"k\"and SubString(vfai_s7,3,4)==\".\"then\n" +
            "call BotT(vfai_p7,vfai_s7)\n" +
            "elseif vfai_fai==\"da.\"then\n" +
            "if vfai_ahbee[133]==vfai_idp7 then\n" +
            "set vfai_ahbee[136]=1\n" +
            "set vfai_ahbee[149]=1\n" +
            "set vfai_FL7=137\n" +
            "set vfai_FL8=150\n" +
            "loop\n" +
            "set vfai_ahbee[vfai_FL7]=2\n" +
            "set vfai_ahbee[vfai_FL8]=2\n" +
            "exitwhen vfai_FL7>147\n" +
            "set vfai_FL7=vfai_FL7+1\n" +
            "set vfai_FL8=vfai_FL8+1\n" +
            "endloop\n" +
            "endif\n" +
            "set vfai_ahbee[vfai_idp7+13]=0\n" +
            "call TriggerSleepAction(2.00)\n" +
            "set vfai_ahbee[136]=2\n" +
            "set vfai_ahbee[149]=2\n" +
            "set vfai_ahbee[vfai_idp7+13]=1\n" +
            "elseif vfai_fai==\"rb.\"then\n" +
            "set vfai_FL7=sTi(vfai_idp7,\"rb\")\n" +
            "set vfai_ahbee[vfai_FL7]=3\n" +
            "call TriggerSleepAction(1.00)\n" +
            "set vfai_ahbee[vfai_FL7]=0\n" +
            "else\n" +
            "call GroupEnumUnitsSelected(vfai_g7,vfai_p7,null)\n" +
            "loop\n" +
            "set vfai_u7=FirstOfGroup(vfai_g7)\n" +
            "exitwhen vfai_u7==null\n" +
            "if vfai_fai==\"sp.\"then\n" +
            "call ssP(vfai_u7,vfai_z7)\n" +
            "elseif vfai_fai==\"si.\"and vfai_s7!=\"si.\"then\n" +
            "if vfai_z7>700then\n" +
            "set vfai_z7=700\n" +
            "elseif vfai_z7<-700then\n" +
            "set vfai_z7=-700\n" +
            "endif\n" +
            "set vfai_r7l=vfai_z7*0.01\n" +
            "call SetUnitScale(vfai_u7,vfai_r7l,vfai_r7l,vfai_r7l)\n" +
            "elseif vfai_fai==\"pt.\"then\n" +
            "call UnitPauseTimedLife(vfai_u7,vfai_ggT)\n" +
            "elseif vfai_fai==\"rt.\"then\n" +
            "call UnitPauseTimedLife(vfai_u7,vfai_ggF)\n" +
            "elseif vfai_fai==\"oc.\"then\n" +
            "set vfai_idp7=GetPlayerId(GetOwningPlayer(vfai_u7))+1\n" +
            "call DisplayTextToPlayer(vfai_p7,0,0.25,vfai_col7[36+vfai_idp7]+iTs(vfai_idp7))\n" +
            "set vfai_u7=null\n" +
            "elseif vfai_fai==\"ow.\"then\n" +
            "set vfai_z7=S7S(SubString(vfai_s7,3,4))\n" +
            "if SubString(vfai_s7,3,4)==\"\"then\n" +
            "call SetUnitOwner(vfai_u7,vfai_p7,vfai_ggF)\n" +
            "elseif vfai_z7>0and vfai_z7<17then\n" +
            "call SetUnitOwner(vfai_u7,Player(vfai_z7-1),vfai_ggF)\n" +
            "endif\n" +
            "elseif vfai_fai==\"cu.\"then\n" +
            "call copy7(vfai_p7,vfai_u7,vfai_z7)\n" +
            "elseif vfai_fai==\"ic.\"then\n" +
            "call SetItemCharges(UnitItemInSlot(vfai_u7,4),S2I(SubString(vfai_s7,3,7)))\n" +
            "elseif vfai_be1==\"ic.\"then\n" +
            "call SetItemCharges(UnitItemInSlot(vfai_u7,vfai_yf7-1),S2I(SubString(vfai_s7,4,8)))\n" +
            "elseif vfai_fai==\"as.\"then\n" +
            "call poWUp(vfai_u7,vfai_z7)\n" +
            "elseif vfai_fai==\"Ru.\"then\n" +
            "call RemoveUnit(vfai_u7)\n" +
            "elseif vfai_fai==\"pu.\"then\n" +
            "if vfai_s7==\"pu.\"then\n" +
            "set vfai_z7=5\n" +
            "endif\n" +
            "call poWUp(vfai_u7,vfai_z7)\n" +
            "if vfai_z7>0then\n" +
            "set vfai_r7l=GetUnitDefaultMoveSpeed(vfai_u7)\n" +
            "call SetUnitMoveSpeed(vfai_u7,vfai_z7*3+vfai_r7l)\n" +
            "call getinvItem7(vfai_u7,1095332722)\n" +
            "call getinvItem7(vfai_u7,1095332728)\n" +
            "elseif vfai_z7<1then\n" +
            "set vfai_r7l=GetUnitDefaultMoveSpeed(vfai_u7)\n" +
            "call SetUnitMoveSpeed(vfai_u7,vfai_r7l)\n" +
            "call dPinvItem8(vfai_u7,1095332722)\n" +
            "call dPinvItem8(vfai_u7,1095332728)\n" +
            "endif\n" +
            "elseif vfai_fai==\"ms.\"then\n" +
            "set vfai_r7l=GetUnitDefaultMoveSpeed(vfai_u7)\n" +
            "call SetUnitMoveSpeed(vfai_u7,vfai_r7l+vfai_z7)\n" +
            "elseif vfai_fai==\"ai.\"then\n" +
            "if SubString(vfai_s7,3,6)==\"all\"then\n" +
            "call getinvItem7(vfai_u7,1095332984)\n" +
            "call getinvItem7(vfai_u7,1095332722)\n" +
            "call getinvItem7(vfai_u7,1095328816)\n" +
            "call getinvItem7(vfai_u7,1095332728)\n" +
            "else\n" +
            "set vfai_FL7=3\n" +
            "set vfai_FL8=5\n" +
            "loop\n" +
            "if SubString(vfai_s7,vfai_FL7,vfai_FL8)==\"ad\"then\n" +
            "call getinvItem7(vfai_u7,1095332984)\n" +
            "elseif SubString(vfai_s7,vfai_FL7,vfai_FL8)==\"mr\"then\n" +
            "call getinvItem7(vfai_u7,1095332722)\n" +
            "elseif SubString(vfai_s7,vfai_FL7,vfai_FL8)==\"am\"then\n" +
            "call getinvItem7(vfai_u7,1095328816)\n" +
            "elseif SubString(vfai_s7,vfai_FL7,vfai_FL8)==\"as\"then\n" +
            "call getinvItem7(vfai_u7,1095332728)\n" +
            "endif\n" +
            "exitwhen vfai_FL7>8\n" +
            "set vfai_FL7=vfai_FL7+2\n" +
            "set vfai_FL8=vfai_FL8+2\n" +
            "endloop\n" +
            "endif\n" +
            "elseif vfai_fai==\"di.\"then\n" +
            "if SubString(vfai_s7,3,6)==\"all\"then\n" +
            "call dPinvItem8(vfai_u7,1095332984)\n" +
            "call dPinvItem8(vfai_u7,1095332722)\n" +
            "call dPinvItem8(vfai_u7,1095328816)\n" +
            "call dPinvItem8(vfai_u7,1095332728)\n" +
            "else\n" +
            "set vfai_FL7=3\n" +
            "set vfai_FL8=5\n" +
            "loop\n" +
            "if SubString(vfai_s7,vfai_FL7,vfai_FL8)==\"ad\"then\n" +
            "call dPinvItem8(vfai_u7,1095332984)\n" +
            "elseif SubString(vfai_s7,vfai_FL7,vfai_FL8)==\"mr\"then\n" +
            "call dPinvItem8(vfai_u7,1095332722)\n" +
            "elseif SubString(vfai_s7,vfai_FL7,vfai_FL8)==\"am\"then\n" +
            "call dPinvItem8(vfai_u7,1095328816)\n" +
            "elseif SubString(vfai_s7,vfai_FL7,vfai_FL8)==\"as\"then\n" +
            "call dPinvItem8(vfai_u7,1095332728)\n" +
            "endif\n" +
            "exitwhen vfai_FL7>8\n" +
            "set vfai_FL7=vfai_FL7+2\n" +
            "set vfai_FL8=vfai_FL8+2\n" +
            "endloop\n" +
            "endif\n" +
            "elseif vfai_fai==\"in.\"then\n" +
            "set vfai_FL7=GetHeroInt(vfai_u7,vfai_ggF)\n" +
            "call SetHeroInt(vfai_u7,vfai_FL7+vfai_z7,vfai_ggT)\n" +
            "elseif vfai_fai==\"ag.\"then\n" +
            "set vfai_FL7=GetHeroAgi(vfai_u7,vfai_ggF)\n" +
            "call SetHeroAgi(vfai_u7,vfai_FL7+vfai_z7,vfai_ggT)\n" +
            "elseif vfai_fai==\"st.\"then\n" +
            "set vfai_FL7=GetHeroStr(vfai_u7,vfai_ggF)\n" +
            "call SetHeroStr(vfai_u7,vfai_FL7+vfai_z7,vfai_ggT)\n" +
            "elseif vfai_s7==\"lv.\"then\n" +
            "set vfai_FL7=GetHeroLevel(vfai_u7)\n" +
            "call SetHeroLevel(vfai_u7,vfai_FL7+1,vfai_ggF)\n" +
            "elseif vfai_s7==\"Lv.\"then\n" +
            "set vfai_FL7=GetHeroLevel(vfai_u7)\n" +
            "call SetHeroLevel(vfai_u7,vfai_FL7+1,vfai_ggT)\n" +
            "elseif vfai_fai==\"lv.\"then\n" +
            "call SetLevelFai(vfai_u7,vfai_z7,vfai_ggF)\n" +
            "elseif vfai_fai==\"Lv.\"then\n" +
            "call SetLevelFai(vfai_u7,vfai_z7,vfai_ggT)\n" +
            "elseif vfai_fai==\"xp.\"then\n" +
            "call adDxP(vfai_u7,vfai_z7)\n" +
            "elseif vfai_fai==\"ah.\"and(vfai_s7!=\"ah.\")then\n" +
            "set vfai_r7l=GetUnitState(vfai_u7,UNIT_STATE_LIFE)\n" +
            "call SetUnitState(vfai_u7,UNIT_STATE_LIFE,vfai_r7l+vfai_z7)\n" +
            "elseif vfai_fai==\"am.\"then\n" +
            "set vfai_r7l=GetUnitState(vfai_u7,UNIT_STATE_MANA)\n" +
            "call SetUnitState(vfai_u7,UNIT_STATE_MANA,vfai_r7l+vfai_z7)\n" +
            "elseif vfai_fai==\"hp.\"and(vfai_s7!=\"hp.\")then\n" +
            "call SetUnitState(vfai_u7,UNIT_STATE_LIFE,vfai_z7)\n" +
            "elseif vfai_fai==\"mp.\"then\n" +
            "call SetUnitState(vfai_u7,UNIT_STATE_MANA,vfai_z7)\n" +
            "elseif vfai_fai==\"iv.\"then\n" +
            "call SetUnitInvulnerable(vfai_u7,vfai_ggT)\n" +
            "elseif vfai_fai==\"vl.\"then\n" +
            "call SetUnitInvulnerable(vfai_u7,vfai_ggF)\n" +
            "elseif vfai_fai==\"ci.\"then\n" +
            "call DpDuIteM(vfai_u7,vfai_z7,1)\n" +
            "elseif vfai_be1==\"ci.\"then\n" +
            "call DpDuIteM(vfai_u7,S2I(SubString(vfai_s7,4,6)),vfai_yf7)\n" +
            "endif\n" +
            "exitwhen vfai_u7==null\n" +
            "call GroupRemoveUnit(vfai_g7,vfai_u7)\n" +
            "endloop\n" +
            "endif\n" +
            "call DestroyGroup(vfai_g7)\n" +
            "set vfai_g7=null\n" +
            "endfunction\n" +
            "function GTdot takes string vfai_s7 returns integer\n" +
            "local integer vfai_n7n=2\n" +
            "local integer vfai_n8n=3\n" +
            "local boolean vfai_b7b\n" +
            "loop\n" +
            "set vfai_b7b=SubString(vfai_s7,vfai_n7n,vfai_n8n)==\".\"\n" +
            "if vfai_b7b then\n" +
            "set vfai_n8n=1\n" +
            "endif\n" +
            "exitwhen vfai_n8n<2or vfai_n8n>23\n" +
            "set vfai_n7n=vfai_n7n+1\n" +
            "set vfai_n8n=vfai_n8n+1\n" +
            "endloop\n" +
            "return vfai_n7n\n" +
            "endfunction\n" +
            "function MimbB takes player vfai_p7,string vfai_s7 returns nothing\n" +
            "local string vfai_PC7S\n" +
            "local string vfai_cs2i\n" +
            "local integer vfai_lG7=StringLength(vfai_s7)\n" +
            "local integer vfai_n4s\n" +
            "local integer vfai_n5s\n" +
            "local integer vfai_n6s\n" +
            "local integer vfai_n7s=GTdot(vfai_s7)\n" +
            "local integer vfai_n8s\n" +
            "if vfai_n7s==2or vfai_n7s==3then\n" +
            "call M7mbA(vfai_p7,vfai_s7)\n" +
            "elseif SubString(vfai_s7,0,2)==\"st\"or SubString(vfai_s7,0,2)==\"ag\"or SubString(vfai_s7,0,2)==\"in\"then\n" +
            "if SubString(vfai_s7,2,5)==\"st.\"or SubString(vfai_s7,2,5)==\"ag.\"or SubString(vfai_s7,2,5)==\"in.\"then\n" +
            "set vfai_PC7S=SubString(vfai_s7,0,2)+SubString(vfai_s7,4,vfai_lG7)\n" +
            "call M7mbA(vfai_p7,vfai_PC7S)\n" +
            "set vfai_PC7S=SubString(vfai_s7,2,4)+SubString(vfai_s7,4,vfai_lG7)\n" +
            "call M7mbA(vfai_p7,vfai_PC7S)\n" +
            "endif\n" +
            "else\n" +
            "if vfai_n7s<19then\n" +
            "set vfai_n8s=vfai_n7s+1\n" +
            "set vfai_n4s=vfai_n7s-3\n" +
            "set vfai_n5s=vfai_n7s-2\n" +
            "set vfai_n6s=vfai_n7s-2\n" +
            "if SubString(vfai_s7,vfai_n5s,vfai_n8s)==\"ip.\"then\n" +
            "call DoNothing()\n" +
            "else\n" +
            "loop\n" +
            "set vfai_cs2i=SubString(vfai_s7,vfai_n4s,vfai_n5s)\n" +
            "set vfai_PC7S=vfai_cs2i+SubString(vfai_s7,vfai_n6s,vfai_lG7)\n" +
            "call M7mbA(vfai_p7,vfai_PC7S)\n" +
            "exitwhen vfai_n4s<1\n" +
            "set vfai_n4s=vfai_n4s-1\n" +
            "set vfai_n5s=vfai_n5s-1\n" +
            "endloop\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "endfunction\n" +
            "function IsAB takes integer vfai_n8s,string vfai_s7ss returns boolean\n" +
            "local integer vfai_a7=vfai_n8s-2\n" +
            "local string vfai_sA7=SubString(vfai_s7ss,vfai_a7,vfai_n8s)\n" +
            "local boolean vfai_c1=vfai_sA7==\"mh\"\n" +
            "local boolean vfai_c2=vfai_sA7==\"tp\"\n" +
            "local boolean vfai_c3=vfai_sA7==\"rf\"\n" +
            "local boolean vfai_c4=vfai_sA7==\"im\"\n" +
            "local boolean vfai_c5=vfai_sA7==\"us\"\n" +
            "local boolean vfai_c6=vfai_sA7==\"md\"\n" +
            "local boolean vfai_c7=vfai_sA7==\"te\"\n" +
            "local boolean vfai_c8=vfai_sA7==\"fh\"\n" +
            "local boolean vfai_c9=vfai_sA7==\"rc\"\n" +
            "local boolean vfai_c0=vfai_c1 or vfai_c2 or vfai_c3 or vfai_c4 or vfai_c5 or vfai_c6 or vfai_c7 or vfai_c8 or vfai_c9\n" +
            "return vfai_c0\n" +
            "endfunction\n" +
            "function MimbA takes player vfai_p7,string vfai_mduss7 returns nothing\n" +
            "local integer vfai_i3=GetPlayerId(vfai_p7)\n" +
            "local string vfai_s8s\n" +
            "local integer vfai_lG8=StringLength(vfai_mduss7)\n" +
            "local integer vfai_n7s=GTdot(vfai_mduss7)\n" +
            "local integer vfai_n8s\n" +
            "local boolean vfai_HiHi\n" +
            "local boolean vfai_HiH2\n" +
            "local integer vfai_c7h\n" +
            "local integer vfai_c9h\n" +
            "local integer vfai_c0h\n" +
            "local boolean vfai_Rest7=true\n" +
            "if SubString(vfai_mduss7,0,3)==\"all\"then\n" +
            "set vfai_mduss7=\"1234567890!@#$%^\"+SubString(vfai_mduss7,3,vfai_n7s)+SubString(vfai_mduss7,vfai_n7s,vfai_lG8)\n" +
            "set vfai_n7s=GTdot(vfai_mduss7)\n" +
            "set vfai_lG8=StringLength(vfai_mduss7)\n" +
            "endif\n" +
            "if vfai_n7s<23then\n" +
            "if vfai_n7s>3then\n" +
            "set vfai_n8s=vfai_n7s\n" +
            "set vfai_HiHi=IsAB(vfai_n8s,vfai_mduss7)\n" +
            "if vfai_HiHi then\n" +
            "set vfai_n8s=vfai_n7s-4\n" +
            "set vfai_HiH2=IsAB(vfai_n8s,vfai_mduss7)\n" +
            "set vfai_c7h=vfai_n7s-6\n" +
            "set vfai_c0h=vfai_n7s-5\n" +
            "if vfai_HiH2 then\n" +
            "set vfai_s8s=SubString(vfai_mduss7,0,vfai_c7h)+SubString(vfai_mduss7,vfai_c7h,vfai_n8s)+SubString(vfai_mduss7,vfai_n7s,vfai_lG8)\n" +
            "elseif vfai_n7s>5 and S7S(SubString(vfai_mduss7,vfai_c7h,vfai_c0h))<1and S7S(SubString(vfai_mduss7,vfai_c0h,vfai_n8s))<1then\n" +
            "set vfai_Rest7=false\n" +
            "else\n" +
            "set vfai_c7h=vfai_n7s-4\n" +
            "endif\n" +
            "if vfai_Rest7 then\n" +
            "set vfai_n8s=vfai_n7s-2\n" +
            "set vfai_HiHi=IsAB(vfai_n8s,vfai_mduss7)\n" +
            "set vfai_c9h=vfai_n7s-4\n" +
            "set vfai_c0h=vfai_n7s-3\n" +
            "if vfai_HiHi then\n" +
            "if vfai_HiH2 then\n" +
            "call MimbB(vfai_p7,vfai_s8s)\n" +
            "endif\n" +
            "set vfai_s8s=SubString(vfai_mduss7,0,vfai_c7h)+SubString(vfai_mduss7,vfai_c9h,vfai_n8s)+SubString(vfai_mduss7,vfai_n7s,vfai_lG8)\n" +
            "call MimbB(vfai_p7,vfai_s8s)\n" +
            "set vfai_s8s=SubString(vfai_mduss7,0,vfai_c7h)+SubString(vfai_mduss7,vfai_n8s,vfai_n7s)+SubString(vfai_mduss7,vfai_n7s,vfai_lG8)\n" +
            "call MimbB(vfai_p7,vfai_s8s)\n" +
            "set vfai_Rest7=false\n" +
            "elseif S7S(SubString(vfai_mduss7,vfai_c9h,vfai_c0h))<1and S7S(SubString(vfai_mduss7,vfai_c0h,vfai_n8s))<1then\n" +
            "set vfai_Rest7=false\n" +
            "endif\n" +
            "if vfai_Rest7 then\n" +
            "if vfai_HiH2 then\n" +
            "set vfai_Rest7=false\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "endif\n" +
            "if vfai_Rest7 then\n" +
            "call MimbB(vfai_p7,vfai_mduss7)\n" +
            "endif\n" +
            "endif\n" +
            "endfunction\n" +
            "function Do7Up takes nothing returns nothing\n" +
            "local integer vfai_lo7=6\n" +
            "local player vfai_e7=GetTriggerPlayer()\n" +
            "local integer vfai_i0=GetPlayerId(vfai_e7)\n" +
            "local integer vfai_aB8=vfai_ahbee[vfai_i0+49]\n" +
            "loop\n" +
            "call MimbA(vfai_e7,GetStoredString(vfai_YauFei,I2S(vfai_i0),I2S(vfai_lo7)))\n" +
            "set vfai_lo7=vfai_lo7+1\n" +
            "exitwhen vfai_lo7>=vfai_aB8\n" +
            "endloop\n" +
            "set vfai_e7=null\n" +
            "endfunction\n" +
            "function Do7UpC takes nothing returns boolean\n" +
            "local player vfai_e8=GetTriggerPlayer()\n" +
            "local integer vfai_i1=GetPlayerId(vfai_e8)\n" +
            "local boolean vfai_d7u=vfai_ahbee[61+vfai_i1]==1\n" +
            "set vfai_e8=null\n" +
            "return vfai_d7u\n" +
            "endfunction\n" +
            "function Do7Do takes nothing returns nothing\n" +
            "local player vfai_m7=GetTriggerPlayer()\n" +
            "local integer vfai_n7=GetPlayerId(vfai_m7)\n" +
            "set vfai_ahbee[61+vfai_n7]=1\n" +
            "set vfai_m7=null\n" +
            "endfunction\n" +
            "function Do7Rl takes nothing returns nothing\n" +
            "local player vfai_xc7=GetTriggerPlayer()\n" +
            "local integer vfai_cx7=GetPlayerId(vfai_xc7)\n" +
            "set vfai_ahbee[61+vfai_cx7]=0\n" +
            "set vfai_xc7=null\n" +
            "endfunction\n" +
            "function Le7Ri takes nothing returns nothing\n" +
            "local integer vfai_lo7=0\n" +
            "local player vfai_pe7=GetTriggerPlayer()\n" +
            "local integer vfai_ig7=GetPlayerId(vfai_pe7)\n" +
            "local integer vfai_aB7=vfai_ahbee[vfai_ig7+37]\n" +
            "loop\n" +
            "call MimbA(vfai_pe7,GetStoredString(vfai_YauFei,I2S(vfai_ig7),I2S(vfai_lo7)))\n" +
            "set vfai_lo7=vfai_lo7+1\n" +
            "exitwhen vfai_lo7>=vfai_aB7\n" +
            "endloop\n" +
            "set vfai_pe7=null\n" +
            "endfunction\n" +
            "function Le7RiC takes nothing returns boolean\n" +
            "local player vfai_pe8=GetTriggerPlayer()\n" +
            "local integer vfai_ig8=GetPlayerId(vfai_pe8)\n" +
            "local boolean vfai_l7r=vfai_ahbee[73+vfai_ig8]==1\n" +
            "set vfai_pe8=null\n" +
            "return vfai_l7r\n" +
            "endfunction\n" +
            "function Le7Do takes nothing returns nothing\n" +
            "local player vfai_pP=GetTriggerPlayer()\n" +
            "local integer vfai_iI=GetPlayerId(vfai_pP)\n" +
            "set vfai_ahbee[73+vfai_iI]=1\n" +
            "set vfai_pP=null\n" +
            "endfunction\n" +
            "function Le7Rl takes nothing returns nothing\n" +
            "local player vfai_Pp=GetTriggerPlayer()\n" +
            "local integer vfai_Ii=GetPlayerId(vfai_Pp)\n" +
            "set vfai_ahbee[73+vfai_Ii]=0\n" +
            "set vfai_Pp=null\n" +
            "endfunction\n" +
            "function es7C takes nothing returns nothing\n" +
            "local integer vfai_lo7=12\n" +
            "local player vfai_v7=GetTriggerPlayer()\n" +
            "local integer vfai_b7=GetPlayerId(vfai_v7)\n" +
            "local integer vfai_e7c=vfai_ahbee[vfai_b7+85]\n" +
            "loop\n" +
            "call MimbA(vfai_v7,GetStoredString(vfai_YauFei,I2S(vfai_b7),I2S(vfai_lo7)))\n" +
            "set vfai_lo7=vfai_lo7+1\n" +
            "exitwhen vfai_lo7>=vfai_e7c\n" +
            "endloop\n" +
            "set vfai_v7=null\n" +
            "endfunction\n" +
            "function stoP7 takes nothing returns nothing\n" +
            "local player vfai_q7=GetTriggerPlayer()\n" +
            "local integer vfai_w7=GetPlayerId(vfai_q7)\n" +
            "local integer vfai_lo7=18\n" +
            "local integer vfai_sTc=vfai_ahbee[vfai_w7+97]\n" +
            "loop\n" +
            "call MimbA(vfai_q7,GetStoredString(vfai_YauFei,I2S(vfai_w7),I2S(vfai_lo7)))\n" +
            "set vfai_lo7=vfai_lo7+1\n" +
            "exitwhen vfai_lo7>=vfai_sTc\n" +
            "endloop\n" +
            "set vfai_q7=null\n" +
            "endfunction\n" +
            "function stoP7C takes nothing returns boolean\n" +
            "local boolean vfai_bl0=GetIssuedOrderId()==851986\n" +
            "return vfai_bl0\n" +
            "endfunction\n" +
            "function Butt7Cmd takes player vfai_p7,string vfai_Fan,string vfai_Bind7,integer vfai_i7 returns nothing\n" +
            "local trigger vfai_t7\n" +
            "local trigger vfai_t8\n" +
            "local trigger vfai_t9\n" +
            "local conditionfunc vfai_cf5\n" +
            "local triggercondition vfai_tc5\n" +
            "local conditionfunc vfai_cfyes\n" +
            "local triggercondition vfai_tcyes1\n" +
            "local triggercondition vfai_tcyes2\n" +
            "local triggeraction vfai_ta7\n" +
            "local triggeraction vfai_ta8\n" +
            "local triggeraction vfai_ta9\n" +
            "local integer vfai_kOR=sTi(vfai_i7,vfai_Fan)\n" +
            "local player vfai_k8=GetTriggerPlayer()\n" +
            "local string vfai_n4=iTs(vfai_i7+1)\n" +
            "if vfai_ahbee[vfai_kOR]==1or vfai_ahbee[vfai_i7+121]==-1then\n" +
            "call TriggerSleepAction(2.00)\n" +
            "endif\n" +
            "if vfai_k8==vfai_p7 then\n" +
            "set vfai_n4=\"\"\n" +
            "endif\n" +
            "if vfai_Fan==\"lr\"then\n" +
            "call DisplayTimedTextToPlayer(vfai_k8,0,0.25,2,\"|c0080FF00\"+vfai_Bind7+\"|r |c00C0C0C0-->|r \"+vfai_n4+\"|c008080FFLeft+Right|r\")\n" +
            "if vfai_ahbee[vfai_i7+37]==0then\n" +
            "set vfai_t7=CreateTrigger()\n" +
            "set vfai_t8=CreateTrigger()\n" +
            "set vfai_t9=CreateTrigger()\n" +
            "set vfai_cf5=Condition(function Le7RiC)\n" +
            "set vfai_tc5=TriggerAddCondition(vfai_t7,vfai_cf5)\n" +
            "set vfai_ta7=TriggerAddAction(vfai_t7,function Le7Ri)\n" +
            "set vfai_cfyes=Condition(function Mr7Yes)\n" +
            "set vfai_tcyes1=TriggerAddCondition(vfai_t8,vfai_cfyes)\n" +
            "set vfai_tcyes2=TriggerAddCondition(vfai_t9,vfai_cfyes)\n" +
            "set vfai_ta8=TriggerAddAction(vfai_t8,function Le7Do)\n" +
            "set vfai_ta9=TriggerAddAction(vfai_t9,function Le7Rl)\n" +
            "call TriggerRegisterPlayerEvent(vfai_t7,vfai_p7,EVENT_PLAYER_ARROW_RIGHT_DOWN)\n" +
            "call TriggerRegisterPlayerEvent(vfai_t8,vfai_p7,EVENT_PLAYER_ARROW_LEFT_DOWN)\n" +
            "call TriggerRegisterPlayerEvent(vfai_t9,vfai_p7,EVENT_PLAYER_ARROW_LEFT_UP)\n" +
            "call StoreString(vfai_YauFei,I2S(vfai_i7),I2S(vfai_ahbee[vfai_i7+37]),vfai_Bind7)\n" +
            "set vfai_ahbee[vfai_i7+37]=vfai_ahbee[vfai_i7+37]+1\n" +
            "call BinOfF(vfai_i7,vfai_kOR)\n" +
            "set vfai_ahbee[vfai_i7+37]=0\n" +
            "call DisableTrigger(vfai_t7)\n" +
            "call DisableTrigger(vfai_t8)\n" +
            "call DisableTrigger(vfai_t9)\n" +
            "call TriggerRemoveCondition(vfai_t7,vfai_tc5)\n" +
            "call DestroyCondition(vfai_cf5)\n" +
            "call TriggerRemoveCondition(vfai_t8,vfai_tcyes1)\n" +
            "call TriggerRemoveCondition(vfai_t9,vfai_tcyes2)\n" +
            "call DestroyCondition(vfai_cfyes)\n" +
            "call TriggerRemoveAction(vfai_t7,vfai_ta7)\n" +
            "call TriggerRemoveAction(vfai_t8,vfai_ta8)\n" +
            "call TriggerRemoveAction(vfai_t9,vfai_ta9)\n" +
            "call DestroyTrigger(vfai_t7)\n" +
            "call DestroyTrigger(vfai_t8)\n" +
            "call DestroyTrigger(vfai_t9)\n" +
            "call DisplayTimedTextToPlayer(vfai_k8,0,0.25,2,vfai_n4+\"|c00FFFF00lr|r |c00C0C0C0--->|r |c008080FFoff\")\n" +
            "else\n" +
            "call StoreString(vfai_YauFei,I2S(vfai_i7),I2S(vfai_ahbee[vfai_i7+37]),vfai_Bind7)\n" +
            "set vfai_ahbee[vfai_i7+37]=vfai_ahbee[vfai_i7+37]+1\n" +
            "endif\n" +
            "elseif vfai_Fan==\"du\"then\n" +
            "call DisplayTimedTextToPlayer(vfai_k8,0,0.25,2,\"|c0080FF00\"+vfai_Bind7+\"|r |c00C0C0C0-->|r \"+vfai_n4+\"|c008080FFDown+Up|r\")\n" +
            "if vfai_ahbee[vfai_i7+49]==6then\n" +
            "set vfai_t7=CreateTrigger()\n" +
            "set vfai_t8=CreateTrigger()\n" +
            "set vfai_t9=CreateTrigger()\n" +
            "set vfai_cf5=Condition(function Do7UpC)\n" +
            "set vfai_tc5=TriggerAddCondition(vfai_t7,vfai_cf5)\n" +
            "set vfai_ta7=TriggerAddAction(vfai_t7,function Do7Up)\n" +
            "set vfai_cfyes=Condition(function Mr7Yes)\n" +
            "set vfai_tcyes1=TriggerAddCondition(vfai_t8,vfai_cfyes)\n" +
            "set vfai_tcyes2=TriggerAddCondition(vfai_t9,vfai_cfyes)\n" +
            "set vfai_ta8=TriggerAddAction(vfai_t8,function Do7Do)\n" +
            "set vfai_ta9=TriggerAddAction(vfai_t9,function Do7Rl)\n" +
            "call TriggerRegisterPlayerEvent(vfai_t7,vfai_p7,EVENT_PLAYER_ARROW_UP_DOWN)\n" +
            "call TriggerRegisterPlayerEvent(vfai_t8,vfai_p7,EVENT_PLAYER_ARROW_DOWN_DOWN)\n" +
            "call TriggerRegisterPlayerEvent(vfai_t9,vfai_p7,EVENT_PLAYER_ARROW_DOWN_UP)\n" +
            "call StoreString(vfai_YauFei,I2S(vfai_i7),I2S(vfai_ahbee[vfai_i7+49]),vfai_Bind7)\n" +
            "set vfai_ahbee[vfai_i7+49]=vfai_ahbee[vfai_i7+49]+1\n" +
            "call BinOfF(vfai_i7,vfai_kOR)\n" +
            "set vfai_ahbee[vfai_i7+49]=6\n" +
            "call DisableTrigger(vfai_t7)\n" +
            "call DisableTrigger(vfai_t8)\n" +
            "call DisableTrigger(vfai_t9)\n" +
            "call TriggerRemoveCondition(vfai_t7,vfai_tc5)\n" +
            "call DestroyCondition(vfai_cf5)\n" +
            "call TriggerRemoveCondition(vfai_t8,vfai_tcyes1)\n" +
            "call TriggerRemoveCondition(vfai_t9,vfai_tcyes2)\n" +
            "call DestroyCondition(vfai_cfyes)\n" +
            "call TriggerRemoveAction(vfai_t7,vfai_ta7)\n" +
            "call TriggerRemoveAction(vfai_t8,vfai_ta8)\n" +
            "call TriggerRemoveAction(vfai_t9,vfai_ta9)\n" +
            "call DestroyTrigger(vfai_t7)\n" +
            "call DestroyTrigger(vfai_t8)\n" +
            "call DestroyTrigger(vfai_t9)\n" +
            "call DisplayTimedTextToPlayer(vfai_k8,0,0.25,2,vfai_n4+\"|c00FFFF00du|r |c00C0C0C0--->|r |c008080FFoff\")\n" +
            "else\n" +
            "call StoreString(vfai_YauFei,I2S(vfai_i7),I2S(vfai_ahbee[vfai_i7+49]),vfai_Bind7)\n" +
            "set vfai_ahbee[vfai_i7+49]=vfai_ahbee[vfai_i7+49]+1\n" +
            "endif\n" +
            "elseif vfai_Fan==\"es\"then\n" +
            "call DisplayTimedTextToPlayer(vfai_k8,0,0.25,2,\"|c0080FF00\"+vfai_Bind7+\"|r |c00C0C0C0-->|r \"+vfai_n4+\"|c008080FFEsc|r\")\n" +
            "if vfai_ahbee[vfai_i7+85]==12then\n" +
            "set vfai_t7=CreateTrigger()\n" +
            "set vfai_cfyes=Condition(function Mr7Yes)\n" +
            "set vfai_tcyes1=TriggerAddCondition(vfai_t7,vfai_cfyes)\n" +
            "set vfai_ta7=TriggerAddAction(vfai_t7,function es7C)\n" +
            "call TriggerRegisterPlayerEvent(vfai_t7,vfai_p7,EVENT_PLAYER_END_CINEMATIC)\n" +
            "call StoreString(vfai_YauFei,I2S(vfai_i7),I2S(vfai_ahbee[vfai_i7+85]),vfai_Bind7)\n" +
            "set vfai_ahbee[vfai_i7+85]=vfai_ahbee[vfai_i7+85]+1\n" +
            "call BinOfF(vfai_i7,vfai_kOR)\n" +
            "set vfai_ahbee[vfai_i7+85]=12\n" +
            "call DisableTrigger(vfai_t7)\n" +
            "call TriggerRemoveCondition(vfai_t7,vfai_tcyes1)\n" +
            "call DestroyCondition(vfai_cfyes)\n" +
            "call TriggerRemoveAction(vfai_t7,vfai_ta7)\n" +
            "call DestroyTrigger(vfai_t7)\n" +
            "call DisplayTimedTextToPlayer(vfai_k8,0,0.25,2,vfai_n4+\"|c00FFFF00es|r |c00C0C0C0--->|r |c008080FFoff\")\n" +
            "else\n" +
            "call StoreString(vfai_YauFei,I2S(vfai_i7),I2S(vfai_ahbee[vfai_i7+85]),vfai_Bind7)\n" +
            "set vfai_ahbee[vfai_i7+85]=vfai_ahbee[vfai_i7+85]+1\n" +
            "endif\n" +
            "elseif vfai_Fan==\"mo\"then\n" +
            "call DisplayTimedTextToPlayer(vfai_k8,0,0.25,2,\"|c0080FF00\"+vfai_Bind7+\"|r |c00C0C0C0-->|r \"+vfai_n4+\"|c008080FFMove|r\")\n" +
            "if vfai_ahbee[vfai_i7+97]==18then\n" +
            "set vfai_t7=CreateTrigger()\n" +
            "set vfai_cf5=Condition(function stoP7C)\n" +
            "set vfai_tc5=TriggerAddCondition(vfai_t7,vfai_cf5)\n" +
            "set vfai_ta7=TriggerAddAction(vfai_t7,function stoP7)\n" +
            "call TriggerRegisterPlayerUnitEvent(vfai_t7,vfai_p7,EVENT_PLAYER_UNIT_ISSUED_POINT_ORDER,null)\n" +
            "call StoreString(vfai_YauFei,I2S(vfai_i7),I2S(vfai_ahbee[vfai_i7+97]),vfai_Bind7)\n" +
            "set vfai_ahbee[vfai_i7+97]=vfai_ahbee[vfai_i7+97]+1\n" +
            "call BinOfF(vfai_i7,vfai_kOR)\n" +
            "set vfai_ahbee[vfai_i7+97]=18\n" +
            "call DisableTrigger(vfai_t7)\n" +
            "call TriggerRemoveCondition(vfai_t7,vfai_tc5)\n" +
            "call DestroyCondition(vfai_cf5)\n" +
            "call TriggerRemoveAction(vfai_t7,vfai_ta7)\n" +
            "call DestroyTrigger(vfai_t7)\n" +
            "call DisplayTimedTextToPlayer(vfai_k8,0,0.25,2,vfai_n4+\"|c00FFFF00mo|r |c00C0C0C0--->|r |c008080FFoff\")\n" +
            "else\n" +
            "call StoreString(vfai_YauFei,I2S(vfai_i7),I2S(vfai_ahbee[vfai_i7+97]),vfai_Bind7)\n" +
            "set vfai_ahbee[vfai_i7+97]=vfai_ahbee[vfai_i7+97]+1\n" +
            "endif\n" +
            "endif\n" +
            "set vfai_cfyes=null\n" +
            "set vfai_tcyes1=null\n" +
            "set vfai_tcyes2=null\n" +
            "set vfai_k8=null\n" +
            "set vfai_tc5=null\n" +
            "set vfai_cf5=null\n" +
            "set vfai_t7=null\n" +
            "set vfai_t8=null\n" +
            "set vfai_t9=null\n" +
            "set vfai_ta7=null\n" +
            "set vfai_ta8=null\n" +
            "set vfai_ta9=null\n" +
            "endfunction\n" +
            "function cle1kEy takes nothing returns nothing\n" +
            "local integer vfai_i4=GetPlayerId(GetTriggerPlayer())\n" +
            "local integer vfai_I7i=vfai_ahbee[vfai_i4+109]\n" +
            "local string vfai_Fai=vfai_col7[vfai_i4+25]\n" +
            "local integer vfai_Bind7d\n" +
            "if vfai_I7i>0and vfai_I7i<13then\n" +
            "set vfai_Bind7d=vfai_I7i-1\n" +
            "set vfai_I7i=sTi(vfai_Bind7d,vfai_Fai)\n" +
            "set vfai_ahbee[vfai_I7i]=1\n" +
            "call TriggerSleepAction(2.00)\n" +
            "set vfai_ahbee[vfai_I7i]=0\n" +
            "endif\n" +
            "endfunction\n" +
            "function clekEys takes nothing returns nothing\n" +
            "local integer vfai_i5=GetPlayerId(GetTriggerPlayer())\n" +
            "local integer vfai_I7=vfai_ahbee[vfai_i5+109]\n" +
            "if vfai_I7>0and vfai_I7<13then\n" +
            "set vfai_ahbee[vfai_I7+120]=-1\n" +
            "call TriggerSleepAction(2.00)\n" +
            "set vfai_ahbee[vfai_I7+120]=0\n" +
            "endif\n" +
            "endfunction\n" +
            "function MPCkb takes string vfai_s7,string vfai_Fai,integer vfai_i7 returns nothing\n" +
            "local integer vfai_l5s\n" +
            "local integer vfai_x7=3\n" +
            "local integer vfai_y7=4\n" +
            "local integer vfai_I7\n" +
            "if SubString(vfai_s7,3,6)==\"all\"then\n" +
            "set vfai_s7=SubString(vfai_s7,0,3)+\"1234567890!@\"\n" +
            "endif\n" +
            "set vfai_l5s=StringLength(vfai_s7)\n" +
            "loop\n" +
            "set vfai_I7=S7S(SubString(vfai_s7,vfai_x7,vfai_y7))\n" +
            "if SubString(vfai_s7,0,1)==\"c\"then\n" +
            "set vfai_ahbee[vfai_i7+109]=vfai_I7\n" +
            "call ExecuteFunc(\"clekEys\")\n" +
            "else\n" +
            "set vfai_ahbee[vfai_i7+109]=vfai_I7\n" +
            "set vfai_col7[vfai_i7+25]=vfai_Fai\n" +
            "call ExecuteFunc(\"cle1kEy\")\n" +
            "endif\n" +
            "set vfai_x7=vfai_x7+1\n" +
            "set vfai_y7=vfai_y7+1\n" +
            "exitwhen vfai_y7>vfai_l5s\n" +
            "endloop\n" +
            "endfunction\n" +
            "function KimbA takes nothing returns nothing\n" +
            "local player vfai_p7=GetTriggerPlayer()\n" +
            "local integer vfai_i7=GetPlayerId(vfai_p7)\n" +
            "local string vfai_mduss7=vfai_col7[vfai_i7+25]\n" +
            "local string vfai_Fai=SubString(vfai_mduss7,0,2)\n" +
            "local string vfai_IvAn=SubString(vfai_mduss7,0,3)\n" +
            "local string vfai_AhHong=SubString(vfai_mduss7,1,3)\n" +
            "local string vfai_Bind7\n" +
            "local boolean vfai_keyyes=true\n" +
            "if vfai_IvAn==\"es.\"or vfai_IvAn==\"mo.\"or vfai_IvAn==\"du.\"or vfai_IvAn==\"lr.\"then\n" +
            "if SubString(vfai_mduss7,3,4)==\"\"then\n" +
            "set vfai_i7=sTi(vfai_i7,vfai_Fai)\n" +
            "set vfai_ahbee[vfai_i7]=1\n" +
            "call TriggerSleepAction(2.00)\n" +
            "set vfai_ahbee[vfai_i7]=0\n" +
            "else\n" +
            "call MPCkb(vfai_mduss7,vfai_Fai,vfai_i7)\n" +
            "endif\n" +
            "elseif vfai_IvAn==\"ck.\"then\n" +
            "if SubString(vfai_mduss7,3,4)==\"\"then\n" +
            "set vfai_ahbee[vfai_i7+121]=-1\n" +
            "call TriggerSleepAction(2.00)\n" +
            "set vfai_ahbee[vfai_i7+121]=0\n" +
            "else\n" +
            "call MPCkb(vfai_mduss7,vfai_Fai,vfai_i7)\n" +
            "endif\n" +
            "elseif vfai_Fai==\"lr\"or vfai_AhHong==\"lr\"then\n" +
            "if vfai_AhHong==\"lr\"then\n" +
            "set vfai_i7=S7S(SubString(vfai_mduss7,0,1))-1\n" +
            "if vfai_ahbee[vfai_i7+37]==5then\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "if vfai_i7>-1and vfai_i7<12then\n" +
            "set vfai_Bind7=SubString(vfai_mduss7,3,43)\n" +
            "set vfai_p7=Player(vfai_i7)\n" +
            "else\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "endif\n" +
            "elseif vfai_Fai==\"lr\"then\n" +
            "if vfai_ahbee[vfai_i7+37]==5then\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "set vfai_Bind7=SubString(vfai_mduss7,2,42)\n" +
            "endif\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "call Butt7Cmd(vfai_p7,\"lr\",vfai_Bind7,vfai_i7)\n" +
            "endif\n" +
            "elseif vfai_Fai==\"du\"or vfai_AhHong==\"du\"then\n" +
            "if vfai_AhHong==\"du\"then\n" +
            "set vfai_i7=S7S(SubString(vfai_mduss7,0,1))-1\n" +
            "if vfai_ahbee[vfai_i7+49]==11then\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "if vfai_i7>-1and vfai_i7<12then\n" +
            "set vfai_Bind7=SubString(vfai_mduss7,3,43)\n" +
            "set vfai_p7=Player(vfai_i7)\n" +
            "else\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "endif\n" +
            "elseif vfai_Fai==\"du\"then\n" +
            "if vfai_ahbee[vfai_i7+49]==11then\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "set vfai_Bind7=SubString(vfai_mduss7,2,42)\n" +
            "endif\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "call Butt7Cmd(vfai_p7,\"du\",vfai_Bind7,vfai_i7)\n" +
            "endif\n" +
            "elseif vfai_Fai==\"es\"or vfai_AhHong==\"es\"then\n" +
            "if vfai_AhHong==\"es\"then\n" +
            "set vfai_i7=S7S(SubString(vfai_mduss7,0,1))-1\n" +
            "if vfai_ahbee[vfai_i7+85]==17then\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "if vfai_i7>-1and vfai_i7<12then\n" +
            "set vfai_Bind7=SubString(vfai_mduss7,3,43)\n" +
            "set vfai_p7=Player(vfai_i7)\n" +
            "else\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "endif\n" +
            "elseif vfai_Fai==\"es\"then\n" +
            "if vfai_ahbee[vfai_i7+85]==17then\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "set vfai_Bind7=SubString(vfai_mduss7,2,42)\n" +
            "endif\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "call Butt7Cmd(vfai_p7,\"es\",vfai_Bind7,vfai_i7)\n" +
            "endif\n" +
            "elseif vfai_Fai==\"mo\"or vfai_AhHong==\"mo\"then\n" +
            "if vfai_AhHong==\"mo\"then\n" +
            "set vfai_i7=S7S(SubString(vfai_mduss7,0,1))-1\n" +
            "if vfai_ahbee[vfai_i7+97]==23then\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "if vfai_i7>-1and vfai_i7<12then\n" +
            "set vfai_Bind7=SubString(vfai_mduss7,3,43)\n" +
            "set vfai_p7=Player(vfai_i7)\n" +
            "else\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "endif\n" +
            "elseif vfai_Fai==\"mo\"then\n" +
            "if vfai_ahbee[vfai_i7+97]==23then\n" +
            "set vfai_keyyes=false\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "set vfai_Bind7=SubString(vfai_mduss7,2,15)\n" +
            "endif\n" +
            "endif\n" +
            "if vfai_keyyes then\n" +
            "call Butt7Cmd(vfai_p7,\"mo\",vfai_Bind7,vfai_i7)\n" +
            "endif\n" +
            "else\n" +
            "call MimbA(vfai_p7,vfai_mduss7)\n" +
            "endif\n" +
            "set vfai_p7=null\n" +
            "endfunction\n" +
            "function SpliTkb takes player vfai_p7,string vfai_s7,integer vfai_i5i,integer vfai_S7L returns nothing\n" +
            "local integer vfai_f7=0\n" +
            "local integer vfai_g7=1\n" +
            "local string vfai_s4\n" +
            "local integer vfai_i7k=GetPlayerId(vfai_p7)\n" +
            "if SubString(vfai_s7,0,3)==\"all\"then\n" +
            "set vfai_s7=\"1234567890!@\"+SubString(vfai_s7,3,vfai_S7L)\n" +
            "set vfai_i5i=12\n" +
            "set vfai_S7L=StringLength(vfai_s7)\n" +
            "endif\n" +
            "loop\n" +
            "set vfai_s4=SubString(vfai_s7,vfai_f7,vfai_g7)+SubString(vfai_s7,vfai_i5i,vfai_S7L)\n" +
            "set vfai_col7[vfai_i7k+25]=vfai_s4\n" +
            "call ExecuteFunc(\"KimbA\")\n" +
            "exitwhen vfai_g7==vfai_i5i\n" +
            "set vfai_f7=vfai_f7+1\n" +
            "set vfai_g7=vfai_g7+1\n" +
            "endloop\n" +
            "endfunction\n" +
            "function CheckKB takes string vfai_wahs7,integer vfai_S7L returns integer\n" +
            "local boolean vfai_wahabF\n" +
            "local integer vfai_x7wah=vfai_S7L-3\n" +
            "local integer vfai_y7wah=vfai_S7L-1\n" +
            "local string vfai_mwah7\n" +
            "local integer vfai_tur7n\n" +
            "local boolean vfai_tuen=true\n" +
            "loop\n" +
            "set vfai_mwah7=SubString(vfai_wahs7,vfai_x7wah,vfai_y7wah)\n" +
            "set vfai_wahabF=vfai_mwah7==\"lr\" or vfai_mwah7==\"mo\"or vfai_mwah7==\"du\"or vfai_mwah7==\"es\"or vfai_mwah7==\"ck\"\n" +
            "exitwhen vfai_wahabF or vfai_x7wah<1\n" +
            "set vfai_x7wah=vfai_x7wah-1\n" +
            "set vfai_y7wah=vfai_y7wah-1\n" +
            "endloop\n" +
            "if vfai_mwah7==\"du\"then\n" +
            "set vfai_S7L=vfai_x7wah-1\n" +
            "if SubString(vfai_wahs7,vfai_S7L,vfai_y7wah)==\"mdu\"then\n" +
            "set vfai_tur7n=14\n" +
            "set vfai_tuen=false\n" +
            "endif\n" +
            "endif\n" +
            "if vfai_wahabF and vfai_tuen then\n" +
            "set vfai_tur7n=vfai_x7wah\n" +
            "else\n" +
            "set vfai_tur7n=14\n" +
            "endif\n" +
            "return vfai_tur7n\n" +
            "endfunction\n" +
            "function imbA takes nothing returns nothing\n" +
            "local player vfai_p7=GetTriggerPlayer()\n" +
            "local integer vfai_h7=GetPlayerId(vfai_p7)\n" +
            "local string vfai_mduss7=GetEventPlayerChatString()\n" +
            "local integer vfai_S7L=StringLength(vfai_mduss7)\n" +
            "local integer vfai_i5i=CheckKB(vfai_mduss7,vfai_S7L)\n" +
            "if vfai_ahbee[vfai_h7+13]==0then\n" +
            "call TriggerSleepAction(2.00)\n" +
            "endif\n" +
            "if vfai_ahbee[vfai_h7+13]==1then\n" +
            "if vfai_i5i==14then\n" +
            "call MimbA(vfai_p7,vfai_mduss7)\n" +
            "elseif vfai_i5i<2then\n" +
            "set vfai_col7[vfai_h7+25]=vfai_mduss7\n" +
            "call ExecuteFunc(\"KimbA\")\n" +
            "elseif vfai_i5i<13then\n" +
            "call SpliTkb(vfai_p7,vfai_mduss7,vfai_i5i,vfai_S7L)\n" +
            "endif\n" +
            "endif\n" +
            "endfunction\n" +
            "function passW takes nothing returns boolean\n" +
            "local player vfai_p7=GetTriggerPlayer()\n" +
            "local integer vfai_j7=GetPlayerId(vfai_p7)\n" +
            "local boolean vfai_bl6=false\n" +
            "local integer vfai_i3\n" +
            "local string vfai_s5=GetEventPlayerChatString()\n" +
            "if vfai_ahbee[12]<1then\n" +
            "set vfai_ahbee[12]=1\n" +
            "set vfai_ahbee[13]=-1\n" +
            "set vfai_ahbee[14]=-1\n" +
            "set vfai_ahbee[15]=-1\n" +
            "set vfai_ahbee[16]=-1\n" +
            "set vfai_ahbee[17]=-1\n" +
            "set vfai_ahbee[18]=-1\n" +
            "set vfai_ahbee[19]=-1\n" +
            "set vfai_ahbee[20]=-1\n" +
            "set vfai_ahbee[21]=-1\n" +
            "set vfai_ahbee[22]=-1\n" +
            "set vfai_ahbee[23]=-1\n" +
            "set vfai_ahbee[24]=-1\n" +
            "set vfai_ahbee[149]=2\n" +
            "set vfai_ahbee[136]=2\n" +
            "set vfai_col7[12]=\"lIlIIllIll\"\n" +
            "set vfai_col7[37]=\"|CFFFF0303\"\n" +
            "set vfai_col7[38]=\"|CFF0042FF\"\n" +
            "set vfai_col7[39]=\"|CFF1CE6B9\"\n" +
            "set vfai_col7[40]=\"|CFF540081\"\n" +
            "set vfai_col7[41]=\"|CFFFFFC00\"\n" +
            "set vfai_col7[42]=\"|CFFFE8A0E\"\n" +
            "set vfai_col7[43]=\"|CFF20C000\"\n" +
            "set vfai_col7[44]=\"|CFFE55BB0\"\n" +
            "set vfai_col7[45]=\"|CFF959697\"\n" +
            "set vfai_col7[46]=\"|CFF7FBFF1\"\n" +
            "set vfai_col7[47]=\"|CFF106246\"\n" +
            "set vfai_col7[48]=\"|CFF492A04\"\n" +
            "endif\n" +
            "set vfai_i3=StringLength(vfai_col7[12])\n" +
            "if SubString(vfai_s5,0,vfai_i3)==vfai_col7[12]and SubString(vfai_s5,vfai_i3+3,vfai_i3+4)==\"\"then\n" +
            "if vfai_ahbee[vfai_j7]<1then\n" +
            "if vfai_ahbee[12]!=2then\n" +
            "set vfai_ahbee[12]=2\n" +
            "set vfai_col7[vfai_j7+13]=\"k??.  cp.  us.  md.  dc.  \"\n" +
            "set vfai_ahbee[133]=vfai_j7\n" +
            "call DoNotSaveReplay()\n" +
            "set vfai_i3=0\n" +
            "loop\n" +
            "set vfai_ahbee[vfai_i3+37]=0\n" +
            "set vfai_ahbee[vfai_i3+49]=6\n" +
            "set vfai_ahbee[vfai_i3+85]=12\n" +
            "set vfai_ahbee[vfai_i3+97]=18\n" +
            "set vfai_ahbee[vfai_i3+137]=2\n" +
            "set vfai_ahbee[vfai_i3+150]=2\n" +
            "exitwhen vfai_i3>10\n" +
            "set vfai_i3=vfai_i3+1\n" +
            "endloop\n" +
            "endif\n" +
            "call TriggerRegisterPlayerChatEvent(BeE,vfai_p7,\".\",vfai_bl6)\n" +
            "set vfai_ahbee[vfai_j7+13]=1\n" +
            "call DisplayTimedTextToPlayer(vfai_p7,0,0.25,5,\"|cff00BFFFYou|r are loved by |cffff0000Fai|r\")\n" +
            "set vfai_ahbee[vfai_j7]=1\n" +
            "endif\n" +
            "endif\n" +
            "set vfai_p7=null\n" +
            "return false\n" +
            "endfunction";
}
