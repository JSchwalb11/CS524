#!/bin/bash


# Check the number of arguments
if [ "$#" -eq 1 ]; then
    runs=$1
else
    runs=10
fi

echo "Runs = $runs"

echo "BEGIN - Java Binary Search"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time java Java.java_experiments
  echo "----------"
done
echo "END - Java Binary Search"


echo "BEGIN - Java Sum Pooling"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time java Java.Sum
  echo "----------"
done
echo "END - Java Sum Pooling"


echo "BEGIN - Java Min Pooling"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time java Java.Min
  echo "----------"
done
echo "END - Java Min Pooling"


echo "BEGIN - Java Max Pooling"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time java Java.Max
  echo "----------"
done
echo "END - Java Max Pooling"


echo "BEGIN - Java Avg Pooling"
for i in $(seq 1 $runs); do
  echo "Run $i"
  time java Java.Avg
  echo "----------"
done
echo "END - Java Avg Pooling"
