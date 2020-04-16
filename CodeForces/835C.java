/**
 * 以下代码在第8组测试数据超时
 * 
 * 暴力搜索
 * 对于每一次仰望星空，初始化Answer为0
 * 然后遍历所有的星星，看是不是在视野内
 * 如果在，则计算当前Moments的亮度，然后加入Answer
 * 
 */

/*

import java.awt.Point;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numberOfStars = in.nextInt();
		int numberOfViews = in.nextInt();
		int maximumBrightnessOfStars = in.nextInt();
		Star[] stars = new Star[numberOfStars];
		View[] views = new View[numberOfViews];
		for (int i = 0; i < stars.length; i++) {
			stars[i] = new Star(in.nextInt(), in.nextInt(), in.nextInt(), maximumBrightnessOfStars);
		}
		for (int i = 0; i < views.length; i++) {
			views[i] = new View(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
		}
		// 至此，读入数据完成
		for (int i = 0; i < views.length; i++) {
			// 对于每次仰望
			int answer = 0;
			for (int j = 0; j < stars.length; j++) {
				// 遍历星星
				if (views[i].isInRange(stars[j])) {
					// 如果这星星在视野范围内
					// System.out.println("Star "+j+" is in range: " +
					// stars[j].timeFlush(views[i].getMoments()));
					answer += stars[j].timeFlush(views[i].getMoments());
					// 计算当前时刻的亮度，然后加入到answer中
				}
			}
			System.out.println(answer);
			// 输出答案
		}

	}
}

class Star {
	// 星星类
	
	private Point coordinates = null; // 星星的坐标
	private int initialBrightness = 0; // 初始亮度
	private int maximumBrightnessOfStars = 0; // 最大亮度
	// 这个类同时适用于每颗星星最大亮度不同的情况

	public Star(int x, int y, int s, int c) {
		this.coordinates = new Point(x, y);
		this.initialBrightness = s;
		this.maximumBrightnessOfStars = c;
	}

	public int timeFlush(int t) {
		return (initialBrightness + t) % (maximumBrightnessOfStars + 1);
		// 初始亮度加上时刻，如果大于最大亮度则回到0
	}

	public Point getCoordinates() {
		return coordinates;
	}

}

class View {
	// 视野类
	
	private int moments = 0; // 当前的时刻
	private Point lowerLeftCorner = null; // 左下角坐标
	private Point upperRightCorner = null; // 右上角坐标

	public View(int t, int x1, int y1, int x2, int y2) {
		this.moments = t;
		this.lowerLeftCorner = new Point(x1, y1);
		this.upperRightCorner = new Point(x2, y2);
	}

	public boolean isInRange(Star star) {
		return star.getCoordinates().getX() >= lowerLeftCorner.getX()
				&& star.getCoordinates().getY() >= lowerLeftCorner.getY()
				&& star.getCoordinates().getX() <= upperRightCorner.getX()
				&& star.getCoordinates().getY() <= upperRightCorner.getY();
				// 判断星星是不是在视野内
				// 横纵坐标需要满足以上4个条件
	}

	public int getMoments() {
		return moments;
	}

}

*/

/**
 * 上述代码超时的原因是数据范围实在是太大了
 * 在看了CSDN上的某题解以后，明白了这道题应该用动态规划做
 * 考虑去预处理，使每次查询都能在O(1)的时间内得到矩形内的星星
 * 由于星星亮度不同，无法直接计算
 * 但是亮度的范围小，所以把不同亮度的星星分开统计
 * 用info[][][]数组记录视野信息
 * info[x][y][brightness]表示从(1,1)到(x,y)这个矩形内，初始亮度为brightness的星星有多少
 * 动态转移方程为
 * info[i][j][k]=info[i][j][k]+info[i-1][j][k]+info[i][j-1][k]-info[i-1][j-1][k]
 * 下详
 * 之后每次查询的时候，显然初始亮度相同的星星在Moments时刻的亮度是相同的
 * 故由info可查询到初始亮度为{X}的星星的数量
 * 查询的表达式为
 * info[x2][y2][iniB]-info[x1-1][y2][iniB]-info[x2][y1-1][iniB]+info[x1-1][y1-1][iniB]
 * 下详
 * 以上两个式子都是容斥原理，画图可以显然
 * 之后由初始亮度计算当前亮度
 * 计算表达式为
 * (initialBrightness+currentMoments)%(MAXIMUM_BRIGHTNESS+1)
 * 同上
 * 得到需要的全部数据
 * 亮度乘上数量，加到Answer中
 * 
 * 另有很多坑，下详
 * 
 */


import java.util.Scanner;

public class Main {

	private final static int MAX_COORDINATE_ROW = 100; 
	private final static int MAX_COORDINATE_COL = 100;
	// 坐标最大范围

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int NUMBER_OF_STARS = in.nextInt();
		final int NUMBER_OF_VIEWS = in.nextInt();
		final int MAXIMUM_BRIGHTNESS = in.nextInt();
		int[][][] info = new int[MAX_COORDINATE_ROW + 7][MAX_COORDINATE_COL + 7][MAXIMUM_BRIGHTNESS + 7];
		// 动态规划
		// info数组记录从(1,1)到(x,y)这个矩形内，初始亮度为brightness的星星有多少
		for (int i = 0; i < NUMBER_OF_STARS; i++)
			info[in.nextInt()][in.nextInt()][in.nextInt()]++;
		// 读入星星的数据
		// 注意这时候其实info[x][y][k]的意义仅仅是在(x,y)这个点有多少亮度为k的星星
		// 这时候还不表示从(1,1)到(x,y)的矩阵
		// 这里有个坑，那就是题目没有说保证星星的坐标唯一
		// 所以，info[in.nextInt()][in.nextInt()][in.nextInt()]=1，就会使后面答案错误
		// 这里要写成info[in.nextInt()][in.nextInt()][in.nextInt()]++
		for (int i = 1; i <= MAX_COORDINATE_ROW; i++) {
			for (int j = 1; j <= MAX_COORDINATE_COL; j++) {
				// 动态转移方程
				// 注意到坐标是从(1,1)开始的
				// 这里避免了下标0减去1变成-1导致的ArrayIndexOutOfBoundsException
				for (int k = 0; k <= MAXIMUM_BRIGHTNESS; k++) {
					info[i][j][k] = info[i][j][k] + info[i - 1][j][k] + info[i][j - 1][k] - info[i - 1][j - 1][k];
					// 第1个表达式，info[i][j][k]，表示从(1,1)到(i,j)这个矩形内亮度为k的星星有多少
					// 第2个表达式，info[i][j][k]，注意这里其实表示在(i,j)这个点有多少亮度为k的星星
					// 第3个表达式，info[i-1][j][k]，因为[i-1]已经经历过了动态转移方程，所以这里表示的不是一个点，而是一个矩形
					// 把这矩形加上去
					// 第4个表达式，同理，是另一个方向的矩形，加上去
					// 一个点，加上左边一块矩形和上边一块矩形
					// 发现左上角一块矩形加了2次，所以要减去这个矩形
					// 第5个表达式就是左上角的部分，也是一个矩形，减去
					// 这里就是容斥原理，画个图可以看得更明白
				}
			}
		}
		// 下面处理视野
		for (int i = 0; i < NUMBER_OF_VIEWS; i++) {
			int currentMoments = in.nextInt();
			int x1 = in.nextInt();
			int y1 = in.nextInt();
			int x2 = in.nextInt();
			int y2 = in.nextInt();
			// 首先读入视野信息
			int ans = 0;
			for (int initialBrightness = 0; initialBrightness <= MAXIMUM_BRIGHTNESS; initialBrightness++) {
				// 因为info存放的都是初始亮度的信息
				// 所以这里就用初始亮度来遍历
				// 初始亮度对应每种情况，可以把所有的星星都过一遍
				// 从0到MAXIMUM_BRIGHTNESS的闭区间
				int currentBrightness = (initialBrightness + currentMoments) % (MAXIMUM_BRIGHTNESS + 1);
				// 计算当前亮度，方法与第1段代码相同
				// (initialBrightness+currentMoments)%(MAXIMUM_BRIGHTNESS+1)
				int targetStars = info[x2][y2][initialBrightness] - info[x1 - 1][y2][initialBrightness]
						- info[x2][y1 - 1][initialBrightness] + info[x1 - 1][y1 - 1][initialBrightness];
				// 计算目标矩形内有多少星星
				// 也是容斥原理，跟上面动态转移的部分类似，这里简述
				// 先加上(1,1)到(x2,y2)的矩形
				// 然后减去(1,1)到(x1,y2)的矩形和(1,1)到(x2,y1)的矩形
				// 发现有一块多减去了一次，加回来，(1,1)到(x1,y1)的矩形
				// 这里需要特别注意，题目说了在边界上的是属于视野内的
				// 所以加上的时候是[x2][y2]
				// 但是减去的时候不是[x1][y2]，而是[x1-1][y2]
				// [x2][y1-1]和[x1-1][y1-1]同样
				// 这里的减1是要注意的
				// 得到的就是目标星形数量
				// System.out.printf("initialBrightness: %d, targetStars:%d
				// \n",initialBrightness,targetStars);
				ans += currentBrightness * targetStars;
				// 星星数乘上亮度，加到ans
			}
			System.out.println(ans);
		}

	}

}


