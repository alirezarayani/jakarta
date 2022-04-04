package session05;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.TimeUnit;

@Logged
public class CustomerService {

    public CustomerService() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }

    public void suspendCustomerAccount(String customerName, String reason) {
        // perform logic that suspends the customer's account.
    }

    public void internalAccountAudit(String customerName) {
        // perform internal audit logic
    }
}
