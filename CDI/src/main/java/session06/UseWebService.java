package session06;

import javax.inject.Inject;

public class UseWebService {
    @Inject
    private WebService webService;

    public void go(){
        webService.getRemoteData().stream()
                .forEach(System.out::println);
    }
}
