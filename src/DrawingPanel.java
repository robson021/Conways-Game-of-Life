import javax.swing.*;
import java.awt.*;

/**
 * Created by student on 2016-04-21.
 */
public class DrawingPanel extends JPanel {
    private static final int SIZE = 20;
    //public static final int WINDOW_SIZE = SIZE * 32;
    private boolean[][] cells = new boolean[SIZE][SIZE];
    private JLabel[][] fields = new JLabel[SIZE][SIZE];

    public DrawingPanel() {
        super(new GridLayout(SIZE, SIZE));
        clearCells();
        init();

        //this.setSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
    }

    private void init() {
        for (int j, i = 0; i < SIZE; i++)
            for (j = 0; j < SIZE; j++) {
                fields[i][j] = new JLabel("x");
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
        } else {
            return false;
        }
        repaint();
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int j, i = 0; i < SIZE; i++) {
            for (j = 0; j < SIZE; j++) {
                if (cells[i][j] == true) {
                    fields[i][j] = new JLabel("o");
                } else {
                    fields[i][j] = new JLabel("x");
                }

            }
        }
        repaint();
    }
}
