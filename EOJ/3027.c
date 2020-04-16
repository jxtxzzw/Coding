#include <stdio.h>

int gcd ( int a, int b ) {
    while ( b ) {
        int t = a;
        a = b;
        b = t % b;
    }

    return a;
}

int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        int m, n;
        scanf ( "%d%d", &m, &n );
        int ans;

        for ( int i = 1, cnt = 0; cnt < n; i++ ) {
            if ( gcd ( i, m ) == 1 ) {
                cnt++;
                ans = i;
            }
        }

        printf ( "%d\n", ans );
    }

    return 0;
}
