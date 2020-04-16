#include <stdio.h>
#include <stdlib.h>

int main() {
    int a[11][11];
    int i, j, n;
    scanf ( "%d", &n );

    for ( i = 1; i <= n; i++ ) {
        a[i][1] = 1;
        a[i][i] = 1;
    }

    for ( i = 3; i <= n; i++ ) {
        for ( j = 2; j < i; j++ ) {
            a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
        }
    }

    for ( i = 1; i <= n; i++ ) {
        for ( j = 1; j < i; j++ ) {
            printf ( "%d ", a[i][j] );
        }

        printf ( "%d", a[i][i] );
        printf ( "\n" );
    }

    return 0;
}
