package edu.neu.coe.info6205.mcts.othello;

import org.junit.Test;
import static org.junit.Assert.*;
public class CoordinatePairTest {

    @Test
    public void testConstructorAndGetters() {
        CoordinatePair cp = new CoordinatePair(3, 5);
        assertEquals("getRow() should return the correct row.", 3, cp.getRow());
        assertEquals("getCol() should return the correct column.", 5, cp.getCol());
    }

    @Test
    public void testToString() {
        CoordinatePair cp = new CoordinatePair(3, 5);
        String expected = "35";
        assertEquals("toString() should concatenate row and column.", expected, cp.toString());
    }

    @Test
    public void testDecodeRow() {
        String position = "35";
        assertEquals("decodeRow() should extract the row correctly from a concatenated position string.", 3, CoordinatePair.decodeRow(position));
    }

    @Test
    public void testDecodeCol() {
        String position = "35";
        assertEquals("decodeCol() should extract the column correctly from a concatenated position string.", 5, CoordinatePair.decodeCol(position));
    }
}
