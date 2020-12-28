/*
步数        能到达的位置
1:          1
2:          3, 1
3:          6, 4, 2, 0
4:          10, 8, 6, 4, 0
5:          15, 13, 11, 9, 7, 5, 3, 1

每一步能到达的最大位置是上一步最大位置加上步数，而每一步所能达到的位置之间间隔都为2
*/

class Solution {
    public int reachNumber(int target) {
        if (target < 0) {
            target = -target;
        }
        int step = 0;
        int max = 0;
        while (true) {
            if (max >= target && (max - target) % 2 == 0) {
                return step;
            }
            step++;
            max += step;
        }
    }
}
