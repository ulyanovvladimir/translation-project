<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# ��������� � ������ JSON

� Java, Play ���������� [Jackson](http://jackson.codehaus.org/) ���������� ��� ����������� �������� � JSON � �������. Play-������ action-� �������� � ����� `JsonNode` � ��������� ������������� ������ ��� ����������� � ������ `play.libs.Json` API.

## ����������� Java �������� � JSON

Jackson ��������� ����� �������������� Java ������� � JSON � ������� ��������� ���� �����, get-����� � set-�����. � �������� ������� �� ����� ������������ �������� ������� Java ������:

@[person-class](code/javaguide/json/JavaJsonActions.java)

�� ����� ���������� JSON ������������� ������� � ������� ����� `Person`:

@[to-json](code/javaguide/json/JavaJsonActions.java)

������� ������� �� ����� ������������� ������ `Person` � `JsonNode`:

@[from-json](code/javaguide/json/JavaJsonActions.java)

## ��������� JSON �������

JSON ������ - ��� HTTP ������, ������������ ��������� JSON � �������� ���� �������. ��� ���������`Content-Type` ������ ���������� MIME ��� `text/json` ��� `application/json`.

��-��������� action ���������� ������ ���� ������� ��� **������ ��������**, ������� �� ������ ������������ ��� ��������� ���� � �������� JSON (���� ����������, �� `JsonNode` ���������� Jackson ):

@[json-request-as-anycontent](code/javaguide/json/JavaJsonActions.java)

�������, ������� ����� (��� �����) ���������� ���� ����������� `BodyParser` ����� ��������� Play ���������� ���� ������� �������� � JSON:

@[json-request-as-json](code/javaguide/json/JavaJsonActions.java)

> **�������� ��������:** � ������ ������ ������ ������������� ������  HTTP ����� � ����� 400 ��� �� JSON-������ ��������, ��� Content-type ���������� ��� application/json.

�� ������ �������������� ��� ���������������� � �������� **cURL** �� ��������� ������:

```bash
curl
  --header "Content-type: application/json"
  --request POST
  --data '{"name": "Guillaume"}'
  http://localhost:9000/sayHello
```

������ ������ �����:

```http
HTTP/1.1 200 OK
Content-Type: text/plain; charset=utf-8
Content-Length: 15

Hello Guillaume
```

## ������ JSON ������

� ����� ���������� ������� �� ���������� JSON ������, �� ������� `text/plain` � �������� ������. ������� ������� ��� ���, ����� ������� ���������� JSON HTTP �����:

@[json-response](code/javaguide/json/JavaJsonActions.java)

������ �� ������ ������ �����:

```http
HTTP/1.1 200 OK
Content-Type: application/json; charset=utf-8

{"exampleField1":"foobar","exampleField2":"Hello world!"}
```

�� ����� ������ ������� Java ������ � ������������� ������������� ��� � JSON � ������� ���������� Jackson:

@[json-response-dao](code/javaguide/json/JavaJsonActions.java)

## ����������� �������������

��������� Play ���������� Jackson, �� ������ ������������ ��� ����������� `ObjectMapper` ����� ������� ���� `JsonNode`. [������������ ��  jackson-databind](https://github.com/FasterXML/jackson-databind/blob/master/README.md) ��������� ��� �� ������ ����� ��������� ������� ������������ JSON.

���� �� �������� ������������ Play'������ `Json` API (`toJson`/`fromJson`) � ������� ������������ `ObjectMapper`, �� ������ �������� ���-�� ����� ����� � ���  `GlobalSettings#onStart`:

@[custom-object-mapper](code/javaguide/json/JavaJsonActions.java)
