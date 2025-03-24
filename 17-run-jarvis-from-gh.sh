#!/bin/bash

# Load enviroment variables
set -a
source .env
set +a

clear

bat -P -r 12: $(basename "$0")

# Run Jarvis from GitHub
jbang https://github.com/philippart-s/jbang/blob/main/jbang-demo/Jarvis.java