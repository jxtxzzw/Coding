#include <bits/stdc++.h>

const int MAX_NODE = 100007;
int size = 0; // 其实是结点的编号、数组的下标，index
int root = 0; // 0 表示空，0 的 l r 自然也是 0 所以也是空

struct treap {
    int v; // 关键字
    int w; // 同关键字的元素个数
    int rnd; // 随机生成的优先级
    int size; // 当前子树内的结点总数
    int l; // 左孩子下标
    int r; // 右孩子下标
} tr[MAX_NODE];

void resize(int &k) {
    tr[k].size = tr[tr[k].l].size + tr[k].w + tr[tr[k].r].size;
}

void _rturn(int &k) {
    int t = tr[k].l;
    tr[k].l = tr[t].r;
    tr[t].r = k;
    k = t;
    resize(tr[k].r); // 自底向上重新计算大小
    resize(k);
}

void _lturn(int &k) {
    int t = tr[k].r;
    tr[k].r = tr[t].l;
    tr[t].l = k;
    k = t;
    resize(tr[k].l); // 自底向上重新计算大小
    resize(k);
}

void _insert(int &k, int x) {
    if (k == 0) {
        size++;
        k = size;
        tr[k].size = tr[k].w = 1;
        tr[k].v = x;
        tr[k].rnd = rand();
        return;
    }
    tr[k].size++;
    if (tr[k].v == x) {
        tr[k].w++; // 每个结点顺便记录下与该节点相同值的数的个数
    } else if (x > tr[k].v) {
        _insert(tr[k].r, x);
        if (tr[tr[k].r].rnd < tr[k].rnd) {
            _lturn(k); // 维护堆性质
        }
    } else {
        _insert(tr[k].l, x);
        if (tr[tr[k].l].rnd < tr[k].rnd) {
            _rturn(k); // 维护堆性质
        }
    }
}

void insert(int x) {
    _insert(root, x);
}

void _del(int &k, int x) {
    if (k == 0) {
        return;
    }
    if (tr[k].v == x) {
        if (tr[k].w > 1) { // 若关键字 x 有多个，只删去一个
            tr[k].w--;
            tr[k].size--;
            return;
        }
        if (tr[k].l * tr[k].r == 0) { // 有一个儿子为空
            k = tr[k].l + tr[k].r;
        } else if (tr[tr[k].l].rnd < tr[tr[k].r].rnd) {
            _rturn(k);
            _del(k, x);
        } else {
            _lturn(k);
            _del(k, x);
        }
    } else if (x > tr[k].v) {
        tr[k].size--;
        _del(tr[k].r, x);
    } else {
        tr[k].size--;
        _del(tr[k].l, x);
    }
}

void del(int x) {
    _del(root, x);
}

int _query(int &k, int x) {
    if (tr[k].v == x) {
        return tr[tr[k].l].size + 1;
    } else if (x > tr[k].v) {
        return _query(tr[k].r, x) + tr[tr[k].l].size + tr[k].w;
    } else {
        return _query(tr[k].l, x);
    }
}

int query(int x) {
    return _query(root, x);
}

int _kth(int &k, int x) {
    if (x <= tr[tr[k].l].size) {
        return _kth(tr[k].l, x);
    }
    int size = tr[tr[k].l].size + tr[k].w;
    if (x <= size) {
        return tr[k].v;
    } else {
        return _kth(tr[k].r, x - size);
    }
}

int kth(int x) {
    return _kth(root, x);
}

int _prev(int &k, int x) {
    int tmp = k;
    int snapshot = 0;
    while (tmp != 0) {
        if (tr[tmp].v < x) {
            snapshot = tr[tmp].v;
            tmp = tr[tmp].r;
        } else {
            tmp = tr[tmp].l;
        }
    }
    return snapshot;
}

int prev(int x) {
    return _prev(root, x);
}

int _succ(int &k, int x) {
    int tmp = k;
    int snapshot = 0;
    while (tmp != 0) {
        if (tr[tmp].v <= x) { // 前驱后继如果严格大于小于，要加等号
            tmp = tr[tmp].r;
        } else {
            snapshot = tr[tmp].v;
            tmp = tr[tmp].l;
        }
    }
    return snapshot;
}

int succ(int x) {
    return _succ(root, x);
}

void _print(int &k) {
    if (k == 0) {
        return;
    }
    printf("[%d: {L: ", tr[k].size);
    _print(tr[k].l);
    printf(", %d (%d), R: ", tr[k].v, tr[k].w);
    _print(tr[k].r);
    printf("}]");
}

void print() {

    _print(root);
    printf("\n");
}

int main() {
//    freopen("in.txt", "r", stdin);
//    freopen("out.txt", "w", stdout);
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        int op, num;
        scanf("%d %d", &op, &num);
        int ans;
        switch(op) {
            case 1:
                insert(num);
                break;
            case 2:
                del(num);
                break;
            case 3:
                ans = query(num);
                printf("%d\n", ans);
                break;
            case 4:
                ans = kth(num);
                printf("%d\n", ans);
                break;
            case 5:
                ans = prev(num);
                printf("%d\n", ans);
                break;
            case 6:
                ans = succ(num);
                printf("%d\n", ans);
                break;
        }
//        print();
    }
    return 0;
}
