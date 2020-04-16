#include <stdio.h>
#include <stdlib.h>

int main() {
    int t, n, r, i, k, a[32], sig;
    scanf ( "%d", &t );

    for ( k = 0; k < t; k++ ) {
        scanf ( "%d%d", &n, &r );

        if ( n < 0 ) {
            sig = -1;
            n = -n;

        } else sig = 1;

        i = 0;

        while ( n > 0 ) {
            a[i] = n % r;
            n = n / r;
            i++;
        }

        n = i;

        if ( sig == -1 ) printf ( "-" );

        for ( i = n - 1; i >= 0; i-- ) {
            if ( a[i] >= 10 ) printf ( "%c", 55 + a[i] );

            else printf ( "%d", a[i] );
        }

        printf ( "\n" );
    }

    return 0;
}
