public class ToDos extends Task{

    public ToDos(String description) throws MochiException {
        super(description);
    }

    public ToDos(String description, boolean isCompleted) throws MochiException{
        super(description);
        this.isCompleted = isCompleted;
    }

    public static ToDos parseString(String toParse) throws MochiException{
        String[] result = toParse.strip().split(" \\| ", 2);

        return new ToDos(result[1], result[0].equals("1"));
    }

    @Override
    public String toSaveString() {
        return String.format("T | %d | %s", this.isCompleted ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
