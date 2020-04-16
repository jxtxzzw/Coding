#include <stdio.h>
#include <string.h>

int main() {
	int cas;
	scanf ( "%d\n", &cas );

	for ( int t = 0; t < cas; t++ ) {
		int num;
		scanf ( "%d", &num );
		char bin[1000000] = {0};
		int n = num;

		if ( n ) {
			//将十进制n转换成二进制，存放在bin[]数组
			while ( n ) {
				bin[strlen ( bin )] = '0' + n % 2;
				n /= 2;
			}

		} else {
			//如果输入的num为0，单独处理
			bin[0] = '0';
		}

		printf ( "case #%d:\n", t );

		//输出二进制
		for ( int i = strlen ( bin ) - 1; i >= 0; i-- ) {
			printf ( "%c", bin[i] );
		}

		//输出十六进制
		printf ( " %X\n", num );
	}

	return 0;
}
