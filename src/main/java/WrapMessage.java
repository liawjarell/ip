public class WrapMessage {
    public static String wrap(String message) {
        return String.format("""
                ________________________________________
                %s
                ________________________________________""", message).indent(4);
    }
}
