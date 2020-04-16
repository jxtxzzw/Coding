#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 107;  // 最大顶点数
const int MAX_M = 10007;  // 最大边数

struct edge {
    int u, v, w;
}e[MAX_M];

int fa[MAX_N], n, m;  // fa 数组记录了并查集中结点的父亲

int d[MAX_N][MAX_N];

bool cmp(edge a,edge b) {
    return a.w < b.w;
}

// 并查集相关代码
int ancestor(int x) {  // 在并查集森林中找到 x 的祖先，也是所在连通块的标识
    if(fa[x] == x) return fa[x];
    else return fa[x] = ancestor(fa[x]);
}
int same(int x, int y) {  // 判断两个点是否在一个连通块（集合）内
    return ancestor(x) == ancestor(y);
}
void merge(int x, int y) {  // 合并两个连通块（集合）
    int fax = ancestor(x), fay = ancestor(y);
    fa[fax] = fay;
}

int main() {
    scanf("%d", &n);  // n 为点数
    m = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            scanf("%d", &d[i][j]);
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            m++;
            e[m].u = i;
            e[m].v = j;
            e[m].w = d[i][j];
        }
    }
    sort(e + 1, e + m + 1, cmp);  // 对边按边权进行升序排序
    for (int i = 1; i <= n; i++) {
        fa[i] = i;
    }
    int rst = n, ans = 0;  // rst 表示还剩多少个集合，ans 保存最小生成树上的总边权
    for (int i = 1; i <= m && rst > 1; i++) {
        int x = e[i].u, y = e[i].v;
        if (same(x, y)) {
            continue;  // same 函数是查询两个点是否在同一集合中
        } else {
            merge(x, y);  // merge 函数用来将两个点合并到同一集合中
            rst--;  // 每次将两个不同集合中的点合并，都将使 rst 值减 1
            ans += e[i].w;  // 这条边是最小生成树中的边，将答案加上边权
        }
    }
    printf("%d\n", ans);
    return 0;
}