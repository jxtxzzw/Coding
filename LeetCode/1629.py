class Solution:
    def slowestKey(self, releaseTimes: List[int], keysPressed: str) -> str:
        dic = {}
        n = len(releaseTimes)

        m = 0
        ans = ''
        for i in range(n):
            time = releaseTimes[i] if i == 0 else releaseTimes[i] - releaseTimes[i - 1]
            word = keysPressed[i]
            dic[word] = max(0 if not word in dic else dic[word], time)
            print(word, dic[word])
            if dic[word] > m:
                m = dic[word]
                ans = word
            elif dic[word] == m:
                ans = ans if ans > word else word

        return ans
