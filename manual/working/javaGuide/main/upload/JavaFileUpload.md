<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# Загрузка файлов

## Загрузка файлов через форму `multipart/form-data`

стандартный способ загрузки файлов в веб-приложении - это использовать форму со специальной кодировкой `multipart/form-data`, которая позволяет совмещать стандартные данные формы с вложениями файлов. Пожалуйста, обратите внимание, что HTTTP метод для формы должен быть POST (не GET). 

Начнем с написания HTML формы

```
@form(action = routes.Application.upload, 'enctype -> "multipart/form-data") {

    <input type="file" name="picture">

    <p>
        <input type="submit">
    </p>

}
```

Теперь давайте определим контроллер `upload`:

@[syncUpload](code/JavaFileUpload.java)

## Прямая загрузка файлов

Другой способ отправить файлы на сервер - использовать Ajax чтобы загрузить файлы из формы асинхронно. В этом случае, тело запроса не будет закодировано как `multipart/form-data`, но будет лишь состоять из содержимого файла.

@[asyncUpload](code/JavaFileUpload.java)
