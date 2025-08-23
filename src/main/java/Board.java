import java.awt.*;

public class Board {
    public static final int TILE_SIZE = 100;
    private Pieces[][] board;

    public Board(int rows, int cols) {
        board = new Pieces[rows][cols];
    }

    // Initialize pieces on the board
    public void initializeBoard() {
        // Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(true);  // white pawns
            board[6][i] = new Pawn(false); // black pawns
        }

        // Rooks
        board[0][0] = new Rook(true);
        board[0][7] = new Rook(true);
        board[7][0] = new Rook(false);
        board[7][7] = new Rook(false);

        // Knights
        board[0][1] = new Knight(true);
        board[0][6] = new Knight(true);
        board[7][1] = new Knight(false);
        board[7][6] = new Knight(false);

        // Bishops
        board[0][2] = new Bishop(true);
        board[0][5] = new Bishop(true);
        board[7][2] = new Bishop(false);
        board[7][5] = new Bishop(false);

        // Queens
        board[0][3] = new Queen(true);
        board[7][3] = new Queen(false);

        // Kings
        board[0][4] = new King(true);
        board[7][4] = new King(false);

        // Empty squares remain null
    }

    // Draw the board and pieces
    public void draw(Graphics g) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                // Determine tile color
                Color tileColor = (row + col) % 2 == 0 ? Color.BLUE : Color.GRAY;
                g.setColor(tileColor);
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                // Draw piece if exists
                // draw piece image
                if (board[row][col] != null && board[row][col].getImage() != null) {
                    g.drawImage(board[row][col].getImage(), col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
            }
        }
    }

    // Getter for board array
    public Pieces[][] getBoard() {
        return board;
    }
    public void setBoard(Pieces[][] board) {
        this.board = board;

    }
}
