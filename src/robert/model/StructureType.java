package robert.model;

/**
 * Created by robert on 23.04.16.
 */
public enum StructureType {

    NONE("None"),
    GLIDER("Glider"),
    STATIC("Static"),
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
