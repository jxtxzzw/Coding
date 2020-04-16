// by - UNKNOWN
// submit by - LiuTianyi
#include <cstdio>
#include <cstdlib>
#include <algorithm>

using namespace std;

long long n, m;

long long gcd(long long a, long long b) {// a > b
    if (b == 0)
        return a;
    else
        return gcd(b, a % b);
}


int main() {
    //freopen("in.txt", "r", stdin);
    while (scanf("%lld%lld", &n, &m) == 2) {
        long long MAX = max(n, m);
        long long MIN = min(n, m);
        MAX--;
        MIN--;
        long long g = gcd(MAX, MIN);
        long long LCM = MAX * MIN / g;
        long long max1 = LCM / MAX - 1;
        long long min1 = LCM / MIN - 1;
        printf("%lld\n", LCM + 1 - max1 * min1);
    }
    return 0;
}
