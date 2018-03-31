import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

import java.util.Comparator;

public class Solver {
  private MinPQ<Board> optimalsQueue;
  // The priority queues storing all
  // neightbhors of a evolving board
  
  private MinPQ<Board> dualOptimalsQueue;
  // The optimalsQueue using the twin
  // version as a starting point
  
  private Queue<Board> optimalPath;
  // The queue containing all the boards
  // to the solution

  
  private int moveCount;
  // the moving count
  
  private Board lastMove;
  // A place to hold the predecessor board  
  // for each iteration

  private boolean solvable;
  
  public Solver(Board initial) {
    Comparator<Board> manThenHam = 
        (Board b1, Board b2)-> {
      int compareVal = b1.manhattan()-b2.manhattan();
      
      return compareVal == 0 ? b1.hamming() - b2.hamming() : compareVal;

    };
    // Comparator that first compare man values then
    // ham values if tight happens
    
    optimalsQueue = new MinPQ<>(manThenHam);

    
    dualOptimalsQueue = new MinPQ<>(manThenHam);
    
    optimalPath = new Queue<>();
    
    optimalsQueue.insert(initial);
    dualOptimalsQueue.insert(initial.twin());
    
    moveCount = 0;
    lastMove = null;
    
    solvable = solveIt();
  }
  
  public boolean isSolvable() {
    return solvable;
  }
  public int moves() {
    return moveCount;
  }
  public Iterable<Board> solution() {

    return optimalPath;
  }

  private boolean solveIt() {
    Board currentBoard = optimalsQueue.delMin();

    Board currentDualBoard = dualOptimalsQueue.delMin();

    while(!currentBoard.isGoal() || !currentDualBoard.isGoal())
    {     
      
      for(Board aNeightbor : currentBoard.neighbors()) {
        if (lastMove == null
            ||
            !aNeightbor.equals(lastMove))
        {
          optimalsQueue.insert(aNeightbor);
        }        
      }

      for(Board aNeightbor : currentDualBoard.neighbors()) {
        if (lastMove == null
            ||
            !aNeightbor.equals(lastMove))
        {
          dualOptimalsQueue.insert(aNeightbor);
        }        
      }

      lastMove = currentBoard;
      currentBoard = optimalsQueue.delMin();
      currentDualBoard = dualOptimalsQueue.delMin();
      optimalPath.enqueue(lastMove);
      moveCount++;
    }
    optimalPath.enqueue(currentBoard);
    return currentBoard.isGoal();
  }
  
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
    
  }
  
}
