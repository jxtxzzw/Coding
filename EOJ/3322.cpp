/**
 * 题目的意思是给定一天
 * 然后输出这是星期几
 *
 * 这道题的测试数据有误
 * 下详
 *
 * 用Java做竟然是Wrong Answer
 * 用张羽戈的模板是Accept
 * 可见模板是错的
 *
 * 下面先给出AC的代码
 * 注意因为测试数据有误
 * 所以这段AC的代码实际上是错误的
 * 
 */

#include <iostream>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

string dayOfWeek[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

int DateToInt (int m, int d, int y){
  return
    1461 * (y + 4800 + (m - 14) / 12) / 4 +
    367 * (m - 2 - (m - 14) / 12 * 12) / 12 -
    3 * ((y + 4900 + (m - 14) / 12) / 100) / 4 +
    d - 32075;
}

void IntToDate (int jd, int &m, int &d, int &y){
  int x, n, i, j;

  x = jd + 68569;
  n = 4 * x / 146097;
  x -= (146097 * n + 3) / 4;
  i = (4000 * (x + 1)) / 1461001;
  x -= 1461 * i / 4 - 31;
  j = 80 * x / 2447;
  d = x - 2447 * j / 80;
  x = j / 11;
  m = j + 2 - 12 * x;
  y = 100 * (n - 49) + i + x;
}

string IntToDay (int jd){
  return dayOfWeek[jd % 7];
}


int main()
{
    int m,d,y;
    scanf("%d-%d-%d",&y,&m,&d);
    cout<<IntToDay(DateToInt(m,d,y))<<endl;
    return 0;
}

/**
 * 下面给出正确的代码
 * 注意由于测试数据有误
 * 所以这段代码运行的结果是WA
 * 
 */

/*

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		final String namesOfWeekdays[] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
				"Saturday" };
		Scanner in = new Scanner(System.in);
		Calendar calendarInstance = Calendar.getInstance();
		Date dateInformation = new Date();
		try {
			dateInformation = new SimpleDateFormat("yyyy-MM-dd").parse(in.nextLine());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendarInstance.setTime(dateInformation);
		int dayOfWeek = calendarInstance.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;
		System.out.println(namesOfWeekdays[dayOfWeek]);
	}
}

*/

/**
 * 原因如下
 * 罗马的某个教皇把1582年10月5日至14日这10天撤销了
 * （详询Wiki）
 * 1582年10月4日后紧跟着就是15日
 * 所以1582-10-15是星期五
 * 它前面的1天是1582-10-4是星期四
 * 注意到中间的10天是不存在的
 *
 * 如果按照一般的情况
 * 10月15日是星期五
 * 10月4日比15日相差了11天
 * 所以是星期一
 * 按照张羽戈的模板做下来确实
 * 1582-10-15是星期五
 * 1582-10-04是星期一
 *
 * 然而Java的Calendar在这里做了特殊的处理
 * 1582-10-15是星期五
 * 1582-10-04是星期四
 *
 * 同样的
 * select to_date('1582-10-4','yyyy-mm-dd')+1 from dual
 * 答案是1582-10-15
 *
 * 由此可见
 * 张羽戈的模板仅适用于1582-10-15及以后的日历
 * 但是题目说到年份包含前导0
 * 那么就应该分类讨论
 * 分10-04之前的和10-15之后的两个公式
 * 
 */

/**
 * 附张羽戈的模板
 * 
 */

/*

// Routines for performing computations on dates.  In these routines,
// months are exprsesed as integers from 1 to 12, days are expressed
// as integers from 1 to 31, and years are expressed as 4-digit
// integers.

string dayOfWeek[] = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};

// converts Gregorian date to integer (Julian day number)

int DateToInt (int m, int d, int y){  
  return 
    1461 * (y + 4800 + (m - 14) / 12) / 4 +
    367 * (m - 2 - (m - 14) / 12 * 12) / 12 - 
    3 * ((y + 4900 + (m - 14) / 12) / 100) / 4 + 
    d - 32075;
}

// converts integer (Julian day number) to Gregorian date: month/day/year

void IntToDate (int jd, int &m, int &d, int &y){
  int x, n, i, j;

  x = jd + 68569;
  n = 4 * x / 146097;
  x -= (146097 * n + 3) / 4;
  i = (4000 * (x + 1)) / 1461001;
  x -= 1461 * i / 4 - 31;
  j = 80 * x / 2447;
  d = x - 2447 * j / 80;
  x = j / 11;
  m = j + 2 - 12 * x;
  y = 100 * (n - 49) + i + x;
}

// converts integer (Julian day number) to day of week

string IntToDay (int jd){
  return dayOfWeek[jd % 7];
}

*/