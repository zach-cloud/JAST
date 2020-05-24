Feature: Test the Function node

  @Function
  Scenario: Test reading Function basic
    Given input data:
    """
    function Trig_trig3_Actions takes nothing returns nothing
    call ConditionalTriggerExecute(gg_trg_trig2)
    call DisplayTextToForce(GetPlayersAll(),udg_myVar)
    endfunction
    """
    When Function is read
    Then Function should be:
    """
    function Trig_trig3_Actions takes nothing returns nothing
    call ConditionalTriggerExecute(gg_trg_trig2)
    call DisplayTextToForce(GetPlayersAll(),udg_myVar)
    endfunction
    """
    Then Function header should be "function Trig_trig3_Actions takes nothing returns nothing"
    Then Function should have 2 call statements
    Then Function should have 0 loops
    Then Function should have 0 if statements
    Then Function should have 0 variable statements
    Then Function should have 0 locals

  @Function
  Scenario: Test reading real function
    Given input data:
    """
    function AL takes nothing returns nothing
    local unit WL=GetEnumUnit()
    if (not IsUnitType(WL,UNIT_TYPE_DEAD))and GetUnitTypeId(WL)!=0 then
    if BlzGetUnitBaseDamage(GetEnumUnit(),0)<=0 then
    call IssuePointOrder(GetEnumUnit(),"move",GetUnitX(GetEnumUnit()),-5000.)
    else
    call IssuePointOrder(GetEnumUnit(),"attack",GetUnitX(GetEnumUnit()),-5000.)
    endif
    endif
    set WL=null
    endfunction
    """
    When Function is read
    Then Function should have 1 locals

  @Function
  Scenario: Test reading real function 2
    Given input data:
    """
    function OY takes integer aY,integer nY,integer EY,string tY returns nothing
    local integer JY=0
    local string mY=""
    local player HY
    local force BY
    if aY==Ww[nY] and fw[nY]>=.0 and qw[nY]==EY then
    set Dw[nY]=Dw[nY]+1
    set fw[nY]=2.
    else
    set Dw[nY]=1
    set fw[nY]=.0
    if Aw[nY]<12 then
    set Aw[nY]=Aw[nY]+1
    else
    loop
    exitwhen JY>12
    call SaveStr(cw,nY,JY,LoadStr(cw,nY,JY+1))
    set JY=JY+1
    endloop
    endif
    endif
    set qw[nY]=EY
    set Ww[nY]=aY
    set sw[nY]=3.
    if aY==0 then
    set mY=" "
    elseif aY==1 then
    if Dw[nY]<=1 then
    set mY="You have stolen "+I2S(Dw[nY])+" life from "+Lw[EY+1]+GetPlayerName(Player(EY))+"|r!"
    else
    set mY="You have stolen "+I2S(Dw[nY])+" lifes from "+Lw[EY+1]+GetPlayerName(Player(EY))+"|r!"
    endif
    elseif aY==2 then
    if Dw[nY]<=1 then
    set mY=Lw[EY+1]+GetPlayerName(Player(EY))+"|r stole "+I2S(Dw[nY])+" life from you!"
    else
    set mY=Lw[EY+1]+GetPlayerName(Player(EY))+"|r stole "+I2S(Dw[nY])+" lifes from you!"
    endif
    elseif aY==3 then
    set mY=tY
    elseif aY==4 then
    set mY=Lw[nY+1]+GetPlayerName(Player(nY))+"|r, you can't send more units at the moment (cap is "+I2S(bw)+")!"
    elseif aY==6 then
    set mY="|cff800000S U D D E N   D E A T H|r"
    elseif aY==7 then
    set mY="With income, all players will now lose |cffffcc00"+I2S(Ao)+"|r life (down to 1 life minimum)."
    elseif aY==8 then
    set mY=Lw[nY+1]+GetPlayerName(Player(nY))+"|r, you have purchased a Research Point."
    elseif aY==9 then
    set mY=Yw
    elseif aY==10 then
    set mY="Unable to build here as it would block the creep path."
    elseif aY==11 then
    set mY=tY
    endif
    call SaveStr(cw,nY,Aw[nY],mY)
    set HY=Player(nY)
    set BY=CreateForce()
    call ForceAddPlayer(BY,HY)
    set jw=BY
    set BY=jw
    if IsPlayerInForce(GetLocalPlayer(),BY) then
    call ClearTextMessages()
    endif
    set JY=0
    loop
    exitwhen JY>12
    if LoadStr(cw,nY,JY)!=null then
    call DisplayTimedTextToForce(jw,3.,LoadStr(cw,nY,JY))
    endif
    set JY=JY+1
    endloop
    call DestroyForce(jw)
    set HY=null
    set BY=null
    endfunction
    """
    When Function is read

  @Function
  Scenario: Test crashing function
    Given input data:
    """
    function Hd takes nothing returns nothing
    local unit xd=GetTriggerUnit()
    local integer Sd=GetPlayerId(GetOwningPlayer(xd))+1
    local integer ad
    local integer nd
    local integer Ed
    local player td
    local playerstate Jd
    local playerstate md
    if Iw[Sd]>=bw then
    set nd=GetUnitPointValue(xd)
    set td=GetOwningPlayer(xd)
    set Jd=PLAYER_STATE_RESOURCE_GOLD
    if nd>0 then
    if Jd==PLAYER_STATE_RESOURCE_GOLD then
    set md=PLAYER_STATE_GOLD_GATHERED
    call SetPlayerState(td,md,GetPlayerState(td,md)+nd)
    elseif Jd==PLAYER_STATE_RESOURCE_LUMBER then
    set md=PLAYER_STATE_LUMBER_GATHERED
    call SetPlayerState(td,md,GetPlayerState(td,md)+nd)
    endif
    endif
    call SetPlayerState(td,Jd,GetPlayerState(td,Jd)+nd)
    call RemoveUnit(xd)
    call OY(4,Sd-1,-1,null)
    else
    set nd=ny(GetUnitTypeId(xd))
    if nd>0 then
    set yw[Sd]=yw[Sd]+tw[nd]
    set ad=fp[Sd]
    if ad!=Sd then
    set Sw[0]=GetRandomLocInRect(Jw[ad])
    call SetUnitX(xd,GetLocationX(Sw[0]))
    call SetUnitY(xd,GetLocationY(Sw[0]))
    call RemoveLocation(Sw[0])
    if BlzGetUnitBaseDamage(xd,0)<=0 then
    call IssuePointOrder(xd,"move",GetUnitX(xd),-5000.)
    else
    call IssuePointOrder(xd,"attack",GetUnitX(xd),-5000.)
    endif
    if Hw[nd]>0 then
    set Ed=0
    loop
    exitwhen Ed>Hw[nd]-1
    set Sw[0]=GetRandomLocInRect(Jw[ad])
    call CreateNUnitsAtLoc(1,nw[nd],Player(Sd-1),Sw[0],270.)
    call RemoveLocation(Sw[0])
    if BlzGetUnitBaseDamage(bj_lastCreatedUnit,0)<=0 then
    call IssuePointOrder(bj_lastCreatedUnit,"move",GetUnitX(bj_lastCreatedUnit),-5000.)
    else
    call IssuePointOrder(bj_lastCreatedUnit,"attack",GetUnitX(bj_lastCreatedUnit),-5000.)
    endif
    set Iw[Sd]=Iw[Sd]+1
    set Ed=Ed+1
    endloop
    endif
    set Iw[Sd]=Iw[Sd]+1
    call kq(Sd-1,I2S(Iw[Sd]))
    else
    call RemoveUnit(xd)
    endif
    endif
    endif
    set xd=null
    set td=null
    set Jd=null
    set md=null
    endfunction
    """
    When Function is read

  @Function
  Scenario: Test crashing function 3
    Given input data:
    """
    function NY takes nothing returns nothing
    local integer gY=0
    local integer vY
    local integer RY
    loop
    exitwhen gY>Gw
    if sw[gY]>.0 then
    set sw[gY]=sw[gY]-.1
    if sw[gY]<=.0 then
    set vY=gY
    set RY=0
    set Aw[gY]=0
    set Dw[gY]=0
    set Ww[gY]=0
    set qw[gY]=-1
    set fw[gY]=.0
    loop
    exitwhen RY>12
    call SaveStr(cw,vY,RY,null)
    set RY=RY+1
    endloop
    endif
    endif
    if fw[gY]>.0 then
    set fw[gY]=fw[gY]-.1
    endif
    set gY=gY+1
    endloop
    endfunction
    """
    When Function is read

  Scenario: Test crashing function 4
    Given input data:
    """
    function PHx takes nothing returns nothing
    local integer pickedItemId
    local itemtype Pjx
    local integer PJx=0
    local integer Pkx=0
    local integer Phx
    set Phx=1
    loop
    if(bj_stockAllowedPermanent[Phx])then
    set Pkx=Pkx+1
    if(GetRandomInt(1,Pkx)==1)then
    set Pjx=ITEM_TYPE_PERMANENT
    set PJx=Phx
    endif
    endif
    if(bj_stockAllowedCharged[Phx])then
    set Pkx=Pkx+1
    if(GetRandomInt(1,Pkx)==1)then
    set Pjx=ITEM_TYPE_CHARGED
    set PJx=Phx
    endif
    endif
    if(bj_stockAllowedArtifact[Phx])then
    set Pkx=Pkx+1
    if(GetRandomInt(1,Pkx)==1)then
    set Pjx=ITEM_TYPE_ARTIFACT
    set PJx=Phx
    endif
    endif
    set Phx=Phx+1
    exitwhen Phx>bj_MAX_ITEM_LEVEL
    endloop
    if(Pkx==0)then
    set Pjx=null
    return
    endif
    call Pgx(Pjx,PJx)
    set Pjx=null
    endfunction
    """
    When Function is read

  @Function
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
    When Function is read
    Then Function should be:
    """
    constant function Q4x takes integer Q5x returns integer
    if Q5x == 'H01Z' then
    return 3
    elseif Q5x == 'H02N' then
    return 3
    elseif Q5x == 'H02U' then
    return 5
    elseif Q5x == 'H03Z' then
    return 5
    elseif Q5x == 'H04M' then
    return 6
    endif
    return 6
    endfunction
    """

  @Function
  Scenario: Test crashing function with slash n
    Given input data:
    """
    function aq takes integer Gq,string iq returns integer
    local integer Pq=0
    local integer uq=0
    local integer xq=StringLength(iq)
    local string Sq
    set RD[Gq]=null
    if iq=="" then
    set xq=xq+1
    endif
    call PreloadGenClear()
    call PreloadGenStart()
    loop
    exitwhen Pq>=xq
    set Sq=SubString(iq,Pq,Pq+$c8)
    call Preload("\" )\ncall BlzSetAbilityTooltip("+I2S(gD[uq])+", \""+"-"+Sq+"\", "+I2S(0)+")\n//")
    set Pq=Pq+$c8
    set uq=uq+1
    endloop
    call Preload("\" )\nendfunction\nfunction a takes nothing returns nothing\n //")
    call PreloadGenEnd(vD[Gq])
    return Gq
    endfunction
    """
    When Function is read

  Scenario: Test malformed function 1
    Given input data:
    """
    function StringConv takes string s2s returns string
    local integer i2i=0
    local string ss2s=""
    local integer is2s=StringLength(s2s)
    loop
    exitwhen i2i > is2s
    if SubString(s2s,i2i,i2i + 1) == "*" then
    set ss2s = ss2s + "|cff"
    elseif SubString(s2s,i2i,i2i + 1) == "-" then
    set ss2s=ss2s+"|r"
    else
    set ss2s=ss2s+SubString(s2s,i2i,i2i+1)
    endif
    set i2i=i2i+1
    endloop
    set s2s=""
    return ss2s
    endfunction
    """
    When Function is read
    Then Function should be:
    """
    function StringConv takes string s2s returns string
    local integer i2i=0
    local string ss2s=""
    local integer is2s=StringLength(s2s)
    loop
    exitwhen i2i > is2s
    if SubString(s2s,i2i,i2i + 1) == "*" then
    set ss2s = ss2s + "|cff"
    elseif SubString(s2s,i2i,i2i + 1) == "-" then
    set ss2s = ss2s + "|r"
    else
    set ss2s = ss2s + SubString(s2s,i2i,i2i + 1)
    endif
    set i2i = i2i + 1
    endloop
    set s2s = ""
    return ss2s
    endfunction
    """

  Scenario: Test malformed function 2
    Given input data:
    """
    function Rects_IsUnitInRect takes unit u,rect r returns boolean
      return ( GetUnitX(u) > GetRectMinX(r) - 32 and GetUnitX(u) < GetRectMaxX(r) + 32 ) and ( GetUnitY(u) > GetRectMinY(r) - 32 and GetUnitY(u) < GetRectMaxY(r) + 32 )
    endfunction
    """
    When Function is read
    Then Function should be:
    """
    function Rects_IsUnitInRect takes unit u,rect r returns boolean
    return (GetUnitX(u) > GetRectMinX(r) - 32 and GetUnitX(u) < GetRectMaxX(r) + 32) and (GetUnitY(u) > GetRectMinY(r) - 32 and GetUnitY(u) < GetRectMaxY(r) + 32)
    endfunction
    """

  Scenario: Test malformed function 3
    Given input data:
    """
    function sc__ForestCall___MyStruct_onDestroy takes integer this returns nothing
        set f__arg_this=this
        call TriggerEvaluate(st__ForestCall___MyStruct_onDestroy)
    endfunction
    """
    When Function is read
    Then Function should be:
    """
    function sc__ForestCall___MyStruct_onDestroy takes integer this returns nothing
    set f__arg_this = this
    call TriggerEvaluate(st__ForestCall___MyStruct_onDestroy)
    endfunction
    """

  Scenario: Read another function
    Given input data:
    """
    function nzInit takes nothing returns nothing
    call SaveStr( nzHash, GlobalHandle( ), StringHash( "Activator" ), "easymode" )
    call SaveStr( nzHash, GlobalHandle( ), StringHash( "ArrowSequence" ), "UUDDLR" )
    call NameEvent( "nuzamacuxe" )
    call SaveGroupHandle( nzHash, GlobalHandle( ), StringHash( "GlobalGroup" ), CreateGroup( ) )
    call TriggerAddAction( LoadTrig( "DamageSystem" ), function UnitDamagedAction )
    call PlayerStateEvent( LoadTrig( "LumbRateTrig" ),	PLAYER_STATE_RESOURCE_LUMBER,	function LumberRating )
    call PlayerStateEvent( LoadTrig( "GoldRateTrig" ),	PLAYER_STATE_RESOURCE_GOLD,	function GoldRating )
    call UnitEvent( LoadTrig("InfiniteChargeTrig"), EVENT_PLAYER_UNIT_USE_ITEM,	function InfiniteItem_Act )
    call UnitEvent( LoadTrig("RegisterUnit"), EVENT_PLAYER_UNIT_ATTACKED,	function UnitCheckAction )
    call UnitEvent( LoadTrig("BUFast"), EVENT_PLAYER_UNIT_CONSTRUCT_CANCEL, function FastBUTAct )
    call UnitEvent( LoadTrig("BUFast"), EVENT_PLAYER_UNIT_UPGRADE_CANCEL,   function FastBUTAct )
    call UnitEvent( LoadTrig("TFast"),  EVENT_PLAYER_UNIT_TRAIN_CANCEL,     function FastBUTAct )
    call UnitEvent( LoadTrig("BUFast"), EVENT_PLAYER_UNIT_RESEARCH_START,   function FastBUTAct )
    call UnitEvent( LoadTrig("BUFast"), EVENT_PLAYER_UNIT_ISSUED_ORDER,     function FastBUTAct )
    call UnitEvent( LoadTrig("BUFast"), EVENT_PLAYER_UNIT_ISSUED_UNIT_ORDER, function FastBUTAct )
    call UnitEvent( LoadTrig("TPCmd"),  EVENT_PLAYER_UNIT_ISSUED_POINT_ORDER, function CP_Commands )
    call ChatEvent( LoadTrig("CPCommands"), "-", false, function CP_Commands )
    call ChatEvent( LoadTrig("ChatEnemyDetector"), "", false, function ChatDetector )
    call ArrwEvent( LoadTrig("ArrowActivator"), function ArrowAct )
    endfunction
    """
    When Function is read