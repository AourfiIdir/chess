public class Knight extends Pieces {
    public Knight(boolean witchPlayer) {
        super(witchPlayer, "N", witchPlayer ? "white_knight.png" : "black_knight.png");
    }

    @Override
    public boolean isValidMove(int selectedRow, int selectedCol, int row, int col, Pieces[][] board) {
        int dRow = Math.abs(row - selectedRow);
        int dCol = Math.abs(col - selectedCol);

        return ((dRow == 2 && dCol == 1) || (dRow == 1 && dCol == 2))
                && (board[row][col] == null || board[row][col].witchPlayer != this.witchPlayer);
    }
}
