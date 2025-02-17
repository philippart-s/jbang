#!/bin/bash

bat -P -r 5: $(basename "$0")

# Create the Java project using Maven
mvn archetype:generate -DgroupId=fr.wilda -DartifactId=maven-demo -Dversion=1.0.0-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5