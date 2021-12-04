## Реализованная функциональность

- полнотекстовый поиск
- отображение списка и детальной информации по имеющимся решениям
- отображение списка и детальной информации по имеющимся задачам
- выделение ключевых меток в описании стартапов в виде тегов
- ролевая модель соответствующая бизнес-требованиям кейса

## Особенность проекта заключается в следующем:

- использование Identity Provider для OpenID (Keycloak) 
- гибкая, масштабируемая, мобильная инфраструктура приближенная к продакшену (Контейнеризация)
- Стандартизованный формат взаимодействия с Gateway API через OpenAPI 3
- полнотекстовый поиск с использованием интсрумента Elasticsearch
- использование алгоритмов машинного обучения для облегчения поиска

## Основной стек технологий

- HTML5, CSS3, JS
- SASS, PostCSS
- Vue3
- npm, Webpack, Babel
- Axios, Keycloak-js
- Java 11 (Spring Boot), OpenAPI 3 (Swagger)
- Keycloak
- Elasticsearch
- Postgres
- Python 3.8 (numpy, pandas, scikit-learn, scipy)
- docker-compose

# СРЕДА ЗАПУСКА

1. Развертывание сервиса производится на debian-like linux (debian 9+);
2. Требуется установленный docker (версии 20.10.11+)
3. Требуется установленный git (версии 2.25.1+)

# УСТАНОВКА

1. Необходимо склонировать репозиторий
```
git clone https://github.com/aleshinanton/inno-team.git
```
2. Перейти в папку с проектом
```
cd inno-team
```
3. Выполнить команды
```
docker-compose build python
docker-compose build inno-team-back
docker-compose build inno-team-front
docker-compose up
```

После выполнения команды начнет настраиваться и устанавливаться необходимая инфраструктура

РАЗРАБОТЧИКИ

Алешин Антон fullstack https://t.me/antonaleshin
Фуфаев Михаил fullstack https://t.me/pulldakar
Прудников Юрий data-scientist https://t.me/killtheciv