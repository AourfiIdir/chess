import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Pieces {
    boolean witchPlayer;
    String symbol;
    Image image;

    public Pieces(boolean witchPlayer, String symbol, String imagePath) {
        this.witchPlayer = witchPlayer;
        this.symbol = symbol;

        if (imagePath != null) {
            this.image = new ImageIcon(getClass().getResource("/images/" + imagePath)).getImage();
        }

    }


    public Image getImage() {
        return image;
    }

    public String getSymbol() {
        return symbol;
    }


    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Pieces[][] board);
}
