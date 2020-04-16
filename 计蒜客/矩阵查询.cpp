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

// 子矩阵查询
int subSum(int x1, int y1, int x2, int y2) {
    return sum(x2, y2) - sum(x1 - 1, y2) - sum(x2, y1 - 1) + sum(x1 - 1, y1 - 1);
}

int main() {
    int t;
    scanf("%d%d", &n, &t);
    for (int i = 0; i < t; i++) {
        char op;
        scanf("\n%c", &op);
        if (op == 'C') {
            int x, y, c;
            scanf("%d%d%d", &x, &y, &c);
            change(x, y, c);
        } else {
            int x1, y1, x2, y2;
            scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
            printf("%d\n", subSum(x1, y1, x2, y2));
        }
    }

    return 0;
}
