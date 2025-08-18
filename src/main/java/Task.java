public class Task {
    private String description;
    private int position;
    private boolean completed = false;

    public Task(String description, int position) {
        this.description = description;
        this.position = position;
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
            return String.format("%d.[X] %s", position, description);
        } else {
            return String.format("%d.[ ] %s", position, description);
        }
    }

}
