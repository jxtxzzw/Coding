#include <stdio.h>
#include <stdlib.h>

int main()
{

		int ans = 0;
		int n;
		scanf("%d",&n);
		int x;
		for (int i=0;i<n-1;i++){
		scanf("%*d%*d%d",&x);
		ans+=x;
		}
		printf("%d\n",ans*2);



    return 0;
}
