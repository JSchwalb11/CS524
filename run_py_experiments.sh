#!/bin/bash

# Check the number of arguments
if [ "$#" -eq 1 ]; then
    runs=$1
else
    runs=10
fi

echo "Runs = $runs"

echo "BEGIN - Python Binary Search"
time (for i in $(seq 1 $runs); do
  echo "Run $i"
  python3 Python/py_experiments.py
done) > py_search.txt
echo "END - Python Binary Search"

echo "BEGIN - Python Sum"
time (for i in $(seq 1 $runs); do
  python3 Python/sum.py
done) > py_sum.txt
echo "END - Python Sum"

echo "BEGIN - Python Avg"
time (for i in $(seq 1 $runs); do
  python3 Python/avg.py
done) > py_avg.txt
echo "END - Python Avg"

echo "BEGIN - Python Min"
time (for i in $(seq 1 $runs); do
  python3 Python/min.py
done) > py_min.txt
echo "END - Python Min"

echo "BEGIN - Python Max"
time (for i in $(seq 1 $runs); do
  python3 Python/max.py
done) > py_max.txt
echo "END - Python Max"
