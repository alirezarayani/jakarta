package session03.producerQualifier;

import javax.inject.Inject;

public class Tailor {

    @Inject
    @ShirtFactory
    @Color(name = Color.Name.GREEN)
    private Shirt shirt;

    public void makeSuit() {
        System.out.println("Color: " + shirt.getColor());
    }
}
