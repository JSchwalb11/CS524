#include <iostream>
#include <vector>
#include <limits>

// Function to perform 3D convolution
std::vector<std::vector<std::vector<int>>> convolution3D_max(
    const std::vector<std::vector<std::vector<int>>>& input,
    const std::vector<std::vector<int>>& filter) {

    int filterSize = filter.size();
    int vectorSize = input.size();
    int channelSize = input[0][0].size();
    int outputSize = vectorSize - filterSize + 1;
    // std::cout << "vectorSize " << vectorSize << "n_channels " << channelSize << "\n";

    std::vector<std::vector<std::vector<int>>> result(vectorSize, std::vector<std::vector<int>>(vectorSize, std::vector<int>(channelSize, 1)));

    // Perform convolution
    for (int i = 0; i < channelSize; ++i) {
        for (int j = 0; j < outputSize; ++j) {
            for (int k = 0; k < outputSize; ++k) {
                int maxValue = std::numeric_limits<int>::min();
                // std::cout << i << " " << j << " " << k << "\n" ;
                // Apply the 2D filter
                for (int x = 0; x < filterSize; ++x) {
                    for (int y = 0; y < filterSize; ++y) {
                        int curr = input[i + x][j + y][k] * filter[x][y];
                        if (curr > maxValue){
                            maxValue = curr;
                        }  
                    }
                }
                result[j][k][i] = maxValue;
            }
        }
    }
    return result;
}

int main() {
    // Define a 3D vector (640x640x3)
    std::vector<std::vector<std::vector<int>>> inputVector(640, std::vector<std::vector<int>>(640, std::vector<int>(3, 1)));

    // Define a 2D filter (3x3)
    std::vector<std::vector<int>> filter = {{1, 0, -1},
                                           {2, 0, -2},
                                           {1, 0, -1}};

    // Perform 3D convolution
    std::vector<std::vector<std::vector<int>>> result1 = convolution3D_max(inputVector, filter);

    return 0;
}