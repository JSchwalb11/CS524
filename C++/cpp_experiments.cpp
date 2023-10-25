#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <filesystem>
#include <math.h>


namespace fs = std::filesystem;

std::vector<int> setup(std::string fn){

  fs::path p = std::filesystem::current_path();
  std::string c = p.parent_path().generic_string();

  std::ifstream csv_file(c + "/" + fn);
  std::string line;
  std::vector<int> arr;

  while (std::getline(csv_file, line)) {
    arr.push_back(stoi(line));
  }

  return arr;  
}

int binary_search(std::vector<int> arr, int size, int search_value) {
  int low = 0, high = size - 1, guesses = 0;

  while (low <= high) {
    guesses = guesses + 1;
    int mid = (low + high) / 2;
    if (arr[mid] == search_value) {
      std::cout << "Found value in " << std::to_string(guesses) << " guesses.\n";
      return mid;
    } else if (arr[mid] < search_value) {
      low = mid + 1;
    } else {
      high = mid - 1;
    }
  }
  std::cout << "You searched for " << std::to_string(search_value) << " but the value was not found.\n";
  return -1;
}


int main(int argc, char* argv[]) {
    std::vector<int> arr = setup("sorted_list.csv");
    int search_value = static_cast<int>(pow(2,19)); // list is 0-based and of length 2^20. Force 20 guesses.
    std::cout << "Searching for " << std::to_string(search_value) << "\n";
    int index = binary_search(arr, arr.size(), search_value);

    if (index > -1){
      std::cout << "Found value " << search_value << " at index " << index << "\n";
    }

    return 0;
}