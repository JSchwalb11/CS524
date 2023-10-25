#!/bin/bash
time (for i in {1..10}; do
  python3 Python/py_experiments.py
done) > py_results.txt