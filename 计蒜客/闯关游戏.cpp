#include <bits/stdc++.h>

using namespace std;

const int INF = 0x3F3F3F3F;

const int MAX_N = 100;
const int MAX_M = 10000;
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
    memset(d, -0x3F, sizeof(d)); // 套用模板，初始化为负无穷
    memset(cnt, 0, sizeof(cnt));
//    d[s] = 0;
    d[s] = 100; // 套用模板，初始体力是 100
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
//            if (d[u] + e[i].w < d[v]) {
            if (d[u] + e[i].w > d[v]) {  // 套用模板，要保留走过去的时候生命值最大的
                d[v] = d[u] + e[i].w;
                if (!inq[v]) {
                    q.push(v);
                    inq[v] = true;
                    cnt[v]++;
                    if (cnt[v] == n) {
                        // 如果一个点入队 n 次，说明这个点能无限增加体力
                        // 置终点的剩余体力为无穷大
                        d[n] = INF;
                        return;
                    }
                }
            }
        }
    }
}

int main() {
    scanf("%d", &n);
    init();
    // 本题 id 从 1 开始
    for (int i = 1; i <= n; i++) {
        int hp, rooms, to;
        scanf("%d%d", &hp, &rooms);
        for (int j = 0; j < rooms; j++) {
            scanf("%d", &to);
            insert(i, to, hp);
        }
    }

    // 从起点开始做单源最短路
    spfa(1);

    // 最终输出的结果是：达到终点的时候体力还大于 0 吗
    printf("%s", d[n] > 0 ? "Yes" : "No");
}
