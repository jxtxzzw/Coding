#include <stdio.h>

int process ( int a[], int n ) {
    int isChanged = 0;

    for ( int i = 0; i < n; i++ ) {
        int cnt = 0;

        for ( int j = i + 1; j < n; j++ ) {
            if ( a[j] < a[i] ) {
                cnt++;
            }
        }

        if ( cnt != a[i] ) {
            a[i] = cnt;
            isChanged = 1;
        }
    }

    return isChanged;
}

int main() {
    int cas;
    scanf ( "%d\n", &cas );

    for ( int t = 0; t < cas; t++ ) {
        int a[31] = {0};
        int n;
        scanf ( "%d", &n );

        for ( int i = 0; i < n; i++ ) {
            scanf ( "%d", &a[i] );
        }

        while ( process ( a, n ) );

        printf ( "case #%d:\n", t );

        for ( int i = 0; i < n; i++ ) {
            printf ( "%d%c", a[i], i < n - 1 ? ' ' : '\n' );
        }
    }

    return 0;
}
