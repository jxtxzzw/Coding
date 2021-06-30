
/*用 O(n) 记录下每一个糖果数，正反扫描两边取较大值，这个方法简单
但是省空间的话就要贪心，分类讨论不重不漏很重要*/

class Solution {
        public int candy(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        int ans = 0;
        int up = 0, down = 0;
        int oldDirection = 0;
        for (int i = 1; i < ratings.length; i++) {
            // 看是上升还是下降
            int newDirection = Integer.compare(ratings[i], ratings[i - 1]);

            if (oldDirection == -1 && newDirection != oldDirection) {
                // 2 cases
                // 下降的到头了，所以要把这一部分加起来
                // 等差数列加起来上升下降的部分，然后把公共的最高点加上去（取较大值）
                ans += (up * (up + 1) / 2) + (down * (down + 1) / 2) + Math.max(up, down);
                up = (newDirection == 1 ? 1 : 0);
                down = 0;
            } else if (oldDirection == newDirection) {
                // 3 cases
                // 同号，说明仍在继续
                up += (newDirection == 1 ? 1 : 0);
                down += (newDirection == -1 ? 1 : 0);
            } else if (oldDirection == 0) {
                // 2 cases
                up += (newDirection == 1 ? 1 : 0);
                down += (newDirection == -1 ? 1 : 0);
            } else if (newDirection == 0) {
                // 上升后水平，说明上升的部分完成
                assert down == 0;
                ans += (up * (up + 1) / 2) + up;
                up = 0;
            } else {
                // 上升后下降，继续往后考虑
                assert down == 0;
                down = 1;
            }

            if (newDirection == 0) {
                // 特别地，如果水平，直接加 1
                ans++;
            }
            oldDirection = newDirection;
        }
        ans += (up * (up + 1) / 2) + (down * (down + 1) / 2) + Math.max(up, down);
        return ans + 1;
    }
}