package session03.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Random;

public class RandomNumberGenerator {

    @Inject
    private Random ran;

    @Produces
    @ApplicationScoped
    public ArrayList<Integer> get() {
        return new ArrayList<Integer>() {{
            add(ran.nextInt(100));
            add(ran.nextInt(100));
            add(ran.nextInt(100));
            add(ran.nextInt(100));
            add(ran.nextInt(100));
        }};
    }
}
