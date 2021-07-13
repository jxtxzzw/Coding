
import java.util.*;


public class LC {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        int ans = 0;
        int debt = 0;
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            tank = tank + gas[i] - cost[i];
            if (tank < 0) {
                ans = i + 1; // 达不到下一个加油站，必不可能是起点
                debt += tank; // 但是这条路又是必须走的（要绕一圈），所以记录会欠下的油
                tank = 0;
            }
        }
        // 绕一圈之后，更新油箱的剩余油量，大于欠账，则可以，否则 -1
        tank += debt;
        return tank >= 0 ? ans : -1;
    }


    public static void main(String[] args) {
        LC lc = new LC();

//        int[] arr1 = new int[]{1,2,3,4,5}, arr2 = new int[]{3,4,5,1,2};
//        assert lc.canCompleteCircuit(arr1, arr2) == (3);
        int[] arr1 = new int[]{5,1,2,3,4}, arr2 = new int[]{4,4,1,5,1};
        assert lc.canCompleteCircuit(arr1, arr2) == (4);

    }
}