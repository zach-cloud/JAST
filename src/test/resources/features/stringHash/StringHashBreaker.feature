Feature: Test String Hash Breaking

  Scenario: Test breaking a simple stringhash
    Given the hash to be broken is "-244676034"
    When the hash is broken
    Then the plaintext should be "aa"