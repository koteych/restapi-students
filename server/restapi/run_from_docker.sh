#!/bin/bash

mvn clean package
java -jar ./target/restapi-1.0-SNAPSHOT-jar-with-dependencies.jar
