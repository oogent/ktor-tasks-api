# Tasks API

REST-сервис для работы со списком задач на Kotlin и Ktor.

## Запуск

Требуется JDK 21.

### IntelliJ IDEA

1. Открыть папку проекта через `File → Open`.
2. Дождаться загрузки Gradle.
3. Открыть `src/main/kotlin/com/example/tasks/Application.kt`.
4. Нажать зелёную стрелку у функции `main` и выбрать `Run`.

### Командная строка

```bash
gradlew.bat run
```

Сервер будет доступен по адресу `http://localhost:8080`.

## Документация API

- Swagger UI: `http://localhost:8080/swagger`
- OpenAPI: `http://localhost:8080/openapi`
- Спецификация: `src/main/resources/openapi/documentation.yaml`

Все маршруты описаны в OpenAPI-спецификации: параметры, тела запросов, ответы и коды ошибок.

## Маршруты

| Метод | Путь | Описание |
|---|---|---|
| GET | `/tasks` | Список задач |
| GET | `/tasks/{id}` | Задача по id |
| POST | `/tasks` | Создание задачи |
| DELETE | `/tasks/{id}` | Удаление задачи |

`GET /tasks` поддерживает query-параметры `status` (`todo`, `in_progress`, `done`) и `limit`.

Ошибки обрабатываются через `StatusPages` и возвращаются в JSON. Входящие запросы логируются через `CallLogging`.

## Примеры

Создание задачи:

```bash
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Сделать домашнее задание\",\"description\":\"Ktor\",\"status\":\"todo\"}"
```

Получение списка:

```bash
curl "http://localhost:8080/tasks?status=todo&limit=10"
```

Удаление:

```bash
curl -X DELETE http://localhost:8080/tasks/1
```

## Тестирование

### Автотесты

```bash
gradlew.bat test
```

В проекте 10 тестов. Они проверяют:

- пустой список задач;
- создание задачи и заголовок `Location`;
- получение задачи по id;
- удаление задачи и повторный запрос после удаления;
- ответы `404` и `400`;
- пустой `title`;
- неверный `Content-Type` (`415`);
- некорректный JSON;
- доступность Swagger UI и OpenAPI.

Ожидаемый результат — `BUILD SUCCESSFUL`.

### Postman

В папке `postman` находятся коллекция и окружение. Импортируйте оба файла в Postman, выберите окружение `Tasks API` и запустите коллекцию. Все 7 запросов должны пройти без ошибок.

### Swagger UI

Запустите сервер и откройте `http://localhost:8080/swagger`. Маршруты можно раскрыть и выполнить через кнопку `Try it out`.

## Проверка

После запуска откройте в браузере `http://localhost:8080/tasks`. Сервер вернёт пустой список:

```json
[]
```

## Структура

```
src/main/kotlin/com/example/tasks/
├── Application.kt
├── Models.kt
└── TaskRepository.kt
src/main/resources/openapi/documentation.yaml
src/test/kotlin/com/example/tasks/ApplicationTest.kt
postman/
├── Tasks API.postman_collection.json
└── Tasks API.postman_environment.json
```
