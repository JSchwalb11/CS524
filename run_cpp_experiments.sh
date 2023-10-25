#!/bin/bash
cd C++
g++ cpp_experiments.cpp -o cpp_experiments
time (for i in {1..10}; do
  ./cpp_experiments
done) > cpp_results.txt