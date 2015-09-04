# Weather Agregator

<a name="top"/>

## <a name="toc">Содержание</a>
 - [Введение](#introduction)
 - [Требования](#requirements)
 - [Сборка](#package)
 - [Настройка](#setup)
 - [Запуск](#run)
 
 
 ## <a name="introduction">Введение</a> [&#8593;](#top)
 
 Приложение предназначено для отслеживания изменения погоды в нескольких городах с помощью информации из нескольких веб-сервисов
 
 В приложении использованы следующие технологии/фреймворки:
 * Spring Boot - основной веб фреймворк включающий в себя:
    - Spring Data
    - Spring MVC
    - Spring Test
 * h2 - база данных для хранения информации о погоде
 * Apache Tomcat - веб сервер
 * angular - frontent JavaScript Framework
 * bower - DI для JavaScript пакетов
 * grunt - система сборки для JavaScript
 * bootstrap - фреймворк для разработки удобного польязовательского web интерфейса
 
 ## <a href="requirements">Требования</a> [&#8593;](#top)
 
 Минимальные требования:
 - Java 8
 - maven
 - NodeJS 0.12
 - npm
 
 ## <a href="package">Сборка</a> [&#8593;](#top)
 ```bash 
 mvn -P prod package
 ```
 
 ## <a href="setup">Настройка</a> [&#8593;](#top)
 Для настройки необходимо редактировать файл src/main/java/resources/application.yml
 В этом файле есть 3 секции настроек:
 - **weather.period** - периодичность запуска сбора погоды (в милисекундах)
 - **weather.providers** - Провайдеры сбора погоды. Реализовано 2 провайдера:
    - [**OWMap**](http://openweathermap.org)
    - [**Wunderground**](http://wunderground.com)
 - **weather.cities** - список городов по которым необходимо получать погодные данные
 
 Пример:
 ```yaml
 weather:
   #  period: Периодичность запуска сбора погоды (в милисекундах)
   period: 900000 # 15 мин
   # Провайдеры сбора погоды
   providers:
     - OWMap1
     - Wunderground1
   # Города
   cities:
     -
       name: Magnitogorsk
       country: RU
     -
       name: Yekaterinburg
       country: RU
     -
       name: Moscow
       country: RU
     -
       name: Perm
       country: RU
 ```
 
 ## <a href="run">Запуск</a> [&#8593;](#top)
 ```bash 
  java -jar target/weather-cit-0.1.jar
  ```
  