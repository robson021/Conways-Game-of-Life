package robert.model;

import robert.gui.CellPane;

import java.util.List;
import java.util.Random;

/**
 * Created by robert on 23.04.16.
 */
public abstract class AbstractStructure {
    //protected static final DrawingPanel drawingPanel = DrawingPanel.getPanel();
    protected static final int[] DIRECTIONS = new int[]{-1, 1};
    protected static final Random random = new Random(DIRECTIONS.length);
    protected List<CellPane> cells; //= new ArrayList<>();
    protected StructureType type = null;

    public AbstractStructure(List<CellPane> cells, StructureType type) {
        this.cells = cells;
        this.type = type;
        for (CellPane c : cells) {
            c.setBelongsToStructure(true);
        }
    }

    public List<CellPane> getCells() {
        return cells;
    }

    public void setCells(List<CellPane> cells) {
        this.cells = cells;
    }

    public StructureType getType() {
        return type;
    }

    /*public void moveIt() {
        switch (type) {
            case GLIDER:
                int direction = DIRECTIONS[random.nextInt()];
                int upOrDown = DIRECTIONS[random.nextInt()];
                for (robert.gui.CellPane c : cells) {
                    if (upOrDown == 1) {
                        c.setCordX(c.getCordX() + direction);
                    } else {
                        c.setCordY(c.getCordY() + direction);
                    }
                }
                break;
            case STATIC:
                System.out.println("Can not move const figure.");
                break;
            case OSCILLATOR:
                break;
            default:
                break;
        }
    }*/

    public abstract void move();
}
