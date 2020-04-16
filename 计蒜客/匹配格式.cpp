#include <bits/stdc++.h>

#define MAX_LEN 1000007

using namespace std;

void print(const char *t, const int *next, int n) {
    for (int i = 1; i <= n; i++) {
        printf("%c%c", t[i], i == n ? '\n' : '\t');
    }
    for (int i = 1; i <= n; i++) {
        printf("%d%c", next[i], i == n ? '\n' : '\t');
    }
}

int *getNext(const char *t, int n) {
    int *next = (int *) malloc(sizeof(int) * (n + 1));
    next[1] = n;
    int p = 1;
    while (p < n && t[p] == t[p + 1]) p++;
    next[2] = p - 1;
    int k = 2, l;
    for (int i = 3; i <= n; i++) {
        p = k + next[k] - 1;
        l = next[i - k + 1];
        if (i + l <= p) next[i] = l;
        else {
            int j = p - i + 1;
            if (j < 0) j = 0;
            while (i + j <= n && t[i + j] == t[j + 1]) j++;
            next[i] = j;
            k = i;
        }
    }
    return next;
}

int main() {
    char s[MAX_LEN] = {0};
    scanf("%s", s + 1);
    int length = strlen(s + 1);
    int *next = getNext(s, length);
    for (int len = length / 3; len >= 1; len--) {
        if (next[length - len + 1] < len) {
            continue;
        }
        for (int t = len + 1; t <= length - 2 * len; t++) {
            if (next[t] < len) {
                continue; // 长度不满足要求，继续
            }
            printf("%d\n", len);
            return 0;
        }
    }
    return -1;
}
