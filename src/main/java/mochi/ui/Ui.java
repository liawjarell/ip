package mochi.ui;

import mochi.task.TaskList;

/**
 * Ui class for displaying messages to the user.
 */
@SuppressWarnings("checkstyle:CommentsIndentation")
public class Ui {

    /**
     * Main constructor for Ui.
     * Prints a welcome message to the user upon initialisation.
     */
    public Ui() {
        this.welcome();
    }

    /**
     * Wraps a message in a box of 80 characters.
     *
     * @param message The message to be wrapped.
     * @return The wrapped message.
     */
    public static String wrap(String message) {
        return String.format("""
                ________________________________________________________________________________
                %s
                ________________________________________________________________________________""",
                message).indent(4);
    }

    /**
     * Wraps and prints a message to the user.
     *
     * @param message The message to be printed.
     */
    public void wrapPrint(String message) {
        System.out.println(wrap(message));
    }

    /**
     * Prints a warning message to the user.
     *
     * @param message The warning message to be printed.
     */
    public void warn(String message) {
        this.wrapPrint(message);
    }

    /**
     * Prints a welcome message to the user.
     */
    public String welcome() {
        return Messages.MESSAGE_WELCOME;
    }


    /**
     * Prints a goodbye message to the user.
     */
    public String goodbye() {
        return Messages.MESSAGE_GOODBYE;
    }

    /**
     * Notifies the user that a task has been marked.
     *
     * @param taskString The string representation of the task that has been marked.
     */
    public String notifyMarkTask(String taskString) {
        return String.format(Messages.MESSAGE_TASK_MARKED, taskString);
    }


    /**
     * Notifies the user that a task has been unmarked.
     *
     * @param taskString The string representation of the task that has been unmarked.
     */
    public String notifyUnmarkTask(String taskString) {
        return String.format(Messages.MESSAGE_TASK_UNMARKED, taskString);
        //        this.wrapPrint(String.format(Messages.MESSAGE_TASK_UNMARKED, taskString));
    }

    /**
     * Notifies the user that a task has been added.
     *
     * @param taskString The string representation of the task that has been added.
     * @param numOfTasks The number of tasks in the task list after the addition.
     */
    public String notifyAddTask(String taskString, int numOfTasks) {
        return String.format(Messages.MESSAGE_TASK_ADDED, taskString, numOfTasks);
    }

    /**
     * Notifies the user that a task has been deleted.
     *
     * @param taskString The string representation of the task that has been deleted.
     * @param numOfTasks The number of tasks in the task list after the deletion.
     */
    public String notifyDeleteTask(String taskString, int numOfTasks) {
        return String.format(Messages.MESSAGE_TASK_DELETED, taskString, numOfTasks);
    }

    /**
     * Notifies the user that a task has been tagged.
     *
     * @param taskString The string representation of the task that has been tagged.
     * @return The message to be displayed to the user.
     */
    public String notifyTaggedTask(String taskString) {
        return String.format(Messages.MESSAGE_TAGGED, taskString);
    }

    /**
     * Notifies the user that a task has been untagged.
     *
     * @param taskString The string representation of the task that has been untagged.
     * @return The message to be displayed to the user.
     */
    public String notifyUntaggedTask(String taskString) {
        return String.format(Messages.MESSAGE_UNTAGGED, taskString);
    }

    /**
     * Prints the list of tasks to the user.
     * If the task list is empty, a corresponding message is displayed to indicate
     * that there are no tasks.
     *
     * @param tasks The task list to be printed.
     */
    public String showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return Messages.MESSAGE_EMPTY_LIST;
        } else {
            return String.format(Messages.MESSAGE_LIST_PRINT, tasks.toString());
        }
    }

    /**
     * Displays the tasks that match the user's query.
     * If the task list is empty, a corresponding message is displayed
     * to indicate that there are no matching tasks.
     *
     * @param tasks The task list containing the matching tasks to be displayed.
     */
    public String showMatchingTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return Messages.MESSAGE_EMPTY_LIST;
        } else {
            return String.format(Messages.MESSAGE_LIST_PRINT, tasks.toString());
        }
    }

}
