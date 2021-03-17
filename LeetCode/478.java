class Solution {

    private final double r;
    private final double x;
    private final double y;

    public Solution(double radius, double x_center, double y_center) {
        r = radius;
        x = x_center;
        y = y_center;
    }

    public double[] randPoint() {
        double[] d = new double[2];
        double l = Math.sqrt(Math.random()) * r;
        double a = Math.random() * 2 * Math.PI;
        d[0] = Math.cos(a) * l + x;
        d[1] = Math.sin(a) * l + y;
        return d;
    }
}

// 用极坐标很容易想到，这题的关键在于等概率，即对随机数开方
