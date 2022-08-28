#!/usr/bin/env bash

awk -F':' '{ system("docker exec -t zookeeper /bin/kafka-topics --bootstrap-server kafka-1:9092,kafka-2:9092,kafka-3:9092 --topic=" $1 " --create --partitions=" $2 " --replication-factor=" $3) }' ./scripts/kafka-topics.txt