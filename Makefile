# Make defaults from https://tech.davis-hansson.com/p/make/
SHELL := bash
.ONESHELL:
.SHELLFLAGS := -eu -o pipefail -c
.DELETE_ON_ERROR:
MAKEFLAGS += --warn-undefined-variables
MAKEFLAGS += --no-builtin-rules

BUILD_DIR  := build
IMG_DIR    := resources/public/img

IMG_FILES  := $(shell find $(IMG_DIR) -type f)
TARGET_HTML  := $(BUILD_DIR)/index.html
TARGET_ANALYTICS  := $(BUILD_DIR)/js/autotrack.js
TARGET_FAVICON  := $(BUILD_DIR)/favicon.ico
TARGET_IMG_FILES := $(patsubst $(IMG_DIR)/%, $(BUILD_DIR)/img/%, $(IMG_FILES))

default: build

.PHONY: clean setup

clean:
	rm -rf node_modules
	rm -rf .shadow-cljs
	rm -rf .nrepl-port
	rm -rf build

setup:
	npm install

compile:
	npm run release

build: setup compile $(TARGET_IMG_FILES) $(TARGET_HTML) $(TARGET_ANALYTICS) $(TARGET_FAVICON)

$(TARGET_HTML): resources/public/index.html
	cat $^ | sed 's|autotrack.js|js/autotrack.js|'  > $@

$(TARGET_ANALYTICS): resources/public/autotrack.js
	cp $^ $@

$(TARGET_FAVICON): resources/public/favicon.ico
	cp $^ $@

$(TARGET_IMG_FILES): $(BUILD_DIR)/img/%: $(IMG_DIR)/%
	@[ -d build/img ] || mkdir build/img
	cp $< $@
