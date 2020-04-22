Feature: Test the W3T Reader

  Scenario: Test reading real W3T file 1
    Given Input object file "src/test/resources/war3map3.w3t"
    When W3T file is read
    Then there should be 0 modified objects and 1 new objects

  Scenario: Test reading real W3T file 2
    Given Input object file "src/test/resources/war3map2.w3t"
    When W3T file is read
    Then there should be 1 modified objects and 3 new objects

  Scenario: Test reading real W3T file 3
    Given Input object file "src/test/resources/war3map.w3t"
    When W3T file is read
    Then there should be 4 modified objects and 3 new objects