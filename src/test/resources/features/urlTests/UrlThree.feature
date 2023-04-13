#language:en
@testUrl @testUrl3
Feature: First test with Cucumber

  Background: set preTest

  Scenario: new test [super test]

    And send GET request on URL "https://yandex.ru/". Response saved in variable "str123ing"
      | type     | name     | value    |
      | qwwe1    | qww2e    | qww3e    |
      | q111wwe1 | qww2222e | qww3333e |

    And say URL "https://yandex.ru/qwe/"
    And say URL "https://yandex.ru/qwe/333?qweecwekfm"
    And say URL "https://yandex.ru/qwe/"

    And send GET request on URL "https://google.com/". Response saved in variable "str123ing"
      | type     | name     | value    |
      | qwwe1    | qww2e    | qww3e    |
      | q111wwe1 | qww2222e | qww3333e |
    And say URL "https://yandex.ru/"
    And say URL "https://yandex.com/13/"
    Given say "test.prop"
    And read text
#    And wait 20 sec

