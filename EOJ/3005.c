#include <stdio.h>
 
long long data[1001][1001] = {{0}};
 
void getC() {
    data[0][0] = 1;
    data[1][0] = 1;
    data[1][1] = 1;
 
    for ( int i = 2; i < 1001; i++ ) {
        data[i][0] = 1;
 
        for ( int j = 1; j <= i; j++ ) {
            data[i][j] = ( data[i - 1][j] + data[i - 1][j - 1] );
        }
    }
}
 
int main() {
    int cas;
    scanf ( "%d", &cas );
    getC();
 
    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        int m,n;
        scanf ( "%d%d", &m, &n );
        printf ( "%lld\n", data[m][n] );
    }
 
    return 0;
}