#include<stdio.h>

int main() {
    int cas;
    scanf( "%d",&cas );
    for ( int t=0; t<cas; t++ ) {
        int n;
        scanf( "%d",&n );
        int cnt=0;
        for ( int begin=1; begin<n; begin++ ) {
            for ( int length=2; begin+length<=n; length++ ) {
                int tmp = ( begin+( begin+length-1 ) )*length;
                if ( tmp==2*n ) {
                    cnt++;
                } else if( tmp>2*n ) {
                    break;
                }
            }
        }
        printf( "case #%d:\n%d\n",t,cnt );
    }
    return 0;
}



