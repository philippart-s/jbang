#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 12:13 $(basename "$0")

# Jarvis CLI mode
./Jarvis.java

read -n 1 -p "Press any key to continue\n"
