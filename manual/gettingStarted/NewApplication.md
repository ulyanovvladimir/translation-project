<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# Создаем новое приложение

## Создайте новое приложение с помощью команды activator

Команда `activator` может быть использована для того чтобы создать новое Play приложение.  Активатор позволяет вам выбрать шаблон, на котором должно базироваться ваше приложение. Для ванильных проектов Play имена этих шаблонов: `play-scala` для приложений Play, базирующихся на Scala, и `play-java` для приложений Play, базирующихся на Java.

> Обратите внимание, что выбоор шаблона как для Scala, так и для Java не означает, то вы не можете сменить язык в последствии. Например, вы можете создать новое приложение, используя  Java шаблон по-умолчанию и начать добавлять Scala код, когда захотите.

Чтобы создать ванильное  Play приложение на Scala, запустите:

```bash
$ activator new my-first-app play-scala
```

Чтобы создать ванильное  Play приложение на Java, запустите:

```bash
$ activator new my-first-app play-java
```

В любом случае, вы можете заменить `my-first-app` на любое другое имя, которое вы хотите использовать для вашего приложения. Активатор будет использовать его в качестве имени директории, в которую будет помещено созданное приложение. Если захотите, вы можете впоследствии изменить это имя.

[[images/activatorNew.png]]

> Если вы хотите использовать другие шаблоны Активатора, вы можете сделать это, запустив команду `activator new`. В данном случае, у вас будет запрошено имя приложения и будет предоставлен шанс выбрать подходящий шаблон.

Когда приложение будет создано, вы можете использовать команду `activator` еще раз, чтобы овйти в [[консоль Play|PlayConsole]].

```bash
$ cd my-first-app
$ activator
```

## Создать новое приложение с помощью Activator UI

Новое приложение Play может быть создано с помощью Activator UI.  Чтобы использовать Activator UI, запустите:

```bash
$ activator ui
```

Вы можете прочесть документацию по использованию Activator UI [здесь](https://typesafe.com/activator/docs).

## Создать новое приложение без активатора Activator

Еще можно создать новое приложение Play без установки Активатора, используя sbt напрямую.

> Для начала установите [sbt](http://www.scala-sbt.org/) при необходимости.

Создайте новую директорию для вашего приложение и сконфигурируйте ваш build скрипт с двумя дополнениями.

В `project/plugins.sbt`, добавьте:

```scala
// Typesafe репозиторий
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

// Используйте Play sbt плагин для проектов Play
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "%PLAY_VERSION%")
```

Убедитесь, что вы заменили `%PLAY_VERSION%` на конкретную верси, которую вы хотите использовать. Если вы хотите использовать снимок версии, вам понадобится определить дополнительный ресолвер зависимостей:

```scala
// Typesafe snapshots
resolvers += "Typesafe Snapshots" at "https://repo.typesafe.com/typesafe/snapshots/"
```

Чтобы убедиться в том, что используется подходящая версия sbt, добавьте следующие строки в `project/build.properties`:

```
sbt.version=0.13.8
```

В `build.sbt` для Java проектов:

```scala
name := "my-first-app"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
```

...или для Scala проектов:

```scala
name := "my-first-app"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
```

Вы можете затем запустить sbt консоль в этой директории:

```bash
$ cd my-first-app
$ sbt
```

sbt загрузит ваш проект и получит зависимости.
 the proper sbt version is used, make sure you have the following in `project/build.properties`:

```
sbt.version=0.13.8
```

В `build.sbt` для Java проектов:

```scala
name := "my-first-app"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
```

...или для Scala проектов:

```scala
name := "my-first-app"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
```

Вы можете затем запустить консоль sbt в этой директории:

```bash
$ cd my-first-app
$ sbt
```

sbt загрузит проект и получит все зависимости.
