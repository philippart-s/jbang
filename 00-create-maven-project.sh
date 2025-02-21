#!/bin/bash

rm -rf ./maven-demo-create/.mvn ./maven-demo-create/src ./maven-demo-create/target ./maven-demo-create/pom.xml

bat -P -r 7: $(basename "$0")

# Create the Java project using Maven
mvn archetype:generate -DgroupId=fr.wilda -DartifactId=maven-demo-create -Dversion=1.0.0-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5