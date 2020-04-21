Feature: Test WTS String node

  @WtsString
  Scenario: Test reading WTS String Basic
    Given input data without preprocessing:
    """
    STRING 8
    {
    Builder
    }
    """
    When WTS String is read
    Then WTS String key should be "STRING 8"
    Then WTS String value should be "Builder"
    Then WTS String comment should be ""

  @WtsString
  Scenario: Test reading WTS String Multiline
    Given input data without preprocessing:
    """
    STRING 1107
    {
    As the player, you have four major resources: Life, Income, Gold and Research Points.

    At the beginning of the game you have 25 life, 30 gold, a starting income of 20 gold and 4 Research Points.

    Every 10 seconds of game time your income is added to your total gold (there is a timer that displays the time remaining to the next income cycle at the top right).

    Research Points can be bought for 100000 gold from the Research Center.
    }
    """
    When WTS String is read
    Then WTS String key should be "STRING 1107"
    Then WTS String value should be "As the player, you have four major resources: Life, Income, Gold and Research Points.\n\nAt the beginning of the game you have 25 life, 30 gold, a starting income of 20 gold and 4 Research Points.\n\nEvery 10 seconds of game time your income is added to your total gold (there is a timer that displays the time remaining to the next income cycle at the top right).\n\nResearch Points can be bought for 100000 gold from the Research Center."
    Then WTS String comment should be ""

  @WtsString
  Scenario: Test reading WTS String With Comments
    Given input data without preprocessing:
    """
    STRING 883
    // Units: u006 (Crypt Fiend), Hotkey (Hotkey)
    {
    E
    }
    """
    When WTS String is read
    Then WTS String should be:
    """
    STRING 883
    // Units: u006 (Crypt Fiend), Hotkey (Hotkey)
    {
    E
    }
    """
    Then WTS String key should be "STRING 883"
    Then WTS String value should be "E"