#language:en
@tags @testPlugin @oneTest2 @mix @sce
Feature: First test with Cucumber

  Background: set preTest
#    Given say "zero"

#  Scenario: try to test Hello World
  @sce1
  Scenario: try to test Hello World One Test
    Given say "<one>"

  @testPlugin @oneTest2 @mix @sce2
  Scenario: try to test Hello World One Test1
    And say URL "https://habr.com/ru/companies/avito/articles/734430/"
    And say URL "https://habr.com/ru/flows/develop/"
    And say URL "https://habr.com/ru/flows/admin?qweqwe"
    And say URL "https://habr.com/ru/flows/admin?qweq*&we"
    And say URL "https://habr.com/ru/users/alizar/"
#    And say URL "https://yandex.ru/qwe/"
#    And say URL "https://yandex.ru/qwe/"
    Given say "<o1ne>"