import java.awt.Color;
import java.util.Arrays;
import java.util.Scanner;

// 这道题用面向对象的思想做，其实很简单
// 思路就是，要我判断是不是一样的，我只会看是不是完全一样
// 所谓完全一样，就是指两个数组是不是一模一样
// 数组一模一样，就是Array的equals，而恰好Color的equals也是重写过的
// 至于旋转之后是不是一样，我不知道，我不会判断，我只会判断是不是一模一样，我不管旋转的事
// 那么你就帮我旋转了之后，我再来看是不是一模一样
// 至于怎么旋转，旋转几次，你自己去做，我不管，这是你的Judge（也就是下面的myJudge()），而不是我equals的事
// 
// 据LTY说用C写会比较复杂
// if...else...写得很混乱，情况比较多
// 大体上是确定了2个面，看其他面是不是能够通过旋转得到

class Cube {
	// Cube类是正方体

	final int UP = 0;
	final int FRONT = 1;
	final int LEFT = 2;
	final int RIGHT = 3;
	final int BACK = 4;
	final int DOWN = 5;
	// 6个面

	Color[] colors = new Color[6];
	// 6个面的颜色

	public Cube(String s) {
		// Constructor，初始化Cube
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'r': {
				colors[i] = new Color(255, 0, 0);
				break;
			}

			case 'g': {
				colors[i] = new Color(0, 255, 0);
				break;
			}
			
			case 'b': {
				colors[i] = new Color(0, 0, 255);
				break;
			}

			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Cube)) {
			return false;
		}
		return myJudge((Cube) obj);
		// equals就是myJudge
	}

	// 3种不同的旋转方向
	public void rotateA() {
		Color c = colors[FRONT];
		colors[FRONT] = colors[RIGHT];
		colors[RIGHT] = colors[BACK];
		colors[BACK] = colors[LEFT];
		colors[LEFT] = c;
	}

	public void rotateB() {
		Color c = colors[FRONT];
		colors[FRONT] = colors[UP];
		colors[UP] = colors[BACK];
		colors[BACK] = colors[DOWN];
		colors[DOWN] = c;
	}

	public void rotateC() {
		Color c = colors[UP];
		colors[UP] = colors[LEFT];
		colors[LEFT] = colors[DOWN];
		colors[DOWN] = colors[RIGHT];
		colors[RIGHT] = c;
	}

	// 唯一的判断标准，当前的是不是和目标的完全一模一样的
	// 旋转之后是不是一样，Judge是判断不了的，Judge只会看是不是一模一样
	private boolean myJudge(Cube other) {
		if (Arrays.equals(colors, other.colors)) {
			return true;
			// 如果一样，true
		} else {
			// 不一样就旋转，旋转之后的是不是一模一样
			// 3**3=27，全部做一遍，每次判断是不是一样的数组
			for (int i = 0; i < 4; i++) {
				// 转了3次要回来，所以是小于4，下同
				rotateA();
				if (Arrays.equals(colors, other.colors)) {
					return true;
				} else {
					for (int j = 0; j < 4; j++) {
						rotateB();
						if (Arrays.equals(colors, other.colors)) {
							return true;
						} else {
							for (int k = 0; k < 4; k++) {
								rotateC();
								if (Arrays.equals(colors, other.colors)) {
									return true;
								}
							}
						}
					}
				}
			}

		}
		// 最终转回来原样，那就是false
		return false;
	}

}

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String s = in.nextLine();
			Cube c1 = new Cube(s.substring(0, 6));
			Cube c2 = new Cube(s.substring(6, 12));
			// main函数很简答，就是返回c1和c2的equals
			if (c1.equals(c2)) {
				System.out.println("TRUE");
			} else {
				System.out.println("FALSE");
			}
		}

	}
}
