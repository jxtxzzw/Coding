#include <bits/stdc++.h>

const int MAXC = 26;
const int MAXN = 100007;

using namespace std;

int child[MAXN][MAXC], fail[MAXN], sta[MAXN], Q[MAXN];
int tot;
int len[MAXN];

/**
 * AC 自动机
 */
struct AC_Automaton {

    /**
     * 清空
     */
    void clear() {
        memset(child, 255, sizeof(child));
        memset(fail, 0, sizeof(fail));
        tot = 0;
        memset(sta, 0, sizeof(sta));
    }

    /**
     * 插入单词
     * @param ch 单词，该单词下标从 1 开始
     */
    void insert(char *ch) {
        int p = 0, l = strlen(ch + 1);
        for (int i = 1; i <= l; i++) {
            if (child[p][ch[i] - 'a'] == -1) child[p][ch[i] - 'a'] = ++tot;
            p = child[p][ch[i] - 'a'];
        }
        sta[p] = 1; // 以结点 p 的字符串是否存在，由于本题只需要是否存在，设置 true 就好了，像上一题还需要统计个数，可以改为 sta[p]++，表示有多少个
        len[p] = l; // 结点 p 的字符串的长度
    }

    /**
     * 对插入了单词的前缀树构造失败指针
     */
    void build() {
        int l = 0, r = 0;
        for (int i = 0; i < MAXC; i++)
            if (child[0][i] == -1)
                child[0][i] = 0;
            else
                Q[++r] = child[0][i];
        while (l < r) {
            int p = Q[++l];
            for (int i = 0; i < MAXC; i++)
                if (child[p][i] == -1)
                    child[p][i] = child[fail[p]][i];
                else {
                    fail[child[p][i]] = child[fail[p]][i];
                    Q[++r] = child[p][i];
                }
        }
    }

    /**
     * 给定一个字符串，删除所有存在在字典中的单词，并返回
     * @param ch 给定的字符串，该字符串下标从 1 开始
     * @return 操作完成后的字符串
     */
    char *solve(char *ch) {
        // ans 为字符串，cursor 为当前光标的位置
        char *ans = (char *) malloc(sizeof(char) * MAXN);
        int cursor = 0;
        // stack 为模拟的栈，其每一个元素记录一个前缀树结点的编号，top 是栈顶指针
        int *stack = (int *) malloc(sizeof(int) * MAXN);
        int top = 0;
        // 初始从前缀树根节点开始
        int p = 0;
        // 注意字符串下标从 1 开始
        int l = strlen(ch + 1);
        for (int i = 1; i <= l; i++) {
            ans[cursor] = ch[i]; // 输入当前字符
            cursor++; // 光标移动一位
            p = child[p][ch[i] - 'a']; // 前缀树节点往后移动一个，到该字符
            stack[top] = p; // 保存进栈，以便后面处理
            top++; // 栈顶指针
            if (sta[p]) { // sta[p] 表示以结点 p 为路径的字符串是不是存在，如果存在，那么按照题目意思就要删除它，模拟 Backspace 的过程
                int length = len[p]; // 需要删除的单词的长度
                cursor -= length; // 回退那么多个长度
                top -= length; // 回退那么多个长度
                p = stack[top - 1]; // 取出当前的栈顶元素
            }
        }
        ans[cursor] = '\0';
        return ans;
    }
} T;

int main() {
//    freopen("in.txt", "r", stdin);
    // 构造 AC 自动机
    auto ac = new AC_Automaton();
    ac->clear();
    // 给定的文章，下标从 1 开始
    char *t = (char *) malloc(sizeof(char) * MAXN);
    scanf("%s", t + 1);
    int n;
    scanf("%d", &n);
    // 读入单词，加入前缀树
    char *s = (char *) malloc(sizeof(char) * MAXN);
    for (int i = 0; i < n; i++) {
        // 字符串下标从 1 开始
        scanf("%s", s + 1);
        ac->insert(s);
    }
    // 根据前缀树中的单词构造失败指针，即构造字典
    ac->build();
    // 执行操作，输出结果
    printf("%s\n", ac->solve(t));
    return 0;
}