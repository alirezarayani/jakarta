package session04.challenge;

import javax.enterprise.event.ObservesAsync;

public class ServerLogEvent {

    private void action(@ObservesAsync LogEvent logEvent) {
        if (logEvent.getLevel().equals(LogEvent.Level.SEVERE)) {
            System.out.println("Red Alert = Severe log event: " + logEvent.getMessage());
        }
    }
}
