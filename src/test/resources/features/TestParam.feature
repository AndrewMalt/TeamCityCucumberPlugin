#language:en
@testPlugin @param
Feature: First test with Cucumber

  Background: set preTest
    Given say "zero"

#  Scenario Outline: : try to test Hello World 1
  Scenario Outline: : try to test Hello World Param
    Given say "<one>"
    And say "<two>"
    And send "<boolean>"
    And send "<boolean>"

    Examples:
      | one         | two        | boolean |
      | Hello World | test one   | true    |
      | Hello World | test two   | true    |
      | Hello World | test three | false   |
#      | Hello World ! ! ! | test two |