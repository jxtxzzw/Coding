#include <stdio.h>
#include <stdlib.h>

int main() {
    int n, a[20], i, sum = 0;
    scanf ( "%d", &n );

    for ( i = 0; i < n; i++ ) {
        scanf ( "%d", &a[i] );
        sum += a[i];
    }

    for ( i = n - 1; i > 0; i-- ) {
        printf ( "%d ", a[i] );
    }

    printf ( "%d\n", a[0] );
    printf ( "%d %0.2f\n", sum, ( ( double ) sum / ( double ) n ) );
    return 0;
}
