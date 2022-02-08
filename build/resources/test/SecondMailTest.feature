#language:en
@two
Feature: Second test with Cucumber

  Background: set preTest
    Given say "zero zero"
  @two
  Scenario Outline: try to test Hello World
    Given say "<one>"
#    And say "<two>"

    Examples:
      | one                      | two      |
      | Second Hello World       | test one |
      | Second Hello World ! ! ! | test two |