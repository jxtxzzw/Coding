
class Solution {
    
    private int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
    
    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
    
    public int mirrorReflection(int p, int q) {
        // 如果光线到达任何一个接收器，那么光路在水平方向上走过的距离+垂直方向上走过的距离，一定是边长的倍数
        int m = lcm(p, q);
        // 每走过一个 q，就左右两边（水平方向）反射 1 次，每走过一个 p，就上下两边（竖直方向）反射 1 次
        int h = m / q;
        int v = m / p;
        // 根据反射的次数选择，每反射 1 次就换边
        if (h % 2 == 1) {
            // 右边镜面
            if (v % 2 == 1) {
                // 上面镜面
                return 1;
            } else {
                return 0;
            }
        } else {
            return 2;
        }
    }
}
