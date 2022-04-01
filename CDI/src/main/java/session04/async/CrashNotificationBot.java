package session04.async;

import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;

public class CrashNotificationBot {
    public void action(@ObservesAsync PriceChangeEvent priceChangeEvent) {
        if (priceChangeEvent.getPriceChange() / priceChangeEvent.getPrice() < -0.1) {
            System.out.println("WARNING: possible stock price crash in progress:" + priceChangeEvent.getStock());
        }
    }
}
