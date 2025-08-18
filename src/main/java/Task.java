public class Task {
    protected String description;
    protected boolean completed = false;

    public Task(String description) {
        this.description = description;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public String getStatusIcon() {
        return (completed ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
