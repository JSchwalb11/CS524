package Java;

public class Min {
    private int[][] filter;
    private int[][][] image;
    private int channels;
    private int imageHeight;
    private int imageWidth;
    private int filterHeight;
    private int filterWidth;
    private int outputHeight;
    private int outputWidth;
    private int[][][] result;

    public Min(int[][] filter, int[][][] image) {
        this.filter = filter;
        this.image = image;
        this.channels = this.image[0][0].length;
        this.imageHeight = this.image.length;
        this.imageWidth = this.image[0].length;
        this.filterHeight = this.filter.length;
        this.filterWidth = this.filter[0].length;
        this.outputHeight = this.imageHeight - this.filterHeight + 1;
        this.outputWidth = this.imageWidth - this.filterWidth + 1;
        this.result = new int[this.outputHeight][this.outputWidth][this.channels];
        this.apply_min();
    }

    private void apply_min() {
        for (int c = 0; c < this.channels; c++) {
            for (int i = 0; i < this.outputHeight; i++) {
                for (int j = 0; j < this.outputWidth; j++) {
                    int min = Integer.MAX_VALUE;
                    for (int m = 0; m < this.filterHeight; m++) {
                        for (int n = 0; n < this.filterWidth; n++) {
                            int current = this.image[i + m][j + n][c] * this.filter[m][n];
                            min = Math.min(min, current);
                        }
                    }
                    this.result[i][j][c] = min;
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
        
        Min min = new Min(filter, img);
}
}



