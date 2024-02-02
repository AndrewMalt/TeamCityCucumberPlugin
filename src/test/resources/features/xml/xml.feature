#language:ru
@xml
Функционал: проверка кастомного метода

  Сценарий: заменить атрибуты в XML файле и сохранить в новый XML файл
    Пусть в XML файле "src/main/resources/xml/test2.xml" произведена замена атрибутов данными из таблицы и результат сохранен в файл "src/main/resources/xml/newTest3.xml"
      | xPath                                 | attribute | value     |
      | //company/staff[@id='2001']/lastname  | pam       | Ну привет   |
      | //company/staff[@id='1001']/firstname | q         | Ура??     |

