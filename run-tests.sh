#!/bin/bash

JAR_NAME="selenium-server-4.34.0.jar"
PORT=4444

echo "Check if Selenium server launched..."

if lsof -i :$PORT | grep -q LISTEN; then
    echo "Selenium server already launched"
else
    echo "Run Selenium Server..."
    nohup java -jar "$JAR_NAME" standalone &
    sleep 5
fi

echo "Run tests..."
mvn clean test