/**
 * Created by robert on 23.04.16.
 */
public enum StructureType {

    NONE("None"),
    GLIDER("Glider"),
    CONST("Const"),
    OSCILLATOR("Oscillator");

    private final String text;

    StructureType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
