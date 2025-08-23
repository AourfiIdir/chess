public class Pawn extends Pieces {

    public Pawn(boolean witchPlayer) {
        super(witchPlayer, "P", witchPlayer ? "white_pawn.png" : "black_pawn.png");
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean isValidMove(int selectedRow, int selectedCol, int row, int col, Pieces[][] board) {
        // Check bounds
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return false;

        int direction = witchPlayer ? 1 : -1;
        // if you defined white as true: they move "down" (increasing row)
        // black as false: they move "up" (decreasing row)

        // Move forward
        if (selectedCol == col && board[row][col] == null) {
            if (row - selectedRow == direction) {
                System.out.println("valid move forward");
                System.out.println("Moving " + (witchPlayer ? "White" : "Black") + " Pawn");

                return true;
            }
        }

        // Capture diagonally
        if (Math.abs(selectedCol - col) == 1 && row - selectedRow == direction) {
            if (board[row][col] != null && board[row][col].witchPlayer != this.witchPlayer) {
                System.out.println("valid capture");
                System.out.println("Moving " + (witchPlayer ? "White" : "Black") + " Pawn");

                return true;
            }
        }

        System.out.println("not valid move");
        return false;
    }
}
