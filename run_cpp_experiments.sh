#!/bin/bash

# Check the number of arguments
if [ "$#" -eq 1 ]; then
    runs=$1
else
    runs=10
fi

echo "Runs = $runs"

echo "BEGIN - Binary Search"
time (for i in $(seq 1 $runs); do
  C++/cpp_experiments
done) > cpp_search.txt

echo "END - Binary Search"

echo "BEGIN - Max Pooling"
time (for i in $(seq 1 $runs); do
  C++/max
done) > cpp_max.txt

echo "END - Max Pooling"

echo "BEGIN - Min Pooling"
time (for i in $(seq 1 $runs); do
  C++/min
done) > cpp_min.txt

echo "END - min Pooling"

echo "BEGIN - avg Pooling"
time (for i in $(seq 1 $runs); do
  C++/avg
done) > cpp_avg.txt

echo "END - avg Pooling"

echo "BEGIN - Sum Pooling"
time (for i in $(seq 1 $runs); do
  C++/sum
done) > cpp_sum.txt

echo "END - Sum Pooling"