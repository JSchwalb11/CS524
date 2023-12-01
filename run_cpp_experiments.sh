#!/bin/bash

# Check the number of arguments
if [ "$#" -eq 1 ]; then
    runs=$1
else
    runs=10
fi

echo "Runs = $runs"

echo "BEGIN - Binary Search"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time C++/cpp_experiments
  echo "----------"

done

echo "END - Binary Search"

echo "BEGIN - Max Pooling"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time C++/max
  echo "----------"

done

echo "END - Max Pooling"

echo "BEGIN - Min Pooling"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time C++/min
  echo "----------"

done

echo "END - min Pooling"

echo "BEGIN - avg Pooling"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time C++/avg
  echo "----------"

done

echo "END - avg Pooling"

echo "BEGIN - Sum Pooling"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time C++/sum
  echo "----------"
done

echo "END - Sum Pooling"

echo "BEGIN - Linear Layer"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time C++/linear
  echo "----------"
done

echo "END - Linear Pooling"