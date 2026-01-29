#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 13: $(basename "$0")

cd ../jbang-demo
# Jarvis unnamed class
jbang JarvisPreview.java