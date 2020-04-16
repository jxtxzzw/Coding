/**
 * 这道题目有点类似于最短路径
 * 开二维数组
 * 然后a->b的话就[a][b]=1
 * b->c的话就[b][c]=1
 * 那么处理完输入就可以得到初始的解码规则
 * 下面要考虑经过中间量解码情况
 * 遍历二维数组
 * 如果[i][j]==1&&[j][k]==1
 * 必然有[i][k]=1
 * 赋值
 * 这个过程要一直持续到isChanged为false
 * 最后只要看是不是[s][t]为1
 *
 * 注意处理的时候先剔除String.length不一样的
 * 然后要特别注意如果s和t的charAt(index)是一样的
 * 就算是可行的
 * s[i]==t[i]这种情况不需要去查二维数组
 * 也不能去查
 * 否则会得到错误的答案
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int map[27][27] = {{0}};

int judge ( const char * s, const char * t ) {
	if ( !strcmp ( s, t ) ) return 1;

	if ( strlen ( s ) != strlen ( t ) ) return 0;

	int len = strlen ( s );

	for ( int i = 0; i < len; i++ ) {
		char k = s[i] - 'a';
		char v = t[i] - 'a';

		if ( k == v ) continue;

		if ( map[k][v] == 1 ) continue;

		return 0;
	}

	return 1;
}


int main() {
	int m, n;
	scanf ( "%d %d\n", &m, &n );

	while ( m-- ) {
		char k, v;
		scanf ( "%c %c\n", &k, &v );
		map[k - 'a'][v - 'a'] = 1;
		map[k - 'a'][k - 'a'] = 1;
	}

	int changed;

	do {
		changed = 0;

		for ( int i = 0; i < 26; i++ ) {
			for ( int j = 0; j < 26; j++ ) {
				if ( map[i][j] == 1 ) {
					for ( int k = 0; k < 26; k++ ) {
						if ( map[j][k] == 1 ) {
							if ( map[i][k] == 0 ) {
								map[i][k] = 1;
								changed = 1;
							}
						}
					}
				}
			}
		}
	} while ( changed );

	while ( n-- ) {
		char s[60] = {0};
		char t[60] = {0};
		scanf ( "%s%s", s, t );
		printf ( "%s\n", judge ( s, t ) ? "yes" : "no" );
	}

	return 0;
}