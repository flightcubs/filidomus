# Make defaults from https://tech.davis-hansson.com/p/make/
SHELL := bash
.ONESHELL:
.SHELLFLAGS := -eu -o pipefail -c
.DELETE_ON_ERROR:
MAKEFLAGS += --warn-undefined-variables
MAKEFLAGS += --no-builtin-rules

BUILD_DIR  := build

SRC_FILES  := $(shell find src -type f)
CSS_FILES  := $(shell find resources/css -type f)
TARGET_CSS := $(BUILD_DIR)/css/main.css
TARGET_CSS_DEV := resources/public/css/main.css
TARGET_JS  := $(BUILD_DIR)/js/main.js

default: build

css: $(TARGET_CSS_DEV)
cssp: $(TARGET_CSS)

build: $(TARGET_JS) $(TARGET_CSS) index.html

watch: $(TARGET_CSS_DEV)
	npx chokidar $(CSS_FILES) -c "make css"

$(TARGET_JS): $(SRC_FILES)
	@echo "---- Building cljs"
	shadow-cljs release app

$(TARGET_CSS): $(CSS_FILES) $(SRC_FILES)
	@echo "---- Building css"
	npx postcss $< -o $@

$(TARGET_CSS_DEV): $(CSS_FILES)
	npx postcss $< -o $@

clean:
	rm -rf node_modules
	rm -rf .shadow-cljs
	rm -rf .nrepl-port
.PHONY: clean

setup:
	yarn install
.PHONY: setup

serve:
	npx shadow-cljs watch app
.PHONY: serve

.PHONY: build dist build-report stage-install install

release: setup
	npx shadow-cljs release app
.PHONY: release
