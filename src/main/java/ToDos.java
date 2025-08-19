public class ToDos extends Task{

    public ToDos(String description) throws MochiException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
