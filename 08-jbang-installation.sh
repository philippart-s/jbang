#!/bin/bash

bat -P -r 5: $(basename "$0")

# JBang installation
curl -Ls https://sh.jbang.dev | bash -s - app setup

source ~/.zshrc