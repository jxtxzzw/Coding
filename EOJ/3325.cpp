/**
 * 注意到题目输入的是1900-9999年
 * 所以可以直接用张羽戈的模板
 * 这里不会触发那个隐藏的错误点
 *
 * 事实上
 * 1900-01-01以后的日期
 * 处理起来是最安全的
 * 也不会有兼容性的问题
 * 
 */

#include <iostream>
#include <stdio.h>
#include <math.h>

using namespace std;

int DateToInt ( int m, int d, int y ) {
	return
		1461 * ( y + 4800 + ( m - 14 ) / 12 ) / 4 +
		367 * ( m - 2 - ( m - 14 ) / 12 * 12 ) / 12 -
		3 * ( ( y + 4900 + ( m - 14 ) / 12 ) / 100 ) / 4 +
		d - 32075;
}

void IntToDate ( int jd, int &m, int &d, int &y ) {
	int x, n, i, j;
	x = jd + 68569;
	n = 4 * x / 146097;
	x -= ( 146097 * n + 3 ) / 4;
	i = ( 4000 * ( x + 1 ) ) / 1461001;
	x -= 1461 * i / 4 - 31;
	j = 80 * x / 2447;
	d = x - 2447 * j / 80;
	x = j / 11;
	m = j + 2 - 12 * x;
	y = 100 * ( n - 49 ) + i + x;
}

int isPrime ( int x ) {
	if ( x == 1 ) return 0;
	if ( x == 2 ) return 1;
	if ( x == 3 ) return 1;

	for ( int i = 2; i <= sqrt ( x ); i++ ) {
		if ( x % i == 0 ) return 0;
	}
	// 这里是我一开始疯狂WA的地方
	// 就是判断质数应该做到哪里
	// 注意应该是(i*i<x)的写法是最保险的
	// (i<sqrt(x))而是(i<=sqrt(x))

	return 1;
}

int isLeap ( int year ) {
	if ( ( year % 4 == 0 && year % 100 != 0 ) || year % 400 == 0 ) {
		return 1;
	} else {
		return 0;
	}
}

int main() {
	int yy;
	scanf ( "%d", &yy );
	int cnt = 0;
	int jd = DateToInt ( 1, 1, yy ) - 1;
	//printf ( "jd=%d\n", jd );
	// 直接用DateToInt()函数把给定年份的1月1日计算出来
	// 见1是因为1月1日算第1天

	for ( int k = 1; k <= 365 + ( isLeap ( yy ) ); k++ ) {
		// 遍历这一年的第k天就死jd+k
		// 闰年是366天
		int m, d, y;
		IntToDate ( jd + k, m, d, y );
		// 把Int转换成Date
		int date = 10000 * y + 100 * m + d;
		// 变成整数YYYYMMDD的形式
		//printf ( "date=%d, k=%d\n", date, k );

		if ( isPrime ( date ) && isPrime ( k ) ) {
			cnt++;
			//printf ( "date:%d is a Prime, it is the %d-th day of the year.\n", date, k );
			// date是质数且第k天是质数
		}
	}

	//printf ( "cnt=%d\n", cnt );
	printf ( "%f\n", ( ( double ) cnt ) / ( 365 + isLeap ( yy ) ) );
}