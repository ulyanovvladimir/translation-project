<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# ������������� �������� ��� �������� ����

Play ������������� ��������� ������� ����� ������ ��� ����������� ���� ����� � HTML ��������.

## �������� ���� `<form>` 

������ ������ ������� ��� `<form>`. ��� ���������� ������� ������, ������� ������������� ������������� ��������� `action` � `method` ���� `<form>` � ������������ � ��������� ������������� ��� ������� ����������:       

@[form](code/javaguide/forms/helpers.scala.html)

�� ����� ������ �������� �������������� ����� ����������, ������� ����� �������� � ���������������� HTML:

@[form-with-id](code/javaguide/forms/helpers.scala.html)

## ��������� �������� `<input>`

���� ��������� ������� ��� input-�� � ������ `views.html.helper`. �� ������������ �� ���� ����� � ��� ���������� ��������������� ���������� ����� HTML � ������������� ���������, ������������� � ��������:   

@[full-form](code/javaguide/forms/fullform.scala.html)

��� �������� ������� `form`, �� ������ ���������� �������������� ����� ����������, ������� ����� �������� � ���������������� HTML:

@[extra-params](code/javaguide/forms/helpers.scala.html)

> **���������:** ��� �������������� ��������� ����� ��������� � ��������������� HTML HTML, �� ����������� ���, ��� ����� ���������� � ������� `_`. ���������, ������������ � ������������� �������� ������������������� ��� ��������� ������������ ���� (������� �� ������ �����).

## ���������� ��������� HTML input ��������� ��������������

���� ��� ����� ����� ������ `input`, ������� �������� ��� ������������ �������� HTML ���������:

@[generic-input](code/javaguide/forms/helpers.scala.html)

## ������������ ����

������������� ���� �� ������ �������� ��� `<input>`, �� ����� ����� ��������� � `<label>` � � ����� ������ �����, ������������ ����� CSS ����������� ��� ������������� ����.

��� ������� ����� ����� ��������� ������� `FieldConstructor`, ������� ��������� ���� ������. ����������� ��-��������� (������������, ���� �� ������ ������� ������������ ����� ��� � ������� ���������), ���������� HTML ���������� ����: 
     
```
<dl class="error" id="email_field">
    <dt><label for="email">Email:</label></dt>
    <dd><input type="text" name="email" id="email" value=""></dd>
    <dd class="error">������������ ����!</dd>
    <dd class="error">������ ������</dd>
    <dd class="info">Required</dd>
    <dd class="info">������ �����������</dd>
</dl>
```

���� ����������� ���� ������������ �������������� �����, ������� �� ������ �������� � ���������� ������� input:

```
'_label -> "������� ��� ����"
'_id -> "idForTheTopDlElement"
'_help -> "��������� ��� ����"
'_showConstraints -> false
'_error -> "����������� ������"
'_showErrors -> false
```

### ��������� ������������ ������������ ����

����� ������, ��� ��� ����� �������� ����������� ����������� ����. ������� � ��������� ��������� �������: 

@[template](code/javaguide/forms/myFieldConstructorTemplate.scala.html)

��������� ��� �� `views/` � ����� ��� `myFieldConstructorTemplate.scala.html`

> **���������:** ��� ����� ���� ������. �� ������ ��������� ��� ���������, ��������� ��� ���������. �� ����� ������ ������ � ������������� ���� ����� `@elements.field`.

������ �������� `FieldConstructor` ���-��, ���������:

@[field](code/javaguide/forms/withFieldConstructor.scala.html)

## ��������� ������������� ��������

��������� ������ ��������� ��������� ��������� ����� ����� ��� ������������� ���������. �����������, � ��� ��������� ��� �����:

@[code](code/javaguide/forms/html/UserForm.java)

� �� ������ ������������� ��� ����� input-�� ��� ���� `emails`, ������� �������� �����. ��� ����� ����������� ������ `repeat`:   

@[repeat](code/javaguide/forms/helpers.scala.html)

����������� �������� `min` ��� ����������� ������������ ����� �����, ���� ���� ��������������� ������ ����� �����.