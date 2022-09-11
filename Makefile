# Setup maven and build jar
setup/all:
	@make setup/common
	@scripts/maven-setup.sh .

setup/common:
	@scripts/common-module.sh

setup/config_server:
	@scripts/maven-setup.sh config-server

setup/eureka_server:
	@scripts/maven-setup.sh eureka-server

setup/auth_service:
	@scripts/maven-setup.sh auth-service

setup/gateway_zuul:
	@scripts/maven-setup.sh gateway-zuul

setup/order_service:
	@scripts/maven-setup.sh order-service

setup/stock_service:
	@scripts/maven-setup.sh stock-service

setup/turbine_stream:
	@scripts/maven-setup.sh turbine-stream

setup/hystrix_dashboard:
	@scripts/maven-setup.sh hystrix-dashboard

# DOCKER
docker/kafka/up:
	@scripts/docker-setup.sh docker/kafka up

docker/kafka/down:
	@scripts/docker-setup.sh docker/kafka down

docker/kafka/stop:
	@scripts/docker-setup.sh docker/kafka stop

docker/zipkin/up:
	@scripts/docker-setup.sh docker/zipkin up

docker/zipkin/down:
	@scripts/docker-setup.sh docker/zipkin down

docker/zipkin/stop:
	@scripts/docker-setup.sh docker/zipkin stop

docker/infrastructure:
	@make docker/kafka/up
	@sleep 25
	@scripts/create-topic.sh
	@make docker/zipkin/up

docker/all/up:
	@make docker/infrastructure
	@scripts/docker-setup.sh docker up

docker/all/down:
	@scripts/docker-setup.sh docker down
	@make docker/zipkin/down
	@make docker/kafka/down

docker/all/stop:
	@scripts/docker-setup.sh docker stop
	@make docker/zipkin/stop
	@make docker/kafka/stop

docker/all/reset:
	@make docker/infrastructure
	@scripts/docker-setup.sh docker reset