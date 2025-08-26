public class Event extends Task {
    protected String from;
    protected String to;

    // Default constructor, no longer in use
    public Event(String description) throws MochiException {

        // Split at /from to get title of task.
        super(description.split("\\s*/from\\s*",2)[0]);

        int fromIndex = description.indexOf("/from");
        int toIndex = description.indexOf("/to");
        // Checks if /from exists
        if (fromIndex == -1) {
            throw new MochiException("Please specify a start date or time using /from");
        } // Checks if /to exists
        if (toIndex == -1) {
            throw new MochiException("Please specify a end date or time using /to");
        } // Checks if /from comes before /to
        if (toIndex < fromIndex) {
            throw new MochiException("/from must come before /to");
        }

        // first part is task title, second part is duration
        String[] parts = description.split("\\s*/from\\s*");

        // First part is from, second part is to
        String[] duration = parts[1].split("\\s*/to\\s*");

        String from = duration[0].trim();
        if (from.isEmpty()) {
            throw new MochiException("Please provide a start date or time after /from");
        }

        String to = duration[1].trim();
        if (to.isEmpty()) {
            throw new MochiException("Please provide an end date or time after /to");
        }

        this.from = from;
        this.to = to;
    }

    public Event(String[] results) throws MochiException {
        super(results[1]);
        this.from = results[2];
        this.to = results[3];
    }

    // Used after parsing from saved tasks.
    public Event(String description, boolean isCompleted, String from, String to) throws MochiException{
        super(description);
        this.isCompleted = isCompleted;
        this.from = from;
        this.to = to;
    }

    // Used after parsing from saved tasks.
    public static Event parseString(String toParse) throws MochiException{
        String[] result = toParse.strip().split(" \\| ", 4);

        return new Event(result[1], result[0].equals("1"), result[2], result[3]);
    }

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
