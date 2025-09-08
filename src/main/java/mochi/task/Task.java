package mochi.task;

import mochi.exception.MochiException;

/**
 * Task class for storing and retrieving tasks.
 * It also provides methods for manipulating the task, such as marking, unmarking, and printing.
 * The class is meant to be abstract and is not meant to be instantiated directly.
 * Instead, it is extended by other task types such as Events, ToDos, and Deadlines.
 * The class is also responsible for parsing and saving tasks to storage.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The status of the task.
     */
    protected boolean isCompleted = false;

    /**
     * The tag of the task.
     */
    protected String tag;

    /**
     * Initializes a new instance of the Task class.
     * The description of the task cannot be empty.
     *
     * @param description The description of the task.
     * @throws MochiException If the description is empty.
     */
    public Task(String description) throws MochiException {
        if (description.isBlank()) {
            throw new MochiException("Task description cannot be empty!");
        }
        this.description = description;
        this.tag = "";
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Unmarks the task as incomplete.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Retrieves the status icon for the task.
     *
     * @return The status icon for the task.
     */
    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    /**
     * Returns a string representation of the task for saving to storage.
     *
     * @return A string representation of the task for saving to storage.
     */
    public String toSaveString() {
        return null;
    }

    /**
     * Parses a string representation of a task and returns the corresponding task object.
     *
     * @param toParse The string representation of the task to be parsed.
     * @return The parsed task object.
     * @throws MochiException If an error occurs while parsing the task.
     */
    public static Task parseString(String toParse) throws MochiException {
        return null;
    }

    /**
     * Checks if the description of the task contains the specified keyword.
     * The comparison is case-insensitive.
     *
     * @param keyword The keyword to check for in the task description.
     * @return true if the keyword is present in the task description; false otherwise.
     */
    public boolean descriptionContainsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Sets the tag of the task.
     *
     * @param tag The tag to be set.
     */
    public void tag(String tag) {
        this.tag = tag;
    }

    /**
     * Gets the tag of the task.
     *
     * @return The tag of the task.
     */
    public String getTag() {
        return this.tag.isBlank() ? "" : "#" + this.tag;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
