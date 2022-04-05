package session07;

import javax.enterprise.inject.Vetoed;

@Vetoed
public class CustomerService {
    public void serviceName(){
        System.out.println("Customer Service");
    }
}
