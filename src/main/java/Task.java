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

    public String getStatusIcon() {
        return (completed ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
