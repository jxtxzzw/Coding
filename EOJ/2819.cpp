#include <bits/stdc++.h>


int main() {

    int n;
    scanf("%d", &n);

    while (n-- != 0) {
        int x;
        scanf("%d", &x);
        int cnt = x;

        while (x / 3 != 0) {
            cnt += x / 3;
            x = x / 3 + x % 3;

        }
        printf("%d\n", cnt);

    }


    return 0;
}