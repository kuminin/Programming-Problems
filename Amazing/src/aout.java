import java.util.Scanner;

class aout {
	static Scanner sc;
	enum Direction {
		NORTH, SOUTH, WEST, EAST
	}


	static class Amazing {
		Direction direction;
		private int x, y;
		private Direction directionList[] = new Direction[4];

		Amazing(Direction direction, int y, int x) {
			this.direction = direction;
			this.x = x;
			this.y = y;

		}
	}

	public static void movingDirection(Amazing a) {

		switch (a.direction) {
		case NORTH:
			a.directionList[0] = Direction.EAST;
			a.directionList[1] = Direction.NORTH;
			a.directionList[2] = Direction.WEST;
			a.directionList[3] = Direction.SOUTH;
			break;
		case SOUTH:
			a.directionList[0] = Direction.WEST;
			a.directionList[1] = Direction.SOUTH;
			a.directionList[2] = Direction.EAST;
			a.directionList[3] = Direction.NORTH;
			break;
		case WEST:
			a.directionList[0] = Direction.NORTH;
			a.directionList[1] = Direction.WEST;
			a.directionList[2] = Direction.SOUTH;
			a.directionList[3] = Direction.EAST;
			break;
		case EAST:
			a.directionList[0] = Direction.SOUTH;
			a.directionList[1] = Direction.EAST;
			a.directionList[2] = Direction.NORTH;
			a.directionList[3] = Direction.WEST;
			break;
		}
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		// Initialize Variables
		while (true) {
			String[] input = sc.nextLine().split(" ");
			int height = Integer.parseInt(input[0]);
			int width = Integer.parseInt(input[1]);
			int[] solution = new int[5];
			if (height == 0 && width == 0) {
				break;
			}
			int[][] maze = new int[height][width];
			Amazing mouse = new Amazing(Direction.EAST, height - 1, 0);

			generateMaze(maze);

			solveMaze(mouse, maze, height, width);

			printSolution(maze, solution);
		}
	}

	public static void solveMaze(Amazing mouse, int maze[][], int height, int width) {
		while (true) {
			movingDirection(mouse);
			for (int i = 0; i < 4; i++) {
				if (mouse.directionList[i] == Direction.NORTH) {
					if (mouse.y - 1 != -1 && maze[mouse.y - 1][mouse.x] != -1) {
						maze[mouse.y - 1][mouse.x]++;
						mouse.y -= 1;
						mouse.direction = Direction.NORTH;
						break;
					}
				} else if (mouse.directionList[i] == Direction.SOUTH) {
					if (mouse.y + 1 != height && maze[mouse.y + 1][mouse.x] != -1) {
						maze[mouse.y + 1][mouse.x]++;
						mouse.y += 1;
						mouse.direction = Direction.SOUTH;
						break;
					}

				} else if (mouse.directionList[i] == Direction.WEST) {
					if (mouse.x - 1 != -1 && maze[mouse.y][mouse.x - 1] != -1) {
						maze[mouse.y][mouse.x - 1]++;
						mouse.x -= 1;
						mouse.direction = Direction.WEST;
						break;
					}
				} else if (mouse.directionList[i] == Direction.EAST) {
					if (mouse.x + 1 != width && maze[mouse.y][mouse.x + 1] != -1) {
						maze[mouse.y][mouse.x + 1]++;
						mouse.x += 1;
						mouse.direction = Direction.EAST;
						break;
					}
				}
			}
			if (maze[height - 1][0] == 1 || (mouse.x == 0 && mouse.y == height-1)) {
				break;
			}
		}
	}

	public static void printSolution(int[][] maze, int[] solution) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] != -1) {
					solution[maze[i][j]]++;
				}
			}
		}
		System.out.printf("%d %d %d %d %d\n", solution[0], solution[1], solution[2], solution[3], solution[4]);
		for (int i = 0; i < solution.length; i++) {
			solution[i] = 0;
		}
	}

	public static void generateMaze(int[][] maze) {
		for (int x = 0; x < maze.length; x++) {
			String mazeRow = sc.nextLine();
			for (int y = 0; y < maze[0].length; y++) {
				if (mazeRow.charAt(y) == '1') {
					maze[x][y] = -1;
				} else {
					maze[x][y] = 0;
				}
			}
		}
	}

}
