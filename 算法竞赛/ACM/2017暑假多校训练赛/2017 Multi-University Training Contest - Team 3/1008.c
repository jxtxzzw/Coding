//by - lty
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>

long long n , k;
const long long MOD = 1000000007;
long long quickpow();

// Core part is quickPower, will not cause TLE

int main()
{
    //freopen("input.txt" , "r" , stdin);
    int cas=1;
    while (scanf("%I64d%I64d" , &n , &k) != EOF)
    {
        n %= MOD;
        printf("Case #%d: " , cas++);
        printf("%I64d\n" , quickpow());
    }
    return 0;
}

long long quickpow()
{
    long long r = 1 , base = n;
    while (k != 0)
    {
        if(k % 2)
        {
            r *= base;
            r %= MOD;
        }
        base = (base * base) % MOD;
        k /= 2;
    }
    return r;
}