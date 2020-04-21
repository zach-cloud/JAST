Feature: Test Function Call

  @FunctionCall
  Scenario: Test function call basic
    Given input data:
    """
    GetHandleId((s__TimerUtils___tT[i] ))
    """
    When Function Call is read
    Then Function Call should be:
    """
    GetHandleId(s__TimerUtils___tT[i])
    """

  @FunctionCall
  Scenario: Test no-args function call
    Given input data:
    """
    GetPlayersAll()
    """
    When Function Call is read
    Then Function Call should be:
    """
    GetPlayersAll()
    """

  @FunctionCall
  Scenario: Test function call complex
    Given input data:
    """
    myFunction(x, 2, myFunction2(a,b,c), ((anotherArg)), arg3+(arg4))
    """
    When Function Call is read
    Then Function Call should be:
    """
    myFunction(x,2,myFunction2(a,b,c),anotherArg,arg3 + arg4)
    """

  @FunctionCall
  Scenario: Test function call complex atual
    Given input data:
    """
    SetCameraBounds((-7424.)+GetCameraMargin(CAMERA_MARGIN_LEFT),(-5120.)+GetCameraMargin(CAMERA_MARGIN_BOTTOM),11520.-GetCameraMargin(CAMERA_MARGIN_RIGHT),10368.-GetCameraMargin(CAMERA_MARGIN_TOP),(-7424.)+GetCameraMargin(CAMERA_MARGIN_LEFT),10368.-GetCameraMargin(CAMERA_MARGIN_TOP),11520.-GetCameraMargin(CAMERA_MARGIN_RIGHT),(-5120.)+GetCameraMargin(CAMERA_MARGIN_BOTTOM))
    """
    When Function Call is read
    Then Function Call should be:
    """
    SetCameraBounds(-7424. + GetCameraMargin(CAMERA_MARGIN_LEFT),-5120. + GetCameraMargin(CAMERA_MARGIN_BOTTOM),11520. - GetCameraMargin(CAMERA_MARGIN_RIGHT),10368. - GetCameraMargin(CAMERA_MARGIN_TOP),-7424. + GetCameraMargin(CAMERA_MARGIN_LEFT),10368. - GetCameraMargin(CAMERA_MARGIN_TOP),11520. - GetCameraMargin(CAMERA_MARGIN_RIGHT),-5120. + GetCameraMargin(CAMERA_MARGIN_BOTTOM))
    """