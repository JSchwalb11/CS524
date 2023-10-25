#!/bin/bash
# init git
cd ~ && git init 
# check if demo directory exists. If not, pull git repo.
if [ ! -d "~/java-demo" ]; then
  mkdir java-demo && cd java-demo
  git pull https://github.com/pytorch/java-demo.git
fi