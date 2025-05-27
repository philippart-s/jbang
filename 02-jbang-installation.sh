#!/bin/bash

clear

bat -P -r 9:10 $(basename "$0")

read -n 1 -p "Press any key to continue"

# JBang installation
curl -Ls https://sh.jbang.dev | bash -s - app setup