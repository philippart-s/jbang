#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

jbang app uninstall Jarvis
jbang app uninstall httpd
jbang alias remove jarvis-gh

read -n 1 -p "Press any key to continue\n"

jbang alias list
jbang app list