package edu.neu.coe.info6205.mcts.othello;
import org.junit.Test;
import static org.junit.Assert.*;

public class PseudoPlayerTest {

    @Test
    public void testConstructorWithX() {
        PseudoPlayer player = new PseudoPlayer('X');
        assertEquals("Piece should be 'X' when initialized with 'X'", 'X', player.getPiece());
        assertEquals("OtherPiece should be 'O' when Piece is 'X'", 'O', player.otherPiece);
    }

    @Test
    public void testConstructorWithO() {
        PseudoPlayer player = new PseudoPlayer('O');
        assertEquals("Piece should be 'O' when initialized with 'O'", 'O', player.getPiece());
        assertEquals("OtherPiece should be 'X' when Piece is 'O'", 'X', player.otherPiece);
    }

    @Test
    public void testGetInput() {
        PseudoPlayer player = new PseudoPlayer('X');
        assertNull("getInput should return null for PseudoPlayer", player.getInput());
    }
}
