public class Pawn extends Pieces {
    boolean firstMove = true;

    public Pawn(boolean witchPlayer) {
        super(witchPlayer, "P", witchPlayer ? "white_pawn.png" : "black_pawn.png");
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    public void notFirstMove() {
        this.firstMove = false;
    }

    @Override
    public boolean isValidMove(int selectedRow, int selectedCol, int row, int col, Pieces[][] board) {
        // Bounds check
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return false;


        int step = witchPlayer ? 1 : -1;


        if (col == selectedCol && board[row][col] == null) {
            if (row - selectedRow == step) {
                return true;
            }

            if (firstMove && row - selectedRow == 2 * step) {
                int middleRow = selectedRow + step;
                if (board[middleRow][col] == null && board[row][col] == null) {
                    return true;
                }
            }
        }


        if (Math.abs(col - selectedCol) == 1 && row - selectedRow == step) {
            if (board[row][col] != null && board[row][col].witchPlayer != this.witchPlayer) {
                return true;
            }
        }

        return false;
    }
}
