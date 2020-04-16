	#include <stdio.h>
	#include <stdlib.h>
	#include <math.h>
	int getnum ( char c ) {
	int ret = 0;
	if ( c >= 'a' && c <= 'z' ) c -= 97;
	else c -= 65;
	if ( c <= 17 ) ret = c / 3 + 2;
	else ret = fmin ( 7 + ( c - 16 ) / 3, 9 );
	return ret;
	}
	int main() {
	char ch[8];
	int i;
	for ( i = 0; i < 8; i++ ) {
	scanf ( "%c", &ch[i] );
	printf ( "%d", getnum ( ch[i] ) );
	}
	printf ( "\n" );
	return 0;
	}
