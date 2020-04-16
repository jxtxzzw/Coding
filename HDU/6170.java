/**
 * 这道题是正则表达式的题目
 * 不过有点区别
 * 所以我还是看了测试数据以后才把所有情况考虑进去的
 * 一开始我用的是正宗的正则表达式直接判断的
 * 不仅超时
 * 还是错的
 * 
 * 下面开始一步一步说
 * 
 */

/*
int t = in.nextInt();
while  (t--!=0) {
    String s = in.next();
    String regex = in.next();
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(s);
    if (m.matches()) {
        System.out.println("yes");
    } else {
        System.out.println("no");
    }
}
*/

/**
 * 上面这段代码很清楚
 * 就是读入一个input是输入
 * 读入一个regex是正则表达式匹配规则
 * 然后compile一下得到一个Pattern
 * 用Pattern的input作为Matcher
 * 去match看是不是true
 * 注意是match
 * 而不是find
 * 要求的是整个字符串匹配
 * 而不是存在部分匹配
 * 
 */

/**
 * 但是这段代码超时
 * 并且答案也是错的
 * 
 */

/**
 * 首先解决错误
 * 看一种情况
 * ".*"
 * 如果是普通的匹配
 * 是可以匹配"sdfnjkwe"的
 * 因为".*"意思就是""或者"."或者".."或者"....."等
 * 但是在这道题的意思是
 * "."匹配了一个字母
 * 假设是"s"
 * 那么".*"只能匹配到"sssss"或者"ss"、"sss"、""
 * 因为".*"此时就变成了"s*"
 * 这是这道题和Regex不一样的地方
 * 于是我们要把
 * ".*"
 * 换成"(.)\1*"
 * 这里的括号是分组
 * 表示"."匹配到的那一个
 * \1是反向引用
 * 于是"(.)\1*"在上面的例子上就相当于"s*"
 * 
 */

/*
String[] s = rawRegex.split("\\.\\*");
*/

/**
 * 首先把".*"给分出来
 * 因为单独的"."和"*"都是不影响的
 * 
 */

/*
for (i = 0; i < s.length; i++) {
	sb.append(s[i]);
	if (i < s.length - 1)
		sb.append("(.)\\" + (${aNumber}) + "*");
}
*/

/**
 * 然后利用StringBuffer把".*"替换成"(.)\${aNumber}*"
 * 其中${aNumber}指的是反向引用的编号
 * 
 */
/*
if (rawRegex.endsWith(".*"))
	sb.append("(.)\\" + (${aNumber}) + "*");
*/

/**
 * 注意到最后一个如果是以".*"结尾的
 * 要加上
 * 因为Split会吞掉
 * 这个还有陷阱
 * 下文提及
 * 
 */

/**
 * 这样一来就把所有的".*"换掉
 * 比如".*aab..*a*ab*"就是"(.)\1*aab.(.)\2*a*ab*"
 * 
 */

/**
 * 还有一个问题是会超时
 * 比如用"a*a*a*"去匹配"baaaa"
 * 显然瞬间就是不可能的
 * 不会超时
 * 但是匹配"aaaaab"的话就要去看看再说
 * 于是为了满足贪婪匹配原则
 * 就要不停地尝试
 * 所以当比较短的时候还行
 * 长了就不行了
 * 比如最后一组测试数据（下附）
 * 就会导致TLE
 * 这时候我们考虑一个问题
 * "a*a*"和"a*"是一样的
 * "a*a*a*"和"a*"是一样的
 * 即"(.*)\1{1,}"只要留下一个就行了
 * 但是怎么就留下一个呢
 * replacement调用分组编号
 * 
 */

/*
rawRegex = rawRegex.replaceAll("(.\\*)\\1{1,}", "$1");
*/

/**
 * 这里就把"(.\\*)"匹配到的重复了2次及以上的保留1个
 * 具体意思是"."匹配任意字符，不妨假设为a
 * 而"\\*"意思是"*"本身
 * 于是"(.\\*)"在这里就是"(a*)"
 * 而"\1"的意思是第1个分组即"(a*)"
 * 这个分组再次出现了[1,正无穷)次
 * 意思就是"(a*)"一共出现了2次及以上
 * 保留一次的意思就是把所有的次数替换成"(a*)"
 * 即"(.\\*)"
 * 即"$1"
 * 
 * 比如"a*b*b*b*b*"
 * 替换后就会变成"a*b*"
 * 
 * 而显然这两者在判断yes或者no的时候是等价的
 * 
 */

/**
 * 核心代码如下
 * 
 */

/*
把冗余的匹配信息去除
比如能够用a*表示相同的意思就不用a*a*a*
rawRegex=rawRegex.replaceAll("(.\\*)\\1{1,}","$1");
*/

/*
分组
把.*按照题目的意思替换成分组
String[] s=rawRegex.split("\\.\\*");
for(...){sb.append(...); sb.append(...);}
注意这里的编号是(i+1)
sb.append("(.)\\"+(i+1)+"*");
*/

/*
如果出现在最后
split的时候会被吞掉
但是要补上
if(...)sb.append(...);
注意补上的编号
如果出现过
就一定是s.length
"(.)\\"+(s.length)+"*"
但是特别注意
如果原始字符串就是".*"
那么这里就会变成"(.)\1*"
但是原始字符串的".*"经过split就被吞并了
s数组是空
所以这里用s.length还不行
这里应该是1而不是0
所以当这种情况的时候要判断一下
也就是if(s.length==0)的时候要特殊处理
我这里直接用了Math.max(s.length,1)
"(.)\\"+(Math.max(s.length,1))+"*"
*/

/**
 * 此时得到的regex才是真正的Regex
 * 下面就是最简单的匹配了
 * 直接Pattern+Matcher
 * 
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- != 0) {
			String input = in.next();
			String rawRegex = in.next();
			rawRegex = rawRegex.replaceAll("(.\\*)\\1{1,}", "$1");
			String[] s = rawRegex.split("\\.\\*");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length; i++) {
				sb.append(s[i]);
				if (i < s.length - 1)
					sb.append("(.)\\" + (i + 1) + "*");
			}
			if (rawRegex.endsWith(".*"))
				sb.append("(.)\\" + (Math.max(s.length, 1)) + "*");
			String regex = sb.toString();
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(input);
			System.out.println(m.matches() ? "yes" : "no");
		}
	}

}

/**
 * 附测试数据及答案
 * 
 * 请特别注意
 * 测试数据少考虑了一种情况
 * 如
 * 2
 * baa
 * b.*.*
 * dcc
 * dc*c*
 * 
 */

/*
15
aab
c*a*b
abcaba
abbcaba
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aa*aa.*aaa
abbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb
aa*bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb
adfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFF
adfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFFadfasfsfsdfgfgddsarftewrwrsfdsfsdafsdfsfaqeregafwerewrgfsafwregdafgdfdfsdAAAAVFDDGFGDDDSSGSDFEFF
abaAAAASSSfdfdsagfdgfdgsdafdsfsa
aabaAAAASSSfdfdsagfdgfdgsdafdsfs
ABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRY
a*.*.b*
ABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEW
ABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWTERYTWBWGYHWTYWYTGTYWGTYTWRYABCDEFGHJKLSDFAFGGSDFAGFDFADSFAFDSAGSADFSAFDSFDSFWEEWbb*
aaaaabbbbbccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc
a*b*c*
dagfadgfdsagfgdsfdgfdfgdfdsafdagfgdafgda
.
dagfadgfdsagfgdsfdgfdfgdfdsafdagfgdafgda
.*
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
.*
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*
baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab
a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*
*/

/*
yes
no
yes
yes
yes
no
no
no
yes
no
no
yes
yes
no
no
*/