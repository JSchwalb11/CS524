import pandas as pd
import numpy as np
from pathlib import Path

import os


def setup(fn = "sorted_list.csv") -> np.ndarray:
    par_dir = os.getcwd()
    fn = "sorted_list.csv"
    fp = os.path.join(par_dir, fn)
    df = pd.read_csv(fp)
    return df.values.flatten()

def binarySearch(arr: np.ndarray, size:int, search_value:int):
    low = 0
    high = size - 1
    guesses = 0

    while (low <= high):
      guesses = guesses + 1
      mid = (low + high) // 2
      if (arr[mid] == search_value):
        print(f"Found value in {guesses} guesses\n")
        return mid
      elif (arr[mid] < search_value):
          low = mid + 1
      else:
        high = mid - 1
        
    print(f"You searched for {search_value} but the value was not found.\n")
    return -1

if __name__ == '__main__':
    arr = setup()
    search_value = 2**19 + 1 # list is 0-based and of length 2^20. Force 20 guesses.
    index = binarySearch(arr, len(arr), search_value)
    if (index > -1):
        print(f"Found value {search_value} at index {index}\n")
    #   System.out.println("Found value " + Integer.toString(search_value) + " at index " + Integer.toString(index) + "\n");
    # }

    