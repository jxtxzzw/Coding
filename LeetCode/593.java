class Solution {
    
    // one edge (a) should occur 4 times and the other (b) should occur 2 times, and (a * sqrt(2) == b)
    int a = 0, b = 0;
    
    // do not sqrt(), to avoid floating points
    private int distanceSquare(int[] x, int[] y) {
        return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
    }
    
    private boolean judge(int[] x, int[] y) {
        int d = distanceSquare(x, y);
        
        // caution: corner case
        if (d == 0) {
            return false;
        }
        
        if (a == 0) {
            a = d;
            return true;
        } else if (b == 0) {
            if (a == d * 2 || a * 2 == d) {
                b = d;
                return true;
            } else {
                return d == a;
            }
        } else {
            return d == a || d == b;
        }
    }
    
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return judge(p1, p2) && judge(p1, p3) && judge(p1, p4) && judge(p2, p3) && judge(p2, p4) && judge(p3, p4);
    }
}