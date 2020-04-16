#include <stdio.h>
#include <stdlib.h>

int comp ( const void*a, const void*b ) {
    return * ( int* ) a - * ( int* ) b;
}

int main() {

    int n, a[1001], last;
    scanf ( "%d", &n );

    for ( int i = 0; i < n; i++ ) {
        scanf ( "%d", &a[i] );
    }

    qsort ( a, n, sizeof ( int ), comp );
    printf ( "%d", a[0] );
    last = a[0];

    for ( int i = 1; i < n; i++ ) {
        if ( a[i] != last ) {
            printf ( " %d", a[i] );
            last = a[i];
        }
    }


    return 0;
}
