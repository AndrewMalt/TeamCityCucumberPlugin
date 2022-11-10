#language:en
@testPlugin @oneTest
Feature: First test with Cucumber

  Background: set preTest
#    Given say "zero"

  Scenario: try to test Hello World
    Given say "<one>"
    And say "<two>"
    And send "true"
    And send "true"
    And send "true"