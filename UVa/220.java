import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static char[][] board = new char[9][9]; // 棋盘
	private static ArrayList<Point> list = new ArrayList<Point>(); // 用Point记下哪些点是可以操作的点
	private static boolean currentPlayerIsBlack; // 记录当前是谁在下棋，currentPlayer =
													// currentPlayerIsBalck ?
													// Black : White

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();
		in.nextLine();
		for (int t = 0; t < caseNumber; t++) {
			for (int row = 1; row <= 8; row++) {
				String s = in.nextLine();
				for (int i = 1; i <= 8; i++) {
					board[row][i] = s.charAt(i - 1);
				}
				// 因为下标从1开始，所以不能用 board[row] = in.nextLine.toCharArray()，只能一个个赋值
			}
			currentPlayerIsBlack = in.nextLine().equals("B"); // 记录从谁开始
			String cmd = in.nextLine();
			while (!cmd.equals("Q")) { // 不是Q就要一直继续
				if (cmd.equals("L")) {
					for (int r = 1; r <= 8; r++) {
						for (int c = 1; c <= 8; c++) {
							if (isValid(r, c)) { // 遍历，判断点是不是合法，合法就加入list
								list.add(new Point(r, c));
							}
						}
					}
					// 如果list为空，说明没有合法操作，否则遍历list输出
					if (list.isEmpty()) {
						System.out.println("No legal move.");
					} else {
						for (int i = 0; i < list.size(); i++) {
							System.out.printf("(%d,%d)%c", (int) list.get(i).getX(), (int) list.get(i).getY(),
									i == list.size() - 1 ? '\n' : ' ');
						}
					}
					cmd = in.nextLine();
				} else {
					int r = cmd.charAt(1) - '0';
					int c = cmd.charAt(2) - '0';
					/*
					 * 注意： 如果老老实实去判断M(a)(b)是不是合法操作点 即isValid(a,b) 是不会遇到这个问题的
					 * 我是判断Point(a,b)在不在list里面 那么这就要求必须做过L才能做M 如果上来就是M就会导致list为空
					 * 那么所有的操作都将是非法的 —— 感谢LTY帮我找出的错误
					 */
					// if (!list.contains(new Point(r, c))) {
					if (!isValid(r, c)) {
						list.clear();
						currentPlayerIsBlack = !currentPlayerIsBlack;
					}
					board[r][c] = currentPlayerIsBlack ? 'B' : 'W';
					flush(r, c);
					// 操作合法，就把棋子放下，然后更新，也就是翻棋子
					list.clear();
					currentPlayerIsBlack = !currentPlayerIsBlack;
					cmd = in.nextLine();
				}
			}
			// Q：输出棋局
			for (int row = 1; row <= 8; row++) {
				for (int col = 1; col <= 8; col++) {
					System.out.print(board[row][col]);
				}
				System.out.println();
			}
			// 准备下一组
			list.clear();
			if (t != caseNumber - 1)
				System.out.println();
		}
	}
	
	/*
	 * 判断8个方向，存在严重的代码复制
	 * 更加优秀的做法是，保存在一个directions[][]数组里面，形如{{1,1},{-1,1}}
	 * 然后可以再开一个boolean[]数组表示8个方向哪些是合法的
	 * 判断isValid的时候，遍历数组，确定newDirection，然后判断是不是在range里面，是不是可行，可行就设置旗标
	 * flush的时候，遍历数组，根据旗标判断要不要往direction方向刷新
	 */

	private static void flush(int r, int c) {
		// 判断8个方向，翻开棋子，并且计数黑白
		int newRow;
		int newCol;
		if (isUpValid(r, c)) {
			for (newRow = r - 1; newRow >= 1 && newRow <= 8
					&& board[newRow][c] == (currentPlayerIsBlack ? 'W' : 'B'); newRow--) {
				board[newRow][c] = (currentPlayerIsBlack ? 'B' : 'W');
			}
		}
		if (isDownValid(r, c)) {
			for (newRow = r + 1; newRow >= 1 && newRow <= 8
					&& board[newRow][c] == (currentPlayerIsBlack ? 'W' : 'B'); newRow++) {
				board[newRow][c] = (currentPlayerIsBlack ? 'B' : 'W');
			}
		}
		if (isLeftValid(r, c)) {
			for (newCol = c - 1; newCol >= 1 && newCol <= 8
					&& board[r][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newCol--) {
				board[r][newCol] = (currentPlayerIsBlack ? 'B' : 'W');
			}
		}
		if (isRightValid(r, c)) {
			for (newCol = c + 1; newCol >= 1 && newCol <= 8
					&& board[r][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newCol++) {
				board[r][newCol] = (currentPlayerIsBlack ? 'B' : 'W');
			}
		}
		if (isUpLeftValid(r, c)) {
			for (newRow = r - 1, newCol = c - 1; newRow >= 1 && newRow <= 8 && newCol >= 1 && newCol <= 8
					&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newRow--, newCol--) {
				board[newRow][newCol] = (currentPlayerIsBlack ? 'B' : 'W');
			}
		}
		if (isDownRightValid(r, c)) {
			for (newRow = r + 1, newCol = c + 1; newRow >= 1 && newRow <= 8 && newCol >= 1 && newCol <= 8
					&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newRow++, newCol++) {
				board[newRow][newCol] = (currentPlayerIsBlack ? 'B' : 'W');
			}
		}
		if (isDownLeftValid(r, c)) {
			for (newRow = r + 1, newCol = c - 1; newRow >= 1 && newRow <= 8 && newCol >= 1 && newCol <= 8
					&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newRow++, newCol--) {
				board[newRow][newCol] = (currentPlayerIsBlack ? 'B' : 'W');
			}
		}
		if (isUpRightValid(r, c)) {
			for (newRow = r - 1, newCol = c + 1; newRow >= 1 && newRow <= 8 && newCol >= 1 && newCol <= 8
					&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newRow--, newCol++) {
				board[newRow][newCol] = (currentPlayerIsBlack ? 'B' : 'W');
			}
		}
		int balckCounter = 0;
		int whiteCounter = 0;
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if (board[i][j] == 'B')
					balckCounter++;
				else if (board[i][j] == 'W')
					whiteCounter++;
			}
		}
		System.out.printf("Black - %2d White - %2d\n", balckCounter, whiteCounter);
		// 这里有一个坑，输出的格式，%2d
	}

	private static boolean isValid(int r, int c) {
		if (board[r][c] != '-')
			return false;
		// 如果已经有棋子了，就是invalid
		return (isUpValid(r, c) || isDownValid(r, c) || isLeftValid(r, c) || isRightValid(r, c) || isUpLeftValid(r, c)
				|| isDownRightValid(r, c) || isDownLeftValid(r, c) || isUpRightValid(r, c));
		// 8个方向有1个valid就可以了
	}

	private static boolean isUpRightValid(int r, int c) {
		int newRow;
		int newCol;
		for (newRow = r - 1, newCol = c + 1; isInRange(newRow, newCol)
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newRow--, newCol++)
			;
		if (isInRange(newRow, newCol) && newRow != r - 1 && newCol != c + 1
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'B' : 'W'))
			return true;
		else
			return false;
	}

	private static boolean isDownLeftValid(int r, int c) {
		int newRow;
		int newCol;
		for (newRow = r + 1, newCol = c - 1; isInRange(newRow, newCol)
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newRow++, newCol--)
			;
		if (isInRange(newRow, newCol) && newRow != r + 1 && newCol != c - 1
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'B' : 'W'))
			return true;
		else
			return false;
	}

	private static boolean isDownRightValid(int r, int c) {
		int newRow;
		int newCol;
		for (newRow = r + 1, newCol = c + 1; isInRange(newRow, newCol)
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newRow++, newCol++)
			;
		if (isInRange(newRow, newCol) && newRow != r + 1 && newCol != c + 1
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'B' : 'W'))
			return true;
		else
			return false;
	}

	private static boolean isUpLeftValid(int r, int c) {
		int newRow;
		int newCol;
		for (newRow = r - 1, newCol = c - 1; isInRange(newRow, newCol)
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newRow--, newCol--)
			;
		if (isInRange(newRow, newCol) && newRow != r - 1 && newCol != c - 1
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'B' : 'W'))
			return true;
		else
			return false;
	}

	private static boolean isInRange(int newRow, int newCol) {
		// TODO Auto-generated method stub
		return newRow >= 1 && newRow <= 8 && newCol >= 1 && newCol <= 8;
	}

	private static boolean isRightValid(int r, int c) {
		int newRow = r;
		int newCol;
		for (newCol = c + 1; isInRange(newRow, newCol)
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newCol++)
			;
		if (isInRange(newRow, newCol) && newCol != c + 1 && board[newRow][newCol] == (currentPlayerIsBlack ? 'B' : 'W'))
			return true;
		else
			return false;
	}

	private static boolean isLeftValid(int r, int c) {
		int newRow = r;
		int newCol;
		for (newCol = c - 1; isInRange(newRow, newCol)
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newCol--)
			;
		if (isInRange(newRow, newCol) && newCol != c - 1 && board[newRow][newCol] == (currentPlayerIsBlack ? 'B' : 'W'))
			return true;
		else
			return false;
	}

	private static boolean isDownValid(int r, int c) {
		int newRow;
		int newCol = c;
		for (newRow = r + 1; isInRange(newRow, newCol)
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newRow++)
			;
		if (isInRange(newRow, newCol) && newRow != r + 1 && board[newRow][newCol] == (currentPlayerIsBlack ? 'B' : 'W'))
			return true;
		else
			return false;
	}

	private static boolean isUpValid(int r, int c) {
		int newRow;
		int newCol = c;
		for (newRow = r - 1; isInRange(newRow, newCol)
				&& board[newRow][newCol] == (currentPlayerIsBlack ? 'W' : 'B'); newRow--)
			;
		if (isInRange(newRow, newCol) && newRow != r - 1 && board[newRow][newCol] == (currentPlayerIsBlack ? 'B' : 'W'))
			return true;
		else
			return false;
	}
	
	
}
