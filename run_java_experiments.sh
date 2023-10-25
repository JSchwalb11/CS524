#!/bin/bash
time (for i in {1..100}; do
  java Java.java_experiments
done) > java_results.txt