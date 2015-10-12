<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# Раота с публичными активами

Этот раздел раскрывает тему выдачи вашим приложением статических ресурсов, таких как JavaScript, CSS и изображения.

Работа с публичными ресурсами в Play точно такая же как обработка любого другого HTTP запроса. Она использует тот же самый механизм маршрутизации, как и обычные ресурсы с помощью пары контроллер/путь чтобы передать CSS, JavaScript или файлы изображений на клиент.

## Папка public/

По договоренностям публичные активы сохраняются в папке `public` вашего приложения. Эта папка может быть организована способом, которым вы пожелаете. Мы рекомендуем следующую структуру:

```
public
 └ javascripts
 └ stylesheets
 └ images
```

Если вы будете следовать этой структуре, будет проще для начала, но ничто не мешает вам модифицировать ее как только вы поймете как это работает.

## WebJars

[WebJars](http://www.webjars.org/) предоставляет удобный механизм упаковки, который является частью Активатора и sbt. Например вы можете объявить, что вы будете использовать популярную библиотеку [Bootstrap library](http://getbootstrap.com/) просто добавив следующую зависимость в ваш файл сборки:

```scala
libraryDependencies += "org.webjars" % "bootstrap" % "3.3.4"
```

WebJars автоматически извлекаются в папку `lib` относительно ваших публичных активов для удобства. Например, если вы объявили зависимость от [RequireJs](http://requirejs.org/) , то вы можете ссылать на нее из вида (view) , используя строку вроде этой:

```html
<script data-main="@routes.Assets.at("javascripts/main.js")" type="text/javascript" src="@routes.Assets.at("lib/requirejs/require.js")"></script>
```

Обратите внимание, на путь `lib/requirejs/require.js`. Директория `lib` указывает на извлеченные активы WebJar, `requirejs` директория соответствует WebJar библиотеке, и `require.js` ссылается на требуемый актив от корня WebJar библиотеки.

## Как упаковываются публичные активы?

В процессе сборки, содержимое папки `public` обрабатывается и добавляется в classpath приложения.

Когда вы упаковываете ваше приложение, все активы для приложения , включая все подпроекты, агрегируются в одну jar-ку, в`target/my-first-app-1.0.0-assets.jar`. Этот jar-файл включается в дистрибутив, так что ваше приложение Play может их предоставить. Этот jar также может быть использован для развертывания активов на CDN или реверсный прокси.

## Контроллер Assets

Play идет со встроенным контроллером для выдачи публичых активов. По-умолчанию этот контроллер предоставляет кэширование, ETag, gzip и поддержку сжатия.

Этот контроллер доступен в в стандартной Play JAR как `controllers.Assets` и определяет простой action `at` с двумя параметрами:

```
Assets.at(path: String, file: String)
```

Параметр `path` должен быть зафиксирован и определяет директорию, управляемую этим action-ом. Параметр `file` обычно динамически извлекается из пути запроса.

Здесь приводится типовое отражение контроллера `Assets` в вашем файле `conf/routes`:

```
GET  /assets/*file        controllers.Assets.at(path="/public", file)
```

Обратите внимание, что мы определяем динамическую часть `*file` которая соответствует регулярному выражению `.*`. Поэтому, например, если вы отправите этот запрос на сервер:

```
GET /assets/javascripts/jquery.js
```

Маршрутизаор вызовет метод `Assets.at` со следующими параметрами:

```
controllers.Assets.at("/public", "javascripts/jquery.js")
```

Этот action найдет и выдаст файл , если он существует.

## Реверсная маршрутизация для пуличных активов

Как для любого контроллера, отображаемого в файле маршрутов, реверсный контроллер создается и для `controllers.routes.Assets`. Вы можете использовать его для получения реверсного URL, необбходимого для получения публичного ресурса. Например, из шаблона:

```html
<script src="@routes.Assets.at("javascripts/jquery.js")"></script>
```

This will produce the following result:

```html
<script src="/assets/javascripts/jquery.js"></script>
```

Обратите внимание, мы не определяем первый параметр `folder` когда мы получаем реверсный маршрут. Это потому что наш файл маршрутов определяет простое отображение для action-а `Assets.at`, где параметр `folder`фиксирован. Поэтому его не нуждается в определении.

Однако, если вы определите два отображения для `Assets.at`, например так:

```
GET  /javascripts/*file        controllers.Assets.at(path="/public/javascripts", file)
GET  /images/*file             controllers.Assets.at(path="/public/images", file)
```

Вам потребуется определить оба параметра, когда будете исопльзовать реверсный маршрутизатор:

```html
<script src="@routes.Assets.at("/public/javascripts", "jquery.js")"></script>
<img src="@routes.Assets.at("/public/images", "logo.png")" />
```

## Реверсная маршрутизация и фингерпринтинг для публичных активов

[sbt-web](https://github.com/sbt/sbt-web) привносит порцию высококонфигурируемого иструмента для управления активами в Play в ваш build файл:

```scala
pipelineStages := Seq(rjs, digest, gzip)
```

Эта строка прикажет оптимизатору RequireJS (`sbt-rjs`), дайджестеру (`sbt-digest`) и затем сжать (`sbt-gzip`). В отличие от многих задач sbt, эти задачи будут выполнены в порядке объявления, одна за другой.

В сущности фингерпринтинг активов позволяет вашим активам быть отданными с агресссивными кэширующими инструкциями. Это приведет к улучшенному поведению ресурса для ваших пользователей, осуществляющих последовательные визиты на ваш сайт, это приведет к меньшей необходимости скачивания ресурсов. Rails также описывает преимущества [фингерпринтинга активов](http://guides.rubyonrails.org/asset_pipeline.html#what-is-fingerprinting-and-why-should-i-care-questionmark). 

Объявление `pipelineStages`, приведенная выше и реквизит `addSbtPlugin` в вашем `plugins.sbt` для необходимых вам плагинов - это ваша исходная позиция. Вы должны затем заявить Play-ю, какие активы должны быть обеспечены контролем версий. Следующие записи файла маршрутов объявляют, что все активы дожны быть версионизированы:

```scala
GET  /assets/*file  controllers.Assets.versioned(path="/public", file: Asset)
```

> Убедитесь, что вы обозначили этот `file` как актив, написав `file: Asset`.

Вы, затем можете использовать реверсный маршрутизатор, например в `scala.html` виде:

```html
<link rel="stylesheet" href="@routes.Assets.versioned("assets/css/app.css")">
```

We highly encourage the use of asset fingerprinting.

## Etag support

The `Assets` controller automatically manages **ETag** HTTP Headers. The ETag value is generated from the digest (if `sbt-digest` is being used in the asset pipeline) or otherwise the resource name and the file’s last modification date. If the resource file is embedded into a file, the JAR file’s last modification date is used.

When a web browser makes a request specifying this **Etag** then the server can respond with **304 NotModified**.

## Gzip support

If a resource with the same name but using a `.gz` suffix is found then the `Assets` controller will also serve the latter and add the following HTTP header:

```
Content-Encoding: gzip
```

Including the `sbt-gzip` plugin in your build and declaring its position in the `pipelineStages` is all that is required to generate gzip files.

## Additional `Cache-Control` directive

Using Etag is usually enough for the purposes of caching. However if you want to specify a custom `Cache-Control` header for a particular resource, you can specify it in your `application.conf` file. For example:

```
# Assets configuration
# ~~~~~
"assets.cache./public/stylesheets/bootstrap.min.css"="max-age=3600"
```

## Managed assets

Starting with Play 2.3 managed assets are processed by [sbt-web](https://github.com/sbt/sbt-web#sbt-web) based plugins. Prior to 2.3 Play bundled managed asset processing in the form of CoffeeScript, LESS, JavaScript linting (ClosureCompiler) and RequireJS optimization. The following sections describe sbt-web and how the equivalent 2.2 functionality can be achieved. Note though that Play is not limited to this asset processing technology as many plugins should become available to sbt-web over time. Please check-in with the [sbt-web](https://github.com/sbt/sbt-web#sbt-web) project to learn more about what plugins are available.

Many plugins use sbt-web's [js-engine plugin](https://github.com/sbt/sbt-js-engine). js-engine is able to execute plugins written to the Node API either within the JVM via the excellent [Trireme](https://github.com/apigee/trireme#trireme) project, or directly on [Node.js](https://nodejs.org/) for superior performance. Note that these tools are used during the development cycle only and have no involvement during the runtime execution of your Play application. If you have Node.js installed then you are encouraged to declare the following environment variable. For Unix, if `SBT_OPTS` has been defined elsewhere then you can:

```bash
export SBT_OPTS="$SBT_OPTS -Dsbt.jse.engineType=Node"
```

The above declaration ensures that Node.js is used when executing any sbt-web plugin.
