#!/bin/bash

clear

bat -P -r 8: $(basename "$0")

cd ../jbang-demo
# App with Java Launch Single File (JEP330)
./AppLSF.java