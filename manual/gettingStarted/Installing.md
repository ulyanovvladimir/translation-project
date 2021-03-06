<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# ������������ Play

## ����������

��� ���������� ����� JDK 1.8 (��� �����) ������������� �� ����� ������ (��. [����� ����� �����������](#JDK-installation)).

## ������� �����

1. **�������** ��������� ������ [Typesafe Activator](https://typesafe.com/get-started).
2. **�������** ����� � �� ��������������, ��� � ��� ���� ������ �� ������.
3. **��������** ������� ���������� � ������� ������� `cd activator*` (���� � ������� ����-���������)
4. **���������** ��������� � ������� ������� `activator ui` (���� � ������� ����-���������)
5. **��������** ��� �� ������ [http://localhost:8888](http://localhost:8888)

�� ����� �� �������� ������������ � ������ �������� ����������, ������� �� ����������� ��������. ��� �������� ������ ����������� ������ **play-java**.


### ��������� ������

����� ������������ play �� ������ �������������� � ����� �������� �������, �������� �����
**activator** � ���� ���������� path (��. [����� ����� �����������](#Add-Executables-to-Path)).

�������� `my-first-app` �� ������ ������� `play-java` �������� ��� �� ������ ���:

```bash
activator new my-first-app play-java
cd my-first-app
activator run
```

[http://localhost:9000](http://localhost:9000) - ����� ������ � ������ ����������.

������ �� ������ �������� � Play!

## ����� ����� �����������

��� �������� ����������� ����� ���� � ����� ����������� ��� ���� ����� ���������� Play! �� ���� �������. 

### ��������� JDK 

���������, ��� � ��� ����������� JDK (Java Development Kit) ������� 1.8 ��� ����. ������ ����������� ��� ������� ��� �����������:

```bash
java -version
javac -version
```

���� � ��� ��� JDK, ��� ���������� ��� ����������:

1. **MacOS**, Java ��������, �� ���, ��������, ����������� [�������� �� ��������� ������](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
2. **Linux**, ����������� ���� ��������� Oracle JDK ��� OpenJDK (�� ����������� �� gcj). 
3. **Windows** ������ �������� � ��������� [��������� JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html).


### �������� ���� �� JDK � PATH

��� ��������, ��� ������� �������� ���������� � ������������� ����������� � ���� ���������� ����� ��������� `PATH`.

�� **Unix**, ����������� `export PATH=/path/to/activator:$PATH`

�� **Windows**, �������� `;C:\path\to\activator` � ���� ���������� ����� ���������`PATH`. �� ����������� ����, ���������� �������.

### ���������� �� ������ ������

#### Unix

� �������� ������ `activator` ����� ��������� ����� � ����������, ���� ����������, ������� �� �������������� � `/opt`, `/usr/local` ��� � ����� ������ �����, ��� ��� ��������� �������������� ���������� �� ������.

���������, ��� ������ `activator` �����������. ���� ��� ��������� `chmod u+x /path/to/activator`.

### ��������� Proxy

���� �� �� ������-��������, ���������, ��� ��������� ��� � ������� `set HTTP_PROXY=http://<host>:<port>` � Windows ��� `export  HTTP_PROXY=http://<host>:<port>` � UNIX.
