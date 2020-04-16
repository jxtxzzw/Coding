#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define M 100
#define N 100
 
int comp ( const void * pa, const void *pb ) {
    int ret = 0;
    int suma = 0, sumb = 0;
    int * aa = ( int* ) pa;
    int * bb = ( int* ) pb;
 
    for ( int i = 0; i < M; i++ ) {
        suma += * ( aa + i );
    }
 
    for ( int i = 0; i < M; i++ ) {
        sumb += * ( bb + i );
    }
 
    if ( suma - sumb ) {
        ret = suma - sumb;
 
    } else {
        for ( int i = 0; i < M; i++ ) {
            if ( aa[i] - bb[i] ) {
                ret = aa[i] - bb[i];
                break;
            }
        }
    }
 
    return ret;
}
 
void SortLines ( int ( *p ) [M], int n, int m ) {
    qsort ( p, n, sizeof ( *p ), comp );
}
 
 
int main() {
    int a[N][M];
    int n, m, i, j;
    int t, cas;
    scanf ( "%d", &cas );
 
    for ( t = 0; t < cas; t++ ) {
        memset ( a, 0, sizeof ( a ) );
        scanf ( "%d%d", &n, &m );
 
        for ( i = 0; i < n; i++ )
            for ( j = 0; j < m; j++ )
                scanf ( "%d", &a[i][j] );
 
        SortLines ( a, n, m );
        printf ( "case #%d:\n", t );
 
        for ( i = 0; i < n; i++ )
            for ( j = 0; j < m; j++ )
                printf ( "%d%c", a[i][j], j < m - 1 ? ' ' : '\n' );
    }
 
    return 0;
}