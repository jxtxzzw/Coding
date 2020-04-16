#include <stdio.h>
#include <stdlib.h>
int main() {
    int n, i, j, tmp, a[5001];
    scanf ( "%d", &n );

    for ( i = 0; i < n; i++ ) {
        scanf ( "%d", &a[i] );
    }

    for ( i = 0; i < n - 1; i++ ) {
        for ( j = i + 1; j < n; j++ ) {
            if ( a[i] > a[j] ) {
                printf ( "%d %d\n", a[i], a[j] );
                tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
    }

    for ( i = 0; i < n - 1; i++ ) {
        printf ( "%d ", a[i] );
    }

    printf ( "%d\n", a[n - 1] );

    return 0;
}
