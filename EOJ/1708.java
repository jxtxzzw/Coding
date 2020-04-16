import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();

		while (caseNumber-- != 0) {

			int water = in.nextInt();

			// 读入数据，并构造两个连通器的实例
			Vector[] vectors;
			vectors = new Vector[in.nextInt()];
			for (int i = 0; i < vectors.length; ++i) {
				vectors[i] = new Vector(in.nextInt(), in.nextInt());
			}
			Gheef gheefA = new Gheef(vectors);
			vectors = new Vector[in.nextInt()];
			for (int i = 0; i < vectors.length; ++i) {
				vectors[i] = new Vector(in.nextInt(), in.nextInt());
			}
			Gheef gheefB = new Gheef(vectors);

			double answer;
			if (gheefA.isLowerThan(gheefB)) {
				/*
				 * 如果连通器A整个在B下面 那么不管怎么倒水都只能倒在A 最多把A倒满就一定溢出了 B是不可能有水的 于是等价于只往A倒水就可以了
				 */
				answer = gheefA.fill(water);
			} else if (gheefB.isLowerThan(gheefA)) {
				// 如果B在A的下面就往B倒水
				answer = gheefB.fill(water);
			} else {
				// 这种情况就可能两个连通器都有水
				answer = Gheef.fillBoth(gheefA, gheefB, water);
			}

			System.out.printf("%.3f\n", answer);

		}
	}
}

/**
 * 向量类
 *
 */
class Vector {

	private double x; // 横坐标
	private double y; // 纵坐标

	/**
	 * 取出横坐标
	 * 
	 * @return 横坐标
	 */
	public double getX() {
		return x;
	}

	/**
	 * 取出纵坐标
	 * 
	 * @return 纵坐标
	 */
	public double getY() {
		return y;
	}

	/**
	 * 默认构造器，构造一个坐标为(x,y)的向量点
	 * 
	 * @param x
	 *            横坐标
	 * @param y
	 *            纵坐标
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 计算向量的叉积
	 * 
	 * @param v1
	 *            第1个向量的终止点
	 * @param v2
	 *            第2个向量的终止点
	 * @return 该点与第1个向量的终止点所构成的向量，与该点与第2个向量的终止点所构成的向量，这两个向量的叉积
	 */
	public double cross(Vector v1, Vector v2) {
		return (v1.x - x) * (v2.y - y) - (v1.y - y) * (v2.x - x);
	}

	/**
	 * 计算两个点的连线与直线y=height的交点
	 * 
	 * @param v1
	 *            第1个点
	 * @param v2
	 *            第2个点
	 * @param height
	 *            纵坐标的值
	 * @return 一个向量点，表示交点为(x,y)
	 */
	public static Vector crossOverPoint(Vector v1, Vector v2, double height) {
		// 计算横坐标
		double x = v1.x - ((v1.x - v2.x) * (height - v1.y) / (v2.y - v1.y));
		double y = height; // 纵坐标就是height
		return new Vector(x, y);
	}

	/**
	 * 计算多边形的面积
	 * 
	 * @param drownList
	 *            一个ArrayList，其中的每一个元素表示这个多边形的顶点
	 * @return 多边形的面积
	 */
	public static double size(ArrayList<Vector> drownList) {
		double size = 0;
		// 取出第0个元素作为原点
		Vector basePoint = drownList.get(0);
		// 依次两两取出，计算原点与这两个点的叉积
		for (int i = 2; i < drownList.size(); i++) {
			Vector v1 = drownList.get(i - 1);
			Vector v2 = drownList.get(i);
			// 计算<basePoint,v1>与<basePoint,v2>的叉积，加和
			size += basePoint.cross(v1, v2);
		}
		// 除以二就是有向面积，取绝对值为面积
		return Math.abs(size / 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}

}

/**
 * 连通器类
 * 
 */
class Gheef {
	private Vector[] vectors; // 表示连通器的各个点的数组
	private double cusp; // 连通器的最低点高度
	private double height; // 连通器的高度

	/**
	 * 默认构造器，以vector的各个点为连通器的各个点构造
	 * 
	 * @param vectors
	 */
	public Gheef(Vector[] vectors) {

		// 用clone做immutable的
		this.vectors = vectors.clone();

		// 遍历找到纵坐标最小的下标，得到最低点的高度
		int cuspIndex = 0;
		for (int i = 0; i < vectors.length; ++i) {
			if (vectors[cuspIndex].getY() > vectors[i].getY()) {
				cuspIndex = i;
			}
		}
		cusp = vectors[cuspIndex].getY();

		// 最高点的高度为左右两个边缘的高度中较小的一个
		height = Math.min(vectors[0].getY(), vectors[vectors.length - 1].getY());
	}

	/**
	 * 连通器的最低点高度
	 * 
	 * @return 连通器的最低点高度
	 */
	public double getCusp() {
		return cusp;
	}

	/**
	 * 连通器的高度
	 * 
	 * @return 连通器的高度
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * 同时往两个连通器倒水
	 * 
	 * @param gheefA
	 *            第1个连通器
	 * @param gheefB
	 *            第2个连通器
	 * @param water
	 *            倒水的量
	 * @return 最终的水面高度
	 */
	public static double fillBoth(Gheef gheefA, Gheef gheefB, int water) {

		double lowEndPoint = Math.min(gheefA.cusp, gheefB.cusp); // 二分法最低点
		double highEndPoint = Math.min(gheefA.height, gheefB.height); // 二分法最高点
		double filledHeight = lowEndPoint; // 最终的水面高度

		while (lowEndPoint < highEndPoint) {
			double midPoint = (lowEndPoint + highEndPoint) / 2; // 二分法中点

			double drownSize = 0; // 表示被水淹没的面积

			// 往B倒水
			if (midPoint > gheefB.height) {
				/*
				 * 如果二分尝试的点的高度大于了连通器的高度 说明会有水满出来 那就倒水直到倒满
				 */
				drownSize += drownTillFull(gheefB);
			} else if (midPoint > gheefB.cusp) {
				// 否则的话等价于往B里面倒水
				drownSize += drown(gheefB, midPoint);
			}

			// 往A倒水，同理
			if (midPoint > gheefA.height) {
				drownSize += drownTillFull(gheefA);
			} else if (midPoint > gheefA.cusp) {
				drownSize += drown(gheefA, midPoint);
			}

			// 判断是不是到了二分的终点，如果不是，更新上下边界值
			final double EPSILON = 1E-9;
			if (Math.abs(drownSize - water) < EPSILON) {
				// 如果到了终点，就更新水面高度，返回
				filledHeight = midPoint;
				break;
			} else if (drownSize > water) {
				highEndPoint = midPoint - EPSILON;
			} else {
				lowEndPoint = midPoint + EPSILON;
				filledHeight = lowEndPoint;
			}
			/*
			 * 这里加减一个EPSILON是为了防止如下情况发生： 在一个最多能容纳25的连通器中倒100水
			 * 会抛出ArrayIndexOutOfBoundsException 或者会给出一个错误的水面高度
			 */

		}
		return filledHeight; // 返回水面高度

	}

	/**
	 * 往连通器中倒水，直到倒满
	 * 
	 * @param gheef
	 *            连通器
	 * @return 被水淹没的面积
	 */
	private static double drownTillFull(Gheef gheef) {

		// 存放哪些点被水淹没了
		ArrayList<Vector> drownList = new ArrayList<Vector>();

		// 遍历，找到淹没了哪些点
		int index = 0;
		while (index < gheef.vectors.length) {
			// 如果这个点在高度之下，就说明是被淹没的，加入list
			if (gheef.vectors[index].getY() <= gheef.height) {
				drownList.add(gheef.vectors[index]);
				++index;
			} else {
				--index;
				break;
			}
		}

		// 如果list非空，说明一定有点能够被淹没，于是计算交点
		if (!drownList.isEmpty()) {
			Vector drownBegin = Vector.crossOverPoint(gheef.vectors[index], gheef.vectors[index + 1], gheef.height);
			// 把交点加入list
			drownList.add(drownBegin);
		}

		// 计算被水淹没的面积
		double drownSize = Vector.size(drownList);

		return drownSize;
	}

	/**
	 * 往连通器中加水加到midPoint
	 * 
	 * @param gheef
	 *            连通器
	 * @param midPoint
	 *            加水到的高度
	 * @return 加到这个高度能够被水淹没的面积
	 */
	private static double drown(Gheef gheef, double midPoint) {

		// 存放哪些点被水淹没了
		ArrayList<Vector> drownList = new ArrayList<Vector>();

		// 遍历，找到从哪个点开始淹没
		int index = 0;
		while (index < gheef.vectors.length) {
			// 如果这个点在高度之下，就说明从这个点开始后面的点可能是被淹没的
			if (gheef.vectors[index].getY() <= midPoint) {
				break;
			} else {
				++index;
			}
		}

		/*
		 * 从这个点开始淹没 “这个点”指的是，刚才高度在midPoint下面的点，与它前面一个点，这两个点的连线，与高度midPoint的交点 加入list
		 */
		Vector drownBegin = Vector.crossOverPoint(gheef.vectors[index], gheef.vectors[index - 1], midPoint);
		drownList.add(drownBegin);

		// 遍历，找到从淹没到哪个点为止
		while (index < gheef.vectors.length) {
			if (gheef.vectors[index].getY() < midPoint) {
				// 这些点都在水面下，都是被淹没的
				drownList.add(gheef.vectors[index]);
				++index;
			} else {
				// 直到这个点为止
				--index;
				break;
			}
		}
		// 计算交点，加入到list
		Vector drownEnd = Vector.crossOverPoint(gheef.vectors[index], gheef.vectors[index + 1], midPoint);
		drownList.add(drownEnd);

		// 计算被水淹没的面积
		double drownSize = Vector.size(drownList);

		return drownSize;
	}

	/**
	 * 往自己这个连通器中加水
	 * 
	 * @param water
	 *            水量
	 * @return 最终的水面高度
	 */
	public double fill(double water) {
		double lowEndPoint = cusp; // 二分法最低点
		double highEndPoint = height; // 二分法最高点
		double filledHeight = lowEndPoint; // 最终的水面高度

		while (lowEndPoint < highEndPoint) {
			double midPoint = (lowEndPoint + highEndPoint) / 2; // 二分法中点

			// 计算往自己倒midPoint高度的水会淹没多少面积
			double drownSize = drown(this, midPoint);

			// 判断是不是到了二分的终点，如果不是，更新上下边界值
			final double EPSILON = 1E-9;
			if (Math.abs(drownSize - water) < EPSILON) {
				filledHeight = midPoint;
				break;
			} else if (drownSize > water) {
				highEndPoint = midPoint - EPSILON;
			} else {
				lowEndPoint = midPoint + EPSILON;
				filledHeight = lowEndPoint;
			}
			/*
			 * 这部分与上面是类似的
			 */
		}

		return filledHeight;

	}

	/**
	 * 是不水比给定的连通器低
	 * 
	 * @param that
	 *            给定的连通器
	 * @return 如果是，返回true，否则，false
	 */
	public boolean isLowerThan(Gheef that) {
		return height < that.cusp; // 就是看自己的高度是不是严格小于对面的低谷
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(vectors);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gheef other = (Gheef) obj;
		if (!Arrays.equals(vectors, other.vectors))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Gheef [vectors=" + Arrays.toString(vectors) + ", cusp=" + cusp + ", height=" + height + "]";
	}

}
