#include <iostream>
#include <vector>
#include <limits>
using namespace std;

// // Function to perform 3D convolution
// std::vector<std::vector<std::vector<int>>> linear_layer(
//     vector<vector<int>>& input,
//     vector<vector<int>>& weights){
//         vector<vector<int>> throw_away = multiplyMatrices(input, weights);
//     }

std::vector<std::vector<int>> multiplyMatrices(std::vector<std::vector<int>>& a, std::vector<std::vector<int>>& b) {
    int rowsA = a.size();
    int colsA = a[0].size();
    int colsB = b[0].size();

    std::vector<std::vector<int>> result(rowsA, std::vector<int>(colsB, 0));

    for (int i = 0; i < rowsA; i++) {
        for (int j = 0; j < colsB; j++) {
            for (int k = 0; k < colsA; k++) {
                result[i][j] += a[i][k] * b[k][j];
            }
        }
    }

    return result;
}

int main() {
    // Define a 3D vector (640x640x1)
    

    int height = 640;
    int width = 640;
    int out_shape = 6;

    vector<vector<int>> input_vector(height, vector<int>(width, 0));
    vector<vector<int>> weights(width, vector<int>(out_shape, 0));
    




    // Repeat 3 times, one for each channel

    vector<vector<int>> result1 = multiplyMatrices(input_vector, weights);
    int min_dim1 = min(result1.size(), result1[0].size());
    vector<vector<int>> activation(min_dim1, vector<int>(min_dim1, 0));

    for (int i = 0; i < min_dim1; ++i) {
        for (int j = 0; j < min_dim1; ++j) {
            // Diagonal elements are 1, others are 0
            activation[i][i] = 1;
        }
    }
    vector<vector<int>> toss = multiplyMatrices(result1, activation);

    vector<vector<int>> result2 = multiplyMatrices(input_vector, weights);
    int min_dim2 = min(result2.size(), result2[0].size());
    vector<vector<int>> activation1(min_dim2, vector<int>(min_dim2, 0));

    for (int i = 0; i < min_dim2; ++i) {
        for (int j = 0; j < min_dim2; ++j) {
            // Diagonal elements are 1, others are 0
            activation1[i][i] = 1;
        }
    }

    vector<vector<int>> result3 = multiplyMatrices(input_vector, weights);
    int min_dim3 = min(result3.size(), result3[0].size());
    vector<vector<int>> activation2(min_dim3, vector<int>(min_dim3, 0));

    for (int i = 0; i < min_dim3; ++i) {
        for (int j = 0; j < min_dim3; ++j) {
            // Diagonal elements are 1, others are 0
            activation2[i][i] = 1;
        }
    }


    return 0;
}