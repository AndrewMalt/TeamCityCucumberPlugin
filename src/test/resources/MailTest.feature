Feature: First test with Cucumber

  Scenario Outline: try to test Hello World
    Given say "<one>"
    And say "<two>"

    Examples:
      | one               | two      |
      | Hello World       | test one |
      | Hello World ! ! ! | test two |