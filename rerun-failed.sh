#!/bin/bash

FAILED_XML="target/surefire-reports/testng-failed.xml"

if [ -f "$FAILED_XML" ]; then
  echo "Rerun failed tests..."
  mvn test -DsuiteXmlFile="$FAILED_XML"
else
  echo "Failed tests not found"
fi
