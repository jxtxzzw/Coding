#include <stdio.h>
#include <stdlib.h>



typedef struct paper{
int id;
int hard;
} Paper;

Paper PAPER[1007] = {0};

int comp1(const void * a, const void * b)
{
    return ((Paper*)b) -> hard - ((Paper*)a) -> hard;
}

int comp2(const void * a, const void * b)
{
    return ((Paper*)a) -> id - ((Paper*)b) -> id;
}

int main()
{

    int caseNumber = 0;
    scanf("%d",&caseNumber);
    while (caseNumber--)
    {
        int n = 0;
        int k = 0;
        scanf("%d%d",&n,&k);

        for (int i = 0; i < n; i++)
        {
            int x;
            scanf("%d",&x);
            PAPER[i].hard = x;
            PAPER[i].id = i+1;
        }
        qsort(PAPER,n,sizeof(Paper),comp1);
        qsort(PAPER,k,sizeof(Paper),comp2);
        for (int i = 0; i < k; i++)
            printf("%d%c", PAPER[i].id , i == k-1 ? '\n' : ' ');
    }
}
