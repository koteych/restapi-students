#!/bin/bash

echo -e "Get all students";
curl -H "Accept: application/json" http://localhost:8000/api/v2/students

echo -e "\n\nAdding one more";
curl -X POST -d '{"firstName": "John", "lastName": "Doe", "middleName": "Smith", "id": "12345", "group": "A", "birthdate": "2000-01-01"}' -H "Content-Type: application/json" http://localhost:8000/api/v2/students

echo -e "\n\nLet's see how it worked";
curl -H "Accept: application/json" http://localhost:8000/api/v2/students

echo -e "\n\nNow let's delete the first one"
curl -X DELETE http://localhost:8000/api/v2/students/1

echo -e "\n\nHe or she should disappear";
curl -H "Accept: application/json" http://localhost:8000/api/v2/students

