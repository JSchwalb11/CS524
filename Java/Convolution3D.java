package Java;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Java.*;

public class Convolution3D {

    private double[][] filter;
    private double[][][] image;
    private int channels;
    private int imageHeight;
    private int imageWidth;
    private int filterHeight;
    private int filterWidth;
    private int outputHeight;
    private int outputWidth;
    private double[][][] result;
    
    public Convolution3D(double[][] filter, BufferedImage image) {
        this.filter = filter;
        this.image = this.imageTo3DArray(image);
        this.channels = this.image[0][0].length;
        this.imageHeight = this.image.length;
        this.imageWidth = this.image[0].length;
        this.filterHeight = this.filter.length;
        this.filterWidth = this.filter[0].length;
        this.outputHeight = this.imageHeight - this.filterHeight + 1;
        this.outputWidth = this.imageWidth - this.filterWidth + 1;
        this.result = new double[this.outputHeight][this.outputWidth][this.channels];
        this.apply();
    }

    public Convolution3D(double[][] filter, BufferedImage image, Sum nothing) {
        this.filter = filter;
        this.image = this.imageTo3DArray(image);
        this.channels = this.image[0][0].length;
        this.imageHeight = this.image.length;
        this.imageWidth = this.image[0].length;
        this.filterHeight = this.filter.length;
        this.filterWidth = this.filter[0].length;
        this.outputHeight = this.imageHeight - this.filterHeight + 1;
        this.outputWidth = this.imageWidth - this.filterWidth + 1;
        this.result = new double[this.outputHeight][this.outputWidth][this.channels];
        this.apply();
    }

    public Convolution3D(double[][] filter, BufferedImage image, Max nothing) {
        this.filter = filter;
        this.image = this.imageTo3DArray(image);
        this.channels = this.image[0][0].length;
        this.imageHeight = this.image.length;
        this.imageWidth = this.image[0].length;
        this.filterHeight = this.filter.length;
        this.filterWidth = this.filter[0].length;
        this.outputHeight = this.imageHeight - this.filterHeight + 1;
        this.outputWidth = this.imageWidth - this.filterWidth + 1;
        this.result = new double[this.outputHeight][this.outputWidth][this.channels];
        this.apply_max();
    }

    public Convolution3D(double[][] filter, BufferedImage image, Min nothing) {
        this.filter = filter;
        this.image = this.imageTo3DArray(image);
        this.channels = this.image[0][0].length;
        this.imageHeight = this.image.length;
        this.imageWidth = this.image[0].length;
        this.filterHeight = this.filter.length;
        this.filterWidth = this.filter[0].length;
        this.outputHeight = this.imageHeight - this.filterHeight + 1;
        this.outputWidth = this.imageWidth - this.filterWidth + 1;
        this.result = new double[this.outputHeight][this.outputWidth][this.channels];
        this.apply_min();
    }

    public Convolution3D(double[][] filter, BufferedImage image, Average nothing) {
        this.filter = filter;
        this.image = this.imageTo3DArray(image);
        this.channels = this.image[0][0].length;
        this.imageHeight = this.image.length;
        this.imageWidth = this.image[0].length;
        this.filterHeight = this.filter.length;
        this.filterWidth = this.filter[0].length;
        this.outputHeight = this.imageHeight - this.filterHeight + 1;
        this.outputWidth = this.imageWidth - this.filterWidth + 1;
        this.result = new double[this.outputHeight][this.outputWidth][this.channels];
        this.apply_avg();
    }

    public Convolution3D(double[][] filter, BufferedImage image, boolean no_op) {
        this.filter = filter;
        this.image = this.imageTo3DArray(image);
        this.channels = this.image[0][0].length;
        this.imageHeight = this.image.length;
        this.imageWidth = this.image[0].length;
        this.filterHeight = this.filter.length;
        this.filterWidth = this.filter[0].length;
        this.outputHeight = this.imageHeight - this.filterHeight + 1;
        this.outputWidth = this.imageWidth - this.filterWidth + 1;
        this.result = this.image;
    }

    private void apply() {
        for (int c = 0; c < this.channels; c++) {
            for (int i = 0; i < this.outputHeight; i++) {
                for (int j = 0; j < this.outputWidth; j++) {
                    double sum = 0;
                    for (int m = 0; m < this.filterHeight; m++) {
                        for (int n = 0; n < this.filterWidth; n++) {
                            double a = this.image[i + m][j + n][c];
                            double b = this.filter[m][n];
                            sum += a * b;
                            // sum += this.image[i + m][j + n][c] * this.filter[m][n];
                        }
                    }
                    this.result[i][j][c] = (int) sum;
                }
            }
        }
    }

    private void apply_max() {
        for (int c = 0; c < this.channels; c++) {
            for (int i = 0; i < this.outputHeight; i++) {
                for (int j = 0; j < this.outputWidth; j++) {
                    double max = Double.MIN_VALUE;
                    for (int m = 0; m < this.filterHeight; m++) {
                        for (int n = 0; n < this.filterWidth; n++) {
                            double current = this.image[i + m][j + n][c] * this.filter[m][n];
                            max = Math.max(max, current);
                        }
                    }
                    this.result[i][j][c] = max;
                }
            }
        }
    }

    private void apply_min() {
        for (int c = 0; c < this.channels; c++) {
            for (int i = 0; i < this.outputHeight; i++) {
                for (int j = 0; j < this.outputWidth; j++) {
                    double min = Double.MAX_VALUE;
                    for (int m = 0; m < this.filterHeight; m++) {
                        for (int n = 0; n < this.filterWidth; n++) {
                            double current = this.image[i + m][j + n][c] * this.filter[m][n];
                            min = Math.min(min, current);
                        }
                    }
                    this.result[i][j][c] = min;
                }
            }
        }
    }

    private void apply_avg() {
        for (int c = 0; c < this.channels; c++) {
            for (int i = 0; i < this.outputHeight; i++) {
                for (int j = 0; j < this.outputWidth; j++) {
                    double sum = 0;
                    for (int m = 0; m < this.filterHeight; m++) {
                        for (int n = 0; n < this.filterWidth; n++) {
                            sum += this.image[i + m][j + n][c] * this.filter[m][n];
                        }
                    }
                    this.result[i][j][c] = sum / (this.filterHeight * this.filterWidth);
                }
            }
        }
    }

    private void saveImage(String outputPath) {
        int height = this.result.length;
        int width = this.result[0].length;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = ((int) this.result[i][j][0] << 16) | ((int) this.result[i][j][1] << 8) | (int) this.result[i][j][2];
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

    public double[][][] imageTo3DArray(BufferedImage img) {
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


    public static void main(String[] args) {
        // Define a simple 2D filter/kernel
        double[][] filter = {
                {1, 0, -1},
                {2, 0, -2},
                {1, 0, -1}
        };
        try {
            // Load a sample color image
            BufferedImage img = ImageIO.read(new File("img.jpg"));

            Convolution3D convolution3D = new Convolution3D(filter, img, true);
            convolution3D.saveImage("java_before.jpg");

            // Convolution3D convolution3D1 = new Convolution3D(filter, img, new Sum());
            // convolution3D1.saveImage("java_after_sum.jpg");

            // Convolution3D convolution3D2 = new Convolution3D(filter, img, new Max());
            // convolution3D2.saveImage("java_after_max.jpg");

            // Convolution3D convolution3D3 = new Convolution3D(filter, img, new Min());
            // convolution3D3.saveImage("java_after_min.jpg");

            // Convolution3D convolution3D4 = new Convolution3D(filter, img, new Average());
            // convolution3D4.saveImage("java_after_avg.jpg");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
