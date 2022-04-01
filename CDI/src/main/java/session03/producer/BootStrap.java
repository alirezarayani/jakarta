package session03.producer;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class BootStrap {

    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        container.select(RandomNumberService.class).get().print();
        container.select(RandomNumberService.class).get().print();
        container.select(RandomNumberService.class).get().print();
        container.select(RandomNumberService.class).get().print();
    }
}
