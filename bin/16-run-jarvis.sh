#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 15: $(basename "$0")

read -n 1 -p "Press any key to continue"

cd ..
# Run Jarvis
pwd && Jarvis

