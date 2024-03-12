
public class SudokuSolver {
	
	public boolean sudokuSolve(Object[][] sudokuAnswer) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if ((int)sudokuAnswer[row][col] == 0) {
					for (int num = 1; num <= 9; num++) {
						if (isValid(sudokuAnswer, row, col, num)) {
							sudokuAnswer[row][col] = num;
							if (sudokuSolve(sudokuAnswer)) {
								return true;
							}
							sudokuAnswer[row][col] = 0;
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isValid(Object[][] sudokuAnswer, int row, int col, int num) {
		// 행,열 중복 체크
		for (int i = 0; i < 9; i++) {
			if(Integer.parseInt(sudokuAnswer[row][i].toString()) == num || Integer.parseInt(sudokuAnswer[i][col].toString()) == num) {
				return false;
			}
		}

		// 3x3칸 중복 체크
		int startRow = 3 * (row / 3);
		int startCol = 3 * (col / 3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if ((int)sudokuAnswer[startRow + i][startCol + j] == num) {
					return false;
				}
			}
		}
		return true;
	}
}
