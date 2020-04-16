#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main() {
    int n, m, i;
    scanf ( "%d%d", &n, &m );
    int len = log10 ( n ) + 1;
    //用以10为底的对数来求n的位数，记得求完以后要加1
    printf ( "%d ", len );
    char buffer[len];
    sprintf ( buffer, "%d", n );
    m = ( len - ( m % len ) ) % len;
    //特别注意这里要写 % len
    int isnum = 0;
    for ( i = 0; i < len; i++ ) {
        if ( isnum || ( buffer[m] - '0' ) ) {
        //用一个逻辑或运算，实现判断最高位是否为0
        //因为buffer是char数组，所以要减去48，即'0'
            printf ( "%c", buffer[m] );
            isnum = 1;
        }
        m = ( m + 1 ) % len;
        //数组从0开始，记得加1
    }
    return 0;
}
