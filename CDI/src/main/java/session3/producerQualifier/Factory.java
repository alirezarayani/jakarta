package session3.producerQualifier;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class Factory {

    @Produces
    @ShirtFactory
    public Shirt makeShirt(@Any Instance<Shirt> instance, InjectionPoint injectionPoint) {
        Color.Name color = injectionPoint.getAnnotated().getAnnotation(Color.class)
                .name();
        return new Shirt(color);
    }
}
