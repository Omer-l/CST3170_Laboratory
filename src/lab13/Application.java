package lab13;

public class Application {
    private Queen[] queens;
    private char[][] matrix;
    private int numberOfQueens;

    public Application(int numberOfQueens) {
        this.queens = new Queen[numberOfQueens];
        this.matrix = initialiseMatrix(numberOfQueens);
        this.numberOfQueens = numberOfQueens;
    }

    private char[][] initialiseMatrix(int numberOfQueens) {
        char[][] matrix = new char[numberOfQueens][numberOfQueens];

        for (int row = 0; row < numberOfQueens; row++)
            for (int column = 0; column < numberOfQueens; column++)
                matrix[row][column] = ' ';

        matrix[0][0] = 'Q';
        queens[0] = new Queen(0, 0);
        return matrix;
    }

    // A utility function to find min
    // of two integers
    static int min(int a, int b) {
        return (a < b) ? a : b;
    }

    // A utility function to find min
    // of three integers
    static int min(int a, int b, int c) {
        return min(min(a, b), c);
    }

    // A utility function to find max
    // of two integers
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    //Is the row and column coordinate a valid slot for a queen?
    public boolean majorDiagonalValid(int row, int column) {
        return false;
    }

    public void run() {
        //starts with 1 since queen number 0 was added at initialiseMatrix()
        for (int queenNumber = 1; queenNumber < numberOfQueens; queenNumber++) {

//            if(!majorDiagonal()) {
//
//            }
        }
    }

    public Queen[] getQueens() {
        return queens;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setQueens(Queen[] queens) {
        this.queens = queens;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public void setNumberOfQueens(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
    }
}
