package edu.neu.coe.info6205.mcts.othello;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {

    private Node root;
    private char[][] initialBoard;

    @Before
    public void setUp() {
        initialBoard = new char[8][8]; // Assume some initial setup
        root = new Node(initialBoard, null, 'X', null);
    }

    @Test
    public void testNodeCreation() {
        assertNotNull("Node creation should be successful", root);
        assertNull("Root node should not have a parent", root.getParent());
        assertNull("Root node's previous move should be null", root.getPrevMove());
    }

    @Test
    public void testBuildChain() {
        root.buildChain();
        assertTrue("Node should have children after building chain", root.hasNodes());
        assertTrue("Node should be marked as used after building chain", root.wasUsed());
    }

    @Test
    public void testRegenerateChain() {
        root.regenerateChain();
        assertTrue("Node should still have children after regenerating chain", root.hasNodes());
    }

    @Test
    public void testBoardStateCopy() {
        char[][] copy = root.boardStateCopy();
        assertArrayEquals("Board state copy should match the original", initialBoard, copy);
    }

    @Test
    public void testGetRandomNode() {
        root.buildChain(); // Build the chain to ensure there are children
        assertNotNull("Should be able to retrieve a random node from the chain", root.getRandomNode());
    }

    @Test
    public void testUpdateStats() {
        root.updateStats(true);
        assertEquals("Wins should be incremented", 1, root.getWins());
        root.updateStats(false);
        assertEquals("Simulations should be incremented", 2, root.getSimulations());
    }

    @Test
    public void testEquals() {
        Node newNode = new Node(initialBoard, root, 'O', "34");
        assertTrue("Nodes with the same board state should be equal", root.equals(newNode));
    }

    @Test
    public void testEqualsGameState() {
        assertTrue("Node should be equal to its own game state", root.equalsGameState(initialBoard));
    }

    @Test
    public void testSetParent() {
        Node child = new Node(initialBoard, root, 'O', "34");
        child.setParent();
        assertNull("Setting parent should make parent null", child.getParent());
    }

    @Test
    public void testHasParent() {
        Node child = new Node(initialBoard, root, 'O', "34");
        assertTrue("Child should have a parent", child.hasParent());
    }
}
