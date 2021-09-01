class Solution:
    def arrayNesting(self, nums: List[int]) -> int:
        visited = [False] * len(nums)
        cnt = 0
        ans = 0

        for i in range(len(nums)):
            cnt = 0
            while i != nums[i]:
                if (visited[i]):
                    break
                cnt += 1
                ans = max(cnt, ans)
                visited[i] = True
                i = nums[i]
        return max(1, ans)
