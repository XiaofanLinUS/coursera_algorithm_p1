import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

import java.util.Comparator;

public class Solver {
  private MinPQ<Board> hamOptimal;
  private MinPQ<Board> manOptimal;
  
  private Queue<Board> hamOptimalPath;
  private Queue<Board> manOptimalPath;


  
  private int moveCount;
  
  private Board lastMove;

  
  public Solver(Board initial) {
    Comparator<Board> byHam = 
        (Board b1, Board b2)->(b1.hamming()  -b2.hamming());
    
    Comparator<Board> byMan = 
        (Board b1, Board b2)->(b1.manhattan()-b2.manhattan());

    hamOptimal = new MinPQ<>(byHam);
    manOptimal = new MinPQ<>(byMan);
    hamOptimalPath = new Queue<>();
    manOptimalPath = new Queue<>();
    
    hamOptimal.insert(initial);
    manOptimal.insert(initial);
    
    moveCount = 0;
    lastMove = null;
    
  }
  
  public boolean isSolvable() {
    return false;
  }
  public int moves() {
    return 0;
  }
  public Iterable<Board> solution() {
    solveIt();
    return null;
  }

  private void solveIt() {


    Board currentBoard = hamOptimal.delMin();
    
    while(!currentBoard.isGoal())
    {     
      
      for(Board aNeightbor : currentBoard.neighbors()) {
        if (lastMove != null
             &&
            !aNeightbor.equals(lastMove))
        {
          hamOptimal.insert(aNeightbor);
        }
      }
      
      lastMove = currentBoard;
      currentBoard = hamOptimal.delMin();
      hamOptimalPath.enqueue(lastMove);
      moveCount++;
    }
    hamOptimalPath.enqueue(currentBoard);
    
  }
  public static void main(String[] args) {


  }
}
