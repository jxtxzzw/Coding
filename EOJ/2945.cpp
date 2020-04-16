#include <bits/stdc++.h>

using namespace std;

int main(){
    int max = - 0x3F3F3F3F;
    int n;
    int cnt = 0;
    string ss[20];
    scanf("%d", &n);
    while (n--!=0){
        string s;
        cin >> s;
        int w;
        cin >> w;
        if (w>max){
            cnt = 0;
            max = w;
            ss[cnt] = s;
            cnt++;
        } else if (w==max){
            ss[cnt] = s;
            cnt++;
        }
        
    }
    
    for (int i=0;i<cnt;++i){
        cout << ss[i] << endl;
    }
    
}