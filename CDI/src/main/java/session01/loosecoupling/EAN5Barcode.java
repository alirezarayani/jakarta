package session01.loosecoupling;

public class EAN5Barcode implements CodeGenerator {
    @Override
    public String generateCode() {
        return "EAN5: 9791234567896";
    }
}
