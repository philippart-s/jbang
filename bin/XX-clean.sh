#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

jbang app uninstall Jarvis 
jbang app uninstall jarvis-catalog
jbang app uninstall httpd
jbang alias remove jarvis-gh
jbang catalog remove wildagsx
jbang template remove jarvis-template
rm ../template/logback.xml ../template/myapp.java ../template/Ultron.java

read -n 1 -p "Press any key to continue\n"

jbang alias list
jbang app list
jbang catalog list
jbang template list