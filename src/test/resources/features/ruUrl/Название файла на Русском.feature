#language:en
@tags @testPlugin @oneTest2 @mix @locale
Feature: First test with Cucumber

  Background: set preTest
#    Given say "zero"

#  Scenario: try to test Hello World
  Scenario: try to test Hello World One Test
    Given say "<one>"
    And read text
    And send "False"