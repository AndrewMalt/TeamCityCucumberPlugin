#language:en
@testPlugin @newTest
Feature: First test with Cucumber

  Background: set preTest
#    Given say "zero"

#  Scenario: try to test Hello World
  Scenario Outline: new test
    Given say "<one>"

    Examples:
      | one         | two        | boolean |
      | Hello World | test one   | true    |
      | Hello World | test two   | true    |
      | Hello World | test three | false   |

  Scenario Outline: new test
    Given say "<one>"

    Examples:
      | one         | two        | boolean |
      | Hello World | test one   | true    |
      | Hello World | test two   | true    |
