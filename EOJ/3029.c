#include <stdio.h>
#include <stdlib.h>
#include <math.h>
 
void toBinaryString ( int a[], int n, int m ) {
    for ( int i = 1; i <= m; i++ ) {
        a[i] = n % 2;
        n /= 2;
    }
}
 
int solve ( int n, int m ) {
    int ret = 0;
    int p = ( int ) ( pow ( 2, m ) );
 
    for ( int i = 1; i < p; i++ ) {
        int a[21]={0};
        toBinaryString ( a, i, m );
        int sum = 0;
 
        for ( int j = 1; j <= m; j++ ) {
            sum += a[j] * j;
        }
 
        if ( sum == n ) {
            ret++;
        }
    }
 
    return ret;
}
 
int main() {
    int cas;
    scanf ( "%d", &cas );
 
    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        int m, n;
        scanf ( "%d%d", &n, &m );
        printf ( "%d\n", solve ( n, m ) );
    }
 
    return 0;
}