import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Created by student on 2016-04-21.
 */
public class DrawingPanel extends JPanel {
    private static DrawingPanel self = null;
    public static final int SIZE = 15;

    public CellPane[][] getCells() {
        return cells;
    }

    private CellPane[][] cells = new CellPane[SIZE][SIZE];

    public DrawingPanel() {
        setLayout(new GridBagLayout());
        self = this;

        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                CellPane cellPane = new CellPane(row, col);
                Border border = null;
                if (row < SIZE - 1) {
                    if (col < SIZE - 1) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < SIZE - 1) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                cells[row][col] = cellPane;
                cellPane.setBorder(border);
                add(cellPane, gbc);
            }
        }
    }


    public void clearCells() {
        for (int j, i = 0; i < SIZE; i++)
            for (j = 0; j < SIZE; j++)
                this.killLife(i, j);
        repaint();
    }

    public boolean addNewLife(int x, int y) {
        if (x < SIZE && y < SIZE) {
            cells[x][y].resurrectThisCell();
            MainFrame.updateInfo("Added new life at: " + x + ", " + y);
            return true;
        }
        return false;
    }

    public static DrawingPanel getPanel() {
        return self;
    }

    public CellPane getFieldsAt(int x, int y) {
        return cells[x][y];
    }

    public boolean killLife(int x, int y) {
        if (x < SIZE && y < SIZE && cells[x][y].isAlive()) {
            this.cells[x][y].killThisCell();
            MainFrame.updateInfo("Killed life at: " + x + ", " + y);
            return true;
        }
        return false;
    }

}
