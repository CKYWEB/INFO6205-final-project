package edu.neu.coe.info6205.mcts.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeNodeTest {

    @Test
    public void winsAndPlayouts() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState(Position.parsePosition("X . 0\nX O .\nX . 0", TicTacToe.X));
        TicTacToeNode node = new TicTacToeNode(state);
        assertTrue(node.isLeaf());
        assertEquals(2, node.wins());
        assertEquals(1, node.playouts());
    }

    @Test
    public void state() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState();
        TicTacToeNode node = new TicTacToeNode(state);
        assertEquals(state, node.state());
    }

    @Test
    public void white() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState();
        TicTacToeNode node = new TicTacToeNode(state);
        assertTrue(node.white());
    }

    @Test
    public void children() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState();
        TicTacToeNode node = new TicTacToeNode(state);
        assertNotNull(node.children());
        assertTrue(node.children().isEmpty());
    }

    @Test
    public void addChild() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState();
        TicTacToeNode parentNode = new TicTacToeNode(state);
        TicTacToe.TicTacToeState childState = new TicTacToe().new TicTacToeState();
        parentNode.addChild(childState);
        assertEquals(1, parentNode.children().size());
    }

    @Test
    public void backPropagate() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState(Position.parsePosition("X . .\n0 X X\nX 0 0", TicTacToe.X));
        TicTacToeNode node = new TicTacToeNode(state);
        TicTacToe.TicTacToeState childState1 = new TicTacToe().new TicTacToeState(Position.parsePosition("X X .\n0 X X\nX 0 0", TicTacToe.O));
        TicTacToeNode childNode1 = new TicTacToeNode(childState1);
        childNode1.backPropagate(); // Simulate a scenario for childNode1
        node.addChild(childState1);

        TicTacToe.TicTacToeState childState2 = new TicTacToe().new TicTacToeState(Position.parsePosition("X X 0\n0 X X\nX 0 0", TicTacToe.X));
        TicTacToeNode childNode2 = new TicTacToeNode(childState2);
        childNode2.backPropagate(); // Simulate a scenario for childNode2
        node.addChild(childState2);

        node.backPropagate();

        assertEquals(1, node.wins());
        assertEquals(1, node.playouts());
    }
}