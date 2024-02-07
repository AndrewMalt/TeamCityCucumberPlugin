#language:ru
@tags @testPlugin @oneTest2 @mix @locale
Функционал: проверить локаль

  Предыстория: пред условия
#    Given say "zero"

  Сценарий: проверить отображение символов
    Пусть say "<one>"
    И read text
    И send "<boolean>"
#  Scenario: try to test Hello World
  Структура сценария: проверить отображение символов
    Пусть say "<one>"
    И read text
    И send "<boolean>"

    Примеры:
      | one | boolean |
#      | one | False   |
      | one | True    |
      | one | False   |

