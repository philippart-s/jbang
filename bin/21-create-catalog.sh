#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 13: $(basename "$0")

cd ../jbang-demo
# Add alias to local catalog
jbang alias add -f ./jbang-catalog.json --name=jarvis-catalog --description="Jarvis application alias" Jarvis.java