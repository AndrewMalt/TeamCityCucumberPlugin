#language:en
@tags @testPlugin @oneTest2 @mix
Feature: First test with Cucumber

  Background: set preTest
#    Given say "zero"

#  Scenario: try to test Hello World
  Scenario: try to test Hello World One Test
    Given say "<one>"

  @testPlugin @oneTest2 @mix
  Scenario: try to test Hello World One Test1
    Given say "<o1ne>"