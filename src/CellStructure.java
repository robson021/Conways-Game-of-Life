import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 23.04.16.
 */
public class CellStructure {
    private List<CellPane> cells = new ArrayList<>();
    private StructureType type = null;

    public CellStructure() {
    }

    public CellStructure(List<CellPane> cells, StructureType type) {
        this.cells = cells;
        this.type = type;
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
}
