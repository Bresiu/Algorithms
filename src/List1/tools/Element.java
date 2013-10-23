package List1.tools;

/**
 * Created by bresiu on 23.10.13.
 */
public class Element {
    int value;
    int initialPosition;
    int numberOfComparison;
    int numberOfInversion;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(int initialPosition) {
        this.initialPosition = initialPosition;
    }

    public int getNumberOfComparison() {
        return numberOfComparison;
    }

    public void setNumberOfComparison(int numberOfComparison) {
        this.numberOfComparison = numberOfComparison;
    }

    public int getNumberOfInversion() {
        return numberOfInversion;
    }

    public void setNumberOfInversion(int numberOfInversion) {
        this.numberOfInversion = numberOfInversion;
    }
}
