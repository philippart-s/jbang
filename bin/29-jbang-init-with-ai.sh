#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 16:17 $(basename "$0")

read -n 1 -p "Press any key to continue"

cd ../template

# Init JBang script thanks to AI Endpoints
jbang init myapp.java "Create a simple script to illustrate a streaming chatbot thanks to LangChain4J and the openAI provider, the API key is OVH_AI_ENDPOINTS_ACCESS_TOKEN, the URL is OVH_AI_ENDPOINTS_MODEL_URL ans the model name is OVH_AI_ENDPOINTS_MODEL_NAME"