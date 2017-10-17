#!/bin/bash

while ! exec 6<>/dev/tcp/${DB_URL}/${DB_PORT}; do
    echo "Trying to connect to MySQL at ${DB_PORT}..."
    sleep 10
done

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar /app.jar