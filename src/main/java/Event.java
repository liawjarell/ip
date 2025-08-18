public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description) {
        super(description.split(" /from ",2)[0]);
        String duration = description.split("/from ", 2)[1];
        this.from = duration.split(" /to ", 2)[0];
        this.to = duration.split(" /to ", 2)[1];
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

}
