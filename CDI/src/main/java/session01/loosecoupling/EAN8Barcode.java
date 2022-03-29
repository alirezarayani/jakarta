package session01.loosecoupling;

public class EAN8Barcode implements CodeGenerator {
    @Override
    public String generateCode() {
        return "EAN8: 94184600";
    }
}
