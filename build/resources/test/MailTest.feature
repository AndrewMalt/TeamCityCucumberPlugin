@one
Feature: First test with Cucumber

  Background: set preTest
    Given say "zero"

  Scenario Outline: try to test Hello World
    Given say "<one>"
    And say "<two>"

    Examples:
      | one               | two      |
      | Hello World       | test one |
#      | Hello World ! ! ! | test two |