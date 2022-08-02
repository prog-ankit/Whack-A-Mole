import java.util.*;

class WhackAMole {
	char[][] moleGrid;
	final static int Total_moles = 10;
	int score, molesLeft, attemptsLeft;
	int gridSize, placeMolesleft;

	WhackAMole(int numAttempts, int gridDimension) {

		System.out.println("Welcome to Ankit Bose's Whack A Mole Game.\n");
		System.out.println("You have " + numAttempts + " attempts to whack all the moles.\n");
		System.out.println("Rules of Game -- ");
		System.out.println("1. Coordinates must be entered split through either space or enter. For eg : 5 7");
		System.out.println(
				"2. On Entering same coordinates, a penalty will be fired and two attempts will be removed.");
		System.out.println(
				"3. Negative Co-ordinates will remove your chance to continue the game.");
		System.out.println(
				"4. Your Co-ordinates must lie within 1 to 10. ");
		System.out.println(
				"5. All the Best and Don't Cheat -- Moles are waiting to be whacked.😄 \n\n");

		attemptsLeft = numAttempts;
		moleGrid = new char[gridDimension][gridDimension];
		gridSize = gridDimension;
		score = 0;
		molesLeft = Total_moles;
		placeMolesleft = Total_moles;
	}

	boolean place(int x, int y) {
		if (placeMolesleft > 0 && moleGrid[x][y] != 'M') {
			moleGrid[x][y] = 'M';
			placeMolesleft--;
			return true;
		} else
			return false;

	}

	void whack(int x, int y) {
		if (moleGrid[x][y] == 'M') {
			moleGrid[x][y] = 'W';
			score++;
			attemptsLeft--;
			molesLeft--;
			System.out.println("Bingo!!! Score = " + score);
			System.out.println("Left Attempts: " + attemptsLeft + "\n");
		} else {
			if (moleGrid[x][y] == 'W')
				attemptsLeft -= 2;
			else
				attemptsLeft--;
			System.out.println("Hehehe😛!! Wrong Choice -- Left Attempts: " + attemptsLeft + "\n");
		}
	}

	void printGridToUser() {
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (moleGrid[i][j] == 'M')
					System.out.print("*" + "\t");
				else
					System.out.print(moleGrid[i][j] + "\t");

			}
			System.out.print("\n");
		}

	}

	void printGrid() {
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (moleGrid[i][j] == 'W') {
					System.out.print('W' + "\t");
				} else if (moleGrid[i][j] == 'M') {
					System.out.print('M' + "\t");
				} else {
					System.out.print("*" + "\t");
				}
			}
			System.out.print("\n");
		}
//		Sy//stem.out.println("\n\n\n\n");

	}

	public static void main(String args[]) {
		int size = 10;
		WhackAMole Game = new WhackAMole(50, 10);
		for (int i = 0; i < Game.gridSize; i++) {
			for (int j = 0; j < Game.gridSize; j++) {
				Game.moleGrid[i][j] = '*';
			}

		}
		for (int m = 0; m < 10; m++) {
			int r = (int) (Math.random() * size);
			int c = (int) (Math.random() * size);
			Game.place(r, c);
		}
		Game.printGridToUser();
		Scanner in = new Scanner(System.in);
		int a, b;
		while (true) {
			System.out.println("Enter two coordinates to whack: ");
			a = in.nextInt() - 1;
			b = in.nextInt() - 1;

			if ((a <= -1 || b <= -1) || (a > 10 || b > 10) || Game.attemptsLeft == 0 || Game.molesLeft == 0) {
				System.out.println("Game is Finished!!");
				break;
			}
			Game.whack(a, b);
			Game.printGridToUser();
		}

		Game.printGrid();
	}
}