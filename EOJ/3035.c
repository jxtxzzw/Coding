/**
#include <stdio.h>
#include <stdlib.h>

int row = 0, col = 0;
int cnt = 0;
int data[101][101] = {{0}};

void dfs ( int x, int y ) {
    data[x][y] = 0;
    int new_x;
    int new_y;

    //向左搜索
    new_x = x;
    new_y = y - 1;
    if ( new_x >= 0 && new_x < row && new_y >= 0 && new_y < col && data[new_x][new_y] == 1 ) {
        cnt++;
        dfs ( new_x, new_y );
    }

	//向右搜索
    new_x = x;
    new_y = y + 1;
    if ( new_x >= 0 && new_x < row && new_y >= 0 && new_y < col && data[new_x][new_y] == 1 ) {
        cnt++;
        dfs ( new_x, new_y );
    }

    //向上搜索
    new_x = x - 1;
    new_y = y;
    if ( new_x >= 0 && new_x < row && new_y >= 0 && new_y < col && data[new_x][new_y] == 1 ) {
        cnt++;
        dfs ( new_x, new_y );
    }

    //向下搜索
    new_x = x + 1;
    new_y = y;
    if ( new_x >= 0 && new_x < row && new_y >= 0 && new_y < col && data[new_x][new_y] == 1 ) {
        cnt++;
        dfs ( new_x, new_y );
    }

}

int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );

        scanf ( "%d%d", &row, &col );

        for ( int i = 0; i < row; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                scanf ( "%d", &data[i][j] );
            }
        }

int max = 0;

for ( int i = 0; i < row; i++ ) {
	for ( int j = 0; j < col; j++ ) {
		if ( data[i][j] == 1 ) {
			cnt = 1;
			dfs ( i, j );

			if ( cnt > max ) {
				max = cnt;
			}
		}
	}
}

        printf ( "%d\n", max );
    }

    return 0;
}
**/
/*
#include <stdio.h>
#include <stdlib.h>

int row = 0, col = 0;
int cnt = 0;
int data[101][101] = {{0}};
int original_data[101][101] = {{0}};

void dfs ( int x, int y ) {
    data[x][y] = 0;
    int new_x;
    int new_y;
    new_x = x;
    new_y = y - 1;

    if ( new_x >= 0 && new_x < row && new_y >= 0 && new_y < col && data[new_x][new_y] == 1 ) {
        cnt++;
        dfs ( new_x, new_y );
    }

    new_x = x;
    new_y = y + 1;

    if ( new_x >= 0 && new_x < row && new_y >= 0 && new_y < col && data[new_x][new_y] == 1 ) {
        cnt++;
        dfs ( new_x, new_y );
    }

    new_x = x - 1;
    new_y = y;

    if ( new_x >= 0 && new_x < row && new_y >= 0 && new_y < col && data[new_x][new_y] == 1 ) {
        cnt++;
        dfs ( new_x, new_y );
    }

    new_x = x + 1;
    new_y = y;

    if ( new_x >= 0 && new_x < row && new_y >= 0 && new_y < col && data[new_x][new_y] == 1 ) {
        cnt++;
        dfs ( new_x, new_y );
    }

}

int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );

        scanf ( "%d%d", &row, &col );

        for ( int i = 0; i < row; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                scanf ( "%d", &data[i][j] );
                original_data[i][j] = data[i][j];
            }
        }

        int max1 = 0;

        for ( int i = 0; i < row; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                if ( data[i][j] == 1 ) {
                    cnt = 1;
                    dfs ( i, j );

                    if ( cnt > max1 ) {
                        max1 = cnt;
                    }
                }
            }
        }

for ( int i = 0; i < row; i++ ) {
	for ( int j = 0; j < col; j++ ) {
		data[i][j] = original_data[i][j];
	}
}

        int max2 = 0;

        for ( int i = 0; i < row; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                if ( data[i][j] == 1 ) {
                    cnt = 1;
                    dfs ( i, j );

if ( cnt > max2 && cnt < max1 ) {
	max2 = cnt;
}
                }
            }
        }

        printf ( "%d\n", max2 );
    }

    return 0;
}
*/

#include <stdio.h>
#include <stdlib.h>

int row = 0, col = 0;
int cnt = 0;
int data[101][101] = {{0}};
int original_data[101][101] = {{0}};

void dfs ( int x, int y ) {
    data[x][y] = 0;
    int new_x;
    int new_y;
    int direction[4][2] = {{0, 1}, {0, -1}, {1, 0}, { -1, 0}};
    for ( int i = 0; i < 4; i++ ) {
        new_x = x + direction[i][0];
        new_y = y + direction[i][1];
        if ( new_x >= 0 && new_x < row && new_y >= 0 && new_y < col && data[new_x][new_y] == 1 ) {
            cnt++;
            dfs ( new_x, new_y );
        }
    }
}

int main() {
    int cas;
    scanf ( "%d", &cas );
    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        scanf ( "%d%d", &row, &col );
        for ( int i = 0; i < row; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                scanf ( "%d", &data[i][j] );
                original_data[i][j] = data[i][j];
            }
        }
        int max1 = 0;
        for ( int i = 0; i < row; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                if ( data[i][j] == 1 ) {
                    cnt = 1;
                    dfs ( i, j );
                    if ( cnt > max1 ) {
                        max1 = cnt;
                    }
                }
            }
        }
        for ( int i = 0; i < row; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                data[i][j] = original_data[i][j];
            }
        }
        int max2 = 0;
        for ( int i = 0; i < row; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                if ( data[i][j] == 1 ) {
                    cnt = 1;
                    dfs ( i, j );
                    if ( cnt > max2 && cnt < max1 ) {
                        max2 = cnt;
                    }
                }
            }
        }
        printf ( "%d\n", max2 );
    }
    return 0;
}
