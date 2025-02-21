#!/bin/bash

# Load enviroment variables
source ../.env

bat -P -r 6: $(basename "$0")

# JBang installation
./Jarvis