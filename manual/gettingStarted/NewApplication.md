<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# ������� ����� ����������

## �������� ����� ���������� � ������� ������� activator

������� `activator` ����� ���� ������������ ��� ���� ����� ������� ����� Play ����������.  ��������� ��������� ��� ������� ������, �� ������� ������ ������������ ���� ����������. ��� ��������� �������� Play ����� ���� ��������: `play-scala` ��� ���������� Play, ������������ �� Scala, � `play-java` ��� ���������� Play, ������������ �� Java.

> �������� ��������, ��� ������ ������� ��� ��� Scala, ��� � ��� Java �� ��������, �� �� �� ������ ������� ���� � �����������. ��������, �� ������ ������� ����� ����������, ���������  Java ������ ��-��������� � ������ ��������� Scala ���, ����� ��������.

����� ������� ���������  Play ���������� �� Scala, ���������:

```bash
$ activator new my-first-app play-scala
```

����� ������� ���������  Play ���������� �� Java, ���������:

```bash
$ activator new my-first-app play-java
```

� ����� ������, �� ������ �������� `my-first-app` �� ����� ������ ���, ������� �� ������ ������������ ��� ������ ����������. ��������� ����� ������������ ��� � �������� ����� ����������, � ������� ����� �������� ��������� ����������. ���� ��������, �� ������ ������������ �������� ��� ���.

[[images/activatorNew.png]]

> ���� �� ������ ������������ ������ ������� ����������, �� ������ ������� ���, �������� ������� `activator new`. � ������ ������, � ��� ����� ��������� ��� ���������� � ����� ������������ ���� ������� ���������� ������.

����� ���������� ����� �������, �� ������ ������������ ������� `activator` ��� ���, ����� ����� � [[������� Play|PlayConsole]].

```bash
$ cd my-first-app
$ activator
```

## ������� ����� ���������� � ������� Activator UI

����� ���������� Play ����� ���� ������� � ������� Activator UI.  ����� ������������ Activator UI, ���������:

```bash
$ activator ui
```

�� ������ �������� ������������ �� ������������� Activator UI [�����](https://typesafe.com/activator/docs).

## ������� ����� ���������� ��� ���������� Activator

��� ����� ������� ����� ���������� Play ��� ��������� ����������, ��������� sbt ��������.

> ��� ������ ���������� [sbt](http://www.scala-sbt.org/) ��� �������������.

�������� ����� ���������� ��� ������ ���������� � ��������������� ��� build ������ � ����� ������������.

� `project/plugins.sbt`, ��������:

```scala
// Typesafe �����������
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

// ����������� Play sbt ������ ��� �������� Play
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "%PLAY_VERSION%")
```

���������, ��� �� �������� `%PLAY_VERSION%` �� ���������� �����, ������� �� ������ ������������. ���� �� ������ ������������ ������ ������, ��� ����������� ���������� �������������� �������� ������������:

```scala
// Typesafe snapshots
resolvers += "Typesafe Snapshots" at "https://repo.typesafe.com/typesafe/snapshots/"
```

����� ��������� � ���, ��� ������������ ���������� ������ sbt, �������� ��������� ������ � `project/build.properties`:

```
sbt.version=0.13.8
```

� `build.sbt` ��� Java ��������:

```scala
name := "my-first-app"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
```

...��� ��� Scala ��������:

```scala
name := "my-first-app"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
```

�� ������ ����� ��������� sbt ������� � ���� ����������:

```bash
$ cd my-first-app
$ sbt
```

sbt �������� ��� ������ � ������� �����������.
 the proper sbt version is used, make sure you have the following in `project/build.properties`:

```
sbt.version=0.13.8
```

� `build.sbt` ��� Java ��������:

```scala
name := "my-first-app"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
```

...��� ��� Scala ��������:

```scala
name := "my-first-app"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
```

�� ������ ����� ��������� ������� sbt � ���� ����������:

```bash
$ cd my-first-app
$ sbt
```

sbt �������� ������ � ������� ��� �����������.
