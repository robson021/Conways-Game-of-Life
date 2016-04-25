package robert.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 23.04.16.
 */
public class CellPane extends JPanel {
    private static final List<CellPane> toUpdateList = new ArrayList<>();
    private static final CellPane[][] otherCells = DrawingPanel.getPanel().getCells();
    private Color defaultBackground;
    private int cordX, cordY;
    private boolean alive = false;
    private boolean belongsToStructure = false;
    private boolean toUpdate = false;

    public CellPane(int x, int y) {
        cordX = x;
        cordY = y;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                defaultBackground = getBackground();
                setBackground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackground);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (alive) {
                    DrawingPanel.getPanel().killLife(cordX, cordY);
                } else DrawingPanel.getPanel().addNewLife(cordX, cordY);
            }
        });
    }

    public void killThisCell() {
        defaultBackground = Color.BLACK;
        setBackground(defaultBackground);
        alive = false;
    }

    public void resurrectThisCell() {
        defaultBackground = Color.GREEN;
        setBackground(defaultBackground);
        alive = true;
    }

    public boolean isBelongsToStructure() {
        return belongsToStructure;
    }

    public void setBelongsToStructure(boolean belongsToStructure) {
        this.belongsToStructure = belongsToStructure;
    }

    public int getCordX() {
        return cordX;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }

    public boolean isToUpdate() {
        return toUpdate;
    }

    public void setToUpdate(boolean toUpdate) {
        this.toUpdate = toUpdate;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    }

    public boolean isAlive() {
        return alive;
    }

    /*public void setAlive(boolean alive) {
        this.alive = alive;
    }*/

    public void checkNeighbourhood() {
        System.out.println("Cell " + this.getCords() + " is " + this.alive);
        if (this.belongsToStructure) {
            System.out.println("This cell is part of structure.");
            return;
        }
        int x, y;

        int otherAlive = 0, otherDead = 0;

        x = cordX - 1;
        for (int j, i = 0; i < 3; i++) {
            if (x < 0) x = DrawingPanel.SIZE - 1;


            y = cordY - 1;
            for (j = 0; j < 3; j++) {

                if (y < 0) y = DrawingPanel.SIZE - 1;
                else if (y > DrawingPanel.SIZE - 1) y = 0;

                if (x == cordX && y == cordY) {
                    ++y;
                    continue;
                }

                //System.out.println("\tIteration: "+x+", "+y);

                //logic ------------------------------------

                try { // fixed
                    if (otherCells[x][y].isAlive()) otherAlive++;
                    else otherDead++;
                } catch (Exception e) {
                    System.out.println("EXCEPTION: " + x + ", " + y);
                }

                //end logic ---------------------------------

                y++;

            }
            x++;
            if (x > DrawingPanel.SIZE - 1) x = 0;
        }

        if (this.alive) {
            if (otherAlive < 2 || otherAlive > 3) {
                //robert.gui.DrawingPanel.getPanel().killLife(cordX, cordY);
                toUpdate = true;
                toUpdateList.add(this);
            }
        } else if (otherAlive == 3) {
            //robert.gui.DrawingPanel.getPanel().addNewLife(cordX, cordY);
            toUpdate = true;
            toUpdateList.add(this);
        }

        System.out.println("Finished update of Cell: " + this.getCords() + ".\n" +
                "Alive/Dead neighbours: " + otherAlive + " / " + otherDead);
    }

    public String getCords() {
        return "x: " + cordX + ", y: " + cordY;
    }

    public void checkNonPeriodic() {
        System.out.println("Cell " + this.getCords() + " is " + this.alive);
        if (this.belongsToStructure) {
            System.out.println("This cell is part of structure.");
            return;
        }
        int otherAlive = 0;
        int otherDead = 0;
        int x, y;

        // TODO: 25.04.16 periodic BC


        System.out.println("Finished update of Cell: " + this.getCords() + ".\n" +
                "Alive/Dead neighbours: " + otherAlive + " / " + otherDead);
    }

    public static List<CellPane> getToUpdateList() {
        return toUpdateList;
    }
}

