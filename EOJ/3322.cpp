/**
 * ��Ŀ����˼�Ǹ���һ��
 * Ȼ������������ڼ�
 *
 * �����Ĳ�����������
 * ����
 *
 * ��Java����Ȼ��Wrong Answer
 * ��������ģ����Accept
 * �ɼ�ģ���Ǵ��
 *
 * �����ȸ���AC�Ĵ���
 * ע����Ϊ������������
 * �������AC�Ĵ���ʵ�����Ǵ����
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
 * ���������ȷ�Ĵ���
 * ע�����ڲ�����������
 * ������δ������еĽ����WA
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
 * ԭ������
 * �����ĳ���̻ʰ�1582��10��5����14����10�쳷����
 * ����ѯWiki��
 * 1582��10��4�պ�����ž���15��
 * ����1582-10-15��������
 * ��ǰ���1����1582-10-4��������
 * ע�⵽�м��10���ǲ����ڵ�
 *
 * �������һ������
 * 10��15����������
 * 10��4�ձ�15�������11��
 * ����������һ
 * ����������ģ��������ȷʵ
 * 1582-10-15��������
 * 1582-10-04������һ
 *
 * Ȼ��Java��Calendar��������������Ĵ���
 * 1582-10-15��������
 * 1582-10-04��������
 *
 * ͬ����
 * select to_date('1582-10-4','yyyy-mm-dd')+1 from dual
 * ����1582-10-15
 *
 * �ɴ˿ɼ�
 * ������ģ���������1582-10-15���Ժ������
 * ������Ŀ˵����ݰ���ǰ��0
 * ��ô��Ӧ�÷�������
 * ��10-04֮ǰ�ĺ�10-15֮���������ʽ
 * 
 */

/**
 * ��������ģ��
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