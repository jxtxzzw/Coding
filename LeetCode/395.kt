// 数出出现次数小于 k 的字母，用这些字母分隔字符串
// 递归处理分隔后的每一个字符串，记录最大的结果
// 特殊判断一下数组长度为 1 的情况

class Solution {
    fun longestSubstring(s: String, k: Int): Int {
        val chars = s.toCharArray()
        val invalidChars = chars.groupBy { it }.filter { (_, lst) -> lst.size < k }.keys
        var candidate = if (s.isNotEmpty()) listOf(s) else listOf()
        invalidChars.forEach { c -> candidate = candidate.flatMap { it.split(c) } }
        return if (candidate.size > 1)
            candidate.fold(0) { sum, str -> sum.coerceAtLeast(longestSubstring(str, k)) }
        else
            candidate.firstOrNull()?.length ?: 0
    }
}
