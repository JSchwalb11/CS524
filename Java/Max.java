package Java;


public class Max {
    // private double[][] filter;
    // private double[][][] image;
    private int[][] filter;
    private int[][][] image;
    private int channels;
    private int imageHeight;
    private int imageWidth;
    private int filterHeight;
    private int filterWidth;
    private int outputHeight;
    private int outputWidth;
    // private double[][][] result;
    private int[][][] result;

    public Max(int[][] filter, int[][][] image) {
        this.filter = filter;
        // this.image = this.imageTo3DArray(image);
        this.image = image;
        this.channels = this.image[0][0].length;
        this.imageHeight = this.image.length;
        this.imageWidth = this.image[0].length;
        this.filterHeight = this.filter.length;
        this.filterWidth = this.filter[0].length;
        this.outputHeight = this.imageHeight - this.filterHeight + 1;
        this.outputWidth = this.imageWidth - this.filterWidth + 1;
        this.result = new int[this.outputHeight][this.outputWidth][this.channels];
        this.apply_max();
    }
    private void apply_max() {
        for (int c = 0; c < this.channels; c++) {
            for (int i = 0; i < this.outputHeight; i++) {
                for (int j = 0; j < this.outputWidth; j++) {
                    int max = Integer.MIN_VALUE;
                    for (int m = 0; m < this.filterHeight; m++) {
                        for (int n = 0; n < this.filterWidth; n++) {
                            int current = this.image[i + m][j + n][c] * this.filter[m][n];
                            max = Math.max(max, current);
                        }
                    }
                    this.result[i][j][c] = max;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Define a simple 2D filter/kernel
        int[][] filter = {
                {1, 0, -1},
                {2, 0, -2},
                {1, 0, -1}
        };
        // Load a sample color image
        int[][][] img = new int[640][640][3];
        
        Max max = new Max(filter, img);
    }
}



