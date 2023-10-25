#!/bin/bash
cd C++
time (for i in {1..10}; do
  ./cpp_experiments
done) > cpp_results.txt