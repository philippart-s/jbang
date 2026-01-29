#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 15: $(basename "$0")

read -n 1 -p "Press any key to continue"

cd ../jbang-demo
# Jarvis CLI mode
./Jarvis.java

