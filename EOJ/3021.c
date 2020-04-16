#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
int comp ( const void * a, const void * b ) {
    char ch1 = * ( char* ) a;
    char ch2 = * ( char* ) b;
    return ch1 - ch2;
}
 
char* CharsSort ( char *p ) {
    char letter[201] = {0};
    int pos[201] = {0};
    int cnt = 0;
 
    for ( int i = 0; p[i]; i++ ) {
        if ( p[i] >= 'A' && p[i] <= 'Z' ) {
            letter[cnt] = p[i];
            pos[cnt] = i;
            cnt++;
        }
    }
 
    qsort ( letter, cnt, sizeof ( char ), comp );
 
    for ( int i = 0; i < cnt; i++ ) {
        p[pos[i]] = letter[i];
    }
 
    return p;
}
 
 
void solve() {
    char s[201];
    gets ( s );
    printf ( "%s\n", CharsSort ( s ) );
}
 
int main() {
    int i, t;
    scanf ( "%d\n", &t );
 
    for ( i = 0; i < t; i++ ) {
        printf ( "case #%d:\n", i );
        solve();
    }
 
    return 0;
}