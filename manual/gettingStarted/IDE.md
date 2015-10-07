<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# ��������� ���������������� IDE

�������� � Play ������. ��� ���� �� ����� ���������������� IDE, ��������� Play ����������� � ��������� ������������� ���� ��������� �������� ������ �������������, ������� �� ����� ������ �������� � ������� ��������� ���������.

������, ������������� ����������� Java ��� Scala IDE ������������� ������ ����������� ��������� ������������������, ����� ��� ����-����������, ���������� �� ����, ������ � ������� � ������������.

## Eclipse

### ��������� sbteclipse

Play ������� [sbteclipse](https://github.com/typesafehub/sbteclipse) 4.0.0 ��� �����.

```scala
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")
```

�� ������ `��������������` ��� ������ ����� �������� ������� `eclipse`. �� ������ ����������� ���������� �� ����� ������� ������� `eclipse` ������� ��������� ���������:

```scala
// �������������� ������ ����� ���������� ������ Eclipse, ����� ��������������� .scala ��� .class ����� ��� ����� � ��������� ��������������
EclipseKeys.preTasks := Seq(compile in Compile)
```

���� � ����� ������� ���� Scala ��������� ��� ����������� ���������� [Scala IDE](http://scala-ide.org/).

���� �� �� ������ ������������� Scala IDE � � ��� ������ ��������� �� Java, ����� �� ������ ���������� ���������:

```scala
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           // Java ������. �� ������� Scala IDE
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)  // ������������ .class ����� ������ ��������������� .scala ������ ��� ����� � ��������� 
```

### ��������� ������������

Play ������������� ������� ��� ��������� ������������ [Eclipse](https://eclipse.org/). ����� ���������������� To transform a Play application into a working Eclipse project, use the `eclipse` command:

```bash
[my-first-app] $ eclipse
```

���� �� ������ ������� ��������� �������� jar-�� (��� ����� ������ � ��������, ��� ��������� ��������� ����� �������������):

```bash
[my-first-app] $ eclipse with-source=true
```

> �������� ��������, ���� � ����������� ���������� � �������������� ��� ����������� ���������� `skipParents` ���������� ������� � `build.sbt`:

```scala
EclipseKeys.skipParents in ThisBuild := false
```

��� ����� �������, ��������:

```bash
[my-first-app] $ eclipse skip-parents=false
```

����� ��� ����� ���������������� ���������� � ��� Workspace � ������� ���� **File/Import/General/Existing project�**  (������ ������������� ���� ����������).

[[images/eclipse.png]] 

��� ������� ��������� ���� ���������� � ������� `activator -jvm-debug 9999 run` � �  Eclipse �� ������ ������ ���� �� ������� �������� **Debug As**, **Debug Configurations**. � ������� **Debug Configurations**, �� ������ ������ ���� �� **Remote Java Application** �������� **New**. �������� **Port** �� 9999 � ������� **Apply**. � ����� ������� �� ������ ������ **Debug** ��� ���������� � ���������� �����������. ��������� ���������� ������ �� �������� � ��������� �������.

> **���������**: �� ������ ��������� ���� ���������� ��������� `~run` ����� �������� ���������������� ���������� ��� ��������� ����������������. ����� �������� ����� �������� scala ������������� ��������������, ����� �� �������� ����� ������ �� `view` � ������������� �������������, ����� ����� ����������.

���� �� ������� �����-�� ������ ��������� ������ ����������, ����� ��� ��������� classpath, ����������� ������� `eclipse` ��� ��� ����� ���������������� ���������������� �����.

> **���������**: �� ��������� ������������ Eclipse, ����� �� ��������� � �������!

��������������� ���������������� �����, �������� ������ �� ���������� ���� �� �������������� ����������. ��� ���������� ��� ����� ��������. ����� ��������� � �������, ������ ���������� ������ ������� ���������� ����� ������������ Eclipse.

## IntelliJ

[Intellij IDEA](https://www.jetbrains.com/idea/) ��������� ��� ������ ��������� Play ���������� ��� ������������� ��������� ������. ��� �� ����� ��������������� ������ ��� ����� IDE, SBT build tool ����������� � ���������� ����������� ���������, ���������� ������������ � ������ �������.

����� ��� ��� �� ������� �������� ����������� Play � IntelliJ IDEA, ���������, ��� ��������� [Scala ������](http://www.jetbrains.com/idea/features/scala.html) ���������� � ������� � IntelliJ IDEA. ���� ���� �� �� ��������������� �� Scala, �� ������� � ������� �������� � ��� � ����������� ������������.

����� ������� ���������� Play:

1. �������� ������ ***New Project***, �������� ***Play 2.x*** ��� ������� ***Scala*** � ������� ***Next***.
2. ������� ���������� � ����� ������� � ������� ***Finish***.

IntelliJ IDEA ������� ������ ����������, ��������� SBT.

� ��������� ������ ��� Play 2.4.x ������ ������������� ������ �������� ������ ������� �� ���������� ��������� ��� � ������� ���������� � ����� ������������� ��� � IntelliJ.

�� ����� ������ ������������� ������������ ������.

����� ���������������� ������ Play:

1. �������� ������ Project, �������� ***Import Project***.
2. � ����, ������� ���������, �������� ������, ������� �� ������ ���������������� � ������� ***OK***.
3. �� ��������� �������� ������� �������� ����� ***Import project from external model*** , �������� ***SBT project*** � ������� ***Next***. 
4. �� ��������� �������� ������� �������� �������������� ����� ������� � ������� ***Finish***. 

�������� ��������� �������, ���������, ��� ������� ��� ����������� �����������. �� ������ ������������ ������ � �����������, ��������� � ����������� ������� ���� �� ����.

�� ������ ��������� ��������� ���������� � ���������� ��������� � �������� �� ������ `http://localhost:9000`. ����� ��������� ���������� Play:

1. �������� ����� ������������ Run  -- �� �������� ���� �������� Run -> Edit Configurations
2. ������� �� + ����� �������� ����� ������������
3. �� ������ ������������ �������� "SBT Task"
4. � ���� ����� "tasks", ������ ������� "run"
5. ��������� ��������� � ������� OK.
6. ������ �� ������ ������������ "Run" �� ��������� ���� Run � ��������� ���� ����������

�� ������ ����� ��������� ���������� ������ ��� ���������� Play, ��������� ��������� ��������� Run/Debug .

��� ����� ��������� ����������, ��. Play Framework 2.x ����������� �� ���������� URL:

<https://confluence.jetbrains.com/display/IntelliJIDEA/Play+Framework+2.0> 

### ��������� �� �������� � ������� �� ���������� 

��������� ���������������� ����� `play.editor`, �� ������ ���������� Play ����� �������� ����������� �� �������� � �������. � ����� ������� �� ������ ����� ���������� �� ������� � ������� � IntelliJ, ����� � ���� ��������� (��� ��������� ������� ���������� ������ RemoteCall <https://github.com/Zolotov/RemoteCall> ��� IntelliJ).

������ ���������� ������ Remote Call � ��������� ���� ���������� �� ���������� �������:
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
