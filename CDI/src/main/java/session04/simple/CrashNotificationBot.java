package session04.simple;

import javax.enterprise.event.Observes;

public class CrashNotificationBot {
    public void action(@Observes PriceChangeEvent priceChangeEvent) {
        if (priceChangeEvent.getPriceChange() / priceChangeEvent.getPrice() < -0.1) {
            System.out.println("WARNING: possible stock price crash in progress:" + priceChangeEvent.getStock());
        }
    }
}
