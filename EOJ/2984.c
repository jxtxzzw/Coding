#include<stdio.h>

int main() {
    int cas;
    scanf( "%d",&cas );
    for ( int t=0; t<cas; t++ ) {
        printf( "case #%d:\n",t );
        int n,k;
        int isSolved = 0;
        scanf( "%d%d",&n,&k );
        for ( int m=0; m<=k/3 && m<=n; m++ ) {
            for ( int w=0; w<=k/2 && w<=n; w++ ) {
                for ( int c=0; c<=k && c<=n; c++ ) {
                    if ( 3*m+2*w+1*c==k && m+w+c==n ) {
                        printf( "%d %d %d\n",m,w,c );
                        isSolved = 1;
                    }
                }
            }
        }
        if ( !isSolved ) {
            printf( "-1\n" );
        }
    }
    return 0;
}



