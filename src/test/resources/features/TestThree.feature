#language:en
@testPlugin
Feature: First test with Cucumber

  Background: set preTest
    Given say "zero"

  Scenario: try to test Hello World TestThree
#  Scenario: try to test Hello World
    Given say "<one>"
    And say "<two>"
    And send "false"