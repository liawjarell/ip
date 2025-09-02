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
        //        this.wrapPrint(Messages.MESSAGE_WELCOME);
    }


    /**
     * Prints a goodbye message to the user.
     */
    public String goodbye() {
        return Messages.MESSAGE_GOODBYE;
        //        this.wrapPrint(Messages.MESSAGE_GOODBYE);
    }

    /**
     * Notifies the user that a task has been marked.
     *
     * @param taskString The string representation of the task that has been marked.
     */
    public String notifyMarkTask(String taskString) {
        return String.format(Messages.MESSAGE_TASK_MARKED, taskString);
        //        this.wrapPrint(String.format(Messages.MESSAGE_TASK_MARKED, taskString));
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
        //        this.wrapPrint(String.format(Messages.MESSAGE_TASK_ADDED, taskString, numOfTasks));
    }

    /**
     * Notifies the user that a task has been deleted.
     *
     * @param taskString The string representation of the task that has been deleted.
     * @param numOfTasks The number of tasks in the task list after the deletion.
     */
    public String notifyDeleteTask(String taskString, int numOfTasks) {
        return String.format(Messages.MESSAGE_TASK_DELETED, taskString, numOfTasks);
        //        this.wrapPrint(String.format(Messages.MESSAGE_TASK_DELETED, taskString, numOfTasks));
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
            //            this.wrapPrint(Messages.MESSAGE_EMPTY_LIST);
        } else {
            return String.format(Messages.MESSAGE_LIST_PRINT, tasks.toString());
            //            this.wrapPrint(String.format(Messages.MESSAGE_LIST_PRINT, tasks.toString()));
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
            //            this.wrapPrint(Messages.MESSAGE_EMPTY_LIST);
        } else {
            return String.format(Messages.MESSAGE_LIST_PRINT, tasks.toString());
            //            this.wrapPrint(String.format(Messages.MESSAGE_LIST_PRINT, tasks.toString()));
        }
    }

    //    public static void main(String[] args) {
    //        mochi.ui.Ui ui = new mochi.ui.Ui();
    //        try {
    //            mochi.task.TaskList testTasks = new mochi.task.TaskList(new ArrayList<>());
    //
    //            testTasks.addTask("todo read book");
    //            ui.notifyAddTask(testTasks.getTasks().get(0).toString(), 1);
    //
    //            testTasks.markTask(0);
    //            ui.notifyMarkTask(testTasks.getTasks().get(0).toString());
    //
    //            testTasks.unmarkTask(0);
    //            ui.notifyUnmarkTask(testTasks.getTasks().get(0).toString());
    //
    //            testTasks.addTask("deadline return book /by today");
    //            ui.notifyAddTask(testTasks.getTasks().get(1).toString(), 2);
    //
    //            ui.showTasks(testTasks);
    //
    //            mochi.task.Task temp = testTasks.getTasks().get(1);
    //            testTasks.deleteTask(1);
    //            ui.notifyDeleteTask(temp.toString(), 1);
    //
    //
    //        } catch (mochi.exception.MochiException e) {
    //            ui.warn(e.getMessage());
    //        }
    //    }


}
