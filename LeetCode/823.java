/**
首先需要先排序，注意到题目中说了数组是 Unique 的，所以每个数字出现的次数只有 1 次
使用 dp 数组保存每个数字作为根节点的情况下能构建出的所有二叉树数目
求法是遍历所有小于自己的数字取值作为左子树，然后把根节点/左子树的值当做右节点，然后对能组成的二叉树数目乘积求和
值应该都是整数，并判断曾经在 dp 中出现
**/

/* 中间计算过程会超过 int 题目没说，应该要提一句，数据保证中间乘法过程不会超过 long 范围 */

class Solution {
    
    private final Integer MOD = 1000000007;
    
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        
        HashMap <Integer, Long> dp = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            dp.put(arr[i], 1L);
            
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0 && dp.containsKey(arr[i] / arr[j])) {
                    Long count = (dp.get(arr[j]) * dp.get(arr[i] / arr[j])) % MOD;
                    Long oldV = dp.get(arr[i]);
                    Long newV = (oldV + count) % MOD;
                    dp.put(arr[i], newV);
                }
            }
            
        }
        
        long ans = 0L;
        for (Integer k : dp.keySet()) {
            ans = (ans + dp.get(k)) % MOD;
        }
        return (int)ans;
        
    }
}