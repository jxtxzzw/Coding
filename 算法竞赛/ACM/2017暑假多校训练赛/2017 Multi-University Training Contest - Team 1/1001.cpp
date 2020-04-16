#include <bits/stdc++.h>
using namespace std;
int main()
{
    int i=1;
    int num;
    while(scanf("%d",&num)!=EOF)
    {
        int result;double temp;
        temp=num*log10(2);
        result=(int)temp;
        cout<<"Case #"<<i<<": "<<result<<endl;
        i++;
    }
    return 0;
}