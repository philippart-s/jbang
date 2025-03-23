#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

# Compile before launching
mvn clean compile
clear

bat -P -r 14: $(basename "$0")

# Run the Jarvis class
mvn -q -e exec:java -Dexec.useMavenLogger=false -Dexec.quietLogs=true -Dexec.mainClass="fr.wilda.Jarvis"