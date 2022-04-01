package session03.producer;

import javax.enterprise.inject.Produces;
import java.util.Random;

public class Configuration {

    @Produces
    public Random getRandom(){
        return new Random();
    }
}
