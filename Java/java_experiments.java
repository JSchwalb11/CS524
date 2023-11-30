package Java;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class java_experiments {

  public static ArrayList<Integer> setup(String fn){
    String par_dir = Paths.get(".", fn).toAbsolutePath().normalize().toString();

    ArrayList<Integer> arr = new ArrayList<>();
    //try (BufferedReader br = new BufferedReader(new FileReader(fp))) {
    try (BufferedReader br = new BufferedReader(new FileReader(par_dir))) {
      String line;
      while ((line = br.readLine()) != null) {
        arr.add(Integer.parseInt(line));
      }
    }
    catch(Exception e){
      System.out.println(e);
    }

    return arr;
  }

  public static int binarySearch(ArrayList<Integer> arr, int size, int search_value){
    int low = 0, high = size - 1, guesses = 0;

    while (low <= high) {
      guesses = guesses + 1;
      int mid = (low + high) / 2;
      if (arr.get(mid).intValue() == search_value) {
        System.out.println("Found value in " + Integer.toString(guesses) + " guesses.\n");
        return mid;
      }
      else if (arr.get(mid).intValue() < search_value) {
          low = mid + 1;
      }
      else {
        high = mid - 1;
      }
    } 
    System.out.println("You searched for " + Integer.toString(search_value) + " but the value was not found.\n");
    return -1;
  }



  public static double[][][] averagePooling(double[][][] image, int poolSize, int stride) {
        int height = image.length;
        int width = image[0].length;
        int channels = image[0][0].length;

        int outputHeight = (height - poolSize) / stride + 1;
        int outputWidth = (width - poolSize) / stride + 1;

        double[][][] result = new double[outputHeight][outputWidth][channels];

        for (int c = 0; c < channels; c++) {
            for (int i = 0; i < outputHeight * stride; i += stride) {
                for (int j = 0; j < outputWidth * stride; j += stride) {
                    // Extract the region of the image that the pooling is currently applied to
                    double sum = 0;
                    for (int m = 0; m < poolSize; m++) {
                        for (int n = 0; n < poolSize; n++) {
                            sum += image[i + m][j + n][c];
                        }
                    }
                    // Compute the average value of the region
                    result[i / stride][j / stride][c] = sum / (poolSize * poolSize);
                }
            }
        }

        return result;
  }


  public static void saveImage(double[][][] image, String outputPath) {
      int height = image.length;
      int width = image[0].length;

      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

      for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
              int rgb = ((int) image[i][j][0] << 16) | ((int) image[i][j][1] << 8) | (int) image[i][j][2];
              bufferedImage.setRGB(j, i, rgb);
          }
      }

      try {
          ImageIO.write(bufferedImage, "jpg", new File(outputPath));
          System.out.println("Image saved successfully.");
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  public class Convolution2D {

    public static double[][] imageTo3DArray(BufferedImage img) {
        int height = img.getHeight();
        int width = img.getWidth();
        double[][][] imageArray = new double[height][width][3];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = img.getRGB(j, i);
                imageArray[i][j][0] = (rgb >> 16) & 0xFF;
                imageArray[i][j][1] = (rgb >> 8) & 0xFF;
                imageArray[i][j][2] = rgb & 0xFF;
            }
        }

        return imageArray;
    }

    public static double[][] convolution2D(double[][] image, double[][] filter) {
      int imageHeight = image.length;
      int imageWidth = image[0].length;
      int filterHeight = filter.length;
      int filterWidth = filter[0].length;
      int outputHeight = imageHeight - filterHeight + 1;
      int outputWidth = imageWidth - filterWidth + 1;

      double[][] result = new double[outputHeight][outputWidth];

      for (int i = 0; i < outputHeight; i++) {
          for (int j = 0; j < outputWidth; j++) {
              double sum = 0;
              for (int m = 0; m < filterHeight; m++) {
                  for (int n = 0; n < filterWidth; n++) {
                      sum += image[i + m][j + n] * filter[m][n];
                  }
              }
              result[i][j] = sum;
          }
      }

      return result;
  }

  public static double[][][] convolution3D(double[][][] image, double[][] filter) {
      int channels = image[0][0].length;
      int imageHeight = image.length;
      int imageWidth = image[0].length;
      int filterHeight = filter.length;
      int filterWidth = filter[0].length;
      int outputHeight = imageHeight - filterHeight + 1;
      int outputWidth = imageWidth - filterWidth + 1;

      double[][][] result = new double[outputHeight][outputWidth][channels];

      for (int c = 0; c < channels; c++) {
          double[][] channelImage = new double[imageHeight][imageWidth];
          for (int i = 0; i < imageHeight; i++) {
              for (int j = 0; j < imageWidth; j++) {
                  channelImage[i][j] = image[i][j][c];
              }
          }

          double[][] convResult = convolution2D(channelImage, filter);

          for (int i = 0; i < outputHeight; i++) {
              for (int j = 0; j < outputWidth; j++) {
                  result[i][j][c] = convResult[i][j];
              }
          }
      }

      return result;
  }

  public static void main(String[] args) {
    // ArrayList<Integer> arr = setup("sorted_list.csv");
    // int search_value = (int) Math.pow(2, 19); // list is 0-based and of length 2^20. Force 20 guesses.
    // int index = binarySearch(arr, arr.size(), search_value);
    
    // if (index > -1){
    //   System.out.println("Found value " + Integer.toString(search_value) + " at index " + Integer.toString(index) + "\n");
    // }
    
    try {
            // Load a sample color image
            BufferedImage img = ImageIO.read(new File("test.jpg"));

            // Convert BufferedImage to 3D array
            int height = img.getHeight();
            int width = img.getWidth();
            double[][][] image = new double[height][width][3];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int rgb = img.getRGB(j, i);
                    image[i][j][0] = (rgb >> 16) & 0xFF;
                    image[i][j][1] = (rgb >> 8) & 0xFF;
                    image[i][j][2] = rgb & 0xFF;
                }
            }

            // Perform average pooling with a pool size of 2 and stride of 2
            double[][][] pooledResult = averagePooling(image, 2, 2);

            // Save the pooled image
            saveImage(pooledResult, "java_pooled_image.jpg");

        } catch (IOException e) {
            e.printStackTrace();
        }
  }
}