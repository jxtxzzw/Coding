class  BIT2D {
    private final int[][] C;

    public BIT2D(int size1, int size2) {
        C = new int[size1 + 1][size2 + 1];
    }

    private int lowBit(int x) {
        return x & -x;
    }

    public int getSum(int x, int y) {
        x += 1; // offset for BIT
        y += 1;
        int sum = 0;
        for (int i = x; i != 0; i -=lowBit(i)) {
            for (int j = y; j != 0; j -= lowBit(j)) {
                sum += C[i][j];
            }
        }
        return sum;
    }

    public int getSum(int x1, int y1, int x2, int y2) {
        return getSum(x2, y2) - getSum(x2, y1 - 1) - getSum(x1 - 1, y2) + getSum(x1 - 1, y1 - 1);
    }

    public void change(int x, int y, int diff) {
        x += 1; // offset for BIT
        y += 1;
        for (int i = x; i < C.length; i += lowBit(i)) {
            for (int j = y; j < C[i].length; j += lowBit(j)) {
                C[i][j] += diff;
            }
        }
    }
}


class NumMatrix {

    private BIT2D bit2d;
    private int[][] matrix;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        bit2d = new BIT2D(matrix.length, matrix[0].length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                bit2d.change(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int i, int j, int val) {
        int diff = val - matrix[i][j];
        bit2d.change(i, j, diff);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return bit2d.getSum(row1, col1, row2, col2);
    }

}
