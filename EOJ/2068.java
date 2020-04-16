import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * ÕâµÀÌâÊÇBFS¾Í½â¾öÁË
 */

public class Main {

	final static int COORDINATE_SIZE = 500;
	final static int ARRAY_SAFETY_THERHOLD = 7;
	final static int ARRAY_SIZE = COORDINATE_SIZE + COORDINATE_SIZE + ARRAY_SAFETY_THERHOLD;
	final static int MUD_PUDDLE_TAG = -1;
	final static int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		final int BESSIE_X = in.nextInt() + COORDINATE_SIZE;
		final int BESSIE_Y = in.nextInt() + COORDINATE_SIZE;
		int n = in.nextInt();
		int[][] farm = new int[ARRAY_SIZE][ARRAY_SIZE];
		boolean[][] visited = new boolean[ARRAY_SIZE][ARRAY_SIZE];
		
		while (n-- != 0) {
			int mudPuddleX = in.nextInt() + COORDINATE_SIZE;
			int mudPuddleY = in.nextInt() + COORDINATE_SIZE;
			farm[mudPuddleX][mudPuddleY] = MUD_PUDDLE_TAG;
		}
		
		Queue<Point> queue = new LinkedList<Point>();
		final Point BESSIE = new Point(BESSIE_X, BESSIE_Y);
		queue.add(BESSIE);	
		visited[BESSIE_X][BESSIE_Y] = true;
		
		while (!queue.isEmpty()) {
			
			Point currentPoint = queue.poll();
			
			for (int direction = 0; direction < 4; ++direction) {
				
				int newX = currentPoint.x + DIRECTIONS[direction][0];
				int newY = currentPoint.y + DIRECTIONS[direction][1];
				
				if (isInRange(newX, newY) && farm[newX][newY] != MUD_PUDDLE_TAG) {
					
					farm[newX][newY] = farm[currentPoint.x][currentPoint.y] + 1;
					
					if (!visited[newX][newY]) {
						queue.add(new Point(newX, newY));
						visited[newX][newY] = true;
					}
					
					if (newX == COORDINATE_SIZE && newY == COORDINATE_SIZE) {
						System.out.println(farm[COORDINATE_SIZE][COORDINATE_SIZE]);
						System.exit(0);
					}
					
				}
				
			}
			
		}
		
	}

	private static boolean isInRange(int x, int y) {
		return x >= 0 && x <= 2 * COORDINATE_SIZE && y >= 0 && y <= 2 * COORDINATE_SIZE;
	}
	
}
