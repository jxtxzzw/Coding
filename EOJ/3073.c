#include <stdio.h>
#include <stdlib.h>
int comp ( const void *pa, const void * pb ) {
    int ret = 0;
    int *a = * ( int ( * ) [3] ) pa ;

    int *b = * ( int ( * ) [3] ) pb ;

    if ( a[2] != b[2] ) {
        ret = b[2] - a[2];

    } else if ( a[0] != b[0] ) {
        ret = a[0] - b[0];

    } else {
        ret = a[1] - b[1];
    }

    return ret;
}

void solve ( int ( *a ) [3], int m ) {
    qsort ( a, m, sizeof ( a[0] ), comp );

}

int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        int n, m;
        scanf ( "%d%d", &n, &m );
        int a[40000][3] = {{0, 0, 0}};

        for ( int i = 0; i < m; i++ ) {
            scanf ( "%d%d%d", &a[i][0], &a[i][1], &a[i][2] );

        }

        printf ( "case #%d:\n", t );
        solve ( a, m );

        for ( int i = 0; i < m; i++ ) {
            printf ( "(%d,%d,%d)\n", a[i][0], a[i][1], a[i][2] );
        }
    }


    return 0;
}
