#language:en
@tags @testPlugin @oneTest2 @api
Feature: First test with Cucumber

  Background: set preTest
#    Given say "zero"

#  Scenario: try to test Hello World
  Scenario: try to test Hello World One Test
    Given say "<one>"
    And throw NPE