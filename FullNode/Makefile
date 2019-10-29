.DEFAULT_GOAL := help

run:
	@npm start

start: run

deps: ## Start dependencies
	@docker-compose up -d mongo

docker-stop:
	@docker-compose stop -t 0

docker-build: docker-stop
	@docker-compose build

docker-run:
	@docker-compose up -d

.PHONY: help
help: ## show this help
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
