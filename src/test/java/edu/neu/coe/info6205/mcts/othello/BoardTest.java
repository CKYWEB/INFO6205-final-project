package edu.neu.coe.info6205.mcts.othello;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void testInitialBoardSetup() {
        Board board = new Board();
        char[][] expected = new char[8][8];
        expected[3][3] = 'X';
        expected[4][3] = 'O';
        expected[3][4] = 'O';
        expected[4][4] = 'X';
        assertArrayEquals("Initial board setup is incorrect", expected, board.getBoard());
    }

    @Test
    public void testGetWinnerXWins() {
        char[][] boardState = new char[8][8];
        for (int i = 0; i < 8; i++) {
            Arrays.fill(boardState[i], (i % 2 == 0) ? 'X' : 'O');
        }
        boardState[1][1] = 'X';  // Make 'X' have more pieces
        assertEquals("Winner determination failed when X should win", 'X', Board.getWinner(boardState));
    }

    @Test
    public void testGetWinnerTie() {
        char[][] boardState = new char[8][8];
        for (int i = 0; i < 8; i++) {
            Arrays.fill(boardState[i], (i % 2 == 0) ? 'X' : 'O');
        }
        assertEquals("Winner determination failed in case of a tie", 'T', Board.getWinner(boardState));
    }

    @Test
    public void testGetBoardDeepCopy() {
        Board board = new Board();
        char[][] copy = board.getBoard();
        assertNotSame("Deep copy of board failed", copy, board.getBoard());
        assertArrayEquals("Copied board does not match original", copy, board.getBoard());
    }

    @Test
    public void testUpdateBoard() {
        Board board = new Board();
        List<List<CoordinatePair>> flipCoordinates = new ArrayList<>();
        flipCoordinates.add(Arrays.asList(new CoordinatePair(3, 3), new CoordinatePair(3, 4)));

        board.updateBoard('X', "25", flipCoordinates);
        assertEquals("Board update did not place 'X' at expected position", 'X', board.getBoard()[2][5]);
        assertEquals("Board update did not flip 'X' at expected position", 'X', board.getBoard()[3][3]);
        assertEquals("Board update did not flip 'X' at expected position", 'X', board.getBoard()[3][4]);
    }
}

