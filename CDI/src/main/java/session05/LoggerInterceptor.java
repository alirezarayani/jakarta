package session05;

import javax.annotation.Priority;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

@Interceptor
@Logged
@Priority(Interceptor.Priority.APPLICATION + 100)
public class LoggerInterceptor {

    @AroundInvoke
    private Object doMethodLogging(InvocationContext ic) throws Exception {
        System.out.println("Method name: " + ic.getMethod().getName());
        System.out.println("Parameters : ");
        Arrays.stream(ic.getParameters()).map(Object::toString).forEach(System.out::println);
        return ic.proceed();
    }

    @AroundConstruct
    private Object doClassLogging(InvocationContext ic) throws Exception {
        Long start = System.currentTimeMillis();
        Object returnValue = ic.proceed();
        Long end = System.currentTimeMillis();
        System.out.println("Construction time: " + (end - start));
        return returnValue;
    }
}
