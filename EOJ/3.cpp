#include <cstdio>
using namespace std;
 
const int MAX_N = 100000 + 3, MAX_LEN = 10 + 3;
int n, m, dir[MAX_N];
char str[MAX_N][MAX_LEN];
 
int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++i)
        scanf("%d%s", dir + i, str[i]);
    int pos = 0;
    for (int i = 0, d, a; i < m; ++i) {
        scanf("%d%d", &d, &a);
        int dd = d ^ dir[pos];
        if (dd == 1) (pos += a) %= n;
        else (pos -= a) %= n;
        pos = (pos % n + n) % n;
    }
    printf("%s\n", str[pos]);
    return 0;
}
