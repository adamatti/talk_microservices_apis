.DEFAULT_GOAL := help

clean:

run: ## Run the app
	@gradle run

start: run

docker-stop: ## Stop docker
	@docker-compose stop -t 0

docker-build: docker-stop ## build docker image
	@docker-compose build

docker-run: ## run docker image
	@docker-compose up -d app

.PHONY: help
help: ## show this help
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
