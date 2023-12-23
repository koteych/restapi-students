#!/bin/bash

docker build -t init-postgres-image .
docker run --name init-postgres-container --rm -e POSTGRES_PASSWORD=mysecretpassword --network restapi-sql -p 5432:5432 -d init-postgres-image
