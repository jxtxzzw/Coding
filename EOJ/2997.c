#include <stdio.h>
#include <stdlib.h>
#define N 37
 
int main() {
    int cas;
    scanf ( "%d", &cas );
    long long n, i;
    long long a[N] = {0};
    a[0] = 0;
    a[1] = 1;
    a[2] = 1;
 
    for ( i = 3; i <= N; i++ ) {
        a[i] = a[i - 1] + a[i - 2] + a[i - 3];
    }
 
    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        scanf ( "%lld", &n );
        printf ( "%lld\n", a[n] );
    }
 
    return 0;
}