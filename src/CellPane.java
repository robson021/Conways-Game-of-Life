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
                DrawingPanel.getPanel().addNewLife(X, Y);
            }
        });
    }

    public void killThisCell() {
        setBackground(Color.black);
        alive = false;
    }

    @Override
    public void setBackground(Color bg) {
        defaultBackground = getBackground();
        super.setBackground(bg);
    }

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
}

