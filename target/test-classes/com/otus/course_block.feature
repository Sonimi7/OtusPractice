#language: ru

  @course_block
  Функциональность: Блок курсов

    Сценарий: Поиск указанного курса по названию курса
      Пусть Открываем каталог курсов
      Если Кликнуть по плитке курса по "названию" в каталоге курсов
      Тогда Откроется детальная страница курса

    Сценарий: Проверка дат курсов
      Пусть Открываем каталог курсов
      Тогда Проверка названий самых ранних и самых поздних курсов
