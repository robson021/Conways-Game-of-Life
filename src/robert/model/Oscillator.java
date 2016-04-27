package robert.model;

import robert.gui.CellPane;

import java.awt.*;
import java.util.List;

/**
 * Created by robert on 25.04.16.
 */
public class Oscillator extends AbstractStructure {
    public static final int SIZE = 3;
    private boolean rotated = false;
    private Color color = Color.CYAN;

    public Oscillator(List<CellPane> cells) {
        super(cells, StructureType.OSCILLATOR);
    }

    @Override
    public void move() {
        CellPane midCell = this.myCells.get(1);
        midCell.changeColor(color);
        int x = midCell.getCordX();
        int y = midCell.getCordY();

        if (!rotated) {
            try {
                CellPane c = drawingPanel.getFieldsAt(x, y - 1);
                c.changeColor(Color.BLACK);
                c = drawingPanel.getFieldsAt(x, y + 1);
                c.changeColor(Color.BLACK);
                c = drawingPanel.getFieldsAt(x + 1, y);
                c.changeColor(color);
                c = drawingPanel.getFieldsAt(x - 1, y);
                c.changeColor(color);
                rotated = !rotated;
            } catch (Exception e) {
            }
        } else {
            try {
                CellPane c = drawingPanel.getFieldsAt(x, y - 1);
                c.changeColor(color);
                c = drawingPanel.getFieldsAt(x, y + 1);
                c.changeColor(color);
                c = drawingPanel.getFieldsAt(x + 1, y);
                c.changeColor(Color.BLACK);
                c = drawingPanel.getFieldsAt(x - 1, y);
                c.changeColor(Color.BLACK);

            } catch (Exception e) {
            }
            rotated = !rotated;
        }

    }
}
