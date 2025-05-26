#!/bin/zsh

clear

bat -P -r 7:8 $(basename "$0")

# JBang installation
curl -Ls https://sh.jbang.dev | bash -s - app setup

read -n 1 -p "Press any key to continue\n"

source ~/.zshrc