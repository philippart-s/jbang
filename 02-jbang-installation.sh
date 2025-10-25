#!/bin/bash

clear

# JBang installation
echo "How install JBang?"
echo "     1️⃣. curl -Ls https://sh.jbang.dev | bash -s - app setup"
echo "     2️⃣. brew install jbangdev/tap/jbang"
echo "     3️⃣. sdk install jbang"
echo ""
read -n 1 -p "Press any key to continue"

clear

bat -P -r 22: $(basename "$0")

# Load SDKMAN into this script environment
if [ -s "$HOME/.sdkman/bin/sdkman-init.sh" ]; then
    source "$HOME/.sdkman/bin/sdkman-init.sh"
fi

# Display current installed SDKs
sdk current