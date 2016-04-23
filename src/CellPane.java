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
        System.out.println("Cell " + this.getId() + " is " + this.alive);
        for (int y, x = X - 1; x < (X + 2); x++) {
            for (y = Y - 1; y < (Y + 2); y++) {
                if (x == X && y == Y) continue; // skip self
                System.out.println("iteration: " + x + ", " + y + " ");
            }
        }
        System.out.println("Finished update of Cell: " + this.getId());
    }

    public String getId() {
        return "x: " + X + ", y: " + Y;
    }
}

