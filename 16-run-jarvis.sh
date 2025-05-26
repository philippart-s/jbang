#!/bin/bash

# Load enviroment variables
set -a
source .env
set +a

clear

bat -P -r 12:13 $(basename "$0")

# Run Jarvis
Jarvis

read -n 1 -p "Press any key to continue\n"
