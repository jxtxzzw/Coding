#include <stdio.h>
#include <stdlib.h>

typedef struct {
    long long num;
    int score;
} student;


int comp ( const void *a, const void *b ) {
    int ret = 0;
    ret =  ( * ( student * ) b ).score - ( * ( student * ) a ).score;

    if ( ret == 0 ) {
        if ( ( * ( student * ) a ).num > ( * ( student * ) b ).num ) {
            ret = 1;

        } else {
            ret = -1;
        }
    }

    return ret;
}


int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        int n, m, g;
        student students[501];
        int scores[11];
        scanf ( "%d%d%d", &n , &m, &g );

        for ( int i = 0; i < m; i++ ) {
            scanf ( "%d", &scores[i] );
        }

        long long num;
        int len = 0;

        for ( int i = 0; i < n; i++ ) {
            scanf ( "%lld", &num );
            int score = 0;
            int s;
            scanf ( "%d", &s );

            for ( int j = 0; j < s; j++ ) {
                int t;
                scanf ( "%d", &t );
                score += scores[t - 1];
            }

            if ( score >= g ) {
                students[len].num = num;
                students[len].score = score;
                len++;
            }
        }

        qsort ( students, len, sizeof ( student ), comp );
        printf ( "case #%d:\n%d\n", t , len );

        for ( int i = 0; i < len; i++ ) {
            printf ( "%011lld %d\n", students[i].num, students[i].score );
        }
    }

    return 0;
}
