#include <stdio.h>
#include <stdlib.h>

int main() {
    int cas;
    scanf ( "%d\n", &cas );

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        
        //max[]记下每一行的最大值，因为这可能是一个鞍点
        int max[5] = {0};
        //data[][]是5*5的矩阵
        int data[5][5] = {{0}};

        for ( int i = 0; i < 5; i++ ) {
            //因为数据的范围是1-100的整数，所以可以让max一开始为0
            //max[i]表示第i行的最大值，行数用i控制
            max[i] = 0;

            for ( int j = 0; j < 5; j++ ) {
				//两层循环读入数据
                scanf ( "%d", &data[i][j] );

                //读入数据的同时，就记下第i行的最大值
                if ( data[i][j] > max[i] ) {
                    max[i] = data[i][j];
                }
            }
        }
		
		//由于没有鞍点需要输出-1 -1，所以这里用isExist表示是否存在鞍点
        int isExist = 0;

        //i表示行数、j表示列数，两层循环遍历data[][]数组
        for ( int i = 0; i < 5; i++ ) {
            for ( int j = 0; j < 5; j++ ) {
				//遍历到第i行第j列的时候，判断data[i][j]这个元素是不是第i行最大的
                if ( data[i][j] == max[i] ) {
				//如果是最大的，那就说明这可能是鞍点，就要开始比第j列
                    //min可以初始化为第j列的第0行
                    int min = data[0][j];
					
					//k控制行号，遍历第j列，找到第j列的最小值
                    for ( int k = 0; k < 5; k++ ) {
                        if ( data[k][j] < min ) {
                            min = data[k][j];
                        }
                    }
					
					//至此已经找到第j列最小值
					//再次遍历第j列
                    for ( int k = 0; k < 5; k++ ) {
                    	//如果第j列的第k行是最小值，并且k和i相等，说明是鞍点
                    	//输出，并将isExist标记为1
                        if ( data[k][j] == min && k == i ) {
                            printf ( "%d %d\n", i, j );
                            isExist = 1;
                        }
                    }

                }
            }
        }
        
/**
注意这里嵌套循环的顺序
遍历data[][]数组的顺序与输出顺序是一致的
这样的顺序保证了，输出的顺序是题目要求的“如有两个以上鞍点，按照行号由小到大排列；行号相同时，按照列号由小到大排序。”
由此不需要进行多余的操作，直接按行、列、行的顺序遍历即可
**/
        
		//如果没有鞍点，输出-1 -1
        if ( !isExist ) {
            printf ( "-1 -1\n" );
        }
    }

    return 0;
}
