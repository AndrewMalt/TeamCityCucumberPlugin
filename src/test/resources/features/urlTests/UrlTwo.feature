#language:en
@testUrl @testUrl2
Feature: First test with Cucumber

  Background: set preTest

  Scenario: new test [super test]
    And send GET request on URL "https://yandex.ru/". Response saved in variable "str123ing"
      | type     | name     | value    |
      | qwwe1    | qww2e    | qww3e    |
      | q111wwe1 | qww2222e | qww3333e |

    And say URL "https://yandex.ru/qwe/"
    And say URL "https://yandex.ru/qwe/222"
    And say URL "ftp://yandex.ru/qwe/222"
    And say URL "qwe://yandex.ru/qwe/2221111111111"
    And say URL "wefw4fg34f34y34y"
    And say URL "https://yandex.ru/qwe/222/"

#    And wait 20 sec

    And say URL "https://yandex.ru/qwe/"

    And send GET request on URL "https://google.com/". Response saved in variable "str123ing"
      | type     | name     | value    |
      | qwwe1    | qww2e    | qww3e    |
      | q111wwe1 | qww2222e | qww3333e |
    And say URL "https://yandex.ru/"
    And say URL "https://yandex.com/12/"
    Given say "test.prop"
    And read text

