#!/bin/bash

# Load environment variables
set -a
source .env
set +a

clear

bat -P -r 14:15 $(basename "$0")

read -n 1 -p "Press any key to continue"

# List available catalogs
jbang --fresh catalog list

read -n 1 -p "Press any key to continue"

clear

bat -P -r 25:26 $(basename "$0")

read -n 1 -p "Press any key to continue"

# List available aliases in the jbangdev catalog
jbang --fresh alias list jbangdev

read -n 1 -p "Press any key to continue"

clear

bat -P -r 34:35 $(basename "$0")

# Run alias from remote catalog
jbang httpd@jbangdev