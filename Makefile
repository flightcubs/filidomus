# Make defaults from https://tech.davis-hansson.com/p/make/
SHELL := bash
.ONESHELL:
.SHELLFLAGS := -eu -o pipefail -c
.DELETE_ON_ERROR:
MAKEFLAGS += --warn-undefined-variables
MAKEFLAGS += --no-builtin-rules

BUILD_DIR  := build
IMG_DIR    := resources/public/img

SRC_FILES  := $(shell find src -type f)
CSS_FILES  := $(shell find resources/css -type f)
IMG_FILES  := $(shell find $(IMG_DIR) -type f)
TARGET_CSS := $(BUILD_DIR)/css/main.css
TARGET_CSS_DEV := resources/public/css/main.css
TARGET_JS  := $(BUILD_DIR)/js/main.js
TARGET_HTML  := $(BUILD_DIR)/index.html
TARGET_ANALYTICS  := $(BUILD_DIR)/js/autotrack.js
TARGET_FAVICON  := $(BUILD_DIR)/favicon.ico
TARGET_IMG_FILES := $(patsubst $(IMG_DIR)/%, $(BUILD_DIR)/img/%, $(IMG_FILES))

default: build

.PHONY: clean setup serve watch

clean:
	rm -rf node_modules
	rm -rf .shadow-cljs
	rm -rf .nrepl-port
	rm -rf build

setup:
	yarn install

serve:
	npx shadow-cljs watch app

watch: $(TARGET_CSS_DEV)
	npx chokidar $(CSS_FILES) -c "make $(TARGET_CSS_DEV)"

build: setup $(TARGET_JS) $(TARGET_CSS) $(TARGET_IMG_FILES) $(TARGET_HTML) $(TARGET_ANALYTICS) $(TARGET_FAVICON)

$(TARGET_JS): $(SRC_FILES)
	@echo "---- Building cljs"
	shadow-cljs release app

$(TARGET_CSS): $(CSS_FILES) $(SRC_FILES)
	@echo "---- Building css"
	npx postcss $< -o $@

$(TARGET_CSS_DEV): $(CSS_FILES)
	npx postcss $< -o $@

$(TARGET_HTML): resources/public/index.html
	cat $^ | sed 's|autotrack.js|js/autotrack.js|'  > $@

$(TARGET_ANALYTICS): resources/public/autotrack.js
	cp $^ $@

$(TARGET_FAVICON): resources/public/favicon.ico
	cp $^ $@

$(TARGET_IMG_FILES): $(BUILD_DIR)/img/%: $(IMG_DIR)/%
	@[ -d build/img ] || mkdir build/img
	cp $< $@
