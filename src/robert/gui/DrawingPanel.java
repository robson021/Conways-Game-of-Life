package robert.gui;

import robert.model.AbstractStructure;
import robert.model.StaticStructure;
import robert.model.StructureType;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by student on 2016-04-21.
 */
public class DrawingPanel extends JPanel {
    private static DrawingPanel self = null;
    public static final int SIZE = 20;

    public CellPane[][] getCells() {
        return cells;
    }

    private CellPane[][] cells = new CellPane[SIZE][SIZE];
    private java.util.List<AbstractStructure> structures = new ArrayList<>();

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

    public void addStructure(int index, int x, int y) {
        System.out.print("Attempting to add new structure at: " + x + ", " + y);
        java.util.List<CellPane> cells = new ArrayList<>();
        AbstractStructure structure;
        switch (index) {
            case 1:
                System.out.println(". Type: Glider");
                break;
            case 2:
                System.out.println(". Type: Static");
                // construct
                for (int i = 0; i < StaticStructure.SIZE; i++) {
                    if (this.addNewLife(x, y)) {
                        cells.add(this.cells[x][y]);
                    }
                    x++;
                    y++;
                }
                for (int i = 0; i < StaticStructure.SIZE - 1; i++) {
                    y--;
                    if (this.addNewLife(x, y)) {
                        cells.add(this.cells[x][y]);
                    }
                    x++;
                }



                // add to list
                structure = new StaticStructure(cells, StructureType.STATIC);
                this.structures.add(structure);
                break;
            case 3:
                System.out.println(". Type: Oscillator");
                break;
            default:
                System.out.println("Bad index!");
                break;
        }
    }

    public static DrawingPanel getPanel() {
        return self;
    }

    public CellPane getFieldsAt(int x, int y) {
        return cells[x][y];
    }

    public boolean killLife(int x, int y) {
        if (x < SIZE && y < SIZE && cells[x][y].isAlive()) {
            if (this.cells[x][y].isPartOfStructure()) return false;
            this.cells[x][y].killThisCell();
            MainFrame.updateInfo("Killed life at: " + x + ", " + y);
            return true;
        }
        return false;
    }

    public java.util.List<AbstractStructure> getStructures() {
        return structures;
    }
}
