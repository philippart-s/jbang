#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 12:13 $(basename "$0")

# Add alias to local catalog
jbang catalog add --name wildagsx https://github.com/philippart-s/jbang/blob/main/jbang-demo/jbang-catalog.json

read -n 1 -p "Press any key to continue"

clear

bat ~/.jbang/jbang-catalog.json

bat -P -r 23:24 $(basename "$0")

# List available catalogs
jbang --fresh catalog list

read -n 1 -p "Press any key to continue"

clear

bat -P -r 32:33 $(basename "$0")

# List wildagsx's remote catalog aliases
jbang --fresh alias list wildagsx
