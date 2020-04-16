#include<stdio.h>
#include<math.h>

double square(double N){
return N*N;
}

double circle(double R){
//const double PI = 3.14159265358972323846264338327950288;
//return PI*R*R;
return M_PI*R*R;
}

int main() {
    int cas;
    scanf( "%d",&cas );
    for ( int t=0; t<cas; t++ ) {
        int n,k;
        scanf( "%d%d",&n,&k );
        double ans = 0;
        double N = n;
        for (int i=0;i<k;i++){
			double R = N/2;
			ans = ans + square(N)-circle(R);
			N = (sqrt(2)/2)*N;
        }
        printf( "case #%d:\n%f\n",t,ans );
    }
    return 0;
}



