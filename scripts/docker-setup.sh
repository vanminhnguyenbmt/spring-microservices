#!/usr/bin/env bash

command=$2
if [ $2 == up ]
then
  command="$command -d"
fi

if [ $2 == reset ]
then
  command="up --build --remove-orphans --force-recreate"
fi

docker-compose -f $1/docker-compose.yml $command