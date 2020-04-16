//当2^k<n<=2^(k+1)时，答案就是(n-2^k)*2

#include <stdio.h>
#include <math.h>

int main() {
    int cas;
    scanf ( "%d\n", &cas );
    for ( int t = 0; t < cas; t++ ) {
		int n;
		scanf("%d",&n);
		int k=0;
		while (1){
			if ((int)pow(2,k)<n && n<=(int)pow(2,k+1)){
				printf("case #%d:\n%d\n",t,(n-(int)pow(2,k))*2);
				break;
			} else {
				k++;
			}
		}
    }
    return 0;
}

===============================================================

#include <stdio.h>

int main() {
    int cas;
    scanf ( "%d\n", &cas );

    for ( int t = 0; t < cas; t++ ) {
        int n;
        scanf ( "%d", &n );
        //pos[i]保存第i张牌是编号为多少的牌
        int pos[201] = {0};

        //初始化，第i个位置上就是编号为i的牌
        for ( int i = 1; i <= n; i++ ) {
            pos[i] = i;
        }

        //当剩下的牌大于1张的时候，要进行操作
        while ( n > 1 ) {

			//扔牌
			//把第1张牌扔掉，就是把后面的牌往前移1位
			//因此第i的位置的牌就是第i+1的位置上的牌
			//for循环从1开始，到等于n结束
            for ( int i = 1; i <= n; i++ ) {
                pos[i] = pos[i + 1];
            }

			//扔牌之后，牌的数就减1
            n--;

		//由于不知道牌是奇数还是偶数，所以不一定每次都要做扔牌、把牌放到最后这两件事
		//有可能做完扔牌就只剩1张了，后面的操作就不用做了
		//因此这里判断了n>1的条件
			//注意，扔牌不用判断n>1的条件
			//是因为能够进while(n>1)，就一定要扔牌
			//把牌放到最后，不会使牌的数量减少
            if ( n > 1 ) {
				//记下第1张牌的编号
                int tmp = pos[1];

				//每张牌往前移1位
                //for循环从1开始，到等于n-1结束
                for ( int i = 1; i <= n - 1; i++ ) {
                    pos[i] = pos[i + 1];
                }
				//最后一张牌的编号是tmp
				//就是原来第1张牌的编号
                pos[n] = tmp;

            //这里不需要n--
            }
        }
		//退出while循环，只剩下1张牌，输出pos[1]
        printf ( "case #%d:\n%d\n", t, pos[1] );
    }

    return 0;
}
