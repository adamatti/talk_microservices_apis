.DEFAULT_GOAL := help

stop-all: ## stop all containers
	@docker-compose stop -t 0
	@docker-compose -f CountryGroovy/docker-compose.yml stop -t 0
	@docker-compose -f FullNode/docker-compose.yml stop -t 0

start-restql:
	@docker-compose up -d restql-manager

start-country:
	@docker-compose -f CountryGroovy/docker-compose.yml up -d

start-node:
	@docker-compose -f FullNode/docker-compose.yml up -d

start-all: start-country start-restql start-node ## start all containers

.PHONY: help
help: ## show this help
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
