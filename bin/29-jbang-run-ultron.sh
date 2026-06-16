#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 16: $(basename "$0")

read -n 1 -p "Press any key to continue"

cd ../template

# Run Ultron
jbang Ultron.java