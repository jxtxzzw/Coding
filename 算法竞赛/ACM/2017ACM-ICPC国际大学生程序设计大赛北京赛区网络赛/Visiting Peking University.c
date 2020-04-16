// by - LiuTianyi
#include <bits/stdc++.h>

using namespace std;
int n, m, k, head, tail;
int day[105];

int intervalmin(int st, int ed) {
    int ret = day[st];
    for (int i = st + 1; i <= ed; i++) {
        ret = min(ret, day[i]);
    }
    return ret;
}

int main() {
    //freopen("in.txt", "r", stdin);
    while (scanf("%d%d", &n, &m) == 2) {
        memset(day, 0, sizeof(day));
        head = tail = 0;
        for (int i = 0; i < n; i++)
            scanf("%d", &day[i]);
        scanf("%d", &k);
        for (int i = 0; i < k; i++) {
            int tmp;
            scanf("%d", &tmp);
            day[tmp] = 9999;
        }
        while (head < n && day[head] == 9999)
            head++;
        tail = head;
        int cnt = 0;
        while (tail < n && cnt != m) {
            if (day[tail] != 9999)
                cnt++;
            if (cnt == m)
                break;
            tail++;
        }
        int ans = 9999, anshead, anstail;
        while (tail < n) {
            int min = intervalmin(head + 1, tail);
            int tmp = day[head] + min;
            if (tmp < ans) {
                anshead = head;
                for (int i = head + 1; i <= tail; i++)
                    if (day[i] == min) {
                        anstail = i;
                        break;
                    }
                ans = tmp;
            }
            head++;
            while (head < n && day[head] == 9999)
                head++;
            tail++;
            while (tail < n && day[tail] == 9999)
                tail++;
        }
        printf("%d %d\n", anshead, anstail);
    }
    return 0;
}