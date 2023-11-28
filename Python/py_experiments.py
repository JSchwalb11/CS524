import pandas as pd
import numpy as np
from pathlib import Path
from PIL import Image

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

def read_image(fp: str) -> Image.Image:
    return Image.open(fp)

def convolve3d(image: np.ndarray, kernel: np.ndarray, stride=3, padding=0, func=np.sum):
    # Get the dimensions of the input image and kernel
    image_height, image_width, num_channels = image.shape
    kernel_height, kernel_width = kernel.shape
    
    # Calculate the output dimensions
    output_height = (image_height - kernel_height + 2 * padding) // stride + 1
    output_width = (image_width - kernel_width + 2 * padding) // stride + 1
    
    # Initialize the result with zeros
    result = np.zeros((output_height, output_width, num_channels), dtype=np.uint8)
    
    # Add padding to the image
    padded_image = np.pad(image, ((padding, padding), (padding, padding), (0, 0)), mode='constant')
    
    # Flip the kernel for the convolution operation
    kernel = np.flipud(np.fliplr(kernel))
    
    # Perform the convolution with stride and padding
    for c in range(num_channels):
        for i in range(0, output_height * stride, stride):
            for j in range(0, output_width * stride, stride):
                # Extract the region of the padded image that the kernel is currently applied to
                image_patch = padded_image[i:i + kernel_height, j:j + kernel_width, c]
                # Compute the dot product of the region and the kernel
                result[i // stride, j // stride, c] = func(image_patch * kernel).astype(np.uint8)
    
    return result
if __name__ == '__main__':
    # arr = setup()
    # search_value = 2**19 + 1 # list is 0-based and of length 2^20. Force 20 guesses.
    # index = binarySearch(arr, len(arr), search_value)
    # if (index > -1):
    #     print(f"Found value {search_value} at index {index}\n")
    # #   System.out.println("Found value " + Integer.toString(search_value) + " at index " + Integer.toString(index) + "\n");
    # # }
    
    filter_kernel = np.array([[1, 0, -1],
                            [2, 0, -2],
                            [1, 0, -1]])
    
    filter_kernel1 = np.array([[-1, 0, 1],
                            [-2, 0, 2],
                            [-1, 0, 1]])
    
    stride = 1

    image = np.array(read_image(os.getcwd() + "/img.jpg"))
    # conv_result1 = convolve3d(image, filter_kernel, stride=stride)
    # image1 = Image.fromarray(conv_result1)
    # image1.save(f"res_1_stride={stride}.jpg")
    
    # conv_result2 = convolve3d(image, filter_kernel1, stride=stride)
    # image2 = Image.fromarray(conv_result2)
    # image2.save(f"res_2_stride={stride}.jpg")
    
    conv_avg_pool_result = convolve3d(image, filter_kernel1, stride=stride, func=np.mean)
    image2 = Image.fromarray(conv_avg_pool_result)
    image2.save(f"avg_pool_res_1_stride={stride}.jpg")
    
    conv_max_pool_result = convolve3d(image, filter_kernel1, stride=stride, func=np.max)
    image2 = Image.fromarray(conv_avg_pool_result)
    image2.save(f"max_pool_res_1_stride={stride}.jpg")
    
    conv_min_pool_result = convolve3d(image, filter_kernel1, stride=stride, func=np.min)
    image2 = Image.fromarray(conv_min_pool_result)
    image2.save(f"min_pool_res_1_stride={stride}.jpg")