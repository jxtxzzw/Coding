#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXIMUM_LENGTH 110007
#define MAXIMUM_INDEX 18777777
#define UNUSED_CHARACTER '$'
#define true 1
#define false 0

int inputStringA[MAXIMUM_LENGTH]= {0};
int inputStringB[MAXIMUM_LENGTH]= {0};
int stringForLCP[MAXIMUM_LENGTH]= {0};

int suffixArray[MAXIMUM_LENGTH]= {0};
int rankArray[MAXIMUM_LENGTH]= {0};
int heightArray[MAXIMUM_LENGTH]= {0};

int x[MAXIMUM_LENGTH]= {0};
int y[MAXIMUM_LENGTH] = {0};
int bucket[MAXIMUM_INDEX]= {0};

int length;
int lengthA;
int lengthB;

void getSuffixArray();
void getHeightArray();
int getAnswer();
int max(int,int );
int belongsToDifferentString(int );
void joinForLCP(int [], int[], int[]);
void swap(int [], int[]);
int input(int []);
void clearBucket();
void recountBucket();

int main()
{
    //freopen("../data.in","r",stdin);
    lengthA = input(inputStringA);
    lengthB = input(inputStringB);
    joinForLCP(stringForLCP,inputStringA,inputStringB);
    getSuffixArray(MAXIMUM_INDEX);
    //for (int i=0; i<length; ++i) printf("%d%c",suffixArray[i],i==length-1?'\n':' ');
    getHeightArray();
    // for (int i=0; i<length; ++i) printf("%d%c",heightArray[i],i==length-1?'\n':' ');
    int answer = getAnswer();
    printf("%d\n", answer);
}


void swap(int *x, int* y)
{
    for (int i=0; i<MAXIMUM_LENGTH; ++i)
    {
        int tmp = x[i];
        x[i] = y[i];
        y[i] = tmp;
    }

}


void getSuffixArray()
{

    clearBucket();
    for (int i=0; i<length; i++)
    {
        int ch = stringForLCP[i];
        x[i] = ch;
        bucket[ch]++;
    }
    recountBucket();
    for (int i=length-1; i>=0; i--)
    {
        int ch = x[i];
        bucket[ch]=bucket[ch]-1;
        int index = bucket[ch];
        suffixArray[index]=i;
    }
    for (int k=1; k<=length; k=k*2)
    {
        int recounter=0;
        for (int i=length-k; i<length; i++)
        {
            y[recounter]=i;
            recounter=recounter+1;
        }
        for (int i=0; i<length; i++)
        {
            if (suffixArray[i]>=k)
            {
                y[recounter]=suffixArray[i]-k;
                recounter=recounter+1;
            }
        }
        clearBucket();
        for (int i=0; i<length; i++)
        {
            int lastIndex = y[i];
            int ch = x[lastIndex];
            bucket[ch]++;
        }
        recountBucket();
        for (int i=length-1; i>=0; i--)
        {
            int lastIndex = y[i];
            int ch = x[lastIndex];
            bucket[ch] = bucket[ch]-1;
            int index = bucket[ch];
            suffixArray[index] = lastIndex;
        }
        swap(x,y);
        recounter=1;
        x[suffixArray[0]]=0;
        for (int i=1; i<length; i++)
        {
            if (y[suffixArray[i-1]]==y[suffixArray[i]]&&y[suffixArray[i-1]+k]==y[suffixArray[i]+k])
            {
                x[suffixArray[i]]=recounter-1;
            }
            else
            {
                x[suffixArray[i]]=recounter++;

            }
            if (recounter>=length) break;
            // maximumIndex=recounter;
        }

    }

}

void getHeightArray()
{
    int i,k=0;
    for(i=0; i<length; i++) rankArray[suffixArray[i]]=i;
    for (i=0; i<length; i++)
    {
        if (k!=0) k--;
        int j = suffixArray[rankArray[i]-1];
        while (stringForLCP[i+k]==stringForLCP[j+k]) k++;
        heightArray[rankArray[i]]=k;
    }


}

int getAnswer()
{
    int answer = heightArray[0];
    for (int i=1; i<length; i++)
    {
        if (belongsToDifferentString(i))
        {
            answer = max(answer,heightArray[i]);
        }
    }
    return answer;
}


int max(int a,int b)
{
    return a>b?a:b;
}

int belongsToDifferentString(int i)
{
    int ascriptionForLaterSuffix = suffixArray[i]<lengthA ? 1 : 0;
    int ascriptionForPreviousSuffix = suffixArray[i-1]<lengthA? 1: 0;
    return ascriptionForLaterSuffix ^ ascriptionForPreviousSuffix;
}

/*
，y<2^31
，y<2^31
这组数据答案是9

因为逗号是中文逗号
所以s[0]~s[2]才表示这个逗号
如果用的是char[]的话就会出现负数
下标越界
所以不能用char[]保存
要用int[]
这时如果仍然使用scanf("%c")就要单独做处理
以上问题使用C++的string不会有这个问题
仅C的char[]会有

注意上述样例的可见字符仅7个
但是答案是9
是因为"，"算3个
后面"y"~"1"共6个
*/
int input(int s[])
{
    int cnt = 0;
    int possibleChar;
    while (scanf("%c",&possibleChar) && possibleChar!='\n')
    {
        if (possibleChar<0 || possibleChar >=128)
        {
            int character = (256+possibleChar) % 128;
            int restPart1,restPart2;
            scanf("%c%c",&restPart1,&restPart2);
            restPart1 = (restPart1+256)%128;
            restPart2 = (restPart2+256)%128;
            s[cnt++]=(character);
            s[cnt++]=(restPart1);
            s[cnt++]=(restPart2);
        }
        else
        {
            s[cnt++]=possibleChar;
        }
        //printf("input = %d\n",s[cnt]);
        //cnt++;
    }
    return cnt;
}



void joinForLCP(int stringForLCP[], int inputStringA[], int inputStringB[])
{
    for (int i=0; i<lengthA; i++)
    {
        stringForLCP[i]=inputStringA[i];
    }
    stringForLCP[lengthA]=UNUSED_CHARACTER;
    for (int i=0; i<lengthB; i++)
    {
        stringForLCP[lengthA+1+i]=inputStringB[i];
    }
    length = lengthA + 1 + lengthB;

}

void clearBucket()
{
    memset(bucket,0,sizeof(bucket));
}

void recountBucket()
{
    for (int i=1; i<MAXIMUM_INDEX; i++) bucket[i]=bucket[i-1]+bucket[i];

}
