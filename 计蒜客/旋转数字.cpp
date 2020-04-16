#include <bits/stdc++.h>

//#define DEBUG_USE_ONLY
#define MAX_LEN 100007

void print(const char *t, const int *next, int n) {
    for (int i = 1; i <= n; i++) {
        printf("%c%c", t[i], i == n ? '\n' : '\t');
    }
    for (int i = 1; i <= n; i++) {
        printf("%d%c", next[i], i == n ? '\n' : '\t');
    }
}

// 求出字符串 t 的循环节长度
int getLoop(const char *t, int length) {
    int *next = (int *) malloc(sizeof(int) * (length + 1));
    next[1] = 0;
    for (int i = 2; i <= length; i++) {
        int j = next[i - 1];
        while (t[j + 1] != t[i] && j > 0) {
            j = next[j];
        }
        if (t[j + 1] == t[i]) {
            next[i] = j + 1;
        } else {
            next[i] = 0;
        }
    }
    int loop = length - next[length];
#ifdef DEBUG_USE_ONLY
    print(t, next, length);
    printf("[%d]\n", loop);
#endif
    if (next[length] % loop != 0) {
        return length;
    } else {
        return loop;
    }
}

char *duplicate(const char *s, int len, int loop) {
    char *ss = (char *) malloc(sizeof(char) * (len + loop + 2));
    strncpy(ss + 1, s + 1, len);
    strncpy(ss + 1 + len, s + 1, loop);
    ss[1 + len + loop] = '\0';
    return ss;
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
    char num[MAX_LEN];
    scanf("%s", num + 1);
    int len1 = strlen(num + 1);
    int loop = getLoop(num, len1);
    char *numnum = duplicate(num, len1, loop);
    int len2 = strlen(numnum + 1);
    int *next = getNext(numnum, len2);
#ifdef DEBUG_USE_ONLY
    print(numnum, next, len2);
#endif
    int greater = 0;
    int equal = 0;
    int less = 0;
    for (int i = 1; i <= loop; i++) {
#ifdef DEBUG_USE_ONLY
        for (int j = i; j < i + len1; j++) {
            printf("%c", numnum[j]);
        }
        printf(", next = %d, %c - %c\n", next[i], numnum[i + next[i]], numnum[1 + next[i]]);
#endif
        if (next[i] == len2) {
            equal++;
        } else if (numnum[i + next[i]] < numnum[1 + next[i]]) {
            less++;
        } else {
            greater++;
        }
    }
    printf("%d %d %d", less, equal, greater);
    return 0;
}
