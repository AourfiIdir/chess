public class Bishop extends Pieces {
    public Bishop(boolean witchPlayer) {
        super(witchPlayer, "B", witchPlayer ? "white_bishop.png" : "black_bishop.png");
    }

    @Override
    public boolean isValidMove(int selectedRow, int selectedCol, int row, int col, Pieces[][] board) {
        if (Math.abs(row - selectedRow) != Math.abs(col - selectedCol)) return false;

        int rowStep = (row > selectedRow) ? 1 : -1;
        int colStep = (col > selectedCol) ? 1 : -1;
        int r = selectedRow + rowStep;
        int c = selectedCol + colStep;

        while (r != row && c != col) {
            if (board[r][c] != null) return false;
            r += rowStep;
            c += colStep;
        }

        return board[row][col] == null || board[row][col].witchPlayer != this.witchPlayer;
    }
}
