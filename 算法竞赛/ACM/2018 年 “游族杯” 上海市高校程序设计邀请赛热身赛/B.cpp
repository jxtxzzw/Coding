#include <bits/stdc++.h>
#ifndef ONLINE_JUDGE
    #define DEBUG_USE_ONLY
#endif // ONLINE_JUDGE
#define MAX_N 10000007

using namespace std;

int a[MAX_N]={0};

int main(){

int n;
scanf("%d",&n);
for (int i=0;i<n;++i) scanf("%d",&a[i]);
sort(a,a+n);

for (int i=0;i<n;++i) printf("%d ",a[i]);


return 0;

}
