<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# �������� ������

## �������� ������ ����� ����� `multipart/form-data`

����������� ������ �������� ������ � ���-���������� - ��� ������������ ����� �� ����������� ���������� `multipart/form-data`, ������� ��������� ��������� ����������� ������ ����� � ���������� ������. ����������, �������� ��������, ��� HTTTP ����� ��� ����� ������ ���� POST (�� GET). 

������ � ��������� HTML �����

```
@form(action = routes.Application.upload, 'enctype -> "multipart/form-data") {

    <input type="file" name="picture">

    <p>
        <input type="submit">
    </p>

}
```

������ ������� ��������� ���������� `upload`:

@[syncUpload](code/JavaFileUpload.java)

## ������ �������� ������

������ ������ ��������� ����� �� ������ - ������������ Ajax ����� ��������� ����� �� ����� ����������. � ���� ������, ���� ������� �� ����� ������������ ��� `multipart/form-data`, �� ����� ���� �������� �� ����������� �����.

@[asyncUpload](code/JavaFileUpload.java)
