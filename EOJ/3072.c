#include <stdio.h>
#include <stdlib.h>


int solve ( int a[], int n ) {
    int min = 0, max = 0, ret = 0;

    for ( int i = 0; i < n; i++ ) {
        if ( a[i] <= a[min] ) {
            min = i;

            for ( int j = min; j < n; j++ ) {
                if ( a[j] - a[min] >= max ) {
                    max = a[j] - a[min];
                }
            }

            if ( max >= ret ) {
                ret = max;
            }
        }
    }

    return ret;
}


int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        int n;
        int a[500];
        scanf ( "%d", &n );

        for ( int i = 0; i < n; i++ ) {
            scanf ( "%d", &a[i] );
        }

        printf ( "case #%d:\n%d\n", t, solve ( a, n ) );
    }

    return 0;
}
