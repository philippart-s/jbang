#!/bin/bash

clear

bat -P -r 8: $(basename "$0")

cd ../jbang-demo
# App run with JBang
jbang App.java