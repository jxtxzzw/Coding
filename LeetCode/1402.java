import java.util.*;


public class LC {

    // 很巧妙的贪心：越是珍贵的菜，越是要往后排
    // 且，如果我们要做一道新菜，那就势必要使得总的价值是增加的
    public int maxSatisfaction(int[] satisfaction) {
        int index = satisfaction.length - 1;
        Arrays.sort(satisfaction);
        // 我们总是假设第一秒会做最珍贵的菜 A
        int possible = 0;
        int sum = 0;
        // 但是我们始终在下一秒才做（如果下一秒有更合适的菜 B，则 A 会顺延一位）
        // 下一秒有 C，则 B 被推迟到第 2 秒，A 被推迟到第 3 秒
        // 如果上 D 会使得总价值下降，则不再上菜
        // 这里的下降不是菜为负数，而是总价值，因为菜虽然为负数但是有可能因为 A 被后移，总价值更高
        while (index >= 0) {
            if (sum + possible + satisfaction[index] > sum) {
                possible += satisfaction[index];
                sum += possible;
                index--;
            } else {
                break;
            }
        }
        System.out.println(sum);
        return sum;
    }


    public static void main(String[] args) {
        LC lc = new LC();

        int[] arr1 = new int[]{-1,-8,0,5,-9}, arr2 = new int[]{-1,-4,-5}, arr3 = new int[]{-2,5,-1,0,3,-3};
        assert lc.maxSatisfaction(arr1) == (14);
        assert lc.maxSatisfaction(arr2) == (0);
        assert lc.maxSatisfaction(arr3) == (35);

    }
}
