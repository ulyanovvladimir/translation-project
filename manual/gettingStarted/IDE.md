<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# Установка предпочтительной IDE

Работать с Play просто. Вам даже не нужна интеллектуальная IDE, поскольку Play компилирует и обновляет произведенные вами изменения исходных файлов автоматически, поэтому вы легко можете работать в простом текстовом редакторе.

Однако, использование современной Java или Scala IDE предоставляет крутые возможности повышения производительности, такие как авто-дополнение, компиляция на лету, помощь в отладке и рефакторинге.

## Eclipse

### Установка sbteclipse

Play требует [sbteclipse](https://github.com/typesafehub/sbteclipse) 4.0.0 или новее.

```scala
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")
```

Вы должны `скомпилировать` ваш проект перед запуском команды `eclipse`. Вы можете форсировать компиляцию во время запуска команды `eclipse` добавив следующую настройку:

```scala
// Скомпилировать проект перед генерацией файлов Eclipse, чтобы сгенерированные .scala или .class файлы для видов и маршрутов присутствовали
EclipseKeys.preTasks := Seq(compile in Compile)
```

Если в вашем проекте есть Scala исходники вам потребуется установить [Scala IDE](http://scala-ide.org/).

Если вы не хотите устанавливать Scala IDE и у вас только исходники на Java, тогда вы можете установить следующее:

```scala
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           // Java проект. Не ожидаем Scala IDE
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)  // Использовать .class файлы вместо сгенерированных .scala файлов для видов и маршрутов 
```

### Генерация конфигурации

Play предоставляет команду для упрощения конфигурации [Eclipse](https://eclipse.org/). Чтобы трансформировать To transform a Play application into a working Eclipse project, use the `eclipse` command:

```bash
[my-first-app] $ eclipse
```

Если вы хотите скачать доступные исходные jar-ки (это будет дольше и возможно, что некоторые исходники будут отсутствовать):

```bash
[my-first-app] $ eclipse with-source=true
```

> Обратите внимание, если ы используете подпроекты с агрегированием вам потребуется установить `skipParents` подходящим образом в `build.sbt`:

```scala
EclipseKeys.skipParents in ThisBuild := false
```

или через консоль, наберите:

```bash
[my-first-app] $ eclipse skip-parents=false
```

Затем вам нужно проимпортировать приложение в ваш Workspace с помощью меню **File/Import/General/Existing project¦**  (сперва скомпилируйте ваше приложение).

[[images/eclipse.png]] 

Для отладки стартуйте ваше приложение с помощью `activator -jvm-debug 9999 run` и в  Eclipse по правой кнопке мыши на проекте выберите **Debug As**, **Debug Configurations**. В диалоге **Debug Configurations**, по правой кнопке мыши на **Remote Java Application** выберите **New**. Измените **Port** на 9999 и нажмите **Apply**. С этого момента вы можете нажать **Debug** для соединения с запущенным приложением. Остановка отладочной сессии не приведет к остановке сервера.

> **Подсказка**: Вы можете запустить ваше приложение используя `~run` чтобы включить непосредственную компиляцию при изменении файловисходников. Таким способом файлы шаблонов scala автоматически обнаруживаются, когда вы создаете новый шаблон во `view` и автоматически компилируются, когда файлы изменяются.

Если вы делаете какие-то важные изменения вашего приложения, такие как изменения classpath, используйте команду `eclipse` еще раз чтобы перегенерировать конфигурационные файлы.

> **Подсказка**: Не коммитьте конфигурацию Eclipse, когда вы раобтаете в команде!

Сгенерированные конфигурационные файлы, содержат ссылки на абсолютные пути до установленного фреймворка. Они специфичны для вашей устаовки. Когда работаете в команде, каждый рзработчик должен держать приватными файлы конфигурации Eclipse.

## IntelliJ

[Intellij IDEA](https://www.jetbrains.com/idea/) позволяет вам быстро создавать Play приложения без использования командной строки. Вам не нужно конфигурировать ничего вне вашей IDE, SBT build tool позаботится о скачивании необходимых библиотек, разрешении зависимостей и сборке проекта.

Перед тем как вы начнете создание проиложения Play в IntelliJ IDEA, убедитесь, что последний [Scala Плагин](http://www.jetbrains.com/idea/features/scala.html) установлен и включен в IntelliJ IDEA. Даже если вы не разработаываете на Scala, он поможет с движком шаблонов и еще с разрешением зависимостей.

Чтобы создать приложение Play:

1. Откройте мастер ***New Project***, выберите ***Play 2.x*** под секцией ***Scala*** и нажмите ***Next***.
2. Введите информацию о вашем проекте и нажмите ***Finish***.

IntelliJ IDEA создаст пустое приложение, используя SBT.

В настоящий момент для Play 2.4.x вместо использования мастер создания нового проекта мы предлагаем создавать его с помощью Активатора и затем Импортировать его в IntelliJ.

Вы также можете импортировать существующий проект.

Чтобы проимпортировать проект Play:

1. Откройте мастер Project, выберите ***Import Project***.
2. В окне, которое откроется, выберите проект, который вы хотите проимпортировать и нажмите ***OK***.
3. На следующей странице мастера выберите опцию ***Import project from external model*** , выберите ***SBT project*** и нажмите ***Next***. 
4. На следующей странице мастера выберите дополнительные опции импорта и нажмите ***Finish***. 

Выберите структуру проекта, убедитесь, что скачаны все необходимые зависимости. Вы можете использовать помощь в кодировании, навигацию и возможности анализа кода на лету.

Вы можете запустить созданное приложение и посмотреть результат в браузере по адресу `http://localhost:9000`. Чтобы запустить приложение Play:

1. Создайте новую конфигурацию Run  -- Из главного меню выберите Run -> Edit Configurations
2. Нажмите на + чтобы добавить новую конфигурацию
3. Из списка конфигураций выберите "SBT Task"
4. В поле ввода "tasks", просто введите "run"
5. Примените изменения и нажмите OK.
6. Теперь вы можете использовать "Run" из главногоо меню Run и запустить ваше приложение

ВЫ можете легко запустить отладочную сессию для приложения Play, используя дефолтные настройки Run/Debug .

Для более детальной информации, см. Play Framework 2.x руководство по следующему URL:

<https://confluence.jetbrains.com/display/IntelliJIDEA/Play+Framework+2.0> 

### Навигация от страницы с ошибкой до исходников 

Используя конфигурационную опцию `play.editor`, вы можете установить Play чтобы добавить гиперссылки на страницу с ошибкой. С этого момента вы можете легко переходить от страниц с ошибкой к IntelliJ, прямо в ваши исходники (вам неоходимо сначала установить плагин RemoteCall <https://github.com/Zolotov/RemoteCall> для IntelliJ).

Просто установите плагин Remote Call и запустите ваше приложение со следующими опциями:
`-Dplay.editor=http://localhost:8091/?message=%s:%s -Dapplication.mode=dev`


## Netbeans

### Generate Configuration

Play does not have native [Netbeans](https://netbeans.org/) project generation support at this time, but there is a Scala plugin for NetBeans which can help with both Scala language and SBT:

<https://github.com/dcaoyuan/nbscala>

There is also a SBT plugin to create Netbeans project definition:

<https://github.com/dcaoyuan/nbsbt>

## ENSIME

### Install ENSIME

Follow the installation instructions at <https://github.com/ensime/ensime-emacs>.

### Generate configuration

Edit your project/plugins.sbt file, and add the following line (you should first check <https://github.com/ensime/ensime-sbt> for the latest version of the plugin):

```scala
addSbtPlugin("org.ensime" % "ensime-sbt" % "0.1.5-SNAPSHOT")
```

Start Play:

```bash
$ activator
```

Enter 'ensime generate' at the play console. The plugin should generate a .ensime file in the root of your Play project.

```bash
$ [MYPROJECT] ensime generate
[info] Gathering project information...
[info] Processing project: ProjectRef(file:/Users/aemon/projects/www/MYPROJECT/,MYPROJECT)...
[info]  Reading setting: name...
[info]  Reading setting: organization...
[info]  Reading setting: version...
[info]  Reading setting: scala-version...
[info]  Reading setting: module-name...
[info]  Evaluating task: project-dependencies...
[info]  Evaluating task: unmanaged-classpath...
[info]  Evaluating task: managed-classpath...
[info] Updating {file:/Users/aemon/projects/www/MYPROJECT/}MYPROJECT...
[info] Done updating.
[info]  Evaluating task: internal-dependency-classpath...
[info]  Evaluating task: unmanaged-classpath...
[info]  Evaluating task: managed-classpath...
[info]  Evaluating task: internal-dependency-classpath...
[info] Compiling 5 Scala sources and 1 Java source to /Users/aemon/projects/www/MYPROJECT/target/scala-2.9.1/classes...
[info]  Evaluating task: exported-products...
[info]  Evaluating task: unmanaged-classpath...
[info]  Evaluating task: managed-classpath...
[info]  Evaluating task: internal-dependency-classpath...
[info]  Evaluating task: exported-products...
[info]  Reading setting: source-directories...
[info]  Reading setting: source-directories...
[info]  Reading setting: class-directory...
[info]  Reading setting: class-directory...
[info]  Reading setting: ensime-config...
[info] Wrote configuration to .ensime
```

### Start ENSIME

From Emacs, execute M-x ensime and follow the on-screen instructions.

That's all there is to it. You should now get type-checking, completion, etc. for your Play project. Note, if you add new library dependencies to your play project, you'll need to re-run "ensime generate" and re-launch ENSIME.

### More Information

Check out the ENSIME README at <https://github.com/ensime/ensime-emacs>. If you have questions, post them in the ensime group at <https://groups.google.com/forum/?fromgroups=#!forum/ensime>.

## All Scala Plugins if needed

Scala is a newer programming language, so the functionality is provided in plugins rather than in the core IDE.

1. Eclipse Scala IDE: <http://scala-ide.org/>
2. NetBeans Scala Plugin: <https://github.com/dcaoyuan/nbscala>
3. IntelliJ IDEA Scala Plugin: <http://confluence.jetbrains.net/display/SCA/Scala+Plugin+for+IntelliJ+IDEA>
4. IntelliJ IDEA's plugin is under active development, and so using the nightly build may give you additional functionality at the cost of some minor hiccups.
5. Nika (11.x) Plugin Repository: <https://www.jetbrains.com/idea/plugins/scala-nightly-nika.xml>
6. Leda (12.x) Plugin Repository: <https://www.jetbrains.com/idea/plugins/scala-nightly-leda.xml>
7. IntelliJ IDEA Play plugin (available only for Leda 12.x): <http://plugins.intellij.net/plugin/?idea&pluginId=7080>
8. ENSIME - Scala IDE Mode for Emacs: <https://github.com/aemoncannon/ensime>
(see below for ENSIME/Play instructions)
