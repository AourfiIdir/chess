import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePlay extends JPanel {
    public static final int WIDTH = 800, HEIGHT = 800;
    boolean Player = true; // white starts
    boolean pieceSelected = false;
    int selectedRow = -1, selectedCol = -1;
    boolean gameOver = false;

    Board board = new Board(8, 8);

    public GamePlay() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        board.initializeBoard();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / Board.TILE_SIZE;
                int row = e.getY() / Board.TILE_SIZE;

                if (!pieceSelected) {
                    if (board.getBoard()[row][col] != null && board.getBoard()[row][col].witchPlayer == Player) {
                        selectedRow = row;
                        selectedCol = col;
                        pieceSelected = true;
                    }
                } else {
                    if (isValidMoveGeneral(selectedRow, selectedCol, row, col)) {
                        if(movePiece(selectedRow, selectedCol, row, col)){
                            Pieces piece = board.getBoard()[row][col];
                            if(piece instanceof Pawn){
                                Pawn pawn = (Pawn) piece;
                                pawn.notFirstMove();
                            }
                            Player = !Player;
                            check();
                        }

                        if (isGameOver()) gameOver = true;
                    }
                    pieceSelected = false;
                    selectedRow = -1;
                    selectedCol = -1;
                }
                repaint();
            }
        });
    }

    private boolean movePiece(int sRow, int sCol, int eRow, int eCol) {
        Pieces piece = board.getBoard()[sRow][sCol];
        Pieces newPiece;
        if(board.getBoard()[eRow][eCol] != null){
            newPiece = board.getBoard()[eRow][eCol];
        }else{
            newPiece = null;
        }

        board.getBoard()[eRow][eCol] = board.getBoard()[sRow][sCol];
        board.getBoard()[sRow][sCol] = null;
        if(check()){
            board.getBoard()[sRow][sCol] = piece;
            board.getBoard()[eRow][eCol] = newPiece;
            System.out.println("invalide move ---makes check");
            return false;
        }
        return true;
    }

    private boolean isValidMoveGeneral(int sRow, int sCol, int eRow, int eCol) {
        if(board.getBoard()[sRow][sCol] == null) return false;
        Pieces p = board.getBoard()[sRow][sCol];
        if (board.getBoard()[eRow][eCol] != null && board.getBoard()[eRow][eCol].witchPlayer == Player)
            return false;
        return p.isValidMove(sRow, sCol, eRow, eCol, board.getBoard());
    }

    private boolean check() {
        King king = null;
        int kRow = -1, kCol = -1;

        for (int r = 0; r < board.getBoard().length; r++)
            for (int c = 0; c < board.getBoard()[r].length; c++) {
                Pieces p = board.getBoard()[r][c];
                if (p instanceof King && p.witchPlayer == Player) {
                    king = (King)p;
                    kRow = r;
                    kCol = c;
                    king.resetImage();
                }
            }

        if (king == null) return false;

        boolean inCheck = false;
        for (int r = 0; r < board.getBoard().length; r++)
            for (int c = 0; c < board.getBoard()[r].length; c++) {
                Pieces p = board.getBoard()[r][c];
                if (p != null && p.witchPlayer != Player) {
                    if (p.isValidMove(r, c, kRow, kCol, board.getBoard())) {
                        inCheck = true;
                        System.out.println("Check by " + p.getSymbol() + " at (" + r + "," + c + ")");
                    }
                }
            }

        if (inCheck) king.setCheckImage();
        return inCheck;
    }

    private boolean isGameOver() {
        for (int r = 0; r < board.getBoard().length; r++)
            for (int c = 0; c < board.getBoard()[r].length; c++) {
                Pieces p = board.getBoard()[r][c];
                if (p instanceof King && p.witchPlayer == Player) return false;
            }
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.draw(g);
        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 20, 20);
        }
    }

}

