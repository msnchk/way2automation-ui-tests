FROM maven:3.9.9-amazoncorretto-17

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

CMD mvn test -DsuiteXmlFile=src/test/resources/suites/ui_test_selenoid_docker.xml && \
    mvn allure:report -Dallure.report.directory=/app/allure-report

