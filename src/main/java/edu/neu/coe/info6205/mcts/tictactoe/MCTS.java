package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.core.Move;
import edu.neu.coe.info6205.mcts.core.Node;
import edu.neu.coe.info6205.mcts.core.State;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Class representing a Monte Carlo Tree Search algorithm for TicTacToe.
 */
public class MCTS {
    public static TicTacToeNode rootNode;

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        rootNode = new TicTacToeNode(game.start());
        MCTS mcts = new MCTS(rootNode);
        mcts.run(1000); // Run 1000 iterations of MCTS

        // After running MCTS, select the best move
        if (rootNode.children().isEmpty()) {
            System.out.println("No moves available.");
        } else {
            Node<TicTacToe> bestMove = mcts.bestChild(rootNode);
            if (bestMove != null) {
                System.out.println("Recommended move: " + bestMove.state().toString());
                System.out.println("Number of playouts: " + bestMove.playouts());
                System.out.println("Number of wins: " + bestMove.wins());
            } else {
                System.out.println("No best move could be determined.");
            }
        }
    }

    /**
     * Run the Monte Carlo Tree Search algorithm for a specified number of iterations.
     *
     * @param iterations The number of iterations to run the algorithm.
     */
    public void run(int iterations) {
        for (int i = 0; i < iterations; i++) {
            Node<TicTacToe> node = select(rootNode);
            int result = simulate(node);
            backPropagate(node, result);
        }
    }

    /**
     * Select a node in the tree for exploration based on the UCB1 algorithm.
     *
     * @param node The current node in the tree.
     * @return The selected node for exploration.
     */
    Node<TicTacToe> select(Node<TicTacToe> node) {
        while (!node.isLeaf()) {
            if (!node.children().isEmpty()) {
                node = bestChild(node);
            } else {
                node.explore();  // Expand the node and backpropagate
                return node;
            }
        }
        return node;
    }

    /**
     * Select the best child node based on the UCB1 algorithm.
     *
     * @param node The parent node whose children need to be evaluated.
     * @return The best child node according to UCB1.
     */
    Node<TicTacToe> bestChild(Node<TicTacToe> node) {
        if (node.children().isEmpty()) {
            return null;
        }
        return node.children().stream()
                .max(Comparator.comparingDouble(this::ucb1))
                .orElseThrow(() -> new IllegalStateException("No best child found, but children list is not empty"));
    }

    /**
     * Calculate the UCB1 value for a node.
     *
     * @param node The node for which to calculate the UCB1 value.
     * @return The UCB1 value for the node.
     */
    private double ucb1(Node<TicTacToe> node) {
        double c = Math.sqrt(2);
        return node.wins() / (double) node.playouts() +
                c * Math.sqrt(Math.log(node.getParent().playouts()) / (double) node.playouts());
    }

    /**
     * Simulate a game from the given node to the terminal state.
     *
     * @param node The node from which to start the simulation.
     * @return The result of the simulation (winner: 1 for X, -1 for O, 0 for draw).
     */
    int simulate(Node<TicTacToe> node) {
        State<TicTacToe> state = node.state();
        Random random = new Random();
        while (!state.isTerminal()) {
            List<Move<TicTacToe>> moves = new ArrayList<>(state.moves(state.player()));
            Move<TicTacToe> move = moves.get(random.nextInt(moves.size()));
            state = state.next(move);
        }
        return state.winner().orElse(-1);
    }

    /**
     * Backpropagate the result of a simulation up the tree.
     *
     * @param node   The node to start the backpropagation from.
     * @param result The result of the simulation.
     */
    void backPropagate(Node<TicTacToe> node, int result) {
        while (node != null) {
            int playout = node.playouts();
            node.setPlayouts(playout + 1);
            int win = node.wins();
            if ((node.state().player() == 1 && result == 1) ||
                    (node.state().player() == 0 && result == -1)) {
                node.setWins(win + 2);
            }
//                else if (result == 0) {
//                node.setWins(win + 1);
//            }
            node = node.getParent();
        }
    }

    /**
     * Constructor for MCTS.
     *
     * @param rootNode The root node of the Monte Carlo Tree.
     */
    public MCTS(TicTacToeNode rootNode) {
        MCTS.rootNode = rootNode;
    }
}
