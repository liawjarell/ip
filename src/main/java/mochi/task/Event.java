package mochi.task;

import mochi.exception.MochiException;

/**
 * Event class for storing and retrieving events.
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    protected String from;

    /**
     * The end time of the event.
     */
    protected String to;

    /**
     * Main constructor for Event.
     *
     * @param results The parsed input from Parser.
     * @throws MochiException If an error occurs initialising the task.
     */
    public Event(String[] results) throws MochiException {
        super(results[1]);
        this.from = results[2];
        this.to = results[3];
    }

    /**
     * Alternate constructor for Event.
     * Used after parsing string from storage.
     *
     * @param description The description of the event.
     * @param isCompleted The status of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @throws MochiException If an error occurs while initialising the task.
     */
    public Event(String description, boolean isCompleted, String from, String to) throws MochiException {
        super(description);
        this.isCompleted = isCompleted;
        this.from = from;
        this.to = to;
    }

    /**
     * Parses a string from storage to create a new Event object.
     *
     * @param toParse The string to be parsed.
     * @return The parsed Event object.
     * @throws MochiException If an error occurs while parsing the string.
     */
    public static Event parseString(String toParse) throws MochiException {
        String[] result = toParse.strip().split(" \\| ", 4);

        return new Event(result[1], result[0].equals("1"), result[2], result[3]);
    }

    /**
     * Converts the Event object to a string to be saved to storage.

     * @return A string representation of the Event object.
     */
    @Override
    public String toSaveString() {
        return String.format("E | %d | %s | %s | %s",
                this.isCompleted ? 1 : 0, this.description, this.from, this.to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

}
