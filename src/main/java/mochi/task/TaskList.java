package mochi.task;

import java.util.ArrayList;
import java.util.List;

import mochi.exception.MochiException;


/**
 * TaskList class for storing and retrieving tasks.
 * This class is responsible for storing and retrieving tasks.
 * It also provides methods for manipulating the task list, such as adding, deleting,
 * marking, and unmarking tasks.
 */
public class TaskList {

    /**
     * A List object that represents the list of tasks currently managed by Mochi.
     */
    protected List<Task> tasks;

    /**
     * The total count of tasks currently stored in the task list.
     */
    protected int tasksCount;

    /**
     * Initializes a new instance of the TaskList class.
     *
     * @param tasks The list of tasks to be stored in the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.tasksCount = tasks.size();
    }

    /**
     * Retrieves the list of tasks currently stored in the task list.
     *
     * @return The list of tasks currently stored in the task list.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Retrieves the total count of tasks currently stored in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTasksCount() {
        return this.tasksCount;
    }

    /**
     * Creates a new instance of the appropriate task type and adds it to the task list.
     *
     * @param input The input string array representing the task to be added.
     * @return The newly created task.
     * @throws MochiException If an error occurs while creating the task.
     */
    public Task addTask(String[] input) throws MochiException {
        Task task = null;

        switch (input[0]) {
        case "todo":
            task = new ToDo(input);
            break;
        case "deadline":
            task = new Deadlines(input);
            break;
        case "event":
            task = new Event(input);
            break;
        default:
            throw new AssertionError("Something went wrong in addTask");
        }

        this.tasks.add(task);
        this.tasksCount++;
        return task;

    }

    /**
     * Deletes a task at the specified position in the task list.
     *
     * @param taskPosition The zero-based index of the task to be deleted.
     * @return The deleted task.
     * @throws MochiException If the specified task position is invalid, or any error occurs while deleting the task.
     */
    public Task deleteTask(int taskPosition) throws MochiException {
        Task task = this.tasks.get(taskPosition);
        this.tasks.remove(taskPosition);
        this.tasksCount--;
        return task;
    }

    /**
     * Marks a task at the specified position in the task list as incomplete.
     *
     * @param taskPosition The zero-based index of the task to be marked as incomplete.
     * @return The marked task.
     * @throws MochiException If the specified task position is invalid, or any error occurs while marking the task.
     */
    public Task markTask(int taskPosition) throws MochiException {
        Task task = this.tasks.get(taskPosition);
        task.mark();
        return task;
    }

    /**
     * Unmarks a task at the specified position in the task list as incomplete.
     *
     * @param taskPosition The zero-based index of the task to be unmarked as incomplete.
     * @return The unmarked task.
     * @throws MochiException If the specified task position is invalid, or any error occurs while unmarking the task.
     */
    public Task unmarkTask(int taskPosition) throws MochiException {
        Task task = this.tasks.get(taskPosition);
        task.unmark();
        return task;
    }

    /**
     * Tags a task at the specified position in the task list with the specified tag.
     *
     * @param taskPosition The zero-based index of the task to be tagged.
     * @param tag The tag to be assigned to the task.
     * @return The tagged task.
     */
    public Task tagTask(int taskPosition, String tag) {
        Task task = this.tasks.get(taskPosition);
        task.tag(tag);
        return task;
    }

    /**
     * Filters the tasks in the current task list based on whether their descriptions
     * contain the specified keyword. The comparison is case-insensitive.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A new TaskList containing tasks whose descriptions contain the specified keyword.
     */
    public TaskList find(String keyword) {
        TaskList newTasks = new TaskList(new ArrayList<>());
        for (Task task : this.tasks) {
            if (task.descriptionContainsKeyword(keyword)) {
                newTasks.tasks.add(task);
            }
        }
        return newTasks;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            result = result.concat(String.format("%d.%s\n", i + 1, this.tasks.get(i).toString()));
        }
        return result.trim();
    }
}
