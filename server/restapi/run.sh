#!/bin/bash

export JAVA_HOME='/home/tester/.jdks/corretto-11.0.21'
export PATH="$PATH:/home/tester/.jdks/corretto-11.0.21/bin"

./mvnw clean package

export SQL_SERVER_URL="jdbc:postgresql://localhost:5432/improvised_university"
export SQL_SERVER_USERNAME="postgres"
export SQL_SERVER_PASSWORD="mysecretpassword"

java -jar ./target/restapi-1.0-SNAPSHOT-jar-with-dependencies.jar 


