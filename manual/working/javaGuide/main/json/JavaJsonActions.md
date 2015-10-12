<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# Обработка и выдача JSON

В Java, Play использует [Jackson](http://jackson.codehaus.org/) библиотеку для конвертации объектов в JSON и обратно. Play-евские action-ы работают с типом `JsonNode` и фреймворк предоставляет методы для конвертации в классе `play.libs.Json` API.

## Отображение Java объектов в JSON

Jackson позволяет легко конвертировать Java объекты в JSON с помощью просмотра имен полей, get-теров и set-теров. В качестве примера мы будем использовать следущий простой Java объект:

@[person-class](code/javaguide/json/JavaJsonActions.java)

Мы можем распарсить JSON представление объекта и создать новый `Person`:

@[to-json](code/javaguide/json/JavaJsonActions.java)

Похожим образом мы можем преобразовать объект `Person` в `JsonNode`:

@[from-json](code/javaguide/json/JavaJsonActions.java)

## Обработка JSON запроса

JSON запрос - это HTTP запрос, использующий корректый JSON в качестве тела запроса. Его заголовок`Content-Type` должен определять MIME тип `text/json` или `application/json`.

По-умолчанию action использует парсер тела запроса для **любого контента**, который вы можете использовать для получения тела в качестве JSON (если конкретнее, то `JsonNode` библиотеки Jackson ):

@[json-request-as-anycontent](code/javaguide/json/JavaJsonActions.java)

Конечно, намного лучше (или проще) определить свой собственный `BodyParser` чтобы попросить Play пропарсить тело конента напрямую в JSON:

@[json-request-as-json](code/javaguide/json/JavaJsonActions.java)

> **Обратите внимание:** В данном случае сервер автоматически вернет  HTTP ответ с кодом 400 для не JSON-овских запросов, чей Content-type установлен как application/json.

Вы можете протестировать эту функциональность с помощьюю **cURL** из командной строки:

```bash
curl
  --header "Content-type: application/json"
  --request POST
  --data '{"name": "Guillaume"}'
  http://localhost:9000/sayHello
```

Сервер выдаст ответ:

```http
HTTP/1.1 200 OK
Content-Type: text/plain; charset=utf-8
Content-Length: 15

Hello Guillaume
```

## Выдача JSON ответа

В нашем предыдущем примере мы обработали JSON запрос, но вернули `text/plain` в качестве ответа. Давайте изменим это так, чтобы вернуть корректный JSON HTTP ответ:

@[json-response](code/javaguide/json/JavaJsonActions.java)

Теперь на запрос выдает ответ:

```http
HTTP/1.1 200 OK
Content-Type: application/json; charset=utf-8

{"exampleField1":"foobar","exampleField2":"Hello world!"}
```

Вы также можете вернуть Java объект и автоматически сериализовать его в JSON с помощью библиотеки Jackson:

@[json-response-dao](code/javaguide/json/JavaJsonActions.java)

## Продвинутое использование

Поскольку Play использует Jackson, вы можете использовать ваш собственный `ObjectMapper` чтобы создать узлы `JsonNode`. [Документация по  jackson-databind](https://github.com/FasterXML/jackson-databind/blob/master/README.md) объясняет как вы можете далее настроить процесс конввертации JSON.

Если вы захотите использовать Play'евский `Json` API (`toJson`/`fromJson`) с помощью собственного `ObjectMapper`, вы можете добавить что-то вроде этого в ваш  `GlobalSettings#onStart`:

@[custom-object-mapper](code/javaguide/json/JavaJsonActions.java)
