package session03.producer;

import javax.inject.Inject;
import java.util.ArrayList;


public class RandomNumberService  {

    @Inject
    private ArrayList<Integer> randomNumber;

    public void print() {
        System.out.println(randomNumber);
    }
}
