#include <stdio.h>
#include <stdlib.h>

int main() {
    int cas;

    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );

        int a, b, n;
        scanf ( "%d%d%d", &a, &b, &n );

        long long pow = 1;

        for ( int i = 0; i < n; i++ ) {
            pow *= 10;
        }

        long long tmp = 1;

        for ( int i = 0; i < b; i++ ) {
            tmp = ( tmp * a ) % pow;
        }

        pow /= 10;

        while ( tmp < pow ) {
            printf ( "0" );
            pow /= 10;
        }

        if ( tmp ) {
            printf ( "%lld\n", tmp );

        } else {
            printf ( "\n" );
        }

    }


    return 0;
}
