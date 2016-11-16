#!/usr/bin/env bash
cf create-service p-mysql 512mb mysql-service
cf create-service p-rabbitmq standard rabbitmq-service

./mvnw package -Dmaven.test.skip
cf push
