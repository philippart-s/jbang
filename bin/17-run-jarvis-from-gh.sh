#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 14: $(basename "$0")

read -n 1 -p "Press any key to continue"

# Run Jarvis from GitHub
jbang https://github.com/philippart-s/jbang/blob/main/jbang-demo/Jarvis.java

