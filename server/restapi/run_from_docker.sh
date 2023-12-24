#!/bin/bash

mvn clean package

export SQL_SERVER_URL="jdbc:postgresql://sql_server:5432/improvised_university"
export SQL_SERVER_USERNAME="postgres"
export SQL_SERVER_PASSWORD="mysecretpassword"

java -jar ./target/restapi-1.0-SNAPSHOT-jar-with-dependencies.jar
