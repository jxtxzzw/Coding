/*
由于猪可以按间隔时间喝水，所以有多少组时间，就可以有多少个状态
因为时间的引入，我们有更多的机会去测试，每一只猪携带的信息就更多，底数不再是 2
所以只要计算出真正的底数，其余的部分和二进制是一样的，找到最小的 x 使得 s^x >= N
*/

class Solution {
    // 由于猪可以按间隔时间喝水，所以有多少组时间，就可以有多少个状态
    // 因为时间的引入，我们有更多的机会去测试，每一只猪携带的信息就更多，底数不再是 2
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int group = minutesToTest / minutesToDie;
        return (int)Math.ceil(Math.log(buckets) / (Math.log(group + 1)));
    }
}

/*

|       | 0-15 | 15-30 | 30-45 | 45-60 |
| 0-15  |  0   |   1   |   2   |   3   |
| 15-30 |  4   |   5   |   6   |   7   |
| 30-45 |  8   |   9   |  10   |  11   |
| 45-60 | 12   |  13   |  14   |  15   |
第一次测试时给A喝0、B喝0，第二次测试时给A喝1、B喝4……
这样每只猪有5个信息
5^x >= N
*/

