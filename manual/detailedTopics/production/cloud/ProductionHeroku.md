<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# ������������� � Heroku

[Heroku](https://www.heroku.com/) - ��� �������� ��������� ����������, ������ ������ � ������������� ����� ���-����������.

��� ������:

1. [���������� Heroku Toolbelt](https://toolbelt.heroku.com)
2. [��������������� ������� Heroku](https://id.heroku.com/signup)

���� ��� ������� ������������� � Heroku:

*  �������� �� ��������� [Git �����������](https://devcenter.heroku.com/articles/deploying-scala).
*  ������������� ������� [sbt-heroku](https://devcenter.heroku.com/articles/deploying-scala-and-play-applications-with-the-heroku-sbt-plugin).

## ������������ ����� ��������� Git �����������

### ��������� ���� ���������� � git

```bash
$ git init
$ git add .
$ git commit -m "init"
```

### �������� ����� ���������� �� Heroku

```bash
$ heroku create
Creating warm-frost-1289... done, stack is cedar-14
http://warm-frost-1289.herokuapp.com/ | git@heroku.com:warm-frost-1289.git
Git remote heroku added
```

��� ������� ����� ��������� � HTTP (� HTTPS) �������� ������ � �������� ������ Git ��� ������ ����������.  �������� ����� Git endpoint ��������������� ��� ����� ��������� ������ ����������� � ������ `heroku` � ������������ ������ Git �����������.

### ������������� ������ ����������

����� ���������� ���� ���������� �� Heroku, ����������� Git ����� ������ ��� �� ��������� ����������� `heroku`:

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

Heroku �������� `sbt stage` ����� ����������� ���� ����������. � ������ ������������� ����� ����������� ���������� ���� ������������, ��� ������ ��������� ����� (�� ��� ����� ����������� ��� ������� �������������).

���� �� ����������� RequireJS � �� ����������, ��� ���� ���������� �������� �� ���� ����:

```bash
[info] Optimizing JavaScript with RequireJS
```

����� ���������� ��������� ��������� ���� � [������������� Node.js ��� ���������� JavaScript ����������� ��� Play � Scala ����������](https://devcenter.heroku.com/articles/using-node-js-to-perform-javascript-optimization-for-play-and-scala-applications) ��  Heroku Dev Center. ��� ����������� ������� ������������������ Javascript ������.

### ���������, ��� ���� ���������� ���� ����������

������ ������� �������� ��������� ��������� ����������:

```bash
$ heroku ps
=== web (Free): `target/universal/stage/bin/sample-app -Dhttp.port=${PORT}`
web.1: up 2015/01/09 11:27:51 (~ 4m ago)
```

��� ������� ������� � ��������. �� ������ ���������� ���� ��� ��������� ��������� ����������:

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

�� ����� ������ ������� ���� � ������� ����. ��� ������� ��� �������:

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

�������� ������. �� ������ ����� �������� ����������, ��������:

```bash
$ heroku open
```

## ������������� � ������� ������� sbt-heroku

������ Heroku sbt����������� API ��� �������������� ������� ������������� ���������������� ���������� ���-���������� �� Heroku. ��� ����� ���� ���������������� �������� ��� ����������, ������� ����� �������������, ��� ��������� � ������������� � ������� ����������� ���������� (Continuous Integration server), ������ ��� Travis CI ��� Jenkins.

### ���������� �������

����� �������� ������ � ��� ������, �������� ��������� � ��� ���� `project/plugins.sbt`:

```scala
addSbtPlugin("com.heroku" % "sbt-heroku" % "0.5.1")
```

���������, �� ������ ���������������� ��� Heroku ����������, ������� ������ ���� ������������. �� ��� ������ �������� ����������. ���������� Heroku Toolbelt � ��������� ������� create.

```bash
$ heroku create
Creating obscure-sierra-7788... done, stack is cedar-14
http://obscure-sierra-7788.herokuapp.com/ | git@heroku.com:obscure-sierra-7788.git
```

������ �������� ���-������ ����� ����� � � ��� `build.sbt`, �� �������� obscure-sierra-7788 �� ��� ����������, ������� �� ������� (��� �� ������ ��� ����������, ���� �� ����������� Git ��������).

```scala
herokuAppName in Compile := "obscure-sierra-7788"
```

������������ ������� sbt-heroku �������� ��������� �������� �� [������������ ������ �������](https://github.com/heroku/sbt-heroku#configuring-the-plugin).

### ������������� � ������� ������� 

� ����������� ��������, �� ������ ���������� ���������� �� Heroku � ������� ���� �������:

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

� �� ������ �������� ���� ���������� � ������� ������ �������:

```bash
$ heroku open -a obscure-sierra-7788
```

�� ������ ������� ���� ������ ���������� ���������� � ������� ���� �������:

```bash
$ heroku logs -a obscure-sierra-7788
```

�������� ��������, ��� ���� �� ����������� Git, �� ������ ���������� ����� `-a` ����, ��������� ��� ���������� ����� ���������� �� Git remote, ������� ��� �������� � ������������, ����� �� ��������� `heroku create`.

## ���������� � ����� ������

Heroku ������������� ����� ����������� � NoSQL ��� ������ � ������� [Heroku Add-ons](https://addons.heroku.com). Play ���������� �� Heroku ������������� ���������� ����� ������ [Heroku Postgres](https://addons.heroku.com/heroku-postgresql).  ����� ���������������� ���� Play ���������� ��� ������������� Heroku ���� ������ Postgres, ��� ������ �������� PostgreSQL JDBC ������� � ����������� ������ ���������� (`build.sbt`):

```scala
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
```

�����, �������� ����� ���� � �������� ���������� ������ ������� � ������ `Procfile` (� ��������� "P"), ������� �������� ��������� (� ������� `myapp` �� ��� ������ ����������):

```txt
web: target/universal/stage/bin/myapp -Dhttp.port=${PORT} -Dplay.evolutions.db.default.autoApply=true -Ddb.default.url=${DATABASE_URL}
```

��� ������������� Heroku, ��� ��� �������� � ������ `web` ����� ������� Play � ��������� `play.evolutions.db.default.autoApply`, `db.default.driver`, � `db.default.url` ��������� ������������. �������� ��������, ��� ������� `Procfile` ����� ���� �������� 255 �������� � �����. � �������� ������������, ����������� `-Dconfig.resource=` ��� `-Dconfig.file=` ���������� �� �������� [[������������ production|ProductionConfiguration]].

�����, ���������, ��� `DATABASE_URL` ��������� � �������������������� �������:

```text
vendor://username:password@host:port/db
```

Play ������������� ������������ ��� � JDBC URL ��� ���, ���� �� ����������� ���� �� ���������� ����� ����������. �� ������ ���������� ��� ������ � �����������, ����� ��� Slick ��� Hibernate, ����� �� ������������ ������ ������ �������.
� ���� ������ �� ������ ����������� ����������������� `JDBC_DATABASE_URL` ������ `DATABASE_URL` � ������������ ����� ����:

```text
db.default.url=${?JDBC_DATABASE_URL}
db.default.username=${?JDBC_DATABASE_USERNAME}
db.default.password=${?JDBC_DATABASE_PASSWORD}
```

�������� ��������, ��� �������� Procfile �� ����� ���� �� ����������� ��� Heroku, ��� ��� Heroku ���������� � ����� ���������� conf ���������� Play ���� `application.conf` ��� ���� ����� ����������, ��� ��� ���������� Play.

## ������� ��� ����������� ��������

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
