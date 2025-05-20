#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Install Jarvis
jbang app install Jarvis.java