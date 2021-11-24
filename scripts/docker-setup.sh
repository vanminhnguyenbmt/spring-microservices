#!/usr/bin/env bash

command=$2
if [ $2 == up ]
then
  command="$command -d"
fi

docker-compose -f docker/$1/docker-compose.yml $command