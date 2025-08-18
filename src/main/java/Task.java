public class Task {
    private String description;
    private boolean completed = false;

    public Task(String description) {
        this.description = description;
    }

    public void markTask() {
        this.completed = true;
    }

    public void unmarkTask() {
        this.completed = false;
    }

    @Override
    public String toString() {
        if (completed) {
            return String.format("[X] %s", description);
        } else {
            return String.format("[ ] %s", description);
        }
    }

}
