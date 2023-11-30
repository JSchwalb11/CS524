#!/bin/bash


# Check the number of arguments
if [ "$#" -eq 1 ]; then
    runs=$1
else
    runs=10
fi

echo "Runs = $runs"

echo "BEGIN - Java Binary Search"
time (for i in $(seq 1 $runs); do
  echo "Run $i"
  java Java.java_experiments
done) > java_search.txt
echo "END - Java Binary Search"


echo "BEGIN - Java Sum Pooling"
time (for i in $(seq 1 $runs); do
  java Java.Sum
done) > java_sum.txt
echo "END - Java Sum Pooling"


echo "BEGIN - Java Min Pooling"
time (for i in $(seq 1 $runs); do
  java Java.Min
done) > java_Min.txt
echo "END - Java Min Pooling"


echo "BEGIN - Java Max Pooling"
time (for i in $(seq 1 $runs); do
  java Java.Max
done) > java_Max.txt
echo "END - Java Max Pooling"


echo "BEGIN - Java Avg Pooling"
time (for i in $(seq 1 $runs); do
  java Java.Avg
done) > java_Avg.txt
echo "END - Java Avg Pooling"
