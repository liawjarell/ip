package mochi.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import mochi.exception.MochiException;
import mochi.parser.Parser;



/**
 * Deadlines class for storing and retrieving tasks.
 */
public class Deadlines extends Task {
    /**
     * The deadline of the task.
     */
    protected LocalDateTime by;

    /**
     * Main constructor for Deadline.
     *
     * @param result The parsed input from Parser.
     * @throws MochiException If an error occurs while parsing the string.
     */
    public Deadlines(String[] result) throws MochiException {
        super(result[1]);
        this.by = Parser.stringToLocalDateTime(result[2]);
    }

    /**
     * Alternative constructor for Deadline.
     * Used after parsing string from storage.
     *
     * @param description The description of the task.
     * @param isCompleted The status of the task.
     * @param by The deadline of the task.
     * @throws MochiException If an error occurs while initialising the task.
     */
    public Deadlines(String description, boolean isCompleted, String by) throws MochiException {
        super(description);
        this.isCompleted = isCompleted;
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Parses a string from storage to create a new Deadlines object.
     *
     * @param toParse The string to be parsed.
     * @return The parsed Deadlines object.
     * @throws MochiException If an error occurs while parsing the string.
     */
    public static Deadlines parseString(String toParse) throws MochiException {
        String[] result = toParse.strip().split("\\|", 4);

        String description = result[1].trim();
        boolean completed = result[0].trim().equals("1");
        String by = result[2].trim();
        String tag = result[3].trim();

        Deadlines temp = new Deadlines(description, completed, by);
        if (!tag.isEmpty()) {
            temp.tag(tag);
        }
        return temp;
    }

    /**
     * Converts the Deadlines object to a string to be saved to storage.
     *
     * @return A string representation of the Deadlines object.
     */
    @Override
    public String toSaveString() {
        return String.format("D | %d | %s | %s | %s",
                this.isCompleted ? 1 : 0, this.description, this.by.toString(), super.tag);
    }

    @Override
    public String toString() {
        DateTimeFormatter byFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(byFormatter) + ")" + super.getTag();
    }
}
