package session06;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class BootStrap {
    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance()
                .disableDiscovery()
                .addPackages(WebService.class)
                .selectAlternatives(CustomerWebServiceTest.class)
                .initialize();

        container.select(UseWebService.class).get().go();
    }
}
