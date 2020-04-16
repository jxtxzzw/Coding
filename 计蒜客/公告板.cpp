#include <bits/stdc++.h>

using namespace std;

const int MAX_N = 200000 * 3 + 7; // 由于还要 2 倍的父节点，所以乘以 3
int s[MAX_N] = {0};
int h = 0, w = 0, n = 0;

// 修改
// 由于本题不是给特定节点 x 进行 s[x] + v，所以不需要找到 x 是当前的左孩子还是右孩子
int modify(int p, int l, int r, int v)
{
    // 这个条件当然可以放在 if (l == r) { if (s[p] + v <= w) {} } 里面去判断，一样能得到正确的结果
    // 但是最后一组测试数据会超时，因此需要提前剪枝，如果已经放不下了，就没必要放了
    // 但是怎么知道已经放不下了，s[p] + v > w 这个条件很可能经常成立，所以一直是 return -1，而返回值大于 0 才能输出，如一直是 -1 就要一直继续往后找
    // 所以 s[p] 需要保存 min(s[p * 2], s[p * 2 + 1])，如果左右孩子最短的都已经放不下了，才是真的 -1
    // 否则，若 s[p] + v < w，就说明他的孩子中有一个可以贴，那就找到它，然后 return 一个大于 0 的值，提前剪枝
    if (s[p] + v > w) {
        return -1;
    }
    if (l == r) {
        // 叶结点则可以判断到底是不是贴的下，即，叶子节点就是递归终止的条件
        if (s[p] + v <= w) {
            // 已经贴了的长度加上海报的长度，如果小于宽度，说明贴的下
            // 由于题目要求必须贴在最上面一行，所以一旦放得下，就直接贴了，不需要再往孩子结点去找全局最优
            s[p] += v;
            // l == r，因此 l 就是当前行号
            return l;
        } else {
            // 任何情况下贴不下就返回 -1
            return -1;
        }
    }

    int mid = (l + r) / 2;
    int temp = modify(p * 2, l, mid, v);
    if (temp > 0) {
        // 如果 temp 大于 0，说明找到了贴的地方，直接返回，否则，就要继续找
    } else {
        temp = modify(p * 2 + 1, mid + 1, r, v);
    }
     s[p] = min(s[p * 2], s[p * 2 + 1]); // 这里必须留下最短的，否则会超时
    return temp;
}

// 封装后的修改，以供调用
/**
 *
 * @param v 海报的宽度
 * @return 如果能贴的下，返回最上面的一行的序号，如果贴不下，返回 -1
 */
int modify(int v) {
    // 注意这里是 h 了，因为最大高度是 h
    return modify(1, 1, h, v);
}

int main() {
    scanf("%d%d%d", &h, &w, &n);
    for (int i = 1; i <= n; i++) {
        int wi;
        scanf("%d", &wi);
        printf("%d\n", modify(wi));
    }
    return 0;
}
