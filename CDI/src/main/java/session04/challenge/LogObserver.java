package session04.challenge;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class LogObserver {

    @Inject
    private Event<LogEvent> event;

    public void logEvent(LogEvent logEvent) {
        event.fireAsync(logEvent);
    }
}
