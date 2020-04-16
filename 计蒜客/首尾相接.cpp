#include <bits/stdc++.h>

#define MAX_LEN 50007

// 以字符串 t 求 next 数组，n 为字符串长度
int* getNext(const char* t, int length) {
    int* next = (int*)malloc(sizeof(int) * (length + 1));
    next[1] = 0;
    for(int i = 2; i <= length; i++) {
        int j = next[i - 1];
        while(t[j + 1] != t[i] && j > 0) {
            j = next[j];
        }
        if(t[j + 1] == t[i]) {
            next[i] = j + 1;
        } else {
            next[i] = 0;
        }
    }
    return next;
}

// 拼接两个字符串
char* concat(const char* s1, const char* s2) {
    int len1 = strlen(s1);
    int len2 = strlen(s2);
    char* s3 = (char*)malloc(sizeof(char) * (len1 + len2 + 2));
    strncpy(s3 + 1, s1, len1);
    strncpy(s3 + 1 + len1, s2, len2);
    s3[1 + len1 + len2] = '\0';
    return s3;
}


void print(const char * t, const int* next, int n) {
    for (int i = 1; i <= n; i++) {
        printf("%c%c", t[i], i == n ? '\n' : '\t');
    }
    for (int i = 1; i <= n; i++) {
        printf("%d%c", next[i], i == n ? '\n' : '\t');
    }
}

int main() {
    char s1[MAX_LEN], s2[MAX_LEN];
    scanf("%s%s", s1, s2);
    char* s3 = concat(s1, s2);
    int len3 = strlen(s3 + 1);
    int* next3 = getNext(s3, len3);
//    print(s3, next3, len3);
    int ans = next3[len3];
    if (ans > 0) {
        for (int i = 0; i < ans; i++) {
            printf("%c", s1[i]);
        }
        printf(" ");
    }
    printf("%d", ans);
}
