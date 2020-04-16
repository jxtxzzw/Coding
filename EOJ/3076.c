#include <stdio.h>
 
long long data[121][2]={{0,0}};
 
void getData(int num){
	if (num==0){
		data[num][1]=0;
	} else if (num==1 || num==2){
		data[num][1]=1;
	} else {
 
	if (data[num-1][0]==0 && data[num-1][1]==0) getData(num-1);
	if (data[num-2][0]==0 && data[num-2][1]==0) getData(num-2);
 
	data[num][0] = (data[num][1]+data [num-1][1]+data[num-2][1])/(10000000000)+(data[num][0]+data [num-1][0]+data[num-2][0]);
	data[num][1] = (data[num][1]+data [num-1][1]+data[num-2][1])%(10000000000);
	}
}
 
void solve(int num) {
 
    if (data[num][0]){
		printf("%lld%",data[num][0]);
		printf("%010lld\n",data[num][1]);
    }else if (data[num][1]){
		printf("%lld\n",data[num][1]);
    } else {
		getData(num);
		solve(num);
    }
}
 
 
int main() {
 
    int cas;
    scanf ( "%d", &cas );
 
    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
            int num;
		scanf ( "%d", &num );
		if (num){
        solve(num);
        } else {
        printf("0\n");
        }
    }
    return 0;
}