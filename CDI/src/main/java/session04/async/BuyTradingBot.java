package session04.async;

import javax.enterprise.event.ObservesAsync;

public class BuyTradingBot {

    public void action(@ObservesAsync PriceChangeEvent priceChangeEvent) {
        if (priceChangeEvent.getPriceChange() > 0) {
            System.out.println("BUY: " + priceChangeEvent.getStock());
        }
    }
}
