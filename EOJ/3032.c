#include <stdio.h>
#include <stdlib.h>
#define N 10000
int data[N + 1] = {0};

void getArray() {
    for ( int i = 1; i <= N; i++ ) {
        data[i] = i;
    }

    int limit = 1;
    int cnt = 0;

    while ( limit < N ) {
        for ( int i = 1; i <= N; i++ ) {
            if ( cnt == limit && data[i] ) {
                data[i] = 0;
                cnt = 0;
            }

            if ( data[i] ) {
                cnt++;
            }

            if ( i == N ) {
                limit++;
                cnt = 0;
            }
        }
    }

    cnt = 1;

    for ( int i = 1; i < N; i++ ) {
        if ( data[i] ) {
            data[i] = cnt;
            cnt++;
        }
    }
}

void solve ( int n ) {
    if ( data[n] ) {
        printf ( "Yes %d\n", data[n] );

    } else {
        printf ( "No\n" );
    }

}


int main() {
    int cas;
    scanf ( "%d", &cas );
    getArray();

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        int n;
        scanf ( "%d", &n );
        solve ( n );
    }


    return 0;
}
