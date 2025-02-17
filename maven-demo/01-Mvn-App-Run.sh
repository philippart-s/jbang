#!/bin/bash

# Compile before launching
mvn clean compile
clear

bat -P -r 9: $(basename "$0")

# Run the App class
mvn -q -e exec:java -Dexec.useMavenLogger=false -Dexec.quietLogs=true -Dexec.mainClass="fr.wilda.App"