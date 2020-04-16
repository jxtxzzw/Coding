#include <stdio.h>

#define MOD 10007

long long pow ( int a, int b ) {
    long long ret = 1;

    while ( b ) {
        ret = ( ret * a ) % MOD;
        b--;
    }

    return ret;
}

long long data[1001][1001] = {{0}};

void getC() {
    data[0][0] = 1;
    data[1][0] = 1;
    data[1][1] = 1;

    for ( int i = 2; i < 1001; i++ ) {
        data[i][0] = 1;

        for ( int j = 1; j <= i; j++ ) {
            data[i][j] = ( data[i - 1][j] % MOD + data[i - 1][j - 1] % MOD ) % MOD;
        }
    }
}

int main() {
    int cas;
    scanf ( "%d", &cas );
    getC();

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        int a, b, k, n, m;
        scanf ( "%d%d%d%d%d", &a, &b, &k, &n, &m );
        long long ans = ( ( ( data [ k ][ n ] * pow ( a, n ) ) % MOD ) * pow ( b, m ) ) % MOD;
        printf ( "%lld\n", ans );
    }

    return 0;
}
