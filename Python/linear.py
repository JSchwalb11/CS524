import numpy as np

if __name__ == '__main__':
    out_shape = 6
    
    arr1 = np.ones((640,640), dtype=np.int32)
    weights = np.zeros((640, 6))
    
    
    result = np.dot(arr1,weights)
    
    activation = np.identity(np.min(result.shape), dtype=np.int32)
    
    out = np.dot(result,activation)

    # image = np.zeros((640,640,3), dtype=np.int32)
    # conv_avg_pool_result = convolve3d(image, filter_kernel, stride=stride, func=np.sum)