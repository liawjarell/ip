package mochi.task;

import mochi.exception.MochiException;

public class ToDos extends Task {

    // Default constructor, not in use anymore
    public ToDos(String description) throws MochiException {
        super(description);
    }

    public ToDos(String[] result) throws MochiException {
        super(result[1]);
    }

    // Used after parsing from saved tasks.
    public ToDos(String description, boolean isCompleted) throws MochiException {
        super(description);
        this.isCompleted = isCompleted;
    }

    // Used to parse from saved tasks
    public static ToDos parseString(String toParse) throws MochiException {
        String[] result = toParse.strip().split(" \\| ", 2);

        try {
            return new ToDos(result[1], result[0].equals("1"));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MochiException(e.getMessage());
        }
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
