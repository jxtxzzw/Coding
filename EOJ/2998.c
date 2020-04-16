#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define N 10000000
 
int isPrime ( int num ) {
    for ( int i = 2; i < sqrt ( num ); i++ ) {
        if ( num % i == 0 ) return 0;
    }
 
    return 1;
}
 
int main() {
    int cas;
    scanf ( "%d", &cas );
 
    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        int n;
        scanf ( "%d", &n );
 
        for ( int i = n - 1; i >= 2; i = n / ( n / i + 1 ) ) {
            if ( isPrime ( i ) && isPrime ( n / i ) && n % i == 0 ) {
                printf ( "%d\n", i );
                break;
            }
        }
    }
 
    return 0;
}