<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# Action-�, ����������� � ����������

## ��� ����� Action?

������� ����� ��������, ���������� Play ����������� �������������� `Action`-���. 

Action - � ������ ����� Java �����, ������� ������������ ��������� ������� � ���������� ���������, ������� ����� ��������� �������.

@[simple-action](code/javaguide/http/JavaActions.java)


Action ���������� `play.mvc.Result` ��������, �������������� HTTP ����� ��� �������� �� ���-������. � ���� ������� `ok` ������������ ����� **200 OK**, ����������  ���� ������ � ������� **text/plain**.

## ����������� 

����������� - ��� ������ �����, ��� ����� `play.mvc.Controller`, ������� ���������� ��������� ������ ��� ������ � Action-���.

@[full-controller](code/javaguide/http/full/Application.java)

���������� ��������� ��� ����������� action-� - ��� ����� ��� ����������, ������� ���������� �������� `Result` ��� �������� ����.

Action-����� ����� ����� ����� ���������:

@[params-action](code/javaguide/http/JavaActions.java)

��� ��������� ����� ����������� `Router`-�� � ����� ����������� ��������� �� ULR �������. �������� ���������� ����� ���� ��������� ���� �� URL ����, ���� �� ������ URL �������.

## ����������

������� ������ � ������� �����������: HTTP ��������� � ����� �������, ������� HTTP ���������� � ����� ��� �������� �� ���-������.

��� ��������� ������������ ������� `play.mvc.Result`, � ����� `play.mvc.Results` ������������� ��������� ������� ��� ������������ ����������� HTTP �����������, ����� ��� ����� `ok`, ������� ��� ����������� � ���������� �������:

@[simple-result](code/javaguide/http/JavaActions.java)

��� ��������� �������� ��������� �����������:

@[other-results](code/javaguide/http/JavaActions.java)

��� ��� ������� ����� ���� ������� � ������ `play.mvc.Results`.

## ��������������� - ����� ������� ���������� 

��������������� �������� �� ������ URL - ����� ������ ��� �������� ����������. ������, ��� ���������� �� ����� ���� ������.

There are several helpers available to create redirect results:

@[redirect-action](code/javaguide/http/JavaActions.java)

�� ��������� ������������ ��� ������ `303 SEE_OTHER`, �� �� ������ ���������� ����� ����������� ��� �������:

@[temporary-redirect-action](code/javaguide/http/JavaActions.java)
