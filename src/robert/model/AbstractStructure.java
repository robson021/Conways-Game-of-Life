package robert.model;

import robert.gui.CellPane;
import robert.gui.DrawingPanel;

import java.util.List;
import java.util.Random;

/**
 * Created by robert on 23.04.16.
 */
public abstract class AbstractStructure {
    protected static final DrawingPanel drawingPanel = DrawingPanel.getPanel();
    protected static final int[] DIRECTIONS = new int[]{-1, 1};
    protected static final Random random = new Random(DIRECTIONS.length);
    protected List<CellPane> myCells; //= new ArrayList<>();
    protected StructureType type = null;

    protected AbstractStructure(List<CellPane> cells, StructureType type) {
        this.myCells = cells;
        this.type = type;
        for (CellPane c : cells) {
            c.setPartOfStructure(true);
        }
    }

    public List<CellPane> getMyCells() {
        return myCells;
    }

    public void setMyCells(List<CellPane> myCells) {
        this.myCells = myCells;
    }

    public StructureType getType() {
        return type;
    }

    public abstract void move();
}
