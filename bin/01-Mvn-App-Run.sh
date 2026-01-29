#!/bin/bash

clear

bat -P -r 15: $(basename "$0")

cd ../maven-demo-create

read -n 1 -p "Press any key to continue"

# Compile before launching
mvn clean compile
clear

# Run the App class
mvn -q -e exec:java -Dexec.useMavenLogger=false -Dexec.quietLogs=true -Dexec.mainClass="fr.wilda.App"