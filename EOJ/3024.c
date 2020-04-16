#include <stdio.h>
#include <string.h>
#define ROL 51
#define COL 151
int  power[ROL][COL] = {{0}, {5, 2, 1}, {0}};

void initial() {
	for ( int i = 2; i < ROL; i++ ) {
		for ( int j = 0; j < 3 * i; j++ ) {
			power[i][j] += power[i - 1][j] * power[1][0];
			power[i][j + 1] += power[i - 1][j] * power[1][1];
			power[i][j + 2] += power[i - 1][j] * power[1][2];
		}

		for ( int j = 0; j < COL; j++ ) {
			int tmp = power[i][j];
			power[i][j] = tmp % 10;;
			power[i][j + 1] += tmp / 10;
		}

		//power[i][3*i]=-1;
	}

	for ( int i = 2; i < ROL; i++ ) {
		for ( int j = 0; j < COL; j++ ) {
			int tmp = power[i][j];
			power[i][j] = tmp % 10;;
			power[i][j + 1] += tmp / 10;
		}
	}

	/*
		for (int i=0;i<ROL;i++){
			for (int j=0;j<COL;j++){
				printf("%d",power[i][j]);
			}
			printf("\n");
		}
		*/
}

int main() {
	initial();
	/*
		for (int i=0;i<15;i++){
			for (int j=0;j<50;j++){
				printf("%d",power[i][j]);
			}
			printf("\n");
		}
	*/
	int cas;
	scanf ( "%d", &cas );

	for ( int t = 0; t < cas; t++ ) {
		char st[51] = {0};
		scanf ( "%s", st );
		int octalArray[51] = {0};
		int pos = 0;

		for ( int i = strlen ( st ) - 1; st[i] != '.'; i-- ) {
			octalArray[pos++] = st[i] - '0';
		}

		octalArray[pos] = -1;
		/*
		for (int i=0;i<pos;i++){
			printf("%d",octalArray[i]);
		}

		printf("\n");*/
		int result[COL] = {0};

		for ( int i = 0; i < pos; i++ ) {
			for ( int j = 0; j < 3 * ( pos - i ); j++ ) {
				result[3 * i + j] += octalArray[i] * power[pos - i][j];
			}
		}

		/*
		for (int i=0;i<COL;i++){
			printf("%d=",result[i]);
		}

		printf("\n");
		*/
		for ( int i = 0; i < 3 * pos; i++ ) {
			int tmp = result[i];
			result[i] = tmp % 10;
			result[i + 1] += tmp / 10;
		}

		/*
		for (int i=0;i<COL;i++){
			printf("%d=",result[i]);
		}
		printf("\n");
			*/
		int begin = 0;

		while ( result[begin] == 0 ) {
			begin++;
		}

		printf ( "case #%d:\n0.", t );

		for ( int i = 0; i < 3 * pos - begin; i++ ) {
			printf ( "%d", result[3 * pos - 1 - i] );
		}

		printf ( "\n" );
	}

	return 0;
}
