#include <bits/stdc++.h>

using namespace std;

int n = 0;
const int MAX_N = 1007;
int C[MAX_N][MAX_N] = {{0}}; // 树状数组 C

int lowBit(int x) {
    return x & -x; // return x & (x ^ (x - 1))
}

// 查询
int sum(int i, int j){
    int res = 0;
    for(int x = i; x; x -= lowBit(x))
        for(int y = j; y; y -= lowBit(y))
            res += C[x][y];
    return res;
}

// 修改
void change(int i, int j, int delta){
    for(int x = i; x < MAX_N; x += lowBit(x)) {
        for (int y = j; y < MAX_N; y += lowBit(y)) {
            C[x][y] += delta;
        }
    }
}

// 子矩阵的操作只需要使用 4 个树状数组矩阵的容斥就可以解决

// 子矩阵修改
void subChange(int x1, int y1, int x2, int y2) {
    int delta = 1; // 本题记录操作次数，因此 delta 为 1
    change(x1, y1, delta);
    change(x1, y2 + 1, delta);
    change(x2 + 1, y1, delta);
    change(x2 + 1, y2 + 1, delta);
}

int main() {
    int t;
    scanf("%d%d", &n, &t);
    for (int i = 0; i < t; i++) {
        char op;
        scanf("\n%c", &op);
        if (op == 'C') {
            int x1, y1, x2, y2;
            scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
            subChange(x1, y1, x2, y2);
        } else {
            int x, y;
            scanf("%d%d", &x, &y);
            printf("%d\n", sum(x, y) % 2);
        }
    }

    return 0;
}
