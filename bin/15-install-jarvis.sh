#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 13:14 $(basename "$0")

cd ../jbang-demo
# Install Jarvis
jbang app install Jarvis.java

read -n 1 -p "Press any key to continue"

clear

cd ../bin
bat -P -r 23: $(basename "$0")

ls -lart ~/.jbang/bin