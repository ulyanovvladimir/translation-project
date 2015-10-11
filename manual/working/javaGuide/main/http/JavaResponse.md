<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# ��������������� �������

## ��������� Content-Type �� ���������

��� �������� ���������� ������������� �������� �� Java-��������, ������� �� ����������� � �������� ����.

��������:

@[text-content-type](code/javaguide/http/JavaResponse.java)

������������� ��������� `Content-Type` ��������� � `text/plain`, � �� ����� ���:

@[json-content-type](code/javaguide/http/JavaResponse.java)

��������� ��������� `Content-Type` � `application/json`.

��� ����� ������, �� ������ �� �������� �������� �������� ���. ������ ����������� ����� `as(newContentType)` �� ���������� ��� �������� ������ �������� ���������� � ������ ���������� `Content-Type`:

@[custom-content-type](code/javaguide/http/JavaResponse.java)

## ��������� ����������� HTTP �������

@[response-headers](code/javaguide/http/JavaResponse.java)

�������� ��������, ��� ��������� ���������� HTTP ������������� ������� ����� ���������� ��������.

## ��������� � ������ cookies

Cookies  - ����� ���� ����������� ����� ���������� HTTP, Play ������������� ��������� �������� ��� ���������.

�� ������ ������ �������� Cookie � HTTP �����:

@[set-cookie](code/javaguide/http/JavaResponse.java)

���� ��� ����� ������ ����������, ������� ����, �����, ���� ��������, ���������� �� ����������, � ������ �� ���� ���������� ������ ���� HTTP, �� ������ ������� ��� � ������������� ��������:

@[detailed-set-cookie](code/javaguide/http/JavaResponse.java)

����� �������� Cookie ����� ����������� � ��� ��������:

@[discard-cookie](code/javaguide/http/JavaResponse.java)

���� �� ���������� ���� ��� �����, ����� �������������� cookie, ���������, ��� �� �������������� ��� �� ���� ��� ����� ����� ��������� cookie, ��������� ������� ������� ��� ������ ��� ���������� �����, ���� � ������.

## ����������� ��������� �������� ��� ��������� �����������

��� ��������� HTTP ������� ����� ����� ��������� ���������  ���������� ��������. Play ��������� ���� �� ���, ��������� �� ��������� `utf-8`.

��������� ������������ � ��� ��������������� ���������� ������ � ��������������� ����� ��� �������� �� ���� � ��� ���������� ����������� `;charset=xxx` ���������� � ��������� `Content-Type`.

��������� ����� ���� ����������, ����� �� ����������� �������� `Result`:

@[charset](code/javaguide/http/JavaResponse.java)