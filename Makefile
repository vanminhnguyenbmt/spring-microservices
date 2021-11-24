# build common module before deployed
setup:
	@scripts/common-module.sh

build:
	@make setup
	mvn -U clean install -DskipTests=true

# DOCKER
docker/kafka/up:
	@scripts/docker-setup.sh kafka up

docker/kafka/down:
	@scripts/docker-setup.sh kafka down

docker/kafka/stop:
	@scripts/docker-setup.sh kafka stop

docker/zipkin/up:
	@scripts/docker-setup.sh zipkin up

docker/zipkin/down:
	@scripts/docker-setup.sh zipkin down

docker/zipkin/stop:
	@scripts/docker-setup.sh zipkin stop