import edu.princeton.cs.algs4.*;


public class Board {

  private final int[][] blocks;
  private final int dimension;
  
  private Stack<Board> neighbors;
  
  public Board(int[][] blocks) {
    dimension = blocks.length;
    // Retrieve the dimention of the board
    this.blocks = new int[dimension][dimension];
    // the blocks status of current board
    for(int row = 0; row < dimension; row++) {      
      for(int col = 0; col < dimension; col++) {        
        this.blocks[row][col] = blocks[row][col]; 
      }
    }
    neighbors = new Stack<>();
  }
  
  public int dimension() {
    return dimension;
  }
  public int hamming() {
    int numOfWrongPos = 0;
    int index = 1;
    // The number of blocks in
    // their wrong positions    
    for(int row = 0; row < dimension; row++) {      
      for(int col = 0; col < dimension; col++) { 
        if(index != blocks[row][col]
           &&
           blocks[row][col] != 0) {
          numOfWrongPos++;          
        }
        index++;
      }
    }           
  
    return numOfWrongPos;
  }
  
  public int manhattan() {
    int totalDistance = 0;
    for(int row = 0; row < dimension; row++) {      
      for(int col = 0; col < dimension; col++) {
        if(blocks[row][col] != 0) {
          int[] goalPosition = getRowAndCol(blocks[row][col]);
          int dx = abs(goalPosition[0] - row);
          int dy = abs(goalPosition[1] - col);
          totalDistance += dx + dy;
        }
      }
    }           
  
    
    return totalDistance;
  }
  
  public boolean isGoal() {
    int numOfWrongPos = hamming();
    return numOfWrongPos == 0;    
  }
  
  public Board twin() {
    return null;
  }
  
  public boolean equals(Object that) {
    if (this == that) return true;

    if (!(that instanceof Board)) return false;

    Board thatBoard = (Board) that;

    for(int row = 0; row < dimension; row++) {      
      for(int col = 0; col < dimension; col++) { 
        if(this.blocks[col][row] != thatBoard.blocks[col][row])
          return false;
      }
    }           

    return true;
  }
  
  public Iterable<Board> neighbors() {
    if(neighbors.isEmpty()) {
      int rowOfBlank = 0, colOfBlank = 0;
      // The position of blank block
      int[][] dumper = new int[dimension][dimension];
      Board dumpBoard;
    
      for(int row = 0; row < dimension; row++) {
        for(int col = 0; col < dimension; col++) {
          if(blocks[row][col] == 0) {
            rowOfBlank = row;
            colOfBlank = col;
            break;
          }
        }
      }
      // Move Right the left one
    

      if(colOfBlank > 0) {
        cloneTo(dumper);
        dumper[rowOfBlank][colOfBlank]
            =     
            dumper[rowOfBlank][colOfBlank-1];
        dumper[rowOfBlank][colOfBlank-1] = 0;
      
        neighbors.push(new Board(dumper));
      }
      // Move Left the right one

      if(colOfBlank < dimension - 1) {
        cloneTo(dumper);
        dumper[rowOfBlank][colOfBlank]
            =     
            dumper[rowOfBlank][colOfBlank+1];
        dumper[rowOfBlank][colOfBlank+1] = 0;
      
        neighbors.push(new Board(dumper));
      }
    
      // Move Down the upper one
    
      if(rowOfBlank > 0) {
        cloneTo(dumper);
        dumper[rowOfBlank][colOfBlank]
            =
            dumper[rowOfBlank-1][colOfBlank];
        dumper[rowOfBlank-1][colOfBlank] = 0;
      
        neighbors.push(new Board(dumper));
      }
    
      // Move Up the lower one
    
      if(rowOfBlank < dimension - 1) {
        cloneTo(dumper);
        dumper[rowOfBlank][colOfBlank]
            =     
            dumper[rowOfBlank+1][colOfBlank];
        dumper[rowOfBlank+1][colOfBlank] = 0;
        neighbors.push(new Board(dumper));
      }
    }

    return neighbors;
  }
  
  public String toString() {
    StringBuilder output = new StringBuilder();
    output.append(dimension + "\n");
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        output.append(String.format("%2d ", blocks[i][j]));
      }
      output.append("\n");
    }
    return output.toString();
  }
  
  private int getRightIndex(int x, int y) {
    return x * dimension + y + 1;    
  }

  private int abs(int num) {
    if(num < 0) num = -num;
    return num;
  }
 
  
  public static void main(String[] args) // unit tests (not graded)
  {
    int[][] testOne = {{1, 2, 3},
                       {4, 5, 6},
                       {7, 8, 0}};
    
    int[][] testTwo = {{1, 5, 4},
                       {3, 2, 6},
                       {7, 8, 0}};
    
    int[][] testThree = {{8, 1, 3},
                         {4, 0, 2},
                         {7, 6, 5}};

    Board perfectOne = new Board(testOne);
    System.out.println(perfectOne);
    System.out.println(perfectOne.hamming());

    Board perfectTwo = new Board(testOne);


    Board notPerfectOne = new Board(testTwo);
    System.out.println(notPerfectOne.hamming());
    System.out.println(perfectOne.equals(notPerfectOne));

    Board notPerfectTwo = new Board(testThree);
    System.out.println(notPerfectTwo.manhattan());
    System.out.println(notPerfectTwo.hamming());
    System.out.println("Original: ");
    System.out.println(notPerfectTwo);
    
    for(Board aNeightbor : notPerfectTwo.neighbors()) {
      System.out.println(aNeightbor);
    }
  }

  private int[] getRowAndCol(int index) {
    int[] position = new int[2];
    
    index--;
    int row = index / dimension;
    int col = index % dimension;


    position[0] = row;
    position[1] = col;
    
    return position;
  }
  private void cloneTo(int[][] array) {
    for(int row = 0; row < dimension; row++) {
      for(int col = 0; col < dimension; col++) {
        array[row][col] = blocks[row][col];
      }
    }
  }
}
