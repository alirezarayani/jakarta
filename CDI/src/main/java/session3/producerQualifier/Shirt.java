package session3.producerQualifier;

public class Shirt {

    private Color.Name color;

    public Shirt() {

    }

    public Shirt(Color.Name color) {
        this.color = color;
    }

    public Color.Name getColor() {
        return color;
    }

    public void setColor(Color.Name color) {
        this.color = color;
    }
}
