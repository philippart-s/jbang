#!/bin/bash

clear

bat -P -r 7: $(basename "$0")

# JBang installation
curl -Ls https://sh.jbang.dev | bash -s - app setup

source ~/.zshrc