#!/bin/bash

export JAVA_HOME='/home/tester/.jdks/corretto-11.0.21'
export PATH="$PATH:/home/tester/.jdks/corretto-11.0.21/bin"

./mvnw clean package
#java -cp "./target/*" org.restapi.App;
java -jar ./target/restapi-1.0-SNAPSHOT-jar-with-dependencies.jar 


