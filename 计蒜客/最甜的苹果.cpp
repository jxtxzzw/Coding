#include <bits/stdc++.h>

using namespace std;

const int MAX_N = 200000 * 3 + 7; // 由于还要 2 倍的父节点，所以乘以 3
int s[MAX_N] = {0};
int n = 0, m = 0;

// 修改
void modify(int p, int l, int r, int x, int v)
{
    s[p] = v;
    if (l == r) return; // 叶结点则退出
    int mid = (l + r) / 2;
    if (x <= mid) // 判断 x 在左儿子还是右儿子
        modify(p * 2, l, mid, x, v);
    else
        modify(p * 2 + 1, mid + 1, r, x, v);
	s[p] = max(s[p * 2], s[p * 2 + 1]); // 修改：保存最大值
}

// 封装后的修改，以供调用
void modify(int x, int v) {
    modify(1, 1, n, x, v);
}

// 查询
int query(int p, int l, int r, int x, int y)
{
    if (x <= l && r <= y) return s[p]; // 若该结点被查询区间包含
    int mid = (l + r) / 2, res = 0;
    if (x <= mid) res = max(res, query(p * 2, l, mid, x, y)); // 保存最大值
    if (y > mid) res = max(res, query(p * 2 + 1, mid + 1, r, x, y)); // 保存最大值
    return res;
}

// 封装后的查询，以供调用
int query(int x, int y) {
    return query(1, 1, n, x, y);
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; i++) {
        int v;
        scanf("%d", &v);
        modify(i, v);
    }
    for (int i = 0; i < m; i++) {
        char c;
        scanf("\n%c", &c);
        int x, y;
        scanf("%d%d", &x, &y);
        switch (c) {
            case 'Q':
                printf("%d\n", query(x, y));
                break;
            case 'U':
                modify(x, y);
                break;
            default:
                break;
        }
    }
    return 0;
}
