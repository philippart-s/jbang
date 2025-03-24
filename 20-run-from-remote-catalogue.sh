#!/bin/bash

# Load enviroment variables
set -a
source .env
set +a

clear

bat -P -r 12:13 $(basename "$0")

# Run alias from remote catalogue
jbang httpd@jbangdev