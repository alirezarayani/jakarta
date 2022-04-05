package session06;

import java.util.ArrayList;
import java.util.List;

public class CustomerWebService implements WebService {

    @Override
    public List<String> getRemoteData() {
        return new ArrayList<String>() {{
            add("Alireza");
            add("Mary");
        }};
    }
}
