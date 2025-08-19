public class MochiException extends Exception {

    public MochiException(String message) {
        super(WrapMessage.wrap(message));
    }

}
