#include <stdio.h>
#include <stdlib.h>
 
int ascending ( const void * a, const void * b ) {
    return * ( int * ) a - * ( int* ) b;
}
 
int descending ( const void * a, const void * b ) {
    return * ( int * ) b - * ( int* ) a;
}
 
int main () {
    int cas;
    scanf ( "%d", &cas );
    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        int num;
        scanf ( "%d", &num );
        int v1[1001] = {0}, v2[1001] = {0};
        for ( int i = 0; i < num; i++ ) {
            scanf ( "%d", &v1[i] );
        }
        for ( int i = 0; i < num; i++ ) {
            scanf ( "%d", &v2[i] );
        }
        qsort ( v1, num, sizeof ( int ), ascending );
        qsort ( v2, num, sizeof ( int ), descending );
        int sum = 0;
        for ( int i = 0; i < num; i++ ) {
            sum += v1[i] * v2[i];
        }
        printf ( "%d\n", sum );
    }
    return 0;
}