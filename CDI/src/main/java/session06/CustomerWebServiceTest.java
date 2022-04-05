package session06;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class CustomerWebServiceTest implements WebService {

    @Override
    public List<String> getRemoteData() {
        return new ArrayList<String>() {{
            add("Ahmad");
            add("Reza");
        }};
    }
}
