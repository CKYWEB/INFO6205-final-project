package edu.neu.coe.info6205.mcts.othello;

/**
 *
 * Represents coordinates on a board. Stores a row and a column.
 * 
 */
public class CoordinatePair {

	private final int row;
	private final int col;

	/**
	 *
	 * Constructor for a CoordinatePair object that represents coordinates on
	 * the board.
	 *
	 * @param row row number of a coordinate on the board.
	 * @param col column number of a coordinate on the board.
	 *
	 */
	public CoordinatePair(int row, int col) {

		this.row = row;
		this.col = col;
	}

	public String toString() {

		String str = "";

		str += row;
		str += col;

		return str;
	}

	public static int decodeRow(String position) {

		return Integer.parseInt(position) / 10;
	}

	public static int decodeCol(String position) {

		return Integer.parseInt(position) % 10;
	}

	public int getRow() {

		return row;
	}

	public int getCol() {

		return col;
	}
}
