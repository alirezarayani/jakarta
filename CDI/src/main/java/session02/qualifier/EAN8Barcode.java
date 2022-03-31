package session02.qualifier;

import javax.enterprise.inject.Default;

@Default
public class EAN8Barcode implements CodeGenerator {
    @Override
    public String generateCode() {
        return "EAN8: 818817439871";
    }
}
