<!--- Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com> -->
# Body парсеры

## Что такое body-парсер?

HTTP запрос - это заголовок, за которым следует тело.  Заголовок обычно мал - он может быть безопасно буфферизирован в памяти, в Play это смоделировано с помощью класса [`RequestHeader`](api/java/play/mvc/Http.RequestHeader.html). Тело же потенциальо может быть очень длинным, и поэтому не буфферизируется в памяти, но в отличие от заголовка, оно смоделировано как поток(stream). Однако, много тел запросов на самом деле небольшие и могут быть смоделированы в памяти, поэтому для того чтобы отобразить поток в объект, хранящийся в памяти, Play предоставляет абстракцию[`BodyParser`](api/java/play/mvc/BodyParser.html).

Поскольку Play - это асинхронный фреймворк, традиционный InputStream не может быть использован для чтения тела запроса, поскольку он блокирующий: когда вы вызываете метод `read`, тред, вызывающий его должен ждать полученных данных. Вместо этого, Play использует библиотеку асинхронной поточности, известную как [Akka streams](http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0/java.html).  Akka streams  - это реализация [Реактивных потоков](http://www.reactive-streams.org/), SPI, которое позволяет многим API асинхронной поточности работать совместно, так что традиционные технологии, базирующиеся на `InputStream` не подходят для использования в Play.  Akka потоки и вся экосистема асинхронных библиотек вокруг Реактивных Потоков предоставит вам все, что необходимо.

## Использование встроенных body-парсеров

Самые типовые веб-приложения не будут нуждаться в особых body-парсерах, они могут просто работать со встроенными в Play. К ним относятся парсеры для JSON, XML, форм, также как и парсеры обработки тел, представляющих собой текст в виде строку или последовательности байтов в виде `ByteString`.

### Body-парсер по умолчанию

Body-парсер по умолчанию, который должен использоваться, в случае если вы явно не указали, будет браться из входящего заголовка `Content-Type` и парсить тело соответственно. Так, например `Content-Type` типа `application/json` будет парситься как `JsonNode`, тогда как `Content-Type` вида `application/x-form-www-urlencoded` будет парситься как `Map<String, String[]>`.

Тело запроса может быть доступно с помощью метода `body()` класса [`Request`](api/java/play/mvc/Http.Request.html), и обернуто в объект [`RequestBody`](api/java/play/mvc/Http.RequestBody.html), который предоставляет удобные методы доступа к различным типам тела запроса. Например, чтобы получить доступ к телу JSON:

@[access-json-body](code/javaguide/http/JavaBodyParsers.java)

Ниже - отображение типов, поддерживаемых body-парсером по умолчанию:

- **text/plain**: `String`, доступно через `asText()`.
- **application/json**: `com.fasterxml.jackson.databind.JsonNode`, доступно через `asJson()`.
- **application/xml**, **text/xml** or **application/XXX+xml**: `org.w3c.Document`, доступно через `asXml()`.
- **application/form-url-encoded**: `Map<String, String[]>`, доступно через `asFormUrlEncoded()`.
- **multipart/form-data**: [`MultipartFormData`](api/java/play/mvc/Http.MultipartFormData.html), доступно через `asMultipartFormData()`.
- Any other content type: [`RawBuffer`](api/java/play/mvc/Http.RawBuffer.html), доступно через `asRaw()`.

Body-парсер по умолчанию, в целях производительности не будет пытаться парсить тело в случае , если метод запроса не определяет значимого тела, как определено в спецификации HTTP. Это означает, что он парсит только тела запросов `POST`, `PUT` и `PATCH` , но не `GET`, `HEAD` или `DELETE`. Если вам нужно распарсить тела запросов с этими методами, вы можете использовать body-парсер `AnyContent`, описанный [ниже](#Choosing-an-explicit-body-parser).

### Явный выбор body-парсера

Если вы хотите явно выбрать body-парсер, это может быть сделано с помощью аннотации [`@BodyParser.Of`](api/java/play/mvc/BodyParser.Of.html), например:

@[particular-body-parser](code/javaguide/http/JavaBodyParsers.java)

Body-парсеры, которые предоставляет Play из коробки являются внутренними классами класса [`BodyParser`](api/java/play/mvc/BodyParser.html). Если кратко, то вот они:

- [`Default`](api/java/play/mvc/BodyParser.Default.html): Парсер по умолчанию.
- [`AnyContent`](api/java/play/mvc/BodyParser.AnyContent.html): Также как парсер по умолчанию, но будет парсить тела запросов `GET`, `HEAD` и `DELETE`.
- [`Json`](api/java/play/mvc/BodyParser.Json.html): Парсит тело как JSON.
- [`TolerantJson`](api/java/play/mvc/BodyParser.TolerantJson.html): Как и `Json`, но не проводит валидацию, что заголовок `Content-Type` является JSON.
- [`Xml`](api/java/play/mvc/BodyParser.Xml.html): Парсит тело в формате XML.
- [`TolerantXml`](api/java/play/mvc/BodyParser.TolerantXml.html): Как и `Xml`, но не проверяет, что заголовок `Content-Type` соответствует XML.
- [`Text`](api/java/play/mvc/BodyParser.Text.html): Парсит тело как строку String.
- [`TolerantText`](api/java/play/mvc/BodyParser.TolerantText.html): Как и `Text`, но не проверяет, что заголовок `Content-Type` является `text/plain`.
- [`Bytes`](api/java/play/mvc/BodyParser.Bytes.html): Парсит тело в `ByteString`.
- [`Raw`](api/java/play/mvc/BodyParser.Raw.html): Парсит тело в `RawBuffer`. Этот класс попытается сохранить тело в памяти, вплоть до размера паяти, сконфигурированого размером буффера Play, но в случае неудачи запишет его в `File`.
- [`FormUrlEncoded`](api/java/play/mvc/BodyParser.FormUrlEncoded.html): Парсит тело формы.
- [`MultipartFormData`](api/java/play/mvc/BodyParser.MultipartFormData.html): Парсит тело multipart формы, сохраняя части в файлы.
- [`Empty`](api/java/play/mvc/BodyParser.Empty.html): Не парсит тело, просто игнорирует его.

### Ограничения длины Контента

Большинство встроенных body-парсеров буфферизирует тело в памяти, и некоторые буфферизируют его на диск. Если буфферизация неограничена, это может быть потенциальной уязвимостью для подозрительного или беспечного использования приложения. По этой причине, Play имеет два конфигурируемых лимита буффера, один для буфферизаии в памяти, и второя для дисковой буфферизации.

Лимит для буффера в памяти конфигурируется с помощью `play.http.parser.maxMemoryBuffer`, и по умолчанию равен 100KB, тогда как дисковый буффер конфигурируется с помощью `play.http.parser.maxDiskBuffer`, и по-умолчанию равен 10MB.  Оба они могут быть сконфигурированы в `application.conf`, например, для увеличения лимита буффера памяти до 256KB:

    play.http.parser.maxMemoryBuffer = 256kb
    
Вы также можете ограничить количество памяти, исспользуемой на уровне контроллера, написав собственный body-парсер, см. [ниже](#Writing-a-custom-max-length-body-parser) для подробной информации.

## Написание собственного body-парсера

Собственный body-парсер может быть создан, реализовав класс [`BodyParser`](api/java/play/mvc/BodyParser.html).  Этот класс имеет один абстрактный метод:

@[body-parser-apply](code/javaguide/http/JavaBodyParsers.java)

Сигнатура этого метода может показаться для начала несколько пугающей, поэтому давайте разберемся.

Этот метод принимает [`RequestHeader`](api/java/play/mvc/Http.RequestHeader.html). Он может быть использован чтобы проверить информацию о запросе - чаще всего, он используется чтобы получить `Content-Type`, чтобы тело запроса было корректно распарсено.

Типом результата данного метода является [`Accumulator`](api/java/play/libs/streams/Accumulator.html).  Аккумулятор - это тонкий слой поверх [Akka streams](http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0/java.html) [`Sink`](http://doc.akka.io/japi/akka-stream-and-http-experimental/1.0/akka/stream/javadsl/Sink.html).  Аккумулятор асинхронно аккумулирует потоки элементов в результат, Поэтому он может быть запущен через передачу в Akka потоки [`Source`](http://doc.akka.io/japi/akka-stream-and-http-experimental/1.0/akka/stream/javadsl/Source.html), это вернет `CompletionStage`, который будет искуплен, когда аккумулятор завершится. Это в точности та же вещь, как и `Sink<E, CompletionStage<A>>`, фактически это ничего более чем обертка вокруг этого типа, большая разница в том, что `Accumulator` предоставляет удобные методы, такие как `map`, `mapFuture`, `recover` и т.д. для работы с результатом, как если бы он был обещанием, где `Sink` требует оборачивать все такие операции в вызов `mapMaterializedValue`.

Аккумулятор, который  применяет (`apply`) метод, возвращает потребляет элементы типа [`ByteString`](http://doc.akka.io/japi/akka/2.3.10/akka/util/ByteString.html) - они в сущности являются массивами байтов, но отличаются от `byte[]` тем, что `ByteString` неизменяемый, и многие операции, такие как слайсинг или дописывание выполняются за фиксированное время.

Тип возвращаемого значения для аккумулятора - `F.Either<Result, A>`. Это говорит, что либо вернется `Result`, либо вернется тело типа `A`. Результат A обычно возвращается в случае ошибки, например, если тело не удалось пропарсить, если `Content-Type` не совпадал с типом, который принимает body-парсер, или если был исчерпан буффер в памяти. Когда body-парсер возвращает результат, это сократит круг обработки контроллера - результат body-парсера будет немедленно возвращен и действие никогда не будет вызвано.

### Написание существующего body-парсера

В качестве первого примера мы покажем как написать существующий body-парсер. Скажем, вы хотите парсить входящий JSON в класс, который вы описали и назвали `Item`.

Для начала определим новый body-парсер , который зависит от JSON body-парсера:

@[composing-class](code/javaguide/http/JavaBodyParsers.java)

Теперь, в нашей реализации метода `apply`, мы запустим JSON body-парсер, который выдаст нам `Accumulator<ByteString, F.Either<Result, JsonNode>>` чтобы употребить тело. Мы затем можем отобразить его как обещание чтобы сконвертировать распарсенное тело `JsonNode` в тело `User`. Если преобразование провалится, мы вернем `Left` результата `Result`, говоря о том, что была ошибка:

@[composing-apply](code/javaguide/http/JavaBodyParsers.java)

Возвращаемое тело будет обернуто в `RequestBody`, и может быть доступно с помощью метода `as`:

@[composing-access](code/javaguide/http/JavaBodyParsers.java)

### Написание парсера особой максимального размера

Другой кейс использования парсеров может быть в определении парсера, который переопределяет максимальную длину буфферизации. Много встроенных в Play body-парсеров рарзаботаны расширяемыми чтобы позволить перекрывание длины буффера, например, как можно расширить body-парсер для текста:

@[max-length](code/javaguide/http/JavaBodyParsers.java)

### Перенаправление тела куда-либо

Итак, мы показали расширение и написание существующих body-парсеров. Иногда вы возможно на самом деле не захотите парсить тело, вы просто хотите перенаправить его куда-либо. Например, если вы хотите загрузить тело запроса ну другой сервис, вы могли бы сделать это определив собственный body-парсер:

@[forward-body](code/javaguide/http/JavaBodyParsers.java)

### Собственный парсинг использующий  Akka streams

В редких случаях, возможно понадобится написать собственный парсер, используя Akka streams. В большинстве случаев будет достаточно  сначала буферизировать тело в  `ByteString`, написав `Bytes` парсер, как рассказано [выше](#Composing-an-existing-body-parser), Обычно это предоставляет более простой способ парсинга, т.к. вы можете использовать императивные методы и произвольный доступ к телу.

Однако, когда это не достижимо, например, когда тело, которое надо распарсить слишком длинное, чтобы уместиться в па мяти, тогда возможно вам потребуется написать собственный парсер.

Полное описание того как использовать Akka streams выходит за рамки этой докумментации - наилучшее место для начала - прочитать [Документацию по Akka streams](http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0/java.html).  Однако, далее показан CSV парсер, который строится на [Парсинге строк из потока ByteString](http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0/java/stream-cookbook.html#Parsing_lines_from_a_stream_of_ByteStrings) докумментации из Akka streams рецептов:

@[csv](code/javaguide/http/JavaBodyParsers.java)
