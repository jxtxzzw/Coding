 
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#define N 100
#define LEN 80
 
 
int comp ( const void* a, const void* b ) {
    int ret = 0;
    int da = 0, db = 0;
    const char* sa = * ( char** ) a;
    const char* sb = * ( char** ) b;
 
    for ( int i = 0; i < strlen ( sa ); i++ ) {
        if ( isdigit ( sa[i] ) ) {
            da = sa[i];
            break;
        }
    }
 
    for ( int j = 0; j < strlen ( sb ); j++ ) {
        if ( isdigit ( sb[j] ) ) {
            db = sb[j];
            break;
        }
    }
 
    if ( da && db ) {
        if ( da == db ) {
            ret = strcmp ( sa, sb );
 
        } else {
            ret = da - db;
        }
 
    } else {
        if ( da ) {
            ret = 1;
 
        } else if ( db ) {
            ret = -1;
 
        } else {
            ret = strcmp ( sa, sb );
        }
 
    }
 
    return ret;
}
 
void SortStrings ( char *p[], int n ) {
    qsort ( p, n, sizeof ( char* ), comp );
 
}
 
 
 
int main() {
    char s[N][LEN], *a[N];
    int n, i, t, cas;
    scanf ( "%d", &cas );
 
    for ( t = 0; t < cas; t++ ) {
        scanf ( "%d", &n );
        getchar();
 
        for ( i = 0; i < n; i++ ) scanf ( "%s", a[i] = s[i] );
 
        SortStrings ( a, n );
        printf ( "case #%d:\n", t );
 
        for ( i = 0; i < n; i++ ) printf ( "%s%c", a[i], i < n - 1 ? ' ' : '\n' );
    }
 
    return 0;
}