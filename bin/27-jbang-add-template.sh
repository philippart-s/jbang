#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 15:16 $(basename "$0")

read -n 1 -p "Press any key to continue"

cd ..
# Jarvis template creation
jbang template add --name jarvis-template ./jbang-demo/JarvisPreview.java ./jbang-demo/resources/logback.xml

read -n 1 -p "Press any key to continue"

clear

bat ~/.jbang/jbang-catalog.json