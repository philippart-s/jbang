#!/bin/bash

bat -P -r 6: $(basename "$0")

# JBang installation
curl -Ls https://sh.jbang.dev | bash -s - app setup