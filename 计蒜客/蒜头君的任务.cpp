#include <bits/stdc++.h>

using namespace std;

const int MAX_N = 200007;
int A[MAX_N] = {0}; // 输入数据 A
int C[MAX_N] = {0}; // 树状数组 C

int lowBit(int x) {
    return x & -x; // return x & (x ^ (x - 1))
}

// 注意哪里是 A 哪里是 C
int getMax(int l, int r) {
    int ret = A[r];
    while (l <= r) {
        ret = max(ret, A[r]);
        for (--r; r - l >= lowBit(r); r -= lowBit(r))
            ret = max(ret, C[r]);
    }
    return ret;
}

void change(int r) {
    C[r] = A[r];
    for (int i = 1; i < lowBit(r); i <<= 1)
        C[r] = max(C[r], C[r - i]);
}

int main() {
    int M, D;
    int r = 1; // 树状数组下标从 1 开始
    int t = 0;
    scanf("%d%d", &M, &D);
    for (int i = 0; i < M; i++) {
        char op;
        scanf("\n%c", &op);
        if (op == 'Q') {
            int l;
            scanf("%d", &l);
            t = getMax(r - l, r - 1); // 最后 L 个数是 r - l 到 r - 1，不是从 l 到 r
            printf("%d\n", t);
        } else {
            int n;
            scanf("%d", &n);
            A[r] = (n + t) % D; // 要记录 A[r]
            change(r);
            r++; // r 其实是作为数列最右端的标识
        }
    }

    return 0;
}
