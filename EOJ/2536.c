#include <stdio.h>
#include <stdlib.h>
long long gcd ( long long x, long long y ) {
    if ( y == 0 ) return x;

    else return gcd ( y, x % y );
}
int main() {
    int i, j, n, num;
    long long f[16], fenzi, fenmu;
    f[0] = 0;
    f[1] = 1;

    for ( i = 2; i < 16; i++ ) {
        f[i] = f[i - 1] + f[i - 2];
    }

    scanf ( "%d", &num );

    for ( i = 0; i < num; i++ ) {
        scanf ( "%d", &n );
        fenzi = 0;
        fenmu = 1;

        for ( j = 1; j <= n; j++ ) {
            fenzi = fenzi * f[j + 1] + fenmu * f[j + 2];
            fenmu = fenmu * f[j + 1];
        }

        printf ( "%lld/%lld\n", fenzi / gcd ( fenzi, fenmu ), fenmu / gcd ( fenzi, fenmu ) );
    }

    return 0;
}
