package session03.producerQualifier;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class BootStrap {

    public static void main(String... args) {

        SeContainer container = SeContainerInitializer
                .newInstance()
                .initialize();

        System.out.println("------------------------------------------------");

        container.select(Tailor.class).get().makeSuit();

        System.out.println("------------------------------------------------");

        container.close();

    }
}
