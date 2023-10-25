import numpy as np
import pandas as pd

if __name__ == '__main__':
    arr = np.arange(1,2**20)
    df = pd.DataFrame(arr)
    df.to_csv("sorted_list.csv", index=False)
    