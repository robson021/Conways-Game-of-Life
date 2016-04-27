package robert.model;

import robert.gui.CellPane;

import java.util.List;

/**
 * Created by robert on 25.04.16.
 */
public class Glider extends AbstractStructure {
    public static final int SIZE = 3; // must be even number

    public Glider(List<CellPane> cells) {
        super(cells, StructureType.GLIDER);
    }

    @Override
    public void move() {
        int mx = random.nextInt(this.DIRECTIONS.length);
        int my = random.nextInt(this.DIRECTIONS.length);
        int distance = random.nextInt(3) + 2;
        int x2, y2;
        for (CellPane c : this.myCells) {
            x2 = c.getCordX() + mx * distance;
            y2 = c.getCordY() + my * distance;
            c.moveTo(x2, y2);
        }
    }
}
