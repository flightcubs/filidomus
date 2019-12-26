######################################################
## Defaults
######################################################
# Make defaults from https://tech.davis-hansson.com/p/make/

SHELL := bash
.ONESHELL:
.SHELLFLAGS := -eu -o pipefail -c
.DELETE_ON_ERROR:
MAKEFLAGS += --warn-undefined-variables
MAKEFLAGS += --no-builtin-rules

######################################################
## Running & developing
######################################################

clean:
	rm -rf node_modules
	rm -rf dist
	rm -rf .shadow-cljs
	rm -rf .nrepl-port
.PHONY: clean

serve:
	npx shadow-cljs watch app
.PHONY: serve

######################################################
## Building & deploying
######################################################

.PHONY: build dist build-report stage-install install

build:
	@rm -rf dist/*
	npx shadow-cljs release app
.PHONY: build

dist: build
	./scripts/create_versioned_html.sh
.PHONY: dist
