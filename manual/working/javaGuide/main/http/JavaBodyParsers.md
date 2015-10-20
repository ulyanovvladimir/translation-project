<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# Body �������

## ��� ����� body-������?

HTTP ������ - ��� ���������, �� ������� ������� ����.  ��������� ������ ��� - �� ����� ���� ��������� �������������� � ������, � Play ��� ������������� � ������� ������ [`RequestHeader`](api/java/play/mvc/Http.RequestHeader.html). ���� �� ����������� ����� ���� ����� �������, � ������� �� ��������������� � ������, �� � ������� �� ���������, ��� ������������� ��� �����(stream). ������, ����� ��� �������� �� ����� ���� ��������� � ����� ���� ������������� � ������, ������� ��� ���� ����� ���������� ����� � ������, ���������� � ������, Play ������������� ����������[`BodyParser`](api/java/play/mvc/BodyParser.html).

��������� Play - ��� ����������� ���������, ������������ InputStream �� ����� ���� ����������� ��� ������ ���� �������, ��������� �� �����������: ����� �� ��������� ����� `read`, ����, ���������� ��� ������ ����� ���������� ������. ������ �����, Play ���������� ���������� ����������� ����������, ��������� ��� [Akka streams](http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0/java.html).  Akka streams  - ��� ���������� [���������� �������](http://www.reactive-streams.org/), SPI, ������� ��������� ������ API ����������� ���������� �������� ���������, ��� ��� ������������ ����������, ������������ �� `InputStream` �� �������� ��� ������������� � Play.  Akka ������ � ��� ���������� ����������� ��������� ������ ���������� ������� ����������� ��� ���, ��� ����������.

## ������������� ���������� body-��������

����� ������� ���-���������� �� ����� ��������� � ������ body-��������, ��� ����� ������ �������� �� ����������� � Play. � ��� ��������� ������� ��� JSON, XML, ����, ����� ��� � ������� ��������� ���, �������������� ����� ����� � ���� ������ ��� ������������������ ������ � ���� `ByteString`.

### Body-������ �� ���������

Body-������ �� ���������, ������� ������ ��������������, � ������ ���� �� ���� �� �������, ����� ������� �� ��������� ��������� `Content-Type` � ������� ���� ��������������. ���, �������� `Content-Type` ���� `application/json` ����� ��������� ��� `JsonNode`, ����� ��� `Content-Type` ���� `application/x-form-www-urlencoded` ����� ��������� ��� `Map<String, String[]>`.

���� ������� ����� ���� �������� � ������� ������ `body()` ������ [`Request`](api/java/play/mvc/Http.Request.html), � �������� � ������ [`RequestBody`](api/java/play/mvc/Http.RequestBody.html), ������� ������������� ������� ������ ������� � ��������� ����� ���� �������. ��������, ����� �������� ������ � ���� JSON:

@[access-json-body](code/javaguide/http/JavaBodyParsers.java)

���� - ����������� �����, �������������� body-�������� �� ���������:

- **text/plain**: `String`, �������� ����� `asText()`.
- **application/json**: `com.fasterxml.jackson.databind.JsonNode`, �������� ����� `asJson()`.
- **application/xml**, **text/xml** or **application/XXX+xml**: `org.w3c.Document`, �������� ����� `asXml()`.
- **application/form-url-encoded**: `Map<String, String[]>`, �������� ����� `asFormUrlEncoded()`.
- **multipart/form-data**: [`MultipartFormData`](api/java/play/mvc/Http.MultipartFormData.html), �������� ����� `asMultipartFormData()`.
- Any other content type: [`RawBuffer`](api/java/play/mvc/Http.RawBuffer.html), �������� ����� `asRaw()`.

Body-������ �� ���������, � ����� ������������������ �� ����� �������� ������� ���� � ������ , ���� ����� ������� �� ���������� ��������� ����, ��� ���������� � ������������ HTTP. ��� ��������, ��� �� ������ ������ ���� �������� `POST`, `PUT` � `PATCH` , �� �� `GET`, `HEAD` ��� `DELETE`. ���� ��� ����� ���������� ���� �������� � ����� ��������, �� ������ ������������ body-������ `AnyContent`, ��������� [����](#Choosing-an-explicit-body-parser).

### ����� ����� body-�������

���� �� ������ ���� ������� body-������, ��� ����� ���� ������� � ������� ��������� [`@BodyParser.Of`](api/java/play/mvc/BodyParser.Of.html), ��������:

@[particular-body-parser](code/javaguide/http/JavaBodyParsers.java)

Body-�������, ������� ������������� Play �� ������� �������� ����������� �������� ������ [`BodyParser`](api/java/play/mvc/BodyParser.html). ���� ������, �� ��� ���:

- [`Default`](api/java/play/mvc/BodyParser.Default.html): ������ �� ���������.
- [`AnyContent`](api/java/play/mvc/BodyParser.AnyContent.html): ����� ��� ������ �� ���������, �� ����� ������� ���� �������� `GET`, `HEAD` � `DELETE`.
- [`Json`](api/java/play/mvc/BodyParser.Json.html): ������ ���� ��� JSON.
- [`TolerantJson`](api/java/play/mvc/BodyParser.TolerantJson.html): ��� � `Json`, �� �� �������� ���������, ��� ��������� `Content-Type` �������� JSON.
- [`Xml`](api/java/play/mvc/BodyParser.Xml.html): ������ ���� � ������� XML.
- [`TolerantXml`](api/java/play/mvc/BodyParser.TolerantXml.html): ��� � `Xml`, �� �� ���������, ��� ��������� `Content-Type` ������������� XML.
- [`Text`](api/java/play/mvc/BodyParser.Text.html): ������ ���� ��� ������ String.
- [`TolerantText`](api/java/play/mvc/BodyParser.TolerantText.html): ��� � `Text`, �� �� ���������, ��� ��������� `Content-Type` �������� `text/plain`.
- [`Bytes`](api/java/play/mvc/BodyParser.Bytes.html): ������ ���� � `ByteString`.
- [`Raw`](api/java/play/mvc/BodyParser.Raw.html): ������ ���� � `RawBuffer`. ���� ����� ���������� ��������� ���� � ������, ������ �� ������� �����, ������������������ �������� ������� Play, �� � ������ ������� ������� ��� � `File`.
- [`FormUrlEncoded`](api/java/play/mvc/BodyParser.FormUrlEncoded.html): ������ ���� �����.
- [`MultipartFormData`](api/java/play/mvc/BodyParser.MultipartFormData.html): ������ ���� multipart �����, �������� ����� � �����.
- [`Empty`](api/java/play/mvc/BodyParser.Empty.html): �� ������ ����, ������ ���������� ���.

### ����������� ����� ��������

����������� ���������� body-�������� ������������� ���� � ������, � ��������� ������������� ��� �� ����. ���� ������������ ������������, ��� ����� ���� ������������� ����������� ��� ��������������� ��� ���������� ������������� ����������. �� ���� �������, Play ����� ��� ��������������� ������ �������, ���� ��� ����������� � ������, � ������ ��� �������� ������������.

����� ��� ������� � ������ ��������������� � ������� `play.http.parser.maxMemoryBuffer`, � �� ��������� ����� 100KB, ����� ��� �������� ������ ��������������� � ������� `play.http.parser.maxDiskBuffer`, � ��-��������� ����� 10MB.  ��� ��� ����� ���� ���������������� � `application.conf`, ��������, ��� ���������� ������ ������� ������ �� 256KB:

    play.http.parser.maxMemoryBuffer = 256kb
    
�� ����� ������ ���������� ���������� ������, ������������� �� ������ �����������, ������� ����������� body-������, ��. [����](#Writing-a-custom-max-length-body-parser) ��� ��������� ����������.

## ��������� ������������ body-�������

����������� body-������ ����� ���� ������, ���������� ����� [`BodyParser`](api/java/play/mvc/BodyParser.html).  ���� ����� ����� ���� ����������� �����:

@[body-parser-apply](code/javaguide/http/JavaBodyParsers.java)

��������� ����� ������ ����� ���������� ��� ������ ��������� ��������, ������� ������� ����������.

���� ����� ��������� [`RequestHeader`](api/java/play/mvc/Http.RequestHeader.html). �� ����� ���� ����������� ����� ��������� ���������� � ������� - ���� �����, �� ������������ ����� �������� `Content-Type`, ����� ���� ������� ���� ��������� ����������.

����� ���������� ������� ������ �������� [`Accumulator`](api/java/play/libs/streams/Accumulator.html).  ����������� - ��� ������ ���� ������ [Akka streams](http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0/java.html) [`Sink`](http://doc.akka.io/japi/akka-stream-and-http-experimental/1.0/akka/stream/javadsl/Sink.html).  ����������� ���������� ������������ ������ ��������� � ���������, ������� �� ����� ���� ������� ����� �������� � Akka ������ [`Source`](http://doc.akka.io/japi/akka-stream-and-http-experimental/1.0/akka/stream/javadsl/Source.html), ��� ������ `CompletionStage`, ������� ����� ��������, ����� ����������� ����������. ��� � �������� �� �� ����, ��� � `Sink<E, CompletionStage<A>>`, ���������� ��� ������ ����� ��� ������� ������ ����� ����, ������� ������� � ���, ��� `Accumulator` ������������� ������� ������, ����� ��� `map`, `mapFuture`, `recover` � �.�. ��� ������ � �����������, ��� ���� �� �� ��� ���������, ��� `Sink` ������� ����������� ��� ����� �������� � ����� `mapMaterializedValue`.

�����������, �������  ��������� (`apply`) �����, ���������� ���������� �������� ���� [`ByteString`](http://doc.akka.io/japi/akka/2.3.10/akka/util/ByteString.html) - ��� � �������� �������� ��������� ������, �� ���������� �� `byte[]` ���, ��� `ByteString` ������������, � ������ ��������, ����� ��� �������� ��� ����������� ����������� �� ������������� �����.

��� ������������� �������� ��� ������������ - `F.Either<Result, A>`. ��� �������, ��� ���� �������� `Result`, ���� �������� ���� ���� `A`. ��������� A ������ ������������ � ������ ������, ��������, ���� ���� �� ������� ����������, ���� `Content-Type` �� �������� � �����, ������� ��������� body-������, ��� ���� ��� �������� ������ � ������. ����� body-������ ���������� ���������, ��� �������� ���� ��������� ����������� - ��������� body-������� ����� ���������� ��������� � �������� ������� �� ����� �������.

### ��������� ������������� body-�������

� �������� ������� ������� �� ������� ��� �������� ������������ body-������. ������, �� ������ ������� �������� JSON � �����, ������� �� ������� � ������� `Item`.

��� ������ ��������� ����� body-������ , ������� ������� �� JSON body-�������:

@[composing-class](code/javaguide/http/JavaBodyParsers.java)

������, � ����� ���������� ������ `apply`, �� �������� JSON body-������, ������� ������ ��� `Accumulator<ByteString, F.Either<Result, JsonNode>>` ����� ���������� ����. �� ����� ����� ���������� ��� ��� �������� ����� ��������������� ������������ ���� `JsonNode` � ���� `User`. ���� �������������� ����������, �� ������ `Left` ���������� `Result`, ������ � ���, ��� ���� ������:

@[composing-apply](code/javaguide/http/JavaBodyParsers.java)

������������ ���� ����� �������� � `RequestBody`, � ����� ���� �������� � ������� ������ `as`:

@[composing-access](code/javaguide/http/JavaBodyParsers.java)

### ��������� ������� ������ ������������� �������

������ ���� ������������� �������� ����� ���� � ����������� �������, ������� �������������� ������������ ����� ������������. ����� ���������� � Play body-�������� ����������� ������������ ����� ��������� ������������ ����� �������, ��������, ��� ����� ��������� body-������ ��� ������:

@[max-length](code/javaguide/http/JavaBodyParsers.java)

### ��������������� ���� ����-����

����, �� �������� ���������� � ��������� ������������ body-��������. ������ �� �������� �� ����� ���� �� �������� ������� ����, �� ������ ������ ������������� ��� ����-����. ��������, ���� �� ������ ��������� ���� ������� �� ������ ������, �� ����� �� ������� ��� ��������� ����������� body-������:

@[forward-body](code/javaguide/http/JavaBodyParsers.java)

### ����������� ������� ������������  Akka streams

� ������ �������, �������� ����������� �������� ����������� ������, ��������� Akka streams. � ����������� ������� ����� ����������  ������� �������������� ���� �  `ByteString`, ������� `Bytes` ������, ��� ���������� [����](#Composing-an-existing-body-parser), ������ ��� ������������� ����� ������� ������ ��������, �.�. �� ������ ������������ ������������ ������ � ������������ ������ � ����.

������, ����� ��� �� ���������, ��������, ����� ����, ������� ���� ���������� ������� �������, ����� ���������� � �� ����, ����� �������� ��� ����������� �������� ����������� ������.

������ �������� ���� ��� ������������ Akka streams ������� �� ����� ���� ������������� - ��������� ����� ��� ������ - ��������� [������������ �� Akka streams](http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0/java.html).  ������, ����� ������� CSV ������, ������� �������� �� [�������� ����� �� ������ ByteString](http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0/java/stream-cookbook.html#Parsing_lines_from_a_stream_of_ByteStrings) ������������� �� Akka streams ��������:

@[csv](code/javaguide/http/JavaBodyParsers.java)
