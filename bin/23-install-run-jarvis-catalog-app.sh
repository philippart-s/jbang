#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 12:13 $(basename "$0")

# Install remote alias from catalog
jbang app install jarvis-catalog@wildagsx

read -n 1 -p "Press any key to continue"

clear

bat -P -r 21:22 $(basename "$0")

# Test the application
jarvis-catalog

echo ""
read -n 1 -p "Press any key to continue"
