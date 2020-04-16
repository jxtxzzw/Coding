#include <bits/stdc++.h>


int main() {

    int n;
    scanf("%d", &n);

    int x = n - 2000;

    int cnt = 0;
    cnt += x / 4;
    cnt -= x / 100;
    cnt += x / 400;

    printf("%d %d %d", cnt, x * 7 * 20, cnt * 7 * 100);


    return 0;
}