#!/bin/bash

# ==================================================
echo "Start create Services"

cd ..

echo "* Creating microservices network..."
docker network create \
    --driver=bridge \
    --subnet=172.30.121.0/24 \
    --ip-range=172.30.121.0/25 \
    --opt "com.docker.network.bridge.name"="network" \
    --opt "com.docker.network.bridge.enable_icc"="true" \
    --opt "com.docker.network.bridge.enable_ip_masquerade"="true" \
    --opt "com.docker.network.bridge.host_binding_ipv4"="0.0.0.0" \
    --opt "com.docker.network.driver.mtu"="1500" \
    network

echo "* Creating PostgreSQL..."
docker run \
    --name postgres \
    --network=network \
    -e POSTGRES_PASSWORD=postgres \
    -p 5432:5432 \
    -v :/var/lib/postgresql/data \
    -d postgres

echo "Clean"
mvn clean

echo "Start Packege Services"
mvn package -DskipTests

echo "* Creating database for POSTGRESQL..."
docker exec -it postgres psql -U postgres -c "CREATE DATABASE POSTGRESQL;"

echo "Build Docker"
docker build --tag=customer-microservice --force-rm=true .

echo "* Creating database for POSTGRESQL..."
docker run --name customer-microservice \
            -d customer-microservice \
            --network=network \
            -p 8080:8080 \
            --restart=always
