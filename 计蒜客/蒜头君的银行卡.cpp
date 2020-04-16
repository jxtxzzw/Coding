#include <bits/stdc++.h>

using namespace std;

const int INF = 0x3F3F3F3F;

const int MAX_N = 10007;
const int MAX_M = 10007;
bool inq[MAX_N];
int d[MAX_N];  // 如果到顶点 i 的距离是 0x3F3F3F3F，则说明不存在源点到 i 的最短路
int cnt[MAX_N];
int n;

struct edge {
    int v, next, w;
} e[MAX_M];
int p[MAX_N], eid;

void init() {
    memset(p, -1, sizeof(p));
    eid = 0;
}

void insert(int u, int v, int w) {
    e[eid].v = v;
    e[eid].next = p[u];
    e[eid].w = w;
    p[u] = eid++;
}

// SPFA 算法
void spfa(int s) {
    memset(inq, 0, sizeof(inq));
    memset(d, 0x3F, sizeof(d));
    memset(cnt, 0, sizeof(cnt));
    d[s] = 0;
    inq[s] = true;
    queue<int> q;
    q.push(s);
    cnt[s]++;
    while (!q.empty()) {
        int u = q.front();
        q.pop();
        inq[u] = false;
        for (int i = p[u]; i != -1; i = e[i].next) {
            int v = e[i].v;
            if (d[u] + e[i].w < d[v]) {
                d[v] = d[u] + e[i].w;
                if (!inq[v]) {
                    q.push(v);
                    inq[v] = true;
                    cnt[v]++;
                    if (cnt[v] == n + 1) {
                        // 因为加入了超级源点，所以一共是 n + 1 个点
                        // 出现负环，不等式组无解
                        printf("No");
                        exit(0);
                    }
                }
            }
        }
    }
}

int main() {
    int m;
    scanf("%d%d", &n, &m);
    init();
    for (int i = 0; i < m; i++) {
        int type;
        int a, b;
        scanf("%d%d%d", &type, &a, &b);
        if (type == 3) {
            // (a == b) 等价于 (a <= b && b <= a)
            insert(a, b, 0);
            insert(b, a, 0);
        } else {
            int c;
            scanf("%d", &c);
            if (type == 1) {
                // a 比 b 至少多 c 元：(a - b >= c) => (b - a <= -c) => (a + (-c) >= b)，从 a 到 b 连一条权值为 -c 的边，求最短路
                insert(a, b, -c);
            } else {
                // a 比 b 至多多 c 元：(a - b <= c) => (a - c <= b) => (b + c >= a)，从 b 到 a 连一条权值为 c 的边，求最短路
                insert(b, a, c);
            }
        }
    }
    for (int i = 1; i <= n; i++) {
        insert(0, i, 0); // 插入超级源，连向每一个点，权重为 0
    }

    // 从起点开始做单源最短路
    spfa(1);

    // 如果程序正常结束，那么得到的最短路答案数组就是满足条件的一组解
    printf("Yes");
    return 0;
}
