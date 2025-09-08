package mochi.task;

import mochi.exception.MochiException;

/**
 * ToDo class for storing and retrieving tasks.
 */
public class ToDo extends Task {
    /**
     * Initializes a new instance of the ToDo class.
     *
     * @param result The parsed input from Parser.
     * @throws MochiException If an error occurs while parsing the string.
     */
    public ToDo(String[] result) throws MochiException {
        super(result[1]);
    }

    /**
     * Alternate form of initialising a new instance of the ToDo class.
     * Used after parsing string from storage.
     *
     * @param description Description of the task.
     * @param isCompleted Status of the task.
     * @throws MochiException If an error occurs while initialising the task.
     */
    public ToDo(String description, boolean isCompleted) throws MochiException {
        super(description);
        this.isCompleted = isCompleted;
    }

    /**
     * Parses a string from storage to create a new ToDo object.
     *
     * @param toParse The string to be parsed.
     * @return The parsed ToDo object.
     * @throws MochiException If an error occurs while parsing the string.
     */
    public static ToDo parseString(String toParse) throws MochiException {
        String[] result = toParse.strip().split(" \\| ", 2);

        try {
            return new ToDo(result[1], result[0].equals("1"));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MochiException(e.getMessage());
        }
    }

    /**
     * Converts the ToDo object to a string to be saved to storage.
     *
     * @return A string representation of the ToDo object.
     */
    @Override
    public String toSaveString() {
        return String.format("T | %d | %s", this.isCompleted ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + super.getTag();
    }
}
