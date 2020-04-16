#include <bits/stdc++.h>

using namespace std;

int n = 0;
const int MAX_N = 100007;
int C[MAX_N] = {0};

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
    int ans[n];
    
    for (int i = 0; i < n; i++) {
        ans[i] = 0;
    }

    for (int i = 0; i < n; i++) {
        int x, y;
        scanf("%d%d", &x, &y);
        x++; // 树状数组的下标从 1 开始
        ans[getSum(x)]++; // 这个棋子左下的棋子个数是 getSum(x)，则对应该等级的棋子个数加 1
        change(x); // 把棋子放在这个位置
    }

    for (int a: ans) {
        printf("%d\n", a);
    }

    return 0;
}