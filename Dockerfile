# Этап 1: Сборка проекта
FROM maven:3.8.5-openjdk-17 AS build

# Рабочая директория
WORKDIR /app

# Копируем только файл pom.xml для загрузки зависимостей
COPY pom.xml .

# Загружаем зависимости
RUN mvn dependency:go-offline

# Копируем исходный код
COPY src/ /app/src/

# Выполняем сборку проекта
RUN mvn clean package -DskipTests

# Этап 2: Создание финального образа
FROM openjdk:17-slim

WORKDIR /app

COPY ./data ./data

RUN apt-get update && \
    apt-get install -y wget && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY ./script/ftp_download.sh ./ftp_download.sh

RUN chmod +x ./ftp_download.sh

RUN ./ftp_download.sh

# Копируем JAR-файл из этапа сборки
COPY --from=build /app/target/ktor-sample-0.0.1-jar-with-dependencies.jar /app/ktor-app.jar

# Установка переменных окружения
ENV GENETIC_DATA_FILE_PATH=./data/clinvar.vcf.gz

# Определяем порт, который будет использовать приложение
EXPOSE 8080

# Запускаем приложение
CMD ["java", "-jar", "/app/ktor-app.jar"]
