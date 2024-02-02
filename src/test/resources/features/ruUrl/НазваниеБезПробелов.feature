#language:ru
@tags @testPlugin @oneTest2 @mix @locale
Функционал: проверить локаль

  Предыстория: пред условия
#    Given say "zero"

#  Scenario: try to test Hello World
  Сценарий: проверить отображение символов
    Пусть say "<one>"
    И read text
    И send "False"