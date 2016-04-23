import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by robert on 23.04.16.
 */
public class CellPane extends JPanel {
    private Color defaultBackground;
    private final int X, Y;
    private boolean alive = false;

    public CellPane(int x, int y) {
        X = x;
        Y = y;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackground);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                defaultBackground = Color.GREEN;
                if (alive) {
                    defaultBackground = Color.black;
                    DrawingPanel.getPanel().killLife(X, Y);
                } else DrawingPanel.getPanel().addNewLife(X, Y);
            }
        });
    }

    public void killThisCell() {
        setBackground(Color.black);
        alive = false;
    }

   /* @Override
    public void setBackground(Color bg) {
        defaultBackground = getBackground();
        super.setBackground(bg);
    }*/

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void checkNeighbourhood() {
        System.out.println("Cell " + this.getCords() + " is " + this.alive);
        int x, y;

        CellPane[][] otherCells = DrawingPanel.getPanel().getCells();
        int otherAlive = 0, otherDead = 0;

        x = X - 1;
        for (int j, i = 0; i < 3; i++) {
            if (x < 0) x = DrawingPanel.SIZE - 1;


            y = Y - 1;
            for (j = 0; j < 3; j++) {
                if (y < 0) y = DrawingPanel.SIZE - 1;

                if (x == X && y == Y) {
                    ++y;
                    continue;
                }

                //System.out.println("\tIteration: "+x+", "+y);

                //logic

                try {
                    if (otherCells[x][y].isAlive()) otherAlive++;
                    else otherDead++;
                } catch (Exception e) {
                }

                //end logic

                y++;
                if (y > DrawingPanel.SIZE - 1) y = 0;
            }
            x++;
            if (x > DrawingPanel.SIZE - 1) x = 0;
        }

        if (this.alive) {
            if (otherAlive > 3) DrawingPanel.getPanel().killLife(X, Y);
            else if (otherAlive < 2) this.killThisCell();
        } else {
            if (otherAlive == 3) DrawingPanel.getPanel().killLife(X, Y);
        }

        System.out.println("Finished update of Cell: " + this.getCords() + ".\n" +
                "Alive/Dead neighbours: " + otherAlive + " / " + otherDead);
    }

    public String getCords() {
        return "x: " + X + ", y: " + Y;
    }

}

