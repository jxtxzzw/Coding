#include <bits/stdc++.h>

using namespace std;

const int MAX_N = 107;  // 最大顶点数
const int MAX_M = 10007;  // 最大边数

struct edge {
    int u, v;
    double w;
} e[MAX_M];

struct point {
    int x, y;
} p[MAX_N];

int fa[MAX_N], n, m;  // fa 数组记录了并查集中结点的父亲

bool cmp(edge a, edge b) {
    return a.w < b.w;
}

// 并查集相关代码
int ancestor(int x) {  // 在并查集森林中找到 x 的祖先，也是所在连通块的标识
    if (fa[x] == x) return fa[x];
    else return fa[x] = ancestor(fa[x]);
}

int same(int x, int y) {  // 判断两个点是否在一个连通块（集合）内
    return ancestor(x) == ancestor(y);
}

void merge(int x, int y) {  // 合并两个连通块（集合）
    int fax = ancestor(x), fay = ancestor(y);
    fa[fax] = fay;
}

double distance(int i, int j) {
    int x1 = p[i].x, y1 = p[i].y, x2 = p[j].x, y2 = p[j].y;
    double d = sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
    return d;
}

int main() {
    scanf("%d", &n);  // n 为点数
    m = 0;
    for (int i = 0; i < n; i++) {
        int x, y;
        scanf("%d%d", &x, &y);
        p[i] = {x, y};
    }
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            e[m++] = {i, j, distance(i, j)};
        }
    }
    sort(e, e + m, cmp);  // 对边按边权进行升序排序
    for (int i = 1; i <= n; i++) {
        fa[i] = i;
    }
    int rst = n; double ans = 0;  // rst 表示还剩多少个集合，ans 保存最小生成树上的总边权
    for (int i = 0; i < m && rst > 1; i++) {
        int x = e[i].u, y = e[i].v;
        if (same(x, y)) {
            continue;  // same 函数是查询两个点是否在同一集合中
        } else {
            merge(x, y);  // merge 函数用来将两个点合并到同一集合中
            rst--;  // 每次将两个不同集合中的点合并，都将使 rst 值减 1
            ans += e[i].w;  // 这条边是最小生成树中的边，将答案加上边权
        }
    }
    printf("%.2f\n", ans);
    return 0;
}