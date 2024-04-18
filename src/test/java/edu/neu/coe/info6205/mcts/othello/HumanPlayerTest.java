package edu.neu.coe.info6205.mcts.othello;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class HumanPlayerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testGetInputValidMove() {
        // Set up the input to simulate player entering "1 2" for row 1, col 2
        String input = "1 2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Set up a mock Player map with valid moves
        HumanPlayer player = new HumanPlayer('X') {
            @Override
            public boolean hasKey(CoordinatePair coord) {
                return coord.getRow() == 0 && coord.getCol() == 1;  // Corresponds to input "1 2"
            }
        };

        assertEquals("01", player.getInput()); // Expect row 0, col 1
        assertTrue(outContent.toString().contains("Player 1 please make a move with the X piece:"));
    }

    @Test
    public void testGetInputInvalidInputThenValid() {
        // Set up the input to simulate player entering an invalid input followed by "1 2"
        String input = "invalid input\n1 2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Set up a mock Player map with valid moves
        HumanPlayer player = new HumanPlayer('X') {
            @Override
            public boolean hasKey(CoordinatePair coord) {
                return coord.getRow() == 0 && coord.getCol() == 1;  // Corresponds to input "1 2"
            }
        };

        assertEquals("01", player.getInput()); // Expect row 0, col 1
        String output = outContent.toString();
        assertTrue(output.contains("Invalid input."));
        assertTrue(output.contains("Player 1 please make a move with the X piece:"));
    }
}

