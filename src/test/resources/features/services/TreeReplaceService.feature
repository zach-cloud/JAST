Feature: Test tree replacement service

  Scenario: Test variable replacement
    Given a replacement service for file "src/test/resources/war3map1" and old name "oldName" and new name "newName" and output "out.j"
    When replacement service is executed for variable
    Then replacement service should have written file

  Scenario: Test function replacement
    Given a replacement service for file "src/test/resources/war3map1" and old name "oldName" and new name "newName" and output "out.j"
    When replacement service is executed for function
    Then replacement service should have written file

  Scenario: Test string replacement
    Given a replacement service for file "src/test/resources/war3map1" and old name "oldName" and new name "newName" and output "out.j"
    When replacement service is executed for string
    Then replacement service should have written file