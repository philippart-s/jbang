#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

bat -P -r 10: $(basename "$0")

# Jarvis CLI mode
./Jarvis.java