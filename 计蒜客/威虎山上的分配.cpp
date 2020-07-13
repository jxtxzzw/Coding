#include <bits/stdc++.h>

using namespace std;

const int MAX_N = 10007;
const int MAX_M = 20007;

struct edge {
    int v, next;
} e[MAX_M];

int p[MAX_N], eid;
int n;
int indegree[MAX_N];
int money[MAX_N];
int cnt = 0;

void init() {
    memset(p, -1, sizeof(p));
    memset(indegree, 0, sizeof(indegree));
    fill(money, money + MAX_N, 100);
    eid = 0;
    cnt = 0;
}

void insert(int u, int v) {
    e[eid].v = v;
    e[eid].next = p[u];
    p[u] = eid++;
}

int topo() {
    queue<int> q;
    for (int i = 1; i <= n; i++) {
        if (indegree[i] == 0) {
            q.push(i);
        }
    }

    while (!q.empty()) {
        int now = q.front();
        q.pop();
        cnt++;
        for (int i = p[now]; i != -1; i = e[i].next) {
            int v = e[i].v;
            money[v] = money[now] + 1;
            indegree[v]--;
            if (indegree[v] == 0) {
                q.push(v);
            }
        }
    }

    return cnt == n;
}


int main() {
    int m;
    scanf("%d%d", &n, &m);
    init();
    for (int  i = 0; i < m; i++) {
        int a, b;
        scanf("%d%d", &a, &b);
        insert(b, a);
        indegree[a]++;
    }
    if (topo()) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += money[i];
        }
        printf("%d\n", sum);
    } else {
        printf("Unhappy!");
    }
    return 0;
}