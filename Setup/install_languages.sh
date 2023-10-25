#!/bin/bash

# update new system
sudo apt-get update -y && sudo apt-get upgrade -y
# works on ubuntu 22.04
sudo apt install openjdk-19-jre-headless # install java
sudo apt-get install build-essential # ensure all c/c++ compilers are installed
sudo apt install python3-pip # python package manager (python already included)
# install torch deps
wget https://download.pytorch.org/libtorch/cpu/libtorch-cxx11-abi-shared-with-deps-2.1.0%2Bcpu.zip # java/cpp
pip3 install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cpu # python

# add path vars
export PATH="/home/ubuntu/.local/bin:$PATH"
export JAVA_HOME=/usr/bin/java

#misc tools
sudo apt install unzip