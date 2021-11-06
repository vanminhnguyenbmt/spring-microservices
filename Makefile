# build common module before deployed
setup:
	@scripts/common-module.sh

build:
	@make setup
	mvn -U clean install -DskipTests=true

# DOCKER
docker/kafka/up:
	docker-compose -f docker/kafka/docker-compose.yml up -d

docker/kafka/down:
	docker-compose -f docker/kafka/docker-compose.yml down

docker/kafka/stop:
	docker-compose -f docker/kafka/docker-compose.yml stop
