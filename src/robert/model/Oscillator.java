package robert.model;

import robert.gui.CellPane;

import java.util.List;

/**
 * Created by robert on 25.04.16.
 */
public class Oscillator extends AbstractStructure {
    public static final int SIZE = 3;

    public Oscillator(List<CellPane> cells) {
        super(cells, StructureType.OSCILLATOR);
    }

    @Override
    public void move() {

    }
}
