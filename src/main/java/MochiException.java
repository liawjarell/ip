public class MochiException extends Exception {

    public MochiException(String message) {
        super(Ui.wrap(message));
    }

}
