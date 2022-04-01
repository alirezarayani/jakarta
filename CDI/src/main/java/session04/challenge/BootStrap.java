package session04.challenge;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class BootStrap {
    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance().initialize();

        System.out.println("-------------------------------");
        container.select(LogObserver.class).get().logEvent(new LogEvent(LogEvent.Level.WARN, "Nothing serious has happened."));
        container.select(LogObserver.class).get().logEvent(new LogEvent(LogEvent.Level.SEVERE, "It's all going down!!!"));
        container.select(LogObserver.class).get().logEvent(new LogEvent(LogEvent.Level.ERROR, "Opps an error occurred"));
    }
}
