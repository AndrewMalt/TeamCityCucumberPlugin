#language:en
@tags @testPlugin @oneTest2 @ui
Feature: First test with Cucumber

  Background: set preTest
#    Given say "zero"

#  Scenario: try to test Hello World
  Scenario Template: try to test Hello World One Test
    Given say "<one>"
    Examples:
      | one |
      | 123 |
      | o234ne |

