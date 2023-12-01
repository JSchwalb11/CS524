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
  time python3 Python/py_experiments.py
  echo "----------"
done
echo "END - Binary Search"

echo "BEGIN - Python Sum"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time time python3 Python/sum.py
  echo "----------"
done
echo "END - Sum"

echo "BEGIN - Avg"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time python3 Python/avg.py
  echo "----------"
done
echo "END - Avg"

echo "BEGIN - Min"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time python3 Python/min.py
  echo "----------"
done
echo "END - Python Min"

echo "BEGIN - Max"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time python3 Python/max.py
  echo "----------"
done
echo "END - Max"

echo "BEGIN - Linear"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time python3 Python/linear.py
  echo "----------"
done
echo "END - Linear"

