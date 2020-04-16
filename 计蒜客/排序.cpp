#include <bits/stdc++.h>

using namespace std;

int n = 0;
const int MAX_N = 500007;
int C[MAX_N] = {0}; // 树状数组
int x[MAX_N] = {0}; // 记录原始数据
int dis[MAX_N] = {0}; // 离散化数据
long long ans = 0;

int lowBit(int x) {
    return x & -x; // return x & (x ^ (x - 1))
}

int getSum(int x) {
    int res = 0;
    while (x != 0) {
        res += C[x];
        x -= lowBit(x);
    }
    return res;
}

void change(int x) {
    while (x <= MAX_N) {
        C[x]++;
        x += lowBit(x);
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        scanf("%d", &x[i]);
        dis[i] = x[i]; // 读入数据，复制一份以便离散化处理
    }
    sort(dis, dis + n); // 对数据排序
    int length = unique(dis, dis + n) - dis; // 利用 unique 去重，使大小与下标对应，并得到去重后的长度
    for (int i = 0; i < n; i++) {
        int index = lower_bound(dis, dis + length, x[i]) - dis; // 在去重后的数组中，使用二分查找找到 x[i] 所在位置
        x[i] = index + 1; // 用这个位置作为离散后的值，由于树状数组下标从 1 开始，因此加 1
    }
    for (int i = 0; i < n; i++) {
        change(x[i]); // 更新树状数组，记下新的数 x
        ans += i + 1 - getSum(x[i]);
        // getSum(x) 是已出现的（在 x 左边的）小于等于 x 的数
        // x 在第 i + 1 位，它和它的左边有 i + 1 个数
        // 所以它左边比它大的数有 i + 1 - getSum(x) 个，有那么多个逆序对，就需要交换那么多次
    }
    printf("%lld", ans);

    return 0;
}