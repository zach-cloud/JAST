Feature: Test CFG Service

  Scenario: Test reading simple cfg file
    Given No cfg file at "src/test/resources/init.cfg"
    Given A cfg file "src/test/resources/init.cfg" with contents:
    """
    testKey:testValue
    """
    When The cfg file "src/test/resources/init.cfg" is read
    Then The cfg map should have 1 key value pair

  Scenario: Test writing simple cfg file
    Given No cfg file at "src/test/resources/init.cfg"
    When The cfg file "src/test/resources/init.cfg" is written with key "key1" and value "value1"
    Then The contents of "src/test/resources/init.cfg" should be:
    """
    key1:value1
    """