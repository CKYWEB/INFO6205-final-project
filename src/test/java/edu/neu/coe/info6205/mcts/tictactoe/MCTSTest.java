package edu.neu.coe.info6205.mcts.tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.neu.coe.info6205.mcts.core.Node;

public class MCTSTest {

    @Test
    public void checkInitializationAndFirstGameState() {
        TicTacToe game = new TicTacToe();
        TicTacToeNode startNode = new TicTacToeNode(game.start());
        MCTS mcts = new MCTS(startNode);
        assertNotNull(mcts);
        assertEquals("TicTacToe\n. . .\n. . .\n. . .\n", startNode.state().toString());
    }

    @Test
    public void verifySelectMethodBehavior() {
        // Setting up the game and its root node
        TicTacToe game = new TicTacToe();
        TicTacToeNode rootNode = new TicTacToeNode(game.start());
        rootNode.explore();  // Populate the root node with at least one child for selection

        MCTS mcts = new MCTS(rootNode);  // Initializing MCTS with a valid root node
        Node<TicTacToe> selectedNode = mcts.select(rootNode);

        assertNotNull(selectedNode);  // Ensure that the select method does not return null
    }

    @Test
    public void ensureCorrectBestChildIsChosen() {
        TicTacToeNode rootNode = new TicTacToeNode(new TicTacToe().start());
        rootNode.explore();  // Populate with children to ensure there is a selection pool
        Node<TicTacToe> bestChild = new MCTS(rootNode).bestChild(rootNode);
        assertNotNull(bestChild);
        assertTrue(rootNode.children().contains(bestChild));
    }

    @Test
    public void validateSimulationAndBackpropagationEffects() {
        TicTacToeNode node = new TicTacToeNode(new TicTacToe().start());
        MCTS mcts = new MCTS(node);
        int initialPlayouts = node.playouts();
        int result = mcts.simulate(node);
        mcts.backPropagate(node, result);
        assertEquals(initialPlayouts + 1, node.playouts());
    }

}
