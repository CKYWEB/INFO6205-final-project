package edu.neu.coe.info6205.mcts.othello;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class PlayerTest {

    private static class TestPlayer extends Player {
        public TestPlayer(char piece) {
            this.piece = piece;
            this.otherPiece = (piece == 'X') ? 'O' : 'X';
        }

        @Override
        public String getInput() {
            // Implementing minimally as required for instantiation
            return null;
        }
    }

    private TestPlayer playerX;
    private char[][] board;

    @Before
    public void setUp() {
        playerX = new TestPlayer('X');
        board = new char[8][8]; // Assume a standard 8x8 Othello board
        // Setup a simple board state for testing
        board[3][3] = 'X';
        board[3][4] = 'O';
        board[4][3] = 'O';
        board[4][4] = 'X';
    }

    @Test
    public void testAnalyzeMoves() {
        playerX.analyzeMoves(board);
        assertTrue("Player should have possible moves after analyzing board", playerX.hasMoves());
    }

    @Test
    public void testClearMap() {
        playerX.analyzeMoves(board);
        playerX.clearMap();
        assertFalse("Map should be empty after clearing", playerX.hasMoves());
    }

    @Test
    public void testGetPossibleMoves() {
        playerX.analyzeMoves(board);
        String[] possibleMoves = playerX.getPossibleMoves();
        assertNotNull("Should retrieve possible moves array", possibleMoves);
        assertTrue("Should have possible moves", possibleMoves.length > 0);
    }

    @Test
    public void testHasKey() {
        playerX.analyzeMoves(board);
        // Assuming a valid move exists at (2, 3) which would typically be row 2, col 3 in the array
        assertTrue("Should have key for valid move location", playerX.hasKey(new CoordinatePair(2, 3)));
        assertFalse("Should not have key for invalid move location", playerX.hasKey(new CoordinatePair(0, 0)));
    }

    @Test
    public void testGetCoordinates() {
        playerX.analyzeMoves(board);
        List<List<CoordinatePair>> coords = playerX.getCoordinates("23"); // Assuming "23" maps to row 2, col 3
        assertNotNull("Coordinates list should not be null", coords);
        assertFalse("Coordinates list should not be empty", coords.isEmpty());
    }
}
