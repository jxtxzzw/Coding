#include<stdio.h>

int main() {
    int cas;
    scanf( "%d",&cas );
    for ( int t=0; t<cas; t++ ) {
        int n,m;
        scanf( "%d%d",&n,&m );
        int a=-1,b=-1;
        if ( ( m-2*n ) % 2 ==0 ) {
            b=( m-2*n ) / 2;
            a=n-b;
        }
        printf( "case #%d:\n",t );
        if ( a>=0 && b>=0 ) {
            printf( "%d %d\n",a,b );
        } else {
            printf( "Impossible\n" );
        }
    }
    return 0;
}



