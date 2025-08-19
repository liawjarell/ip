public class WrapMessage {
    public static String wrap(String message) {
        return String.format("""
                ________________________________________________________________________________
                %s
                ________________________________________________________________________________""",
                message).indent(4);
    }
}
