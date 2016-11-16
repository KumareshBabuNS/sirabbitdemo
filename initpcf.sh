#!/usr/bin/env bash
cf create-service p-mysql 512mb mysql-service
cf create-service p-rabbitmq standard rabbitmq-service

cf create-service-key mysql-service mysql-key
cf create-service-key rabbitmq-service rabbitmq-key

cf service-key mysql-service mysql-key
cf service-key rabbitmq-service rabbitmq-key
