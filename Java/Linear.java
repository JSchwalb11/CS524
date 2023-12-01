package Java;

public class Linear {
    private int[][] image;
    private int[][] weights;
    private int[][] result;
    private int[][] activation;

    public Linear(int[][] image, int out_shape){
        this.image = image;
        this.weights = new int[this.image.length][out_shape];
        // this.result = new int[this.image.length][out_shape];
        this.apply_step();
    }

    private void apply_step(){
        multiplyMatrices(this.image, this.weights);
        int min_dim = Math.min(this.result[0].length, this.result.length);
        this.activation = new int[min_dim][min_dim];
        for (int i = 0; i < min_dim; i++) {
            this.activation[i][i] = 1;
        }
        multiplyMatrices(this.result, this.activation);

    }

    public void multiplyMatrices(int[][] a, int[][] b) {

        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;

        this.result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    this.result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    }
    public static void main(String[] args) {
        // Load a sample color image
        int[][] img = new int[640][640];
        
        // Repeat 3 times, one for each channel
        Linear lin = new Linear(img, 6);
        Linear lin1 = new Linear(img, 6);
        Linear lin2 = new Linear(img, 6);
    }

}
