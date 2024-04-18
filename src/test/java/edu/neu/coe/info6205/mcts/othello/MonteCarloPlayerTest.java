package edu.neu.coe.info6205.mcts.othello;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MonteCarloPlayerTest {

    private MonteCarloPlayer player;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        // Redirect System.out to capture debug statements if necessary
        System.setOut(new PrintStream(outContent));
        player = new MonteCarloPlayer('X', 1, true); // debug mode enabled
    }

    @Test
    public void testPlayerInitialization() {
        assertNotNull("Player should be initialized", player);
    }

    @Test
    public void testGetInputWithMockedBoard() {
        // Set up a board state
        char[][] board = new char[8][8]; // Assume some initial setup or use a mocked board
        board[3][3] = 'X';
        board[3][4] = 'O';

        // Update player's current board state
        player.analyzeMoves(board);

        // Run getInput to simulate a move decision
        String move = player.getInput();
        assertNotNull("Player should make a move", move);
        assertFalse("Output should contain move information", outContent.toString().isEmpty());
    }
}
