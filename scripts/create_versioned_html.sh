#!/bin/bash

# Assumes the script is run from the project root folder

# First find the versioned javascript filename
# The information of the versioned file(s) is put in "manifest.json" by shadow-cljs
# Pipe the manifest.json file to jq and parse the output-name
# Use --raw-output to avoid getting quotes around the filename
versioned_js_filename=$(cat ./dist/js/manifest.json | jq '.[]."output-name"' --raw-output)

# We want to copy the existing "static" index.html file with reference to /js/main.js
# put it in the dist/ folder, and replace main.js with the versioned js filename
input_file="./public/index.html"
output_file="./dist/index.html"
original_js_filename="js/main.js"
new_filename=js/${versioned_js_filename}
sed "s,$original_js_filename,$new_filename,g" "$input_file" > "$output_file"
