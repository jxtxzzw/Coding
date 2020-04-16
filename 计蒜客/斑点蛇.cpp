#include <bits/stdc++.h>

using namespace std;

const int MAX_N = 50000 * 3 + 7; // N 最大是 50000，由于还要 2 倍的父节点，所以乘以 3
const int MAX_CMD_LENGTH = 10;
int s[MAX_N] = {0};
int n = 0;

// 修改
void modify(int p, int l, int r, int x, int v)
{
    s[p] += v;
    if (l == r) return; // 叶结点则退出
    int mid = (l + r) / 2;
    if (x <= mid) // 判断 x 在左儿子还是右儿子
        modify(p * 2, l, mid, x, v);
    else
        modify(p * 2 + 1, mid + 1, r, x, v);
}

// 封装后的修改，以供调用
void modify(int x, int v) {
    modify(1, 1, n, x, v);
}

/*
也可以 push_up，把儿子结点的信息更新到父亲节点

void up(int p)
{
    s[p] = s[p * 2] + s[p * 2 + 1];
}

void modify(int p, int l, int r, int x, int v)
{
    if (l == r)
    {
        s[p] += v;
        return;
    }
    int mid = (l + r) / 2;
    if (x <= mid)
        modify(p * 2, l, mid, x, v);
    else
        modify(p * 2 + 1, mid + 1, r, x, v);
    up(p);
}
 */

// 查询
int query(int p, int l, int r, int x, int y)
{
    if (x <= l && r <= y) return s[p]; // 若该结点被查询区间包含
    int mid = (l + r) / 2, res = 0;
    if (x <= mid) res += query(p * 2, l, mid, x, y);
    if (y > mid) res += query(p * 2 + 1, mid + 1, r, x, y);
    return res;
}

// 封装后的查询，以供调用
int query(int x, int y) {
    return query(1, 1, n, x, y);
}

int main() {
    scanf("%d", &n);
    for (int i = 1; i <= n; i++) {
        int v;
        scanf("%d", &v);
        modify(i, v);
    }
    while (true) {
        char* cmd = (char*)malloc(sizeof(char) * MAX_CMD_LENGTH);
        int x, y;
        scanf("\n%s", cmd);
        if (strcmp(cmd, "End") == 0) {
            break;
        } else if (strcmp(cmd, "Query") == 0) {
            scanf("%d%d", &x, &y);
            printf("%d\n", query(x, y));
        } else if (strcmp(cmd, "Add") == 0) {
            scanf("%d%d", &x, &y);
            modify(x, y);
        } else if (strcmp(cmd, "Sub") == 0) {
            scanf("%d%d", &x, &y);
            modify(x, -y);
        }
    }
    return 0;
}

/*
没有必要单独把 init 函数独立出来，可以直接用 modify 完成 init
int init(int p, int l, int r)
{
    if (l == r) {
    	cin>>s[p];
    	return s[p];
    }
    int mid = (l + r) / 2;
    s[p] = init(p * 2, l, mid) + init(p * 2 + 1, mid + 1, r);
    return s[p];
}
 */