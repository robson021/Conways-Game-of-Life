import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by student on 2016-04-21.
 */
public class DrawingPanel extends JPanel {
    private static final int SIZE = 20;
    private static final String EMPTY_TEXT = "    ", ALIVE = "X";
    private boolean[][] cells = new boolean[SIZE][SIZE];
    private JLabel[][] fields = new JLabel[SIZE][SIZE];

    public DrawingPanel() {
        super(new GridLayout(SIZE, SIZE, 3, 3));
        clearCells();
        drawTiles();

        //this.setSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
    }

    private void drawTiles() {
        for (int j, i = 0; i < SIZE; i++)
            for (j = 0; j < SIZE; j++) {
                JLabel l;
                if (cells[i][j] == false) l = new JLabel(EMPTY_TEXT, JLabel.CENTER);
                else l = new JLabel(ALIVE, JLabel.CENTER);
                l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                l.setFont(l.getFont().deriveFont(20f));
                fields[i][j] = l;
                this.add(fields[i][j]);
            }

        repaint();
    }

    public void clearCells() {
        for (int j, i = 0; i < SIZE; i++)
            for (j = 0; j < SIZE; j++)
                cells[i][j] = false;
        repaint();
    }

    public boolean addNewLife(int x, int y) {
        if (x < SIZE && y < SIZE) {
            cells[x][y] = true;
            repaint();
            return true;
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawTiles();
    }
}
