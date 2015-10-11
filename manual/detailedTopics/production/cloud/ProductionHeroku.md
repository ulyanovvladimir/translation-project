<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# Развертывание в Heroku

[Heroku](https://www.heroku.com/) - это облачная платформа приложений, способ сборки и развертывания ваших веб-приложений.

Для начала:

1. [Установите Heroku Toolbelt](https://toolbelt.heroku.com)
2. [Зарегистрируйте аккаунт Heroku](https://id.heroku.com/signup)

Есть два способа развертывания в Heroku:

*  Выложить на удаленный [Git репозиторий](https://devcenter.heroku.com/articles/deploying-scala).
*  Использование плагина [sbt-heroku](https://devcenter.heroku.com/articles/deploying-scala-and-play-applications-with-the-heroku-sbt-plugin).

## Разверывание через удаленный Git репозиторий

### Сохраните ваше приложение в git

```bash
$ git init
$ git add .
$ git commit -m "init"
```

### Создайте новое приложение на Heroku

```bash
$ heroku create
Creating warm-frost-1289... done, stack is cedar-14
http://warm-frost-1289.herokuapp.com/ | git@heroku.com:warm-frost-1289.git
Git remote heroku added
```

Это создаст новое приложние с HTTP (и HTTPS) конечной точкой и конечной точкой Git для вашего приложения.  Конечная точка Git endpoint устанавливается как новый удаленный сервер репозитория с именем `heroku` в конфигурации вашего Git репозитория.

### Развертывание вашего приложения

Чтобы развернуть ваше приложение на Heroku, используйте Git чтобы залить его на удаленный репозиторий `heroku`:

```bash
$ git push heroku master
Counting objects: 93, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (84/84), done.
Writing objects: 100% (93/93), 1017.92 KiB | 0 bytes/s, done.
Total 93 (delta 38), reused 0 (delta 0)
remote: Compressing source files... done.
remote: Building source:
remote:
remote: -----> Play 2.x - Scala app detected
remote: -----> Installing OpenJDK 1.8... done
remote: -----> Priming Ivy cache (Scala-2.11, Play-2.4)... done
remote: -----> Running: sbt compile stage
...
remote: -----> Dropping ivy cache from the slug
remote: -----> Dropping sbt boot dir from the slug
remote: -----> Dropping compilation artifacts from the slug
remote: -----> Discovering process types
remote:        Procfile declares types -> web
remote:
remote: -----> Compressing... done, 93.3MB
remote: -----> Launching... done, v6
remote:        https://warm-frost-1289.herokuapp.com/ deployed to Heroku
remote:
remote: Verifying deploy... done.
To https://git.heroku.com/warm-frost-1289.git
* [new branch]      master -> master
```

Heroku запустит `sbt stage` чтобы подготовить ваше приложение. В первое развертывание будет произведено скачивание всех зависимостей, что займет некоторое время (но они будут скэшированы для будущих развертываний).

Если вы используете RequireJS и вы обнаружите, что ваше приложение зависает на этом шаге:

```bash
[info] Optimizing JavaScript with RequireJS
```

Тогда попробуйте выполнить следующие шаги в [Использование Node.js для выполнения JavaScript Оптимизации для Play и Scala приложений](https://devcenter.heroku.com/articles/using-node-js-to-perform-javascript-optimization-for-play-and-scala-applications) на  Heroku Dev Center. Это значительно улучшит производительность Javascript движка.

### Проверьте, что ваше приложение было развернуто

Теперь давайте проверим состояние процессов приложения:

```bash
$ heroku ps
=== web (Free): `target/universal/stage/bin/sample-app -Dhttp.port=${PORT}`
web.1: up 2015/01/09 11:27:51 (~ 4m ago)
```

Веб процесс запущен и работает. Вы можете посмотреть логи для получения подробной информации:

```bash
$ heroku logs
2015-07-13T20:44:47.358320+00:00 heroku[web.1]: Starting process with command `target/universal/stage/bin/myapp -Dhttp.port=${PORT}`
2015-07-13T20:44:49.750860+00:00 app[web.1]: Picked up JAVA_TOOL_OPTIONS: -Xmx384m -Xss512k -Dfile.encoding=UTF-8
2015-07-13T20:44:52.297033+00:00 app[web.1]: [warn] application - Logger configuration in conf files is deprecated and has no effect. Use a logback configuration file instead.
2015-07-13T20:44:54.960105+00:00 app[web.1]: [info] p.a.l.c.ActorSystemProvider - Starting application default Akka system: application
2015-07-13T20:44:55.066582+00:00 app[web.1]: [info] play.api.Play$ - Application started (Prod)
2015-07-13T20:44:55.445021+00:00 heroku[web.1]: State changed from starting to up
2015-07-13T20:44:55.330940+00:00 app[web.1]: [info] p.c.s.NettyServer$ - Listening for HTTP on /0:0:0:0:0:0:0:0:8626
...
```

Вы также можете скинуть логи в обычный файл. Это полезно для отладки:

```bash
$ heroku logs -t --app warm-frost-1289
2015-07-13T20:44:47.358320+00:00 heroku[web.1]: Starting process with command `target/universal/stage/bin/myapp -Dhttp.port=${PORT}`
2015-07-13T20:44:49.750860+00:00 app[web.1]: Picked up JAVA_TOOL_OPTIONS: -Xmx384m -Xss512k -Dfile.encoding=UTF-8
2015-07-13T20:44:52.297033+00:00 app[web.1]: [warn] application - Logger configuration in conf files is deprecated and has no effect. Use a logback configuration file instead.
2015-07-13T20:44:54.960105+00:00 app[web.1]: [info] p.a.l.c.ActorSystemProvider - Starting application default Akka system: application
2015-07-13T20:44:55.066582+00:00 app[web.1]: [info] play.api.Play$ - Application started (Prod)
2015-07-13T20:44:55.445021+00:00 heroku[web.1]: State changed from starting to up
2015-07-13T20:44:55.330940+00:00 app[web.1]: [info] p.c.s.NettyServer$ - Listening for HTTP on /0:0:0:0:0:0:0:0:8626
...
```

Выглядит хорошо. Мы теперь можем посетить приложение, выполнив:

```bash
$ heroku open
```

## Развертывание с помощью плагина sbt-heroku

Плагин Heroku sbtутилизирует API для предоставления прямого развертывания предупакованного отдельного веб-приложения на Heroku. Это может быть предпочтительным подходом для приложений, которые долго компилируются, или нуждаются в развертывании с сервера непрерывной интеграции (Continuous Integration server), такого как Travis CI или Jenkins.

### Добавление плагина

Чтобы включить плагин в ваш проект, добавьте следующее в ваш файл `project/plugins.sbt`:

```scala
addSbtPlugin("com.heroku" % "sbt-heroku" % "0.5.1")
```

Следующее, мы должны сконфигурировать имя Heroku приложения, которое плагин удет развертывать. Но для начала создайте приложение. Установите Heroku Toolbelt и запустите команду create.

```bash
$ heroku create
Creating obscure-sierra-7788... done, stack is cedar-14
http://obscure-sierra-7788.herokuapp.com/ | git@heroku.com:obscure-sierra-7788.git
```

Теперь добавьте что-нибудь вроде этого в в ваш `build.sbt`, но замените obscure-sierra-7788 на имя приложения, которое вы создали (или вы можете это пропустить, если вы используйте Git локально).

```scala
herokuAppName in Compile := "obscure-sierra-7788"
```

Документация проекта sbt-heroku содержит детальное описание по [конфигурации работы плагина](https://github.com/heroku/sbt-heroku#configuring-the-plugin).

### Развертывание с помощью плагина 

С добавленным плагином, вы можете развернуть приложение на Heroku с помощью этой команды:

```bash
$  sbt stage deployHeroku
...
[info] -----> Packaging application...
[info]        - app: obscure-sierra-7788
[info]        - including: target/universal/stage/
[info] -----> Creating build...
[info]        - file: target/heroku/slug.tgz
[info]        - size: 30MB
[info] -----> Uploading slug... (100%)
[info]        - success
[info] -----> Deploying...
[info] remote:
[info] remote: -----> Fetching custom tar buildpack... done
[info] remote: -----> sbt-heroku app detected
[info] remote: -----> Installing OpenJDK 1.8... done
[info] remote: -----> Discovering process types
[info] remote:        Procfile declares types -> console, web
[info] remote:
[info] remote: -----> Compressing... done, 78.9MB
[info] remote: -----> Launching... done, v6
[info] remote:        https://obscure-sierra-7788.herokuapp.com/ deployed to Heroku
[info] remote:
[info] -----> Done
[success] Total time: 90 s, completed Aug 29, 2014 3:36:43 PM
```

И вы можете посетить ваше приложение с помощью вызова команды:

```bash
$ heroku open -a obscure-sierra-7788
```

Вы можете увидеть логи вашего запущеного приложения с помощью этой команды:

```bash
$ heroku logs -a obscure-sierra-7788
```

Обратите внимание, что если вы используете Git, вы можете пропускать опцию `-a` выше, поскольку имя приложения будет распознано из Git remote, который был добавлен в конфигурацию, когда вы выполняли `heroku create`.

## Соединение с базой данных

Heroku предоставляет набор реляционных и NoSQL баз данных с помощью [Heroku Add-ons](https://addons.heroku.com). Play приложения на Heroku автоматически снабжаются базой данных [Heroku Postgres](https://addons.heroku.com/heroku-postgresql).  Чтобы сконфигурировать ваше Play приложение для использования Heroku базы данных Postgres, для начала добавьте PostgreSQL JDBC драйвер в зависимости вашего приложения (`build.sbt`):

```scala
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
```

Затем, создайте новый файл в корневой директории вашего проекта с именем `Procfile` (с заглавной "P"), который содержит следующее (с заменой `myapp` на имя вашего приложения):

```txt
web: target/universal/stage/bin/myapp -Dhttp.port=${PORT} -Dplay.evolutions.db.default.autoApply=true -Ddb.default.url=${DATABASE_URL}
```

Это инструктирует Heroku, что для процесса с именем `web` будет запущен Play и перекроет `play.evolutions.db.default.autoApply`, `db.default.driver`, и `db.default.url` параметры конфигурации. Обратите внимание, что команда `Procfile` может быть максимум 255 символов в длину. В качестве альтернативы, используйте `-Dconfig.resource=` или `-Dconfig.file=` упомянутый на странице [[конфигурация production|ProductionConfiguration]].

Также, убедитесь, что `DATABASE_URL` находится в платформонезависимом формате:

```text
vendor://username:password@host:port/db
```

Play автоматически сковертирует это в JDBC URL для вас, если вы используйте один из встроенных пулов соединений. Но другие библиотеки баз данных и фреймворков, такие как Slick или Hibernate, могут не поддерживать данный формат нативно.
В этом случае вы можете попробовать экспериментаьлный `JDBC_DATABASE_URL` вместо `DATABASE_URL` в конфигурации вроде этой:

```text
db.default.url=${?JDBC_DATABASE_URL}
db.default.username=${?JDBC_DATABASE_USERNAME}
db.default.password=${?JDBC_DATABASE_PASSWORD}
```

Обратите внимание, что создание Procfile на самом деле не обязательно для Heroku, так как Heroku подсмотрит в вашей директории conf приложения Play файл `application.conf` для того чтобы определить, что это приложение Play.

## Ресурсы для дальнейшего изучения

* [Getting Started with Scala and Play on Heroku](https://devcenter.heroku.com/articles/getting-started-with-scala)
* [Deploying Scala and Play Applications with the Heroku sbt Plugin](https://devcenter.heroku.com/articles/deploying-scala-and-play-applications-with-the-heroku-sbt-plugin)
* [Using Node.js to Perform JavaScript Optimization for Play and Scala Applications](https://devcenter.heroku.com/articles/using-node-js-to-perform-javascript-optimization-for-play-and-scala-applications)
* [Deploy Scala and Play Applications to Heroku from Travis CI](https://devcenter.heroku.com/articles/deploy-scala-and-play-applications-to-heroku-from-travis-ci)
* [Deploy Scala and Play Applications to Heroku from Jenkins CI](https://devcenter.heroku.com/articles/deploy-scala-and-play-applications-to-heroku-from-jenkins-ci)
* [Running a Remote sbt Console for a Scala or Play Application](https://devcenter.heroku.com/articles/running-a-remote-sbt-console-for-a-scala-or-play-application)
* [Using WebSockets on Heroku with Java and the Play Framework](https://devcenter.heroku.com/articles/play-java-websockets)
* [Seed Project for Play and Heroku](https://github.com/jkutner/play-heroku-seed)
* [Play Tutorial for Java](https://github.com/jamesward/play2torial/blob/master/JAVA.md)
* [Getting Started with Play, Scala, and Squeryl](https://www.artima.com/articles/play2_scala_squeryl.html)
* [Edge Caching With Play, Heroku, and CloudFront](http://www.jamesward.com/2012/08/08/edge-caching-with-play2-heroku-cloudfront)
* [Optimizing Play for Database-Driven Apps](http://www.jamesward.com/2012/06/25/optimizing-play-2-for-database-driven-apps)
* [Play App with a Scheduled Job on Heroku](https://github.com/jamesward/play2-scheduled-job-demo)
* [Using Amazon S3 for File Uploads with Java and Play](https://devcenter.heroku.com/articles/using-amazon-s3-for-file-uploads-with-java-and-play-2)
