<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# Action-ы, Контроллеры и Результаты

## Что такое Action?

Большая часть запросов, получаемых Play приложением обрабатываются `Action`-ами. 

Action - в основе своей Java метод, который обрабатывает параметры запроса и производит результат, который будет отправлен клиенту.

@[simple-action](code/javaguide/http/JavaActions.java)


Action возвращает `play.mvc.Result` значение, представляющий HTTP ответ для отправки на веб-клиент. В этом примере `ok` конструирует ответ **200 OK**, содержащий  тело ответа в формате **text/plain**.

## Контроллеры 

Контроллеры - это ничего более, чем класс `play.mvc.Controller`, который объединяет различные методы для работы с Action-ами.

@[full-controller](code/javaguide/http/full/Application.java)

Простейший синтаксис для определения action-а - это метод без параметров, который возвращает значение `Result` как показано выше.

Action-метод может также иметь параметры:

@[params-action](code/javaguide/http/JavaActions.java)

Эти параметры будут ресолвиться `Router`-ом и будут заполняться значениям из ULR запроса. Значения параметров могут быть извлечены либо из URL пути, либо из строки URL запроса.

## Результаты

Давайте начнем с простых результатов: HTTP результат с кодом статуса, набором HTTP заголовков и телом для отправки на веб-клиент.

Эти резульаты определяются классом `play.mvc.Result`, и класс `play.mvc.Results` предоставляет различные хелперы для производства стандартных HTTP результатов, таких как метод `ok`, который был использован в предыдущим разделе:

@[simple-result](code/javaguide/http/JavaActions.java)

Вот несколько примеров различных результатов:

@[other-results](code/javaguide/http/JavaActions.java)

Все эти хелперы могут быть найдены в классе `play.mvc.Results`.

## Перенаправления - также простые результаты 

Перенаправление браузера на другой URL - также другой вид простого результата. Однако, эти результаты не имеют тела ответа.

There are several helpers available to create redirect results:

@[redirect-action](code/javaguide/http/JavaActions.java)

По умолчанию используется тип ответа `303 SEE_OTHER`, но вы можете определить более специфичный код статуса:

@[temporary-redirect-action](code/javaguide/http/JavaActions.java)
