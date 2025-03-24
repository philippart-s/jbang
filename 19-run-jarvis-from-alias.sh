#!/bin/bash

# Load enviroment variables
set -a
source .env
set +a

clear

bat -P -r 12:13 $(basename "$0")

# Create the alias
jbang alias add --name jarvis-gh https://github.com/philippart-s/jbang/blob/main/jbang-demo/Jarvis.java

read -n 1 -p "Press any key to continue"
clear

bat -P -r 20: $(basename "$0")

# Use the alias
jbang jarvis-gh