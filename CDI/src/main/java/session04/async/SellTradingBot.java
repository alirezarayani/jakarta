package session04.async;

import javax.annotation.Priority;
import javax.enterprise.event.ObservesAsync;

public class SellTradingBot {

    public void action(@ObservesAsync @Priority(100) PriceChangeEvent priceChangeEvent) {
        if (priceChangeEvent.getPriceChange() < 0) {
            System.out.println("SELL: " + priceChangeEvent.getStock());
        }
        throw new NullPointerException();
    }
}
