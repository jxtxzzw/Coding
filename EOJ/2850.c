#include<stdio.h>

int isLeap ( int year ) {
	return ( ( year % 4 == 0 && year % 100 != 0 ) || year % 400 == 0 );
}

int main() {
	int year;
	while ( scanf ( "%d", &year ) != EOF ) {
		int interval = 0;
		for ( int i = 2008; i < year; i++ ) {
			interval += ( 365 + isLeap ( i ) );
		}
		int firstDay = interval % 7 + 5;
		printf ( " SU MO TU WE TH FR SA\n" );
		for ( int i = 0; i < firstDay % 7; i++ ) {
			printf ( "   " );
		}
		for ( int i = 1; i <= 28 + isLeap ( year ); i++ ) {
			printf ( "%3d", i );
			if ( ( firstDay + i ) % 7 == 0 || i == 28 + isLeap ( year ) ) {
				printf ( "\n" );
			}
		}
		printf ( "\n" );
	}
	return 0;
}



