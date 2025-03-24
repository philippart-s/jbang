#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Install httpd@jbangdev
jbang app install httpd@jbangdev

read -n 1 -p "Press any key to continue\n"

httpd