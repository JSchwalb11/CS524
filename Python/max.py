from py_experiments import convolve3d, read_image
import os
import numpy as np


if __name__ == '__main__':
    filter_kernel = np.array([[1, 0, -1],
                            [2, 0, -2],
                            [1, 0, -1]])
    
    # filter_kernel1 = np.array([[-1, 0, 1],
    #                         [-2, 0, 2],
    #                         [-1, 0, 1]])
    
    stride = 1

    #image = np.array(read_image(os.getcwd() + "/img.jpg"))
    image = np.zeros((640,640,3))
    conv_avg_pool_result = convolve3d(image, filter_kernel, stride=stride, func=np.max)
