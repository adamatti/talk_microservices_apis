.DEFAULT_GOAL := help

stop-all: ## stop all containers
	@docker-compose stop -t 0
	@docker-compose -f CountryGroovy/docker-compose.yml stop -t 0
	@docker-compose -f FullNode/docker-compose.yml stop -t 0
	@docker-compose -f graphql-sample/docker-compose.yml stop -t 0

start-mongo: ## start mongodb only
	@docker-compose up -d mongo

start-restql: ## start restql
	@docker-compose up -d restql-manager

start-country: ## start country only
	@docker-compose -f CountryGroovy/docker-compose.yml up -d

start-node: start-mongo ## start node sample
	# since we will change this file, force build
	@docker-compose -f FullNode/docker-compose.yml build
	@docker-compose -f FullNode/docker-compose.yml up -d

start-graphql: ## start graphql sample
	@docker-compose -f graphql-sample/docker-compose.yml up -d

start-all: start-country start-restql start-node start-graphql ## start all containers

build-all: stop-all ## build all docker images
	@docker-compose -f CountryGroovy/docker-compose.yml build
	@docker-compose -f FullNode/docker-compose.yml build
	@docker-compose -f graphql-sample/docker-compose.yml build

.PHONY: help
help: ## show this help
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
