<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# ��������� �������� �����

������ ��� �� ������� �������� � ������� Play, �������� ������������ �� [[Play enhancer|PlayEnhancer]]. Play enhancer ���������� ������ ������� � ����� � Java �������, ��� ��� ��� �� ����� ������������ �� ��������������. �� ������ ������ ������������ ��� ��� ��������. ��� ��������������� ������� ������������� ������ ��������� ������� ������� � ����� ����� �������.

## ����������� �����

����� `play.data` �������� ��������� ������� ��� ��������� � ��������� ������ ������������ �� HTTP �����. ����� ������� ������ ���������� ����� - ��� ���������� `play.data.Form` ������� �������� �������� ��� ������������ �������:

@[user](code/javaguide/forms/u1/User.java)

@[create](code/javaguide/forms/JavaForms.java)

> **�������� ��������:** ���������� ������������ ������ [Spring data binder](https://docs.spring.io/spring/docs/3.0.x/reference/validation.html).

��� ����� ����� ������������ �������� �������� ���� `User` �� ������ ���� `HashMap<String,String>`:

@[bind](code/javaguide/forms/JavaForms.java)

���� ��� ������ ������, �� �� ������ ������������ ���������� ��������������� � ���������� �������:

@[bind-from-request](code/javaguide/forms/JavaForms.java)

## ����������� �����������

�� ������ ���������� �������������� �����������, ������� ����� ���������� � ������� ���� ���������� � ������� JSR-303 (Bean Validation) ���������:

@[user](code/javaguide/forms/u2/User.java)

> **���������:** ����� `play.data.validation.Constraints` �������� ��������� ���������� ��������� ��� ���������.

�� ����� ������ ���������� ������������ ��������� ����� � ������� ���������� ������ `validate` � ��� ������ �������� ������:

@[user](code/javaguide/forms/u3/User.java)

���������, ������� ������������ � ��������������� ������� ����� ����� ��������������� ��� ��������� �� ������.

����� `validate` ����� ������� �������� ��������� �����: `String`, `List<ValidationError>` ��� `Map<String,List<ValidationError>>`

����� `validate` ���������� ����� �������� �����������, ������������ �� ���������� � ������ ���� ��� ��������. ���� ��������� ��������, �� ������ ������� `null`. ������� ������ ��-`null` �������� (������ ������ ��� ������� ������) ��������������� ��� ������ ���������.

`List<ValidationError>` ����� ���� �������, ����� � ��� ���� �������������� ��������� ��� �����. ��������:

@[list-validate](code/javaguide/forms/JavaForms.java)

������������� `Map<String,List<ValidationError>>` ������ �� `List<ValidationError>` ��� ������� ���� �������� ���� ������ ����� `email` � ��������������� �������.

## ��������� ������ ���������

�������, �� ������ ���������� �����������, ����� ��� ����� ����������� ���������� ������ ���������.

@[handle-errors](code/javaguide/forms/JavaForms.java)

������, ��� �������� ����, ����� ������ �������� � �����. ���������� ������ ����� ���� ����������� ��������� �������:

@[global-errors](code/javaguide/forms/view.scala.html)

������ ��� ��������� ���� ����� ���� ����������� � ��������� ������:

@[field-errors](code/javaguide/forms/view.scala.html)


## ���������� ����� � ��������� ��������� �����

������ ��� ����������� ��������� ����� ������������� ���������, ������ ��� ���������� � ������ ��������������:

@[fill](code/javaguide/forms/JavaForms.java)

> **���������:** ������� `Form` ������������ - ������ ����� ������� ��� `bind()` � `fill()` ������ ����� ������, ����������� ������ �������.

## ��������� �����, ������� �� ������� � �������

�� ������ ������������ `DynamicForm`, ���� ��� ���������� �������� ������ �� html-�����, ������� �� ������� � `Model`:

@[dynamic](code/javaguide/forms/JavaForms.java)

## ��������������� ������ DataBinder

� ������ ���� �� ������ ���������� ����������� �� ������� ������� � ������ ���� ����� � ��������, ��� ����������� ���������������� ����� Formatter ��� ����� �������.
��� �������� ����  JodaTime-���� `LocalTime` ��� ����� ���� ����������� ���:

@[register-formatter](code/javaguide/forms/JavaForms.java)

����� ��������� �������������, ��������� ������ ������ ������, ����� ����������� ������ ����, ������������ � ����� ���������. ���� ������ ������ ����� ���������:

    ["error.invalid.<fieldName>", "error.invalid.<type>", "error.invalid"]

����� ������ ��������� � ������� [Spring DefaultMessageCodesResolver](http://static.springsource.org/spring/docs/3.0.7.RELEASE/javadoc-api/org/springframework/validation/DefaultMessageCodesResolver.html), ��� ������ "typeMismatch" ������� �� "error.invalid".
