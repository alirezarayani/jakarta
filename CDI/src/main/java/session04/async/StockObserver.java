package session04.async;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class StockObserver {

    @Inject
    private Event<PriceChangeEvent> event;

    public void priceChange(PriceChangeEvent priceChangeEvent){
        event.fireAsync(priceChangeEvent);
    }
}
