public class Deadlines extends Task {
    protected String by;

    public Deadlines(String description) throws MochiException {
        // Split at /by to get title of task.
        super(description.split("\\s*/by\\s*", 2)[0]);

        int byIndex = description.indexOf("/by");
        // Checks if /by exists
        if (byIndex == -1) {
            throw new MochiException("Please specify a deadline using /by");
        }

        // First part title, second part deadline
        String[] parts = description.split("\\s*/by\\s*", 2);

        if (parts[1].isEmpty()) {
            throw new MochiException("Please specify a date/time after /by");

        }

        this.by = parts[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
