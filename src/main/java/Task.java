public class Task {
    protected String description;
    protected boolean isCompleted = false;

    public Task(String description) throws MochiException{
        if (description.isEmpty()) {
            throw new MochiException("Task description cannot be empty!");
        }
        this.description = description;
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    public String toSaveString() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
