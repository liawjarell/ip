public class Deadlines extends Task {
    protected String by;

    public Deadlines(String description) {
        super(description.split(" /by ", 2)[0]);
        this.by = description.split(" /by ", 2)[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
