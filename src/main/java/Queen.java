public class Queen extends Pieces {
    public Queen(boolean witchPlayer) {
        super(witchPlayer, "Q", witchPlayer ? "white_queen.png" : "black_queen.png");
    }

    @Override
    public boolean isValidMove(int selectedRow, int selectedCol, int row, int col, Pieces[][] board) {
        Rook rook = new Rook(this.witchPlayer);
        Bishop bishop = new Bishop(this.witchPlayer);

        return rook.isValidMove(selectedRow, selectedCol, row, col, board)
                || bishop.isValidMove(selectedRow, selectedCol, row, col, board);
    }
}

