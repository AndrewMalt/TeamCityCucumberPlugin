#language:en
@testPlugin
Feature: First test with Cucumber

  Background: set preTest
    Given say "zero"

  Scenario: try to test Hello World 1
    Given say "<one>"
    And say "<two>"
    And send "true"

  Scenario: try to test Hello World 1
    Given say "<one>"
    And say "<two>"
    And send "false"

  Scenario: try to test Hello World 3
    Given say "<one>"
    And say "<two>"
    And send "true"