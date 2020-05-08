class Solution {
    
    private final double EPS = 10E-7;
    
    private double slope(int[] p1, int[] p2) {
        int x1 = p1[0], y1 = p1[1], x2 = p2[0], y2 = p2[1];
        if (x1 == x2 && y1 == y2) {
            return Double.NEGATIVE_INFINITY;
        } else if (x1 == x2) {
            return Double.POSITIVE_INFINITY;
        } else if (y1 == y2) {
            return 0;
        } else {
            return (double) (y1 - y2) / (x1 - x2);
        }
    }
    
    public boolean checkStraightLine(int[][] coordinates) {
        int n = coordinates.length;
        double k = slope(coordinates[0], coordinates[1]);
        for (int i = 2; i < n; i++) {
            double kk = slope(coordinates[0], coordinates[i]);
            if (Math.abs(k - kk) > EPS) {
                return false;
            }
        }
        return true;
    }
}
