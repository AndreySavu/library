# API для работы с библиотекой книг

## Включает в себя:

1. **RESTful API** для управления библиотекой книг.
2. **Функционал отправки и получения уведомлений** при изменении данных о книгах с использованием RabbitMQ.

## Документация

1. **RESTful API**:
   * Команды:
     1) `GET /api/books` - Получение списка всех книг.
     2) `GET /api/books/{id}` - Получение книги по идентификатору.
     3) `POST /api/books` - Добавление новой книги.
     4) `PUT /api/books/{id}` - Обновление информации о книге.
     5) `DELETE /api/books/{id}` - Удаление книги по идентификатору.
   * Модели:
   - Для добавления новой книги используйте следующий формат JSON в теле запроса при **POST**:
     ```json
     {
       "title": "Название книги",
       "author": "Автор книги",
       "publishedDate": "2024-11-05"
     }
     ```
   - Для обновления информации о книге используйте следующий формат JSON в теле запроса при **PUT**:
     ```json
     {
       "title": "Обновленное название книги",
       "author": "Обновленный автор",
       "publishedDate": "2024-11-06"
     }
     ```
   - Также документация приведена в Swagger'e, доступном по адресу [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/).

2. **RabbitMQ**:

   Сервис RabbitMQ запускается в docker-контейнере с дефолной конфигурацией.
   ```yml
       version: '3'
    services:
      localRabbitMQ:
        image: rabbitmq:3-management-alpine
        environment:
          RABBITMQ_DEFAULT_USER: guest
          RABBITMQ_DEFAULT_PASS: guest
        ports:
          - 5672:5672
          - 15672:15672
   ```


## Инструкция по запуску

Для запуска приложения выполните следующие шаги:

1. Убедитесь, что у вас установлены Java 17, Maven и Docker.
2. Склонируйте репозиторий на свою локальную машину:
   ```.bash
   git clone https://github.com/AndreySavu/library.git
   cd library
   ```
3. Запустите docker-compose.yml
   ```.bash
    docker-compose up
   ```
4. Создайте экземпляр базы данных PostgreSQL 'library' на порту 5432, логин и пароль: library_user.
   Или настройте подключения к базе данных в файле `application.properties`, используя следующие параметры:
   
 ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
 ```
   
5. Запустите приложение с помощью Maven:
   
```.bash
   mvn spring-boot:run
```


