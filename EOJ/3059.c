#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define M_PI 3.14159265358979323846
int comp ( const void *pa, const void *pb ) {
    double ret = 0 ;
    double *a = * ( double ( * ) [2] ) pa;
    double *b = * ( double ( * ) [2] ) pb;

    if ( a[1] != b[1] ) {
        ret = a[1] - b[1];

    } else {
        ret = b[0] - a[0];
    }

    if ( ret < 0 ) {
        return -1;

    } else if ( ret > 0 ) {
        return 1;

    } else {
        return 0;
    }

}

void solve ( double ( *a ) [2], int n ) {
    qsort ( a, n, sizeof ( a[0] ), comp );

}

int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        double a[1001][2] = {{0}};
        int n;
        scanf ( "%d", &n );

        for ( int i = 0; i < n; i++ ) {
            double x, y;
            scanf ( "%lf%lf", &x, &y );
            a[i][0] = sqrt ( x * x + y * y );
            double tmp = atan2 ( y, x );

            if ( tmp < 0 ) {
                tmp += 2 * M_PI;
            }

            a[i][1] = tmp ;
        }

        solve ( a, n );

        for ( int i = 0; i < n; i++ ) {
            printf ( "(%.4f,%.4f)\n", a[i][0], a[i][1] );
        }
    }


    return 0;
}
