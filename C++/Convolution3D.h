#ifndef CONVOLUTION3D_H
#define CONVOLUTION3D_H

#include<vector>

std::vector<std::vector<std::vector<int>>> convolution3D_sum(
    const std::vector<std::vector<std::vector<int>>>& input,
    const std::vector<std::vector<int>>& filter);

std::vector<std::vector<std::vector<int>>> convolution3D_min(
    const std::vector<std::vector<std::vector<int>>>& input,
    const std::vector<std::vector<int>>& filter);

std::vector<std::vector<std::vector<int>>> convolution3D_max(
    const std::vector<std::vector<std::vector<int>>>& input,
    const std::vector<std::vector<int>>& filter);

    std::vector<std::vector<std::vector<int>>> convolution3D_avg(
    const std::vector<std::vector<std::vector<int>>>& input,
    const std::vector<std::vector<int>>& filter);

#endif