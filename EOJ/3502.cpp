#include <iostream>
#include <set>
#include <map>

//题目说了是哈希
//对一个密码 abc
//可以影响他的密码是他的子串
//也就是 a ab abc b bc c
//拿到密码，取出所有的子串，每一个子串影响的密码数加1
//也就是 a影响1个密码 ab影响1个密码 ……
//最后遍历所有的密码，把这些密码影响的密码数加起来，再减去自己对自己的密码，就是答案
//然后这些密码是字符串，要变成整数，就是用hash

using namespace std;

/**
 * 计算给定字符串的哈希
 * @param s 字符串
 * @return 哈希
 */
long long getHash(string s) {
    int length = s.length();
    long long ret = 0; // 必须是long long，int不行
    for (int i = 0; i < length; ++i) {
        ret *= 31;
        ret += s[i] - 'a' + 1;
    }
    return ret;
}

int main() {
    int number;
    cin >> number;
    string passwords[number];
    for (int i = 0; i < number; ++i) {
        cin >> passwords[i];
    }
//    const long long MAXN = getHash("zzzzzzzzzz");
    map<long long, long long> map;
    for (int i = 0; i < number; ++i) {
        string affectedString = passwords[i];
//        cout << "current password : " << affectedString << endl;
        set<long long> set;
        // 当前密码收到它所有子串的影响，取出所有子串，计算哈希，重复的子串只记录一次，所以需要用到set
        for (int beginIndex = 0; beginIndex < affectedString.length(); beginIndex++) {
            for (int gapNumber = 1; beginIndex + gapNumber <= affectedString.length(); gapNumber++) {
                string substring = affectedString.substr(beginIndex, gapNumber);
                // substr的用法和substring不一样
                long long hash = getHash(substring);
                set.insert(hash);
//                cout << substring << "+" << hash << endl;
            }
        }
//        cout << "----" << endl;
        for (long long hash : set) {
            ++map[hash];
        }
    }
    long long counter = 0;
    for (int i = 0; i < number; ++i) {
        counter += map[getHash(passwords[i])];
    }

    cout << counter - number << endl;


}
