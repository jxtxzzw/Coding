#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    char value[100];
    struct Node *next;
} NODE;

double data[2][1000] = {{0}};
double num[1000] = {0};
int isnum[1000] = {0};
int p = 0;

double pop() {
    double ret = num[p];
    p--;
    return ret;
}

void push ( double x ) {
    p++;
    num[p] = x;
    return;
}

int nodeToArray ( NODE * expr ) {
    int cnt = 0;

    while ( expr ) {
        if ( ( expr->value[0] == '+' || expr->value[0] == '-' || expr->value[0] == '*' || expr->value[0] == '/' ) && ( expr->value[1] == '\0' ) ) {
            isnum[cnt] = 0;
            data[0][cnt] = expr->value[0];
            cnt++;

        } else {
            isnum[cnt] = 1;
            sscanf ( expr->value, "%lf", &data[1][cnt] );
            cnt++;
        }

        expr = expr->next;
    }

    return  cnt;
}

double Evaluate ( NODE *expr ) {
    double ret = 0;
    int cnt = nodeToArray ( expr );
    cnt--;

    while ( cnt >= 0 ) {
        if ( !isnum[cnt] ) {
            char type = ( int ) data[isnum[cnt]][cnt];

            switch ( type ) {
            case '+':
                push ( pop() + pop() );
                break;

            case '-':
                push ( pop() - pop() );
                break;

            case '*':
                push ( pop() *pop() );
                break;

            case '/':
                push ( pop() / pop() );
                break;
            }

        } else {
            push ( data[isnum[cnt]][cnt] );
        }

        cnt--;
    }

    ret = pop();
    return ret;
}


NODE *input() {
    NODE *h = 0, *p;

    do {
        p = ( NODE* ) malloc ( sizeof ( NODE ) );
        scanf ( "%s", p->value );
        p->next = h;
        h = p;
    } while ( getchar() != '\n' );

    return h;
}

int main() {
    int i, t;
    scanf ( "%d\n", &t );

    for ( i = 0; i < t; i++ ) printf ( "case #%d:\n%.2f\n", i, Evaluate ( input() ) );

    return 0;
}
